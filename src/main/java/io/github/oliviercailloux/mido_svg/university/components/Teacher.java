package io.github.oliviercailloux.mido_svg.university.components;

/**
 * This class corresponds to the teacher's details
 */
public class Teacher {

	private String address;

	private String firstName;

	private String lastName;

	private String gender;

	private String phone;

	public Teacher(String firstName, String lastName, String gender, String address, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
	}

	/*
	 * Getters
	 */

	public String getAddress() {
		return address;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
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

	public String getPhone() {
		return phone;
	}

}
