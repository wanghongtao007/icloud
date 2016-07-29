package com.iconcloud.core.view.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iconcloud.core.config.CloudConfig;


public class IConCloudInterceptor  extends HandlerInterceptorAdapter{
	private static Logger log = Logger.getLogger(IConCloudInterceptor.class);

	private List<String> excludedUrls;

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	
		String requestUri = request.getRequestURI();
        for (String url : excludedUrls) {
            if (requestUri.endsWith(url)) {
                return true;
            }
        }
        
		String token = (String) request.getSession().getAttribute(CloudConfig.SESSION_OSE_TOKEN_KEYNAME);
		String authenticated = (String) request.getSession().getAttribute(CloudConfig.SESSION_AUTHENTICATION_KEYNAME);
		if (token == null || !CloudConfig.SESSION_AUTHENTICATED.equals(authenticated)) {
			log.info("用户未登录，转跳至登录页面");
			request.getRequestDispatcher("/login.page").forward(request, response);
			return false;
		} else
			return true;
	}

	
}
