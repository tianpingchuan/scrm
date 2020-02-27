package com.situ.scrm.sys.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.situ.scrm.commons.dao.BaseDao;
import com.situ.scrm.sys.user.domain.User;

@Repository
public interface UserDao extends BaseDao<User> {
	/**
	 * @Title: getUserByCode 
	 * @Description:(根据UserCode查询实例)
	 * @param userCode
	 * @return
	 */
	User getUserByCode(String userCode);

	/** 
	 * @Title: findAllUserByKind 
	 * @Description:(根据级别查找用户)
	 * @param userKind
	 * @return  
	 */ 
	List<User> findAllUserByKind(Long userKind);

	/** 
	 * @Title: login 
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @param user
	 * @return  
	 */ 
	User login(User user);

	/** 
	 * @Title: findUserByCodeAndId 
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @param userCode
	 * @param rowId
	 * @return  
	 */ 
	User findUserByCodeAndId(String userCode, Long rowId);
}
