package com.github.cocolollipop.mido_svg.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.github.cocolollipop.mido_svg.paper.FactoryPaper;
import com.github.cocolollipop.mido_svg.paper.Paper;
import com.github.cocolollipop.mido_svg.svg_generator.Settings;
import com.github.cocolollipop.mido_svg.university.components.Department;
import com.github.cocolollipop.mido_svg.university.components.Formation;
import com.github.cocolollipop.mido_svg.university.components.Licence;
import com.github.cocolollipop.mido_svg.university.components.Master;
import com.github.cocolollipop.mido_svg.university.components.Subject;
import com.github.cocolollipop.mido_svg.university.components.Teacher;
import com.github.cocolollipop.mido_svg.xml.jaxb.model.Tag;

/**
 * This class is the current database of the application
 *
 */

public class DataBase {

	private Department department;

	private List<Formation> formations;

	private Map<String, Formation> formationsMap;

	private Map<String, Subject> mapSubjects; // used for tags

	private Paper paper;

	private Settings settings;

	private List<Subject> subjects;

	private List<String> tags;

	private Map<String, Teacher> teachers;

	private List<String> users;

	public DataBase() {
		// default settings
		this.settings = new Settings(true, true, true, true, true, true, true, "A4");
		this.teachers = new HashMap<>();
		initTeachers();
		this.formationsMap = new HashMap<>();
		this.formations = new LinkedList<>();
		this.subjects = new ArrayList<>();
		this.tags = new ArrayList<>();
		try {
			initFormations();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initUsers();
		initSubjects();
		setPaper(FactoryPaper.TypeFormat.A4, 0, 0);
		initDepartment();
		FillSubjectListInFormation();

	}

	public DataBase(Settings settings) {
		this.teachers = new HashMap<>();
		initTeachers();
		this.formationsMap = new HashMap<>();
		this.formations = new LinkedList<>();
		this.subjects = new ArrayList<>();
		this.tags = new ArrayList<>();
		try {
			initFormations();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initUsers();
		initSubjects();
		this.settings = settings;
		setPaper(this.settings.getFormat(), this.settings.getWidth(), this.settings.getHeight());
		initDepartment();
		FillSubjectListInFormation();

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

	// LOGGERS
	// public static final Logger LOGGER =
	// LoggerFactory.getLogger(DataBase.class);

	public Department getDepartment() {
		return department;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public Paper getPaper() {
		return paper;
	}

	public Map<String, Subject> getSubjects() {
		return mapSubjects;
	}

	public List<String> getTags() {
		return this.tags;

	}

	public Map<String, Teacher> getTeachersByName() {
		return teachers;
	}

	//////////////// GETTERS AND SETTERS /////////////

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
	 */
	public void initFormations() {

		// The equivalent of the function but with the data from de XML file
		/*
		 * XMLMain myTestXMLMain = new XMLMain();
		 * myTestXMLMain.fillFormationsXML(this.formationsMap);
		 */
		
		// L1 MIDO
		//Licence L1MIDO = new Licence("L1 MIDO", 3, 250, 70);
		
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
		M2MIAGEIF.setTeacher(teachers.get("Pigozzi"));
		Master M2MIAGEID = new Master("M2 MIAGE ID", 5, 250, 300);
		Master M2MIAGESTIN = new Master("M2 MIAGE STIN", 5, 400, 300);
		Master M2MIAGEIFApp = new Master("M2 MIAGE IF App", 5, 600, 300);
		Master M2MIAGEIDApp = new Master("M2 MIAGE ID App", 5, 750, 300);
		Master M2MIAGESTINApp = new Master("M2 MIAGE STIN App", 5, 900, 300);

		// List of formations
		this.formations = new LinkedList<>();
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
		//this.formations.add(L1MIDO);

		// Available formation
		L3MIAGE.addAvailableFormation(M1MIAGE);
		L3MIAGEApp.addAvailableFormation(M1MIAGEApp);
		M1MIAGE.addAvailableFormation(M2MIAGESTIN);
		M1MIAGE.addAvailableFormation(M2MIAGEIF);
		M1MIAGE.addAvailableFormation(M2MIAGEID);
		M1MIAGEApp.addAvailableFormation(M2MIAGESTINApp);
		M1MIAGEApp.addAvailableFormation(M2MIAGEIDApp);
		M1MIAGEApp.addAvailableFormation(M2MIAGEIFApp);
		//L1MIDO.addAvailableFormation(L3MIAGE);

		this.formationsMap.put("L3MIAGE", L3MIAGE);
		this.formationsMap.put("M1MIAGE", M1MIAGE);
		this.formationsMap.put("L3MIAGEApp", L3MIAGEApp);
		this.formationsMap.put("M1MIAGEApp", M1MIAGEApp);
		this.formationsMap.put("M2MIAGEIF", M2MIAGEIF);
		//this.formationsMap.put("L1MIDO", L1MIDO);
		
	}

	/**
	 * Initialize subjects
	 */
	public void initSubjects() {
		this.mapSubjects = new HashMap<>();

		// The equivalent of the function but with the data from de XML file
		// XMLMain myTestXMLMain = new XMLMain();
		// myTestXMLMain.fillSubjectsXML(this.mapSubjects);

		Subject proba = new Subject("Probabilités et Statistiques", teachers.get("Mayag"), 3, 350, 70);
		Subject java = new Subject("POO Java", teachers.get("Cailloux"), 3, 350, 85);
		Subject logique = new Subject("Logique", teachers.get("Pigozzi"), 3, 350, 70);
		Subject math = new Subject("Mathematiques", teachers.get("Pigozzi"), 3, 0, 0);
		Subject proba2 = new Subject("Probabilités et Statistiques", teachers.get("Mayag"), 3, 350, 70);
		Subject java2 = new Subject("POO Java", teachers.get("Cailloux"), 3, 500, 85);
		Subject logique2 = new Subject("Logique", teachers.get("Pigozzi"), 3, 350, 70);
		Subject math2 = new Subject("Mathematiques", teachers.get("Pigozzi"), 3, 0, 0);
		
		Subject java3 = new Subject("POO Java", teachers.get("Cailloux"), 3, 350, 85);
		Subject logique3 = new Subject("Logique", teachers.get("Pigozzi"), 3, 350, 70);
		Subject math3 = new Subject("Mathematiques", teachers.get("Pigozzi"), 3, 0, 0);
		Subject proba3 = new Subject("Probabilités et Statistiques", teachers.get("Mayag"), 3, 350, 70);
		Subject java4 = new Subject("POO Java", teachers.get("Cailloux"), 3, 500, 85);
		Subject logique4 = new Subject("Logique", teachers.get("Pigozzi"), 3, 350, 70);
		Subject math4 = new Subject("Mathematiques", teachers.get("Pigozzi"), 3, 0, 0);
		
		
		Subject proba5 = new Subject("Probabilités et Statistiques", teachers.get("Mayag"), 3, 350, 70);
		Subject java5 = new Subject("POO Java", teachers.get("Cailloux"), 3, 500, 85);
		Subject logique5 = new Subject("Logique", teachers.get("Pigozzi"), 3, 350, 70);
		Subject math5 = new Subject("Mathematiques", teachers.get("Pigozzi"), 3, 0, 0);
		Subject math6 = new Subject("Mathematiques", teachers.get("Pigozzi"), 3, 0, 0);
		
		Subject proba6 = new Subject("Probabilités et Statistiques", teachers.get("Mayag"), 3, 350, 70);
		Subject java6 = new Subject("POO Java", teachers.get("Cailloux"), 3, 500, 85);
		Subject logique6 = new Subject("Logique", teachers.get("Pigozzi"), 3, 350, 70);
		

		mapSubjects.put("proba", proba);
		mapSubjects.put("java", java);
		mapSubjects.put("logique", logique);
		mapSubjects.put("math",  math);
		mapSubjects.put("proba2", proba2);
		mapSubjects.put("java2", java2);
		mapSubjects.put("logique2", logique2);
		mapSubjects.put("math2",  math2);
		mapSubjects.put("java3",  java3);
		mapSubjects.put("logique3",  logique3);
		mapSubjects.put("math3",  math3);
		mapSubjects.put("proba3",  proba3);
		mapSubjects.put("java4",  java4);
		mapSubjects.put("logique4",  logique4);
		mapSubjects.put("math4",  math4);
		
		mapSubjects.put("proba5",  proba5);
		mapSubjects.put("java5",  java5);
		mapSubjects.put("logique5",  logique5);
		mapSubjects.put("math5",  math5);
		mapSubjects.put("math6",  math6);
		mapSubjects.put("proba6",  proba6);
		mapSubjects.put("java6",  java6);
		mapSubjects.put("logique6",  logique6);
		

		logique.setLevel(formationsMap.get("M1MIAGE"));
		math.setLevel(formationsMap.get("L3MIAGEApp"));
		proba.setLevel(formationsMap.get("L3MIAGE"));
		java.setLevel(formationsMap.get("L3MIAGE"));
		
		logique2.setLevel(formationsMap.get("L3MIAGE"));
		math2.setLevel(formationsMap.get("L3MIAGE"));
		proba2.setLevel(formationsMap.get("L3MIAGE"));
		java2.setLevel(formationsMap.get("L3MIAGE"));
		
		java3.setLevel(formationsMap.get("L3MIAGE"));
		logique3.setLevel(formationsMap.get("L3MIAGE"));
		math3.setLevel(formationsMap.get("L3MIAGE"));
		proba3.setLevel(formationsMap.get("L3MIAGE"));
		java4.setLevel(formationsMap.get("L3MIAGE"));
		logique4.setLevel(formationsMap.get("L3MIAGE"));
		math4.setLevel(formationsMap.get("L3MIAGE"));
		
		
		proba5.setLevel(formationsMap.get("M1MIAGE"));
		java5.setLevel(formationsMap.get("M1MIAGE"));
		logique5.setLevel(formationsMap.get("M1MIAGE"));
		math5.setLevel(formationsMap.get("M1MIAGE"));
		math6.setLevel(formationsMap.get("M2MIAGEIF"));
		
		proba6.setLevel(formationsMap.get("M2MIAGEIF"));
		java6.setLevel(formationsMap.get("M2MIAGEIF"));
		logique6.setLevel(formationsMap.get("M2MIAGEIF"));
		
		
		subjects.add(proba);
		subjects.add(java);
		subjects.add(logique);
		subjects.add(math);
		
		subjects.add(proba2);
		subjects.add(java2);
		subjects.add(logique2);
		subjects.add(math2);
		
		subjects.add(java3);
		subjects.add(logique3);
		subjects.add(math3);
		subjects.add(proba3);
		subjects.add(java4);
		subjects.add(logique4);
		subjects.add(math4);
		
		subjects.add(proba5);
		subjects.add(java5);
		subjects.add(logique5);
		
		subjects.add(math5);
		subjects.add(math6);
		
		subjects.add(proba6);
		subjects.add(java6);
		subjects.add(logique6);


		// Add prerequisites of each subject

		logique.addListOfPrerequisites(proba); // It means that logique has
												// proba as prerequisites

	}

	/**
	 * Initialize teachers
	 */
	public void initTeachers() {

		// The equivalent of the function but with the data from de XML file
		//
		// XMLMain myTestXMLMain = new XMLMain();
		// myTestXMLMain.fillTeachersXML(this.teachers);

		Teacher Mayag = new Teacher("Mayag", "Brice ", 150, 70);
		Teacher Pigozzi = new Teacher("Pigozzi", "Gabriella ", 650, 70);
		Teacher Cailloux = new Teacher("Cailloux", "Olivier ", 150, 150);
		this.teachers.put("Cailloux", Cailloux);
		this.teachers.put("Pigozzi", Pigozzi);
		this.teachers.put("Mayag", Mayag);

	}

	public boolean isUser(String username) {
		if (users.contains(username)) {
			return true;
		}
		return false;
	}

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
				// Logger.info("Pas de formation accessible");
			}
			for (int j = 0; j < this.getFormations().get(i).getAvailableFormations().size(); j++) {
				System.out.println("Les formations accessibles sont:"
						+ this.getFormations().get(i).getAvailableFormations().get(j).getFullName());
			}
		}
	}

	public void setDepartment(Department department) {
		if (this.department == null) {
			this.department = new Department();
		}
		this.department = department;
	}

	public void setFormations(List<Formation> formations) {
		if (this.formations == null) {
			this.formations = new LinkedList<>();
		}
		this.formations = formations;
	}

	///////////// INITIALIZE///////////////
	/**
	 * Initialize Paper
	 */
	public void setPaper(Enum<?> format, int x, int y) {
		FactoryPaper facp = new FactoryPaper();
		if (format == FactoryPaper.TypeFormat.A3 || format == FactoryPaper.TypeFormat.A4) {
			this.paper = facp.getPaper(format);
		} else {
			this.paper = facp.getPaper(format, x, y);
		}
	}

	///////// END GETTERS AND SETTERS //////

	public void setPaper(Paper paper) {
		this.paper = paper;

	}

	public void setSubjects(Map<String, Subject> subjects) {
		if (this.mapSubjects == null) {
			this.mapSubjects = new HashMap<>();
		}
		this.mapSubjects = subjects;
	}

	/**
	 * Store tags into the mapSubjects
	 *
	 * @param listOfTags
	 */
	public void setTags(List<Tag> listOfTags) {
		for (Tag tag : listOfTags) {
			Set<String> sujets = tag.getSubjects();
			for (String sujet : sujets) {
				this.mapSubjects.get(sujet).addTag(tag);
			}
		}

	}

	public void setTeachers(Map<String, Teacher> teachers) {
		if (this.teachers == null) {
			this.teachers = new HashMap<>();
		}
		this.teachers = teachers;
	}

	private void initUsers() {
		this.users = new ArrayList<>();
		this.users.add("ikram");
		this.users.add("romain");
		this.users.add("jules");
		this.users.add("cocolollipop");
		this.users.add("ocailloux");

	}

}
