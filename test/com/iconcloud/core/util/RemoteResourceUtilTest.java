package com.iconcloud.core.util;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;


public class RemoteResourceUtilTest {
	private Logger log = Logger.getLogger(RemoteResourceUtilTest.class);
	private String testUsername = "redhat";
	private String testPassword = "welcome1"; 
	private String testAuthUrl = "https://master.example.com:8443/oauth/token/request";
	private String testProjectsUrl = "https://master.example.com:8443/oapi/v1/projects";


	@Test
	public void testRequestRemoteToken() throws Exception{
		String t = RemoteResourceUtil.requestOAuthToken(testAuthUrl, testUsername, testPassword);
		Assert.assertNotNull(t);
		log.info(t);		
	}
	
	@Test
	public void testRequestRemoteResources() throws Exception{
		String t = RemoteResourceUtil.requestOAuthToken(testAuthUrl, testUsername, testPassword);
		Object r = RemoteResourceUtil.getResources(testProjectsUrl, t);
		Assert.assertNotNull(r);
		log.info(r);		
	}
	
	@Test
	public void testGETEndpoint() throws Exception{
		String resUrl = "https://master.example.com:8443/api/v1/namespaces/mobilebank/componentstatuses/mobilebank-2-build";
		String t = RemoteResourceUtil.requestOAuthToken(testAuthUrl, testUsername, testPassword);
		Object r = RemoteResourceUtil.getResources(resUrl, t);
		log.info(r);
	}
	
}
