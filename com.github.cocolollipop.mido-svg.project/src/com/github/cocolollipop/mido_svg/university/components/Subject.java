package com.github.cocolollipop.mido_svg.university.components;

/**
 * This class is corresponding to the subject students could attend
 *
 */
public class Subject {

	private String title;
	private Teacher responsible;
	private int credit;
	private int posX;
	private int posY;

	public Subject(String title, Teacher responsible, int credit, int posX, int posY) {
		this.title = title;
		this.responsible = responsible;
		this.credit = credit;
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int x) {
		this.posX = x;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int y) {
		this.posY = y;
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

}
