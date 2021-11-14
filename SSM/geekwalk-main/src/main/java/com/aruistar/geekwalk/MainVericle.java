package com.aruistar.geekwalk;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class MainVericle extends AbstractVerticle {
    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new MainVericle());
        Vertx.vertx().deployVerticle(new serverVerticle());
    }

    String deployVerticleID;

    @Override
    public void start() throws Exception {

        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route()
                .handler(
                        CorsHandler.create("*")
                                .allowedMethod(HttpMethod.GET)
                );
        router.post("/config")
                .produces("text/plain")
                .handler(BodyHandler.create())
                .handler(routingContext ->{
                    HttpServerResponse response = routingContext.response();
                    JsonObject json = routingContext.getBodyAsJson();
                    System.out.println(json.toString());

                    if (deployVerticleID!=null) {
                        vertx.undeploy(deployVerticleID);
                    }

                    vertx.deployVerticle("com.aruistar.geekwalk.ProxyVerticle",
                            new DeploymentOptions().setInstances(4).setConfig(json)
                    )
                            .onSuccess(result ->{
                                deployVerticleID = result;
                                response.end(deployVerticleID);
                            })
                            .onFailure(err ->{
                                err.printStackTrace();
                                response.setStatusCode(500).end(err.getMessage());
                            });
                });

        router.route().handler(StaticHandler.create());

        server.requestHandler(router).listen(8888,serverAsyncResult -> {
            if (serverAsyncResult.succeeded()) {
                System.out.println("8080启动");
            }
        });
    }

    @Override
    public void start (Promise startPromise) {
        System.out.println("hello");
    }
}
