package com.github.cocolollipop.mido_svg.view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GUISVGTAGAjouter {

	protected Shell shlAjouterTags;
	private Text textNomTag;
	private Button btnNewButton ;
	private Button btnHome;
	private List listTags;
	private Button button;
	private Button button_1;
	private String NomTag;
	private String Subject;
	private List listSujets1;
	private List listSujets2;
	private String subjects;
	private DefaultListModel<String> listmodel1 = new DefaultListModel<String>();
	private DefaultListModel<String> listmodel2 = new DefaultListModel<String>();

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
		listSujets1.setBounds(110, 231, 106, 88);
		
		listSujets2 = new List(shlAjouterTags, SWT.BORDER);
		listSujets2.setBounds(318, 231, 106, 88);
		
		button = new Button(shlAjouterTags, SWT.NONE);
	
		button.setBounds(234, 242, 47, 28);
		button.setText(">>");
		
		button_1 = new Button(shlAjouterTags, SWT.NONE);
	
		button_1.setText("<<");
		button_1.setBounds(234, 279, 47, 28);
		
		btnHome = new Button(shlAjouterTags, SWT.NONE);
	
		btnHome.setBounds(29, 363, 57, 28);
		btnHome.setText("Home");
		
		btnNewButton = new Button(shlAjouterTags, SWT.NONE);
		
		btnNewButton.setBounds(346, 363, 94, 28);
		btnNewButton.setText("Ajouter");

	}
	
	
	/**
	 * Initialise ListTags 
	 * 
	 * 1- Add All Subjects to ListSubject1
	 * 
	 */
	private void initTagsList(List tags) {
		/* Il faut récup tt les sujets et les ajouter dans le tableau subjects */
		
       
	}

	
	
	private void createEvents() {
		
		
		/** This button " Ajouter" adds un tag with the subjects associeted to it  **/
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				
			}
		});
		
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
		
		
		
		
	}
	
	

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlAjouterTags.open();
		shlAjouterTags.layout();
		createEvents();
		while (!shlAjouterTags.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
}
