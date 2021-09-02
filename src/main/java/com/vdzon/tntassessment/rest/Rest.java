package com.vdzon.tntassessment.rest;

import com.vdzon.tntassessment.aggregator.AggregatorService;
import com.vdzon.tntassessment.model.AggregatedResult;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class Rest {
    private final Javalin app;
    private final AggregatorService aggregatorService;

    public Rest(int port, AggregatorService aggregatorService) {
        app = Javalin.create().start(port);
        this.aggregatorService = aggregatorService;
    }

    public void start() {
        app.get("/health", ctx -> ctx.result("ok"));
        app.get("/aggregation", ctx -> getAggregation(ctx));
    }

    private Context getAggregation(Context ctx) {
        try {
            String pricingParam = ctx.queryParam("pricing");
            String trackParam = ctx.queryParam("track");
            String shipmentsParam = ctx.queryParam("shipments");

            AggregationRequest request = AggregationRequestParser.parseRequest(pricingParam, trackParam, shipmentsParam);
            AggregatedResult result = aggregatorService.aggregate(
                    request.getShipmentOrderNumbers(),
                    request.getTrackOrderNumbers(),
                    request.getPricingCountryCodes()
            );
            return ctx.json(result);
        }
        catch (InvalidAggregationRequestException e){
            ctx.status(400);
            ctx.result(e.getMessage());
        }
        return ctx;
    }

    public void stop() {
        app.stop();
    }

}
