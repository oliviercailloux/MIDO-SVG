package com.github.cocolollipop.mido_svg.university.components;
import java.util.List;

public class Tag {
	private String name; // Name of the tag
	private List<Subject> listOfSubjects;
	public Tag(String name, List<Subject> listOfSubjects) {
		this.name = name;
		this.listOfSubjects = listOfSubjects;
	} 
	
	
}
