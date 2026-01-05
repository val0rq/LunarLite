package com.user.lunarlite.hud.impl;

import com.user.lunarlite.hud.HudModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class CoordsHud extends HudModule {
    private String text = "";

    public CoordsHud() { super("Coords", 10, 25); }

    @Override
    public void tick(MinecraftClient c) {
        text = (int)c.player.getX() + " " + (int)c.player.getY() + " " + (int)c.player.getZ();
        w = c.textRenderer.getWidth(text);
        h = c.textRenderer.fontHeight;
    }

    @Override
    public void render(DrawContext ctx) {
        ctx.drawText(MinecraftClient.getInstance().textRenderer, text, x, y, getColor(), true);
    }
}