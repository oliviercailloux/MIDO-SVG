package com.github.cocolollipop.mido_svg.model;

import com.github.cocolollipop.mido_svg.BddQuerries.Querier;
import com.github.cocolollipop.mido_svg.university.components.Subject;
import com.github.cocolollipop.mido_svg.university.components.Teacher;

public class ObjectTransformer {
	
	public static Subject createSubject(Course course) {
		Subject subject;
		Querier querier = new Querier();
		if(course.getEcts()!=null) {
			subject.setCredit(course.getEcts().getValue());
		}
		if(course.getLevel()!=null) {
			subject.setLevel(course.getLevel().getValue());
		}
		if(course.getManagingTeacher()!=null) {
			Person person = querier.getPerson(course.getManagingTeacher().getValue());
			Teacher t = createTeacher(person);
			subject.setResponsible(t);
		}
		if(course.getCourseName()!=null) {
			subject.setTitle(course.getCourseName().getValue());
		}
		return subject;
	}
	
	public static Teacher createTeacher(Person person) {
		Teacher teacher;
		if(person.getGivenName()!=null) {
			teacher.setFirstName(person.getGivenName().getValue());
		}
		if(person.getFamilyName()!=null) {
			teacher.setLastName(person.getFamilyName().getValue());
		}
		return teacher;
	}

}
