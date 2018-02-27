/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * ZebraPrinter.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * 31-Jan-2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.printer;

import java.util.List;

import com.finium.core.drivers.zebra.model.ZebraLabel;
import com.finium.core.drivers.zebra.printer.connection.ZebraConnectionClient;
import com.finium.core.drivers.zebra.zpl.support.exceptions.ZebraPrintException;

/**
 * @author Venkaiah Chowdary Koneru
 */
public abstract class AbstractZebraPrinter {

    private ZebraConnectionClient connectionClient;

    /**
     * Constructor
     */
    public AbstractZebraPrinter(ZebraConnectionClient connectionClient) {
	this.connectionClient = connectionClient;
    }

    /**
     * 
     */
    public void connect() {
	this.connectionClient.connect();
    }

    /**
     * 
     */
    public void disconnect() {
	this.connectionClient.disconnect();
    }

    /**
     * 
     * @return connectionClient
     */
    public ZebraConnectionClient getConnectionClient() {
	return connectionClient;
    }

    /**
     * prints a zebraLabel
     * 
     * @param zebraLabel
     *            zebraLabel
     * @throws ZebraPrintException
     *             if zpl could not be printed
     */
    public void printZpl(ZebraLabel zebraLabel) throws ZebraPrintException {
	printZpl(zebraLabel.getZplCode());
    }

    /**
     * prints multiple zebraLabels
     * 
     * @param zebraLabels
     *            list of zebra labels
     * @throws ZebraPrintException
     *             if zpl could not be printed
     */
    public void printZpl(List<ZebraLabel> zebraLabels) throws ZebraPrintException {
	StringBuilder zpl = new StringBuilder();
	for (ZebraLabel zebraLabel : zebraLabels) {
	    zpl.append(zebraLabel.getZplCode());
	}
	printZpl(zpl.toString());
    }

    /**
     * prints plain zpl text to network zebra
     * 
     * @param zpl
     *            code Zpl to print
     * @throws ZebraPrintException
     *             if zpl could not be printed
     */
    public void printZpl(String zpl) throws ZebraPrintException {
	this.connectionClient.send(zpl);
    }
}
