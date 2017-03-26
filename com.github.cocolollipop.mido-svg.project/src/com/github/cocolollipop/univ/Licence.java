package com.github.cocolollipop.univ;

public class Licence extends Formation{
<<<<<<< HEAD
=======
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
>>>>>>> 6f434e3572f298bc0b16ddf04ab5c47699501336

	public Licence(String name, int x, int y) {
		super(name, x, y);
	}
<<<<<<< HEAD

	
=======
	
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

	
	


>>>>>>> 6f434e3572f298bc0b16ddf04ab5c47699501336
}
