package assessment_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class O03_libraryCatagory {
	Scanner sc = new Scanner(System.in);
	Connection cn = null;
	int choice = 0;

	O03_libraryCatagory() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assessment", "root", "");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void catagory() {
		System.out.println("Enter book name ");
		String name = sc.next();

		System.out.println("Enter author Name");
		String author = sc.next();

		System.out.println("Enter Quantity");
		int qty = sc.nextInt();

		System.out.println("Enter price");
		double price = sc.nextDouble();

		try {
			PreparedStatement ps = cn.prepareStatement("select * from books where bname = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("Book Exiseted");
			} else {
				PreparedStatement ps1 = cn.prepareStatement("insert into books values (?,?,?,?,?)");
				ps1.setInt(1, 0);
				ps1.setString(2, name);
				ps1.setString(3, author);
				ps1.setInt(4, qty);
				ps1.setDouble(5, price);

				int i = ps1.executeUpdate();
				if (i > 0) {
					System.out.println("Book inserted");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void backToMain() {
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

	}

}
