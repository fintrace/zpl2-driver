package com.finium.core.drivers.zebra.zpl.model.element;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.finium.core.drivers.zebra.model.element.ZebraBarCode128;

public class ZebraBarCode128Test {

    @Test
    public void testZplOutput() {
	ZebraBarCode128 barcode = new ZebraBarCode128(70, 1000, "0235600703875191516022937128", 190, false, 4, 2);
	assertEquals("^FT70,1000\n^BY4,2,190\n^BCN,190,N,N,N\n^FD0235600703875191516022937128^FS\n",
		barcode.getZplCode());
    }
}
