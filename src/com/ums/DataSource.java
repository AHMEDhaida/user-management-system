package com.ums;

import java.util.List;


import com.ums.db.UMSDBException;
import com.ums.db.dao.IUserDao;
import com.ums.db.dao.UserDaoImpl;
import com.ums.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSource {

	private ObservableList<User> users = FXCollections.observableArrayList();

	private IUserDao dao;
	public DataSource() throws UMSDBException {
		dao = new UserDaoImpl();
		listUsers();
		//List<User> users = dao.list();
		//this.users.addAll(users);
	}
     
	public ObservableList<User> getUsers() {
		return users;
	}
	

	public void AddUser(User user) throws UMSDBException {
		
		dao.create(user);
		this.users.add(user);
		
		//List<User> users = dao.list();
		//this.users.addAll(users);
		
	}
	
	
	public void DeleteUser(int id) throws UMSDBException {
		User user = this.users.get(id);
		this.users.remove(user);
		dao.delete(user.getId());
		//this.users.remove(users.get(0));
		
	}
	
	public void UpdateUser(User user,int selecteUserIndex ) throws UMSDBException {
		users.remove(selecteUserIndex);
		users.add(selecteUserIndex, user);
		dao.update(user);
		
	}
	
	public User ReadUser (int id) throws UMSDBException {
		
	    return dao.read(id);
		
	} 
	
	public void listUsers() throws UMSDBException {
		List<User> users = dao.list();
		this.users.addAll(users);
	}
}
