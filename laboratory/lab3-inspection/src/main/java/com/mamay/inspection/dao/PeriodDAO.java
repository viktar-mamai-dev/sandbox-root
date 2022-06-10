package com.mamay.inspection.dao;

import com.mamay.inspection.constant.QuerySQL;
import com.mamay.inspection.dbhelper.ProxyConnection;
import com.mamay.inspection.dbhelper.ResultSetCreator;
import com.mamay.inspection.entity.Period;
import com.mamay.inspection.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PeriodDAO extends AbstractDAO<Integer, Period> {

    public PeriodDAO(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public List<Period> findAll() throws DAOException {
        List<Period> periodList = new ArrayList<Period>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(QuerySQL.FIND_ALL_PERIODS);
            while (rs.next()) {
                Period period = ResultSetCreator.createPeriod(rs);
                periodList.add(period);
            }
            return periodList;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Period findEntityById(Integer id) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(QuerySQL.FIND_PERIOD_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return ResultSetCreator.createPeriod(rs);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
        throw new DAOException("Can't calculate period");
    }

    @Override
    public void delete(Integer id) throws DAOException {
        throw new DAOException("Can't delete period");
    }

    @Override
    public Integer create(Period entity) throws DAOException {
        throw new DAOException("Can't create new period");
    }

    @Override
    public void update(Period entity) throws DAOException {
        throw new DAOException("Can't update period");
    }
}
