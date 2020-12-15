package com.epam.web.rotten.potatoes.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final AtomicBoolean IS_CREATED = new AtomicBoolean(false);
    private static ConnectionPool instance = null;
    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> usingConnections;
    private static final ReentrantLock CONNECTIONS_LOCKER = new ReentrantLock();
    private static final ReentrantLock INSTANCE_LOCKER = new ReentrantLock();
    private Semaphore semaphore;

    private ConnectionPool() {
        availableConnections = new ArrayDeque<>();
        usingConnections = new ArrayDeque<>();
        init();
    }

    private void init() {
        ProxyConnectionFactory proxyConnectionFactory = new ProxyConnectionFactory(this);
        int poolSize = proxyConnectionFactory.getPoolSize();
        semaphore = new Semaphore(poolSize, true);
        for (int i = 0; i < poolSize; i++) {
            ProxyConnection proxyConnection = proxyConnectionFactory.create();
            availableConnections.add(proxyConnection);
        }
    }

    public static ConnectionPool getInstance() {
        if (!IS_CREATED.get()) {
            INSTANCE_LOCKER.lock();
            try {
                if (!IS_CREATED.get()) {
                    instance = new ConnectionPool();
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
            }
        } finally {
            CONNECTIONS_LOCKER.unlock();
        }
        semaphore.release();
    }

    public ProxyConnection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            semaphore.acquire();
            CONNECTIONS_LOCKER.lock();
            proxyConnection = availableConnections.poll();
            usingConnections.offer(proxyConnection);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            CONNECTIONS_LOCKER.unlock();
        }
        return proxyConnection;
    }

    public void closeConnections() {
        usingConnections.forEach(this::releaseConnection);
        for (ProxyConnection proxyConnection : availableConnections) {
            try {
                proxyConnection.finalCloseConnection();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
