/**
 * @Company:中享思途   
 * @Title:PageUtils.java 
 * @Author:wxinpeng   
 * @Date:2020年2月8日 下午6:44:35     
 */
package com.situ.scrm.utils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.situ.scrm.commons.domain.Pagination;

/** 
 * @ClassName:DAOUtils 
 * @Description:(DAO的工具类)  
 */
public class DAOUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String GET_PREFIX = "get";//get方法的前缀
	// set方法的前缀
	private static final String SET_PREFIX = "set";
	private static final String REGEX_SET_METHOD = "^set.*";
	// get方法名称的正则表达式
	private static final String REGEX_GET = "^get\\S+$";

	//排除在外的 Method名称集合
	private static List<String> EXCLUDE_METHOD = new ArrayList<String>();
	static {
		EXCLUDE_METHOD.add("setRowId");
		EXCLUDE_METHOD.add("setClass");
		EXCLUDE_METHOD.add("setActiveFlag");
		EXCLUDE_METHOD.add("setCreateBy");
		EXCLUDE_METHOD.add("setCreateDate");
	}

	private DAOUtils() {
	}

	/**
	 * @Title: buildEditData 
	 * @Description:(通过反射机制为EditObject设置数据)
	 * @param editObj 需要修改的对象
	 * @param sourceObj 提供数据的对象
	 * @return
	 */
	public static <T> T buildEditData(T editObj, T sourceObj) {
		if (editObj != null && sourceObj != null) {
			Class<?> sourceClazz = sourceObj.getClass();
			Class<?> editClazz = editObj.getClass();
			Method[] editMethods = editClazz.getMethods();
			for (Method method : editMethods) {
				String methodName = method.getName();
				if (methodName.matches(REGEX_SET_METHOD)) {
					if (EXCLUDE_METHOD.contains(methodName)) {
						continue;
					}
					try {
						//get方法名称
						String getMethodName = GET_PREFIX + methodName.substring(3);
						//get方法对象
						Method sourceMethod = sourceClazz.getMethod(getMethodName);
						method.invoke(editObj, sourceMethod.invoke(sourceObj));
					} catch (NoSuchMethodException e) {
						//e.printStackTrace();
						throw new RuntimeException(e);
					} catch (IllegalArgumentException e) {
						//e.printStackTrace();
						throw new RuntimeException(e);
					} catch (IllegalAccessException e) {
						//e.printStackTrace();
						throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
						//e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		return editObj;
	}

	/**
	 * @Title: buildSearchParam 
	 * @Description:(通过反射机制将类的实例中的""重新赋值为null,方便MyBatis中多条件查询SQL语句的书写)
	 * @param <T>
	 * @param t
	 * @return
	 */
	public static <T> T buildSearchParam(T t) {
		if (t != null) {
			// 得到此对象的Class对象
			Class<?> clazz = t.getClass();
			// 根据Class对象得到类的所有的方法名称
			Method[] methods = clazz.getMethods();
			// Map<方法名称，方法的实例>
			Map<String, Method> methodMap = new HashMap<String, Method>();
			for (Method method : methods) {
				String methodName = method.getName();
				methodMap.put(methodName, method);
			}
			for (String methodName : methodMap.keySet()) {
				// 如果这个方法名称是以get开头
				if (methodName.matches(REGEX_GET)) {
					Method method = methodMap.get(methodName);
					try {
						Object objValue = method.invoke(t);
						if (objValue != null && "".equals(objValue)) {
							// 得到此属性的对应的set方法名称
							String setMethodName = methodName.replace(GET_PREFIX, SET_PREFIX);
							// 根据Sett方法名称从Map中找到这个方法。
							Method methodSet = methodMap.get(setMethodName);
							// 运行这个Set方法，对这个属性重新进行赋值，将这个属性赋值成 null
							methodSet.invoke(t, new Object[] { null });
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return t;
	}

	/**
	 * @Title: buildPagination 
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @param page     页号
	 * @param limit    每页显示的条数
	 * @return
	 */
	public static Pagination buildPagination(Integer page, Integer limit) {
		Integer maxResults = limit;//分页查询的数量
		Integer firstResult = (page - 1) * maxResults;//查询开始的下标;//分页开始的下标
		return new Pagination(firstResult, maxResults);
	}
}
