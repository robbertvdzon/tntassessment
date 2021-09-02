package com.vdzon.tntassessment.settings;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;

public class SettingsLoader {

    public static Settings loadSettings(){
        Config config = ConfigFactory.systemEnvironment();
        return Settings
                .builder()
                .port(getIntOrDefault(config, "SERVER_PORT", 7000))
                .build();

    }

    private static int getIntOrDefault(Config config, String path, int defaultInt) {
        try {
            return config.getInt(path);
        } catch (ConfigException.Missing ex) {
            return defaultInt;
        }
    }
}
