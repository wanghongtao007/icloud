package com.iconcloud.core.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class CloudConfig {

	private static CloudConfig instance = null;
	private String oseMasterHostProtocol = null;
	private String oseMasterHostName = null;
	private String oseServicePort = null;
	private String oseAPIVersion = null;
	private String k8sAPIVersion = null;
	private Map<String, String> objectContextPath = null;

	public static final String NULL = "null";
	public static final String KIND = "kind";
	public static final String API_VERSION = "apiVersion";
	public static final String API_VERSION_VALUE = "v1";
	public static final Integer OSE_PROJECTS = 201;
	public static final Integer K8S_POD = 301;
	public static final Integer K8S_NAMESPACE_POD = 302;
	public static final Integer K8S_NAMESPACE_POD_LOG = 303;
	public static final Integer SVC_OAUTH_TOKEN = 401;
    public static final Integer OSE_TEMPLATES = 501;
    public static final Integer OSE_PROCESSED_TEMPLATES = 502;
    public static final Integer OSE_CREATE_OBJECT = 602;
    public static final Integer OSE_QUERY_OBJECT = 603;
    
    
    
	public static final String SESSION_OSE_TOKEN_KEYNAME = "OSE-TOKEN";
	public static final String SESSION_AUTHENTICATION_KEYNAME = "CLOUD-AUTHENTICATION";
	public static final String SESSION_AUTHENTICATED = "AUTHENTICATED";
	public static final String SESSION_USER_NAME = "USER_NAME";

	//Resource Definition
	private final String CONFIG_FILENAME = "/iConCloud.properties";

	private Map<Integer, String> SERVICE_ENDPNT_MAP = null;
	public static Logger log = Logger.getLogger(CloudConfig.class);
	public static Boolean debug = true;

	private CloudConfig(){
		Properties p = null;
		
		try {
			p = loadConfiguration();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.oseMasterHostProtocol = p.getProperty("oseMasterHostProtocol");
		this.oseMasterHostName = p.getProperty("oseMasterHostName");
		this.oseServicePort = p.getProperty("oseServicePort");
		this.oseAPIVersion = p.getProperty("oseAPIVersion");
		this.k8sAPIVersion = p.getProperty("k8sAPIVersion");
		
		objectContextPath = new HashMap<String, String>();
		objectContextPath.put("buildconfig",oseAPIVersion);
		objectContextPath.put("build",oseAPIVersion);
		objectContextPath.put("event",k8sAPIVersion);
		objectContextPath.put("deploymentconfig",oseAPIVersion);
		objectContextPath.put("group",oseAPIVersion);
		objectContextPath.put("hostsubnet",oseAPIVersion);
		objectContextPath.put("identitie",oseAPIVersion);
		objectContextPath.put("image",oseAPIVersion);
		objectContextPath.put("imagestreammapping",oseAPIVersion);
		objectContextPath.put("imagestream",oseAPIVersion);
		objectContextPath.put("processedtemplate",oseAPIVersion);
		objectContextPath.put("route",oseAPIVersion);
		objectContextPath.put("template",oseAPIVersion);
		objectContextPath.put("user",oseAPIVersion);
		objectContextPath.put("service",k8sAPIVersion);
		objectContextPath.put("serviceaccount",k8sAPIVersion);
		objectContextPath.put("limitrange",k8sAPIVersion);
		objectContextPath.put("persistentvolumeclaim",k8sAPIVersion);
		objectContextPath.put("pod",k8sAPIVersion);
		objectContextPath.put("portforward",k8sAPIVersion);
		objectContextPath.put("replicationcontroller",k8sAPIVersion);
		objectContextPath.put("secret",k8sAPIVersion);
		objectContextPath.put("node",k8sAPIVersion);
		objectContextPath.put("persistentvolume",k8sAPIVersion);
		objectContextPath.put("resourcequota",k8sAPIVersion);
		
		
		SERVICE_ENDPNT_MAP = new HashMap<Integer, String>();
		SERVICE_ENDPNT_MAP.put(SVC_OAUTH_TOKEN, "/oauth/token/request");
		SERVICE_ENDPNT_MAP.put(OSE_PROJECTS, oseAPIVersion + "/projects");
		SERVICE_ENDPNT_MAP.put(K8S_POD, k8sAPIVersion + "/pods");
		SERVICE_ENDPNT_MAP.put(K8S_NAMESPACE_POD, k8sAPIVersion + "/namespaces/{0}/pods");
		SERVICE_ENDPNT_MAP.put(K8S_NAMESPACE_POD_LOG, k8sAPIVersion + "/namespaces/{0}/pods/{1}/log");
		//SERVICE_ENDPNT_MAP.put(OSE_TEMPLATES, oseAPIVersion + "/namespaces/{0}/templates/{1}?labelSelector=cmb=yes");
		SERVICE_ENDPNT_MAP.put(OSE_TEMPLATES, oseAPIVersion + "/namespaces/{0}/templates/{1}");
		SERVICE_ENDPNT_MAP.put(OSE_PROCESSED_TEMPLATES, oseAPIVersion + "/namespaces/{0}/processedtemplates");
		SERVICE_ENDPNT_MAP.put(OSE_CREATE_OBJECT, "{0}/namespaces/{1}/{2}s");
		SERVICE_ENDPNT_MAP.put(OSE_QUERY_OBJECT, "{0}/namespaces/{1}/{2}s{3}");
		
		
	}

	private Properties loadConfiguration() throws IOException{
		Properties p = new Properties();
		try{
		    p.load(this.getClass().getResourceAsStream(this.CONFIG_FILENAME));
		}catch(IOException e){
			log.error("读取配置文件错误，请检查配置文件位置及配置！");
			throw e;
		}
		return p;
	}

	synchronized public static CloudConfig getInstance() {
		if (instance == null) {
			instance = new CloudConfig();
		}

		return instance;
	}

	public String getServiceEndpoint(Integer service) {
		StringBuilder sb = new StringBuilder();
		sb.append(oseMasterHostProtocol).append(oseMasterHostName);
		sb.append(":").append(oseServicePort);
		sb.append(SERVICE_ENDPNT_MAP.get(service));
		return sb.toString();
	}

	public Map<String, String> getObjectContextPath() {
		return objectContextPath;
	}

	public void setObjectContextPath(Map<String, String> objectContextPath) {
		this.objectContextPath = objectContextPath;
	}
	
	
}
