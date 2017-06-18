package com.github.cocolollipop.mido_svg.view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
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
	 * 1- Add All Tags to ListTags
	 * 
	 */
	private void initTagsList(List tags) {
		/* Il faut récup tt les tags et les ajouter dans le tableau subjects */
		
       
	}
	
	private void createEvents() {
		btnHome.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GUISVGHome h = new GUISVGHome();
				h.open();
			}
		});
		
		
		btnSupprimer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Tag = listTags.getSelection().toString();
				listTags.remove(listTags.getSelectionIndex());
			 
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
