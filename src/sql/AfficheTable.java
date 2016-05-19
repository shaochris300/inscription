package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class AfficheTable {
	public static void AfficheTableCompetition(Connection connection, JTable tableCompetition)
	{
		try
		{
			String query = "select * from competition";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableCompetition.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
		}
		catch(Exception e1)
		{
			
		}
	}
	
	public static void AfficheTableCandComp(Connection connection,JTable tableCandComp, Object selectedValue)
	{
		try
		{
			String query = "SELECT IF(candidat.id_cand = personne.id_candP && candidat.id_cand = inscrire.id_cand, personne.nom, IF(candidat.id_cand = equipe.id_candE && candidat.id_cand = inscrire.id_cand, equipe.nom, '')) AS 'nom' FROM candidat, equipe, personne, inscrire, competition WHERE inscrire.id_comp = competition.id_comp AND competition.nom = '"+selectedValue+"' GROUP BY nom LIMIT 100 OFFSET 1";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableCandComp.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
		}
		catch(Exception e1)
		{
			
		}
	}
	
	public static void AfficheTablePersonne(Connection connection, JTable tablePersonne)
	{
		try
		{
			String query = "select * from personne";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tablePersonne.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
		}
		catch(Exception e1)
		{
			
		}
	}
	
	public static void AfficheTableEquipe(Connection connection, JTable tableEquipe)
	{
		try
		{
			String query = "select * from equipe";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableEquipe.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
		}
		catch(Exception e1)
		{
			
		}
	}
	
	public static void AfficheTableMembEquip (Connection connection, JTable tableMembEquip, Object selectedValue)
	{
		try
		{
			String query = "select prenom, personne.nom, mail FROM appartenir, equipe, personne WHERE appartenir.id_candE = equipe.id_candE AND appartenir.id_candP = personne.id_candP AND equipe.nom = '"+selectedValue+"'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableMembEquip.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
		}
		catch(Exception e1)
		{
			
		}
	}
}
