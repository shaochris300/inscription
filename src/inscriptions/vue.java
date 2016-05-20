package inscriptions;
/* blablablabla */
import java.awt.*;

import javax.swing.*;
import net.proteanit.sql.DbUtils;
import sql.AfficheTable;
import sql.ControleSaisie;
import sql.Refresh;
import sql.RemplirBarreSaisie;
import sql.RequeteDelete;
import sql.RequeteInsert;
import sql.RequeteUpdate;
import sql.SqlConnection;
import sql.loadlist;

import java.sql.*;
import java.time.*;
import java.awt.event.*;
import javax.swing.event.*;

import metier.Inscriptions;
import metier.Personne;

public class vue {
	/* */
	private JFrame frame;
	private JTextField textFieldNompers, textFieldPrenompers, textFieldEmailpers, textFieldNomequi, textFieldIdcandP, textFieldIdcandE, textFieldDatecloture, textFieldNomcomp, textFieldIdcomp;
	private JList listPersonne, listEquipe,listEquipe2, listCompetition,listCompetition2, listCandidat;
	private JTable tableEquipe, tablePersonne, tableCompetition, tableMembEquip, tableCandComp;
	
	Connection connection = null;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vue window = new vue();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 */
	
	public vue() {
		initialize();
		connection = SqlConnection.connect();
	}
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 893, 750);
		frame.setTitle("Inscriptions");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Inscriptions inscriptions = Inscriptions.getInscriptions();
		
		JLabel image = new JLabel(new ImageIcon("images/logo.png"));
		image.setBounds(108, 23, 767, 230);
		frame.getContentPane().add(image);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 266, 857, 388);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(tabbedPane);
		
		JPanel GrdpanelCompetition = new JPanel();
		tabbedPane.addTab("Gestion des compétitions", null, GrdpanelCompetition, null);
		GrdpanelCompetition.setLayout(null);
		
		
		JTabbedPane tabbedPaneComp = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneComp.setBounds(0, 0, 852, 357);
		GrdpanelCompetition.add(tabbedPaneComp);
		
		JPanel panelCompetition = new JPanel();
		panelCompetition.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				AfficheTable.AfficheTableCompetition(connection, tableCompetition);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		
		tabbedPaneComp.addTab("Créer une competition", null, panelCompetition, null);
		panelCompetition.setLayout(null);
		
		JRadioButton rdbtnOui = new JRadioButton("Oui");
		buttonGroup.add(rdbtnOui);
		rdbtnOui.setBounds(662, 141, 56, 25);
		panelCompetition.add(rdbtnOui);
		
		JRadioButton rdbtnNon = new JRadioButton("Non");
		buttonGroup.add(rdbtnNon);
		rdbtnNon.setBounds(720, 141, 56, 25);
		panelCompetition.add(rdbtnNon);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(-51, 0, 535, 327);
		panelCompetition.add(scrollPane_2);
		
		tableCompetition = new JTable();
		tableCompetition.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RemplirBarreSaisie.BarreSaisieCompetition(connection, tableCompetition, textFieldIdcomp, textFieldNomcomp, textFieldDatecloture, rdbtnOui, rdbtnNon);
			}
		});
		scrollPane_2.setViewportView(tableCompetition);
		
		JLabel labelIdcomp = new JLabel("id_comp :");
		labelIdcomp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelIdcomp.setBounds(557, 13, 72, 16);
		panelCompetition.add(labelIdcomp);
		
		JLabel labelNomcomp = new JLabel("Nom :");
		labelNomcomp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelNomcomp.setBounds(557, 55, 56, 16);
		panelCompetition.add(labelNomcomp);
		
		JLabel labelDatecloture = new JLabel("Date cloture :");
		labelDatecloture.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelDatecloture.setBounds(557, 96, 89, 16);
		panelCompetition.add(labelDatecloture);
		
		JLabel labelEnequipe = new JLabel("En Equipe :");
		labelEnequipe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelEnequipe.setBounds(557, 144, 78, 16);
		panelCompetition.add(labelEnequipe);
		
		textFieldDatecloture = new JTextField();
		textFieldDatecloture.setBounds(655, 94, 166, 22);
		panelCompetition.add(textFieldDatecloture);
		textFieldDatecloture.setColumns(10);
		
		textFieldNomcomp = new JTextField();
		textFieldNomcomp.setBounds(655, 53, 166, 22);
		panelCompetition.add(textFieldNomcomp);
		textFieldNomcomp.setColumns(10);
		
		textFieldIdcomp = new JTextField();
		textFieldIdcomp.setBounds(655, 11, 166, 22);
		panelCompetition.add(textFieldIdcomp);
		textFieldIdcomp.setColumns(10);
		

		
		JButton btnCreerComp = new JButton("Cr\u00E9er");
		btnCreerComp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int valeur = ControleSaisie.VerifCompetition(connection, textFieldNomcomp, textFieldDatecloture, rdbtnOui, rdbtnNon);
				
				if(valeur == 1)
				{
					JOptionPane.showMessageDialog(null, "Competition déjà existante !");
				}
				else
				{
					if(valeur == 2)
					{
						JOptionPane.showMessageDialog(null, "Champs non remplis !");
					}
					else
					{
							RequeteInsert.InsertCompetition(connection, textFieldNomcomp, textFieldDatecloture, rdbtnOui);
							Refresh.refreshTableCompetition(tableCompetition, connection);
					}
				}
			}
		});
		btnCreerComp.setBounds(665, 189, 97, 25);
		panelCompetition.add(btnCreerComp);
		
		JButton btnModifierComp = new JButton("Modifier");
		btnModifierComp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int valeur = ControleSaisie.VerifCompetition(connection, textFieldNomcomp, textFieldDatecloture, rdbtnOui, rdbtnNon);
				
				if(valeur == 1)
				{
					JOptionPane.showMessageDialog(null, "Aucune modification a été faite !");
				}
				else
				{
					if(valeur == 2)
					{
						JOptionPane.showMessageDialog(null, "Champs non remplis !");
					}
					else
					{
						RequeteUpdate.UpdateCompetition(connection, textFieldNomcomp, textFieldDatecloture, rdbtnOui, textFieldIdcomp);
						Refresh.refreshTableCompetition(tableCompetition, connection);
					}
				}
			}
		});
		btnModifierComp.setBounds(665, 227, 97, 25);
		panelCompetition.add(btnModifierComp);
		
		JButton btnSupprimerComp = new JButton("Supprimer");
		btnSupprimerComp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int valeur = ControleSaisie.VerifCompetition(connection, textFieldNomcomp, textFieldDatecloture, rdbtnOui, rdbtnNon);
				
				if(valeur == 1)
				{
					RequeteDelete.DeleteCompetition(connection, textFieldIdcomp);
					Refresh.refreshTableCompetition(tableCompetition, connection);
				}
				else
				{
					if(valeur == 2)
					{
						JOptionPane.showMessageDialog(null, "Il y a rien à supprimer !");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Erreur !");
					}
				}
			}
		});
		btnSupprimerComp.setBounds(665, 265, 97, 25);
		panelCompetition.add(btnSupprimerComp);
		
		
		JPanel panelAjoutCandidat = new JPanel();
		panelAjoutCandidat.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				loadlist.loadlistPersEqui(connection, listCandidat);
				loadlist.loadlistCompetition(connection, listCompetition);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		tabbedPaneComp.addTab("Inscription aux compétitions", null, panelAjoutCandidat, null);
		panelAjoutCandidat.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(12, 42, 285, 272);
		panelAjoutCandidat.add(scrollPane_5);
		
		listCandidat = new JList();
		scrollPane_5.setViewportView(listCandidat);
		listCandidat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(329, 42, 285, 272);
		panelAjoutCandidat.add(scrollPane_6);
		
		listCompetition = new JList();
		scrollPane_6.setViewportView(listCompetition);
		listCompetition.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnAjouterCand = new JButton("Ajouter");
		btnAjouterCand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object row = listCandidat.getSelectedValue();
				Object row1 = listCompetition.getSelectedValue();
				RequeteInsert.InsertCandComp(connection, row, row1);
			}
		});
		btnAjouterCand.setBounds(674, 49, 97, 25);
		panelAjoutCandidat.add(btnAjouterCand);
		
		JButton btnRetirerCand = new JButton("Retirer");
		btnRetirerCand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Object row = listCandidat.getSelectedValue();
				Object row1 = listCompetition.getSelectedValue();
				int valeur = ControleSaisie.VerifCandComp(connection, row, row1);
				if(valeur == 1)
				{
					JOptionPane.showMessageDialog(null, "Champs non selectionné ! ");
				}
				else
				{
					if(valeur == 2)
					{
						RequeteDelete.DeleteCandComp(connection, row, row1);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Erreur ! ");
					}
				}
				
			}
		});
		btnRetirerCand.setBounds(674, 99, 97, 25);
		panelAjoutCandidat.add(btnRetirerCand);
		
		JLabel labelCand = new JLabel("Candidats");
		labelCand.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelCand.setBounds(118, 13, 78, 16);
		panelAjoutCandidat.add(labelCand);
		
		JLabel labelComp = new JLabel("Comp\u00E9titions");
		labelComp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelComp.setBounds(422, 13, 88, 16);
		panelAjoutCandidat.add(labelComp);
		
		
		
		JPanel panelCandidatComp = new JPanel();
		panelCandidatComp.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				loadlist.loadlistCompetition2(connection, listCompetition2);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		tabbedPaneComp.addTab("Les candidats aux compétitions", null, panelCandidatComp, null);
		panelCandidatComp.setLayout(null);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(23, 38, 235, 276);
		panelCandidatComp.add(scrollPane_7);
		
		listCompetition2 = new JList();
		scrollPane_7.setViewportView(listCompetition2);
		listCompetition2.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Object selectedValue = listCompetition2.getSelectedValue();
				AfficheTable.AfficheTableCandComp(connection, tableCandComp, selectedValue);
			}
		});
		listCompetition2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel labelComp2 = new JLabel("Competitions");
		labelComp2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelComp2.setBounds(100, 13, 97, 16);
		panelCandidatComp.add(labelComp2);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(327, 38, 479, 276);
		panelCandidatComp.add(scrollPane_4);
		
		tableCandComp = new JTable();
		scrollPane_4.setViewportView(tableCandComp);
		
		
		
		JPanel GrdpanelPersonne = new JPanel();
		tabbedPane.addTab("Gestion des personnes", null, GrdpanelPersonne, null);
		GrdpanelPersonne.setLayout(null);
		
		JTabbedPane tabbedPanePers = new JTabbedPane(JTabbedPane.TOP);
		tabbedPanePers.setBounds(0, 0, 852, 357);
		GrdpanelPersonne.add(tabbedPanePers);
		
		
		
		
		JPanel panelPersonne = new JPanel();
		panelPersonne.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				AfficheTable.AfficheTablePersonne(connection, tablePersonne);
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		tabbedPanePers.addTab("Inscriptions personnes", null, panelPersonne, null);
		panelPersonne.setLayout(null);
		
		
		JLabel labelNompers = new JLabel("Nom :");
		labelNompers.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelNompers.setBounds(557, 55, 56, 16);
		panelPersonne.add(labelNompers);
		
		JLabel labelPrenompers = new JLabel("Prenom :");
		labelPrenompers.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelPrenompers.setBounds(557, 96, 89, 16);
		panelPersonne.add(labelPrenompers);
		
		JLabel labelEmailpers = new JLabel("Email :");
		labelEmailpers.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelEmailpers.setBounds(557, 144, 78, 16);
		panelPersonne.add(labelEmailpers);
		
		textFieldNompers = new JTextField();
		textFieldNompers.setBounds(655, 53, 166, 22);
		panelPersonne.add(textFieldNompers);
		textFieldNompers.setColumns(10);
		
		textFieldPrenompers = new JTextField();
		textFieldPrenompers.setBounds(655, 94, 166, 22);
		panelPersonne.add(textFieldPrenompers);
		textFieldPrenompers.setColumns(10);
		
		textFieldEmailpers = new JTextField();
		textFieldEmailpers.setBounds(655, 142, 166, 22);
		panelPersonne.add(textFieldEmailpers);
		textFieldEmailpers.setColumns(10);
		
		JButton btnCreerPers = new JButton("Cr\u00E9er");
		btnCreerPers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Personne personne = inscriptions.createPersonne(textFieldNompers.getText(), textFieldPrenompers.getText(), textFieldEmailpers.getText());
				int valeur = ControleSaisie.VerifPersonne(connection, personne);
				
				if(valeur == 1)
				{
					JOptionPane.showMessageDialog(null, "Personne déjà existante !", null, JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(valeur == 2)
					{
						JOptionPane.showMessageDialog(null, "Champs non remplis !");
					}
					else
					{
						RequeteInsert.InsertPersonne(connection,personne);
						Refresh.refreshTablePersonne(tablePersonne, connection);
					}
				}
			}
		});
		btnCreerPers.setBounds(665, 189, 97, 25);
		panelPersonne.add(btnCreerPers);
		
		JButton btnModifierPers = new JButton("Modifier");
		btnModifierPers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Personne personne = inscriptions.createPersonne(textFieldNompers.getText(), textFieldPrenompers.getText(), textFieldEmailpers.getText());
				int valeur = ControleSaisie.VerifPersonne(connection, personne);
				
				if(valeur == 1)
				{
					JOptionPane.showMessageDialog(null, "Aucune modification a été faite !");
				}
				else
				{
					if(valeur == 2)
					{
						JOptionPane.showMessageDialog(null, "Champs non remplis !");
					}
					else
					{
						RequeteUpdate.UpdatePersonne(connection, personne, textFieldIdcandP);
						Refresh.refreshTablePersonne(tablePersonne, connection);
					}
				}
			}});
		btnModifierPers.setBounds(665, 227, 97, 25);
		panelPersonne.add(btnModifierPers);
		
		JButton btnSupprimerPers = new JButton("Supprimer");
		btnSupprimerPers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Personne personne = inscriptions.createPersonne(textFieldNompers.getText(), textFieldPrenompers.getText(), textFieldEmailpers.getText());
				int valeur = ControleSaisie.VerifPersonne(connection, personne);
				
				if(valeur == 1)
				{
					RequeteDelete.DeletePersonne(connection, textFieldIdcandP);
					Refresh.refreshTablePersonne(tablePersonne, connection);
				}
				else
				{
					if(valeur == 2)
					{
						JOptionPane.showMessageDialog(null, "Il y a rien à supprimer !");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Erreur !");
					}
				}
				
			}
		});
		btnSupprimerPers.setBounds(665, 265, 97, 25);
		panelPersonne.add(btnSupprimerPers);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 535, 327);
		panelPersonne.add(scrollPane);
		
		tablePersonne = new JTable();
		tablePersonne.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				RemplirBarreSaisie.BarreSaisiePersonne(connection, tablePersonne, textFieldIdcandP, textFieldNompers, textFieldPrenompers, textFieldEmailpers);
			}
		});
		scrollPane.setViewportView(tablePersonne);
		
		textFieldIdcandP = new JTextField();
		textFieldIdcandP.setBounds(655, 11, 166, 22);
		panelPersonne.add(textFieldIdcandP);
		textFieldIdcandP.setColumns(10);
		
		JLabel labelIdcandP = new JLabel("id_candP :");
		labelIdcandP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelIdcandP.setBounds(557, 13, 72, 16);
		panelPersonne.add(labelIdcandP);
		
		
		JPanel panelAjoutPersonne = new JPanel();
		panelAjoutPersonne.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				loadlist.loadlistPersonne(connection, listPersonne);
				loadlist.loadlistEquipe(connection, listEquipe);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		tabbedPanePers.addTab("Ajouter une personne à une équipe", null, panelAjoutPersonne, null);
		panelAjoutPersonne.setLayout(null);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(12, 42, 285, 272);
		panelAjoutPersonne.add(scrollPane_8);
		
		listPersonne = new JList();
		scrollPane_8.setViewportView(listPersonne);
		listPersonne.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(329, 42, 285, 272);
		panelAjoutPersonne.add(scrollPane_9);
		
		listEquipe = new JList();
		scrollPane_9.setViewportView(listEquipe);
		listEquipe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnAjouterPers = new JButton("Ajouter");
		btnAjouterPers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object row = listPersonne.getSelectedValue();
				Object row1 = listEquipe.getSelectedValue();
				RequeteInsert.InsertPersEqui(connection, row, row1);
				
			}
		});
		btnAjouterPers.setBounds(674, 49, 97, 25);
		panelAjoutPersonne.add(btnAjouterPers);
		
		JButton btnRetirerPers = new JButton("Retirer");
		btnRetirerPers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object row = listPersonne.getSelectedValue();
				Object row1 = listEquipe.getSelectedValue();
				int valeur = ControleSaisie.VerifPersEqui(connection, row, row1);
				if(valeur == 1)
				{
					JOptionPane.showMessageDialog(null, "Champs non selectionné ! ");
				}
				else
				{
					if(valeur == 2)
					{
						RequeteDelete.DeletePersEqui(connection, row, row1);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Erreur ! ");
					}
				}
				
			}
		});
		btnRetirerPers.setBounds(674, 99, 97, 25);
		panelAjoutPersonne.add(btnRetirerPers);
		
		JLabel labelPers = new JLabel("Personnes");
		labelPers.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelPers.setBounds(118, 13, 78, 16);
		panelAjoutPersonne.add(labelPers);
		
		JLabel labelEqui = new JLabel("Equipes");
		labelEqui.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelEqui.setBounds(422, 13, 88, 16);
		panelAjoutPersonne.add(labelEqui);
		
		
		
		
		JPanel GrdpanelEquipe = new JPanel();
		tabbedPane.addTab("Gestion des équipes", null, GrdpanelEquipe, null);
		GrdpanelEquipe.setLayout(null);
		
		JTabbedPane tabbedPaneEqui = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneEqui.setBounds(0, 0, 852, 357);
		GrdpanelEquipe.add(tabbedPaneEqui);
		
		
		
		JPanel panelEquipe = new JPanel();
		panelEquipe.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				AfficheTable.AfficheTableEquipe(connection, tableEquipe);
			}
			public void ancestorMoved(AncestorEvent event) {
				
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		tabbedPaneEqui.addTab("Inscriptions équipes", null, panelEquipe, null);
		panelEquipe.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 535, 327);
		panelEquipe.add(scrollPane_1);
		
		tableEquipe = new JTable();
		tableEquipe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RemplirBarreSaisie.BarreSaisieEquipe(connection, tableEquipe, textFieldIdcandE, textFieldNomequi);
			}
		});
		scrollPane_1.setViewportView(tableEquipe);
		
		JLabel labelNomequi = new JLabel("Nom :");
		labelNomequi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelNomequi.setBounds(561, 105, 56, 16);
		panelEquipe.add(labelNomequi);
		
		textFieldNomequi = new JTextField();
		textFieldNomequi.setColumns(10);
		textFieldNomequi.setBounds(629, 103, 181, 22);
		panelEquipe.add(textFieldNomequi);
		
		JButton btnCreerEqui = new JButton("Cr\u00E9er");
		btnCreerEqui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int valeur = ControleSaisie.VerifEquipe(connection, textFieldNomequi);
				
				if(valeur == 1)
				{
					JOptionPane.showMessageDialog(null, "Equipe déjà existante !");
				}
				else
				{
					if(valeur == 2)
					{
						JOptionPane.showMessageDialog(null, "Champs non remplis !");
					}
					else
					{
						RequeteInsert.InsertEquipe(connection, textFieldNomequi);
						Refresh.refreshTableEquipe(tableEquipe, connection);
					}
				}
				
			}
		});
		btnCreerEqui.setBounds(665, 189, 97, 25);
		panelEquipe.add(btnCreerEqui);
		
		JButton btnModifierEqui = new JButton("Modifier");
		btnModifierEqui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int valeur = ControleSaisie.VerifEquipe(connection, textFieldNomequi);
				
				if(valeur == 1)
				{
					JOptionPane.showMessageDialog(null, "Aucune modification a été faite !");
				}
				else
				{
					if(valeur == 2)
					{
						JOptionPane.showMessageDialog(null, "Champs non remplis !");
					}
					else
					{
						RequeteUpdate.UpdateEquipe(connection, textFieldNomequi, textFieldIdcandE);
						Refresh.refreshTableEquipe(tableEquipe, connection);
					}
				}
				
			}
		});
		btnModifierEqui.setBounds(665, 227, 97, 25);
		panelEquipe.add(btnModifierEqui);
		
		JButton btnSupprimerEqui = new JButton("Supprimer");
		btnSupprimerEqui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int valeur = ControleSaisie.VerifEquipe(connection, textFieldNomequi);
				if(valeur == 1)
				{
					RequeteDelete.DeleteEquipe(connection, textFieldIdcandE);
					Refresh.refreshTableEquipe(tableEquipe, connection);
				}
				else
				{
					if(valeur == 2)
					{
						JOptionPane.showMessageDialog(null, "Il y a rien à supprimer !");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Erreur !");
					}
				}
				
			}
		});
		btnSupprimerEqui.setBounds(665, 265, 97, 25);
		panelEquipe.add(btnSupprimerEqui);
		
		textFieldIdcandE = new JTextField();
		textFieldIdcandE.setBounds(629, 58, 181, 22);
		panelEquipe.add(textFieldIdcandE);
		textFieldIdcandE.setColumns(10);
		
		JLabel labelIdcandE = new JLabel("id_candE :");
		labelIdcandE.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelIdcandE.setBounds(554, 60, 77, 16);
		panelEquipe.add(labelIdcandE);
		

		

		
		JPanel panelMembreEqui = new JPanel();
		panelMembreEqui.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				loadlist.loadlistEquipe2(connection, listEquipe2);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		tabbedPaneEqui.addTab("Membre des équipes", null, panelMembreEqui, null);
		panelMembreEqui.setLayout(null);
		
		JScrollPane scrollPane_10 = new JScrollPane();
		scrollPane_10.setBounds(23, 38, 235, 276);
		panelMembreEqui.add(scrollPane_10);
		
		listEquipe2 = new JList();
		scrollPane_10.setViewportView(listEquipe2);
		listEquipe2.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Object selectedValue = listEquipe2.getSelectedValue();
				AfficheTable.AfficheTableMembEquip(connection, tableMembEquip, selectedValue);
			}
		});
		listEquipe2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel labelEqui2 = new JLabel("Equipes");
		labelEqui2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelEqui2.setBounds(100, 13, 97, 16);
		panelMembreEqui.add(labelEqui2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(327, 38, 479, 276);
		panelMembreEqui.add(scrollPane_3);
		
		tableMembEquip = new JTable();
		scrollPane_3.setViewportView(tableMembEquip);
		
	}
}