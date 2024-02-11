package assessment_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class O01_libraryMain {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Connection cn = null;
		int choice = 0;

		System.out.println("Enter the password");
		String password = sc.next();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assessment", "root", "");

			PreparedStatement ps = cn.prepareStatement("select * from pwd where password = ?");
			ps.setString(1, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("****** MAIN MENU *******");

				System.out.println("1. Add book");
				System.out.println("2. Delete Book");
				System.out.println("3. Search Book");
				System.out.println("4. View Book list ");
				System.out.println("5. Edit Book Record");
				System.out.println("6. Change password");
				System.out.println("7. Close Application");

				System.out.println("**************************");

				System.out.println("Enter your choise ");
				choice = sc.nextInt();

				O02_libraryOpreration bookOpe = new O02_libraryOpreration();

				switch (choice) {
				case 1:
					bookOpe.addBook();
					break;

				case 2:
					bookOpe.daleteBook();
					break;

				case 3:
					bookOpe.searchBook();
					break;

				case 4:
					bookOpe.ViewBook();
					break;

				case 5:
					bookOpe.editBook();

				case 6:
					bookOpe.changePassword();
					break;

				case 7:
					bookOpe.closeAplication();
					break;

				default:
					System.out.println("invalied Choise");
					break;

				}

			} else {
				System.out.println("incurrect password");

			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}
