package com.example.patternbuilderfactory.DoctorAntivirusSoftwareCommons;

import com.example.patternbuilderfactory.AbstractCommons.AntivirusSoftware;
import com.example.patternbuilderfactory.AbstractCommons.Factory;


public class DoctorAsFactory extends Factory {
    @Override
    public AntivirusSoftware createAntivirusSoftware() {
        return new DoctorAntivirusSoftware();
    }
}
