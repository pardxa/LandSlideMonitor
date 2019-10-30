package org.landslide.data.business;

import java.util.List;
import java.util.Map;

public interface IBoreholeDao {
	public List<Borehole> queryBorehole(Map<String,Integer> params);
	public int queryBoreholeTotal();
	public List<Borehole> queryBoreholes(Map<String,Integer> params);
	public void addBorehole(Borehole borehole);
	public void updateBorehole(Borehole borehole);
	public void deleteBorehole(Borehole borehole);
	public Borehole getBorehole(Map<String,Integer> param);
	public void uploadBhPhoto(Borehole borehole);
	public List<BoreholeNetwork> queryBoreholeNetwork(Map<String,Integer> params);
}
