package io.github.oliviercailloux.mido_svg.svg_generator;

import io.github.oliviercailloux.mido_svg.paper.FactoryPaper;
import io.github.oliviercailloux.mido_svg.paper.Paper;

/**
 * This class saves the user settings
 *
 */
public class Settings {
  private Enum<?> format;

  private boolean hiddenAdmission;

  private boolean hiddenLicence;

  private boolean hiddenMaster;

  private boolean hiddenPrerequisites;

  private boolean hiddenResponsable;

  private boolean hiddenSubject;

  private boolean hiddenTeacher;

  private int width, height;

  /**
   * The constructor to choose the size of the canevas (The user can choose any values he want for
   * height and width) -> format = other
   **/

  public Settings(boolean lic, boolean master, boolean resp, boolean admission, boolean subject,
      boolean teacher, boolean prereq, int width, int height) {
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

  /** The constructor to choose the size of the canevas (between A3 or A4) **/

  public Settings(boolean lic, boolean master, boolean resp, boolean admission, boolean subject,
      boolean teacher, boolean prereq, String format) {
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

  public Enum<?> getFormat() {
    return this.format;
  }

  public int getHeight() {
    return this.height;
  }

  public int getWidth() {
    return this.width;
  }

  public boolean isHiddenAdmission() {
    return hiddenAdmission;
  }

  public boolean isHiddenLicence() {
    return hiddenLicence;
  }

  public boolean isHiddenMaster() {
    return hiddenMaster;
  }

  public boolean isHiddenPrerequisites() {
    return hiddenPrerequisites;
  }

  public boolean isHiddenResponsable() {
    return hiddenResponsable;
  }

  public boolean isHiddenSubject() {
    return hiddenSubject;
  }

  public boolean isHiddenTeacher() {
    return hiddenTeacher;
  }

  public void setFormat(Enum<?> format) {
    this.format = format;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setHiddenAdmission(boolean hiddenAdmission) {
    this.hiddenAdmission = hiddenAdmission;
  }

  public void setHiddenLicence(boolean hiddenLicence) {
    this.hiddenLicence = hiddenLicence;
  }

  public void setHiddenMaster(boolean hiddenMaster) {
    this.hiddenMaster = hiddenMaster;
  }

  public void setHiddenPrerequisites(boolean hiddenPrerequisites) {
    this.hiddenPrerequisites = hiddenPrerequisites;
  }

  public void setHiddenResponsable(boolean hiddenResponsable) {
    this.hiddenResponsable = hiddenResponsable;
  }

  public void setHiddenSubject(boolean hiddenSubject) {
    this.hiddenSubject = hiddenSubject;
  }

  public void setHiddenTeacher(boolean hiddenTeacher) {
    this.hiddenTeacher = hiddenTeacher;
  }

  public void setWidth(int width) {
    this.width = width;
  }
}
