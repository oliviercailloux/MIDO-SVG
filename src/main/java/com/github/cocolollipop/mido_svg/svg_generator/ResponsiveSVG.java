package com.github.cocolollipop.mido_svg.svg_generator;

import java.awt.Canvas;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.List;

import com.github.cocolollipop.mido_svg.university.components.Formation;

/**
 * This class adapts the position of objects in order to draw a
 * responsive SVG according to the user's settings.
 *
 */
public class ResponsiveSVG {

	private static java.awt.Font Basicfont = new java.awt.Font("TimesRoman", 12, 12);

	/**
	 * Count the number of formations of a specific level in a list.
	 *
	 * @param list
	 *            is a LinkedList of formations
	 * @param myYear
	 *            is a year of study such as "L3" or "M1"
	 *            
	 * @return an integer corresponding to the number of formations in "list" of level "myYear"
	 */
	public int countFormations(List<Formation> list, String myYear) {
		int nb = 0;
		for (Formation aFormation : list) {
			if (aFormation.getFullName().indexOf(myYear) != -1) {
				nb++;
			}
		}
		return nb;
	}
	/**
	 * Count the number max of Subjects in a Formation of myYear
	 * @param list
	 * @param myYear
	 * @return an Integer that represents the number max of Subjects in a Formation of myYear.
	 */
	public Integer countMaxSubjects(List<Formation> list, String myYear) {
		Integer max = -1;
		for (Formation aFormation : list) {
			if (aFormation.getFullName().indexOf(myYear) != -1) {
				if (aFormation.getSubjects().size()>max) {
					max = aFormation.getSubjects().size();
				}
			}
		}
		return max;
	}

	public int calculateHeightOfSubjects(Integer nbOfSubjects) {
		Canvas c = new Canvas();
		FontMetrics fm = c.getFontMetrics(Basicfont);
		int height = fm.getHeight();
		// the space between 2 lines is 14 (seen in the code of DrawerSVGGen) and we count the space before "cours"
		int heightOfSubjects = (nbOfSubjects+1) * height + (nbOfSubjects+2) * 14 ;
		return heightOfSubjects;
	}

