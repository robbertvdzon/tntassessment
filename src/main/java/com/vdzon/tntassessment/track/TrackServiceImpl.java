package com.vdzon.tntassessment.track;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrackServiceImpl implements TrackService{
    @Override
    public Map<String, String> getTracking(List<String> countryCodes) {
        return countryCodes
                .stream()
                .collect(Collectors.toMap(cc -> cc, cc -> "NEW"));
    }
}
