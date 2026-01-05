package com.user.lunarlite.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import java.awt.Color;

public abstract class HudModule {
    public String name;
    public int x, y, width, height;
    public boolean enabled = true;
    public int colorIndex = 0; 

    public HudModule(String name, int defaultX, int defaultY) {
        this.name = name;
        this.x = defaultX;
        this.y = defaultY;
    }

    public abstract void render(DrawContext context, MinecraftClient client);
    
    public void renderDummy(DrawContext context) {
        context.fill(x, y, x + width, y + height, 0x40000000);
        drawBox(context, x, y, width, height, 0xFFFFFFFF);
        render(context, MinecraftClient.getInstance());
    }

    public void drawBox(DrawContext context, int x, int y, int w, int h, int color) {
        context.fill(x, y, x + w, y + 1, color); // Top
        context.fill(x, y + h - 1, x + w, y + h, color); // Bottom
        context.fill(x, y, x + 1, y + h, color); // Left
        context.fill(x + w - 1, y, x + w, y + h, color); // Right
    }

    public int getColor() {
        switch (colorIndex) {
            case 1: return 0xFFFF5555; // Red
            case 2: return 0xFF55FF55; // Green
            case 3: return 0xFF5555FF; // Blue
            case 4: return getChroma(3000); // Chroma
            default: return 0xFFFFFFFF; // White
        }
    }
    
    public void nextColor() {
        colorIndex++;
        if (colorIndex > 4) colorIndex = 0;
    }
    
    private int getChroma(int speed) {
        float hue = (System.currentTimeMillis() % speed) / (float)speed;
        return Color.getHSBColor(hue, 1f, 1f).getRGB();
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isEnabled() { return enabled; }
}