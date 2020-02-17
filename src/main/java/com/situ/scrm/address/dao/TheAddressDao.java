package com.situ.scrm.address.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.situ.scrm.address.domain.TheAddress;

@Repository
public interface TheAddressDao {

	/**
	 * 搜索所有的省份
	 * @return
	 */
	List<TheAddress> findAllProvince();

	/**
	 * 根据传进的Long型的数据查询省份下的地区
	 * @param parentCode
	 * @return
	 */
	List<TheAddress> findArea(Long parentCode);

}
