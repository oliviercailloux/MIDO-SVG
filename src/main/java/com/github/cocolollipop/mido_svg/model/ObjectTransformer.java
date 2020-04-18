package com.github.cocolollipop.mido_svg.model;


public static Subject ObjectTransformer(Course course) {


	Subject subject;
	if(course.getEcts()!=null) {
		subject.setCredit(course.getEcts().getValue());
	}
	if(course.getLevel()!=null) {
		subject.setLevel(course.getLevel().getValue());
	}
	/*
	if(course.getFormalPrerequisites()==null) {
		subject.listOfPrerequisites=null;
	}
	else {
		subject.credit = course.getEcts().getValue();
	}
	*/
	if(course.getManagingTeacher()!=null) {
		Person person = querier.getPerson(course.getManagingTeacher().getValue());
		Teacher t = createTeacher(person);
		subject.setResponsible(t);
	}
	//searchword ? pour tag
	if(course.getCourseName()!=null) {
		subject.setTitle(course.getCourseName().getValue());
	}
	return subject;
}

public static Teacher createTeacher(Person person) {
	Teacher teacher;
	teacher.adress=null;
	if(person.getGivenName()!=null) {
		teacher.setFirstName(person.getGivenName().getValue());
	}
	//gender ?
	if(person.getFamilyName()!=null) {
		teacher.setLastName(person.getFamilyName().getValue());
	}
	return teacher;
}
