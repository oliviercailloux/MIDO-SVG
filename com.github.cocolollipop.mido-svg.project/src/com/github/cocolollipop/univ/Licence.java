package com.github.cocolollipop.univ;

public class Licence extends Formation{
	public Licence(){
		super();
		this.nomFormation = 'L';
		
	}
	
	public Licence(int p, String s){
		super();
		this.nomFormation = 'M';
		//if (p==2 || p==3)	// Peut etre inutile, � placer directement dans le code
			this.niveau = p;
		this.intitule = s;

	}
}
