package org.landslide.data.system;

import java.util.List;
import java.util.Map;

public interface ISysLog {
	public void addUserLog(UserLog userLog);
	public List<UserLog> queryUserLog(Map<String,String> params);
	public int queryAllUserLog(Map<String,String> params);
}
