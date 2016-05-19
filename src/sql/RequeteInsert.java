package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import metier.Personne;

public class RequeteInsert {
	
	public static void InsertCompetition(Connection connection, JTextField textFieldNomcomp, JTextField textFieldDatecloture, JRadioButton rdbtnOui)
	{	
		try
		{
				int enEquipe=0;
				if(rdbtnOui.isSelected())
				{
					enEquipe = 1;
				}
				String query1 = "INSERT INTO competition(nom, datecloture, enEquipe) VALUES (?,?,?)";
				PreparedStatement pst1 = connection.prepareStatement(query1);
				pst1.setString(1, textFieldNomcomp.getText());
				pst1.setString(2, textFieldDatecloture.getText());
				pst1.setInt(3, enEquipe);
				pst1.execute();

				pst1.close();
				JOptionPane.showMessageDialog(null, "Données sauvegardées !");
			
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, "Erreur !");
		}
	}
	
	public static void InsertCandComp(Connection connection, Object row, Object row1)
	{
		try
		{
			int n = 0;
			int n2 =0;
			boolean valeur = false;
			boolean valeur2 = false;
			String query = "SELECT * FROM personne WHERE prenom = '"+row +"'  ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet idcand = pst.executeQuery();
			
			if(idcand.next())
			{
				n = idcand.getInt("id_candP");
				valeur = true;
			}
			else
			{
				String query2 = "SELECT * FROM equipe WHERE nom = '"+ row +"'";
				PreparedStatement pst1 = connection.prepareStatement(query2);
				ResultSet idcand2 = pst1.executeQuery();
				idcand2.next();
				n = idcand2.getInt("id_candE");
			}
			
			String query3 = "SELECT * FROM competition WHERE nom = '"+ row1 +"'";
			PreparedStatement pst2 = connection.prepareStatement(query3);
			ResultSet idcand3 = pst2.executeQuery();
			idcand3.next();
			n2 = idcand3.getInt("id_comp");
			valeur2 = idcand3.getBoolean("enEquipe");
			
			if(valeur == true && valeur2 == false || valeur == false && valeur2 == true)
			{
				String query1 = "INSERT INTO inscrire(id_cand, id_comp) VALUES ('"+ n + "', '"+n2+"')";
				Statement s = connection.createStatement();
				s.executeUpdate(query1);
				JOptionPane.showMessageDialog(null, "Données sauvegardées !");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Le candidat et l'équipe ne correspondent pas !");
			}

			
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, "Erreur !");
		}

	}
	
	public static void InsertPersonne(Connection connection, Personne personne)
	{
		try
		{
				LocalDateTime datecreation = LocalDateTime.now();
				String query1 = "INSERT INTO candidat(datecreation) VALUES ('"+ datecreation+ "')";
				Statement s = connection.createStatement();
				s.executeUpdate(query1);

				int n = 0;
				String query2 = "SELECT id_cand FROM candidat WHERE datecreation = '"+ datecreation +"'";
				PreparedStatement pst2 = connection.prepareStatement(query2);
				ResultSet idcand = pst2.executeQuery();
				idcand.next();
				n = idcand.getInt("id_cand");
		
				String query3 = "INSERT INTO personne(id_candP,nom,prenom, mail) VALUES (?,?,?,?)";
				PreparedStatement pst3 = connection.prepareStatement(query3);
				pst3.setInt(1, n);
				pst3.setString(2, personne.getNom());
				pst3.setString(3, personne.getPrenom());
				pst3.setString(4, personne.getMail());
					pst3.execute();

				pst2.close();
				pst3.close(); 
				JOptionPane.showMessageDialog(null, "Données sauvegardées !");
		
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, "Erreur !");
		}
	}
	
	public static void InsertPersEqui(Connection connection, Object row, Object row1)
	{
		try
		{
			int n = 0;
			int n2= 0;
			String query = "SELECT id_candP FROM personne WHERE prenom = '"+ row +"'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet idcand = pst.executeQuery();
			idcand.next();
			n = idcand.getInt("id_candP");
			
			String query2 = "SELECT id_candE FROM equipe WHERE nom = '"+ row1 +"'";
			PreparedStatement pst1 = connection.prepareStatement(query2);
			ResultSet idcand2 = pst1.executeQuery();
			idcand2.next();
			n2 = idcand2.getInt("id_candE");
		
			String query1 = "INSERT INTO appartenir(id_candP, id_candE) VALUES ('"+ n + "', '"+n2+"')";
			Statement s = connection.createStatement();
			s.executeUpdate(query1);

			pst.close();
			s.close();
			JOptionPane.showMessageDialog(null, "Données sauvegardées !");
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, "Erreur !");
		}
	}
	
	public static void InsertEquipe(Connection connection, JTextField textFieldNomequi)
	{
		try
		{
				LocalDateTime datecreation = LocalDateTime.now();
				String query1 = "INSERT INTO candidat(datecreation) VALUES ('"+ datecreation+ "')";
				Statement s = connection.createStatement();
				s.executeUpdate(query1);

				int n = 0;
				String query2 = "SELECT id_cand FROM candidat WHERE datecreation = '"+ datecreation +"'";
				PreparedStatement pst2 = connection.prepareStatement(query2);
				ResultSet idcand = pst2.executeQuery();
				idcand.next();
				n = idcand.getInt("id_cand");
		
				String query3 = "INSERT INTO equipe(id_candE,nom) VALUES (?,?)";
				PreparedStatement pst3 = connection.prepareStatement(query3);
				pst3.setInt(1, n);
				pst3.setString(2,textFieldNomequi.getText());
				pst3.execute();
				
				pst2.close();
				pst3.close();
				JOptionPane.showMessageDialog(null, "Données sauvegardées !");
				
		
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, "Erreur !");
		}
	}
}
