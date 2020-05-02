package com.github.cocolollipop.mido_svg.svg_generator;

import com.github.cocolollipop.mido_svg.university.components.*;	
import com.github.cocolollipop.mido_svg.university.components.Formation;

import java.util.List;
import sun.font.FontDesignMetrics;
import java.awt.Color;
import java.awt.FontMetrics;

/**
 * This class adapts the position of objects in order to draw a
 * responsive SVG according to the user's settings.
 *
 */
public class ResponsiveSVG {
	

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
	 * Count the number of subjects in a Formation.
	 *
	 * @param formation is a Formation
	 * 
	 * @return an integer corresponding to the number of subjects in the formation"
	 */
	
	public int countSubjects(Formation formation) {
		List<Subject> listesub = formation.getSubjects();
		int nb=listesub.size();
		return nb;
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
	public void defineObjectsPosition(List<Formation> list, int canvasX, int canvasY) {

		/*
		 * We have to define the initial shift. So we must count the total
		 * number formations in "list" then calculate Y offset depending on which
		 * formation are chosen (Only L1 or all ?). Finally we calculate X offset
		 */
		int offsetX = 0;
		int offsetY = 0;
	
		
		int totalCptY = 0; /* O<=totalCptY<=5 corresponds to the DOM Tree height 
						    * totalCptY = 5 means we have to draw the formations from 
					        * grade 1 to 5 ("L1" to "M2") 
					        */

		int cptY[] = {0,0,0,0,0};   /* We assume that the indexes correspond to the grade 
									 * ordered from 1 to 5 ("L1" to "M2")
									 * For the moment we just initialize the tab with zeros 
									 * Afterwards, depending on which levels the user choose to
									 * draw, each level will have a certain height in the tree
									 */
	
		/* 
		 * The following instruction loop on the list of formation and calculate:
		 * 
		 * 		- the number of formation per grade --> each result is store on the array "nb_forma_per_grade"
		 * 		- the maximum number of subjects per grade--> each result is store on the array "maxsubj_per_grade"
		 * */
		
		int[] nb_forma_per_grade = new int[5]; 
		int[] maxsubj_per_grade = new int[5];
		
		for (Formation f: list) {
			for (int i=0; i<=4; i++){
				if (f.getGrade()==i+1) {
					nb_forma_per_grade[i] ++; 
					
					int nbsujet =countSubjects(f);
					if (nbsujet >= maxsubj_per_grade[i]) {
						maxsubj_per_grade[i]= nbsujet;
					}
				}
			}	
		}

		

		/* The following instruction loop on the number of formation per grade to:
		 * 		- fill cptY
		 * 		- calculate totalCptY
		 * 
		 * For example grade 1 (nb_forma_per_grade[0]) and grade 2 (nb_forma_per_grade[1])
		 * then cpt[2] == 1 which means that the level 
		 * grade 3 has the height 1 in the tree which means
		 * grade 3 is the root of the tree.
		 * 
		 */
		
		for (int i=0; i<=4; i++) {
			if (nb_forma_per_grade[i] !=0) {
				totalCptY++;
				cptY[i] = totalCptY;
			}	
		}
		totalCptY+= 1;
		
		/* calculate the offset for an subject */
		
		java.awt.Font Basicfont = new java.awt.Font("TimesRoman", 12, 12);
		FontMetrics fm = FontDesignMetrics.getMetrics(Basicfont);
		int height_font_subj = fm.getHeight();
		int offset_subject = height_font_subj + 14;
		
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
		
		for (int i=0; i<=4; i++) {
			if (nb_forma_per_grade[i] !=0) {
				
				offsetX = canvasX / (nb_forma_per_grade[i] + 1);
				offsetY = (int) ((canvasY / (totalCptY) ) * (cptY[i] - 1) + (canvasY * 0.1));
				associatePositionX(list, i+1, offsetX, offsetY);
				
			}	
		}	
	}
	
	/**
	 * Associate each formation of the specified level to a certain 
	 * position according to the coordinates passed in parameter 
	 *
	 * @param list
	 *            is a LinkedList of Formation
	 * @param myYear
	 *            is the grade 1="l1", 2=" l2",....
	 * @param offsetX
	 * 			  the abscissa calculated after shifting and adjusting 
	 * @param offsetY
	 * 			  the ordinate calculated after shifting and adjusting
	 */

	private void associatePositionX(List<Formation> list, int myYear, int offsetX, int offsetY) {
		int i = 0;
		for (Formation aFormation : list) {
			if (aFormation.getGrade() == myYear) {
				aFormation.setPosX((int) (offsetX * i + offsetX * 0.5));
				aFormation.setPosY(offsetY);
				i++;
				System.out.println("associerOK : " + aFormation.getFullName());
			}
		}
	}

}