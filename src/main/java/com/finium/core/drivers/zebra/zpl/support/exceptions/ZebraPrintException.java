/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * ZebraPrintException.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * Jan 26, 2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.zpl.support.exceptions;

/**
 * Exception throwed when Socket communication with printer failed
 * 
 * @author Venkaiah Chowdary Koneru
 * 
 */
public class ZebraPrintException extends Exception {

    private static final long serialVersionUID = -7865386315087561487L;

    /**
     * Default Constructor
     * 
     * @param message
     *            message
     */
    public ZebraPrintException(String message) {
	super(message);
    }

    /**
     * Default Constructor
     * 
     * @param message
     *            message
     */
    public ZebraPrintException(String message, Throwable t) {
	super(message, t);
    }
}
