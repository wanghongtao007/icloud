package com.iconcloud.core.service;

import java.text.MessageFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.iconcloud.core.config.CloudConfig;
import com.iconcloud.core.model.PostObject;
import com.iconcloud.core.model.ProcessedTemplate;
import com.iconcloud.core.model.Project;
import com.iconcloud.core.model.Template;
import com.iconcloud.core.model.TemplateParameter;
import com.iconcloud.core.util.RemoteResourceUtil;
import com.iconcloud.core.util.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RemoteDataService {
	public static Logger log = Logger.getLogger(RemoteDataService.class);

	public ArrayList<Project> getProjects(String token) throws Exception {

		ArrayList<Project> ret = null;

		String url = CloudConfig.getInstance().getServiceEndpoint(CloudConfig.OSE_PROJECTS);
		Object rs = RemoteResourceUtil.getResources(url, token);
		log.debug("Response from server:");
		log.debug(rs);

		if (rs == null) {
			return ret;
		}

		JSONObject json = JSONObject.fromObject(rs);
		JSONArray arr = json.getJSONArray("items");

		if (arr == null) {
			return ret;
		}

		ret = new ArrayList<Project>();
		for (int i = 0; i < arr.size(); i++) {
			JSONObject md = arr.getJSONObject(i).getJSONObject("metadata");
			JSONObject anno = md.getJSONObject("annotations");
			JSONObject status = arr.getJSONObject(i).getJSONObject("status");

			Project p = new Project();
			p.setName(md.getString("name"));
			p.setDispalyName(Util.getJSONString(anno, "openshift.io/display-name"));
			p.setDescription(Util.getJSONString(anno, "openshift.io/description"));
			p.setStatus(status.getString("phase"));
			ret.add(p);
		}

		return ret;
	}

//	public ArrayList<Pod> getPodsByProject(String projectName, String token) throws Exception {
//		ArrayList<Pod> ret = null;
//
//		String url = MessageFormat.format(CloudConfig.getInstance().getServiceEndpoint(CloudConfig.K8S_NAMESPACE_POD),
//				projectName);
//		Object rs = RemoteResourceUtil.getResources(url, token);
//		log.debug("Response from server:");
//		log.debug(rs);
//
//		if (rs == null) {
//			return ret;
//		}
//
//		JSONObject json = null;
//		try {
//			json = JSONObject.fromObject(rs);
//		} catch (Exception e) {
//			json = null;
//		}
//		JSONArray arr = Util.getJSONArray(json, "items");
//
//		if (arr == null) {
//			return ret;
//		}
//
//		ret = new ArrayList<Pod>();
//		for (int i = 0; i < arr.size(); i++) {
//			JSONObject md = arr.getJSONObject(i).getJSONObject("metadata");
//			JSONObject status = arr.getJSONObject(i).getJSONObject("status");
//			JSONObject labels = md.getJSONObject("labels");
//
//			Pod p = new Pod();
//			p.setName(md.getString("name"));
//			p.setNamespace(projectName);
//			p.setStatus(status.getString("phase"));
//			p.setHostIP(Util.getJSONString(status, "hostIP"));
//			p.setPodIP(Util.getJSONString(status, "podIP"));
//			// p.setStartTime(Util.getJSONString(status, "startTime"));
//			p.setIsApplicationPod(labels == null ? false : labels.containsKey("app"));
//			ret.add(p);
//		}
//
//		return ret;
//	}

	public String createRemoteObject(String projectName, String token, String kind, String payload) throws Exception {

		String apiContext = CloudConfig.getInstance().getObjectContextPath().get(kind.toLowerCase());
		String url = MessageFormat.format(CloudConfig.getInstance().getServiceEndpoint(CloudConfig.OSE_CREATE_OBJECT),
				apiContext, projectName, kind.toLowerCase());
		Object rs = RemoteResourceUtil.postResources(url, token, payload);

		if (CloudConfig.debug) {
			log.debug("Response from server:");
			log.debug(rs);
		}

		return rs.toString();
	}

	public String queryRemoteObject(String projectName, String token, String kind, String objectName) throws Exception {

		String apiContext = CloudConfig.getInstance().getObjectContextPath().get(kind.toLowerCase());
		String url = MessageFormat.format(CloudConfig.getInstance().getServiceEndpoint(CloudConfig.OSE_QUERY_OBJECT),
				apiContext, projectName, kind.toLowerCase(),objectName==null?"":"/" + objectName);
		Object rs = RemoteResourceUtil.getResources(url, token);

		if (CloudConfig.debug) {
			log.debug("Response from server:");
			log.debug(rs);
		}

		return rs.toString();
	}
	
	public String queryRemoteObject(String projectName, String token, String kind) throws Exception {
		return queryRemoteObject( projectName,  token,  kind, "");
	}

	public ProcessedTemplate getProcessedTemplate(String projectName, String token, String payload) throws Exception {
		String url = MessageFormat
				.format(CloudConfig.getInstance().getServiceEndpoint(CloudConfig.OSE_PROCESSED_TEMPLATES), projectName);
		Object rs = RemoteResourceUtil.postResources(url, token, payload);
		log.debug("Response from server:");
		log.debug(rs);

		JSONObject json = null;
		try {
			json = JSONObject.fromObject(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (json == null || !json.containsKey("objects")) {
			return null;
		}

		JSONArray objects = json.getJSONArray("objects");
		ProcessedTemplate pt = new ProcessedTemplate();
		pt.setJson(json.toString());

		for (int i = 0; i < objects.size(); i++) {
			JSONObject node = objects.getJSONObject(i);

			PostObject po = new PostObject();
			po.setKind(node.getString("kind"));
			po.setPayload(node.toString());
			pt.getObjects().add(po);
		}

		return pt;
	}

	public ArrayList<Template> getTemplates(String projectName, String templateName, String token) throws Exception {
		ArrayList<Template> ret = null;

		String url = MessageFormat.format(CloudConfig.getInstance().getServiceEndpoint(CloudConfig.OSE_TEMPLATES),
				projectName, templateName == null ? "" : templateName);
		Object rs = RemoteResourceUtil.getResources(url, token);
		log.debug("Response from server:");
		log.debug(rs);

		if (rs == null) {
			return null;
		}

		JSONObject json = null;
		try {
			json = JSONObject.fromObject(rs);
		} catch (Exception e) {
			json = null;
		}

		if (json == null) {
			return null;
		}

		ret = new ArrayList<Template>();
		JSONArray arr = null;

		if (templateName == null) {
			arr = Util.getJSONArray(json, "items");
		} else {
			arr = new JSONArray();
			arr.add(json);
		}

		if (arr == null) {
			return null;
		}

		for (int i = 0; i < arr.size(); i++) {
			JSONObject node = arr.getJSONObject(i);
			JSONObject md = node.getJSONObject("metadata");
			JSONArray params = Util.getJSONArray(node, "parameters");
			JSONObject annotation = Util.getJSONObject(md, "annotations");

			Template t = new Template();
			t.setName(md.getString("name"));
			t.setDisplayName(md.getString("name"));
			t.setNamespace(projectName);
			t.setDescription(Util.getJSONString(annotation, "description"));
			t.setJson(node.toString());

			if (params != null && !params.isEmpty()) {
				for (int j = 0; j < params.size(); j++) {
					JSONObject pms = params.getJSONObject(j);
					TemplateParameter p = new TemplateParameter();

					p.setName(Util.getJSONString(pms, "name"));
					p.setDescription(Util.getJSONString(pms, "description"));
					p.setValue(Util.getJSONString(pms, "value"));
					p.setGenerate(Util.getJSONString(pms, "generate"));
					p.setFrom(Util.getJSONString(pms, "from"));

					t.getParameters().add(p);
				}
			}

			ret.add(t);
		}

		return ret;
	}

	public String getLogOutputByPod(String namespace, String podName, String token) throws Exception {
		String url = MessageFormat.format(
				CloudConfig.getInstance().getServiceEndpoint(CloudConfig.K8S_NAMESPACE_POD_LOG), namespace, podName);
		String rs = (String) RemoteResourceUtil.getResources(url, token);
		log.debug("Response from server:");
		log.debug(rs);

		return rs;
	}

}
