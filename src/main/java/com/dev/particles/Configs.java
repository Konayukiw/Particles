package com.dev.particles;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class Configs {
    private static Configuration config;
    public static float globalScale = 1.0f;

    public static void init(File configFile) {
        config = new Configuration(configFile);
        config.load();

        Property scaleProp = config.get("general", "global_scale", 1.0f, "Global particle scale multiplier (0.1 to 10)");
        globalScale = (float) scaleProp.getDouble();

        if (config.hasChanged()) {
            config.save();
        }
    }

    public static void setGlobalScale(float scale) {
        globalScale = scale;
        config.get("general", "global_scale", 1.0f).set(scale);
        config.save();
    }
}