package sql;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class SqlConnection {
	Connection conn =null;
	
	public static Connection connect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://192.168.100.3/inscription";
			/*String url = "jdbc:mysql://localhost/inscription";*/
			String user = "root";
			Connection c = DriverManager.getConnection(url,user, "");
			JOptionPane.showMessageDialog(null, "Connexion réussi !");
			return c;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}