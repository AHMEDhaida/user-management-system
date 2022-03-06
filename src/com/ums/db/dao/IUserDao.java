package com.ums.db.dao;
import java.util.List;


import com.ums.db.UMSDBException;
import com.ums.model.User;

public interface IUserDao {
	/**
	 * @param user
	 * @throws UMSDBException
	 */
	public void create(User user) throws UMSDBException;

	/**
	 * @param id
	 * @return
	 * @throws UMSDBException
	 */
	public User read(int id) throws UMSDBException;

	/**
	 * @param user
	 * @throws UMSDBException
	 */
	public void update(User user) throws UMSDBException;

	/**
	 * @param integerProperty
	 * @throws UMSDBException
	 */
	public void delete(int id) throws UMSDBException;

	/**
	 * @return
	 * @throws UMSDBException
	 */
	public List<User> list() throws UMSDBException;

	
}