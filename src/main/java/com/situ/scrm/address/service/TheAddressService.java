package com.situ.scrm.address.service;

import java.util.List;

import com.situ.scrm.address.domain.TheAddress;

public interface TheAddressService {
	
	/**
	 * 搜索所有的省份
	 * @return
	 */
	List<TheAddress> findAllProvince();

	/**
	 * 查找北京省下的市
	 * @return
	 */
	List<TheAddress> findBeiJing();

	/**
	 * 查找北京的市辖区
	 * @return
	 */
	List<TheAddress> findBeiJingArea();

	/**
	 * 根据parentCode查找地区
	 * @param provinceCode
	 * @return
	 */
	List<TheAddress> findCity(Long provinceCode);

}
