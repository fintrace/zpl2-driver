/*
 * Copyright © 2017, Finium Solutions, All Rights Reserved
 * 
 * TSCTest.java
 * Modification History
 * *************************************************************
 * Date				Author		Comment
 * Feb 04, 2017		Venkaiah Chowdary Koneru		Created
 * *************************************************************
 */
package com.finium.core.drivers.zebra.zpl;

import com.finium.core.drivers.zebra.zpl.support.exceptions.ZebraPrintException;
import com.finium.core.drivers.zebra.zpl.support.exceptions.ZebraPrintNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import javax.print.*;
import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.standard.PrinterName;
import javax.usb.*;
import javax.usb.event.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author Venkaiah Chowdary Koneru
 */
public class TSCTest {
    private short tscVendorId = 0x1203;
    private short tscProductId = 0x0172;

    /**
     * Send the UsbControlIrp to the UsbDevice on the DCP.
     *
     * @param usbDevice     The UsbDevice.
     * @param usbControlIrp The UsbControlIrp.
     * @return If the submission was successful.
     */
    public static boolean sendUsbControlIrp(UsbDevice usbDevice, UsbControlIrp usbControlIrp) {
        try {
            /* This will block until the submission is complete.
             * Note that submissions (except interrupt and bulk in-direction)
			 * will not block indefinitely, they will complete or fail within
			 * a finite amount of time.  See MouseDriver.HidMouseRunnable for more details.
			 */
            usbDevice.syncSubmit(usbControlIrp);
            return true;
        } catch (UsbException uE) {
            /* The exception sould indicate the reason for the failure.
             * For this example, we'll just stop trying.
			 */
            System.out.println("DCP submission failed : " + uE.getMessage());
            return false;
        }
    }

