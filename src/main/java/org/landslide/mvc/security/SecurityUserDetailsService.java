package org.landslide.mvc.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.landslide.data.security.IRoleDao;
import org.landslide.data.security.IUserDao;
import org.landslide.data.security.Role;
import org.landslide.data.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SecurityUserDetailsService implements UserDetailsService {
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IUserDao userDao;
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		//获取User对象
		User user=userDao.getUserByName(userName);
		if(user==null){
			throw new UsernameNotFoundException(userName);
		}
		//获取User的Role
		List<Role> roles=roleDao.queryRolesByUserId(user.getUserId());
		//
		Collection<GrantedAuthority> authors=new ArrayList<GrantedAuthority>();
		if(roles!=null){
			Iterator<Role> it=roles.iterator();
			while(it.hasNext()){
				Role role=it.next();
				authors.add(new SimpleGrantedAuthority(role.getRoleName()));
			}
		}
		Date currentDate=new Date();
		org.springframework.security.core.userdetails.User usr=
				new org.springframework.security.core.userdetails.User(
						user.getUserName(),
						user.getPassWord(),
						user.isEnabled(),
						(user.getActExpireDate()==null)?true:(currentDate.after(user.getActExpireDate())),
						(user.getCredentialsExpireDate()==null)?true:(currentDate.after(user.getCredentialsExpireDate())),
						user.isActNonLocked(),
						authors);
		return usr;
	}

}
