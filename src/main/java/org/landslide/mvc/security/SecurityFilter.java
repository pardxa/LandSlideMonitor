package org.landslide.mvc.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

public class SecurityFilter extends AbstractSecurityInterceptor implements
		Filter {
	private SecurityMetaDatasource metaDatasource;
	private String errorPage;
	/**
	 * FilterInvocation 保证request,response都是HttpRequest,HttpResponse,和FilterChain都不是NULL
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		FilterInvocation inv=new FilterInvocation(request,response,chain);
		InterceptorStatusToken token=null;
		try{
			token=super.beforeInvocation(inv);
			inv.getChain().doFilter(inv.getRequest(), inv.getResponse());
		}catch(java.lang.IllegalArgumentException illegalArguException){
			//
			request.setAttribute("java.lang.IllegalArgumentException", illegalArguException);//存储业务异常信息类 
			request.getRequestDispatcher("/login.do").forward(request, response);//跳转到信息提示页面！！  		
		}finally{
			super.afterInvocation(token, null);
		}	
	}

	@Override
	public Class<?> getSecureObjectClass() {
		// TODO Auto-generated method stub
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		// TODO Auto-generated method stub
		return metaDatasource;
	}
	/////////----
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public SecurityMetaDatasource getMetaDatasource() {
		return metaDatasource;
	}

	public void setMetaDatasource(SecurityMetaDatasource metaDatasource) {
		this.metaDatasource = metaDatasource;
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
}
