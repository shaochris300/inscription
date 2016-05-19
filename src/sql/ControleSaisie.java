package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import metier.Personne;

public class ControleSaisie {
	
	public static int VerifCompetition(Connection connection, JTextField textFieldNomcomp, JTextField textFieldDatecloture, JRadioButton rdbtnOui, JRadioButton rdbtnNon)
	{
		int valeur =0;
		try
		{
			int enEquipe =0;
			if(rdbtnOui.isSelected())
			{
				enEquipe = 1;
			}
			String query = "SELECT * FROM competition WHERE nom = '"+textFieldNomcomp.getText()+"' AND datecloture ='"+textFieldDatecloture.getText()+"' AND enEquipe = '"+enEquipe+"'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				valeur = 1;
			}
			else
			{
				if(textFieldNomcomp.getText().isEmpty()|| textFieldDatecloture.getText().isEmpty() || rdbtnOui == null && rdbtnNon == null)
				{
					valeur = 2;
				}

			}
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, "Erreur !");
		}
		return valeur;
	}
	
	public static int VerifPersonne(Connection connection, Personne personne)
	{
		int valeur = 0;
		try
		{
			String query = "SELECT * FROM personne WHERE nom = '"+personne.getNom()+"' AND prenom ='"+personne.getPrenom()+"' AND mail = '"+personne.getMail()+"'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				valeur = 1;
			}
			else
			{
				if(personne.getNom().isEmpty()|| personne.getPrenom().isEmpty() || personne.getMail().isEmpty() )
				{
					valeur = 2;
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Erreur !");
		}
		return valeur;
	}
	
	public static int VerifEquipe(Connection connection, JTextField textFieldNomequi)
	{
		int valeur = 0;
		try
		{
			String query = "SELECT * FROM equipe WHERE nom = '"+textFieldNomequi.getText()+"'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				valeur = 1;
			}
			else
			{
				if(textFieldNomequi.getText().isEmpty())
				{
					valeur = 2;
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Erreur !");
		}
		return valeur;
	}
	
	public static int VerifCandComp(Connection connection, Object row, Object row1)
	{	
		int valeur =0;
		try
		{
			if(row == null || row1 == null)
			{
				valeur =1;
			}
			else
			{
				int n = 0;
				int n2 =0;
				String query = "SELECT * FROM personne WHERE prenom = '"+row +"'  ";
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet idcand = pst.executeQuery();
				
				if(idcand.next())
				{
					n = idcand.getInt("id_candP");
				}
				else
				{
					String query1 = "SELECT * FROM equipe WHERE nom = '"+ row +"'";
					PreparedStatement pst1 = connection.prepareStatement(query1);
					ResultSet idcand1 = pst1.executeQuery();
					idcand1.next();
					n = idcand1.getInt("id_candE");
				}
				
				String query2 = "SELECT id_comp FROM competition WHERE nom = '"+ row1 +"'";
				PreparedStatement pst2 = connection.prepareStatement(query2);
				ResultSet idcand2 = pst2.executeQuery();
				idcand2.next();
				n2 = idcand2.getInt("id_comp");
				
				String query3 = "SELECT * FROM inscrire WHERE id_cand = '"+n +"' and id_comp= '"+n2+"'";
				PreparedStatement pst3 = connection.prepareStatement(query3);
				ResultSet idcand3 = pst3.executeQuery();
			
				if(idcand3.next())
				{
					valeur = 2;
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Erreur !");
		}
		
		return valeur;
	}
	
	public static int VerifPersEqui(Connection connection, Object row, Object row1)
	{
		int valeur =0;
		try
		{
			if(row == null || row1 == null)
			{
				valeur =1;
			}
			else
			{
				int n = 0;
				int n2 =0;
				String query = "SELECT id_candP FROM personne WHERE prenom = '"+ row +"'";
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet idcand = pst.executeQuery();
				idcand.next();
				n = idcand.getInt("id_candP");
				
				String query1 = "SELECT id_candE FROM equipe WHERE nom = '"+ row1 +"'";
				PreparedStatement pst1 = connection.prepareStatement(query1);
				ResultSet idcand1 = pst1.executeQuery();
				idcand1.next();
				n2 = idcand1.getInt("id_candE");
				
				String query2 = "SELECT * FROM appartenir WHERE id_candP = '"+n +"' and id_candE= '"+n2+"'";
				PreparedStatement pst2 = connection.prepareStatement(query2);
				ResultSet idcand2 = pst2.executeQuery();
				
				if(idcand2.next())
				{
					valeur =2;
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Erreur !");
		}
		return valeur;
	}
	
}
