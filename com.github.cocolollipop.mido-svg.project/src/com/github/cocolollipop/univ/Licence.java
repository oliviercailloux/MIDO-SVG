package com.github.cocolollipop.univ;

public class Licence extends Formation {

	// public Licence(){ super(); this.nomFormation = 'L';}

	/*
	 * public Licence(char p, String s) { super(s, p, p); this.title = 'M'; //
	 * if (p==2 || p==3) // Peut etre inutile, � placer directement dans le //
	 * code this.grade = p; this.intitule = s;
	 * 
	 * }
	 */

	public Licence(String string, int k, int i, int j) {
		super(string, k, i, j);
		this.category = "Licence";
	}

	/*
	 * public Licence(int p, String s, Formation fils){ super();
	 * this.nomFormation = 'M'; //if (p==2 || p==3) // Peut etre inutile, �
	 * placer directement dans le code this.niveau = p; this.intitule = s;
	 * this.fils = fils; }
	 */

}
