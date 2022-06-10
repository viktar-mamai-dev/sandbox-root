package com.mamay.inspection.service;

import com.mamay.inspection.dao.ReservationDAO;
import com.mamay.inspection.dao.StatusDAO;
import com.mamay.inspection.dao.SubscriptionDAO;
import com.mamay.inspection.dao.UserDAO;
import com.mamay.inspection.dbhelper.ConnectionPool;
import com.mamay.inspection.dbhelper.ProxyConnection;
import com.mamay.inspection.entity.Reservation;
import com.mamay.inspection.entity.Status;
import com.mamay.inspection.entity.Subscription;
import com.mamay.inspection.entity.User;
import com.mamay.inspection.exception.DAOException;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class ReservationService {
    private static final ConnectionPool POOL = ConnectionPool.getInstance();

    public static List<Reservation> findAllreservations() {
        List<Reservation> resList = null;
        ProxyConnection con = null;
        try {
            con = POOL.getConnection();
            ReservationDAO resDao = new ReservationDAO(con);
            resList = resDao.findAll();
        } catch (DAOException e) {
            log.error(e);
        } finally {
            POOL.closeConnection(con);
        }
        return resList;
    }

    public static void createReservation(int userId, int subId, int count) {
        ProxyConnection con = null;
        try {
            con = POOL.getConnection();
            UserDAO userDao = new UserDAO(con);
            User user = userDao.findEntityById(userId);
            SubscriptionDAO subDao = new SubscriptionDAO(con);
            Subscription sub = subDao.findEntityById(subId);
            ReservationDAO resDao = new ReservationDAO(con);
            Reservation res = new Reservation(count);
            res.setUser(user);
            res.setSubscription(sub);
            int id = resDao.create(res);
            res.setId(id);
        } catch (DAOException e) {
            log.error(e);
        } finally {
            POOL.closeConnection(con);
        }
    }

    public static void changeStatus(int resId, int statusId) {
        ProxyConnection con = null;
        try {
            con = POOL.getConnection();
            StatusDAO statusDAO = new StatusDAO(con);
            Status status = statusDAO.findEntityById(statusId);
            ReservationDAO resDao = new ReservationDAO(con);
            Reservation reserv = resDao.findEntityById(resId);
            reserv.setStatus(status);
            resDao.update(reserv);
        } catch (DAOException e) {
            log.error(e);
        } finally {
            POOL.closeConnection(con);
        }
    }

    public static void deleteReservation(int resId) {
        ProxyConnection con = null;
        try {
            con = POOL.getConnection();
            ReservationDAO resDao = new ReservationDAO(con);
            resDao.delete(resId);
        } catch (DAOException e) {
            log.error(e);
        } finally {
            POOL.closeConnection(con);
        }
    }

    public static List<Reservation> findByUserId(int id) {
        List<Reservation> resList = null;
        ProxyConnection con = null;
        try {
            con = POOL.getConnection();
            ReservationDAO resDao = new ReservationDAO(con);
            resList = resDao.findByUserId(id);
        } catch (DAOException e) {
            log.error(e);
        } finally {
            POOL.closeConnection(con);
        }
        return resList;
    }

}
