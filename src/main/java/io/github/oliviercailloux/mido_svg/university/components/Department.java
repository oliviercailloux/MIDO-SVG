package io.github.oliviercailloux.mido_svg.university.components;

import java.util.ArrayList;

/**
 * This class is corresponding to the Department of the University Program. It contains : title (the name of
 * departement) and listOfFormations (the university programs available in this Departement)
 *
 */
public class Department {
	
	// name of the department (for example: "MIDO", "LSO" etc.)
	private String title;
	
	// list of the formations
	private ArrayList<UniversityProgram> listOfUniversityPrograms;
	
	public Department() {
		this.title = "";
		this.listOfUniversityPrograms = new ArrayList<>();
	}
	
	public Department(String titre) {
		this.title = titre;
		this.listOfUniversityPrograms = new ArrayList<>();
	}
	
	public Department(String titre, ArrayList<UniversityProgram> liste) {
		this.title = titre;
		this.listOfUniversityPrograms = liste;
	}

	public String getTitle() {
		return title;
	}

	public ArrayList<UniversityProgram> getListOfUniversityPrograms() {
		return listOfUniversityPrograms;
	}

}
