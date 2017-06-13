package com.github.cocolollipop.mido_svg.svg_generator;

import com.github.cocolollipop.mido_svg.paper.FactoryPaper;
import com.github.cocolollipop.mido_svg.paper.Paper;

/**
 * This class saves the user settings
 *
 */
public class Settings {
	private boolean hiddenLicence;
	private boolean hiddenMaster;
	private boolean hiddenResponsable;
	private boolean hiddenAdmission;
	private boolean hiddenSubject;
	private boolean hiddenTeacher;
	private boolean hiddenPrerequisites;
	private Enum format;
	private int width, height;

	/** The constructor to choose the size of the canevas (between A3 or A4) **/

	public Settings(boolean lic, boolean master, boolean resp, boolean admission, boolean subject, boolean teacher,
			boolean prereq, String format) {
		this.hiddenLicence = lic;
		this.hiddenMaster = master;
		this.hiddenResponsable = resp;
		this.hiddenAdmission = admission;
		this.hiddenSubject = subject;
		this.hiddenTeacher = teacher;
		this.hiddenPrerequisites = prereq;
		this.format = Enum.valueOf(FactoryPaper.TypeFormat.class, format);
		FactoryPaper factorypaper = new FactoryPaper();
		Paper paper = factorypaper.getPaper(this.format);
		this.width = paper.getDimXCanvas();
		this.height = paper.getDimYCanvas();
	}

	/**
	 * The constructor to choose the size of the canevas (The user can choose
	 * any values he want for height and width) -> format = other
	 **/

	public Settings(boolean lic, boolean master, boolean resp, boolean admission, boolean subject, boolean teacher,
			boolean prereq, int width, int height) {
		this.hiddenLicence = lic;
		this.hiddenMaster = master;
		this.hiddenResponsable = resp;
		this.hiddenAdmission = admission;
		this.hiddenSubject = subject;
		this.hiddenTeacher = teacher;
		this.hiddenPrerequisites = prereq;
		this.width = width;
		this.height = height;
		this.format = Enum.valueOf(FactoryPaper.TypeFormat.class, "Other");
	}

	public boolean isHiddenLicence() {
		return hiddenLicence;
	}

	public void setHiddenLicence(boolean hiddenLicence) {
		this.hiddenLicence = hiddenLicence;
	}

	public boolean isHiddenMaster() {
		return hiddenMaster;
	}

	public void setHiddenMaster(boolean hiddenMaster) {
		this.hiddenMaster = hiddenMaster;
	}

	public boolean isHiddenResponsable() {
		return hiddenResponsable;
	}

	public void setHiddenResponsable(boolean hiddenResponsable) {
		this.hiddenResponsable = hiddenResponsable;
	}

	public boolean isHiddenAdmission() {
		return hiddenAdmission;
	}

	public void setHiddenAdmission(boolean hiddenAdmission) {
		this.hiddenAdmission = hiddenAdmission;
	}

	public boolean isHiddenSubject() {
		return hiddenSubject;
	}

	public void setHiddenSubject(boolean hiddenSubject) {
		this.hiddenSubject = hiddenSubject;
	}

	public boolean isHiddenTeacher() {
		return hiddenTeacher;
	}

	public void setHiddenTeacher(boolean hiddenTeacher) {
		this.hiddenTeacher = hiddenTeacher;
	}

	public boolean isHiddenPrerequisites() {
		return hiddenPrerequisites;
	}

	public void setHiddenPrerequisites(boolean hiddenPrerequisites) {
		this.hiddenPrerequisites = hiddenPrerequisites;
	}

	public Enum getFormat() {
		return this.format;
	}

	public void setFormat(Enum format) {
		this.format = format;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
