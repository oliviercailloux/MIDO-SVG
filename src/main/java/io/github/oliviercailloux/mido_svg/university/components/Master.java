package io.github.oliviercailloux.mido_svg.university.components;

import io.github.oliviercailloux.mido_svg.old.university.components.Teacher;
import java.util.Set;

/**
 * A Master is a french degree, equivalent to Master's degree.
 */
public class Master extends UniversityProgram {

	// We add in the constructor the information that the university program take place in Master
	public Master(String intitule, String name, int grade, Set<Course> courses,
			Set<UniversityProgram> availableUniversityProgram, String admisssion, Teacher teacher,
			String NameWithLink) {
		super(intitule, name, grade, courses, availableUniversityProgram, admisssion, teacher, NameWithLink);
		this.category = Category.MASTER;
	}

}
