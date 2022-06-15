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
import com.mamay.inspection.entity.Magazine;
import com.mamay.inspection.exception.DAOException;

public class MagazineDAO extends AbstractDAO<Integer, Magazine> {

	public MagazineDAO(ProxyConnection connection) {
		super(connection);
	}

	@Override
	public List<Magazine> findAll() throws DAOException {
		List<Magazine> magList = new ArrayList<Magazine>();
		try (Statement statement = connection.createStatement()) {
			ResultSet rs = statement.executeQuery(QuerySQL.FIND_ALL_MAGAZINES + QuerySQL.MAGAZINE_ORDER);
			while (rs.next()) {
				Magazine magazine = ResultSetCreator.createMagazine(rs);
				magList.add(magazine);
			}
			return magList;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public List<Magazine> findAll(int offset) throws DAOException {
		List<Magazine> magList = new ArrayList<Magazine>();
		try (PreparedStatement statement = connection.prepareStatement(QuerySQL.FIND_ALL_MAGAZINES
						+ QuerySQL.MAGAZINE_ORDER + QuerySQL.MAGAZINE_LIMIT)) {
			statement.setInt(1, offset);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Magazine magazine = ResultSetCreator.createMagazine(rs);
				magList.add(magazine);
			}
			return magList;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public int calculateCount() throws DAOException {
		try (Statement statement = connection.createStatement()) {
			ResultSet rs = statement.executeQuery(QuerySQL.MAGAZINE_COUNT);
			rs.next();
			return rs.getInt("count");
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Magazine findEntityById(Integer id) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(QuerySQL.FIND_MAGAZINE_BY_ID);) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return ResultSetCreator.createMagazine(rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		throw new DAOException("Can' find magazine");
	}

	@Override
	public void delete(Integer id) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(QuerySQL.DELETE_MAGAZINE_BY_ID)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Integer create(Magazine entity) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(QuerySQL.CREATE_MAGAZINE,
				Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, entity.getTitle());
			statement.setString(2, entity.getAnnotation());
			statement.setInt(3, entity.getPeriod().getId());
			statement.setString(4, entity.getLocation());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void update(Magazine entity) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(QuerySQL.UPDATE_MAGAZINE)) {
			statement.setString(1, entity.getTitle());
			statement.setString(2, entity.getAnnotation());
			statement.setInt(3, entity.getPeriod().getId());
			statement.setInt(4, entity.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
