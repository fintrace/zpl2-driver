/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * ZebraPPP.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * Jan 26, 2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.zpl.enums;

/**
 * Constants used to define printed precision
 * 
 * @author Venkaiah Chowdary Koneru
 */
public enum ZebraPPP {

    DPI_203(8), DPI_300(12), DPI_600(23.5F);

    private float dotByMm;

    /**
     * 
     * @param dotByMm
     */
    private ZebraPPP(float dotByMm) {
	this.dotByMm = dotByMm;
    }

    /**
     * @return the dotByMm
     */
    public float getDotByMm() {
	return dotByMm;
    }
}
