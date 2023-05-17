package com.mamay.inspection.service;

import com.mamay.inspection.dao.SubscriptionDAO;
import com.mamay.inspection.dbhelper.ConnectionPool;
import com.mamay.inspection.dbhelper.ProxyConnection;
import com.mamay.inspection.entity.Subscription;
import com.mamay.inspection.exception.DAOException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SubscriptionService {

  private static final ConnectionPool POOL = ConnectionPool.getInstance();

  public static Subscription findSubscriptionById(int subId) {
    Subscription sub = null;
    ProxyConnection con = null;
    try {
      con = POOL.getConnection();
      SubscriptionDAO subscriptionDao = new SubscriptionDAO(con);
      sub = subscriptionDao.findEntityById(subId);
    } catch (DAOException e) {
      log.error(e);
    } finally {
      POOL.closeConnection(con);
    }
    return sub;
  }

  public static void createSubscription(Subscription sub) {
    ProxyConnection con = null;
    try {
      con = POOL.getConnection();
      SubscriptionDAO subscriptionDao = new SubscriptionDAO(con);
      int id = subscriptionDao.create(sub);
      sub.setId(id);
    } catch (DAOException e) {
      log.error(e);
    } finally {
      POOL.closeConnection(con);
    }
  }

  public static void updateSubscription(Subscription entity) {
    ProxyConnection con = null;
    try {
      con = POOL.getConnection();
      SubscriptionDAO subscriptionDao = new SubscriptionDAO(con);
      subscriptionDao.update(entity);
    } catch (DAOException e) {
      log.error(e);
    } finally {
      POOL.closeConnection(con);
    }
  }

  public static void deleteSubscription(int subId) {
    ProxyConnection con = null;
    try {
      con = POOL.getConnection();
      SubscriptionDAO subscriptionDao = new SubscriptionDAO(con);
      subscriptionDao.delete(subId);
    } catch (DAOException e) {
      log.error(e);
    } finally {
      POOL.closeConnection(con);
    }
  }
}
