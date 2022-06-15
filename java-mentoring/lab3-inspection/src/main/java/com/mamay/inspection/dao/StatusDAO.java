package com.mamay.inspection.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mamay.inspection.constant.QuerySQL;
import com.mamay.inspection.dbhelper.ProxyConnection;
import com.mamay.inspection.dbhelper.ResultSetCreator;
import com.mamay.inspection.entity.Status;
import com.mamay.inspection.exception.DAOException;

public class StatusDAO extends AbstractDAO<Integer, Status> {

	public StatusDAO(ProxyConnection connection) {
		super(connection);
	}

	@Override
	public List<Status> findAll() throws DAOException {
		throw new DAOException("Can't find all statuses");
	}

	@Override
	public Status findEntityById(Integer id) throws DAOException {
		try (PreparedStatement statement = connection.prepareStatement(QuerySQL.FIND_STATUS_BY_ID);) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return ResultSetCreator.createStatus(rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		throw new DAOException("Can't find status");
	}

	@Override
	public void delete(Integer id) throws DAOException {
		throw new DAOException("Can't delete status");
	}

	@Override
	public Integer create(Status entity) throws DAOException {
		throw new DAOException("Can't create new status");
	}

	@Override
	public void update(Status entity) throws DAOException {
		throw new DAOException("Can't update status");
	}

}
