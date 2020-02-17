package com.situ.scrm.address.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.address.dao.TheAddressDao;
import com.situ.scrm.address.domain.TheAddress;
import com.situ.scrm.address.service.TheAddressService;

@Service
public class TheAddressServiceImpl implements Serializable, TheAddressService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private TheAddressDao theAddressDao;
	@Override
	public List<TheAddress> findAllProvince() {
		return theAddressDao.findAllProvince();
	}
	@Override
	public List<TheAddress> findBeiJing() {
		return theAddressDao.findArea(11L);
	}
	@Override
	public List<TheAddress> findBeiJingArea() {
		return theAddressDao.findArea(1101L);
	}
	@Override
	public List<TheAddress> findCity(Long provinceCode) {
		return theAddressDao.findArea(provinceCode);
	}

}
