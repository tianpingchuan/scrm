/**
 * @Company:中享思途   
 * @Title:DdServiceImpl.java 
 * @Author:Administrator   
 * @Date:2020年2月18日 上午10:20:47     
 */
package com.situ.scrm.sys.dd.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.dd.dao.DdDao;
import com.situ.scrm.sys.dd.domain.Dd;
import com.situ.scrm.sys.dd.service.DdService;
import com.situ.scrm.sys.syscount.util.SysCountUtils;

/**
 * @ClassName:DdServiceImpl
 * @Description:(数据字典逻辑层的实现层)
 */
@Service
public class DdServiceImpl implements Serializable, DdService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private DdDao DdDao;
	@Autowired
	private SysCountUtils sysCountUtils;

	/**
	 * @Title: findAllDd
	 * @Description:(查询全部数据字典信息)
	 * @return
	 */
	@Override
	public LayResult findAllDd() {
		List<Dd> allDdList = new ArrayList<Dd>();
		// 将所有的资源信息build成Map<ParentCode,List<Dd>>
		Map<String, List<Dd>> ddMap = buildddMap();
		if (ddMap != null) {
			// 先从map中将一级资源的集合取出。
			List<Dd> ddList = ddMap.get(Dd.DEFAULT_PARENT_KEY);
			// 遍历一级资源
			if (ddList != null) {
				for (Dd dd : ddList) {
					// 判断是否有子数据
					Integer hasChild = dd.getHasChild();
					// 如果有子数据
					if (hasChild == 1) {
						// 通过递归方法(重复调用本身)将所有资源的子数据处理成功。
						callBackChildList(dd, ddMap);
					}
					allDdList.add(dd);
				}
			}
		}
		return new LayResult(200, "ok", allDdList);
	}

	/**
	 * @Title: callBackChildList
	 * @Description:(递归的方式拿到子类数据)
	 * @param parentCode
	 * @param ddMap
	 * @return
	 */
	private void callBackChildList(Dd dd, Map<String, List<Dd>> ddMap) {
		String parentKey = dd.getDdKey();
		List<Dd> childList = ddMap.get(parentKey);
		if (childList != null) {
			for (Dd child : childList) {
				if (child.getHasChild() == 1) {
					callBackChildList(child, ddMap);
				}
			}
			// 将拿到的子数据设置进去。
			dd.setChildren(childList);
		}
	}

	/**
	 * @Title: buildddMap
	 * @Description:(将资源数据转换成MAP格式的数据)
	 * @param ddList
	 * @return Map<parentKey,List
	 *         <Dd>>
	 */
	private Map<String, List<Dd>> buildddMap() {
		// 将系统资源所有的数据都查询出来。
		List<Dd> ddList = DdDao.find();
		Map<String, List<Dd>> ddMap = new HashMap<String, List<Dd>>();
		if (ddList != null) {
			for (Dd dd : ddList) {
				String parentKey = dd.getParentKey();
				List<Dd> list = ddMap.get(parentKey);
				if (list == null) {
					list = new ArrayList<Dd>();
				}
				list.add(dd);
				ddMap.put(parentKey, list);
			}
		}
		return ddMap;
	}

	/**
	 * @Title: saveDd
	 * @Description:(新增数据字典)
	 * @param dd
	 * @return
	 */
	@Override
	@Transactional // 此逻辑牵扯到多次数据库的操作，启用事务处理
	public Long saveDd(Dd dd) {
		// 得到资源编号
		String ddKey = sysCountUtils.buildDdKey();
		dd.setDdKey(ddKey);
		// 根据父类数据处理排序问题。
		String parentKey = dd.getParentKey();
		Integer maxOrder = DdDao.getMaxOrder(parentKey);
		maxOrder = maxOrder == null ? 0 : maxOrder;
		dd.setDdOrder(maxOrder + 1);
		// 如果不是默认的ParentCode，则需要对parent类更新有子类数据信息
		if (!Dd.DEFAULT_PARENT_KEY.equals(parentKey)) {
			DdDao.updateHasChild(parentKey, 1);
		}
		dd.setHasChild(0);
		dd.setActiveFlag(1);
		dd.setCreateBy("sys");
		dd.setCreateDate(new Date());
		DdDao.save(dd);
		return dd.getRowId();
	}

	/** 
	 * @Title: getDdById 
	 * @Description:(根据id查询数据字典)
	 * @param parentId
	 * @return  
	 */  
	@Override
	public Dd getDdById(Long rowId) {
		return DdDao.get(rowId);
	}
	
	/**
	 * @Title: checkDdValue
	 * @Description:(检测字典编码唯一)
	 * @param rescName
	 * @param parentCode
	 * @return
	 */
	@Override
	public Integer checkDdValue(String ddValue, String parentKey) {
		Dd dd = DdDao.getByNameAndParent(ddValue, parentKey);
		return dd == null ? 0 : 1;
	}

	/** 
	 * @Title: doDeleteDd 
	 * @Description:(删除数据字典)
	 * @param rowId
	 * @return  
	 */  
	@Override
	public Integer doDeleteDd(Long rowId) {
		Dd deleteDd = DdDao.get(rowId);
		String parentKey = deleteDd.getParentKey();
//		先删除数据
		DdDao.delete(rowId);
		// 回调删除相关的数据
		handlerDeleteDd(deleteDd);
		// 如果不是默认的父类CODE
		if (!parentKey.equals(Dd.DEFAULT_PARENT_KEY)) {
			// 修改父类的资源是否有子元素
			Integer hasChild = 0;
//			查找该父类字典下是否还有子数据
			List<Dd> childDdList = DdDao.findByParent(parentKey);
			if (childDdList != null && !childDdList.isEmpty()) {
				hasChild = 1;
			}
			DdDao.updateHasChild(parentKey, hasChild);
		}
		return 1;
	}
	
	/**
	 * @Title: handlerDeleteDd
	 * @Description:(回调删除相关的数据)
	 * @param parentResoruce
	 */
	private void handlerDeleteDd(Dd parentDd) {
		String ddKey = parentDd.getDdKey();
		List<Dd> childDdList = DdDao.findByParent(ddKey);
		if (childDdList != null) {
			for (Dd dd : childDdList) {
				DdDao.delete(dd.getRowId());
				if (dd.getHasChild() == 1) {
					handlerDeleteDd(dd);
				}
			}
		}
	}

	/** 
	 * @Title: updateDd 
	 * @Description:(执行修改)
	 * @param dd
	 * @return  
	 */  
	@Override
	public Long updateDd(Dd dd) {
		dd.setUpdateBy("sys");
		dd.setUpdateDate(new Date());
		DdDao.update(dd);
		return dd.getRowId();
	}

	/** 
	 * @Title: getAllDdById 
	 * @Description:(根据id查询用户字典实例的全部信息)
	 * @param rowId
	 * @return  
	 */  
	@Override
	public Dd getAllDdById(Long rowId) {
		Dd dd = DdDao.get(rowId);
		String parentKey = dd.getParentKey();
		if (!parentKey.equals(Dd.DEFAULT_PARENT_KEY)) {
			Dd parentDd = DdDao.getByKey(parentKey);
			dd.setParentValue(parentDd.getDdDescribe());
		}
		return dd;
	}

	/** 
	 * @Title: findAllByParentKey 
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @param parentKey
	 * @return  
	 */  
	@Override
	public List<Dd> findAllByParentKey(String parentKey) {
		return DdDao.findByParent(parentKey);
	}
	
	
}
