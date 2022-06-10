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
import com.mamay.inspection.entity.Subscription;
import com.mamay.inspection.exception.DAOException;

public class SubscriptionDAO extends AbstractDAO<Integer, Subscription> {

	public SubscriptionDAO(ProxyConnection connection) {
		super(connection);
	}

	@Override
	public List<Subscription> findAll() throws DAOException {
		try (Statement statement = connection.createStatement()) {
			List<Subscription> subList = new ArrayList<Subscription>();
			ResultSet rs = statement.executeQuery(QuerySQL.FIND_ALL_SUBS);
			while (rs.next()) {
				Subscription sub = ResultSetCreator.createSubscription(rs);
				subList.add(sub);
			}
			return subList;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Subscription findEntityById(Integer id) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(QuerySQL.FIND_SUB_BY_ID);) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				Subscription sub = ResultSetCreator.createSubscription(rs);
				return sub;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		throw new DAOException("Can' find subscription");
	}

	public List<Subscription> findByMagazineId(int magId) throws DAOException {
		List<Subscription> subList = new ArrayList<Subscription>();
		try (PreparedStatement statement = connection.prepareStatement(QuerySQL.FIND_SUBS_BY_MAGAZINE);) {
			statement.setInt(1, magId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Subscription sub = ResultSetCreator.createSubscription(rs);
				subList.add(sub);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return subList;
	}

	@Override
	public void delete(Integer id) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(QuerySQL.DELETE_SUB_BY_ID);) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Integer create(Subscription entity) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(
				QuerySQL.CREATE_SUB, Statement.RETURN_GENERATED_KEYS)) {
			Magazine mag = entity.getMagazine();
			statement.setInt(1, mag.getId());
			statement.setString(2, entity.getIndex());
			statement.setString(3, entity.getDuration());
			statement.setString(4, entity.getPrice());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			int lastIndex = rs.getInt(1);
			return lastIndex;
		} catch (SQLException e) {
			throw new DAOException(e);
		}

	}

	@Override
	public void update(Subscription entity) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(QuerySQL.UPDATE_SUB)) {
			statement.setString(1, entity.getIndex());
			statement.setString(2, entity.getDuration());
			statement.setString(3, entity.getPrice());
			statement.setInt(4, entity.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
