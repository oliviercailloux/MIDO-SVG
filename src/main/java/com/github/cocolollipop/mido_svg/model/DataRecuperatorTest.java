package com.github.cocolollipop.mido_svg.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DataRecuperatorTest {

	@Test
	public void test() {
		List<Subject> list = getSubjects("FRUAI0750736TPRCP1ALUEC1");
		Assertions.assertEquals(list.size(), 2);
		
		Iterator<Subject> l = list.iterator();
		
		Subject s1 = l.next();
		Assertions.assertEquals("Introduction à la microéconomie", s1.getTitle());
		Assertions.assertEquals(4, s1.getCredit());
		Assertions.assertEquals("TAN", s1.getResponsible().getLastName());
		
		Subject s2 = l.next();
		Assertions.assertEquals("Anglais 1", s2.getTitle());
		Assertions.assertEquals(2, s2.getCredit());
		Assertions.assertEquals("TAN", s2.getResponsible().getLastName());

	}

}
