package com.example.patternbuilderfactory.DetectorAntivirusSoftwareCommons;
import com.example.patternbuilderfactory.AbstractCommons.AntivirusSoftware;
import com.example.patternbuilderfactory.AbstractCommons.Factory;

public class DetectorAsFactory extends Factory {

    @Override
    public AntivirusSoftware createAntivirusSoftware() {
        return new DetectorAntivirusSoft();
    }
}
