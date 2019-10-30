package org.landslide.service.system;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.landslide.data.security.IUserDao;
import org.landslide.data.system.ISysLog;
import org.landslide.data.system.UserLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class SystemLoginSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired
	private ISysLog iSyslog;
	@Autowired
	private IUserDao iUserDao;
	private static final Logger logger = LoggerFactory.getLogger(SystemLoginSuccessHandler.class);
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		//登录成功记录下来
		org.springframework.security.core.userdetails.User user=(org.springframework.security.core.userdetails.User)authentication.getPrincipal();
		org.landslide.data.security.User currentUser=iUserDao.getUserByName(user.getUsername());
		int userId=currentUser.getUserId();
		UserLog userLog=new UserLog();
		userLog.setUserId(userId);
		userLog.setOperType(1);
		userLog.setOperDetail("登录");
		iSyslog.addUserLog(userLog);
		//
		HttpSession session=request.getSession();
		session.setAttribute("user", currentUser);
		// TODO Auto-generated method stub
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
