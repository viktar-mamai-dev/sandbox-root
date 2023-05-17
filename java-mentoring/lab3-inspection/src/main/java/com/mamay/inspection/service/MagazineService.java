package com.mamay.inspection.service;

import com.mamay.inspection.dao.MagazineDAO;
import com.mamay.inspection.dao.PeriodDAO;
import com.mamay.inspection.dao.SubscriptionDAO;
import com.mamay.inspection.dbhelper.ConnectionPool;
import com.mamay.inspection.dbhelper.ProxyConnection;
import com.mamay.inspection.entity.Magazine;
import com.mamay.inspection.entity.Period;
import com.mamay.inspection.entity.Subscription;
import com.mamay.inspection.exception.DAOException;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MagazineService {

  private static final ConnectionPool POOL = ConnectionPool.getInstance();

  public static List<Magazine> findAllMagazines(int offset) {
    ProxyConnection con = null;
    List<Magazine> magList = null;
    try {
      con = POOL.getConnection();
      MagazineDAO magazineDao = new MagazineDAO(con);
      magList = magazineDao.findAll(offset);
    } catch (DAOException e) {
      log.error(e);
    } finally {
      POOL.closeConnection(con);
    }
    return magList;
  }

  public static List<Magazine> findAllMagazines() {
    ProxyConnection con = null;
    List<Magazine> magList = null;
    try {
      con = POOL.getConnection();
      MagazineDAO magazineDao = new MagazineDAO(con);
      magList = magazineDao.findAll();
    } catch (DAOException e) {
      log.error(e);
    } finally {
      POOL.closeConnection(con);
    }
    return magList;
  }

  public static Magazine findMagazineById(int id) {
    ProxyConnection con = null;
    Magazine mag = null;
    try {
      con = POOL.getConnection();
      MagazineDAO magazineDao = new MagazineDAO(con);
      mag = magazineDao.findEntityById(id);
      SubscriptionDAO subDao = new SubscriptionDAO(con);
      List<Subscription> subList = subDao.findByMagazineId(id);
      mag.addSubscriptions(subList);
    } catch (DAOException e) {
      log.error(e);
    } finally {
      POOL.closeConnection(con);
    }
    return mag;
  }

  public static int calculateCount() {
    ProxyConnection con = null;
    int count = 0;
    try {
      con = POOL.getConnection();
      MagazineDAO magazineDao = new MagazineDAO(con);
      count = magazineDao.calculateCount();
    } catch (DAOException e) {
      log.error(e);
    } finally {
      POOL.closeConnection(con);
    }
    return count;
  }

  public static void createMagazine(int periodId, Magazine mag) {
    ProxyConnection con = null;
    try {
      con = POOL.getConnection();
      PeriodDAO periodDao = new PeriodDAO(con);
      Period period = periodDao.findEntityById(periodId);
      mag.setPeriod(period);
      MagazineDAO magazineDao = new MagazineDAO(con);
      magazineDao.create(mag);
    } catch (DAOException e) {
      log.error(e);
    } finally {
      POOL.closeConnection(con);
    }
  }

  public static void updateMagazine(int periodId, Magazine mag) {
    ProxyConnection con = null;
    try {
      con = POOL.getConnection();
      PeriodDAO periodDao = new PeriodDAO(con);
      Period period = periodDao.findEntityById(periodId);
      mag.setPeriod(period);
      MagazineDAO magazineDao = new MagazineDAO(con);
      magazineDao.update(mag);
    } catch (DAOException e) {
      log.error(e);
    } finally {
      POOL.closeConnection(con);
    }
  }

  public static void deleteMagazine(int id) {
    ProxyConnection con = null;
    try {
      con = POOL.getConnection();
      MagazineDAO magazineDao = new MagazineDAO(con);
      magazineDao.delete(id);
    } catch (DAOException e) {
      log.error(e);
    } finally {
      POOL.closeConnection(con);
    }
  }

  public static List<Period> findAllPeriods() {
    ProxyConnection con = null;
    List<Period> periodList = null;
    try {
      con = POOL.getConnection();
      PeriodDAO periodDao = new PeriodDAO(con);
      periodList = periodDao.findAll();
    } catch (DAOException e) {
      log.error(e);
    } finally {
      POOL.closeConnection(con);
    }
    return periodList;
  }
}
