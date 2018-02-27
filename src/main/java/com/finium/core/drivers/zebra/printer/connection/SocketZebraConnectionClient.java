/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * SocketZebraConnectionClient.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * 31-Jan-2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.printer.connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.finium.core.drivers.zebra.listeners.ClientListener;
import com.finium.core.drivers.zebra.listeners.DataListener;

/**
 * This class is an implementation of <code>ZebraConnectionClient</code> That
 * will communicate with Zebra printer using TCP/IP connectivity protocol and
 * their supported medium (LAN, INTERNET or simple cross UTP).
 * 
 * This implementation is using high performance NIO non blocking method.
 * However, it employs single independent thread that do the channel selector
 * and invocation.
 * 
 * IMPORTANT : Each notifications fires by this class for each of its registered
 * listeners is done by their own independent thread. This means new thread for
 * each notification fires. Thus, the listener implementation added into this
 * socket do not need to implement "Fast Return" method and will not clog the
 * selector thread.
 * 
 * @author Venkaiah Chowdary Koneru
 *
 */
public class SocketZebraConnectionClient implements ZebraConnectionClient {

    private List<ClientListener> clientListeners = new ArrayList<ClientListener>();
    private List<DataListener> dataListeners = new ArrayList<DataListener>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private ExecutorService commExecutorService = Executors.newSingleThreadExecutor();

    private String host;
    private int port;

    private SocketChannel channel;
    private Selector selector;

    private boolean connected = false;
    private boolean alive = false;

    /**
     * Constructor
     * 
     * @param host
     *            The zebra printer host address or IP address.
     * @param port
     *            The port number on zebra printer that will accept the
     *            connection.
     */
    public SocketZebraConnectionClient(String host, int port) {
	super();
	this.host = host;
	this.port = port;
    }

    private Runnable commRunnable = new Runnable() {
	public void run() {
	    if (!connected) {
		try {
		    selector = Selector.open();
		    channel = SocketChannel.open();
		    channel.configureBlocking(false);

		    channel.register(selector, SelectionKey.OP_CONNECT);
		    channel.connect(new InetSocketAddress(host, port));

		    alive = true;
		    while (alive && !Thread.interrupted()) {
			selector.select(1000);
			Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
			while (keys.hasNext()) {
			    SelectionKey key = keys.next();
			    keys.remove();
			    if (!key.isValid()) {
				continue;
			    } else if (key.isConnectable()) {
				makeConnect(key);
				connected = true;
				notifyConnected();
			    } else {
				System.out.println("Unrecognized valid key");
			    }
			}
		    }
		} catch (IOException ioe) {
		    notifyFailConnect(ioe);
		} finally {
		    if (connected) {
			notifyDisconnected();
		    }
		    connected = false;
		    alive = false;
		    try {
			if (channel != null)
			    channel.close();
			if (selector != null)
			    selector.close();
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}
	    }
	}

    };

    private void notifyFailConnect(final Exception e) {
	/*
	 * For each listener, create a separate independent thread for firing
	 * the listener methods.
	 */
	for (final ClientListener l : clientListeners) {
	    executorService.execute(new Runnable() {
		public void run() {
		    l.connectionIsFailing(SocketZebraConnectionClient.this, e);
		}
	    });
	}
    }

    private void notifyConnected() {
	/*
	 * For each listener, create a separate independent thread for firing
	 * the listener methods.
	 */
	for (final ClientListener l : clientListeners) {
	    executorService.execute(new Runnable() {
		public void run() {
		    l.connectionEstablised(SocketZebraConnectionClient.this);
		}
	    });
	}
    }

    private void notifyDisconnected() {
	/*
	 * For each listener, create a separate independent thread for firing
	 * the listener methods.
	 */
	for (final ClientListener l : clientListeners) {
	    executorService.execute(new Runnable() {
		public void run() {
		    l.connectionLost(SocketZebraConnectionClient.this);
		}
	    });
	}
    }

    private void notifyMessageSent(final String message) {
	/*
	 * For each listener, create a separate independent thread for firing
	 * the listener methods.
	 */
	for (final DataListener l : dataListeners) {
	    executorService.execute(new Runnable() {
		public void run() {
		    l.messageSent(message);
		}
	    });
	}
    }

    private void notifyMessageSendFailed(final ConnectionClientException exception, final String messageToSend) {
	/*
	 * For each listener, create a separate independent thread for firing
	 * the listener methods.
	 */
	for (final DataListener l : dataListeners) {
	    executorService.execute(new Runnable() {
		public void run() {
		    l.messageSendFailed(exception, messageToSend);
		}
	    });
	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addClientListener(ClientListener listener) {
	clientListeners.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeClientListener(ClientListener listener) {
	clientListeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDataListener(DataListener listener) {
	dataListeners.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeDataListener(DataListener listener) {
	dataListeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws IOException
     */
    @Override
    public void send(String zplMessage) {
	try {
	    ByteBuffer bb = ByteBuffer.wrap(zplMessage.getBytes(Charset.forName("US-ASCII")));
	    while (bb.hasRemaining()) {
		channel.write(bb);
	    }
	    notifyMessageSent(zplMessage);
	} catch (IOException ioe) {
	    notifyMessageSendFailed(new ConnectionClientException("Failed to send message.", ioe), zplMessage);
	}
    }

    /**
     * {@inheritDoc}
     */
    public void connect() {
	if (!alive) {
	    commExecutorService.execute(commRunnable);
	}
    }

    /**
     * {@inheritDoc}
     */
    public void disconnect() {
	if (alive) {
	    alive = false;
	}

    }

    private void makeConnect(SelectionKey key) throws IOException {
	SocketChannel channel = (SocketChannel) key.channel();
	if (channel.isConnectionPending()) {
	    channel.finishConnect();
	}
	channel.configureBlocking(false);
    }

    /**
     * Get the configure host address.
     * 
     * @return the host
     */
    public String getHost() {
	return host;
    }

    /**
     * Set the host address to connect to.
     * 
     * @param host
     *            the host to set
     */
    public void setHost(String host) {
	this.host = host;
    }

    /**
     * @return the port
     */
    public int getPort() {
	return port;
    }

    /**
     * @param port
     *            the port to set
     */
    public void setPort(int port) {
	this.port = port;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isConnected() {
	return connected;
    }

    /**
     * @param connected
     *            the connected to set
     */
    public void setConnected(boolean connected) {
	this.connected = connected;
    }
}
