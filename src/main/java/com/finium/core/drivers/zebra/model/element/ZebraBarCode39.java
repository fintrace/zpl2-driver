/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * ZebraBarCode39.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * Jan 26, 2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.model.element;

import com.finium.core.drivers.zebra.zpl.support.ZplUtils;

/**
 * Element to create a bar code 39
 * 
 * Zpl command : ^B3 and ^BY
 * 
 * @author Venkaiah Chowdary Koneru
 * 
 */
public class ZebraBarCode39 extends ZebraBarCode {

    private boolean checkDigit43 = false;

    public ZebraBarCode39(int positionX, int positionY, String text, int barCodeHeigth) {
	super(positionX, positionY, text, barCodeHeigth);
    }

    public ZebraBarCode39(int positionX, int positionY, String text, int barCodeHeigth, int barCodeWidth,
	    int wideBarRatio) {
	super(positionX, positionY, text, barCodeHeigth, barCodeWidth, wideBarRatio);
    }

    public ZebraBarCode39(int positionX, int positionY, String text, int barCodeHeigth, int barCodeWidth,
	    int wideBarRatio, boolean checkDigit43) {
	super(positionX, positionY, text, barCodeHeigth, barCodeWidth, wideBarRatio);
	this.setCheckDigit43(checkDigit43);
    }

    public ZebraBarCode39(int positionX, int positionY, String text, int barCodeHeigth, boolean showTextInterpretation,
	    boolean showTextInterpretationAbove) {
	super(positionX, positionY, text, barCodeHeigth, showTextInterpretation, showTextInterpretationAbove);
    }

    @Override
    public String getZplCode() {
	StringBuilder zpl = getStartZplCodeBuilder();
	zpl.append(ZplUtils.zplCommandSautLigne("B3", getZebraRotation(), getBarCodeHeigth(), checkDigit43,
		isShowTextInterpretation(), isShowTextInterpretationAbove()));
	zpl.append("^FD");
	zpl.append(getText());
	zpl.append(ZplUtils.zplCommandSautLigne("FS"));
	return zpl.toString();
    }

    public boolean isCheckDigit43() {
	return checkDigit43;
    }

    public ZebraBarCode39 setCheckDigit43(boolean checkDigit43) {
	this.checkDigit43 = checkDigit43;
	return this;
    }

}
