package xmltojava;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ReadXML {
	/**
	 * The aim of this class is to get data form an XML FILE in static mode
	 * In a second time we will create a function which can used in the main program, in order to create
	 * multiple objects such as Enseignant, Matiere, Formation (package univ)
	 * 
	 * We will use a DOM parser
	 * @param args
	 */
	public static void main(String[] args) {
		
		// First we have to create an object DocumentBuilderFactory in order to use the DocumentBuilder
		// then we create the DocumentBuilder
		// And finally we create the document which opens "myFile.xml"
		// the try catch block is mandatory is order to use DocumentBuilder
		// I create 3 catch "blocks" : ParserConfigurationException, SAXException, IOException
		try{
			
		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		final DocumentBuilder db = dbf.newDocumentBuilder();
		
		// MyFile is our XML file which contains data about Maude Manouvrier
		final Document myDocument= db.parse(new File("myFile.xml"));
		}
		catch (final ParserConfigurationException e) {
			System.out.println("Attention, error from DocumentBuilder : parser isn't configured");
		    e.printStackTrace();

		}

		catch (final SAXException e) {
			System.out.println("Attention, error from parser : SAXException");
		    e.printStackTrace();

		}

		catch (final IOException e) {
			System.out.println("Attention, error from parser : IOException");
		    e.printStackTrace();

		}

	}

}
