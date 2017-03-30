package com.github.cocolollipop.univ;

import java.util.ArrayList;
/**
 * This class is corresponding to the Department of the formation
 *
 */
public class Department {
	//name of the department (for example: "MIDO", "LSO" etc.)
	private String title; 
	// list of the formations
	protected ArrayList<Formation> listOfFormations;
	//coordinate X 
	protected int posX;
	//coordinate Y
	protected int posY;
	
	public void setTitle(String title) {
		this.title = title;
	}	
	public String getNomDepartement() {
		return title;
	}
	
	public int getX() {
		return posX;
	}
	public void setX(int x) {
		this.posX = x;
	}
	public int getY() {
		return posY;
	}
	public void setY(int y) {
		this.posY = y;
	}
	
	public Department(String name, int x, int y){
		this.title = name;
		this.posX = x;
		this.posY = y;

	}
}

