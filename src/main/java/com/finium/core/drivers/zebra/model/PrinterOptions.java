/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * PrinterOptions.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * Jan 26, 2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.model;

import com.finium.core.drivers.zebra.zpl.enums.ZebraFont;
import com.finium.core.drivers.zebra.zpl.enums.ZebraPPP;

/**
 * Object about printer type and model,sended to each element to adapt zpl
 * (Heigth, Width)
 * 
 * 
 * @author Venkaiah Chowdary Koneru
 */
public class PrinterOptions {

    private ZebraPPP zebraPPP = ZebraPPP.DPI_300;

    private ZebraFont defaultZebraFont = null;

    private Integer defaultFontSize = null;

    public PrinterOptions() {
	super();
    }

    public PrinterOptions(ZebraPPP zebraPPP) {
	super();
	this.zebraPPP = zebraPPP;
    }

    /**
     * @return the zebraPPP
     */
    public ZebraPPP getZebraPPP() {
	return zebraPPP;
    }

    /**
     * @param zebraPPP
     *            the zebraPPP to set
     */
    public PrinterOptions setZebraPPP(ZebraPPP zebraPPP) {
	this.zebraPPP = zebraPPP;
	return this;
    }

    /**
     * @return the defaultZebraFont
     */
    public ZebraFont getDefaultZebraFont() {
	return defaultZebraFont;
    }

    /**
     * @return the defaultFontSize
     */
    public Integer getDefaultFontSize() {
	return defaultFontSize;
    }

    /**
     * @param defaultZebraFont
     *            the defaultZebraFont to set
     */
    public PrinterOptions setDefaultZebraFont(ZebraFont defaultZebraFont) {
	this.defaultZebraFont = defaultZebraFont;
	return this;
    }

    /**
     * @param defaultFontSize
     *            the defaultFontSize to set
     */
    public PrinterOptions setDefaultFontSize(Integer defaultFontSize) {
	this.defaultFontSize = defaultFontSize;
	return this;
    }

}
