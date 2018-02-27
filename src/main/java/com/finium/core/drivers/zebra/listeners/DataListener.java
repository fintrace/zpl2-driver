/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * DataListener.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * 31-Jan-2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.listeners;

import com.finium.core.drivers.zebra.printer.connection.ConnectionClientException;

/**
 * @author Venkaiah Chowdary Koneru
 */
public interface DataListener {

    /**
     * Invoked when the client have guaranteed a successful sending message to
     * the printer.
     * 
     * @param message
     *            Message has been sent.
     */
    void messageSent(String message);

    /**
     * Invoked when the client has successfully received a message from the
     * printer.
     * 
     * @param message
     *            The Message received.
     */
    void messageReceived(String message);

    /**
     * Invoked when client is sure that it has failed to send a message.
     * ZebraConnectionClient will not try to 're-send' the data. client must
     * attempt to send them again.
     * 
     * @param exception
     *            The exception that causes the message sending failure.
     * @param messageToSend
     *            The message to be send when error occurred.
     */
    void messageSendFailed(ConnectionClientException exception, String messageToSend);
}
