package com.vdzon.tntassessment;

import com.vdzon.tntassessment.aggregator.AggregatorService;
import com.vdzon.tntassessment.pricing.PricingService;
import com.vdzon.tntassessment.pricing.PricingServiceImpl;
import com.vdzon.tntassessment.rest.Rest;
import com.vdzon.tntassessment.settings.Settings;
import com.vdzon.tntassessment.settings.SettingsLoader;
import com.vdzon.tntassessment.shipments.ShipmentService;
import com.vdzon.tntassessment.shipments.ShipmentServiceImpl;
import com.vdzon.tntassessment.track.TrackService;
import com.vdzon.tntassessment.track.TrackServiceImpl;

public class Main {
    private final Rest rest;

    public static void main(String[] args) {
        Settings settings = SettingsLoader.loadSettings();
        new Main(settings).start();
    }

    public Main(Settings settings) {
        ShipmentService shipmentService = new ShipmentServiceImpl();
        TrackService trackService = new TrackServiceImpl();
        PricingService pricingService = new PricingServiceImpl();
        AggregatorService aggregatorService = new AggregatorService(pricingService, shipmentService, trackService);
        rest = new Rest(settings.getPort(), aggregatorService);
    }

    public void start() {
        rest.start();
    }

    public void stop() {
        rest.stop();
    }

}


