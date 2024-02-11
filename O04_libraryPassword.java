package assessment_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class O04_libraryPassword {

	public boolean equalPass() {
		boolean b = false;
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the old passwod ");
		String pass = sc.next();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assessment", "root", "");

			PreparedStatement ps = cn.prepareStatement("select password from pwd where password = ?");
			ps.setString(1, pass);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				b = true;
			} else {
				System.out.println("incurrect old  password");
				b = false;
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;
	}

}
