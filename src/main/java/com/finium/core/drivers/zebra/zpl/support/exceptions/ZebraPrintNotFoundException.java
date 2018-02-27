package com.finium.core.drivers.zebra.zpl.support.exceptions;

/**
 * Exception throwed when printer cannot be found
 * 
 * @author ttropard
 *
 */
public class ZebraPrintNotFoundException extends ZebraPrintException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ZebraPrintNotFoundException(String message) {
	super(message);
    }

    public ZebraPrintNotFoundException(String message, Throwable t) {
	super(message, t);
    }

}
