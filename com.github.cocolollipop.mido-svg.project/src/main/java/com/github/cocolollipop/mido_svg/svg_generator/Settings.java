package com.github.cocolollipop.mido_svg.svg_generator;

/**
 * This class is used to save the user settings
 * 
 * @author Cocolollipop
 *
 */
public class Settings {
	private boolean hiddenLicence;
	private boolean hiddenMaster;
	private boolean hiddenResponsable;
	private boolean hiddenAdmission;
	private boolean hiddenSubject;
	private boolean hiddenTeacher;
	private Enum format;

	public Settings(boolean lic, boolean master, boolean resp, boolean admission, boolean subject, boolean teacher,
			String format) {
		this.hiddenLicence = lic;
		this.hiddenMaster = master;
		this.hiddenResponsable = resp;
		this.hiddenAdmission = admission;
		this.hiddenSubject = subject;
		this.hiddenTeacher = teacher;
		this.format = Enum.valueOf(TypeFormat.class, format);
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

	public Enum getFormat() {
		return format;
	}

	public void setFormat(Enum format) {
		this.format = format;
	}
}
