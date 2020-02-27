/**
 * @Company:中享思途   
 * @Title:Client.java 
 * @Author:Administrator   
 * @Date:2020年2月25日 上午10:44:03     
 */
package com.situ.scrm.bus.client.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.situ.scrm.commons.domain.BaseClass;

/**
 * @ClassName:Client
 * @Description:(客户类)
 */
@Alias("Client")
public class Client extends BaseClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private String clientCode;// 客户编号
	private String clientName;// 客户名称
	private String clientPhone;// 手机号码
	private String linkMan;// 联系人
	private String qqNumber;// QQ
	private String emailNumber;// 电子邮件
	private Long proviceCode;// 省code
	private Long cityCode;// 市code
	private Long areaCode;// 区code
	private String trueAddress;// 详细地址
	private Long clientType;// 客户类型
	private Integer clientKind;// 客户级别
	private Long clientSource;// 客户来源
	private Long toIndustry;// 所属行业
	private String publicDate;// 公海天数
	private Integer ifPublic;// 是否公海 ：1：公海客户；0：非公海客户
	private String accessoryBag;// 附件
	private String otherComment;// 备注

	//private MultipartFile uploadFile;// 附件类型

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public String getEmailNumber() {
		return emailNumber;
	}

	public void setEmailNumber(String emailNumber) {
		this.emailNumber = emailNumber;
	}

	public Long getProviceCode() {
		return proviceCode;
	}

	public void setProviceCode(Long proviceCode) {
		this.proviceCode = proviceCode;
	}

	public Long getCityCode() {
		return cityCode;
	}

	public void setCityCode(Long cityCode) {
		this.cityCode = cityCode;
	}

	public Long getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Long areaCode) {
		this.areaCode = areaCode;
	}

	public String getTrueAddress() {
		return trueAddress;
	}

	public void setTrueAddress(String trueAddress) {
		this.trueAddress = trueAddress;
	}

	public Long getClientType() {
		return clientType;
	}

	public void setClientType(Long clientType) {
		this.clientType = clientType;
	}

	public Integer getClientKind() {
		return clientKind;
	}

	public void setClientKind(Integer clientKind) {
		this.clientKind = clientKind;
	}

	public Long getClientSource() {
		return clientSource;
	}

	public void setClientSource(Long clientSource) {
		this.clientSource = clientSource;
	}

	public Long getToIndustry() {
		return toIndustry;
	}

	public void setToIndustry(Long toIndustry) {
		this.toIndustry = toIndustry;
	}

	public String getPublicDate() {
		return publicDate;
	}

	public void setPublicDate(String publicDate) {
		this.publicDate = publicDate;
	}

	public Integer getIfPublic() {
		return ifPublic;
	}

	public void setIfPublic(Integer ifPublic) {
		this.ifPublic = ifPublic;
	}

	public String getAccessoryBag() {
		return accessoryBag;
	}

	public void setAccessoryBag(String accessoryBag) {
		this.accessoryBag = accessoryBag;
	}

	public String getOtherComment() {
		return otherComment;
	}

	public void setOtherComment(String otherComment) {
		this.otherComment = otherComment;
	}

//	public MultipartFile getUploadFile() {
//		return uploadFile;
//	}
//
//	public void setUploadFile(MultipartFile uploadFile) {
//		this.uploadFile = uploadFile;
//	}

	/**
	 * @Title: toString
	 * @Description:(这里用一句话描述这个方法的作用)
	 * @return
	 */
	@Override
	public String toString() {
		return "Client [clientCode=" + clientCode + ", clientName=" + clientName + ", clientPhone=" + clientPhone
				+ ", linkMan=" + linkMan + ", qqNumber=" + qqNumber + ", emailNumber=" + emailNumber + ", proviceCode="
				+ proviceCode + ", cityCode=" + cityCode + ", areaCode=" + areaCode + ", trueAddress=" + trueAddress
				+ ", clientType=" + clientType + ", clientKind=" + clientKind + ", clientSource=" + clientSource
				+ ", toIndustry=" + toIndustry + ", publicDate=" + publicDate + ", ifPublic=" + ifPublic
				+ ", accessoryBag=" + accessoryBag + ", otherComment=" + otherComment 
				+ "]";
	}

}
