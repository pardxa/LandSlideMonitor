package org.landslide.mvc.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.landslide.data.security.IResourceDao;
import org.landslide.data.security.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


public class SecurityMetaDatasource implements
		FilterInvocationSecurityMetadataSource {
	private static final Logger logger = LoggerFactory.getLogger(SecurityMetaDatasource.class);

	private RequestMatcher requestMatcher;
	private Map<String,List<ConfigAttribute>> resMap;
	/**
	 * 构造函数，将读取的 资源--角色 存入 Map('资源',List<角色>)
	 */
	public SecurityMetaDatasource(IResourceDao resourceDao){
		//
		resMap=new HashMap<String,List<ConfigAttribute>>();
		//
		List<Resource> resource=resourceDao.queryResource();
		Set<String> url=new HashSet<String>();
		Iterator<Resource> ir= resource.iterator();
		while(ir.hasNext()){
			url.add(ir.next().getResourceUrl());
		}
		Iterator<String> ur=url.iterator();
		while(ur.hasNext()){
			String key=ur.next();
			List<ConfigAttribute> roles=new ArrayList<ConfigAttribute>();
			Iterator<Resource> ir2=resource.iterator();
			while(ir2.hasNext()){
				Resource res=ir2.next();
				if(key.equals(res.getResourceUrl())){
					roles.add(new SecurityConfig(res.getRoleName()));
				}
			}
			resMap.put(key, roles);
		}
	}
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 从FilterInvocation中传入的HttpRequest找到具有此权限的角色列表
	 * @param Object obj FilterInvocation
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object obj)
			throws IllegalArgumentException {
		FilterInvocation finv=(FilterInvocation)obj;
		Iterator<String> keys=resMap.keySet().iterator();
		while(keys.hasNext()){
			String key=keys.next();
			//构建匹配器，指定匹配方式
			requestMatcher=new AntPathRequestMatcher(key+"*");			
			logger.debug("====>key from Map<String,List<ConfigAttribute>> :"+key);
			logger.debug("====>HttpRequest.getServletPath() is :"+finv.getHttpRequest().getServletPath());
			logger.debug("====>HttpRequest.getPathInfo() is :"+finv.getHttpRequest().getPathInfo());
			if(requestMatcher.matches(finv.getHttpRequest())){
				return resMap.get(key);
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
