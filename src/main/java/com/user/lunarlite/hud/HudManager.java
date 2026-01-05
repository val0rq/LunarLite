package com.user.lunarlite.hud;

import com.user.lunarlite.hud.impl.*;
import net.minecraft.client.MinecraftClient;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.DrawContext;
import java.util.*;

public class HudManager {
    public static final List<HudModule> modules = new ArrayList<>();

    public static void init() {
        modules.add(new FpsHud());
        modules.add(new CoordsHud());
        modules.add(new KeystrokesHud());

        HudRenderCallback.EVENT.register((ctx, tick) -> render(ctx));
    }

    public static void tick(MinecraftClient client) {
        if (client.player == null || client.world == null) return;
        for (HudModule m : modules) if (m.enabled) m.tick(client);
    }

    private static void render(DrawContext ctx) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;
        for (HudModule m : modules) if (m.enabled) m.render(ctx);
    }
}