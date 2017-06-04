package com.github.cocolollipop.mido_svg.svg_generator;

import com.github.cocolollipop.mido_svg.paper.Paper;
import com.github.cocolollipop.mido_svg.paper.PaperA3;
import com.github.cocolollipop.mido_svg.paper.PaperA4;

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
	private Paper paper;
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
		this.format = Enum.valueOf(Paper.TypeFormat.class, format);
		this.paper = new Paper();
		this.paper = Paper.Paper(Enum.valueOf(Paper.TypeFormat.class, format), 0, 0);

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
		this.format = Enum.valueOf(Paper.TypeFormat.class, "Other");
		this.paper = new Paper(width, height);
		this.paper = Paper.Paper(Enum.valueOf(Paper.TypeFormat.class, "Other"), width, height);

	}

	/**
	 * changeFormat method change the format of the canevas to A3 or A4 and
	 * throws an exception if it's neither A4 nor A3
	 * 
	 * @param settings.
	 *            would be equal to A3/a3 or A4/a4
	 * @throws Exception
	 */

	public void changeFormat(Enum settings) {

		if (settings == Paper.TypeFormat.A4) {
			this.paper = new PaperA4();

		} else if (settings == Paper.TypeFormat.A3) {
			this.paper = new PaperA3();

		}
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
		return format;
	}

	public void setFormat(Enum format) {
		this.format = format;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
