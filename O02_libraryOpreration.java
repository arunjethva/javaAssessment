package assessment_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class O02_libraryOpreration extends Thread {
	Scanner sc = new Scanner(System.in);
	Connection cn = null;

	public O02_libraryOpreration() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assessment", "root", "");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addBook() {
		String catagory = null;
		do {
			System.out.println("******* SELECT CATAGORY********");
			System.out.println("1.Computer");
			System.out.println("2.Electonics");
			System.out.println("3.Electrical");
			System.out.println("4.Civil");
			System.out.println("5.Machanical");
			System.out.println("6.Architecture");
			System.out.println("7.Back to main menu");
			catagory = sc.next();

			if (catagory.equals("7")) {
				System.out.println("Back to main menu");
			} else {

				System.out.println("Enter the id ");
				int id = sc.nextInt();

				System.out.println("Enter the bookName");
				String bname = sc.next();

				System.out.println("Enter the AuthoName");
				String aname = sc.next();

				System.out.println("Enter the Qty");
				int qty = sc.nextInt();

				System.out.println("Enter the Price");
				double price = sc.nextDouble();

				try {
					PreparedStatement ps = cn.prepareStatement("insert into books values (?,?,?,?,?,?)");
					ps.setInt(1, id);
					ps.setString(2, bname);
					ps.setString(3, aname);
					ps.setInt(4, qty);
					ps.setDouble(5, price);
					ps.setString(6, catagory);
					

					int i = ps.executeUpdate();
					if (i > 0) {
						System.out.println("Book inserted");
					} else {
						System.out.println("Try again to insert book");
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} while (!catagory.equals("7"));
	}

	public void daleteBook() {
		System.out.println("enter the book id to delete ");

		int id = sc.nextInt();

		try {
			PreparedStatement ps = cn.prepareStatement("delete from books where bid = ? ");
			ps.setInt(1, id);

			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("Book Deleted");
			} else {
				System.out.println("Book not found");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void searchBook() {

		System.out.println("1.Serach by id ");
		System.out.println("2.Serach by name ");

		System.out.println("Enter your choise ");
		int choise = sc.nextInt();

		if (choise == 1) {
			System.out.println("Search by id ");
			System.out.println("Enter the book id");
			int id = sc.nextInt();
			

			try {
				PreparedStatement ps = cn.prepareStatement("select * from books where bid =?");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					System.out.println("Book id " + rs.getInt(1));
					System.out.println("Book Name " + rs.getString(2));
					System.out.println("Autor " + rs.getString(3));
					System.out.println("Quantity" + rs.getInt(4));
					System.out.println("Price" + rs.getDouble(5));
					System.out.println("Catagory" + rs.getString(6));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (choise == 2) {
			System.out.println("Search by name");
			System.out.println("Enter the book name");
			String name = sc.next();

			try {
				PreparedStatement ps = cn.prepareStatement("select * from books where bname = ?");
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {

					System.out.println("id " + rs.getInt(1));
					System.out.println("Name " + rs.getString(2));
					System.out.println("Autor " + rs.getString(3));
					System.out.println("Quantity" + rs.getInt(4));
					System.out.println("Price" + rs.getDouble(5));
					System.out.println("Catagory" + rs.getString(6));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("invalied choise");

		}

	}

	public void ViewBook() {

		System.out.println("************ VIEW  ALL BOOKS *************");
		try {

			PreparedStatement ps1 = cn.prepareStatement("select * from books");
			ResultSet rs = ps1.executeQuery();

			while (rs.next()) {
				System.out.println("id - " + rs.getInt(1) + "  " + "Name - " + rs.getString(2) + "  " + "Autor -  "
						+ rs.getString(3) + "  " + "Quantity - " + rs.getInt(4) + "  " + "Price - " + rs.getDouble(5)
						+ " " + rs.getString(6));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void editBook() {

	}

	public void changePassword() {
		O04_libraryPassword lb = new O04_libraryPassword();
		boolean b = lb.equalPass();
		if (b) {
			try {
				System.out.println("Enter new password");
				String newpass = sc.next();
				PreparedStatement ps = cn.prepareStatement("update  pwd set password = ?");
				ps.setString(1, newpass);
				int i = ps.executeUpdate();
				if (i > 0) {
					System.out.println("Password sucsessfully Changed");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void closeAplication() {
		System.out.println("Exiting in 3 second......");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		System.out.println("Exit Successfully...!!!");
	}

}
