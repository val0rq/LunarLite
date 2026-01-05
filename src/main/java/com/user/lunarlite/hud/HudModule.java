package com.user.lunarlite.hud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import java.awt.Color;

public abstract class HudModule {
    public String name;
    public int x, y, w, h;
    public boolean enabled = true;
    public int color = 0;

    protected HudModule(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public abstract void tick(MinecraftClient client);
    public abstract void render(DrawContext ctx);

    public int getColor() {
        if (color == 4) {
            float hue = (System.currentTimeMillis() % 3000) / 3000f;
            return Color.HSBtoRGB(hue, 1f, 1f);
        }
        return switch (color) {
            case 1 -> 0xFFFF5555;
            case 2 -> 0xFF55FF55;
            case 3 -> 0xFF5555FF;
            default -> 0xFFFFFFFF;
        };
    }

    public void nextColor() {
        color = (color + 1) % 5;
    }
}