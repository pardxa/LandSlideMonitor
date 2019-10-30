package org.landslide.data.business;

import java.util.List;
import java.util.Map;

public interface INetworkDao {
	public List<Network> queryNetwork(Map<String,Integer> params);
	public int queryNetworkTotal();
	public void addNetwork(Network network);
	public void updateNetwork(Network network);
	public void deleteNetwork(Network network);
	public Network getNetwork(Map<String,Integer> param);
	public void updateNkStatus(Network network);
}
