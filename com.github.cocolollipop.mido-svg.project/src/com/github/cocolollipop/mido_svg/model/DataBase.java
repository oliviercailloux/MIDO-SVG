package com.github.cocolollipop.mido_svg.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import com.github.cocolollipop.mido_svg.paper.Format;
import com.github.cocolollipop.mido_svg.university.components.Department;
import com.github.cocolollipop.mido_svg.university.components.Formation;
import com.github.cocolollipop.mido_svg.university.components.Licence;
import com.github.cocolollipop.mido_svg.university.components.Master;
import com.github.cocolollipop.mido_svg.university.components.Subject;
import com.github.cocolollipop.mido_svg.university.components.Teacher;

public class DataBase {

	private HashMap<String, Teacher> listOfTeachers;
	private HashMap<String, Subject> listOfSubjects;
	private LinkedList<Formation> listOfFormations;
	private ArrayList<String> listOfTags;
	private Department department;
	private Format format;

	public DataBase() {
		this.listOfTeachers = new HashMap<String, Teacher>();
		initTeachers();
		this.listOfSubjects = new HashMap<String, Subject>();
		initSubjects();
		this.listOfFormations = new LinkedList<Formation>();
		this.listOfTags = new ArrayList<>();
		try {
			initFormations();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.format = new Format();
		initFormat();
		initDepartment();

	}

	///////////// INITIALIZE///////////////
	/**
	 * Initialize Canva
	 */
	public void initFormat() {
		this.getFormat().changeFormat("A4");
	}

	/**
	 * Initialize teachers
	 */
	public void initTeachers() {

		Teacher Mayag = new Teacher("Mayag", "Brice ", 150, 70);
		Teacher Pigozzi = new Teacher("Pigozzi", "Gabriella ", 650, 70);
		Teacher Cailloux = new Teacher("Cailloux", "Ollivier ", 150, 150);
		this.listOfTeachers.put("Cailloux", Cailloux);
		this.listOfTeachers.put("Pigozzi", Pigozzi);
		this.listOfTeachers.put("Mayag", Mayag);

	}

	/**
	 * Initialize subjects
	 */
	public void initSubjects() {
		Subject proba = new Subject("Probabilités et Statistiques", listOfTeachers.get("Mayag"), 3, 350, 70);
		Subject java = new Subject("POO Java", listOfTeachers.get("Cailloux"), 3, 350, 85);
		Subject logique = new Subject("Logique", listOfTeachers.get("Pigozzi"), 3, 350, 100);
		listOfSubjects.put("proba", proba);
		listOfSubjects.put("java", java);
		listOfSubjects.put("logique", logique);
	}

	/**
	 * Initialize Department
	 */
	public void initDepartment() {
		Department MIDO = new Department("MIDO", 500, 20);
		MIDO.setTitle("MIDO");
		this.setDepartment(MIDO);
	}

	/**
	 * Initialize formations
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void initFormations() throws IOException {
		// L3MIAGE
		Licence L3MIAGE = new Licence("L3 MIAGE", 3, 250, 70);
		L3MIAGE.setTeacher(listOfTeachers.get("Mayag"));
		L3MIAGE.setFullNameWithLink(
				"http://formations.dauphine.fr/offre/fr-FR/fiche/A3INF/programme//#FRUAI0750736TPRPRA3INF");
		L3MIAGE.setAdmisssion("selection sur dossier");

		// L3MIAGEApp
		Licence L3MIAGEApp = new Licence("L3 MIAGE App", 3, 750, 70);
		L3MIAGEApp.setTeacher(listOfTeachers.get("Pigozzi"));
		L3MIAGEApp.setFullNameWithLink(
				"http://formations.dauphine.fr/offre/fr-FR/fiche/A3INF/programme//#FRUAI0750736TPRPRA3INFAPP");
		L3MIAGEApp.setAdmisssion("selection sur entretien");

		// M1MIAGE
		Master M1MIAGE = new Master("M1 MIAGE", 4, 250, 150);
		M1MIAGE.setTeacher(listOfTeachers.get("Cailloux"));
		M1MIAGE.setFullNameWithLink(
				"http://formations.dauphine.fr/offre/fr-FR/fiche/A5STI/programme//#FRUAI0750736TPRPRA4MIAI");

		Master M1MIAGEApp = new Master("M1 MIAGE App", 4, 750, 150);
		Master M2MIAGEIF = new Master("M2 MIAGE IF", 5, 100, 300);
		Master M2MIAGEID = new Master("M2 MIAGE ID", 5, 250, 300);
		Master M2MIAGESTIN = new Master("M2 MIAGE STIN", 5, 400, 300);
		Master M2MIAGEIFApp = new Master("M2 MIAGE IF App", 5, 600, 300);
		Master M2MIAGEIDApp = new Master("M2 MIAGE ID App", 5, 750, 300);
		Master M2MIAGESTINApp = new Master("M2 MIAGE STIN App", 5, 900, 300);

		// List of formations
		this.listOfFormations = new LinkedList<Formation>();
		this.listOfFormations.add(L3MIAGE);
		this.listOfFormations.add(L3MIAGEApp);
		this.listOfFormations.add(M1MIAGE);
		this.listOfFormations.add(M1MIAGEApp);
		this.listOfFormations.add(M2MIAGEIF);
		this.listOfFormations.add(M2MIAGEID);
		this.listOfFormations.add(M2MIAGESTIN);
		this.listOfFormations.add(M2MIAGEIFApp);
		this.listOfFormations.add(M2MIAGEIDApp);
		this.listOfFormations.add(M2MIAGESTINApp);

		// Available formation
		L3MIAGE.addAvailableFormation(M1MIAGE);
		L3MIAGEApp.addAvailableFormation(M1MIAGEApp);
		M1MIAGE.addAvailableFormation(M2MIAGESTIN);
		M1MIAGE.addAvailableFormation(M2MIAGEIF);
		M1MIAGE.addAvailableFormation(M2MIAGEID);
		M1MIAGEApp.addAvailableFormation(M2MIAGESTINApp);
		M1MIAGEApp.addAvailableFormation(M2MIAGEIDApp);
		M1MIAGEApp.addAvailableFormation(M2MIAGEIFApp);

		// ListOfSubject
		L3MIAGE.addSubjectsOfFormation(listOfSubjects.get("java"));
		L3MIAGE.addSubjectsOfFormation(listOfSubjects.get("logique"));
		L3MIAGE.addSubjectsOfFormation(listOfSubjects.get("proba"));

		// Fill the M2MIAGEID list of tags
		L3MIAGE.setTagsList("L3MIAGE.txt");
		M2MIAGEIF.setTagsList("M2MIAGEIF.txt");
		M2MIAGEID.setTagsList("M2MIAGEID.txt");
		M2MIAGESTIN.setTagsList("M2MIAGESTIN.txt");
		this.listOfTags.addAll(Arrays.asList(L3MIAGE.getTagslist()));
		this.listOfTags.addAll(Arrays.asList(M2MIAGEIF.getTagslist()));
		this.listOfTags.addAll(Arrays.asList(M2MIAGEID.getTagslist()));
		this.listOfTags.addAll(Arrays.asList(M2MIAGESTIN.getTagslist()));
	}

	//////////////// GETTERS AND SETTERS /////////////

	public LinkedList<Formation> getListOfFormations() {
		return listOfFormations;
	}

	public void setListOfFormations(LinkedList<Formation> listOfFormations) {
		this.listOfFormations = listOfFormations;
	}

	public HashMap<String, Teacher> getListOfTeachers() {
		return listOfTeachers;
	}

	public void setListOfTeachers(HashMap<String, Teacher> listOfTeachers) {
		this.listOfTeachers = listOfTeachers;
	}

	public HashMap<String, Subject> getListOfSubjects() {
		return listOfSubjects;
	}

	public void setListOfSubjects(HashMap<String, Subject> listOfSubjects) {
		this.listOfSubjects = listOfSubjects;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}
	///////// END GETTERS AND SETTERS //////

	/////////// MANAGE////////////
	/**
	 * To have the number of child of each formation
	 */
	public void nbOfChildOfEachFormation() {

		for (int i = 0; i < this.getListOfFormations().size(); i++) {
			System.out.println("Pour l\'annee" + this.getListOfFormations().get(i).getGrade()
					+ this.getListOfFormations().get(i).getFullName() + " a "
					+ this.getListOfFormations().get(i).getListOfAvailableFormations().size());

			if (this.getListOfFormations().get(i).getListOfAvailableFormations().size() == 0) {
				System.out.println("Pas de formation accessible");
			}
			for (int j = 0; j < this.getListOfFormations().get(i).getListOfAvailableFormations().size(); j++) {
				System.out.println("Les formations accessibles sont:"
						+ this.getListOfFormations().get(i).getListOfAvailableFormations().get(j).getFullName());
			}
		}
	}

	/**
	 * countFormations count the number of "myYear" in
	 * lesFormations.getFullName()
	 * 
	 * @param someFormations
	 *            is a LinkedList of Formation
	 * @param myYear
	 *            is a year such as "L3" or "M1"
	 * @return an integer or a negative if myYear isn't in the List
	 */
	public int countFormations(LinkedList<Formation> someFormations, String myYear) {
		int nb = 0;
		for (Formation aFormation : someFormations) {
			if (aFormation.getFullName().indexOf(myYear) != -1) {
				nb++;
			}

		}
		return nb;
	}

	public ArrayList<String> getListOfTags() {
		return this.listOfTags;

	}
}
