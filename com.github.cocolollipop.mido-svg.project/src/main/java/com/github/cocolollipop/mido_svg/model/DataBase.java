package com.github.cocolollipop.mido_svg.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.github.cocolollipop.mido_svg.paper.Paper;
import com.github.cocolollipop.mido_svg.svg_generator.Settings;
import com.github.cocolollipop.mido_svg.university.components.Department;
import com.github.cocolollipop.mido_svg.university.components.Formation;
import com.github.cocolollipop.mido_svg.university.components.Licence;
import com.github.cocolollipop.mido_svg.university.components.Master;
import com.github.cocolollipop.mido_svg.university.components.Subject;
import com.github.cocolollipop.mido_svg.university.components.Teacher;

/**
 * This class is the current database of the application
 *
 */
public class DataBase {

	private Map<String, Teacher> teachers;
	// private Map<String, Subject> subjects;
	private List<Subject> subjects;
	private List<Formation> formations;
	private List<String> tags;
	private Department department;
	private Paper format;
	private Settings settings;
	private Map<String, Formation> formationsMap;

	public DataBase() throws IOException {
		this.teachers = new HashMap<String, Teacher>();
		initTeachers();
		this.formationsMap = new HashMap<String, Formation>();
		this.formations = new LinkedList<Formation>();
		this.subjects = new ArrayList<Subject>();
		this.tags = new ArrayList<>();

		// this.subjects = new HashMap<String, Subject>();
		try {
			initFormations();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		initSubjects();

		// InitFormTOSub();

		// default settings
		this.settings = new Settings(true, false, true, true, true, true, true, "A3");
		// this.settings = new Settings(false, false, false, false, false,
		// false, false, 5000, 5000);

		// initFormations();
		// this.format = new Paper();
		// this.format = new Paper(5000, 5000);
		initFormat();
		initDepartment();
		FillSubjectListInFormation();

	}

	///////////// INITIALIZE///////////////
	/**
	 * Initialize Canvas
	 */
	public void initFormat() {
		this.format = Paper.Paper(settings.getFormat(), this.settings.getWidth(), this.settings.getHeight());
	}

	/**
	 * Initialize teachers
	 */
	public void initTeachers() {

		Teacher Mayag = new Teacher("Mayag", "Brice ", 150, 70);
		Teacher Pigozzi = new Teacher("Pigozzi", "Gabriella ", 650, 70);
		Teacher Cailloux = new Teacher("Cailloux", "Olivier ", 150, 150);
		this.teachers.put("Cailloux", Cailloux);
		this.teachers.put("Pigozzi", Pigozzi);
		this.teachers.put("Mayag", Mayag);

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
	 * Initialize subjects
	 */
	public void initSubjects() {
		Subject proba = new Subject("Probabilités et Statistiques", teachers.get("Mayag"), 3, 350, 70);
		Subject java = new Subject("POO Java", teachers.get("Cailloux"), 3, 350, 85);
		Subject logique = new Subject("Logique", teachers.get("Pigozzi"), 3, 350, 70);
		Subject math = new Subject("Mathematiques", teachers.get("Pigozzi"), 3, 0, 0);

		// subjects.put("proba", proba);
		// subjects.put("java", java);
		// subjects.put("logique", logique);

		logique.setLevel(formationsMap.get("M1MIAGE"));
		math.setLevel(formationsMap.get("L3MIAGEApp"));
		proba.setLevel(formationsMap.get("L3MIAGE"));
		java.setLevel(formationsMap.get("L3MIAGE"));

		subjects.add(proba);
		subjects.add(java);
		subjects.add(logique);
		subjects.add(math);

		// Add prerequisites of each subject

		logique.addListOfPrerequisites(proba); // It means that logique has
												// proba as prerequisites

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
		L3MIAGE.setTeacher(teachers.get("Mayag"));
		L3MIAGE.setFullNameWithLink(
				"http://formations.dauphine.fr/offre/fr-FR/fiche/A3INF/programme//#FRUAI0750736TPRPRA3INF");
		L3MIAGE.setAdmisssion("selection sur dossier");

		// L3MIAGEApp
		Licence L3MIAGEApp = new Licence("L3 MIAGE App", 3, 750, 70);
		L3MIAGEApp.setTeacher(teachers.get("Pigozzi"));
		L3MIAGEApp.setFullNameWithLink(
				"http://formations.dauphine.fr/offre/fr-FR/fiche/A3INF/programme//#FRUAI0750736TPRPRA3INFAPP");
		L3MIAGEApp.setAdmisssion("selection sur entretien");

		// M1MIAGE
		Master M1MIAGE = new Master("M1 MIAGE", 4, 250, 150);
		M1MIAGE.setTeacher(teachers.get("Cailloux"));
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
		this.formations = new LinkedList<Formation>();
		this.formations.add(L3MIAGE);
		this.formations.add(L3MIAGEApp);
		this.formations.add(M1MIAGE);
		this.formations.add(M1MIAGEApp);
		this.formations.add(M2MIAGEIF);
		this.formations.add(M2MIAGEID);
		this.formations.add(M2MIAGESTIN);
		this.formations.add(M2MIAGEIFApp);
		this.formations.add(M2MIAGEIDApp);
		this.formations.add(M2MIAGESTINApp);

		// Available formation
		L3MIAGE.addAvailableFormation(M1MIAGE);
		L3MIAGEApp.addAvailableFormation(M1MIAGEApp);
		M1MIAGE.addAvailableFormation(M2MIAGESTIN);
		M1MIAGE.addAvailableFormation(M2MIAGEIF);
		M1MIAGE.addAvailableFormation(M2MIAGEID);
		M1MIAGEApp.addAvailableFormation(M2MIAGESTINApp);
		M1MIAGEApp.addAvailableFormation(M2MIAGEIDApp);
		M1MIAGEApp.addAvailableFormation(M2MIAGEIFApp);

		this.formationsMap.put("L3MIAGE", L3MIAGE);
		this.formationsMap.put("M1MIAGE", M1MIAGE);
		this.formationsMap.put("L3MIAGEApp", L3MIAGEApp);
		this.formationsMap.put("M1MIAGEApp", M1MIAGEApp);

		// Fill the M2MIAGEID list of tags
		L3MIAGE.setTagsList("src/main/resources/tags/L3MIAGE.txt");
		M2MIAGEIF.setTagsList("src/main/resources/tags/M2MIAGEIF.txt");
		M2MIAGEID.setTagsList("src/main/resources/tags/M2MIAGEID.txt");
		M2MIAGESTIN.setTagsList("src/main/resources/tags/M2MIAGESTIN.txt");
		this.tags.addAll(Arrays.asList(L3MIAGE.getTagslist()));
		this.tags.addAll(Arrays.asList(M2MIAGEIF.getTagslist()));
		this.tags.addAll(Arrays.asList(M2MIAGEID.getTagslist()));
		this.tags.addAll(Arrays.asList(M2MIAGESTIN.getTagslist()));
	}

	/**
	 * This function fills the subjects list of each formation with subjects
	 * assigned to this formation
	 * 
	 */
	public void FillSubjectListInFormation() {

		(formationsMap.get("M1MIAGE")).fillsubjectList(subjects);
		(formationsMap.get("L3MIAGEApp")).fillsubjectList(subjects);
		(formationsMap.get("M1MIAGEApp")).fillsubjectList(subjects);
		(formationsMap.get("L3MIAGE")).fillsubjectList(subjects);

	}

	//////////////// GETTERS AND SETTERS /////////////

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		if (this.formations == null) {
			this.formations = new LinkedList<Formation>();
		}
		this.formations = formations;
	}

	public Map<String, Teacher> getTeachersByName() {
		return teachers;
	}

	public void setTeachers(Map<String, Teacher> teachers) {
		if (this.teachers == null) {
			this.teachers = new HashMap<String, Teacher>();
		}
		this.teachers = teachers;
	}

	/*
	 * public Map<String, Subject> getSubjects() { return subjects; }
	 * 
	 * /* public void setSubjects(Map<String, Subject> subjects) { if
	 * (this.subjects == null) { this.subjects = new HashMap<String, Subject>();
	 * } this.subjects = subjects; }
	 */

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		if (this.department == null) {
			this.department = new Department();
		}
		this.department = department;
	}

	public Paper getFormat() {
		return format;
	}

	public void setFormat(Paper format) {
		if (this.format == null) {
			this.format = new Paper();
		}
		this.format = format;
	}
	///////// END GETTERS AND SETTERS //////

	/////////// MANAGE////////////
	/**
	 * To have the number of child of each formation
	 */
	public void nbOfChildOfEachFormation() {

		for (int i = 0; i < this.getFormations().size(); i++) {
			System.out.println(
					"Pour l\'annee" + this.getFormations().get(i).getGrade() + this.getFormations().get(i).getFullName()
							+ " a " + this.getFormations().get(i).getAvailableFormations().size());

			if (this.getFormations().get(i).getAvailableFormations().size() == 0) {
				System.out.println("Pas de formation accessible");
			}
			for (int j = 0; j < this.getFormations().get(i).getAvailableFormations().size(); j++) {
				System.out.println("Les formations accessibles sont:"
						+ this.getFormations().get(i).getAvailableFormations().get(j).getFullName());
			}
		}
	}

	public List<String> getTags() {
		return this.tags;

	}

}
