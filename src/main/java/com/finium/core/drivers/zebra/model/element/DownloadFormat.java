/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * DownloadFormat.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * 31-Jan-2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.model.element;

import static com.finium.core.drivers.zebra.zpl.command.ZebraCommonCodes.DOWNLOAD_FMT;
import static com.finium.core.drivers.zebra.zpl.command.ZebraCommonCodes.END_FMT;
import static com.finium.core.drivers.zebra.zpl.command.ZebraCommonCodes.FIELD_SEPERATOR;
import static com.finium.core.drivers.zebra.zpl.command.ZebraCommonCodes.START_FMT;

import com.finium.core.drivers.zebra.model.ZebraElement;
import com.finium.core.drivers.zebra.zpl.support.ZplUtils;

/**
 * This command saves <code>ZPL II</code> format commands as text strings to be
 * later merged using <code>^XF</code> with variable data. The format to be
 * stored might contain field number <code>(^FN)</code> commands to be
 * referenced when recalled.<br>
 * <br>
 * While use of stored formats reduces transmission time, no formatting time is
 * saved—this command saves <code>ZPL II</code> as text strings formatted at
 * print time.<br>
 * <br>
 * Enter the <code>^DF</code> stored format command immediately after the
 * <code>^XA</code> command, then enter the format commands to be saved. <br>
 * <br>
 * <br>
 * <b>Format: <br>
 * <code>^DFd:o.x<br>
 * </b> <br>
 * d = device to store image. Accepted Values: R:, E:, B:, and A: Default Value:
 * R: <br>
 * o = image name. Accepted Values: 1 to 8 alphanumeric characters.Default
 * Value: if a name is not specified, UNKNOWN is used<br>
 * x = extension. Default Format: .ZPL<br>
 * </code>
 * 
 * @author Venkaiah Chowdary Koneru
 */
public class DownloadFormat extends ZebraElement {
    private String deviceToStoreImage = "R:";
    private String imageName = "UNKNOWN";
    private String extension = ".ZPL";
    private String text;

    /**
     * 
     */
    public DownloadFormat(String imageName, String text) {
	if (imageName != null && imageName.trim().isEmpty()) {
	    this.imageName = imageName.trim();
	}
	this.text = text;
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
     * @return the text
     */
    public String getText() {
	return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
	this.text = text;
    }

    /**
     * 
     * @return
     */
    @Override
    public String getZplCode() {
	StringBuilder zpl = new StringBuilder(ZplUtils.zplCommandSautLigne(START_FMT.name()));
	zpl.append(ZplUtils.zplCommand(DOWNLOAD_FMT.name()));
	zpl.append(this.deviceToStoreImage);
	zpl.append(this.imageName);
	zpl.append(this.extension);
	zpl.append(ZplUtils.zplCommandSautLigne(FIELD_SEPERATOR.name()));
	zpl.append(this.text);
	zpl.append("\n");
	zpl.append(ZplUtils.zplCommandSautLigne(END_FMT.name()));

	return zpl.toString();
    }
}
