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

	// grade is corresponding to the year
	protected char grade;

	// Application type (for example: selection based on student records )
	protected String admisssion;
	// List of formations you could apply for after the current year
	protected ArrayList<Formation> listOfFormationsAvailable;

	protected int posX;
	protected int posY;

	protected Formation child;

	public char getNomFormation() {
		return title;
	}

	public void setNomFormation(char nomFormation) {
		this.title = nomFormation;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public char getNiveau() {
		return grade;
	}

	public void setNiveau(char niveau) {
		this.grade = niveau;
	}

	public String getAdmisssion() {
		return admisssion;
	}

	public void setAdmisssion(String admisssion) {
		this.admisssion = admisssion;
	}

	public ArrayList<Formation> getListeFormationAccessibles() {
		return listOfFormationsAvailable;
	}

	public void setListOfFormationAvailable(ArrayList<Formation> listOfFormationAvailable) {
		this.listOfFormationsAvailable = listOfFormationAvailable;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Formation(String name, int x, int y) {
		this.fullName = name;
		this.posX = x;
		this.posY = y;

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
