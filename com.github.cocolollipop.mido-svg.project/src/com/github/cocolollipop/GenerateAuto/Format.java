package com.github.cocolollipop.GenerateAuto;

public class Format {
	
	String format="";
	private int dimXCanvas = 1920;
	private int dimYCanvas = 1080;
	private int canevasX = 0;
	private int canevasY = 0;
	
	// GETTERS

	public int getCanevasX() {
		return canevasX;
	}

	public int getCanevasY() {
		return canevasY;
	}

	// SETTERS

	public void setCanevasX(int canevasX) {
		this.canevasX = canevasX;
	}


	public void setCanevasY(int canevasY) {
		this.canevasY = canevasY;
	}
	
	/** 
	 * changeFormat method change the format of the canevas to A3 or A4 and throws an exception if it's neither A4 nor A3
	 * @param format would be equal to A3/a3 or A4/a4
	 * @throws Exception
	 */
	
	public void changeFormat(String format) throws Exception{
		
		 if(format=="A4"|| format=="a4"){
			 setCanevasX(2480);
			 setCanevasY(3508);
		 }
		 
		 else if (format=="A3" || format=="a3"){
			 setCanevasX(3508); //3508
			 setCanevasY(4961); //4961
		 }
		 else throw new Exception("This size isn't availaible, please choose between A3 or A4");
	}


}
