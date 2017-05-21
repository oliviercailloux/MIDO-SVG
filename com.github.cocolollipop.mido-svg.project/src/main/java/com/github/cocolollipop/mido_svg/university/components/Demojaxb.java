package com.github.cocolollipop.mido_svg.university.components;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.github.cocolollipop.mido_svg.model.DataBase;
public class Demojaxb {

	public static void main(String[] args) {
		JAXBContext jc = JAXBContext.newInstance(Customer.class);
		
		// On va récuperer les données
		DataBase data = new DataBase(); 
		
		
        Tag monTag = new Tag("Algo",data.getSubjects());
 
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        // Maintenant on a affiche juste dans le syso ; mais on peut très bien ecrire dans src/ressources/tag par ex
        marshaller.marshal(customer, System.out);
	}

}
