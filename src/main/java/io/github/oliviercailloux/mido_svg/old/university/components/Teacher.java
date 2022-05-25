package io.github.oliviercailloux.mido_svg.old.university.components;

/**
 * This class corresponds to the teacher's details
 */
public class Teacher {

  private String address;

  private String firstName;

  private String gender;

  private String lastName;

  private String phone;

  private int posX;

  private int posY;

  public Teacher() {
    this.lastName = "";
    this.firstName = "";
    this.posX = 0;
    this.posY = 0;

  }

  public Teacher(String lname, String fname, int x, int y) {
    this.lastName = lname;
    this.firstName = fname;
    this.posX = x;
    this.posY = y;

  }

  public String getAddress() {
    return address;
  }

  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns the full name (LastName + FirstName) of a teacher
   * 
   */
  public String getFullNameTeacher() {
    return lastName + " " + firstName;
  }

  public String getGender() {
    return gender;
  }

  /*
   * Getters And Setters
   */
  public String getLastName() {
    return lastName;
  }

  public String getPhone() {
    return phone;
  }

  public int getPosX() {
    return posX;
  }

  public int getPosY() {
    return posY;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setFirstName(String firstNameTeacher) {
    this.firstName = firstNameTeacher;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void setLastName(String lastNameTeacher) {
    this.lastName = lastNameTeacher;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setPosX(int posX) {
    this.posX = posX;
  }

  public void setPosY(int posY) {
    this.posY = posY;
  }
}
