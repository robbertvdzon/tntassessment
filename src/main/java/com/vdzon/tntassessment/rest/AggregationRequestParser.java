package com.vdzon.tntassessment.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AggregationRequestParser {
    private static final List<String> isoCountries = Arrays.asList(Locale.getISOCountries());
    private static final Pattern orderNumberPattern = Pattern.compile("\\d{9}");
    public static AggregationRequest parseRequest(String pricingParam, String trackParam, String shipmentsParam) throws InvalidAggregationRequestException{

        // check if params are provided
        if (pricingParam==null) throw new InvalidAggregationRequestException("pricing parameter expected");
        if (trackParam==null) throw new InvalidAggregationRequestException("track parameter expected");
        if (shipmentsParam==null) throw new InvalidAggregationRequestException("shipments parameter expected");

        List<String> pricingCountryCodes =  Arrays.stream(pricingParam.split(",")).collect(Collectors.toList());
        List<String> trackOrderIds =  Arrays.stream(trackParam.split(",")).collect(Collectors.toList());
        List<String> shipmentOrderIds =  Arrays.stream(shipmentsParam.split(",")).collect(Collectors.toList());

        // validate all country codes
        for (String pricingCountryCode : pricingCountryCodes) {
            validateCountryCode(pricingCountryCode);
        }
        // validate all order IDs
        for (String trackOrderId : trackOrderIds) {
            validateOrderNumber(trackOrderId);
        }
        for (String shipmentOrderId : shipmentOrderIds) {
            validateOrderNumber(shipmentOrderId);
        }

        return AggregationRequest
                .builder()
                .pricingCountryCodes(pricingCountryCodes)
                .trackOrderNumbers(trackOrderIds)
                .shipmentOrderNumbers(shipmentOrderIds)
                .build();
    }

    private static void validateCountryCode(String countryCode) throws InvalidAggregationRequestException {
        if (!isoCountries.contains(countryCode)){
            throw new InvalidAggregationRequestException("Country "+countryCode+" does not exist");
        }


    }
    private static void validateOrderNumber(String orderNumber) throws InvalidAggregationRequestException {
        if (!orderNumberPattern.matcher(orderNumber).matches()){
            throw new InvalidAggregationRequestException("Ordernumber "+orderNumber+" is invalid");
        }

    }
}
