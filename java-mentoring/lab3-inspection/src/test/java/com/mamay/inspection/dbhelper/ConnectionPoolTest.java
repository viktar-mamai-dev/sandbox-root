package com.mamay.inspection.dbhelper;

import com.mamay.inspection.exception.DAOException;
import com.mamay.inspection.manager.DatabaseManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedList;
import java.util.stream.IntStream;

import static org.junit.Assert.assertNull;

public class ConnectionPoolTest {
  private static ConnectionPool pool;
  private static LinkedList<ProxyConnection> connectionList;
  private static int poolSize;

  @BeforeClass
  public static void before() throws DAOException {
    pool = ConnectionPool.getInstance();
    poolSize = Integer.parseInt(DatabaseManager.getProperty("db.poolsize"));
    connectionList = new LinkedList<>();
    for (int k = 0; k < poolSize; k++) {
      ProxyConnection connection = pool.getConnection();
      connectionList.push(connection);
    }
  }

  @AfterClass
  public static void after() {
    IntStream.range(0, poolSize).forEach(i -> pool.closeConnection(connectionList.poll()));
  }

  @Test(timeout = 10000)
  @Ignore // TODO
  public void openConnectionTest() throws DAOException {
    ProxyConnection conn = pool.getConnection();
    assertNull("Connection doesn't equal to null", conn);
    pool.closeConnection(conn);
  }
}
