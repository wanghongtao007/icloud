package com.iconcloud.core.util;

import java.nio.charset.Charset;
import java.security.cert.X509Certificate;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.iconcloud.core.config.CloudConfig;

@SuppressWarnings("deprecation")
public class RemoteResourceUtil {

	private final static String OSE_TRANS_PROTOCOL = "https";
	private static final int OSE_HTTPS_PORT = 8443;
	private static final String TOKEN_END_TAG = "</code>";
	private static final String TOKEN_START_TAG = "<code>";

	public static Logger log = Logger.getLogger(RemoteResourceUtil.class);

	public static Object getResources(String url, String token) throws Exception {
		HttpClient client = genInsecureHttpClient();

		HttpGet get = new HttpGet(url);
		if (CloudConfig.debug) {
			log.debug(url);
		}

		get.setHeader("Authorization", "Bearer " + token);
		get.addHeader("Content-Type", "application/json; charset=UTF-8");

		HttpResponse rsp = client.execute(get);
		String body = EntityUtils.toString(rsp.getEntity(), "UTF-8");
		if (CloudConfig.debug) {
			log.debug(body);
		}

		return body;
	}

	public static Object postResources(String url, String token, String payload) throws Exception {
		HttpClient client = genInsecureHttpClient();

		HttpPost post = new HttpPost(url);
		if (CloudConfig.debug) {
			log.debug("postResources request");
			log.debug(url);
			log.debug(payload);
		}

		post.setHeader("Authorization", "Bearer " + token);
		post.addHeader("Content-Type", "application/json; charset=UTF-8");
		StringEntity e = new StringEntity(payload);
		post.setEntity(e);

		HttpResponse rsp = client.execute(post);
		String body = EntityUtils.toString(rsp.getEntity(), "UTF-8");
		if (CloudConfig.debug) {
			log.debug("Response");
			log.debug(body);
		}

		return body;
	}

	public static String requestOAuthToken(String url, String username, String password) throws Exception {
		DefaultHttpClient client = (DefaultHttpClient) genInsecureHttpClient();

		String auth = username + ":" + password;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));
		String authHeader = "Basic " + new String(encodedAuth);

		HttpGet get = new HttpGet(url);
		if (CloudConfig.debug) {
			log.debug(url);
		}
		get.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

		HttpResponse rsp = client.execute(get);
		
		if (CloudConfig.debug) {
			log.debug(rsp.getStatusLine().getStatusCode());
		}
		
		String body = EntityUtils.toString(rsp.getEntity());
		
		if (CloudConfig.debug) {
			log.debug(body.toString());
		}

		int s = body.indexOf(TOKEN_START_TAG);
		int e = body.indexOf(TOKEN_END_TAG);

		String r = (s > 0 && e > s) ? r = body.substring(s + TOKEN_START_TAG.length(), e) : null;
		return r;
	}

	// For insure HTTP connection
	private static HttpClient genInsecureHttpClient() throws Exception {
		TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
			@Override
			public boolean isTrusted(X509Certificate[] certificate, String authType) {
				return true;
			}
		};

		SSLSocketFactory sf = new SSLSocketFactory(acceptingTrustStrategy,
				SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme(OSE_TRANS_PROTOCOL, OSE_HTTPS_PORT, sf));
		ClientConnectionManager ccm = new PoolingClientConnectionManager(registry);

		DefaultHttpClient hc = new DefaultHttpClient(ccm);
		return hc;
	}
}
