package com.iconcloud.core.view.controller;

import java.io.IOException;
import java.net.ConnectException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iconcloud.core.config.CloudConfig;
import com.iconcloud.core.model.DataObject;
import com.iconcloud.core.model.DataObjectList;
import com.iconcloud.core.model.DeploymentConfig;
import com.iconcloud.core.model.PostObject;
import com.iconcloud.core.model.ProcessedTemplate;
import com.iconcloud.core.model.Project;
import com.iconcloud.core.model.Template;
import com.iconcloud.core.service.RemoteDataService;
import com.iconcloud.core.util.RemoteResourceUtil;
import com.iconcloud.core.view.model.CreateApplication;
import com.iconcloud.core.view.model.CreateApplicationDetail;
import com.iconcloud.core.view.model.ProjectDetail;
import com.iconcloud.core.view.model.UserHome;

@Controller
public class GeneralController {
	private static Logger log = Logger.getLogger(GeneralController.class);

	@RequestMapping("/projects.page")
	public ModelAndView projects() throws Exception {
		String message = "Hello World, Spring 3.0!";

		return new ModelAndView("projects", "message", message);
	}

	@RequestMapping("/userHome.page")
	public ModelAndView userHome(HttpSession session) throws Exception {
		RemoteDataService ds = new RemoteDataService();
		ArrayList<Project> projects = ds.getProjects((String) session
				.getAttribute(CloudConfig.SESSION_OSE_TOKEN_KEYNAME));
		UserHome model = new UserHome();
		model.setProjects(projects);
		return new ModelAndView("userHome", "model", model);
	}

	@RequestMapping("/projectDetail.page/{projectName}")
	public ModelAndView projectDetail(@PathVariable(value = "projectName") String projectName, HttpSession session)
			throws Exception {
		RemoteDataService ds = new RemoteDataService();
		String token = (String) session.getAttribute(CloudConfig.SESSION_OSE_TOKEN_KEYNAME);
		List<DataObject> dc = DataObjectList.processJSONString(ds.queryRemoteObject(projectName, token, "deploymentconfig"), new DeploymentConfig());
        ProjectDetail model = new ProjectDetail();
        model.setDeploymentConfigs(dc);
        model.setProjectName(projectName);
		return new ModelAndView("projectDetail", "model", model);
	}
	
	@RequestMapping("/{projectName}/createApplication.page/")
	public ModelAndView createApplication(@PathVariable(value = "projectName") String projectName, HttpSession session)
			throws Exception {
		RemoteDataService ds = new RemoteDataService();
		ArrayList<Template> templates = ds.getTemplates("openshift", null,
				(String) session.getAttribute(CloudConfig.SESSION_OSE_TOKEN_KEYNAME));
        CreateApplication model = new CreateApplication();
        model.setTemplates(templates);
        model.setProjectName(projectName);
		return new ModelAndView("createApplication", "model", model);
	}
	
	@RequestMapping("/{projectName}/createApplicationDetail.page/{templateName}")
	public ModelAndView createApplicationDetail(@PathVariable(value = "projectName") String projectName,@PathVariable(value = "templateName") String templateName, HttpSession session)
			throws Exception {
		RemoteDataService ds = new RemoteDataService();
		ArrayList<Template> templates = ds.getTemplates("openshift", templateName,
				(String) session.getAttribute(CloudConfig.SESSION_OSE_TOKEN_KEYNAME));
        CreateApplicationDetail model = new CreateApplicationDetail();
        model.setTemplate(templates == null ? null : templates.get(0));
        model.setProjectName(projectName);
		return new ModelAndView("createApplicationDetail", "model", model);
	}
	
	@RequestMapping("/{projectName}/createApplication.action/{templateName}")
	public String createApplicationAction(@PathVariable(value = "projectName") String projectName,@PathVariable(value = "templateName") String templateName, HttpSession session, RedirectAttributes attr)
			throws Exception {
		String token = (String) session.getAttribute(CloudConfig.SESSION_OSE_TOKEN_KEYNAME);
		RemoteDataService ds = new RemoteDataService();
		ArrayList<Template> templates = ds.getTemplates("openshift", templateName,
				(String) session.getAttribute(CloudConfig.SESSION_OSE_TOKEN_KEYNAME));
		
		Template t = templates == null ? null : templates.get(0);
		
		if(t != null){
			//update parameters value
			ProcessedTemplate pt = ds.getProcessedTemplate(projectName, token, t.getJson());
			for(PostObject po : pt.getObjects()){
		    	ds.createRemoteObject(projectName, token, po.getKind(), po.getPayload());
		    }
		}
		
		return "redirect:/projectDetail.page/" + projectName;
	}

	@RequestMapping("/login.action")
	public String login(HttpServletResponse response, HttpSession session, RedirectAttributes attr, String uname,
			String upwd) {

		if (uname == null || upwd == null) {
			attr.addFlashAttribute("errorMessage", "请输入用户名及密码");
			return "redirect:login.page";
		}

		String token = null;
		try {
			String authUrl = CloudConfig.getInstance().getServiceEndpoint(CloudConfig.SVC_OAUTH_TOKEN);
			token = RemoteResourceUtil.requestOAuthToken(authUrl, uname, upwd);
		} catch (ConnectException e) {
			e.printStackTrace();
			log.error(MessageFormat.format("无法连接后台系统。用户 [{0}] 登录失败", uname));
			session.setAttribute(CloudConfig.SESSION_AUTHENTICATION_KEYNAME, null);
			attr.addFlashAttribute("errorMessage", "后台服务异常，请稍后再试");
			return "redirect:login.page";
		} catch (Exception e) {
			e.printStackTrace();
			log.error(MessageFormat.format("系统异常。用户 [{0}] 登录失败", uname));
			session.setAttribute(CloudConfig.SESSION_AUTHENTICATION_KEYNAME, null);
			attr.addFlashAttribute("errorMessage", "后台服务异常，请稍后再试");
			return "redirect:login.page";
		}

		if (token == null) {
			log.info(MessageFormat.format("User [{0}] login attempt failed", uname));
			session.setAttribute(CloudConfig.SESSION_AUTHENTICATION_KEYNAME, null);
			attr.addFlashAttribute("errorMessage", "用户名或密码错误，请重试");
			return "redirect:login.page";
		} else {
			log.info(MessageFormat.format("User [{0}] loginned successfully", uname));
			session.setAttribute(CloudConfig.SESSION_USER_NAME, uname);
			session.setAttribute(CloudConfig.SESSION_OSE_TOKEN_KEYNAME, token);
			session.setAttribute(CloudConfig.SESSION_AUTHENTICATION_KEYNAME, CloudConfig.SESSION_AUTHENTICATED);
			return "redirect:userHome.page";
		}

	}

	@RequestMapping("/logout.action")
	public void logout(HttpServletResponse response, HttpSession session) {
		String uname = (String) session.getAttribute(CloudConfig.SESSION_USER_NAME);

		// Clean up
		session.setAttribute(CloudConfig.SESSION_OSE_TOKEN_KEYNAME, null);
		session.setAttribute(CloudConfig.SESSION_AUTHENTICATION_KEYNAME, null);
		session.setAttribute(CloudConfig.SESSION_USER_NAME, null);

		log.info(MessageFormat.format("User [{0}] logout successfully", uname));

		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/login.page")
	public void loginPage() {

	}
}