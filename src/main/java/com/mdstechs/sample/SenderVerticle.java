package com.mdstechs.sample;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

import java.util.UUID;

public class SenderVerticle extends AbstractVerticle {


    @Override
    public void start(Future<Void> future) {

        vertx.createHttpServer()
                .requestHandler(req -> {
                    System.out.println("Received request");
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.put("message", UUID.randomUUID().toString());
                    vertx.eventBus().send("address", jsonObject);
                    req.response().end("Hello World!");
                })
                .listen(8080, handler -> {
                    if (handler.succeeded()) {
                        System.out.println("http://localhost:8080/");
                    } else {
                        System.err.println("Failed to listen on port 8080");
                    }
                });

    }
}
