package com.example.demo.common.dao;

import com.example.demo.common.model.Environment;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.auth.impl.AuthProviderInternal;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;




public class CommonDao {


    private JDBCClient jdbcClient;
    Vertx vertx;
    protected AuthProviderInternal authProvider;
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
    private static final String SQL_CREATE_TABLE_TEST = "create table if not exist Test (id integer identity primary key, name varchar(100)";


    private Future<Void> prepareDatabase() {
        Future<Void> future = Future.future();

        jdbcClient = JDBCClient.createShared(vertx, new JsonObject()
            .put("url", "jdbc:hsqldb:file:db/wiki")
            .put("driver_class", "org.hsqldb.jdbcDriver")
            .put("max_pool_size", 30));

        jdbcClient.getConnection(ar -> {
            if (ar.failed()) {
                LOGGER.error("no db connection", ar.cause());
                future.fail(ar.cause());
            }
            else {
                SQLConnection conn = ar.result();
                conn.execute(SQL_CREATE_TABLE_TEST, create -> {
                    conn.close();
                    if (create.failed()) {
                        LOGGER.error("create table failed", create.cause());
                        future.fail(create.cause());
                    } else {
                        future.complete();
                    }
                });
            }
        });
        return future;
    }


    public Environment getEnvironment(Long id) {
        return null;
    }


}
