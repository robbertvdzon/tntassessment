package com.vdzon.tntassessment.rest;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class AggregationRequest {
    List<String> shipmentOrderNumbers;
    List<String> trackOrderNumbers;
    List<String> pricingCountryCodes;
}
