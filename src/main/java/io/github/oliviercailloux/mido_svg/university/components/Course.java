package io.github.oliviercailloux.mido_svg.university.components;

import io.github.oliviercailloux.mido_svg.xml.jaxb.model.Tag;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is corresponding to the course students could attend
 *
 */
public final class Course {

	/** Number of credits of the course */
	private double credit;
	/** The courses needed to attend to the course */
	private List<Course> listOfPrerequisites; 
	/** The teacher who is responsible of the course */
	private Teacher responsible; 
	/** The title name of the course */
	private String title;
	
	public Course(String title, double courseCredit, Teacher responsible) {
		this.responsible = responsible;
		this.title = title;
		this.credit = courseCredit;
		this.listOfPrerequisites = new ArrayList<>();
	}
	
	public Course(String title, Teacher responsible, int credit, List<Course> list) {
		this.credit = credit;
		this.title = title;
		this.responsible = responsible;
		this.listOfPrerequisites = list;
	}
	
	/**
	   * This subroutine is used to know if a course has other courses as prerequisites
	*/
	public boolean hasPrerequisites() {
	    if (this.getListOfPrerequisites().size() > 0) {
	      return true;
	    }
	    return false;
	  }
	
	public double getCredit() {
		return credit;
	}

	public List<Course> getListOfPrerequisites() {
		return listOfPrerequisites;
	}

	public Teacher getResponsible() {
		return responsible;
	}

	public String getTitle() {
		return title;
	}
	
}
