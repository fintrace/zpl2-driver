package com.finium.core.drivers.zebra.zpl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.finium.core.drivers.zebra.zpl.support.ZplUtils;

/**
 * Unit test for simple App.
 */
public class ZebraUtilsTest {

    /**
     * Test with only label without element
     */
    @Test
    public void testZebraLabelAlone() {

	assertEquals("^XA", ZplUtils.zplCommand("XA").toString());
	assertEquals("^FT5,6", ZplUtils.zplCommand("FT", 5, 6).toString());
	assertEquals("^FT5,,6", ZplUtils.zplCommand("FT", 5, null, 6).toString());

	assertEquals("^XA\n", ZplUtils.zplCommandSautLigne("XA").toString());
	assertEquals("^FT5,6\n", ZplUtils.zplCommandSautLigne("FT", 5, 6).toString());
	assertEquals("^FT5,,6\n", ZplUtils.zplCommandSautLigne("FT", 5, null, 6).toString());
    }
}
