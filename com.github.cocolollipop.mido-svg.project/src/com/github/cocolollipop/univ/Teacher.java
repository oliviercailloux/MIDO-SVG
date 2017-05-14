package com.github.cocolollipop.univ;

/**
 * This class corresponds to the teacher's details
 */
public class Teacher {

	private String lastName;
	private String firstName;
	private String phone;
	private String address;
	private String gender;
	private int posX;
	private int posY;

	/*
	 * Getters And Setters
	 */
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastNameTeacher) {
		this.lastName = lastNameTeacher;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstNameTeacher) {
		this.firstName = firstNameTeacher;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Teacher(String lname, String fname, int x, int y) {
		this.lastName = lname;
		this.firstName = fname;
		this.posX = x;
		this.posY = y;

	}

	public Teacher() {
		this.lastName = "";
		this.firstName = "";
		this.posX = 0;
		this.posY = 0;

	}

	public String getFullNameTeacher() {
		return lastName + " " + firstName;
	}

}
