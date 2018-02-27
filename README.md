zebra-zpl
=========

This library will help you to quickly generate ZPL code for your Zebra printer.


Hello World
=================

Add dependy in your pom

  	  <dependency>
	      <groupId>com.finium.core.drivers</groupId>
	      <artifactId>zebra-driver</artifactId>
	      <version>1.0.0</version>
	      <scope>compile</scope>
	    </dependency>


First code


		ZebraLabel zebraLabel = new ZebraLabel(912, 912);
		zebraLabel.setDefaultZebraFont(ZebraFont.ZEBRA_ZERO);

		zebraLabel.addElement(new ZebraText(10, 84, "Product:", 14));
		zebraLabel.addElement(new ZebraText(395, 85, "Camera", 14));

		zebraLabel.addElement(new ZebraText(10, 161, "CA201212AA", 14));

		//Add Code Bar 39
		zebraLabel.addElement(new ZebraBarCode39(10, 297, "CA201212AA", 118, 2, 2));

		zebraLabel.addElement(new ZebraText(10, 365, "Qté:", 11));
		zebraLabel.addElement(new ZebraText(180, 365, "3", 11));
		zebraLabel.addElement(new ZebraText(317, 365, "QA", 11));

		zebraLabel.addElement(new ZebraText(10, 520, "Ref log:", 11));
		zebraLabel.addElement(new ZebraText(180, 520, "0035", 11));
		zebraLabel.addElement(new ZebraText(10, 596, "Ref client:", 11));
		zebraLabel.addElement(new ZebraText(180, 599, "1234", 11));

Get Zpl Code

    zebraLabel.getZplCode();
		
Print

    ZebraUtils.printZpl(zebraLabel, ip, port);
  

Native code
=================

If you need to customise your label

zebraLabel.addElement(new ZebraNativeZpl("^KD0\n"));

You can also use usefull fonction ZplUtils.zplCommand to generate a zpl command (with many variables)

  ZplUtils.zplCommand("A", "0", "R");
  //will return ^A,0,R

Other documentation
=================

Documentation about ZPL could be find here
https://www.zebra.com/content/dam/zebra/manuals/en-us/software/zpl-zbi2-pm-en.pdf
