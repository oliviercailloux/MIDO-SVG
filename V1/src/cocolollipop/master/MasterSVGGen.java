package cocolollipop.master;

import java.awt.Color;
import java.awt.Dimension;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

/**
 * Based on https://xmlgraphics.apache.org/batik/using/svg-generator.html (with
 * minor modifications).
 *
 */

public class MasterSVGGen {

	public static void main(String[] args) throws Exception {
		MasterSVGGen test = new MasterSVGGen();
		test.paint();
	}

	public void paint() throws IOException, ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		// Get a DOMImplementation.
		DOMImplementation domImpl = db.getDOMImplementation();

		// Create an instance of org.w3c.dom.Document.
		String svgNS = "http://www.w3.org/2000/svg";
		Document document = domImpl.createDocument(svgNS, "svg", null);

		// Create an instance of the SVG Generator.
		SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(document);
		ctx.setEmbeddedFontsOn(true);
		SVGGraphics2D g = new SVGGraphics2D(ctx, false);

		// Ask the test to render into the SVG Graphics2D implementation.
		g.setPaint(Color.black);
		int dimX = 15000;
		int dimY = 15000;
		g.setSVGCanvasSize(new Dimension(dimX, dimY));
		g.drawString("MIDO", 300, 20);
		g.drawString("Masters", 300, 50);

		/**
		 * Part INFO- ORG
		 */
		g.drawString("INFO-ORG", 100, 75);

		ArrayList<String> io_master = new ArrayList<String>();
		io_master.add("M1");
		io_master.add("M2");
		ArrayList<String> io_m1_fil = new ArrayList<String>();
		io_m1_fil.add("MIAGE classique");
		io_m1_fil.add("MIAGE apprentissage");
		io_m1_fil.add("Informatique de décision");

		ArrayList<String> io_m2_fil = new ArrayList<String>();
		io_m2_fil.add("MODO");
		io_m2_fil.add("ISI");

		ArrayList<String> io_m2_isi_fil = new ArrayList<String>();
		io_m2_isi_fil.add("MIAGE-IF");
		io_m2_isi_fil.add("MIAGE-STIN");
		io_m2_isi_fil.add("MIAGE-ID");

		for (int i = 0; i < io_master.size(); i++) {
			g.drawString(io_master.get(i), 30, 180 + i * 300);
		}

		for (int i = 0; i < io_m1_fil.size(); i++) {
			g.drawString(io_m1_fil.get(i), 60, 150 + i * 30);
			g.drawLine(50, 180, 60, 150 + i * 30);
		}
		for (int i = 0; i < io_m2_fil.size(); i++) {
			g.drawString(io_m2_fil.get(i), 60, 450 + i * 60);
			g.drawLine(50, 480, 60, 450 + i * 60);
			
		}

		for (int i = 0; i < io_m2_isi_fil.size(); i++) {
			g.drawString(io_m2_isi_fil.get(i), 175, 485 + i * 30);
			g.drawString("Apprentissage", 300, 485 + i * 30);
			g.drawLine(185, 485 + i * 30, 310, 485 + i * 30);
			g.drawLine(80,510, 175, 485 + i * 30);
		}

		/**
		 * Part MATHS-APP
		 */
		g.drawString("MATHS-APP", 500, 75);
		ArrayList<String> ma_master = new ArrayList<String>();
		ma_master.add("M1");
		ma_master.add("M2");
		ArrayList<String> ma_m1_fil = new ArrayList<String>();
		ma_m1_fil.add("Actuariat");
		ma_m1_fil.add("Maths approfondies");
		ma_m1_fil.add("Méth maths en éco");

		ArrayList<String> ma_m2_fil = new ArrayList<String>();
		ma_m2_fil.add("Actuariat");
		ma_m2_fil.add("etc");

		for (int i = 0; i < ma_master.size(); i++) {
			g.drawString(ma_master.get(i), 500, 180 + i * 300);
		}

		for (int i = 0; i < ma_m1_fil.size(); i++) {
			g.drawString(ma_m1_fil.get(i), 600, 150 + i * 30);
			g.drawLine(510, 180, 600, 150 + i * 30);
		}

		for (int i = 0; i < ma_m2_fil.size(); i++) {
			g.drawString(ma_m2_fil.get(i), 600, 450 + i * 60);
			g.drawLine(510, 480,600, 450 + i * 60);
		}

		// Finally, stream out SVG using UTF-8 encoding.
		boolean useCSS = true; // we want to use CSS style attributes
		try (Writer out = new OutputStreamWriter(new FileOutputStream("outMaster.svg"), "UTF-8")) {
			g.stream(out, useCSS);
		}
	}
}