	/**
	 * Determine the position of each Formation in the 
	 * Canvas according to the number of formations and their level.
	 *
	 * @param list
	 *            : LinkedList of all the formations available in the University
	 * @param canvasX
	 *            : width of the canvas
	 * @param canvasY
	 * 			  : height of the canvas
	 */
	public void defineObjectsPosition(List<Formation> list, int canvasX, int canvasY, boolean hiddenSubjects) {

		/*
		 * We have to define the initial shift. So we must count the total
		 * number formations in "list" then calculate Y offset depending on which
		 * formation are chosen (Only L1 or all ?). Finally we calculate X offset
		 */
		int offsetX = 0;
		int offsetY = 0;
		int nbL1 = 0;
		int nbL2 = 0;
		int nbL3 = 0;
		int nbM1 = 0;
		int nbM2 = 0;
		int totalHeightOfSubjects = 0;

		List<Integer> maxSubjects = new ArrayList<>();

		int totalCptY = 0; /* O<=totalCptY<=5 corresponds to the DOM Tree height 
		 * totalCptY = 5 means we have to draw the formations from 
		 * level "L1" to "M2" 
		 */

		int cptY[] = {0,0,0,0,0};   /* We assume that the indexes correspond to the levels 
		 * ordered from "L1" to "M2"
		 * For the moment we just initialize the tab with zeros 
		 * Afterwards, depending on which levels the user choose to
		 * draw, each level will have a certain height in the tree
		 */


		// First we count the number of formations of every level
		nbL1 = countFormations(list, "L1");
		nbL2 = countFormations(list, "L2");
		nbL3 = countFormations(list, "L3");
		nbM1 = countFormations(list, "M1");
		nbM2 = countFormations(list, "M2");


		/* We fill cptY
		 * 
		 * For example if nbL1==0 and nbL2 == 0 
		 * then cpt[2] == 1 which means that the level 
		 * "L3" has the height 1 in the tree which means
		 * "L3" is the root of the tree.
		 * 
		 */
		int tempCpt = 0;
		if (nbL1 != 0) {					
			totalCptY += 1;     
			cptY[0] = tempCpt + 1;
			tempCpt++;
			maxSubjects.add(countMaxSubjects(list, "L1"));
		}
		if (nbL2 != 0) {
			totalCptY += 1; 
			cptY[1] = tempCpt + 1;
			tempCpt++;
			maxSubjects.add(countMaxSubjects(list, "L2"));
		}
		if (nbL3 != 0) {
			totalCptY += 1; 
			cptY[2] = tempCpt + 1;
			tempCpt++;
			maxSubjects.add(countMaxSubjects(list, "L3"));
		}
		if (nbM1 != 0) {
			totalCptY += 1; 
			cptY[3] = tempCpt + 1;
			tempCpt++;
			maxSubjects.add(countMaxSubjects(list, "M1"));
		}
		if (nbM2 != 0) {
			totalCptY += 1; 
			cptY[4] = tempCpt + 1;
			maxSubjects.add(countMaxSubjects(list, "M2"));
		}

		totalCptY+= 1;

		for (Integer i : maxSubjects) {
			totalHeightOfSubjects += calculateHeightOfSubjects(i);
		}

		// We assume that the height of the police is 12
		// height of a rectangle is 25 pixels and the height of the responsible is 12, 
		//and we have to add this block to additionnal block to prevent space for it
		
		int formationBlock = 25+12;
		
		int additionnalSpace = (int) (((canvasY - totalHeightOfSubjects - (canvasY * 0.15) - formationBlock * (totalCptY - 1)) / totalCptY) + formationBlock);
		
		System.out.println("additionnal space : " + additionnalSpace);
		System.out.println("canvaY :" + canvasY);
		
		if((!hiddenSubjects)&&(additionnalSpace<0)) {
			throw new IllegalStateException("You cannot show all the subject in this format of paper");
		}

		/*
		 * Now we calculate X and Y offset
		 * First we have to check which levels has been seted 
		 * If it has been, then 
		 * 		- the canvas'width should be evenly distributed 
		 * 		over the number of formations of that level
		 * 		- the canvas'height should also be evenly distributed 
		 * 		over the number of formations of that level and should take 
		 * 		into account its height like calculated before in cptY.
		 * 		Plus, we added a margin, which is proportional to the canvas to 
		 * 		make it responsive, so that the SVG would be centered. 
		 */

		int spaceTaken = 0;
		int levelActual = 1;

		if (nbL1 != 0) {
			offsetX = canvasX / (nbL1 + 1);
			if (hiddenSubjects) {
				offsetY = (int) ((canvasY / (totalCptY) ) * (cptY[0] - 1) + (canvasY * 0.1));
			}
			else {
				offsetY = (int) (canvasY * 0.1);
				levelActual++;
				spaceTaken += offsetY + calculateHeightOfSubjects(countMaxSubjects(list, "L1")) + additionnalSpace;
			}
			associatePositionX(list, "L1", offsetX, offsetY);
		}

		if (nbL2 != 0) {
			offsetX = canvasX / (nbL2 + 1);
			if (hiddenSubjects) {
				offsetY = (int) ((canvasY / (totalCptY) ) * (cptY[1] - 1) + (canvasY * 0.1));

			}
			else {
				if (levelActual == 1) {
					offsetY = (int) (canvasY * 0.1);
					levelActual++;
					spaceTaken += offsetY + calculateHeightOfSubjects(countMaxSubjects(list, "L2")) + additionnalSpace;
				}
				else {
					offsetY = spaceTaken;
					levelActual++;
					spaceTaken += calculateHeightOfSubjects(countMaxSubjects(list, "L2")) + additionnalSpace;
				}
			}
			associatePositionX(list, "L2", offsetX, offsetY);
		}

		if (nbL3 != 0) {
			offsetX = canvasX / (nbL3 + 1);
			if (hiddenSubjects) {
				offsetY = (int) ((canvasY / (totalCptY) ) * (cptY[2] - 1) + (canvasY * 0.1));
			}
			else {
				if (levelActual == 1) {
					offsetY = (int) (canvasY * 0.1);
					levelActual++;
					spaceTaken += offsetY + calculateHeightOfSubjects(countMaxSubjects(list, "L3")) + additionnalSpace;
					System.out.println("offsetY L3 : " + offsetY);
				}
				else {
					offsetY = spaceTaken;
					levelActual++;
					spaceTaken += calculateHeightOfSubjects(countMaxSubjects(list, "L3")) + additionnalSpace;
				}
			}
			associatePositionX(list, "L3", offsetX, offsetY);
		}

		if (nbM1 != 0) {
			offsetX = canvasX / (nbM1 + 1);
			if (hiddenSubjects) {
				offsetY = (int) ((canvasY / (totalCptY)) * (cptY[3] - 1) + (canvasY * 0.1));	
			}
			else {
				if (levelActual == 1) {
					offsetY = (int) (canvasY * 0.1);
					levelActual++;
					spaceTaken += offsetY + calculateHeightOfSubjects(countMaxSubjects(list, "M1")) + additionnalSpace;
				}
				else {
					offsetY = spaceTaken;
					levelActual++;
					spaceTaken += calculateHeightOfSubjects(countMaxSubjects(list, "M1")) + additionnalSpace;
					System.out.println("offsetY M1 : " + offsetY);
				}
			}
			associatePositionX(list, "M1", offsetX, offsetY);
		}

		if (nbM2 != 0) {
			offsetX = canvasX / (nbM2 + 2);
			if (hiddenSubjects) {
				offsetY = (int) ((canvasY / (totalCptY)) * (cptY[4] - 1) + (canvasY * 0.1));
			}
			else {
				if (levelActual == 1) {
					offsetY = (int) (canvasY * 0.1);
					levelActual++;
					spaceTaken += offsetY + calculateHeightOfSubjects(countMaxSubjects(list, "M2")) + additionnalSpace;
				}
				else {
					offsetY = spaceTaken;
					levelActual++;
					spaceTaken += calculateHeightOfSubjects(countMaxSubjects(list, "M2")) + additionnalSpace;
					System.out.println("offsetY M2 : " + offsetY);
				}
			}
			associatePositionX(list, "M2", offsetX, offsetY);
		}

	}

	/**
	 * Associate each formation of the specified level to a certain 
	 * position according to the coordinates passed in parameter 
	 *
	 * @param list
	 *            is a LinkedList of Formation
	 * @param myYear
	 *            is a year of study such as "L3" or "M1"
	 * @param offsetX
	 * 			  the abscissa calculated after shifting and adjusting 
	 * @param offsetY
	 * 			  the ordinate calculated after shifting and adjusting
	 */
	private void associatePositionX(List<Formation> list, String myYear, int offsetX, int offsetY) {
		int i = 0;
		for (Formation aFormation : list) {
			if (aFormation.getFullName().indexOf(myYear) != -1) {
				aFormation.setPosX((int) (offsetX * i + offsetX * 0.5));
				aFormation.setPosY(offsetY);
				i++;
				System.out.println("associerOK : " + aFormation.getFullName());
			}
		}
	}

}
