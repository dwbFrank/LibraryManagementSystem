package com.frank.lms.account;

import java.sql.*;

public class Account {
	private String email;
	private String password;
	private boolean admin;
	private boolean valid;
	private String validationMessage;
	private final static String URL = "jdbc:mysql://localhost:3306/LibraryManagementSystem";
	private final static String USER = "root";
	private final static String PASSWORD = "402463";
	private final static String TABLE = "account";

	public boolean isAdmin() {
		return admin;
	}

	public boolean isValid() {
		return valid;
	}

	public String getValidationMessage() {
		return validationMessage;
	}

	public Account(String email, String password) {
		this.email = email;
		this.password = password;
		this.valid = passwordValid();
	}

	private boolean accountValidInitial() {
		return false;
	}

	private boolean passwordValid() {
		boolean registered;
		boolean valid = false;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		     Statement statement = connection.createStatement();
		) {
			String select = String.format("SELECT password, admin FROM %s WHERE email='%s'", TABLE, this.email);
			registered = statement.execute(select);
			if (registered) {
				ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {
					valid = this.password.equals(resultSet.getString("password"));
					this.admin = resultSet.getBoolean("admin");
				}
				if (!valid) {
					this.validationMessage = "パスワードが違います";
				}
			} else {
				this.validationMessage = "アカウントは登録していません";
			}
			this.validationMessage = "有効";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return valid;
	}

	public boolean addMember(String memberEmail, String memberPassword) {
		int confirm = 0;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		     Statement statement = connection.createStatement();
		) {
			String insert = String.format("INSERT INTO %s (email, password, admin) VALUES (%s, %s, %b)",
					TABLE, memberEmail, memberPassword, false);
			confirm = statement.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return confirm == 1;
	}
}
