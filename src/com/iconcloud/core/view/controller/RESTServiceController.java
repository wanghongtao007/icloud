package com.iconcloud.core.view.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iconcloud.core.config.CloudConfig;
import com.iconcloud.core.model.RESTServiceOutput;
import com.iconcloud.core.service.RemoteDataService;

@Controller
public class RESTServiceController {

	private final String API_PREFIX = "/objects.svc";

	public ModelAndView getObjects(String kind, String projectName, String objectName, HttpSession session)
			throws Exception {
		String token = (String) session.getAttribute(CloudConfig.SESSION_OSE_TOKEN_KEYNAME);
		RemoteDataService ds = new RemoteDataService();
		String result = ds.queryRemoteObject(projectName, token, kind, objectName);
		RESTServiceOutput model = new RESTServiceOutput();
		model.setOutput(result);
		return new ModelAndView("output", "model", model);
	}

	// deploymentConfig
	@RequestMapping(value=API_PREFIX + "/{projectName}/deploymentConfig/{objectName}/", method=RequestMethod.GET)
	public ModelAndView getDeploymentConfigs(@PathVariable(value = "projectName") String projectName,
			@PathVariable(value = "objectName") String objectName, HttpSession session) throws Exception {
		return getObjects("DeploymentConfig", projectName, objectName, session);
	}

	@RequestMapping(value=API_PREFIX + "/{projectName}/deploymentConfig/", method=RequestMethod.GET)
	public ModelAndView getDeploymentConfigs(@PathVariable(value = "projectName") String projectName,
			HttpSession session) throws Exception {
		return getObjects("DeploymentConfig", projectName, null, session);
	}

	// service
	@RequestMapping(value=API_PREFIX + "/{projectName}/service/{objectName}/", method=RequestMethod.GET)
	public ModelAndView getServices(@PathVariable(value = "projectName") String projectName,
			@PathVariable(value = "objectName") String objectName, HttpSession session) throws Exception {
		return getObjects("Service", projectName, objectName, session);
	}

	@RequestMapping(value=API_PREFIX + "/{projectName}/service/", method=RequestMethod.GET)
	public ModelAndView getServices(@PathVariable(value = "projectName") String projectName, HttpSession session)
			throws Exception {
		return getObjects("Service", projectName, null, session);
	}

	// route
	@RequestMapping(value=API_PREFIX + "/{projectName}/route/{objectName}/", method=RequestMethod.GET)
	public ModelAndView getRoutes(@PathVariable(value = "projectName") String projectName,
			@PathVariable(value = "objectName") String objectName, HttpSession session) throws Exception {
		return getObjects("Route", projectName, objectName, session);
	}

	@RequestMapping(value=API_PREFIX + "/{projectName}/route/", method=RequestMethod.GET)
	public ModelAndView getRoutes(@PathVariable(value = "projectName") String projectName, HttpSession session)
			throws Exception {
		return getObjects("Route", projectName, null, session);
	}

	// pod
	@RequestMapping(value=API_PREFIX + "/{projectName}/pod/{objectName}/", method=RequestMethod.GET)
	public ModelAndView getPods(@PathVariable(value = "projectName") String projectName,
			@PathVariable(value = "objectName") String objectName, HttpSession session) throws Exception {
		return getObjects("Pod", projectName, objectName, session);
	}

	@RequestMapping(value=API_PREFIX + "/{projectName}/pod/", method=RequestMethod.GET)
	public ModelAndView getPods(@PathVariable(value = "projectName") String projectName, HttpSession session)
			throws Exception {
		return getObjects("Pod", projectName, null, session);
	}

	// event
	@RequestMapping(value=API_PREFIX + "/{projectName}/event/", method=RequestMethod.GET)
	public ModelAndView getEvents(@PathVariable(value = "projectName") String projectName, HttpSession session)
			throws Exception {
		return getObjects("Event", projectName, null, session);
	}

	// template
	@RequestMapping(value=API_PREFIX + "/{projectName}/template/{objectName}/", method=RequestMethod.GET)
	public ModelAndView getTemplates(@PathVariable(value = "projectName") String projectName,
			@PathVariable(value = "objectName") String objectName, HttpSession session) throws Exception {
		return getObjects("Template", projectName, objectName, session);
	}

	@RequestMapping(value=API_PREFIX + "/{projectName}/template/", method=RequestMethod.GET)
	public ModelAndView getTemplates(@PathVariable(value = "projectName") String projectName, HttpSession session)
			throws Exception {
		return getObjects("Template", projectName, null, session);
	}

	// logOutput
	@RequestMapping(value=API_PREFIX + "/{projectName}/log/{objectName}", method=RequestMethod.GET)
	public ModelAndView logOutputByPod(HttpServletResponse response,
			@PathVariable(value = "projectName") String projectName,
			@PathVariable(value = "objectName") String objectName, HttpSession session) throws Exception {
		RemoteDataService ds = new RemoteDataService();
		String result = ds.getLogOutputByPod(projectName, objectName,
				(String) session.getAttribute(CloudConfig.SESSION_OSE_TOKEN_KEYNAME));
		RESTServiceOutput model = new RESTServiceOutput();
		model.setOutput(result);
		return new ModelAndView("output", "model", model);
	}
}
