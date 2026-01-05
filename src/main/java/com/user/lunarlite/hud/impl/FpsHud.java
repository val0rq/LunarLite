package com.user.lunarlite.hud.impl;

import com.user.lunarlite.hud.HudModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class FpsHud extends HudModule {
    private String text = "";

    public FpsHud() { super("FPS", 10, 10); }

    @Override
    public void tick(MinecraftClient client) {
        text = client.getDebugHud().getFps() + " FPS";
        w = client.textRenderer.getWidth(text);
        h = client.textRenderer.fontHeight;
    }

    @Override
    public void render(DrawContext ctx) {
        ctx.drawText(MinecraftClient.getInstance().textRenderer, text, x, y, getColor(), true);
    }
}