package sql;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RequeteDelete {
	
	public static void DeletePersonne(Connection connection, JTextField textFieldIdcandP)
	{
		try
		{
			String query1 = "delete from candidat where id_cand = '"+textFieldIdcandP.getText()+"'";
			Statement s = connection.createStatement();
			s.executeUpdate(query1);
			s.close();
			JOptionPane.showMessageDialog(null, "Données supprimer !");
			
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, "Erreur !");
		}
	}
	
	public static void DeleteEquipe(Connection connection, JTextField textFieldIdcandE)
	{
		try
		{
			String query1 = "delete from candidat where id_cand = '"+textFieldIdcandE.getText()+"'";
			Statement s = connection.createStatement();
			s.executeUpdate(query1);
			s.close();
			JOptionPane.showMessageDialog(null, "Données supprimées !");
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, "Erreur !");
		}
	}
	
	public static void DeleteCompetition(Connection connection, JTextField textFieldIdcomp)
	{
		try
		{
			String query1 = "delete from competition where id_comp = '"+textFieldIdcomp.getText()+"'";
			Statement s = connection.createStatement();
			s.executeUpdate(query1);
			s.close();
			JOptionPane.showMessageDialog(null, "Données supprimer !");
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, "Erreur !");
		}
	}
	
	public static void DeleteCandComp(Connection connection, Object row, Object row1)
	{
		try
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
		
			String query4 = "DELETE FROM inscrire WHERE id_cand = '"+n +"' and id_comp = '"+n2+"' ";
			Statement s = connection.createStatement();
			s.executeUpdate(query4);
			JOptionPane.showMessageDialog(null, "Données supprimées !");

			pst.close();
			pst2.close();
			
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, "Erreur !");
		}
	}
	
	public static void DeletePersEqui(Connection connection, Object row, Object row1)
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
			
			String query1 = "SELECT id_candE FROM equipe WHERE nom = '"+ row1 +"'";
			PreparedStatement pst1 = connection.prepareStatement(query1);
			ResultSet idcand1 = pst1.executeQuery();
			idcand1.next();
			n2 = idcand1.getInt("id_candE");
			
			String query3 = "DELETE FROM appartenir WHERE id_candP = '"+n +"' and id_candE = '"+n2+"' ";
			Statement s = connection.createStatement();
			s.executeUpdate(query3);
			
			pst1.close();
			pst.close();
			JOptionPane.showMessageDialog(null, "Données supprimées !");
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, "Erreur !");
		}
	}
}
