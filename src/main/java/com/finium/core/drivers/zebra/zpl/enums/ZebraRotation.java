/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * ZebraRotation.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * Jan 26, 2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.zpl.enums;

/**
 * Constants to use for rotation.
 * 
 * @author Venkaiah Chowdary Koneru
 */
public enum ZebraRotation {

    NORMAL("N"), ROTATE_90("R"), INVERTED("I"), READ_FROM_BOTTOM("B");

    String letter;

    /**
     * 
     * @param letter
     */
    private ZebraRotation(String letter) {
	this.letter = letter;
    }

    /**
     * @return the letter
     */
    public String getLetter() {
	return letter;
    }

}
