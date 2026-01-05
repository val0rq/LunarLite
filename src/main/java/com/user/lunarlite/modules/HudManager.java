package com.user.lunarlite.modules;

import com.user.lunarlite.config.ConfigManager;
import com.user.lunarlite.modules.impl.FpsModule;
import com.user.lunarlite.modules.impl.CoordsModule;
import com.user.lunarlite.modules.impl.KeystrokesModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import java.util.ArrayList;
import java.util.List;

public class HudManager {
    public static List<HudModule> modules = new ArrayList<>();

    public static void init() {
        register(new FpsModule());
        register(new CoordsModule());
        register(new KeystrokesModule());
        
        if (!ConfigManager.storedProps.isEmpty()) {
            for (HudModule m : modules) {
                if (ConfigManager.storedProps.containsKey(m.name + ".x")) {
                    try {
                        m.x = Integer.parseInt(ConfigManager.storedProps.getProperty(m.name + ".x"));
                        m.y = Integer.parseInt(ConfigManager.storedProps.getProperty(m.name + ".y"));
                        m.enabled = Boolean.parseBoolean(ConfigManager.storedProps.getProperty(m.name + ".enabled"));
                        m.colorIndex = Integer.parseInt(ConfigManager.storedProps.getProperty(m.name + ".colorIndex"));
                    } catch (Exception e) { e.printStackTrace(); }
                }
            }
        }
    }

    public static void register(HudModule m) { modules.add(m); }

    public static void renderAll(DrawContext context, float delta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.options.hudHidden) return;
        for (HudModule m : modules) {
            if (m.isEnabled()) m.render(context, client);
        }
    }
}