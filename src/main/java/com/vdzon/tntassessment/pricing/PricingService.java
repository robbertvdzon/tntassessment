package com.vdzon.tntassessment.pricing;

import java.util.List;
import java.util.Map;

public interface PricingService {
    Map<String, Double> getPrices(List<String> countryCodes);
}
