/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * ZebraGraficBox.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * Jan 26, 2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.model.element;

import com.finium.core.drivers.zebra.model.ZebraElement;
import com.finium.core.drivers.zebra.zpl.support.ZplUtils;

/**
 * Zebra element to create a box (or line)
 * 
 * Zpl command : ^GB
 * 
 * @author Venkaiah Chowdary Koneru
 * 
 */
public class ZebraGraficBox extends ZebraElement {

    private Integer width;
    private Integer height;
    private Integer borderTickness;
    private String lineColor;

    public ZebraGraficBox(int positionX, int positionY, Integer width, Integer height, Integer borderTickness,
	    String lineColor) {
	this.positionX = positionX;
	this.positionY = positionY;
	this.width = width;
	this.height = height;
	this.borderTickness = borderTickness;
	this.lineColor = lineColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getZplCode() {
	StringBuilder zpl = new StringBuilder();
	zpl.append(getZplCodePosition());
	zpl.append("\n");
	zpl.append(ZplUtils.zplCommand("GB", width, height, borderTickness, lineColor));
	zpl.append("^FS");
	zpl.append("\n");
	return zpl.toString();
    }

    protected String getZplCodePosition() {
	StringBuffer zpl = new StringBuffer("");
	if (positionX != null && positionY != null) {
	    zpl.append(ZplUtils.zplCommand("FO", positionX, positionY));
	}
	return zpl.toString();
    }

}
