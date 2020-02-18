/**
 * @Company:中享思途   
 * @Title:SysCountServiceImpl.java 
 * @Author:wxinpeng   
 * @Date:2020年2月8日 下午5:36:42     
 */
package com.situ.scrm.sys.syscount.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.sys.syscount.dao.SysCountDao;
import com.situ.scrm.sys.syscount.domain.SysCount;
import com.situ.scrm.sys.syscount.service.SysCountService;

/** 
 * @ClassName:SysCountServiceImpl 
 * @Description:(SysCountServiceImpl)  
 */
@Service
public class SysCountServiceImpl implements SysCountService {
	@Autowired
	private SysCountDao sysCountDao;
	/**
	 * @Title: initSysCountData 
	 * @Description:(初始化数据)
	 */
	@Override
	@PostConstruct
	public void initSysCountData() {
		//尝试查询所有的记录
		List<SysCount> sysCountList = sysCountDao.find();
		//判断如果没有数据，则写入一条数据
		if (sysCountList == null || sysCountList.isEmpty()) {
			SysCount sysCount = new SysCount();
			sysCount.setActiveFlag(1);
			sysCount.setIndex1(0);
			sysCount.setIndex2(0);
			sysCount.setIndex3(0);
			sysCount.setIndex4(0);
			sysCount.setIndex5(0);
			sysCount.setCreateBy("SYS");
			sysCount.setCreateDate(new Date());
			sysCountDao.save(sysCount);
		}
	}

}
