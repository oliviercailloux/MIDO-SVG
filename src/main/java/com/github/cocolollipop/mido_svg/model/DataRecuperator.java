package com.github.cocolollipop.mido_svg.model;

public static List<Subject> DataRecuperator(String programID){
	List<Subject> list;
	Querier querier = new Querier();
	Program program = querier.getProgram(programID);
	List<String> courseRefs = program.getProgramStructure().getValue().getRefCourse();
	for(String courseRef : courseRefs) {
		final Course course = querier.getCourse(courseRef);
		Subject s = createSubject(course);
		list.add(s);
	}
	return list;
}
