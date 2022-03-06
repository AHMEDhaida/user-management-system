package com.ums.db.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ums.db.JDBCConnection;
import com.ums.db.UMSDBException;

import com.ums.model.User;

public class UserDaoImpl implements IUserDao {
	
    @Override
    public void create(User user) throws UMSDBException {
	
        String query = "Insert Into T_Users (nom,prenom,email,telephone,login,password,role)values (?,?,?,?,?,?,?)";
        try{
        	Connection connection = JDBCConnection.getInstance().open();
        	
        	PreparedStatement preparedStatement = connection.prepareStatement(query);
        	preparedStatement.setString(1,user.getNom().get());
        	preparedStatement.setString(2,user.getPrenom().get());
        	preparedStatement.setString(3,user.getEmail().get());
        	preparedStatement.setString(4,user.getTelephone().get());
        	preparedStatement.setString(5,user.getLogin().get());
        	preparedStatement.setString(6,user.getPassword().get());
        	preparedStatement.setString(7,user.getRole().get());
        	preparedStatement.execute();
        	query = "select Max(id) From T_Users";
        	ResultSet resultSet = connection.prepareStatement(query).executeQuery();
        	if(resultSet.next()) {
        		user.setId(resultSet.getInt("Max(id)"));
        	}
        	
        	JDBCConnection.getInstance().close();

    } catch (SQLException e) {
    throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
    }
}

	@Override
	public List<User> list() throws UMSDBException {
		List<User> users = new ArrayList<>();
		try  {
			Connection connection = JDBCConnection.getInstance().open();
					
			String query = "Select * From T_Users ";
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String email = resultSet.getString("email");
				String telephone = resultSet.getString("telephone");
				String login = resultSet.getString("login");
				String password = resultSet.getString("password");
				String role = resultSet.getString("role");
				User user = new User(id, nom, prenom, email, telephone, login, password, role);
				users.add(user);				
			}
			
			JDBCConnection.getInstance().close();
			return users;
		} catch (SQLException e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
		}
	}

	
	@Override
	public User read(int Id) throws UMSDBException {
		User user = null;
		String query = "Select * From T_Users where id = ?";
		try  {
			Connection connection = JDBCConnection.getInstance().open();
					
			
            PreparedStatement preparedStatement;
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String email = resultSet.getString("email");
				String telephone = resultSet.getString("telephone");
				String login = resultSet.getString("login");
				String password = resultSet.getString("password");
				String role = resultSet.getString("role");
			    user = new User(id, nom, prenom, email, telephone, login, password, role);
				//users.add(user);				
			}
			
			JDBCConnection.getInstance().close();
			return user;
		} catch (SQLException e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
		}
	}

	@Override
	public void update(User user) throws UMSDBException {
		// TODO Auto-generated method stub
		
	     
	      String query = "UPDATE t_users SET nom = ? ,prenom = ? ,email = ? ,telephone = ? , login = ?, password = ? , role = ? where id = ?";
	      try{
	        	Connection connection = JDBCConnection.getInstance().open();
	        	
	        	PreparedStatement preparedStatement = connection.prepareStatement(query);
	        	preparedStatement.setString(1,user.getNom().get());
	        	preparedStatement.setString(2,user.getPrenom().get());
	        	preparedStatement.setString(3,user.getEmail().get());
	        	preparedStatement.setString(4,user.getTelephone().get());
	        	preparedStatement.setString(5,user.getLogin().get());
	        	preparedStatement.setString(6,user.getPassword().get());
	        	preparedStatement.setString(7,user.getRole().get());
	        	preparedStatement.setInt(8,user.getId());
	        	
	        	preparedStatement.execute();
	        	
	        	JDBCConnection.getInstance().close();

	    } catch (SQLException e) {
	    throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
	    }
		
		
	}

	@Override
	public void delete(int  id) throws UMSDBException {
		// TODO Auto-generated method stub
		
		try { 
			Connection connection = JDBCConnection.getInstance().open();
			
			String query = "Delete From T_Users Where id = ?";
			PreparedStatement preparedStatement;
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			
			JDBCConnection.getInstance().close();
			
			} catch (SQLException e) {
			throw new UMSDBException("ERROR:" + e.getClass() + ":" + e.getMessage());
			}

		
	}

}