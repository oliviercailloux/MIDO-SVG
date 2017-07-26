package com.github.cocolollipop.mido_svg.university.components;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.cocolollipop.mido_svg.xml.jaxb.model.Tag;

/**
 * This class is corresponding to the subject students could attend
 *
 */
public class Subject {

	private double credit;

	private Formation level;

	private List<Subject> listOfPrerequisites;

	private Point point;

	private Teacher responsible;

	private Set<Tag> tags; // we are using a set to avoid duplicate entry

	private String title;

	public Subject(String title, double courseCredit) {
		this.responsible = new Teacher();
		this.tags = new HashSet<>();
		this.title = title;
		this.credit = courseCredit;
		this.point = new Point();
		this.point.setLocation(0, 0);
		this.listOfPrerequisites = new ArrayList<>();
	}

	public Subject(String title, Teacher responsible, int credit, int x, int y) {
		if (responsible == null) {
			this.responsible = new Teacher();
		}
		this.tags = new HashSet<>();
		this.title = title;
		this.responsible = responsible;
		this.credit = credit;
		this.point = new Point();
		this.point.setLocation(x, y);
		this.listOfPrerequisites = new ArrayList<>();
	}

	public void addListOfPrerequisites(Subject s) {
		this.listOfPrerequisites.add(s);
	}

	/**
	 * This subroutine is used to add a tag
	 *
	 * @param tag
	 */
	public void addTag(Tag tag) {
		this.tags.add(tag);

	}

	public double getCredit() {
		return credit;
	}

	public Formation getLevel() {
		return level;
	}

	public List<Subject> getListOfPrerequisites() {
		return listOfPrerequisites;
	}

	public Point getPoint() {
		if (this.point == null) {
			this.point.setLocation(0, 0);
		}
		return point;
	}

	public Teacher getResponsible() {
		return responsible;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public String getTitle() {
		return title;
	}

	public boolean hasPrerequisites() {
		if (this.getListOfPrerequisites().size() > 0) {
			return true;
		}
		return false;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public void setLevel(Formation level) {
		this.level = level;
	}

	public void setListOfPrerequisites(List<Subject> listOfPrerequisites) {
		this.listOfPrerequisites = listOfPrerequisites;
	}

	public void setPosX(int i) {
		this.point.setLocation(i, this.getPoint().y);

	}

	public void setPosY(int i) {
		this.point.setLocation(this.getPoint().x, i);

	}

	public void setResponsible(Teacher responsible) {
		this.responsible = responsible;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
