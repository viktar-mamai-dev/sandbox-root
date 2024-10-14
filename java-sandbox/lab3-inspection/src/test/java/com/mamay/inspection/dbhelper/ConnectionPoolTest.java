package com.mamay.inspection.dbhelper;

import com.mamay.inspection.exception.DAOException;
import com.mamay.inspection.manager.DatabaseManager;
import java.util.LinkedList;
import java.util.stream.IntStream;
import org.junit.jupiter.api.*;

public class ConnectionPoolTest {
  private static ConnectionPool pool;
  private static LinkedList<ProxyConnection> connectionList;
  private static int poolSize;

  @BeforeAll
  public static void before() throws DAOException {
    pool = ConnectionPool.getInstance();
    poolSize = Integer.parseInt(DatabaseManager.getProperty("db.poolsize"));
    connectionList = new LinkedList<>();
    for (int k = 0; k < poolSize; k++) {
      ProxyConnection connection = pool.getConnection();
      connectionList.push(connection);
    }
  }

  @AfterAll
  public static void after() {
    IntStream.range(0, poolSize).forEach(i -> pool.closeConnection(connectionList.poll()));
  }

  @Test
  @Disabled // TODO
  public void openConnectionTest() throws DAOException {
    ProxyConnection conn = pool.getConnection();
    Assertions.assertNull(conn, "Connection doesn't equal to null");
    pool.closeConnection(conn);
  }
}
