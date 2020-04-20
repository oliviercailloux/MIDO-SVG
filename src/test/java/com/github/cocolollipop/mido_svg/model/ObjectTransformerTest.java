package com.github.cocolollipop.mido_svg.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Iterator;
import java.util.List;


import com.github.cocolollipop.mido_svg.BddQuerries.Querier;
import com.github.cocolollipop.mido_svg.university.components.Subject;
import com.github.cocolollipop.mido_svg.university.components.Teacher;



class ObjectTransformerTest {
	Querier querier = new Querier();
	
	/**
	 * Tests to see if the method createSubject create correct objects
	 * 
	 * @result to determinate...
	 */
	@Test
	public void createSubject() {
		Course course = querier.getCourse("exemple de course id, pas acces à la bdd pour l'instant");
		Subject subject = createSubject(course);
		
		
		Assertions.assertEquals("nombre de crédit", subject.getCredit());
		Assertions.assertEquals("niveau", subject.getLevel());
		Assertions.assertEquals("prof", subject.getResponsible());
		Assertions.assertEquals("libelé", subject.getTitle());
		// je test avec plusieurs cours ?
		
	}
	
	
	
	/**
	 * Tests to see if the method createTeacher create correct objects
	 * 
	 * @result to determinate...
	 */
	@Test
	public void createTeacher() {
		
		Person personne=  querier.getPerson("je connais pas de personne ID, pas accez à la BDD pour l'instant");
		Teacher teacher= createTeacher(personne);
		
		Assertions.assertEquals("prénom de la personne", teacher.getFirstName());
		Assertions.assertEquals("nom de la personne", teacher.getFirstName());
		// je test avec plusieurs personnes ?
	
	}

}
