package com.github.cocolollipop.mido_svg.view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GUISVGLoginForm {

	protected Shell shlMidosvg;
	private Text text;
	private String Login;
	private Button btnConnexion;

	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GUISVGLoginForm window = new GUISVGLoginForm();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlMidosvg = new Shell();
		shlMidosvg.setSize(450, 300);
		shlMidosvg.setText("MIDO-SVG");
		
		Label lblLogin = new Label(shlMidosvg, SWT.NONE);
		lblLogin.setBounds(193, 64, 59, 14);
		lblLogin.setText("Login");
		
		text = new Text(shlMidosvg, SWT.BORDER);
		text.setBounds(146, 105, 148, 19);
		
		btnConnexion = new Button(shlMidosvg, SWT.NONE);
	
		btnConnexion.setBounds(174, 147, 94, 28);
		btnConnexion.setText("Connexion");

	}
	
	
	private void createEvents() {
		
		/** The " Connexion " button so we can get connected to the app */
		btnConnexion.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Login = text.getText();
				GUISVGHome h = new GUISVGHome();
				shlMidosvg.close();
				h.open();
				
			}
		});
	}
	
	
	
	public String getLogin() {
		return Login;
	}


	public void setLogin(String login) {
		Login = login;
	}



	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlMidosvg.open();
		shlMidosvg.layout();
		createEvents();
		while (!shlMidosvg.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
}
