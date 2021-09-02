package com.vdzon.tntassessment.shipments;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShipmentServiceImpl implements ShipmentService{

    @Override
    public Map<String, List<String>> getShipments(List<String> ids) {
        return ids
                .stream()
                .collect(Collectors.toMap(id -> id, id -> Arrays.asList("box", "box")));
    }
}
