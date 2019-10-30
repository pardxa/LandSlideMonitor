package org.landslide.data.business;

import java.util.List;
import java.util.Map;

public interface IRealMonitorData {
	public List<RealMonitorData> queryRealMonitorData(Map<String,String> params);
	public List<RealMonitorDataGrid> queryRealMonitorDataGrid(Map<String,String> params);
	public int queryRealMonitorDataGridTotal(Map<String,String> params);
	public void addRealMonitorData(RealMonitorData realMonitorData);
}
