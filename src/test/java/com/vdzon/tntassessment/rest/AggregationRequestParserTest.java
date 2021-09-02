package com.vdzon.tntassessment.rest;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AggregationRequestParserTest {

    @Test
    public void testValidRequest() throws InvalidAggregationRequestException {
        AggregationRequest aggregationRequest = AggregationRequestParser.parseRequest("NL,CN", "109347263,123456891", "109347263,123456891");
        assertThat(aggregationRequest.getPricingCountryCodes()).isEqualTo(Arrays.asList("NL","CN"));
        assertThat(aggregationRequest.getTrackOrderNumbers()).isEqualTo(Arrays.asList("109347263","123456891"));
        assertThat(aggregationRequest.getShipmentOrderNumbers()).isEqualTo(Arrays.asList("109347263","123456891"));
    }

    @Test
    public void testPricingNull(){
        Exception exception = Assertions.assertThrows(InvalidAggregationRequestException.class, () -> AggregationRequestParser.parseRequest(null, "109347263,123456891", "109347263,123456891"));
        assertThat(exception.getMessage()).isEqualTo("pricing parameter expected");
    }

    @Test
    public void testTrackingNull(){
        Exception exception = Assertions.assertThrows(InvalidAggregationRequestException.class, () -> AggregationRequestParser.parseRequest("NL,CN", null, "109347263,123456891"));
        assertThat(exception.getMessage()).isEqualTo("track parameter expected");
    }

    @Test
    public void testShipmentNull(){
        Exception exception = Assertions.assertThrows(InvalidAggregationRequestException.class, () -> AggregationRequestParser.parseRequest("NL,CN", "109347263,123456891", null));
        assertThat(exception.getMessage()).isEqualTo("shipments parameter expected");
    }

    @Test
    public void testInvalidCountryCode(){
        Exception exception = Assertions.assertThrows(InvalidAggregationRequestException.class, () -> AggregationRequestParser.parseRequest("NL,XX", "109347263,123456891", "109347263,123456891"));
        assertThat(exception.getMessage()).isEqualTo("Country XX does not exist");
    }

    @Test
    public void testOrderNumberTooShort(){
        Exception exception = Assertions.assertThrows(InvalidAggregationRequestException.class, () -> AggregationRequestParser.parseRequest("NL,CN", "10934726,123456891", "109347263,123456891"));
        assertThat(exception.getMessage()).isEqualTo("Ordernumber 10934726 is invalid");
    }

    @Test
    public void testOrderNumberTooLong(){
        Exception exception = Assertions.assertThrows(InvalidAggregationRequestException.class, () -> AggregationRequestParser.parseRequest("NL,CN", "1093472633,123456891", "109347263,123456891"));
        assertThat(exception.getMessage()).isEqualTo("Ordernumber 1093472633 is invalid");
    }

    @Test
    public void testOrderNumberNotNumber(){
        Exception exception = Assertions.assertThrows(InvalidAggregationRequestException.class, () -> AggregationRequestParser.parseRequest("NL,CN", "10934726A,123456891", "109347263,123456891"));
        assertThat(exception.getMessage()).isEqualTo("Ordernumber 10934726A is invalid");
    }



}