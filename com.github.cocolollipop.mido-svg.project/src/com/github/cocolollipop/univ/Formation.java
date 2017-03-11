package com.github.cocolollipop.univ;

import java.util.ArrayList;

public abstract class Formation {
	// La classe mere qui represente une formation mais qui est abstraite : Soit on est en Licence, soit en Master
	protected char nomFormation; // Soit L soit M De L3 par ex 
	protected String intitule; // Ce sera Math ; Maths Info ; Math Eco
	protected int niveau; // Ann�e en gros L1 L2 L3 M1 M2
	protected String admisssion; // Type d'admission : Sur dossier ou de droit gr�ce � validation ann�e n-1
	protected ArrayList<Formation> listeFormationAccessibles; // Contient la liste des formations quel l'on peut faire apr�s la formation actuelle
	
	
	
	
}	
	
