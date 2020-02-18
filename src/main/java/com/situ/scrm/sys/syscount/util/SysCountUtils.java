/**
 * @Company:中享思途   
 * @Title:SysCountUtils.java 
 * @Author:wxinpeng   
 * @Date:2020年2月8日 下午5:38:59     
 */
package com.situ.scrm.sys.syscount.util;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.situ.scrm.sys.syscount.dao.SysCountDao;
import com.situ.scrm.sys.syscount.domain.SysCount;

/**
 * @ClassName:SysCountUtils
 * @Description:(生成各种编号的工具类)
 */
@Service
public class SysCountUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Format FORMARt_YY_MM_DD = new SimpleDateFormat("yyMMdd");
	private static final Format FOMATER_00 = new DecimalFormat("00");
	private static final Format FOMATER_000 = new DecimalFormat("000");
	private static final String S = "S";// 资源编号的开头
	private static final String D = "D";// 数据字典的开头
	@Autowired
	private SysCountDao sysCountDao;

	// 注入序列表的DAO
	/**
	 * @Title: buildRoleCode yyMMdd01
	 * @Description:(得到角色的编号)
	 * @return
	 */
	@Transactional
	public String buildRoleCode() {
		// 要查询的字段名称 INDEX1
		String columnName = SysCount.INDEX1;
		Calendar calendar = Calendar.getInstance();
		StringBuffer numBuffer = new StringBuffer();
		numBuffer.append(FORMARt_YY_MM_DD.format(calendar.getTime()));
		// 通过序列表的DAO查询出当前序列的数 :比如说当前序列 是 2
		int index = sysCountDao.get(columnName);
		// 将当前的序列 加一 。然后 append到numBuffer 中
		int sequnce = index + 1;
		numBuffer.append(FOMATER_00.format(sequnce));
		// 将这个序列的最新值更新回数据库。
		sysCountDao.updatePlus(columnName, sequnce);
		return numBuffer.toString();
	}

	/**
	 * @Title: buildRescCode
	 * @Description:(得到资源的编号)
	 * @return
	 */
	public String buildRescCode() {
		// 要查询的字段名称 INDEX1
		String columnName = SysCount.INDEX2;
		StringBuffer buffer = new StringBuffer(S);
		// 通过序列表的DAO查询出当前序列的数 :比如说当前序列 是 2
		int index = sysCountDao.get(columnName);
		// 将当前的序列 加一 。然后 append到numBuffer 中
		int sequnce = index + 1;
		buffer.append(FOMATER_000.format(sequnce));
		// 将这个序列的最新值更新回数据库。
		sysCountDao.updatePlus(columnName, sequnce);
		return buffer.toString();
	}
	
	/**
	 * @Title: buildRescCode
	 * @Description:(得到资源的编号)
	 * @return
	 */
	public String buildDdKey() {
		// 要查询的字段名称 INDEX3
		String columnName = SysCount.INDEX3;
		StringBuffer buffer = new StringBuffer(D);
		// 通过序列表的DAO查询出当前序列的数 :比如说当前序列 是 2
		int index = sysCountDao.get(columnName);
		// 将当前的序列 加一 。然后 append到numBuffer 中
		int sequnce = index + 1;
		buffer.append(FOMATER_000.format(sequnce));
		// 将这个序列的最新值更新回数据库。
		sysCountDao.updatePlus(columnName, sequnce);
		return buffer.toString();
	}
}
