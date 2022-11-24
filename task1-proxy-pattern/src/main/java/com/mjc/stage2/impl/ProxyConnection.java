package com.mjc.stage2.impl;


import com.mjc.stage2.Connection;

public class ProxyConnection implements Connection {
    private RealConnection realConnection;
    private ConnectionPool connectionPool;

    public ProxyConnection(RealConnection realConnection, ConnectionPool connectionPool) {
        this.realConnection = realConnection;
        this.connectionPool = connectionPool;
    }

    public void reallyClose() {
        realConnection.close();
    }

    @Override
    public void close() {
        connectionPool.releaseConnection(this);
    }

    @Override
    public boolean isClosed() {
        return realConnection.isClosed();
    }
}
