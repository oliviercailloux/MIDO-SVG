package com.github.cocolollipop.univ;

public class Enseignant {
	private String nomEnseignant;
	private String prenomEnseignant;
	private String phone;
	private String address;
	private String gender;
	private int x;
	private int y;
	
	/*
	 * Getters And Setters
	 */
	public String getNomEnseignant() {
		return nomEnseignant;
	}
	public void setNomEnseignant(String nomEnseignant) {
		this.nomEnseignant = nomEnseignant;
	}
	public String getPrenomEnseignant() {
		return prenomEnseignant;
	}
	public void setPrenomEnseignant(String prenomEnseignant) {
		this.prenomEnseignant = prenomEnseignant;
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
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Enseignant(String name, int x, int y){
		this.nomEnseignant = name;
		this.x = x;
		this.y = y;

	}
	
	
}
