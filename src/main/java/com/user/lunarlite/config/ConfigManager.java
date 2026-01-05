package com.user.lunarlite.config;

import com.user.lunarlite.modules.HudManager;
import com.user.lunarlite.modules.HudModule;
import java.io.*;
import java.util.Properties;

public class ConfigManager {
    private static final File FILE = new File("lunarlite_config.properties");
    public static Properties storedProps = new Properties();

    public static void save() {
        Properties props = new Properties();
        for (HudModule m : HudManager.modules) {
            props.setProperty(m.name + ".x", String.valueOf(m.getX()));
            props.setProperty(m.name + ".y", String.valueOf(m.getY()));
            props.setProperty(m.name + ".enabled", String.valueOf(m.isEnabled()));
            props.setProperty(m.name + ".colorIndex", String.valueOf(m.colorIndex));
        }
        try (FileOutputStream out = new FileOutputStream(FILE)) {
            props.store(out, "LunarLite Configuration");
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void load() {
        if (!FILE.exists()) return;
        try (FileInputStream in = new FileInputStream(FILE)) {
            storedProps.load(in);
        } catch (IOException e) { e.printStackTrace(); }
    }
}