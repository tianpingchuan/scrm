/**
 * @Company:中享思途   
 * @Title:SysActionInfoDao.java 
 * @Author:wxinpeng   
 * @Date:2020年2月14日 上午10:27:34     
 */
package com.situ.scrm.sys.sysresource.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.situ.scrm.sys.sysresource.domain.SysActionInfo;

/**
 * @ClassName:SysActionInfoDao
 * @Description:(SysActionInfoDao)
 */
@Repository
public interface SysActionInfoDao {
	/**
	 * @Title: update
	 * @Description:(更新)
	 * @param sysActionInfo
	 */
	void update(SysActionInfo sysActionInfo);

	/**
	 * @Title: save
	 * @Description:(保存)
	 * @param sysActionInfo
	 */
	void save(SysActionInfo sysActionInfo);

	/**
	 * @Title: findByCode
	 * @Description:(根据rescCode查询)
	 * @param rescCode
	 * @return
	 */
	List<SysActionInfo> findByCode(String rescCode);

	/**
	 * @Title: updateFail
	 * @Description:(根据rescCode将数据设置为失效)
	 * @param rescCode
	 */
	void updateFail(String rescCode);

	/**
	 * @Title: delete
	 * @Description:(根据rescCode 删除)
	 * @param rescCode
	 */
	void delete(String rescCode);
}
