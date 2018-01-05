package com.xunfang.utils.office.util;

import java.io.InputStream;
import org.apache.log4j.Logger;
import com.aspose.words.License;

/**
 * 
 * @ClassName AsposeLicenseUtil
 * @Description: Aspose注册工具 
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author wanglf
 * @date 2017年6月24日 下午4:39:26
 * @version V1.0
 */
public class AsposeLicenseUtil {

	private static InputStream inputStream = null;

	private static Logger logger = Logger.getLogger(AsposeLicenseUtil.class);

	/**
	 * 获取License的输入流
	 *
	 * @return
	 */
	private static InputStream getLicenseInput() {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		inputStream = contextClassLoader.getResourceAsStream("license.xml");
		return inputStream;
	}

	/**
	 * 设置License
	 *
	 * @return true表示已成功设置License, false表示失败
	 */
	public static boolean setWordsLicense() {
		InputStream licenseInput = getLicenseInput();
		if (licenseInput != null) {
			try {
				License aposeLic = new License();
				aposeLic.setLicense(licenseInput);
				return aposeLic.getIsLicensed();
			} catch (Exception e) {
				logger.error("set words license error!", e);
			}
		}
		return false;
	}

	/**
	 * 设置License
	 *
	 * @return true表示已成功设置License, false表示失败
	 */
	public static boolean setCellsLicense() {
		InputStream licenseInput = getLicenseInput();
		if (licenseInput != null) {
			try {
				com.aspose.cells.License aposeLic = new com.aspose.cells.License();
				aposeLic.setLicense(licenseInput);
				return true;
			} catch (Exception e) {
				logger.error("set cells license error!", e);
			}
		}
		return false;
	}

	/**
	 * 设置License
	 *
	 * @return true表示已成功设置License, false表示失败
	 */
	public static boolean setSlidesLicense() {
		InputStream licenseInput = getLicenseInput();
		if (licenseInput != null) {
			try {
				com.aspose.slides.License aposeLic = new com.aspose.slides.License();
				aposeLic.setLicense(licenseInput);
				return aposeLic.isLicensed();
			} catch (Exception e) {
				logger.error("set ppt license error!", e);
			}
		}
		return false;
	}

	/**
	 * 设置Aspose PDF的license
	 * 
	 * @return true表示设置成功，false表示设置失败
	 */
	public static boolean setPdfLicense() {
		InputStream licenseInput = getLicenseInput();
		if (licenseInput != null) {
			try {
				com.aspose.pdf.License aposeLic = new com.aspose.pdf.License();
				aposeLic.setLicense(licenseInput);
				return true;
			} catch (Exception e) {
				logger.error("set pdf license error!", e);
			}
		}
		return false;
	}
}
