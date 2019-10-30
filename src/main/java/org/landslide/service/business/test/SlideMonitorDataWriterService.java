package org.landslide.service.business.test;

import java.util.Random;

import org.landslide.data.business.IRealMonitorData;
import org.landslide.data.business.RealMonitorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
/**
 * 产生实时测试数据
 * @author Administrator
 *
 */
@Configuration
@EnableAsync
@EnableScheduling
public class SlideMonitorDataWriterService {
	@Autowired
	private IRealMonitorData iRealMonitorData;
	@Scheduled(cron = "0/10 * * * * ?")
	public void exeJob(){
		RealMonitorData d1=new RealMonitorData();
		d1.setDvSimCode("123458789");
		d1.setClCode("1");
		d1.setMdDisplaceAlert(getRandomDouble(1,2));
		iRealMonitorData.addRealMonitorData(d1);
		//
		RealMonitorData d2=new RealMonitorData();
		d2.setDvSimCode("123458789");
		d2.setClCode("2");
		d2.setMdDisplaceAlert(getRandomDouble(2,3));
		iRealMonitorData.addRealMonitorData(d2);
		//
		RealMonitorData d3=new RealMonitorData();
		d3.setDvSimCode("123458789");
		d3.setClCode("3");
		d3.setMdDisplaceAlert(getRandomDouble(3,4));
		iRealMonitorData.addRealMonitorData(d3);
		//
		RealMonitorData d4=new RealMonitorData();
		d4.setDvSimCode("123458789");
		d4.setClCode("4");
		d4.setMdDisplaceAlert(getRandomDouble(4,5));
		iRealMonitorData.addRealMonitorData(d4);
	}
	private double getRandomDouble(double min,double max){
		Random r=new Random();
		return (min+(max-min)*r.nextDouble());
	}
}
