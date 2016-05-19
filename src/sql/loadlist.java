package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class loadlist {
	
	public static void loadlistPersonne(Connection connection, JList listPersonne)
	{
		try
		{	
			String query = "select * from personne ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			DefaultListModel DLM = new DefaultListModel();
			
			while(rs.next())
			{
				DLM.addElement(rs.getString("prenom"));

			} 
			listPersonne.setModel(DLM);
			rs.close();
			pst.close(); 
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	public static void loadlistEquipe(Connection connection, JList listEquipe)
	{
		try
		{	
			String query = "select * from equipe ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			DefaultListModel DLM = new DefaultListModel();
			
			while(rs.next())
			{
				DLM.addElement(rs.getString("nom"));

			} 
			listEquipe.setModel(DLM);
			rs.close();
			pst.close(); 
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	public static void loadlistPersEqui(Connection connection, JList listCandidat)
	{
		try
		{	
			
			String query1 = "select * from personne ";
			PreparedStatement pst1 = connection.prepareStatement(query1);
			ResultSet rs1 = pst1.executeQuery();
			
			String query2 = "select * from equipe ";
			PreparedStatement pst2 = connection.prepareStatement(query2);
			ResultSet rs2 = pst2.executeQuery();
			
			DefaultListModel DLM = new DefaultListModel();
			
			while(rs1.next())
			{
				DLM.addElement(rs1.getString("prenom"));
			} 
			
			while(rs2.next())
			{
				DLM.addElement(rs2.getString("nom"));
			}
			
			listCandidat.setModel(DLM);
			rs1.close();
			pst1.close(); 
			rs2.close();
			pst2.close(); 
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	public static void loadlistCompetition(Connection connection, JList listCompetition)
	{
		try
		{	
			String query = "select * from competition ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			DefaultListModel DLM = new DefaultListModel();
			
			while(rs.next())
			{
				DLM.addElement(rs.getString("nom"));

			} 
			listCompetition.setModel(DLM);
			rs.close();
			pst.close(); 
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	public static void loadlistEquipe2(Connection connection, JList listEquipe2)
	{
		try
		{	
			String query = "select * from equipe ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			DefaultListModel DLM = new DefaultListModel();
			
			while(rs.next())
			{
				DLM.addElement(rs.getString("nom"));

			} 
			listEquipe2.setModel(DLM);
			rs.close();
			pst.close(); 
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	public static void loadlistCompetition2(Connection connection, JList listCompetition2)
	{
		try
		{	
			String query = "select * from competition ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			DefaultListModel DLM = new DefaultListModel();
			
			while(rs.next())
			{
				DLM.addElement(rs.getString("nom"));

			} 
			listCompetition2.setModel(DLM);
			rs.close();
			pst.close(); 
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
}
