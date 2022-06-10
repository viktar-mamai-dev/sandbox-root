package com.mamay.inspection.service;

import lombok.extern.log4j.Log4j2;
import org.apache.log4j.Logger;

import com.mamay.inspection.dao.UserDAO;
import com.mamay.inspection.dbhelper.ConnectionPool;
import com.mamay.inspection.dbhelper.ProxyConnection;
import com.mamay.inspection.entity.User;
import com.mamay.inspection.exception.DAOException;
import com.mamay.inspection.exception.LogicException;

@Log4j2
public class UserService {
	private static final ConnectionPool POOL = ConnectionPool.getInstance();

	public static User checkLoginPassword(String enterLogin, String enterPass) {
		ProxyConnection con = null;
		User user = null;
		try {
			con = POOL.getConnection();
			UserDAO userDao = new UserDAO(con);
			user = userDao.findUserByLoginPassword(enterLogin, enterPass);
		} catch (DAOException e) {
			log.error(e);
		} finally {
			POOL.closeConnection(con);
		}
		return user;
	}

	public static User checkLogin(String enterLogin) {
		ProxyConnection con = null;
		User user = null;
		try {
			con = POOL.getConnection();
			UserDAO userDao = new UserDAO(con);
			user = userDao.findUserByLogin(enterLogin);
		} catch (DAOException e) {
			log.error(e);
		} finally {
			POOL.closeConnection(con);
		}
		return user;
	}

	public static User createUser(User user) {
		ProxyConnection con = null;
		try {
			con = POOL.getConnection();
			UserDAO userdDao = new UserDAO(con);
			int userId = userdDao.create(user);
			user.setId(userId);
		} catch (DAOException e) {
			log.error(e);
		} finally {
			POOL.closeConnection(con);
		}
		return user;
	}

	public static void deleteUser(int id) throws LogicException {
		ProxyConnection con = null;
		try {
			con = POOL.getConnection();
			UserDAO userdDao = new UserDAO(con);
			userdDao.delete(id);
		} catch (DAOException e) {
			log.error(e);
		} finally {
			POOL.closeConnection(con);
		}
	}
}
