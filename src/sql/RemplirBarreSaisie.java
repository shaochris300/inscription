package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class RemplirBarreSaisie {

	public static void BarreSaisieCompetition(Connection connection, JTable tableCompetition, JTextField textFieldIdcomp, JTextField textFieldNomcomp, JTextField textFieldDatecloture, JRadioButton rdbtnOui,  JRadioButton rdbtnNon)
	{
		try
		{	
			int row = tableCompetition.getSelectedRow();
			String id_comp = (tableCompetition.getModel().getValueAt(row, 0)).toString();
			String query = "select * from competition where id_comp = '"+id_comp+"' ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				textFieldIdcomp.setText(rs.getString("id_comp"));
				textFieldNomcomp.setText(rs.getString("nom"));
				textFieldDatecloture.setText(rs.getString("datecloture"));
				if(rs.getInt("enEquipe") == 1)
				{
					rdbtnOui.setSelected(true);
					rdbtnNon.setSelected(false);
				}
				else
				{
					rdbtnOui.setSelected(false);
					rdbtnNon.setSelected(true);
				}
			} 
			rs.close();
			pst.close(); 
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		
		}
	}
	
	public static void BarreSaisieEquipe(Connection connection, JTable tableEquipe, JTextField textFieldIdcandE,JTextField textFieldNomequi)
	{
		try
		{	
			int row = tableEquipe.getSelectedRow();
			String id_candE = (tableEquipe.getModel().getValueAt(row, 0)).toString();
			String query = "select * from equipe where id_candE = '"+id_candE+"' ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				textFieldIdcandE.setText(rs.getString("id_candE"));
				textFieldNomequi.setText(rs.getString("nom"));
			} 
			rs.close();
			pst.close(); 
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	public static void BarreSaisiePersonne(Connection connection, JTable tablePersonne,JTextField textFieldIdcandP, JTextField textFieldNompers, JTextField textFieldPrenompers, JTextField textFieldEmailpers)
	{
		try
		{	
			int row = tablePersonne.getSelectedRow();
			String id_candP = (tablePersonne.getModel().getValueAt(row, 0)).toString();
			String query = "select * from personne where id_candP = '"+id_candP+"' ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				textFieldIdcandP.setText(rs.getString("id_candP"));
				textFieldNompers.setText(rs.getString("nom"));
				textFieldPrenompers.setText(rs.getString("prenom"));
				textFieldEmailpers.setText(rs.getString("mail"));
			} 
			rs.close();
			pst.close(); 
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
}
