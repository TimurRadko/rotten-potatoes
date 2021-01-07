package com.epam.web.rotten.potatoes.connection;

import com.epam.web.rotten.potatoes.exceptions.ConnectionPoolException;
import com.epam.web.rotten.potatoes.exceptions.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private final static int POOL_SIZE = 5;
    private final static Semaphore SEMAPHORE = new Semaphore(POOL_SIZE, true);
    private static final AtomicBoolean IS_CREATED = new AtomicBoolean(false);
    private static ConnectionPool instance = null;
    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> usingConnections;
    private static final ReentrantLock CONNECTIONS_LOCKER = new ReentrantLock();
    private static final ReentrantLock INSTANCE_LOCKER = new ReentrantLock();
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    private ConnectionPool() {
        availableConnections = new ArrayDeque<>();
        usingConnections = new ArrayDeque<>();
    }

    private void init() {
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection = connectionFactory.create();
            ProxyConnection proxyConnection = new ProxyConnection(connection, this);
            availableConnections.add(proxyConnection);
        }
    }

    public static ConnectionPool getInstance() {
        if (!IS_CREATED.get()) {
            INSTANCE_LOCKER.lock();
            ConnectionPool local;
            try {
                if (!IS_CREATED.get()) {
                    local = new ConnectionPool();
                    local.init();
                    instance = local;
                    IS_CREATED.set(true);
                }
            } finally {
                INSTANCE_LOCKER.unlock();
            }
        }
        return instance;
    }

    public void releaseConnection(ProxyConnection proxyConnection) {
        CONNECTIONS_LOCKER.lock();
        try {
            if (usingConnections.contains(proxyConnection)) {
                availableConnections.offer(proxyConnection);
                usingConnections.poll();
                SEMAPHORE.release();
            }
        } finally {
            CONNECTIONS_LOCKER.unlock();
        }
    }

    public ProxyConnection getConnection() {
        try {
            SEMAPHORE.acquire();
            CONNECTIONS_LOCKER.lock();
            ProxyConnection proxyConnection = availableConnections.poll();
            usingConnections.offer(proxyConnection);
            return proxyConnection;
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e.getMessage(), e);
        } finally {
            CONNECTIONS_LOCKER.unlock();
        }
    }

    public void closeConnections() throws DaoException {
        availableConnections.addAll(usingConnections);
        usingConnections.clear();
        try {
            for (ProxyConnection connection : availableConnections) {
                connection.finalCloseConnection();
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}