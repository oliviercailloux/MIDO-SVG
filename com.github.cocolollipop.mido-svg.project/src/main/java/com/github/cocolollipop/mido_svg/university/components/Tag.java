package com.github.cocolollipop.mido_svg.university.components;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class Tag {
	@XmlAttribute
	private String name; // Name of the tag
	@XmlElement(name="subject")
	private List<Subject> subjects;
	
	
	public Tag(String name, List<Subject> subjects) {
		this.name = name;
		this.subjects = subjects;
	} 
	
	
}
