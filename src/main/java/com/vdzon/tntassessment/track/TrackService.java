package com.vdzon.tntassessment.track;

import java.util.List;
import java.util.Map;

public interface TrackService {
    Map<String, String> getTracking(List<String> countryCodes);
}
