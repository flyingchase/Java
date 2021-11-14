package com.aruistar.geekwalk;

import com.aruistar.geekwalk.domain.Backend;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpServer;

import java.util.ArrayList;

public class ProxyVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        HttpClientOptions clientOptions = new HttpClientOptions();
        clientOptions.setDefaultHost("127.0.0.1");
        clientOptions.setDefaultPort(8080);

        HttpClient client = vertx.createHttpClient();

        server.requestHandler(req -> {
            client.request(req.method(),req.uri(),ar->{
                if (ar.succeeded()) {
                    HttpClientRequest req2 = ar.result();
                    req2.end()
                }
            })
        }).listen(9090,event -> {
            if (event.succeeded()) {
                System.out.println("8080端口成功启动");
            }
        });
    }


    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        int port = config().getInteger("port");

        ArrayList<Backend> backendsList = new ArrayList<Backend>();



    }
}
