package com.github.cocolollipop.svgGenerator;


	import java.awt.Color;
	import java.awt.Dimension;
import java.io.File;
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
	import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.github.cocolollipop.univ.Enseignant;

	/**
	 * Based on https://xmlgraphics.apache.org/batik/using/svg-generator.html (with
	 * minor modifications).
	 *
	 */

	public class LicenceTracerPlacerTest {

		public static void main(String[] args) throws Exception {
			LicenceSVGGen test = new LicenceSVGGen();
			test.paint();
		}

		public void paint() throws IOException, ParserConfigurationException, SAXException {
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
			
			
			// D'abord on va essayer de récupérer la liste des master
			// Pour cela, on va créer des objets que le parser est censé créer
			// A savoir des elements
			final Document myDocument= db.parse(new File("mastersExample.xml"));

			
			Element root = myDocument.getDocumentElement();
			NodeList childNodesList = root.getChildNodes();
		    for (int j = 0; j<childNodesList.getLength(); j++) {
		        if(childNodesList.item(j).getNodeType() == Node.ELEMENT_NODE) {
		            // Ici on récupère les Noms et tous les trucs intéressant du parser
		        	//Element person = (Element) childNodesList.item(j);
		            //Element name = (Element) person.getElementsByTagName("nom").item(0);
				    //Element surname = (Element) person.getElementsByTagName("prenom").item(0);
		        	
		        	// Ici on complète les données en les rentrant dans les objets
		        	//Enseignant firstEnseignant = new Enseignant();
					//firstEnseignant.setNomEnseignant(name.getTextContent());
					//firstEnseignant.setPrenomEnseignant(surname.getTextContent());
		        	
		        	// Ici on trace : 
		        	
		        	
				    
				    
		        }
			
			
			
			
			
			
			
			
			/*
			 *  Fin proposition
			 */
			ArrayList<String> io_master = new ArrayList<String>();
			io_master.add("M1");
			io_master.add("M2");
			
			
			ArrayList<String> io_m1_fil = new ArrayList<String>();
			io_m1_fil.add("MIAGE classique");
			io_m1_fil.add("MIAGE apprentissage");
			io_m1_fil.add("Informatique de dÃ©cision");

			ArrayList<String> l3_fil = new ArrayList<String>();
			l3_fil.add("Maths appliquees");
			l3_fil.add("Informatique des Organisations");

			ArrayList<String> l3_io_fil = new ArrayList<String>();
			l3_io_fil.add("MIAGE-Apprentissage");
			l3_io_fil.add("MIAGE-DÃ©cision");
			
			ArrayList<String> l3_ma_fil = new ArrayList<String>();
			l3_io_fil.add("Maths-Ã©co");
			l3_io_fil.add("Maths-info");

			for (int i = 0; i < io_master.size(); i++) {
				g.drawString(io_master.get(i), 30, 180 + i * 300);
			}

			for (int i = 0; i < io_m1_fil.size(); i++) {
				g.drawString(io_m1_fil.get(i), 60, 150 + i * 30);
				g.drawLine(50, 180, 60, 150 + i * 30);
			}
			for (int i = 0; i < l3_fil.size(); i++) {
				g.drawString(l3_fil.get(i), 60, 450 + i * 60);
				g.drawLine(50, 480, 60, 450 + i * 60);
				
			}

			for (int i = 0; i < l3_io_fil.size(); i++) {
				g.drawString(l3_io_fil.get(i), 175, 500 + i * 30);
				g.drawLine(80,510, 175, 500 + i * 30);
			}
			
			for (int i = 0; i < l3_ma_fil.size(); i++) {
				g.drawString(l3_ma_fil.get(i), 175, 500 + i * 30);
				g.drawLine(10,450, 175, 30 + i * 30);
				
			}

			// Finally, stream out SVG using UTF-8 encoding.
			boolean useCSS = true; // we want to use CSS style attributes
			try (Writer out = new OutputStreamWriter(new FileOutputStream("outLicence.svg"), "UTF-8")) {
				g.stream(out, useCSS);
			}
		}
	}
	}
