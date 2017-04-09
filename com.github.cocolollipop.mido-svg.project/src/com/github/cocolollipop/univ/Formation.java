package com.github.cocolollipop.univ;

import java.util.ArrayList;

/**
 * This class correspond to a formation of any kind
 */
public abstract class Formation {

	// cocolollipop: I dont know if we need all of these...I let you comment
	// these ones
	protected char title;
	protected String fullName;
	protected String intitule;
	// this name corresponds to the name with a link ref to the program
	private String fullNameWithLink;
	// grade is corresponding to the year
	protected int grade;

	// Application type (for example: selection based on student records )
	protected String admisssion;
	// List of formations you could apply for after the current year
	protected ArrayList<Formation> listOfAvailableFormations;
	
	protected String tagsList[];

	protected int posX;
	protected int posY;

	protected Formation child;

	public Formation(String name, int grade, int x, int y) {
		this.title = ' ';
		this.intitule = " ";
		this.grade = grade;
		this.listOfAvailableFormations = new ArrayList<Formation>();
		this.fullName = name;
		this.tagsList = new String[]{"", "", "", "", ""};
		this.posX = x;
		this.posY = y;

	}

	public String getFullNameWithLink() {
		if (this.fullNameWithLink == null) {
			return this.fullName;
		}
		return this.fullNameWithLink;
	}

	/**
	 * This is to create a link for a formation. It has to be used with the
	 * method drawString() from Graphics2D
	 * 
	 * @param link
	 */
	public void setFullNameWithLink(String link) {

		this.fullNameWithLink = "<a xlink:href=\"" + link + "\" target=\"_blank\">" + this.getFullName() + "</a>";

	}

	public char getTitle() {
		return title;
	}

	public void setTitle(char nomFormation) {
		this.title = nomFormation;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public int getGrade() {
		return this.grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}

	public String getAdmisssion() {
		return admisssion;
	}

	public void setAdmisssion(String admisssion) {
		this.admisssion = admisssion;
	}

	public ArrayList<Formation> getListOfAvailableFormations() {
		return listOfAvailableFormations;
	}

	public void setListOfAvailableFormations(ArrayList<Formation> listOfAvailableFormations) {
		this.listOfAvailableFormations = listOfAvailableFormations;
	}

	public void addAvailableFormation(Formation formation) {
		this.listOfAvailableFormations.add(formation);
	}
	
	public String[] getTagslist() {
		return tagsList;
	}

	public void setTagsList(String[] tagsList) {
		this.tagsList = tagsList;
	}
	

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getPosX() {

		return this.posX;
	}

	public int getPosY() {

		return this.posY;
	}

	public void setPosX(int i) {
		this.posX = i;

	}

	public void setPosY(int j) {
		this.posY = j;

	}

}
