package com.vdzon.tntassessment.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
@Builder
public class AggregatedResult {
    Map<String, Double> pricing;
    Map<String, String> track;
    Map<String, List<String>> shipments;
}
