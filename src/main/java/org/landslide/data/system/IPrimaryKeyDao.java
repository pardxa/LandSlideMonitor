package org.landslide.data.system;

import java.util.Map;

public interface IPrimaryKeyDao {
	public int getPrimaryKey(Map<String,String> params);
	public void updatePrimaryKey(Map<String,String> params);
}
