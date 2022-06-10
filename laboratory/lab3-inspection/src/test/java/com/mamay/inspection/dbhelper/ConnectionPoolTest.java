package com.mamay.inspection.dbhelper;

import com.mamay.inspection.exception.DAOException;
import com.mamay.inspection.manager.DatabaseManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ConnectionPoolTest {
    private static ConnectionPool pool;
    private static LinkedList<ProxyConnection> conList;
    private static int poolSize;

    @BeforeClass
    public static void before() throws DAOException {
        pool = ConnectionPool.getInstance();
        poolSize = Integer.parseInt(DatabaseManager.getProperty("db.poolsize"));
        conList = new LinkedList<ProxyConnection>();
        for (int k = 0; k < poolSize; k++) {
            ProxyConnection connection = pool.getConnection();
            conList.push(connection);
        }
    }

    @AfterClass
    public static void after() {
        for (int k = 0; k < poolSize; k++) {
            ProxyConnection connection = conList.poll();
            pool.closeConnection(connection);
        }
    }

    @Test(timeout = 10000)
    public void openConnectionTest() throws DAOException {
        for (int k = 0; k < poolSize; k++) {
            assertNotNull("Connection equals to null");
        }
        ProxyConnection conn = pool.getConnection();
        assertNull("Connection doesn't equal to null", conn);
        pool.closeConnection(conn);

    }
}
