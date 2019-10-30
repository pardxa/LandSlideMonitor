package org.landslide.service.system;

import org.landslide.data.system.ISysConfig;
import org.landslide.data.system.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysConfigService {
	@Autowired
	private ISysConfig sysConfigDao;
	public SysConfig querySysConfig(){
		SysConfig conf=sysConfigDao.querySysConfig();
		if(conf==null){
			conf=new SysConfig();
			conf.setSysConfigId(1);
		}
		return conf;
	}
	public void updateSysConfig(long refreshFrequency,long realQueryStart,
			int bhSitePhotoH,
			int bhSitePhotoW)throws Exception{
		SysConfig sysConfig=new SysConfig();
		sysConfig.setSysConfigId(1);
		sysConfig.setRefreshFrequency(refreshFrequency);
		sysConfig.setRealQueryStart(realQueryStart);
		sysConfig.setBhSitePhotoH(bhSitePhotoH);
		sysConfig.setBhSitePhotoW(bhSitePhotoW);
		//
		SysConfig conf=sysConfigDao.querySysConfig();
		if(conf==null){
			sysConfigDao.addSysConfig(sysConfig);
		}else{
			sysConfigDao.updateSysConfig(sysConfig);
		}
		
	}
}
