package com.github.cocolollipop.mido_svg.svg_generator;

import java.awt.Canvas;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.cocolollipop.mido_svg.university.components.Formation;
import com.github.cocolollipop.mido_svg.university.components.Subject;

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
	 * Count the number of Subjects in the Formation of the year myYear which has the most Subjects
	 * 
	 * @param list a list of all the formations 
	 * @param myYear the year for which we want to find the maximum number of subjects (L1, L2...)
	 * @return an Integer that represents the maximum number of Subjects in a Formation of myYear.
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
	
	/**
	 * 
	 * @param nbOfSubjects the number of Subjects of a block 
	 * @return the height in pixels of a Subjects block
	 */

	public int calculateHeightOfSubjects(Integer nbOfSubjects) {
		Canvas c = new Canvas();
		FontMetrics fm = c.getFontMetrics(Basicfont);
		int height = fm.getHeight();
		// the space between 2 lines is 14 (seen in the code of DrawerSVGGen) and we count the space before "cours"
		int heightOfSubjects = (nbOfSubjects+1) * height + (nbOfSubjects+2) * 14 ;
		return heightOfSubjects;
	}
	
	/**
	 * 
	 * @param formation 
	 * @return the size in pixels of the longest Subject's title of the Formation in argument
	 */

	public int giveTheLongestSubjectInPixel(Formation formation) {
		int longestword = 0;
		for(Subject subject : formation.getSubjects()) {
			if (longestword < widthOfTheWordInPixel(subject.getTitle())) {
				longestword = widthOfTheWordInPixel(subject.getTitle());
			}
		}
		return longestword;
	}
	
	/**
	 * @param word
	 * @return the width in pixels of a word
	 */

	public int widthOfTheWordInPixel(String word) {
		Canvas c = new Canvas();
		FontMetrics fm = c.getFontMetrics(Basicfont);
		return fm.stringWidth(word);
	}
	
	public int calculateAdditionalSpaceY(int canvasY, List<Integer> maxSubjects, int totalCptY) {
		int totalHeightOfSubjects = 0;
		for (Integer i : maxSubjects) {
			totalHeightOfSubjects += calculateHeightOfSubjects(i);
		}
		Canvas c = new Canvas();
		FontMetrics fm = c.getFontMetrics(Basicfont);
		int heightOfResponsibleName = fm.getHeight();
		//The height of a rectangle is 25 pixels (seen in the drawerSVGGen class)
		
		int formationBlock = 25+heightOfResponsibleName;

		int additionalSpace = (int) (((canvasY - totalHeightOfSubjects - (canvasY * 0.15) - formationBlock * (totalCptY - 1)) / totalCptY) + formationBlock);

		return additionalSpace;
	}
	
	/**
	 * This method aim to calculate the space that is available to add between the formations of a same year when drawing.
	 * 
	 * @param myYear the year on which the space is going to be calculate
	 * @param canvasX the width of the paper format
	 * @param list the list of all the Formations
	 * @return an int corresponding to the space in pixels to add between the formations of a same year
	 * @throws IllegalStateException
	 */

	public int calculateAdditionalSpaceByGrade(String myYear, int canvasX, List<Formation> list) throws IllegalStateException {
		int spaceTaken = 0;
		int cpt = 1;

		for (Formation formation : list) {
			if (formation.getFullName().contains(myYear)) {
				spaceTaken += giveTheLongestSubjectInPixel(formation) + widthOfTheWordInPixel(formation.getFullName()) + 20;
				cpt++;
			}
		}

		int additionalSpace = (int) ((canvasX - spaceTaken - (0.1 * canvasX)) / cpt);
		if (additionalSpace < 0) {
			throw new IllegalStateException("The format paper isn't enought wide.");
		}
		return additionalSpace;
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
		int offsetY = 0;
		int nbL1 = 0;
		int nbL2 = 0;
		int nbL3 = 0;
		int nbM1 = 0;
		int nbM2 = 0;
		
		int additionalSpace=0;
		
		List<Integer> maxSubjects = new ArrayList<>();
		
		int spaceTaken = 0;
		int levelActual = 1;

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
		
		if((!hiddenSubjects)) {
			additionalSpace=calculateAdditionalSpaceY(canvasY, maxSubjects, totalCptY);
			if(additionalSpace<0) {
				throw new IllegalStateException("You cannot show all the subject in this format of paper");
			}
		}

		/*
		 * Now we calculate Y offset.
		 * 
		 * For each level:
		 * 		- if the subjects are going to be drawn, the Y offset is calculated depending on 
		 * 		  the additionalSpace calculated before.
		 * 		- if the subjects are not going to be drawn, we divide the space depending on the
		 * 		  number of level drawn
		 */

		if (nbL1 != 0) {
			if (hiddenSubjects) {
				offsetY = (int) ((canvasY / (totalCptY) ) * (cptY[0] - 1) + (canvasY * 0.1));
			}
			else {
				offsetY = (int) (canvasY * 0.1);
				levelActual++;
				spaceTaken += offsetY + calculateHeightOfSubjects(countMaxSubjects(list, "L1")) + additionalSpace;
			}
			associatePositions(list, "L1", offsetY, canvasX, hiddenSubjects);
		}

		if (nbL2 != 0) {
			if (hiddenSubjects) {
				offsetY = (int) ((canvasY / (totalCptY) ) * (cptY[1] - 1) + (canvasY * 0.1));

			}
			else {
				if (levelActual == 1) {
					offsetY = (int) (canvasY * 0.1);
					levelActual++;
					spaceTaken += offsetY + calculateHeightOfSubjects(countMaxSubjects(list, "L2")) + additionalSpace;
				}
				else {
					offsetY = spaceTaken;
					levelActual++;
					spaceTaken += calculateHeightOfSubjects(countMaxSubjects(list, "L2")) + additionalSpace;
				}
			}
			associatePositions(list, "L2", offsetY, canvasX, hiddenSubjects);
		}

		if (nbL3 != 0) {
			if (hiddenSubjects) {
				offsetY = (int) ((canvasY / (totalCptY) ) * (cptY[2] - 1) + (canvasY * 0.1));
			}
			else {
				if (levelActual == 1) {
					offsetY = (int) (canvasY * 0.1);
					levelActual++;
					spaceTaken += offsetY + calculateHeightOfSubjects(countMaxSubjects(list, "L3")) + additionalSpace;
					System.out.println("offsetY L3 : " + offsetY);
				}
				else {
					offsetY = spaceTaken;
					levelActual++;
					spaceTaken += calculateHeightOfSubjects(countMaxSubjects(list, "L3")) + additionalSpace;
				}
			}
			associatePositions(list, "L3", offsetY, canvasX, hiddenSubjects);
		}

		if (nbM1 != 0) {
			if (hiddenSubjects) {
				offsetY = (int) ((canvasY / (totalCptY)) * (cptY[3] - 1) + (canvasY * 0.1));	
			}
			else {
				if (levelActual == 1) {
					offsetY = (int) (canvasY * 0.1);
					levelActual++;
					spaceTaken += offsetY + calculateHeightOfSubjects(countMaxSubjects(list, "M1")) + additionalSpace;
				}
				else {
					offsetY = spaceTaken;
					levelActual++;
					spaceTaken += calculateHeightOfSubjects(countMaxSubjects(list, "M1")) + additionalSpace;
					System.out.println("offsetY M1 : " + offsetY);
				}
			}
			associatePositions(list, "M1", offsetY, canvasX, hiddenSubjects);
		}

		if (nbM2 != 0) {
			if (hiddenSubjects) {
				offsetY = (int) ((canvasY / (totalCptY)) * (cptY[4] - 1) + (canvasY * 0.1));
			}
			else {
				if (levelActual == 1) {
					offsetY = (int) (canvasY * 0.1);
					levelActual++;
					spaceTaken += offsetY + calculateHeightOfSubjects(countMaxSubjects(list, "M2")) + additionalSpace;
				}
				else {
					offsetY = spaceTaken;
					levelActual++;
					spaceTaken += calculateHeightOfSubjects(countMaxSubjects(list, "M2")) + additionalSpace;
					System.out.println("offsetY M2 : " + offsetY);
				}
			}
			associatePositions(list, "M2", offsetY, canvasX, hiddenSubjects);
		}

	}

	/**
	 * This method calculate the position X of the Formations of a year depending on the parameters.
	 * Then, it set the position X and the position Y of the formations
	 * 
	 * @param list
	 * @param myYear 
	 * @param offsetY
	 * @param canvasX
	 * @param isHidden
	 */
	private void associatePositions(List<Formation> list, String myYear, int offsetY, int canvasX, boolean isHidden) {

		if (!isHidden) {
			int additionalSpace = calculateAdditionalSpaceByGrade(myYear, canvasX, list);
			int spaceTaken = (int) ((0.05 * canvasX) + additionalSpace);
			for (Formation aFormation : list) {
				if (aFormation.getFullName().indexOf(myYear) != -1) {
					aFormation.setPosX(spaceTaken);
					aFormation.setPosY(offsetY);
					spaceTaken += additionalSpace + giveTheLongestSubjectInPixel(aFormation) + widthOfTheWordInPixel(aFormation.getFullName()) + 20;
				}
			}
		}
		else {
			int additionalSpace = 0;
			int j = 0;
			for (Formation aFormation : list) {
				if (aFormation.getFullName().indexOf(myYear) != -1) {
					additionalSpace += widthOfTheWordInPixel(aFormation.getFullName()) + 20;
					j++;
				}
			}
			additionalSpace = (int) ((canvasX - additionalSpace - (canvasX * 0.1)) / (j+1)); 
			if (additionalSpace < 0) {
				throw new IllegalStateException("The format paper isn't enough wide");
			}
			int spaceTaken = (int) (0.05 * canvasX + additionalSpace);
			for (Formation aFormation : list) {
				if (aFormation.getFullName().indexOf(myYear) != -1) {
					aFormation.setPosX(spaceTaken);
					aFormation.setPosY(offsetY);
					spaceTaken += additionalSpace + widthOfTheWordInPixel(aFormation.getFullName()) + 20;
				}
			}
		}
	}

}
