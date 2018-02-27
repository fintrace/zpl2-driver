/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * RecallFormat.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * 31-Jan-2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.model.element;

import static com.finium.core.drivers.zebra.zpl.command.ZebraCommonCodes.END_FMT;
import static com.finium.core.drivers.zebra.zpl.command.ZebraCommonCodes.FIELD_DATA;
import static com.finium.core.drivers.zebra.zpl.command.ZebraCommonCodes.FIELD_SEPERATOR;
import static com.finium.core.drivers.zebra.zpl.command.ZebraCommonCodes.RECALL_FORMAT;
import static com.finium.core.drivers.zebra.zpl.command.ZebraCommonCodes.START_FMT;

import java.util.Map;

import com.finium.core.drivers.zebra.zpl.support.ZplUtils;

/**
 * This command recalls a stored format to be merged with variable data. There
 * can be multiple commands of this in one format, and they can be located
 * anywhere within the code. <br>
 * <br>
 * When recalling a stored format and merging data using the
 * <code>^FN (Field Number)</code> function, the calling format must contain the
 * <code>^FN</code> command to merge the data properly. <br>
 * <br>
 * While using stored formats reduces transmission time, no formatting time is
 * saved. The <code>ZPL II</code> format being recalled is saved as text strings
 * that need to be formatted at print time. <br>
 * <br>
 * <b>Format: <br>
 * <code>^XFd:o.x</b><br>
 * d = device to store image. Accepted Values: R:, E:, B:, and A: Default Value:
 * R: <br>
 * o = image name. Accepted Values: 1 to 8 alphanumeric characters. Default
 * Value: if a name is not specified, UNKNOWN is used<br>
 * x = extension. Default Format: .ZPL<br>
 * </code>
 * 
 * @author Venkaiah Chowdary Koneru
 */
public class RecallFormat {
    private String deviceToStoreImage = "R:";
    private String imageName = "UNKNOWN";
    private String extension = ".ZPL";
    private Map<String, String> fieldNumbers;

    /**
     * 
     */
    public RecallFormat(String imageName) {
	if (imageName != null && imageName.trim().isEmpty()) {
	    this.imageName = imageName.trim();
	}
    }

    /**
     * @return the deviceToStoreImage
     */
    public String getDeviceToStoreImage() {
	return deviceToStoreImage;
    }

    /**
     * @param deviceToStoreImage
     *            the deviceToStoreImage to set
     */
    public void setDeviceToStoreImage(String deviceToStoreImage) {
	this.deviceToStoreImage = deviceToStoreImage;
    }

    /**
     * @return the imageName
     */
    public String getImageName() {
	return imageName;
    }

    /**
     * @param imageName
     *            the imageName to set
     */
    public void setImageName(String imageName) {
	if (imageName != null && imageName.trim().isEmpty()) {
	    this.imageName = imageName.trim();
	} else {
	    this.imageName = "UNKNOWN";
	}
    }

    /**
     * @return the extension
     */
    public String getExtension() {
	return extension;
    }

    /**
     * @param extension
     *            the extension to set
     */
    public void setExtension(String extension) {
	this.extension = extension;
    }

    /**
     * @return the fieldNumbers
     */
    public Map<String, String> getFieldNumbers() {
	return fieldNumbers;
    }

    /**
     * @param fieldNumbers
     *            the fieldNumbers to set
     */
    public void setFieldNumbers(Map<String, String> fieldNumbers) {
	this.fieldNumbers = fieldNumbers;
    }

    /**
     * 
     * @return
     */
    public String getZplCode() {
	StringBuilder zpl = new StringBuilder(ZplUtils.zplCommandSautLigne(START_FMT.name()));
	zpl.append(ZplUtils.zplCommand(RECALL_FORMAT.name()));
	zpl.append(this.deviceToStoreImage);
	zpl.append(this.imageName);
	zpl.append(this.extension);
	zpl.append("\n");

	fieldNumbers.forEach((key, value) -> {
	    zpl.append(ZplUtils.zplCommand(key));
	    zpl.append(ZplUtils.zplCommand(FIELD_DATA.name()));
	    zpl.append(value);
	    zpl.append(ZplUtils.zplCommandSautLigne(FIELD_SEPERATOR.name()));
	});
	zpl.append(ZplUtils.zplCommandSautLigne(END_FMT.name()));

	return zpl.toString();
    }
}
