
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

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
public class TestSVGGen {

	public static void main(String[] args) throws Exception {
		TestSVGGen test = new TestSVGGen();
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
		g.setSVGCanvasSize(new Dimension(1000, 1000));
		g.drawString("MIDO", 500, 20);
		g.drawString("Licenses", 500, 50);
		g.drawString("Sur dossier, bac S toutes spé", 100, 60);
		
		int nbMasters = 3;
				
		for(int i = 0; i<nbMasters; i++){
			g.drawString("Master n°" + i, 300 + i * 100, 180);
			
		}
		
		
		
		for(int i = 0; i<nbMasters; i++){
			g.drawString("Doctorat n°" + i, 300 + i * 100, 300);
		}
		
		g.fill(new Rectangle(10, 10, 100, 100));
//		
//        g.drawLine(60, 60, 100, 100);
//        g.drawLine(100, 80, 100, 100);
//        g.drawLine(80, 100, 100, 100);
//        g.drawString("Licence 2", 110, 110);

		// Finally, stream out SVG using UTF-8 encoding.
		boolean useCSS = true; // we want to use CSS style attributes
		try (Writer out = new OutputStreamWriter(new FileOutputStream("out.svg"), "UTF-8")) {
			g.stream(out, useCSS);
		}
	}
}