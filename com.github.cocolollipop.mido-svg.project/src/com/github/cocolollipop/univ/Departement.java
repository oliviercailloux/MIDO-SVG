package com.github.cocolollipop.univ;

import java.util.ArrayList;

public class Departement {
	private String nomDepartement;
	protected ArrayList<Formation> listeFormationsDepartement; // Contient la liste des formations du departement
	protected int x;
	protected int y;
	
	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}	
	public String getNomDepartement() {
		return nomDepartement;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Departement(String name, int x, int y){
		this.nomDepartement = name;
		this.x = x;
		this.y = y;

	}
}

