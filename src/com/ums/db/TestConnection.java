package com.ums.db;

public class TestConnection {
	
	public static void main(String[] args) {
		try {
			JDBCConnection.getInstance().open();
			System.out.println("Connexion à la base OK.");
			JDBCConnection.getInstance().close();
		} catch (UMSDBException e) {
			System.err.println(e.getMessage());
		}
	}
}
