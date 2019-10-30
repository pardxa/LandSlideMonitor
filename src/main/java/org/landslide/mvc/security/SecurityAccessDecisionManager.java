package org.landslide.mvc.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class SecurityAccessDecisionManager implements AccessDecisionManager {

	@Override
	public void decide(Authentication auth, Object arg1,
			Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException {
		if(configAttributes!=null){
			Iterator<ConfigAttribute> caItor=configAttributes.iterator();
			while(caItor.hasNext()){
				//资源要求的Role
				ConfigAttribute caAttribute=caItor.next();
				String roleRequired=((SecurityConfig)caAttribute).getAttribute();
				//用户实际的Role
				for(GrantedAuthority ga:auth.getAuthorities()){
					//第一次拦截，未登录的情况下，用户实际的Role为"ROLE_ANONYMOUS"，直接throw new AccessDeniedException
					//转至登陆页
					if(ga.getAuthority().equals(roleRequired)){
						return;
					}
				}
			}
		}
		throw new AccessDeniedException("没有权限");
	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
