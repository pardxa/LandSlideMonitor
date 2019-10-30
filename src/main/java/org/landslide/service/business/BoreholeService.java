package org.landslide.service.business;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.landslide.data.business.Borehole;
import org.landslide.data.business.BoreholeNetwork;
import org.landslide.data.business.IBoreholeDao;
import org.landslide.service.system.SystemPremaryKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoreholeService {
	@Autowired
	private IBoreholeDao boreholeDao;
	@Autowired
	private SystemPremaryKeyService pkService;
	/**
	 * 分页显示
	 * @param page
	 * @param rows
	 * @return
	 */
	public String queryBorehole(
			int page,//页号，以1开始
			int rows, //每页多少行
			int nkId//监测网ID
			){
		ObjectMapper om=new ObjectMapper();
		ObjectNode ont=om.createObjectNode();
		int total=boreholeDao.queryBoreholeTotal();
		ont.put("total", total);
		Map<String,Integer> params=new HashMap<String,Integer>();
		params.put("rows", rows);
		int offset=(page-1)*rows;
		params.put("offset", offset);
		params.put("nkId", nkId);
		List<Borehole> boreholes=boreholeDao.queryBorehole(params);
		ArrayNode networkArray=om.valueToTree(boreholes);		
		ont.put("rows", networkArray);
		return ont.toString();
	}
	public List<Borehole> queryBoreholes(
			int nkId//监测网ID
			){
		Map<String,Integer> params=new HashMap<String,Integer>();
		params.put("nkId", nkId);
		List<Borehole> lst = boreholeDao.queryBoreholes(params);
		for(Borehole bh:lst){
			bh.transformBhPhoto();
		}
		return lst;
	}
	public void addBorehole(
			//int bhId,
			int nkId,
			String bhName,
			String dvCode,
			String dvName,
			String dvSimCode,
			double bhIntCoordDe,
			double bhIntCoordDn,
			double bhIntCoordDu,
			String bhContact,
			String bhTel,
			String bhNote,
			double bhLg,
			double bhLt,
			int bhDisplaceRange
			)throws Exception{
		Borehole borehole=new Borehole();
		int bhId=pkService.getPrimaryKey("BOREHOLE", "BH_ID");
		borehole.setBhId(bhId);
		borehole.setNkId(nkId);
		borehole.setBhName(bhName);
		borehole.setDvCode(dvCode);
		borehole.setDvName(dvName);
		borehole.setDvSimCode(dvSimCode);
		borehole.setBhIntCoordDe(bhIntCoordDe);
		borehole.setBhIntCoordDn(bhIntCoordDn);
		borehole.setBhIntCoordDu(bhIntCoordDu);
		borehole.setBhContact(bhContact);
		borehole.setBhTel(bhTel);
		borehole.setBhNote(bhNote);
		borehole.setBhLg(bhLg);
		borehole.setBhLt(bhLt);
		borehole.setBhDisplaceRange(bhDisplaceRange);
		boreholeDao.addBorehole(borehole);
	}
	public void updateBorehole(
			int bhId,
			//int nkId,
			String bhName,
			String dvCode,
			String dvName,
			String dvSimCode,
			double bhIntCoordDe,
			double bhIntCoordDn,
			double bhIntCoordDu,
			String bhContact,
			String bhTel,
			String bhNote,
			double bhLg,
			double bhLt,
			int bhDisplaceRange			
			)throws Exception{
		Borehole borehole=new Borehole();
		borehole.setBhId(bhId);
		//borehole.setNkId(nkId);
		borehole.setBhName(bhName);
		borehole.setDvCode(dvCode);
		borehole.setDvName(dvName);
		borehole.setDvSimCode(dvSimCode);
		borehole.setBhIntCoordDe(bhIntCoordDe);
		borehole.setBhIntCoordDn(bhIntCoordDn);
		borehole.setBhIntCoordDu(bhIntCoordDu);
		borehole.setBhContact(bhContact);
		borehole.setBhTel(bhTel);
		borehole.setBhNote(bhNote);
		borehole.setBhLg(bhLg);
		borehole.setBhLt(bhLt);
		borehole.setBhDisplaceRange(bhDisplaceRange);
		boreholeDao.updateBorehole(borehole);
	}
	public void deleteBorehole(int bhId){
		Borehole borehole=new Borehole();
		borehole.setBhId(bhId);
		boreholeDao.deleteBorehole(borehole);
	}
	public Borehole getBorehole(int bhId){
		Map<String,Integer> param=new HashMap<String,Integer>();
		param.put("bhId", bhId);
		return boreholeDao.getBorehole(param);
	}
	public void uploadBhPhoto(int bhId,MultipartFile bhPhoto,int bhPhotoHeight,int bhPhotoWidth) throws Exception{
		byte[] bytes=bhPhoto.getBytes();
		Borehole borehole=new Borehole();
		borehole.setBhId(bhId);
		borehole.setBhPhoto(bytes);
		borehole.setBhPhotoHeight(bhPhotoHeight);
		borehole.setBhPhotoWidth(bhPhotoWidth);
		boreholeDao.uploadBhPhoto(borehole);
	}
	public  String queryBoreholeNetwork(
			int page,//页号，以1开始
			int rows, //每页多少行
			int bhId,
			int nkId//监测网ID
			){
		ObjectMapper om=new ObjectMapper();
		ObjectNode ont=om.createObjectNode();
		Map<String,Integer> params=new HashMap<String,Integer>();
		params.put("rows", rows);
		int offset=(page-1)*rows;
		params.put("offset", offset);
		params.put("bhId", bhId);
		params.put("nkId", nkId);
		int total=boreholeDao.queryBoreholeTotal();
		ont.put("total", total);
		List<BoreholeNetwork> boreholes=boreholeDao.queryBoreholeNetwork(params);
		ArrayNode networkArray=om.valueToTree(boreholes);		
		ont.put("rows", networkArray);
		return ont.toString();		
	}
}
