package com.vdzon.tntassessment.rest;

import io.javalin.Javalin;

public class Rest {
    private final Javalin app;

    public Rest(int port) {
        app = Javalin.create().start(port);
    }

    public void start() {
        app.get("/health", ctx -> ctx.result("ok"));
    }

    public void stop() {
        app.stop();
    }

}
