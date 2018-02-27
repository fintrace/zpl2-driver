package com.finium.core.drivers.zebra.zpl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.finium.core.drivers.zebra.model.ZebraLabel;

/**
 * Unit test for simple App.
 */
public class ZebraLabelTest {

    /**
     * Test with only label without element
     */
    @Test
    public void testZebraLabelAlone() {
	ZebraLabel zebraLabel = new ZebraLabel();
	assertEquals("^XA\n^MMT\n^XZ\n", zebraLabel.getZplCode());
    }

    /**
     * Test with only label without element
     */
    @Test
    public void testZebraLabelSize() {
	ZebraLabel zebraLabel = new ZebraLabel(500, 760);
	assertEquals("^XA\n^MMT\n^PW500\n^LL760\n^XZ\n", zebraLabel.getZplCode());
    }
}