    /**
     * Function to print code Zpl to local zebra(usb)
     *
     * @throws ZebraPrintException if zpl could not be printed
     */
    @Test
    public void printZpl() throws ZebraPrintException, UnsupportedEncodingException {
        String printerName = "TSC_TTP_244_Pro";
        try {

            String tspl = "SIZE 57 mm,37 mm \n" +
                    "GAP 3 mm,0 \n" +
                    "DIRECTION 0 \n" +
                    "SET COUNTER @1 1 \n" +
                    "CLS \n" +
                    "QRCODE 10,10,L,4,A,0,M2,S7,\"An emerging leader in innovative Auto-ID solutions TSC Auto-ID Technology is an emerging leader in the production of innovative, competitively priced thermal bar code label printers. We were the first Taiwan-based manufacturer to supply more than 2 million label printers to the Auto-identification market  a testimony to the world-class quality of our products.We have used our over 20 years of development and manufacturing experience to design and bring to market a wide range of thermal label printers  everything from budget-friendly entry-level printers to high-performance platforms and portable, mobile-labeling devices. Our ISO9001-certified products are known for their rugged, dependable construction and ease of use - two signature features praised by customers worldwide.Our resources are devoted to the continuous development of durable, reliable, affordably priced thermal label printer  <http://www.tscprinters.com/label-printer.php>- backed by industry-leading service and support. Every member of our team, from salespeople and customer service representatives to engineers and production managers, is dedicated to providing trouble-free Auto-ID solutions - day in and day out - to our rapidly growing worldwide customer base.A barcode printer is a computerperipheral <http://en.wikipedia.org/wiki/Peripheral> for printing barcode <http://en.wikipedia.org/wiki/Barcode>labels or tags that can be attached to, or printed directly on, physical objects. Barcode printers are commonly used to label cartons before shipment, or to label retail items with UPCs <http://en.wikipedia.org/wiki/Universal_Product_Code> or EANs <http://en.wikipedia.org/wiki/European_Article_Number>.The most common barcode printers employ one of two different printing technologies. Direct thermal printers <http://en.wikipedia.org/wiki/Thermal_printer> use a printhead to generate heat that causes a chemical reaction in specially designed paper that turns the paper black. Thermal transfer printers <http://en.wikipedia.org/wiki/Thermal_transfer_printer> also use heat, but instead of reacting the paper, the heat melts awaxy <http://en.wikipedia.org/wiki/Wax> or resin substance on a ribbon <http://en.wikipedia.org/wiki/Ribbon> that runs over the label <http://en.wikipedia.org/wiki/Label> or tag material. The heat transfers ink from the ribbon to the paper. Direct thermal printers are generally less expensive, but they produce labels that can become \" \n" +
                    "PRINT 1";

            PrintService psZebra = null;
            String sPrinterName = null;
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);

            for (int i = 0; i < services.length; i++) {
                PrintServiceAttribute attr = services[i].getAttribute(PrinterName.class);
                sPrinterName = ((PrinterName) attr).getValue();
                System.out.println(sPrinterName);
                if (sPrinterName.toLowerCase().contains(printerName.toLowerCase())) {
                    psZebra = services[i];
                    break;
                }
            }

            if (psZebra == null) {
                throw new ZebraPrintNotFoundException("Zebra printer not found : " + printerName);
            }
            DocPrintJob job = psZebra.createPrintJob();

            byte[] by = tspl.getBytes("US-ASCII");
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(by, flavor, null);
            job.print(doc, null);

        } catch (PrintException e) {
            throw new ZebraPrintException("Cannot print label on this printer : " + printerName, e);
        }
    }

    public UsbDevice findDevice(UsbHub hub, short vendorId, short productId) {
        for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
            UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
            if (desc.idVendor() == vendorId && desc.idProduct() == productId) return device;
            if (device.isUsbHub()) {
                device = findDevice((UsbHub) device, vendorId, productId);
                if (device != null) return device;
            }
        }
        return null;
    }

    @Test
    public void print() throws ZebraPrintException, UsbException, InterruptedException {
        //ZebraBarCode128 barcode = new ZebraBarCode128(70, 1000, "0235600703875191516022937128", 190, false, 4, 2);

        String tspl = "SIZE 57 mm,37 mm \n" +
                "GAP 3 mm,0 \n" +
                "DIRECTION 0 \n" +
                "SET COUNTER @1 1 \n" +
                "CLS \n" +
                "QRCODE 10,10,L,4,A,0,M2,S7,\"An emerging leader in innovative Auto-ID solutions TSC Auto-ID Technology is an emerging leader in the production of innovative, competitively priced thermal bar code label printers. We were the first Taiwan-based manufacturer to supply more than 2 million label printers to the Auto-identification market  a testimony to the world-class quality of our products.We have used our over 20 years of development and manufacturing experience to design and bring to market a wide range of thermal label printers  everything from budget-friendly entry-level printers to high-performance platforms and portable, mobile-labeling devices. Our ISO9001-certified products are known for their rugged, dependable construction and ease of use - two signature features praised by customers worldwide.Our resources are devoted to the continuous development of durable, reliable, affordably priced thermal label printer  <http://www.tscprinters.com/label-printer.php>- backed by industry-leading service and support. Every member of our team, from salespeople and customer service representatives to engineers and production managers, is dedicated to providing trouble-free Auto-ID solutions - day in and day out - to our rapidly growing worldwide customer base.A barcode printer is a computerperipheral <http://en.wikipedia.org/wiki/Peripheral> for printing barcode <http://en.wikipedia.org/wiki/Barcode>labels or tags that can be attached to, or printed directly on, physical objects. Barcode printers are commonly used to label cartons before shipment, or to label retail items with UPCs <http://en.wikipedia.org/wiki/Universal_Product_Code> or EANs <http://en.wikipedia.org/wiki/European_Article_Number>.The most common barcode printers employ one of two different printing technologies. Direct thermal printers <http://en.wikipedia.org/wiki/Thermal_printer> use a printhead to generate heat that causes a chemical reaction in specially designed paper that turns the paper black. Thermal transfer printers <http://en.wikipedia.org/wiki/Thermal_transfer_printer> also use heat, but instead of reacting the paper, the heat melts awaxy <http://en.wikipedia.org/wiki/Wax> or resin substance on a ribbon <http://en.wikipedia.org/wiki/Ribbon> that runs over the label <http://en.wikipedia.org/wiki/Label> or tag material. The heat transfers ink from the ribbon to the paper. Direct thermal printers are generally less expensive, but they produce labels that can become \" \n" +
                "PRINT 1";
        //printZpl(tspl, "TSC_TTP_244_Pro");

        // Get the USB services and dump information about them
        final UsbServices services = UsbHostManager.getUsbServices();
        System.out.println("USB Service Implementation: "
                + services.getImpDescription());
        System.out.println("Implementation version: "
                + services.getImpVersion());
        System.out.println("Service API version: " + services.getApiVersion());
        System.out.println();

        // Dump the root USB hub
        UsbDevice usbDevice = findDevice(services.getRootUsbHub(), tscVendorId, tscProductId);
        Assert.assertNotNull(usbDevice);
        usbDevice.addUsbDeviceListener(new UsbDeviceListener() {
            @Override
            public void usbDeviceDetached(UsbDeviceEvent event) {
                System.out.println("Device Detached");
            }

            @Override
            public void errorEventOccurred(UsbDeviceErrorEvent event) {

            }

            @Override
            public void dataEventOccurred(UsbDeviceDataEvent event) {
                try {
                    System.out.println("Data event on listener: " + new String(event.getData(),
                            "US-ASCII"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        UsbConfiguration configuration = usbDevice.getActiveUsbConfiguration();
            UsbInterface iface = configuration.getUsbInterface((byte) 0);
        iface.claim(new UsbInterfacePolicy() {
            @Override
            public boolean forceClaim(UsbInterface usbInterface) {
                return true;
            }
        });

        try {
            List<UsbEndpoint> endpoints = iface.getUsbEndpoints();
            endpoints.forEach((p) -> {
                System.out.println("Direction: " + p.getDirection() + ", Address: " + p.getUsbEndpointDescriptor().bEndpointAddress());
            });

            UsbEndpoint usbWriteEndpoint = iface.getUsbEndpoint((byte) 1);
            UsbPipe writePipe = usbWriteEndpoint.getUsbPipe();
            writePipe.addUsbPipeListener(new UsbPipeListener() {
                @Override
                public void errorEventOccurred(UsbPipeErrorEvent event) {
                    UsbException error = event.getUsbException();
                    error.printStackTrace();
                }

                @Override
                public void dataEventOccurred(UsbPipeDataEvent event) {
                    byte[] data = event.getData();

                    final StringBuilder builder = new StringBuilder();
                    for (byte b : data) {
                        builder.append(String.format("%02x", b));
                    }
                    System.out.println("inside write data event: " + builder.toString());
                }
            });

            UsbEndpoint usbReadEndpoint = iface.getUsbEndpoint((byte) -126);
            UsbPipe readPipe = usbReadEndpoint.getUsbPipe();
            readPipe.addUsbPipeListener(new UsbPipeListener() {
                @Override
                public void errorEventOccurred(UsbPipeErrorEvent event) {
                    UsbException error = event.getUsbException();
                    error.printStackTrace();
                }

                @Override
                public void dataEventOccurred(UsbPipeDataEvent event) {
                    byte[] data = event.getData();

                    final StringBuilder builder = new StringBuilder();
                    for (byte b : data) {
                        builder.append(String.format("%02x", b));
                    }
                    System.out.println("inside read data event: " + builder.toString());
                }
            });

            try {
                byte[] statusBytes = ((char) 27 + "!S").getBytes(Charset.forName("US-ASCII"));

                writePipe.open();
                writePipe.syncSubmit(statusBytes);
                //writePipe.syncSubmit("AUTODETECT".getBytes(Charset.forName("US-ASCII")));
                writePipe.close();
                System.out.println(statusBytes.length + " bytes sent");

                //pipe.syncSubmit("~!T".getBytes(Charset.forName("US-ASCII")));
                //pipe.syncSubmit(new byte[1]);
                writePipe.open();
                writePipe.syncSubmit("SIZE 50 mm,30 mm\n".getBytes(Charset.forName("US-ASCII")));
                writePipe.syncSubmit("GAP 4 mm,0 mm\n".getBytes(Charset.forName("US-ASCII")));
                writePipe.syncSubmit("SPEED 3\n".getBytes(Charset.forName("US-ASCII")));
                //writePipe.syncSubmit("DENSITY 7".getBytes(Charset.forName("US-ASCII")));
                //writePipe.syncSubmit(new byte[] {0x0D});
                writePipe.syncSubmit("DIRECTION 1,0\n".getBytes(Charset.forName("US-ASCII")));
                writePipe.syncSubmit("CLS\n".getBytes(Charset.forName("US-ASCII")));
                //writePipe.syncSubmit("TEXT 0,10,\"3\",0,1,1, \"Encoded data \"\n".getBytes(Charset.forName("US-ASCII")));
                writePipe.syncSubmit("QRCODE 10,10,L,4,A,0,M2,S7,\"http://fintrace.tk?sc=9krPWOrxzsL\"\n".getBytes(Charset.forName("US-ASCII")));
                //writePipe.syncSubmit("TEXT 10,10,\"3\",0,1,1, \"Encoded data: \"".getBytes(Charset.forName("US-ASCII")));
                //writePipe.syncSubmit(new byte[] {0x0D});
                writePipe.syncSubmit("PRINT 1\n".getBytes(Charset.forName("US-ASCII")));
                writePipe.syncSubmit(new byte[]{0x0D, 0x0A});
                writePipe.close();

//                int offset = 0;
//                byte[] payload = tspl.getBytes(Charset.forName("US-ASCII"));
//                int batchSize = 8;
//                for (int multiplier = 1; offset < payload.length; multiplier++) {
//
//                    byte[] batch = offset + batchSize < payload.length ?
//                            Arrays.copyOfRange(payload, offset, offset + batchSize) :
//                            Arrays.copyOfRange(payload, offset, payload.length);
//
//                    pipe.syncSubmit(batch);
//                    offset = multiplier * batchSize;
//                }

                //UsbIrp sent = pipe.asyncSubmit(tspl.getBytes(Charset.forName("US-ASCII")));
                //System.out.println(new String(statusBytes, Charset.forName("US-ASCII")));
            } finally {
                System.out.println("closing write pipe");
                //writePipe.close();
            }
            try {
                readPipe.open();
                readPipe.syncSubmit(new byte[8]);
            } finally {
                System.out.println("closing read pipe");
                readPipe.close();
            }

            //Thread.sleep(20000);
        } finally {
            System.out.println("closing iface");
            iface.release();
        }
    }
}
