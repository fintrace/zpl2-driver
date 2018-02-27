/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * QRCodeErrorCorrection.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * 31-Jan-2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.zpl.enums;

/**
 * error correction for QR Code symbology.
 * <ul>
 * Available Values:
 * <li><b>H</b> = ultra-high reliability level</li>
 * <li><b>Q</b> = high reliability level</li>
 * <li><b>M</b> = standard level</li>
 * <li><b>L</b> = high density level.</li>
 * </ul>
 * <ul>
 * Default Values should be:
 * <li><b>Q</b> = if empty</li>
 * <li><b>M</b> = invalid values</li>
 * </ul>
 * 
 * @author Venkaiah Chowdary Koneru
 */
public enum QRCodeErrorCorrection {
    ULTRA_HIGH("H"), HIGH("Q"), STANDARD("M"), HIGH_DENSITY("L");

    private String letter;

    /**
     * 
     */
    private QRCodeErrorCorrection(String letter) {
	this.letter = letter;
    }

    /**
     * @return the letter
     */
    public String getLetter() {
	return letter;
    }
}
