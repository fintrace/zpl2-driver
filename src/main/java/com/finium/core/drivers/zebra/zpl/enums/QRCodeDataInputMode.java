/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * QRCodeDataInputMode.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * 31-Jan-2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.zpl.enums;

/**
 * <ul>
 * <li>A = Automatic Input (default). Data character string JIS8 unit, Shift
 * JIS. When the input mode is Automatic Input, the binary codes of 0x80 to 0x9F
 * and 0xE0 to 0xFF cannot be set.</li>
 * <li>M = Manual Input</li>
 * </ul>
 * Two types of data input mode exist: Automatic (A) and Manual (M). If A is
 * specified, the character mode does not need to be specified. If M is
 * specified, the character mode must be specified.
 * 
 * @author Venkaiah Chowdary Koneru
 */
public enum QRCodeDataInputMode {
    A, M;
}
