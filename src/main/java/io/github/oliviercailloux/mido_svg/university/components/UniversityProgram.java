package io.github.oliviercailloux.mido_svg.university.components;

import io.github.oliviercailloux.mido_svg.old.university.components.Teacher;
import java.util.Set;


public class UniversityProgram {
	
	 public enum Category {
		    LICENCE, MASTER
		  }

	 /** Application type (for example: selection based on student records ) */
		  private String admisssion;

		  /** List of University program you could apply for after the current year */
		  private Set<UniversityProgram> availableUniversityProgram;

		  private String fullName;

		 /** this name corresponds to the name with a link ref to the program*/
		  private String fullNameWithLink;

		  /** grade is corresponding to the year*/
		  private int grade;

		  private String intitule;
		  
		  /** list of courses that contain each formation*/
		  private Set<Course> courses;

		  /** the main responsible of the University Program*/
		  private Teacher responsibleTeacher;

		  public Category category;


		  public UniversityProgram(String intitule, String name, int grade, Set<Course> courses, 
				  Set<UniversityProgram> availableUniversityProgram, String admisssion,
				  Teacher teacher,String NameWithLink) {
		    this.intitule = intitule;
		    this.fullName = name;
		    this.grade = grade;
		    this.courses = courses;
		    this.availableUniversityProgram = availableUniversityProgram;
		    this.admisssion = admisssion;
		    this.responsibleTeacher = teacher;
		    this.fullNameWithLink = NameWithLink;
		  }
		  

		  public String getAdmisssion() {
		    return admisssion;
		  }

		  public Set<UniversityProgram> getAvailableUniversityProgram() {
		    return availableUniversityProgram;
		  }

		  public Category getCategory() {
		    return category;
		  }

		  public String getFullName() {
		    return fullName;
		  }

		  public String getFullNameWithLink() {
		    if (fullNameWithLink == null) {
		      return fullName;
		    }
		    return fullNameWithLink;
		  }

		  public int getGrade() {
		    return grade;
		  }

		  public String getIntitule() {
		    return intitule;
		  }

		  public Set<Course> getCourses() {
		    return courses;
		  }

		  public Teacher getTeacher() {
		    return responsibleTeacher;
		  }

}
