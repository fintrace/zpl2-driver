/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * ZebraPrintMode.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * Jan 26, 2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.zpl.command;

/**
 * Command to determine this action the printer takes after a label or group of
 * label has been printed.
 * 
 * ZPL command : ^MM
 * 
 * @author Venkaiah Chowdary Koneru
 */
public enum ZebraPrintMode {

    TEAR_OFF("T"), REWIND("R"), PEEL_OFF_SELECT("P", true), PEEL_OFF_NOSELECT("P", false), CUTTER("C");

    String desiredMode;
    String prePeelSelect;

    /**
     * 
     * @param desiredMode
     */
    private ZebraPrintMode(String desiredMode) {
	this.desiredMode = desiredMode;
	this.prePeelSelect = "";
    }

    /**
     * 
     * @param desiredMode
     * @param prePeelSelectB
     */
    private ZebraPrintMode(String desiredMode, boolean prePeelSelectB) {
	this.desiredMode = desiredMode;
	if (prePeelSelectB) {
	    prePeelSelect = ",Y";
	} else {
	    prePeelSelect = ",N";
	}
    }

    /**
     * @return the desiredMode
     */
    public String getDesiredMode() {
	return desiredMode;
    }

    /**
     * @return the prePeelSelect
     */
    public String getPrePeelSelect() {
	return prePeelSelect;
    }

    /**
     * Function which return ^MM command
     * 
     * @return
     */
    public String getZplCode() {
	return "^MM" + desiredMode + prePeelSelect + "\n";
    }
}
