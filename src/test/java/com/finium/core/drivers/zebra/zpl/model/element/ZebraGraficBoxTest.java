package com.finium.core.drivers.zebra.zpl.model.element;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.finium.core.drivers.zebra.model.element.ZebraGraficBox;

public class ZebraGraficBoxTest {

    @Test
    public void testZplOutput() {
	ZebraGraficBox zebraGraficBox = new ZebraGraficBox(10, 10, 50, 760, 3, "B");
	assertEquals("^FO10,10\n^GB50,760,3,B^FS\n", zebraGraficBox.getZplCode());
    }
}
