/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * ZebraAFontElement.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * Jan 26, 2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.model.element;

import com.finium.core.drivers.zebra.model.ZebraElement;
import com.finium.core.drivers.zebra.zpl.enums.ZebraFont;
import com.finium.core.drivers.zebra.zpl.enums.ZebraRotation;
import com.finium.core.drivers.zebra.zpl.support.ZplUtils;

/**
 * Element to set a font and size (explain in dot).
 * 
 * This command is apply only on the next element (in zebraElements list).
 * 
 * This command could be use when your font and PPP is not yet implemented
 * 
 * ZPL Command : ^A
 * 
 * @author Venkaiah Chowdary Koneru
 */
public class ZebraAFontElement extends ZebraElement {

    private ZebraFont zebraFont;

    private ZebraRotation zebraRotation = ZebraRotation.NORMAL;

    private int dotHeigth;
    private int dotsWidth;

    /**
     * Use this constructor if you want to change the font of the next element.
     * 
     * @param zebraFont
     *            zebra font
     */
    public ZebraAFontElement(ZebraFont zebraFont) {
	super();
	this.zebraFont = zebraFont;
    }

    /**
     * Constructor to set font and size of the next element
     * 
     * @param zebraFont
     *            font zebra
     * @param dotHeigth
     *            height explain in dots
     * @param dotsWidth
     *            height explain in dots
     */
    public ZebraAFontElement(ZebraFont zebraFont, int dotHeigth, int dotsWidth) {
	super();
	this.zebraFont = zebraFont;
	this.dotHeigth = dotHeigth;
	this.dotsWidth = dotsWidth;
    }

    /**
     * Constructor to use if you want have non-horizontal text.
     * 
     * @param zebraFont
     *            font zebra
     * @param zebraRotation
     *            text rotation
     * @param dotHeigth
     *            height explain in dots
     * @param dotsWidth
     *            height explain in dots
     */
    public ZebraAFontElement(ZebraFont zebraFont, ZebraRotation zebraRotation, int dotHeigth, int dotsWidth) {
	super();
	this.zebraFont = zebraFont;
	this.zebraRotation = zebraRotation;
	this.dotHeigth = dotHeigth;
	this.dotsWidth = dotsWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getZplCode() {
	return ZplUtils.zplCommandSautLigne("A", zebraFont.getLetter(), zebraRotation.getLetter(), dotHeigth, dotsWidth)
		.toString();
    }
}
