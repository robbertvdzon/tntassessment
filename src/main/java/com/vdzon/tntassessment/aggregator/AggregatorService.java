package com.vdzon.tntassessment.aggregator;
import com.vdzon.tntassessment.model.AggregatedResult;
import com.vdzon.tntassessment.pricing.PricingService;
import com.vdzon.tntassessment.shipments.ShipmentService;
import com.vdzon.tntassessment.track.TrackService;

import java.util.List;
import java.util.Map;

public class AggregatorService {
    private final PricingService pricingService;
    private final ShipmentService shipmentService;
    private final TrackService trackService;

    public AggregatorService(PricingService pricingService, ShipmentService shipmentService, TrackService trackService){
        this.pricingService = pricingService;
        this.shipmentService = shipmentService;
        this.trackService = trackService;
    }

    public AggregatedResult aggregate(List<String> shipmentOrderNumbers, List<String> trackOrderNumbers, List<String> pricingCountryCodes){
        Map<String, Double> prices = pricingService.getPrices(pricingCountryCodes);
        Map<String, List<String>> shipments = shipmentService.getShipments(shipmentOrderNumbers);
        Map<String, String> tracking = trackService.getTracking(trackOrderNumbers);

        return AggregatedResult
                .builder()
                .pricing(prices)
                .shipments(shipments)
                .track(tracking)
                .build();
    }
}
