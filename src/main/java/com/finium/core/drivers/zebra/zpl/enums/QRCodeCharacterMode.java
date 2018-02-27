/*
 * Copyright © 2016, Finium Sdn Bhd, All Rights Reserved
 * 
 * QRCodeCharacterMode.java
 * Modification History
 * *************************************************************
 * Date			Author		Comment
 * 31-Jan-2016		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.zpl.enums;

/**
 * <ul>
 * <li><b>N</b> = numeric</li>
 * <li><b>A</b> = alphanumeric</li>
 * <li><b>Bxxxx</b> = 8-bit byte mode. This handles the 8-bit Latin/Kana
 * character set in accordance with JIS X 0201 (character values 0x00 to 0xFF).
 * xxxx = number of data characters is represented by two bytes of BCD code.
 * </li>
 * <li><b>K</b> = Kanji — handles only Kanji characters in accordance with the
 * Shift JIS system based on JIS X 0208. This means that all parameters after
 * the character mode K should be 16-bit characters. If there are any 8-bit
 * characters (such as ASCII code), an error occurs.</li>
 * </ul>
 * 
 * @author Venkaiah Chowdary Koneru
 *
 */
public enum QRCodeCharacterMode {
    /**
     * The numeric type is the most efficient way to encode digits.
     * Problematically, the standard make no provisions for encoding negative or
     * fractional numbers. This encoding is better than Alphanumeric, when you
     * only have a list of digits.
     * 
     * To use this encoding, simply specify a string of digits as the data. You
     * can also use a positive integer as the code’s contents.
     */
    N, /**
        * The alphanumeric type is very limited in that it can only encode some
        * ASCII characters. It encodes:
        * 
        * Uppercase letters Digits 0-9 The horizontal space Eight punctuation
        * characters: $, %, *, +, -, ., /, and :
        */
    A, /**
        * This encoding does not change the data in any way. Instead its pure
        * bytes are represented directly in the QR code. This is the least
        * efficient way to store data in a QR code. You should only use this as
        * a last resort.
        */
    BXXX, /**
	   * This mode is used for Kanji characters.
	   */
    K;
}
