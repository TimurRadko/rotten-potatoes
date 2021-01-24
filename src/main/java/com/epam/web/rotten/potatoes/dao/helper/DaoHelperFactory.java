package com.epam.web.rotten.potatoes.dao.helper;

import com.epam.web.rotten.potatoes.connection.ConnectionPool;

public class DaoHelperFactory {

    public DaoHelper create() {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}