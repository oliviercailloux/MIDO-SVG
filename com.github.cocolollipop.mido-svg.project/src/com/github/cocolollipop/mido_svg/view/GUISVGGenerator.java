package com.github.cocolollipop.mido_svg.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.github.cocolollipop.mido_svg.paper.Format;
import com.github.cocolollipop.mido_svg.svg_generator.LicenceSVGGen;
import com.github.cocolollipop.mido_svg.university.components.Formation;

public class GUISVGGenerator {

	private JFrame frmMidosvg;
	private JButton btnGnrerLsvg;
	private JButton btnAjouterMotCle;
	private JButton btnAddList;
	private JButton btnRemoveList;
	private JButton btnFermer;
	private JCheckBox chckbxA3;
	private JCheckBox chckbxA4;
	private JCheckBox chckbxLicence;
	private JCheckBox chckbxMaster;
	private JCheckBox chckbxLesResponsables;
	private JCheckBox chckbxAdmission;
	private JCheckBox chckbxLesMatieres;
	private JCheckBox chckbxLesEnseignants;

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldMotCle;
	private DefaultListModel<String> listmodel1 = new DefaultListModel<String>();
	private DefaultListModel<String> listmodel2 = new DefaultListModel<String>();
	private JList<String> list1;
	private JList<String> list2;
	private String motCle;
	/**
	 * choices
	 */
	private boolean affFormationLicence;
	private boolean affFormationMaster;
	private boolean affResponsable;
	private boolean affMatieres;
	private boolean affAdmission;
	private boolean affSubject;
	private boolean affTeacher;
	private String form;

