package com.github.cocolollipop.mido_svg.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;

public class GUITagGenerator {

	protected Shell shell;
	private Text tag;
	private String USERNAME;


	/**
	 * Open the window.
	 */
	public void open(String username) {
		this.USERNAME = username;
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		Label lblLogin = new Label(shell, SWT.NONE);
		lblLogin.setBounds(20, 20, 300, 200);
		lblLogin.setText(USERNAME);

		shell.setText("Générateur de tags pour MIDO-SVG");
		shell.setLayout(new GridLayout(5, false));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		Label lblDescription = new Label(shell, SWT.NONE);
		lblDescription.setText("A gauche, vous pouvez rentrer un tag");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		tag = new Text(shell, SWT.BORDER);
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 133;
		tag.setLayoutData(gd_text);
		new Label(shell, SWT.NONE);

		Button btnAjouterTag = new Button(shell, SWT.NONE);
		btnAjouterTag.setText("Ajouter Tag");
		btnAjouterTag.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseUp(MouseEvent e) {
				// ici on ouvre la nouvelle fenetre et on lie le nouveau tag aux matieres

				// Pour l'instant on ferme MAIS A SUPPRIMER
				shell.close();
			}
		});
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		Button btnEnd = new Button(shell, SWT.NONE);
		btnEnd.setText("Générer XML");
		btnEnd.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseUp(MouseEvent e) {
				// ici on fait le marshalling

				// maintenant on ferme
				shell.close();
			}
		});
		
	}

}