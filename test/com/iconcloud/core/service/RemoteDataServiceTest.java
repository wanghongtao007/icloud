package com.iconcloud.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.iconcloud.core.config.CloudConfig;
import com.iconcloud.core.model.DataObject;
import com.iconcloud.core.model.DataObjectList;
import com.iconcloud.core.model.DeploymentConfig;
import com.iconcloud.core.model.PostObject;
import com.iconcloud.core.model.ProcessedTemplate;
import com.iconcloud.core.model.Template;
import com.iconcloud.core.util.RemoteResourceUtil;

public class RemoteDataServiceTest {
	public static Logger log = Logger.getLogger(RemoteDataServiceTest.class);
	private String testUsername = "redhat";
	private String testPassword = "welcome1"; 
	
	@Test
	public void testgetProjects()throws Exception{
		RemoteDataService s = new RemoteDataService();
		String t = RemoteResourceUtil.requestOAuthToken(CloudConfig.getInstance().getServiceEndpoint(CloudConfig.SVC_OAUTH_TOKEN), testUsername, testPassword);
		s.getProjects(t);
	}
	
//	@Test
//	public void testGetPodsByProject()throws Exception{
//		RemoteDataService s = new RemoteDataService();
//		String t = RemoteResourceUtil.requestOAuthToken(CloudConfig.getInstance().getServiceEndpoint(CloudConfig.SVC_OAUTH_TOKEN), testUsername, testPassword);
//		Object r = s.getPodsByProject("mobilebank",t);
//		log.info(r);
//	}
	
	@Test
	public void testGetTemplatesByName()throws Exception{
		RemoteDataService s = new RemoteDataService();
		String t = RemoteResourceUtil.requestOAuthToken(CloudConfig.getInstance().getServiceEndpoint(CloudConfig.SVC_OAUTH_TOKEN), testUsername, testPassword);
		Object r = s.getTemplates("openshift", "mysql-ephemeral", t);
		log.info(r);
		log.info(((ArrayList<Template>) r).get(0).processTemplate());
	}
	
	@Test
	public void testGetTemplates()throws Exception{
		RemoteDataService s = new RemoteDataService();
		String t = RemoteResourceUtil.requestOAuthToken(CloudConfig.getInstance().getServiceEndpoint(CloudConfig.SVC_OAUTH_TOKEN), testUsername, testPassword);
		Object r = s.getTemplates("openshift", null, t);
		log.info(r);
		
		//log.info(((ArrayList<Template>) r).get(47).processTemplate());
	}	
	
	@Test
	public void testGetProcessedTemplate()throws Exception{
		RemoteDataService s = new RemoteDataService();
		String t = RemoteResourceUtil.requestOAuthToken(CloudConfig.getInstance().getServiceEndpoint(CloudConfig.SVC_OAUTH_TOKEN), testUsername, testPassword);
		Object r = s.getTemplates("openshift", "mysql-ephemeral", t);
	    String p = ((ArrayList<Template>) r).get(0).processTemplate();
	    ProcessedTemplate r2 = s.getProcessedTemplate("test2", t, p);
	    log.info("Result");
	    log.info(r2.getJson());
	}	
	
	@Test
	public void testCreateRemoteObject()throws Exception{
		RemoteDataService s = new RemoteDataService();
		String t = RemoteResourceUtil.requestOAuthToken(CloudConfig.getInstance().getServiceEndpoint(CloudConfig.SVC_OAUTH_TOKEN), testUsername, testPassword);
		Object r = s.getTemplates("openshift", "nic-tomcat7-rhel7-build", t);
	    String p = ((ArrayList<Template>) r).get(0).processTemplate();
	    ProcessedTemplate r2 = s.getProcessedTemplate("test2", t, p);
	    for(PostObject po : r2.getObjects()){
	    	s.createRemoteObject("test2", t, po.getKind(), po.getPayload());
	    }
	}	
	
	@Test
	public void testQueryRemoteObject()throws Exception{
		RemoteDataService s = new RemoteDataService();
		String t = RemoteResourceUtil.requestOAuthToken(CloudConfig.getInstance().getServiceEndpoint(CloudConfig.SVC_OAUTH_TOKEN), testUsername, testPassword);
		Object r = s.queryRemoteObject("test2", t, "deploymentconfig");
		List<DataObject> l = DataObjectList.processJSONString(r.toString(), new DeploymentConfig());
	    log.info(r);
	    r = s.queryRemoteObject("test2", t, "service");
	    log.info(r);
	    r = s.queryRemoteObject("test2", t, "buildconfig");
	    log.info(r);
	}	
	
	@Test
	public void testGetLogOutputByPod()throws Exception{
		RemoteDataService s = new RemoteDataService();
		String t = RemoteResourceUtil.requestOAuthToken(CloudConfig.getInstance().getServiceEndpoint(CloudConfig.SVC_OAUTH_TOKEN), testUsername, testPassword);
		Object r = s.getLogOutputByPod("mobilebank", "mobilebank-2-build",t);
		log.info(r);
		
	}
	
	
}
