package com.github.cocolollipop.mido_svg.university.components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.github.cocolollipop.mido_svg.model.DataBase;
import com.github.cocolollipop.mido_svg.xml.jaxb.model.Tag;

public class Demojaxb {

	public static void main(String[] args) throws JAXBException, IOException {
		JAXBContext jc = JAXBContext.newInstance(Tag.class);

		// On va récuperer les données
		DataBase data = new DataBase();

		// On crée une liste de <subject>
		List<Subject> maListe = new ArrayList<Subject>();
		// maListe.add(data.getSubjects().get("logique"));
		// Subject mySubject = new Subject();
		/*
		 * Tag monTag = new Tag("Algo", maListe); // ICI ca renvoie une MAP MAIS
		 * // POURQUOI C'EST SI COMPLIQUE ?
		 * 
		 * Marshaller marshaller = jc.createMarshaller();
		 * marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		 * 
		 * // Maintenant on a affiche juste dans le syso ; mais on peut très
		 * bien // ecrire dans src/ressources/tag par ex
		 * marshaller.marshal(monTag, System.out);
		 */
	}

}
