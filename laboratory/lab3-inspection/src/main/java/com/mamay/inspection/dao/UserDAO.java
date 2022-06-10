package com.mamay.inspection.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mamay.inspection.constant.QuerySQL;
import com.mamay.inspection.dbhelper.ProxyConnection;
import com.mamay.inspection.dbhelper.ResultSetCreator;
import com.mamay.inspection.entity.User;
import com.mamay.inspection.exception.DAOException;

public class UserDAO extends AbstractDAO<Integer, User> {

	public UserDAO(ProxyConnection connection) {
		super(connection);
	}

	@Override
	public List<User> findAll() throws DAOException {
		List<User> userList = new ArrayList<User>();
		try (Statement statement = connection.createStatement()) {
			ResultSet rs = statement.executeQuery(QuerySQL.FIND_ALL_USERS);
			while (rs.next()) {
				User user = ResultSetCreator.createUser(rs);
				userList.add(user);
			}
			return userList;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public User findEntityById(Integer id) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(QuerySQL.FIND_USER_BY_ID)) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				User user = ResultSetCreator.createUser(rs);
				return user;
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		}
		throw new DAOException("Can't find user");
	}

	public User findUserByLogin(String login) throws DAOException {
		User user = null;
		try (PreparedStatement statement = connection
				.prepareStatement(QuerySQL.FIND_USER_BY_LOGIN)) {
			statement.setString(1, login);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user = ResultSetCreator.createUser(rs);
			}
			return user;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public User findUserByLoginPassword(String login, String password)
			throws DAOException {
		User user = null;
		try (PreparedStatement statement = connection.prepareStatement(QuerySQL.FIND_USER_BY_LOGIN_PASS)) {
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user = ResultSetCreator.createUser(rs);
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void delete(Integer id) throws DAOException {
		try (PreparedStatement statement = connection
				.prepareStatement(QuerySQL.DELETE_USER_BY_ID)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Integer create(User entity) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(
				QuerySQL.CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, entity.getUserName());
			statement.setInt(2, entity.getAge());
			statement.setString(3, entity.getLogin());
			statement.setString(4, entity.getPassword());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void update(User entity) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(QuerySQL.UPDATE_USER)) {
			statement.setString(1, entity.getUserName());
			statement.setInt(2, entity.getAge());
			statement.setString(2, entity.getLogin());
			statement.setInt(4, entity.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