	private LicenceSVGGen svg = new LicenceSVGGen();
	private Format format = new Format();
	private Formation formation; // A VOIR
	private JLabel lblEnseignWarning;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GUISVGGenerator window = new GUISVGGenerator();
					window.frmMidosvg.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUISVGGenerator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		initComponents();
		createEvents();
		list1.setModel(listmodel1);
		list2.setModel(listmodel2);
	}

	/**
	 * This method containt all code for creating and initializing components
	 * 
	 **/

	private void initComponents() {
		frmMidosvg = new JFrame();
		frmMidosvg.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(GUISVGGenerator.class.getResource("/com/github/cocolollipop/WindowBuilderResources/dauphine.png")));
		frmMidosvg.setTitle("MIDO-SVG");
		frmMidosvg.getContentPane().setBackground(new Color(240, 248, 255));

		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setIcon(
				new ImageIcon(GUISVGGenerator.class.getResource("/com/github/cocolollipop/WindowBuilderResources/dauphine.png")));

		JLabel lblMidosvgApplication = new JLabel("MIDO-SVG APPLICATION");
		lblMidosvgApplication.setFont(new Font("Lucida Bright", Font.BOLD, 19));
		lblMidosvgApplication.setForeground(new Color(0, 0, 205));

		JLabel lblParamtres = new JLabel("Paramètres : ");
		lblParamtres.setForeground(new Color(0, 0, 128));
		lblParamtres.setFont(new Font("Lucida Grande", Font.PLAIN, 13));

		JLabel lblTaille = new JLabel("Taille :");
		lblTaille.setForeground(new Color(0, 0, 128));
		lblTaille.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		chckbxA3 = new JCheckBox("A3");
		buttonGroup.add(chckbxA3);
		chckbxA3.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		chckbxA4 = new JCheckBox("A4");
		buttonGroup.add(chckbxA4);
		chckbxA4.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		JLabel lblOu = new JLabel("ou");
		lblOu.setFont(new Font("Lucida Grande", Font.PLAIN, 11));

		JLabel lblFormations = new JLabel("Formations :");
		lblFormations.setForeground(new Color(0, 0, 128));
		lblFormations.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		chckbxLicence = new JCheckBox("Licence");
		chckbxLicence.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		JLabel lblEtou = new JLabel("et/ou");
		lblEtou.setFont(new Font("Lucida Grande", Font.PLAIN, 11));

		chckbxMaster = new JCheckBox("Master");
		chckbxMaster.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		JLabel lblElementsAfficher = new JLabel("Elements à afficher : ");
		lblElementsAfficher.setForeground(new Color(0, 0, 128));
		lblElementsAfficher.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		chckbxLesResponsables = new JCheckBox("Les responsables");
		chckbxLesResponsables.setFont(new Font("Lucida Grande", Font.PLAIN, 11));

		chckbxAdmission = new JCheckBox("Admission");
		chckbxAdmission.setFont(new Font("Lucida Grande", Font.PLAIN, 11));

		btnGnrerLsvg = new JButton("Générer le SVG");

		btnGnrerLsvg.setForeground(Color.BLACK);
		btnGnrerLsvg.setBackground(new Color(255, 255, 255));
		btnGnrerLsvg.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		JLabel lblLesMotscls = new JLabel("Les mots-clés :");
		lblLesMotscls.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblLesMotscls.setForeground(new Color(0, 0, 128));

		JTextPane textPane = new JTextPane();

		list1 = new JList();

		/*
		 * list1.setModel(new AbstractListModel() { String[] values = new
		 * String[] { "math" };
		 * 
		 * @Override public int getSize() { return values.length; }
		 * 
		 * @Override public Object getElementAt(int index) { return
		 * values[index]; } });
		 */
		initTagsList();
		list1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		list2 = new JList<String>();
		list2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		btnAddList = new JButton(">>");

		btnRemoveList = new JButton("<<");

		textFieldMotCle = new JTextField();
		textFieldMotCle.setColumns(10);

		btnAjouterMotCle = new JButton("Ajouter ");

		btnAjouterMotCle.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		btnFermer = new JButton("Fermer");

		btnFermer.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		chckbxLesMatieres = new JCheckBox("Les matières");
		chckbxLesMatieres.setFont(new Font("Lucida Grande", Font.PLAIN, 11));

		chckbxLesEnseignants = new JCheckBox("Les enseignants");
		chckbxLesEnseignants.setFont(new Font("Lucida Grande", Font.PLAIN, 11));

		lblEnseignWarning = new JLabel("");
		lblEnseignWarning.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblEnseignWarning.setForeground(new Color(255, 0, 0));

		GroupLayout groupLayout = new GroupLayout(frmMidosvg.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(152).addComponent(lblMidosvgApplication)
						.addContainerGap(129, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addGap(55).addGroup(groupLayout
						.createParallelGroup(
								Alignment.LEADING)
						.addComponent(lblParamtres)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(
										groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(lblTaille).addComponent(lblFormations))
														.addGap(60)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(chckbxLicence).addComponent(chckbxA3)))
												.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
														.createParallelGroup(Alignment.TRAILING, false).addComponent(
																btnRemoveList, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
														.addComponent(btnAddList,
																Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 35,
																GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED)))
								.addGap(49)
								.addComponent(list2, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblLesMotscls)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(chckbxLesResponsables).addComponent(lblElementsAfficher))
								.addGap(42).addComponent(chckbxAdmission)))
						.addContainerGap(48, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 601, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(
										groupLayout.createSequentialGroup()
												.addComponent(btnFermer)
												.addPreferredGap(ComponentPlacement.RELATED, 263,
														Short.MAX_VALUE)
												.addComponent(btnGnrerLsvg, GroupLayout.PREFERRED_SIZE, 139,
														GroupLayout.PREFERRED_SIZE)
												.addGap(91))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(textFieldMotCle, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(btnAjouterMotCle).addGroup(groupLayout
														.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(lblOu).addComponent(lblEtou))
														.addGap(24)))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(chckbxA4).addComponent(chckbxMaster)
												.addComponent(chckbxLesMatieres))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 1,
												GroupLayout.PREFERRED_SIZE)
										.addGap(153))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(list1, GroupLayout.PREFERRED_SIZE, 163,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(111).addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblEnseignWarning).addComponent(chckbxLesEnseignants))
						.addContainerGap(384, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap().addComponent(label).addGap(12)
				.addComponent(lblMidosvgApplication).addGap(28).addComponent(lblParamtres).addGap(31)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblTaille)
						.addComponent(chckbxA3).addComponent(chckbxA4).addComponent(lblOu))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(29).addComponent(lblFormations).addGap(30)
								.addComponent(lblElementsAfficher))
						.addGroup(groupLayout.createSequentialGroup().addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(chckbxLicence).addComponent(lblEtou).addComponent(chckbxMaster))))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(chckbxLesMatieres)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(chckbxLesResponsables).addComponent(chckbxAdmission)))
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(chckbxLesEnseignants)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblEnseignWarning).addGap(14)
				.addComponent(lblLesMotscls).addGap(23)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldMotCle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAjouterMotCle))
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(list1, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(list2, GroupLayout.PREFERRED_SIZE, 144,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnGnrerLsvg, GroupLayout.PREFERRED_SIZE, 39,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnFermer, GroupLayout.PREFERRED_SIZE, 34,
														GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup().addGap(49).addComponent(btnAddList).addGap(27)
								.addComponent(btnRemoveList)))
				.addContainerGap()));
		frmMidosvg.getContentPane().setLayout(groupLayout);
		frmMidosvg.setBounds(100, 100, 528, 723);
		frmMidosvg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Initialise tagsList (list1)
	 * 
	 */
	private void initTagsList() {

		ArrayList<String> list = new ArrayList<>();
		list = svg.getData().getListOfTags();
		for (int i = 0; i < list.size(); i++) {
			listmodel1.addElement(list.get(i));
		}
		list1.setModel(listmodel1);

	}

	/**
	 * This method containt all code for creating events
	 * 
	 **/

	private void createEvents() {

		/*
		 * Exit button
		 * 
		 */

		btnFermer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		/*
		 * The button "Générer l'SVG" that gives the final result (generates and
		 * opens the SVG)
		 * 
		 */

		btnGnrerLsvg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					svg.paint(affFormationLicence, affFormationMaster, affResponsable, affMatieres, affAdmission,
							affSubject, affTeacher, form);

					File file = new File("outLicence.svg");

					try {
						java.awt.Desktop.getDesktop().open(file);
					} catch (IOException exc) {
						System.out.println("Exception: " + exc.toString());
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "SVG généré");
			}
		});

		/*
		 * The button "Ajouter" that add to the list of keywords
		 * 
		 */

		btnAjouterMotCle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				motCle = textFieldMotCle.getText();
				if (motCle.length() >= 0) {
					listmodel1.addElement(motCle);
				}

			}
		});

		/*
		 * The button ">>" that adds the selected element from list1 to the
		 * list2
		 * 
		 */

		btnAddList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				motCle = list1.getSelectedValue().toString();
				listmodel2.addElement(motCle);
				listmodel1.remove(list1.getSelectedIndex());

			}
		});

		/*
		 * The button ">>" that adds the selected element from list2 to the
		 * list1
		 * 
		 */

		btnRemoveList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				motCle = list2.getSelectedValue().toString();
				listmodel1.addElement(motCle);
				listmodel2.remove(list2.getSelectedIndex());
			}
		});

		/*
		 * Check box to choose A3 format
		 * 
		 */

		chckbxA3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxA3.isSelected()) {
					form = "A3";
				}
			}
		});

		/*
		 * Check box to choose A4 format
		 * 
		 */

		chckbxA4.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxA4.isSelected()) {
					form = "A4";
				}
			}
		});

		/*
		 * Check box to choose Licence
		 * 
		 */

		chckbxLicence.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxLicence.isSelected()) {
					affFormationLicence = true;
				} else {
					affFormationLicence = false;
				}
			}
		});

		/**
		 * Check box to choose Master
		 * 
		 */
		chckbxMaster.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxMaster.isSelected()) {
					affFormationMaster = true;
				} else {
					affFormationMaster = false;
				}
			}
		});

		/**
		 * Check box to choose display "Responsables" or not
		 * 
		 */
		chckbxLesResponsables.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxLesResponsables.isSelected()) {
					affResponsable = true;
				} else {
					affResponsable = false;

				}
			}
		});

		/**
		 * Check box to choose display "Admission" or not
		 * 
		 */
		chckbxAdmission.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxAdmission.isSelected()) {
					affAdmission = true;
					System.out.println(affAdmission);
				} else
					affAdmission = false;
				System.out.println(affAdmission);
			}
		});

		/**
		 * Check box to choose display "Subjects" or not
		 * 
		 */
		chckbxLesMatieres.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxLesMatieres.isSelected()) {
					affSubject = true;
					affTeacher = false;
				}
			}
		});

		/**
		 * Check box to choose display "Teachers" of each subject or not
		 * 
		 */
		chckbxLesEnseignants.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxLesEnseignants.isSelected() && chckbxLesMatieres.isSelected()) {
					lblEnseignWarning.setText(" Rappel:Les enseignants sont affichés avec les matières associées !!");
					affSubject = true;
					affTeacher = true;

				}
			}
		});

	}

}