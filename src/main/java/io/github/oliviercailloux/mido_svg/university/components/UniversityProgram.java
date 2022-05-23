package io.github.oliviercailloux.mido_svg.university.components;

import io.github.oliviercailloux.mido_svg.university.components.Course.Category;
import java.util.Set;


public final class UniversityProgram {
	
	 protected enum Category {
		    LICENCE, MASTER
		  }

		  // Application type (for example: selection based on student records )
		  private String admisssion;

		  // List of University program you could apply for after the current year
		  private Set<UniversityProgram> availableUniversityProgram;

		  private String fullName;

		  // this name corresponds to the name with a link ref to the program
		  private String fullNameWithLink;

		  // grade is corresponding to the year
		  private int grade;

		  private String intitule;

		  // list of courses that contain each formation
		  private Set<Course> courses;

		  // the main responsible of the University Program
		  private Teacher teacher;

		  protected Category category;


		  public UniversityProgram(String intitule, String name, int grade, Set<Course> courses, 
				  Set<UniversityProgram> availableUniversityProgram, String admisssion, Teacher teacher, boolean shown) {
		    this.intitule = intitule;
		    this.fullName = name;
		    this.grade = grade;
		    this.courses = courses;
		    this.availableUniversityProgram = availableUniversityProgram;
		    this.admisssion = admisssion;
		    this.teacher = teacher;

		  }
		  

		  public String getAdmisssion() {
		    return admisssion;
		  }

		  public Set<UniversityProgram> getAvailableUniversityProgram() {
		    return availableUniversityProgram;
		  }

		  public Enum<?> getCategory() {
		    return this.category;
		  }

		  public String getFullName() {
		    return fullName;
		  }

		  public String getFullNameWithLink() {
		    if (this.fullNameWithLink == null) {
		      return this.fullName;
		    }
		    return this.fullNameWithLink;
		  }

		  public int getGrade() {
		    return this.grade;
		  }

		  public String getIntitule() {
		    return this.intitule;
		  }

		  public Set<Course> getCourses() {
		    return courses;
		  }

		  public Teacher getTeacher() {
		    return teacher;
		  }

		  /**
		   * hasGotATeachert see if a "formation" has got a teacher (the firstname/lastname aren't null) or
		   * not
		   *
		   * @param f
		   * @return true or false
		   */

}
