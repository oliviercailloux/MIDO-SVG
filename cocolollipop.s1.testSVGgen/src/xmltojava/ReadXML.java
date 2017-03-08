package xmltojava;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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

		/*
		 * Now we are going to create the Enseignant object and add set some attributes, thanks to the parser
		 */
		Element root = myDocument.getDocumentElement();
		NodeList childNodesList = root.getChildNodes();
		int nbRootNodesList = childNodesList.getLength();
		
		// We show node Root Name !
		System.out.println(root.getNodeName());
		
		// We show 
		for (int i = 0; i<nbRootNodesList; i++) {
		    if(childNodesList.item(i).getNodeType() == Node.ELEMENT_NODE) {
		         Node person = childNodesList.item(i);
		        System.out.println(person.getNodeName());
		    }				
		}

		
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
