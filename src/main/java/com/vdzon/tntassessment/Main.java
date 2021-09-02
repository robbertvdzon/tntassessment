package com.vdzon.tntassessment;

import io.javalin.Javalin;

public class Main {
    private final Javalin app;

    public static void main(String[] args) {
        new Main().start();
    }

    public Main() {
        app = Javalin.create().start(7000);
    }

    public void start() {
        app.get("/health", ctx -> ctx.result("ok"));
    }

    public void stop() {
        app.stop();
    }

}


