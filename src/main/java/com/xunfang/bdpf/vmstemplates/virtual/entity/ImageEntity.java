/**  
* @Title: ImageEntity.java
* @Package com.xunfang.cloud.model
* @Description: Image Endity
* Copyright: Copyright (c) 2017 
* Company:深圳市讯方技术股份有限公司
* @author lyl
* @date 2017年6月16日 上午11:16:12
* @version V1.0  
*/
package com.xunfang.bdpf.vmstemplates.virtual.entity;


/**
* @ClassName: ImageEntity
* @Description: Image Endity 镜像实体
* @author lyl
* @date 2017年6月16日 上午11:16:12
*
*/

public class ImageEntity {
	/**
	 * image id
	 */
	private String id;
	/**
	 * image name
	 */
	private String name;
	/**
     * image url
     */
    private String imageUrl;
    /**
     * image diskFormat
     */
    private String diskFormat;
    /**
     * Indicates  whether the image is publicly available 
     */
   private  String isPublic = "1";
   
   /**
    * 
    */
   private String isProtected = "0";
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the diskFormat
	 */
	public String getDiskFormat() {
		return diskFormat;
	}

	/**
	 * @param diskFormat the diskFormat to set
	 */
	public void setDiskFormat(String diskFormat) {
		this.diskFormat = diskFormat;
	}

	/**
	 * @return the isPublic
	 */
	public String getIsPublic() {
		return isPublic;
	}

	/**
	 * @param isPublic the isPublic to set
	 */
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * @return the isProtected
	 */
	public String getIsProtected() {
		return isProtected;
	}

	/**
	 * @param isProtected the isProtected to set
	 */
	public void setIsProtected(String isProtected) {
		this.isProtected = isProtected;
	}
}
