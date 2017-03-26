package com.github.cocolollipop.univ;

import java.util.ArrayList;

public abstract class Formation {
	// La classe mere qui represente une formation mais qui est abstraite : Soit on est en Licence, soit en Master
	protected char nomFormation; // Soit L soit M De L3 par ex 
	protected String intitule; // Ce sera Math ; Maths Info ; Math Eco
	protected char niveau; // Ann�e en gros L1 L2 L3 M1 M2
	protected String admisssion; // Type d'admission : Sur dossier ou de droit gr�ce � validation ann�e n-1
	protected ArrayList<Formation> listeFormationAccessibles; // Contient la liste des formations quel l'on peut faire apr�s la formation actuelle
	protected int posX;
	protected int posY;
	protected String fullName;
	
	protected int x;
	protected int y;
	
	public char getNomFormation() {
		return nomFormation;
	}
	public void setNomFormation(char nomFormation) {
		this.nomFormation = nomFormation;
	}
	public String getIntitule() {
		return this.intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public char getNiveau() {
		return niveau;
	}
	public void setNiveau(char niveau) {
		this.niveau = niveau;
	}
	public String getAdmisssion() {
		return admisssion;
	}
	public void setAdmisssion(String admisssion) {
		this.admisssion = admisssion;
	}
	public ArrayList<Formation> getListeFormationAccessibles() {
		return listeFormationAccessibles;
	}
	public void setListeFormationAccessibles(ArrayList<Formation> listeFormationAccessibles) {
		this.listeFormationAccessibles = listeFormationAccessibles;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	
	
	
	public Formation(String name, int x, int y){
		this.fullName = name;
		this.x = x;
		this.y = y;

	}
	

	protected Formation fils;
	
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
	
