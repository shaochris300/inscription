package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import metier.Personne;

public class RequeteUpdate {
	
	public static void UpdateCompetition(Connection connection, JTextField textFieldNomcomp, JTextField textFieldDatecloture, JRadioButton rdbtnOui, JTextField textFieldIdcomp)
	{
		try
		{	
			int enEquipe =0;
			if(rdbtnOui.isSelected())
			{
				enEquipe=1;
			}
			String query1 = "UPDATE competition SET nom = '"+ textFieldNomcomp.getText()+"' , datecloture = '"+ textFieldDatecloture.getText()+"' ,enEquipe = '"+ enEquipe +"' WHERE id_comp = '"+textFieldIdcomp.getText()+"' ";
			PreparedStatement pst1 = connection.prepareStatement(query1);
			pst1.execute();
				
			pst1.close();
			JOptionPane.showMessageDialog(null, "Données modifiées !");
		
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, "Erreur  !");
		}
	}
	
	public static void UpdatePersonne(Connection connection,Personne personne, JTextField textFieldIdcandP)
	{
		try
		{
			String query1 = "UPDATE personne SET nom = '"+ personne.getNom()+"' , prenom = '"+ personne.getPrenom()+"' ,mail = '"+ personne.getMail()+"' WHERE id_candP = '"+textFieldIdcandP.getText()+"' ";
			PreparedStatement pst1 = connection.prepareStatement(query1);
			pst1.execute();
				
			pst1.close();
			JOptionPane.showMessageDialog(null, "Données modifiées !");
			
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, "Erreur  !");
		}
	}
	
	public static void UpdateEquipe(Connection connection, JTextField textFieldNomequi, JTextField textFieldIdcandE)
	{
		try
		{
			String query1 = "UPDATE equipe SET nom = '"+ textFieldNomequi.getText()+"'WHERE id_candE = '"+textFieldIdcandE.getText()+"' ";
			PreparedStatement pst1 = connection.prepareStatement(query1);
			pst1.execute();
				
			pst1.close();
			JOptionPane.showMessageDialog(null, "Données modifiées !");
			
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, "Erreur  !");
		}
	}
}
