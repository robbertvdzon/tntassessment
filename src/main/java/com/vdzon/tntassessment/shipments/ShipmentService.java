package com.vdzon.tntassessment.shipments;

import java.util.List;
import java.util.Map;

public interface ShipmentService {
    Map<String, List<String>> getShipments(List<String> ids);
}
