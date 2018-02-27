/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * ZebraFont.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * Jan 26, 2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.zpl.enums;

/**
 * Constants used to define the zebra suported fonts.
 * 
 * @author Venkaiah Chowdary Koneru
 */
public enum ZebraFont {

    ZEBRA_ZERO("0"), ZEBRA_A("A"), ZEBRA_B("B"), ZEBRA_C("C"), ZEBRA_D("D"), ZEBRA_F("F"), ZEBRA_G("G");

    String letter;

    /**
     * 
     * @param letter
     */
    private ZebraFont(String letter) {
	this.letter = letter;
    }

    /**
     * @return the letter
     */
    public String getLetter() {
	return letter;
    }

    /**
     * use for preview to find an equivalent font compatible with Graphic2D
     * 
     * @param zebraFont
     * @return
     */
    public static String findBestEquivalentFontForPreview(ZebraFont zebraFont) {
	return "Arial";
    }

}
