package io.github.oliviercailloux.mido_svg.university.components;

import io.github.oliviercailloux.mido_svg.old.university.components.Teacher;
import java.util.Set;

/**
 * A licence is a french degree, equivalent to Bachelor's degree.
 */
public class Licence extends UniversityProgram {

	// We add in the constructor the information that the university program take place in Licence
	public Licence(String intitule, String name, int grade, Set<Course> courses,
			Set<UniversityProgram> availableUniversityProgram, String admisssion, Teacher teacher,
			String NameWithLink) {
		super(intitule, name, grade, courses, availableUniversityProgram, admisssion, teacher, NameWithLink);
		this.category = Category.LICENCE;
	}

}
