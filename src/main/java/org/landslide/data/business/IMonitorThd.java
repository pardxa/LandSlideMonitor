package org.landslide.data.business;

import java.util.List;
import java.util.Map;

public interface IMonitorThd {
	public List<MonitorThd> queryMonitorThd(Map<String,Integer> params);
	public int queryMonitorThdTotal(Map<String,Integer> params);
	public void addMonitorThd(MonitorThd mt);
	public void updateMonitorThd(MonitorThd mt);
	public void deleteMonitorThd(MonitorThd mt);
}
