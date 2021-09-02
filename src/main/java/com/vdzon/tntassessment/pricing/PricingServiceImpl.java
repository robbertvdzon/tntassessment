package com.vdzon.tntassessment.pricing;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PricingServiceImpl implements PricingService {

    @Override
    public Map<String, Double> getPrices(List<String> countryCodes) {
        return countryCodes
                .stream()
                .collect(Collectors.toMap(cc -> cc, cc -> 1.01));
    }
}
