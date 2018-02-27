/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * QRCodeModel.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * 31-Jan-2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.zpl.enums;

/**
 * QR Code Model. <br>
 * Model 1 is the original specification, while QR Code Model 2 is an enhanced
 * form of the symbology. Model 2 provides additional features and can be
 * automatically differentiated from Model 1.<br>
 * <br>
 * Model 2 is the recommended model and should normally be used.<br>
 * 
 * @author Venkaiah Chowdary Koneru
 */
public enum QRCodeModel {
    MODEL1(1), MODEL2(2);

    private int model;

    private QRCodeModel(int model) {
	this.model = model;
    }

    /**
     * @return the model
     */
    public int getModel() {
	return model;
    }
}
