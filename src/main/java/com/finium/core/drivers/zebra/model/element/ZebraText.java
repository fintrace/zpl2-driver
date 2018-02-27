/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * ZebraText.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * Jan 26, 2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.model.element;

import java.awt.Font;
import java.awt.Graphics2D;

import com.finium.core.drivers.zebra.model.PrinterOptions;
import com.finium.core.drivers.zebra.model.ZebraElement;
import com.finium.core.drivers.zebra.zpl.enums.ZebraFont;
import com.finium.core.drivers.zebra.zpl.enums.ZebraRotation;
import com.finium.core.drivers.zebra.zpl.support.ZplUtils;

/**
 * Zebra element to add Text to specified position.
 * 
 * @author Venkaiah Chowdary Koneru
 * 
 */
public class ZebraText extends ZebraElement {

    ZebraFont zebraFont = null;

    /**
     * Explain Font Size (11,13,14). Not in dots.
     */
    Integer fontSize = null;

    ZebraRotation zebraRotation = ZebraRotation.NORMAL;

    String text;

    public ZebraText(String text) {
	this.text = text;
    }

    public ZebraText(String text, int fontSize) {
	this.fontSize = fontSize;
	this.text = text;
    }

    public ZebraText(String text, ZebraFont zebraFont, int fontSize) {
	this.zebraFont = zebraFont;
	this.fontSize = fontSize;
	this.text = text;
    }

    public ZebraText(String text, ZebraFont zebraFont, int fontSize, ZebraRotation zebraRotation) {
	this.zebraFont = zebraFont;
	this.zebraRotation = zebraRotation;
	this.fontSize = fontSize;
	this.text = text;
    }

    public ZebraText(int positionX, int positionY, String text) {
	this.text = text;
	this.positionX = positionX;
	this.positionY = positionY;
    }

    public ZebraText(int positionX, int positionY, String text, int fontSize) {
	this.fontSize = fontSize;
	this.text = text;
	this.positionX = positionX;
	this.positionY = positionY;
    }

    public ZebraText(int positionX, int positionY, String text, ZebraFont zebraFont, int fontSize,
	    ZebraRotation zebraRotation) {
	this.zebraFont = zebraFont;
	this.fontSize = fontSize;
	this.zebraRotation = zebraRotation;
	this.text = text;
	this.positionX = positionX;
	this.positionY = positionY;
    }

    public ZebraText(int positionX, int positionY, String text, ZebraFont zebraFont, int fontSize) {
	this.zebraFont = zebraFont;
	this.fontSize = fontSize;
	this.text = text;
	this.positionX = positionX;
	this.positionY = positionY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getZplCode() {
	StringBuffer zpl = new StringBuffer();
	zpl.append(this.getZplCodePosition());

	if (fontSize != null && zebraFont != null) {
	    // This element has specified size and font
	    Integer[] dimension = ZplUtils.extractDotsFromFont(zebraFont, fontSize, printerOptions.getZebraPPP());
	    zpl.append(ZplUtils.zplCommand("A", zebraFont.getLetter() + zebraRotation.getLetter(), dimension[0],
		    dimension[1]));
	} else if (fontSize != null && printerOptions.getDefaultZebraFont() != null) {
	    // This element has specified size, but with default font
	    Integer[] dimension = ZplUtils.extractDotsFromFont(printerOptions.getDefaultZebraFont(), fontSize,
		    printerOptions.getZebraPPP());
	    zpl.append(ZplUtils.zplCommand("A",
		    printerOptions.getDefaultZebraFont().getLetter() + zebraRotation.getLetter(), dimension[0],
		    dimension[1]));
	}

	zpl.append("^FH\\^FD");// We allow hexadecimal and start element
	zpl.append(ZplUtils.convertAccentToZplAsciiHexa(text));
	zpl.append(ZplUtils.zplCommandSautLigne("FS"));

	return zpl.toString();
    }

    /**
     * Used to draw label preview. This method should be overloader by child
     * class.
     * 
     * Default draw a rectangle
     * 
     * @param graphic
     */
    public void drawPreviewGraphic(PrinterOptions printerOptions, Graphics2D graphic) {
	if (defaultDrawGraphic) {
	    int top = 0;
	    int left = 0;
	    if (positionX != null) {
		left = ZplUtils.convertPointInPixel(positionX);
	    }
	    if (positionY != null) {
		top = ZplUtils.convertPointInPixel(positionY);
	    }

	    Font font = null;

	    if (fontSize != null && zebraFont != null) {
		// This element has specified size and font
		Integer[] dimension = ZplUtils.extractDotsFromFont(printerOptions.getDefaultZebraFont(), fontSize,
			printerOptions.getZebraPPP());

		font = new Font(ZebraFont.findBestEquivalentFontForPreview(zebraFont), Font.BOLD, dimension[0]);
	    } else if (fontSize != null && printerOptions.getDefaultZebraFont() != null) {
		// This element has specified size, but with default font
		Integer[] dimensionPoint = ZplUtils.extractDotsFromFont(printerOptions.getDefaultZebraFont(), fontSize,
			printerOptions.getZebraPPP());
		font = new Font(ZebraFont.findBestEquivalentFontForPreview(printerOptions.getDefaultZebraFont()),
			Font.BOLD, Math.round(dimensionPoint[0] / 1.33F));
	    } else {
		// Default font on Printer Zebra
		Integer[] dimensionPoint = ZplUtils.extractDotsFromFont(printerOptions.getDefaultZebraFont(), 15,
			printerOptions.getZebraPPP());

		font = new Font(ZebraFont.findBestEquivalentFontForPreview(ZebraFont.ZEBRA_A), Font.BOLD,
			dimensionPoint[0]);
	    }
	    drawTopString(graphic, font, text, left, top);
	}
    }
}
