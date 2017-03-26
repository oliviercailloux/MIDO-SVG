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
	
	public Licence(int p, String s, Formation fils){
		super();
		this.nomFormation = 'M';
		//if (p==2 || p==3)	// Peut etre inutile, � placer directement dans le code
		this.niveau = p;
		this.intitule = s;
		
		this.fils = fils;

	}

	public void setPosX(int decalageX, int i) {
		this.posX = i*decalageX;
		
	}

	public void setPosY(int decalageY, int j) {
		this.posY = j*decalageY;
		
	}

	
	


}
