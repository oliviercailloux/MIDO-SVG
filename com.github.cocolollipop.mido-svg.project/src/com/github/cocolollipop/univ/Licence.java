package com.github.cocolollipop.univ;

public class Licence extends Formation{
	/**public Licence(){
		super();
		this.nomFormation = 'L';
		
	}**/
	
	public Licence(char p, String s){
		super(s, p, p);
		this.nomFormation = 'M';
		//if (p==2 || p==3)	// Peut etre inutile, � placer directement dans le code
		this.niveau = p;
		this.intitule = s;

	}
	
	public Licence(String string, int i, int j) {
		super(string, j, j);
		this.posX = i;
		this.posY = j;
	}


	/**public Licence(int p, String s, Formation fils){
		super();
		this.nomFormation = 'M';
		//if (p==2 || p==3)	// Peut etre inutile, � placer directement dans le code
		this.niveau = p;
		this.intitule = s;
		
		this.fils = fils;

	}**/



	
	


}
