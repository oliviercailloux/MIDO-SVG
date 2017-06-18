package com.github.cocolollipop.mido_svg.view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.github.cocolollipop.mido_svg.controller.ControllerJAXB;
import com.github.cocolollipop.mido_svg.model.DataBase;
import com.github.cocolollipop.mido_svg.xml.jaxb.model.Tag;
import com.github.cocolollipop.mido_svg.xml.jaxb.model.TagStore;

import org.eclipse.swt.widgets.Label;

import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GUISVGTAGSupprimer {

	protected Shell shlSupprimerTag;
	private Button btnHome;
	private Button btnSupprimer;
	private List listTags;
	private String Tag;
	private TagStore tagstore = new TagStore();
	private DataBase data = new DataBase();
	private Map<String, com.github.cocolollipop.mido_svg.university.components.Subject> map = data.getSubjects();
	private Set<Tag> tags;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GUISVGTAGSupprimer window = new GUISVGTAGSupprimer();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSupprimerTag = new Shell();
		shlSupprimerTag.setSize(450, 300);
		shlSupprimerTag.setText("Supprimer Tag");
		
		Label lblSupprimerTags = new Label(shlSupprimerTag, SWT.NONE);
		lblSupprimerTags.setBounds(190, 27, 109, 14);
		lblSupprimerTags.setText("Supprimer Tags");
		
		Label lblListeDesTags = new Label(shlSupprimerTag, SWT.NONE);
		lblListeDesTags.setBounds(34, 72, 125, 14);
		lblListeDesTags.setText("Liste des tags :");
		
		listTags = new List(shlSupprimerTag, SWT.BORDER);
		listTags.setBounds(165, 99, 149, 116);
		
		btnSupprimer = new Button(shlSupprimerTag, SWT.NONE);
		
		btnSupprimer.setBounds(324, 228, 94, 28);
		btnSupprimer.setText("Supprimer");
		
		btnHome = new Button(shlSupprimerTag, SWT.NONE);
	
		btnHome.setBounds(10, 228, 73, 28);
		btnHome.setText("Home");

	}
	
	
	/**
	 * Initialise ListTags 
	 * 
	 * Add All Tags to ListTags
	 * 
	 */
	private void initTagsList() {
	
			for(String name : map.keySet()){
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
		
		
		/** This button "Supprimer" removes the selected Tag from the List  **/
		
		btnSupprimer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Tag = listTags.getSelection().toString();
				listTags.remove(listTags.getSelectionIndex());
				tagstore.getTagsList().remove(Tag);
			 
			}
		});
	}

	

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlSupprimerTag.open();
		shlSupprimerTag.layout();
		createEvents();
		while (!shlSupprimerTag.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
