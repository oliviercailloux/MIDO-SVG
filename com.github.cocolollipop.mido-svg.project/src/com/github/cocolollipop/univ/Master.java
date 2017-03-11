package com.github.cocolollipop.univ;

public class Master extends Formation{
	public Master(int p){
		super();
		this.nomFormation = 'M';
		if (p==2 || p==1)	// Peut etre inutile, � placer directement dans le code
			this.niveau = p;

	}
	
	public Master(int p, String s){
		super();
		this.nomFormation = 'M';
		if (p==2 || p==1)	// Peut etre inutile, � placer directement dans le code
			this.niveau = p;
		this.intitule = s;

	}
}
