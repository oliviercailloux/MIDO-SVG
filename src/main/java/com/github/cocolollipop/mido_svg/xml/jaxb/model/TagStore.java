package com.github.cocolollipop.mido_svg.xml.jaxb.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is used to store the tags
 *
 * @author Cocolollipop
 *
 */
@XmlRootElement(namespace = "com.github.cocolollipop.mido_svg.xml.jaxb.model")
public class TagStore {

	private String name;

	// XmLElementWrapper generates a wrapper element around XML representation
	@XmlElementWrapper(name = "tagList")
	// XmlElement sets the name of the entities
	@XmlElement(name = "tag")
	private List<Tag> tagList = new ArrayList<>();

	public String getName() {
		return name;
	}

	public List<Tag> getTagsList() {
		return tagList;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}

}