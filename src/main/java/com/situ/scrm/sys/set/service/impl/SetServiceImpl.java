/**
 * @Company:中享思途   
 * @Title:SetServiceImpl.java 
 * @Author:Administrator   
 * @Date:2020年2月19日 下午5:08:05     
 */ 
package com.situ.scrm.sys.set.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.sys.set.dao.SetDao;
import com.situ.scrm.sys.set.domain.Set;
import com.situ.scrm.sys.set.service.SetService;

/** 
 * @ClassName:SetServiceImpl 
 * @Description:(系统设置的逻辑层的实现层)  
 */
@Service
public class SetServiceImpl implements Serializable, SetService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SetDao setDao;
	
	/** 
	 * @Title: initSetData 
	 * @Description:(初始化数据)  
	 */
	@Override
	@PostConstruct
	public void initSetData() {
		//尝试查询所有的记录
		List<Set> setList = setDao.find();
		//判断如果没有数据，则写入一条数据
		if (setList == null || setList.isEmpty()) {
			Set set = new Set();
			set.setActiveFlag(1);
			set.setConfig1(null);
			set.setConfig2(null);
			set.setConfig3(null);
			set.setConfig4(null);
			set.setConfig5(null);
			set.setConfig6(null);
			set.setCreateBy("SYS");
			set.setCreateDate(new Date());
			setDao.save(set);
		}
	}

	/** 
	 * @Title: findSet 
	 * @Description:(查找数据)
	 * @return  
	 */  
	@Override
	public Set findSet() {
		List<Set> setList = setDao.find();
		Set set = setList.get(0);
		return set;
	}

	/** 
	 * @Title: updateSet 
	 * @Description:(修改数据)
	 * @param set
	 * @return  
	 */  
	@Override
	public Integer updateSet(Set set) {
		setDao.updatePlus(Set.CONFIG1, set.getConfig1());
		setDao.updatePlus(Set.CONFIG2, set.getConfig2());
		setDao.updatePlus(Set.CONFIG3, set.getConfig3());
		return 1;
	}

}
