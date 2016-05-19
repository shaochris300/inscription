package sql;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class Refresh {
	public static void refreshTablePersonne(JTable tablePersonne, Connection connection)
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
	
	public static void refreshTableEquipe(JTable tableEquipe, Connection connection)
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
	
	public static void refreshTableCompetition(JTable tableCompetition, Connection connection)
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
}
