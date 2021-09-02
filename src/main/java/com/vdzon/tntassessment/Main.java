package com.vdzon.tntassessment;

import com.vdzon.tntassessment.rest.Rest;
import com.vdzon.tntassessment.settings.Settings;
import com.vdzon.tntassessment.settings.SettingsLoader;

public class Main {
    private final Rest rest;

    public static void main(String[] args) {
        Settings settings = SettingsLoader.loadSettings();
        new Main(settings).start();
    }

    public Main(Settings settings) {
        rest = new Rest(settings.getPort());
    }

    public void start() {
        rest.start();
    }

    public void stop() {
        rest.stop();
    }

}


