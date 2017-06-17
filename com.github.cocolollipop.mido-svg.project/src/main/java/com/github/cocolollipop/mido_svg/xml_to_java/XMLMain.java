package com.github.cocolollipop.mido_svg.xml_to_java;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLMain {
    public static void main(final String[] args) throws ParserConfigurationException, SAXException, IOException {
    	// D'abord on pointe sur l'URL
    			URL myXML = new URL("https://raw.githubusercontent.com/oliviercailloux/projets/master/Voeux/OF_MEA5STI.xml");

    			// Ensuite on parse
    			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    			Document document = dBuilder.parse(myXML.openStream());

    			// si on veut récupérer par l'intermédiaire de JAX-RS
    			// Client client = ClientBuilder.newClient();
    			// On le normalise pour pas avoir de soucis ; cf
    			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
    			document.getDocumentElement().normalize();
			
	    //Affiche du prologue
	    System.out.println("*************PROLOGUE************");
	    System.out.println("version : " + document.getXmlVersion());
	    System.out.println("encodage : " + document.getXmlEncoding());		
            System.out.println("standalone : " + document.getXmlStandalone());
					
	    /*
	     * Etape 4 : récupération de l'Element racine
	     */
	    final Element racine = document.getDocumentElement();
		
	    //Affichage de l'élément racine
	    System.out.println("\n*************RACINE************");
	    System.out.println(racine.getNodeName());
		
	    /*
	     * Etape 5 : récupération des matières
	     */
	    final NodeList racineNoeuds = racine.getChildNodes();
	    final int nbRacineNoeuds = racineNoeuds.getLength();
			
	    for (int i = 0; i<nbRacineNoeuds; i++) {
	        if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
	            final Element subject = (Element) racineNoeuds.item(i);
	            
	            //Looking for subjects
	            if(subject.getNodeName() == "ns3:course"){
	            	if (subject.getChildNodes().item(23).getAttributes().getNamedItem("ECTScredits").getNodeValue() != null){
		            	String courseTitle = subject.getChildNodes().item(5).getFirstChild().getNodeValue();
	            		System.out.println(subject.getChildNodes().item(23).getAttributes().getNamedItem("ECTScredits").getNodeValue());
	            	}
	            	
	            }
				
		    //Affichage d'une personne
//		    System.out.println("\n*************Matieres************");
//		    System.out.println("courseName : " + matiere.getAttribute("courseName"));
			
					
					
	        }				
	    }				
    }
}
