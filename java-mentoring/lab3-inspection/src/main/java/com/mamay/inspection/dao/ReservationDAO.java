package com.mamay.inspection.dao;

import com.mamay.inspection.constant.QuerySQL;
import com.mamay.inspection.dbhelper.ProxyConnection;
import com.mamay.inspection.dbhelper.ResultSetCreator;
import com.mamay.inspection.entity.Reservation;
import com.mamay.inspection.exception.DAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO extends AbstractDAO<Integer, Reservation> {

  public ReservationDAO(ProxyConnection connection) {
    super(connection);
  }

  @Override
  public List<Reservation> findAll() throws DAOException {
    List<Reservation> reservList = new ArrayList<Reservation>();
    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(QuerySQL.FIND_ALL_RESERV);
      while (rs.next()) {
        Reservation reserv = ResultSetCreator.createReservation(rs);
        reservList.add(reserv);
      }
      return reservList;
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public Reservation findEntityById(Integer id) throws DAOException {
    try (PreparedStatement statement = connection.prepareStatement(QuerySQL.FIND_RESERV_BY_ID); ) {
      statement.setInt(1, id);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        Reservation res = ResultSetCreator.createReservation(rs);
        return res;
      }
    } catch (SQLException e) {
      throw new DAOException(e);
    }
    throw new DAOException("Can't find reservation");
  }

  public List<Reservation> findByUserId(int userId) throws DAOException {
    List<Reservation> resList = new ArrayList<Reservation>();
    try (PreparedStatement statement =
        connection.prepareStatement(QuerySQL.FIND_RESERV_BY_USER); ) {
      statement.setInt(1, userId);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        Reservation sub = ResultSetCreator.createReservation(rs);
        resList.add(sub);
      }
    } catch (SQLException e) {
      throw new DAOException(e);
    }
    return resList;
  }

  @Override
  public void delete(Integer id) throws DAOException {
    try (PreparedStatement statement =
        connection.prepareStatement(QuerySQL.DELETE_RESERV_BY_ID); ) {
      statement.setInt(1, id);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }

  @Override
  public Integer create(Reservation entity) throws DAOException {
    try (PreparedStatement statement =
        connection.prepareStatement(QuerySQL.CREATE_RESERV, Statement.RETURN_GENERATED_KEYS)) {
      statement.setInt(1, entity.getUser().getId());
      statement.setInt(2, entity.getSubscription().getId());
      statement.setInt(3, entity.getCount());
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
  public void update(Reservation entity) throws DAOException {
    try (PreparedStatement statement = connection.prepareStatement(QuerySQL.UPDATE_RESERV)) {
      statement.setInt(1, entity.getStatus().getId());
      statement.setInt(2, entity.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new DAOException(e);
    }
  }
}
