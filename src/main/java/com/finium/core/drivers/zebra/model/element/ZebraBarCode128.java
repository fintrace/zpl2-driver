/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * ZebraBarCode128.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * Jan 26, 2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.model.element;

import static com.finium.core.drivers.zebra.zpl.command.ZebraCommonCodes.FIELD_DATA;
import static com.finium.core.drivers.zebra.zpl.command.ZebraCommonCodes.FIELD_SEPERATOR;

import com.finium.core.drivers.zebra.zpl.support.ZplUtils;

/**
 * Element to create a bar code 128
 * 
 * Zpl command : ^BC
 * 
 * @author Venkaiah Chowdary Koneru
 */
public class ZebraBarCode128 extends ZebraBarCode {

    private boolean checkDigit43 = false;

    public ZebraBarCode128(int positionX, int positionY, String text) {
	super(positionX, positionY, text);
    }

    public ZebraBarCode128(int positionX, int positionY, String text, int barCodeHeigth) {
	super(positionX, positionY, text, barCodeHeigth);
    }

    public ZebraBarCode128(int positionX, int positionY, String text, int barCodeHeigth, int barCodeWidth,
	    int wideBarRatio) {
	super(positionX, positionY, text, barCodeHeigth, barCodeWidth, wideBarRatio);
    }

    public ZebraBarCode128(int positionX, int positionY, String text, int barCodeHeigth, boolean showTextInterpretation,
	    int barCodeWidth, int wideBarRatio) {
	super(positionX, positionY, text, barCodeHeigth, showTextInterpretation, barCodeWidth, wideBarRatio);
    }

    public ZebraBarCode128(int positionX, int positionY, String text, int barCodeHeigth, int barCodeWidth,
	    int wideBarRatio, boolean checkDigit43) {
	super(positionX, positionY, text, barCodeHeigth, barCodeWidth, wideBarRatio);
	this.setCheckDigit43(checkDigit43);
    }

    public ZebraBarCode128(int positionX, int positionY, String text, int barCodeHeigth, boolean showTextInterpretation,
	    boolean showTextInterpretationAbove) {
	super(positionX, positionY, text, barCodeHeigth, showTextInterpretation, showTextInterpretationAbove);
    }

    @Override
    public String getZplCode() {
	StringBuilder zpl = getStartZplCodeBuilder();
	zpl.append(ZplUtils.zplCommandSautLigne("BC", getZebraRotation().getLetter(), getBarCodeHeigth(),
		isShowTextInterpretation(), isShowTextInterpretationAbove(), checkDigit43));
	zpl.append(ZplUtils.zplCommand(FIELD_DATA.name()));
	zpl.append(getText());
	zpl.append(ZplUtils.zplCommandSautLigne(FIELD_SEPERATOR.name()));
	return zpl.toString();
    }

    public boolean isCheckDigit43() {
	return checkDigit43;
    }

    public ZebraBarCode128 setCheckDigit43(boolean checkDigit43) {
	this.checkDigit43 = checkDigit43;
	return this;
    }

}
