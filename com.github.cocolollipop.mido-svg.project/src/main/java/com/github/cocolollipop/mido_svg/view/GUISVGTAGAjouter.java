package com.github.cocolollipop.mido_svg.view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.swing.DefaultListModel;
import javax.xml.bind.JAXBException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import com.github.cocolollipop.mido_svg.controller.ControllerJAXB;
import com.github.cocolollipop.mido_svg.model.DataBase;
import com.github.cocolollipop.mido_svg.svg_generator.ResponsiveSVG;
import com.github.cocolollipop.mido_svg.xml.jaxb.model.Tag;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GUISVGTAGAjouter {

	protected Shell shlAjouterTags;
	private Text textNomTag;
	private Button btnAjouter ;
	private Button btnHome;
	private List listTags;
	private Button button;
	private Button button_1;
	private String NomTag;
	private String Subject;
	private List listSujets1;
	private List listSujets2;
	private String subjects;
	private Tag tag = new Tag();
	private DataBase data = new DataBase();
	private Map<String, com.github.cocolollipop.mido_svg.university.components.Subject> map = data.getSubjects();
	//private java.util.List<String> tags = data.getTags();
	private GUISVGLoginForm lform = new GUISVGLoginForm();
	ControllerJAXB jaxb = new ControllerJAXB();
	private Set<Tag> tags;




	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GUISVGTAGAjouter window = new GUISVGTAGAjouter();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAjouterTags = new Shell();
		shlAjouterTags.setSize(450, 423);
		shlAjouterTags.setText("Ajouter Tags");
		
		Label lblAjouterTags = new Label(shlAjouterTags, SWT.NONE);
		lblAjouterTags.setBounds(210, 10, 137, 14);
		lblAjouterTags.setText("Ajouter Tags");
		
		Label lblListeDeM = new Label(shlAjouterTags, SWT.NONE);
		lblListeDeM.setBounds(25, 60, 186, 14);
		lblListeDeM.setText("Liste de mes tags :");
		
		listTags = new List(shlAjouterTags, SWT.BORDER | SWT.H_SCROLL);
		listTags.setBounds(186, 43, 186, 55);
		
		Label lblAjouterUnTag = new Label(shlAjouterTags, SWT.NONE);
		lblAjouterUnTag.setBounds(25, 127, 128, 14);
		lblAjouterUnTag.setText("Ajouter un Tag :");
		
		Label lblNomDuTag = new Label(shlAjouterTags, SWT.NONE);
		lblNomDuTag.setBounds(122, 165, 106, 14);
		lblNomDuTag.setText("Nom du Tag : ");
		
		textNomTag = new Text(shlAjouterTags, SWT.BORDER);
		textNomTag.setBounds(234, 162, 150, 19);
		
		Label lblSujetsAssocier = new Label(shlAjouterTags, SWT.NONE);
		lblSujetsAssocier.setBounds(25, 211, 128, 14);
		lblSujetsAssocier.setText("Sujets à associer:");
		
		listSujets1 = new List(shlAjouterTags, SWT.BORDER);
		listSujets1.setBounds(10, 231, 164, 104);
		
		listSujets2 = new List(shlAjouterTags, SWT.BORDER);
		listSujets2.setBounds(259, 231, 164, 104);
		
		button = new Button(shlAjouterTags, SWT.NONE);
	
		button.setBounds(195, 245, 47, 28);
		button.setText(">>");
		
		button_1 = new Button(shlAjouterTags, SWT.NONE);
	
		button_1.setText("<<");
		button_1.setBounds(195, 279, 47, 28);
		
		btnHome = new Button(shlAjouterTags, SWT.NONE);
	
		btnHome.setBounds(29, 363, 57, 28);
		btnHome.setText("Home");
		
		btnAjouter = new Button(shlAjouterTags, SWT.NONE);
		
		btnAjouter.setBounds(346, 363, 94, 28);
		btnAjouter.setText("Ajouter");

	}
	
	
	/**
	 * Initialise ListTags 
	 * 
	 * 1- Adds All Subjects to ListSubject1 of GUI
	 * 2- Adds All Tags to listTags of GUI
	 * 
	 */
	private void initTagsList() {
		
		/* Adding the subjects to the Jlist of Subjetcs */
		for(String name : map.keySet()){
			String value = map.get(name).getTitle();
			listSujets1.add(value);
			
			
			/* Adding the Tags to the Jlist of tags */
			 tags = map.get(name).getTags();
			 for (Tag tag : tags) {
				 listTags.add(tag.getName());
				}		
		}
   
	}

	
	private void createEvents() {
		
		
		/** This button "Home" opens the GUI home  **/

		btnHome.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GUISVGHome h = new GUISVGHome();
				h.open();
			}
		});
		
		
		/** This button ">>" adds un subject to a list **/

		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Subject = listSujets1.getSelection().toString();
				listSujets2.add(Subject);
				listSujets1.remove(listSujets1.getSelectionIndex());
				
				
			}
		});
		
		/** This button "<<" removes un subject from a list **/

		
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
	
				Subject = listSujets2.getSelection().toString();
				listSujets1.add(Subject);
				listSujets2.remove(listSujets2.getSelectionIndex());
			}
		});
		
		
		/** This button " Ajouter" adds un tag with the subjects associeted to it  **/
		
		btnAjouter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tag.setName(textNomTag.getText());
				for(int i=0; i<listSujets2.getSelectionCount();i++){
				tag.addSubject(listSujets2.getItems()[i]);
				}
				
			}
		});
				
	}
	
	

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlAjouterTags.open();
		shlAjouterTags.layout();
		initTagsList();
		createEvents();
		while (!shlAjouterTags.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
}
