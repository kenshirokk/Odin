package com.kenshiro.vertx.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystem;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.*;

import java.util.List;
import java.util.stream.Stream;

public class VertxFileSystem extends AbstractVerticle {

    private final SqlConnectOptions option;

    public VertxFileSystem(PgConnectOptions option) {
        this.option = option;
    }

    @Override
    public void start() throws Exception {
        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(5);

        Pool pool = Pool.pool(vertx, option, poolOptions);

        FileSystem fs = vertx.fileSystem();
        Future<List<String>> listFuture = fs.readDir("K:/picture/comic/美麗新世界");
        listFuture.onSuccess(files -> {
            Stream<String> stringStream =
                    files.stream().filter(s -> s.contains("_")).map(s->s.substring(s.lastIndexOf("\\")+1));
            stringStream.forEach(file -> {
                String[] name = file.split("_");


//                pool.preparedQuery("INSERT INTO comic(name, episode, number) VALUES ($1,$2,$3)")
//                        .execute(Tuple.of("美麗新世界", name[0],name[1]) ,ar->{
//                            if (ar.succeeded()) {
//                                RowSet<Row> result = ar.result();
//                                System.out.println(result.rowCount());
//                            } else {
//                                ar.cause().printStackTrace();
//                            }
//                        });
            });
        });


    }

    public static void main(String[] args) {
//        m1();
//        PgConnectOptions option = new PgConnectOptions()
//                .setHost("10.0.0.15")
//                .setPort(5432)
//                .setDatabase("postgres")
//                .setUser("postgres")
//                .setPassword("password");
        Vertx vertx = Vertx.vertx();
//        vertx.deployVerticle(new VertxFileSystem(option));
        FileSystem fs = vertx.fileSystem();
        Future<List<String>> listFuture = fs.readDir("K:/picture/comic/美麗新世界", "logo\\d{1}\\..*");
        listFuture.onSuccess(s -> System.out.println(s.size()));
    }

    public static void m1() {
        PgConnectOptions connectOptions = new PgConnectOptions()
                .setPort(5432)
                .setHost("10.0.0.15")
                .setDatabase("postgres")
                .setUser("postgres")
                .setPassword("password");

        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(5);

// Create the client pool
        PgPool pool = PgPool.pool(connectOptions, poolOptions);
        pool.getConnection().onSuccess(con -> {
                    System.out.println("Connected");
                    System.out.println(con);
                    con.query("SELECT * FROM comic WHERE id<9115")
                            .execute(ar -> {
                                if (ar.succeeded()) {
                                    RowSet<Row> result = ar.result();
                                    System.out.println("Got " + result.size() + " rows ");
                                } else {
                                    System.out.println("Failure: " + ar.cause().getMessage());
                                }

                                // Now close the pool
                                con.close();
                            });
                })
                .onFailure(Throwable::printStackTrace);

// A simple query

    }
}
