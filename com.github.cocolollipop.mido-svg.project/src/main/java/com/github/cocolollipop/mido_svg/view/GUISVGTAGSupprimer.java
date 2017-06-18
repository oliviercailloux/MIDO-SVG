package com.github.cocolollipop.mido_svg.view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.github.cocolollipop.mido_svg.controller.ControllerJAXB;
import com.github.cocolollipop.mido_svg.model.DataBase;
import com.github.cocolollipop.mido_svg.xml.jaxb.model.Tag;
import com.github.cocolollipop.mido_svg.xml.jaxb.model.TagStore;

import org.eclipse.swt.widgets.Label;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;

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
	private ControllerJAXB jaxb = new ControllerJAXB();
	private String USERNAME;
	private Label lblListeDesMatires;
	private List list;

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlSupprimerTag = new Shell();
		shlSupprimerTag.setSize(577, 293);
		shlSupprimerTag.setText("Supprimer Tag");
		
		Label lblLogin = new Label(shlSupprimerTag, SWT.NONE);
		lblLogin.setBounds(21, 42, 210, 133);
		lblLogin.setText(USERNAME);

		
		Label lblSupprimerTags = new Label(shlSupprimerTag, SWT.NONE);
		lblSupprimerTags.setBounds(190, 27, 109, 14);
		lblSupprimerTags.setText("Supprimer Tags");
		
		Label lblListeDesTags = new Label(shlSupprimerTag, SWT.NONE);
		lblListeDesTags.setBounds(34, 72, 125, 14);
		lblListeDesTags.setText("Liste des tags :");
		
		listTags = new List(shlSupprimerTag, SWT.BORDER);
		listTags.setBounds(31, 92, 197, 110);
		
		btnSupprimer = new Button(shlSupprimerTag, SWT.NONE);
		
		btnSupprimer.setBounds(473, 228, 94, 28);
		btnSupprimer.setText("Supprimer");
		
		btnHome = new Button(shlSupprimerTag, SWT.NONE);
	
		btnHome.setBounds(10, 228, 73, 28);
		btnHome.setText("Home");
		
		lblListeDesMatires = new Label(shlSupprimerTag, SWT.NONE);
		lblListeDesMatires.setBounds(346, 72, 185, 14);
		lblListeDesMatires.setText("Liste des matières associées :");
		
		list = new List(shlSupprimerTag, SWT.BORDER);
		list.setBounds(346, 92, 197, 110);


	}
	
	
	/**
	 * Initialise ListTags 
	 * 
	 * Add All Tags to ListTags
	 * @throws IOException 
	 * @throws JAXBException 
	 * 
	 */
	private void initTagsList() throws JAXBException, IOException {
		java.util.List<Tag> userListOfTags = jaxb.readTagsFileXML(USERNAME);
		Set<Tag> tagsSet = new HashSet<Tag>();
	
		for (Tag tag : userListOfTags) {
			tagsSet.add(tag);
		}		
		for(Tag tag:tagsSet){
			listTags.add(tag.getName());
		
       
	}
       
	}
	
	
	private void createEvents() {
		
		/** This button "Home" opens the GUI home  **/

		btnHome.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GUISVGHome h = new GUISVGHome();
				h.open(USERNAME);
				shlSupprimerTag.close();
			
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
	 * @throws IOException 
	 * @throws JAXBException 
	 */
	public void open(String username) throws JAXBException, IOException {
		this.USERNAME = username;
		Display display = Display.getDefault();
		createContents();
		shlSupprimerTag.open();
		shlSupprimerTag.layout();
		initTagsList();
		createEvents();
		while (!shlSupprimerTag.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
