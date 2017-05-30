package com.github.cocolollipop.mido_svg.university.components;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is corresponding to the subject students could attend
 *
 */
public class Subject {

	private String title;
	private Teacher responsible;
	private int credit;
	private Point point;
	private List<Tag> listOfTags;
	private List<Subject> listOfPrerequisites;

	public Subject(String title, Teacher responsible, int credit, int x, int y) {
		this.title = title;
		this.responsible = responsible;
		this.credit = credit;
		this.point = new Point();
		this.point.setLocation(x, y);
		this.listOfPrerequisites = new ArrayList<Subject>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Teacher getResponsible() {
		return responsible;
	}

	public void setResponsible(Teacher responsible) {
		this.responsible = responsible;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public Point getPoint() {
		if (this.point == null) {
			this.point.setLocation(0, 0);
		}
		return point;
	}

	public void setPosX(int i) {
		this.point.setLocation(i, this.getPoint().y);

	}

	public void setPosY(int i) {
		this.point.setLocation(this.getPoint().x, i);

	}

	public List<Subject> getListOfPrerequisites() {
		return listOfPrerequisites;
	}

	public void setListOfPrerequisites(List<Subject> listOfPrerequisites) {
		this.listOfPrerequisites = listOfPrerequisites;
	}

	public void addListOfPrerequisites(Subject s) {
		this.listOfPrerequisites.add(s);
	}

}
