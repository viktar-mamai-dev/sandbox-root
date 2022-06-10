package com.mamay.inspection.dbhelper;

import com.mamay.inspection.exception.DAOException;
import com.mamay.inspection.manager.DatabaseManager;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Log4j2
public class ConnectionPool {
    private static final AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static final Lock lock = new ReentrantLock();
    private static ConnectionPool instance = null;
    private final AtomicBoolean flag = new AtomicBoolean(true);
    private final ArrayBlockingQueue<ProxyConnection> connectionQueue;

    private ConnectionPool() {
        int size = Integer.parseInt(DatabaseManager.getProperty("db.poolsize"));
        connectionQueue = new ArrayBlockingQueue<ProxyConnection>(size);
        for (int i = 0; i < size; i++) {
            connectionQueue.offer(new ProxyConnection(initConnection()));
        }
    }

    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            try {
                lock.lock();
                if (!instanceCreated.get()) {
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private Connection initConnection() {
        String url = DatabaseManager.getProperty("db.url");
        String user = DatabaseManager.getProperty("db.user");
        String password = DatabaseManager.getProperty("db.password");
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProxyConnection getConnection() throws DAOException {
        if (flag.get()) {
            try {
                return connectionQueue.poll(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                log.error(e);
            }
        }
        throw new DAOException("Can't get connection");
    }

    public void closeConnection(ProxyConnection connection) {
        if (connection != null) {
            connectionQueue.offer(connection);
        }
    }

    public void closePool() {
        flag.set(false);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException exc) {
            log.error(exc);
        }
        for (ProxyConnection con : connectionQueue) {
            close(con);
        }
    }

    private void close(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            log.error(e);
        }

    }
}
