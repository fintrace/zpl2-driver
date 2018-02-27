/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * ZebraConnectionClient.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * 31-Jan-2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.printer.connection;

import com.finium.core.drivers.zebra.listeners.ClientListener;
import com.finium.core.drivers.zebra.listeners.DataListener;

/**
 * This interface define the Connection level contract with the zebra system.
 * Implementation can be done with Socket, RS232 or other mechanism.
 * 
 * @author Venkaiah Chowdary Koneru
 */
public interface ZebraConnectionClient {

    /**
     * Add a ClientListener to this client. This method will not check for
     * duplicated listener. So please be careful not to add the listener twice,
     * cause if you do, your listener will receive multiple notification.
     * 
     * @param listener
     *            Listener to add.
     */
    void addClientListener(ClientListener listener);

    /**
     * Remove the previously added ClientListener. If the specified listener is
     * not exist, the method will not do anything.
     * 
     * @param listener
     *            The listener to remove.
     */
    void removeClientListener(ClientListener listener);

    /**
     */
    public void addDataListener(DataListener listener);

    /**
     */
    public void removeDataListener(DataListener listener);

    /**
     * Instruct the client to initiate the connection. This method will return
     * immediately. You should listen for connection status using the provided
     * listeners call backs. (ie, ClientListener). OR, using the isConnected
     * method. Connection might not happen instantly after the method call.
     */
    void connect();

    /**
     * Instruct the client to execute disconnect routines. This method will
     * return immediately. You should listen for connection status using the
     * provided listeners call backs. (ie, ClientListener). OR, using is
     * Connected method. Connection close might not happen instantly after the
     * method call.
     */
    void disconnect();

    /**
     * Check the connectivity status.
     * 
     * @return <code>true</code> if connection is currently established, or
     *         <code>false</code> if the client is disconnected.
     */
    boolean isConnected();

    /**
     * Instruct the client to send ZPL communication message. This method will
     * return immediately. You should listen for response using the provided
     * listeners call backs. (ie, DataListener).
     * 
     * @param zplMessage
     *            The message to send.
     */
    void send(String zplMessage);
}
