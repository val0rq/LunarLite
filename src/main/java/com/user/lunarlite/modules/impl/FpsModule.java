package com.user.lunarlite.modules.impl;

import com.user.lunarlite.modules.HudModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;

public class FpsModule extends HudModule {
    public FpsModule() { super("FPS", 10, 10); }

    @Override
    public void render(DrawContext context, MinecraftClient client) {
        String text = client.getCurrentFps() + " FPS";
        this.width = client.textRenderer.getWidth(text);
        this.height = client.textRenderer.fontHeight;
        context.drawTextWithShadow(client.textRenderer, text, x, y, getColor());
    }
}

class CoordsModule extends HudModule {
    public CoordsModule() { super("Coords", 10, 25); }

    @Override
    public void render(DrawContext context, MinecraftClient client) {
        if (client.player == null) return;
        String text = String.format("%.0f, %.0f, %.0f", client.player.getX(), client.player.getY(), client.player.getZ());
        this.width = client.textRenderer.getWidth(text);
        this.height = client.textRenderer.fontHeight;
        context.drawTextWithShadow(client.textRenderer, text, x, y, getColor());
    }
}

class KeystrokesModule extends HudModule {
    public KeystrokesModule() { super("Keystrokes", 10, 50); }

    @Override
    public void render(DrawContext context, MinecraftClient client) {
        this.width = 75;
        this.height = 75;
        
        int boxSize = 22;
        int gap = 3;
        int startX = x;
        int startY = y;
        
        long win = client.getWindow().getHandle();
        boolean w = GLFW.glfwGetKey(win, GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS;
        boolean a = GLFW.glfwGetKey(win, GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS;
        boolean s = GLFW.glfwGetKey(win, GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS;
        boolean d = GLFW.glfwGetKey(win, GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS;

        // W
        drawKey(context, client, "W", startX + boxSize + gap, startY, w);
        // A
        drawKey(context, client, "A", startX, startY + boxSize + gap, a);
        // S
        drawKey(context, client, "S", startX + boxSize + gap, startY + boxSize + gap, s);
        // D
        drawKey(context, client, "D", startX + (boxSize + gap) * 2, startY + boxSize + gap, d);
    }
    
    private void drawKey(DrawContext context, MinecraftClient client, String key, int kX, int kY, boolean pressed) {
        int bg = pressed ? 0x90FFFFFF : 0x60000000;
        int txt = pressed ? 0xFF000000 : getColor();
        
        context.fill(kX, kY, kX + 22, kY + 22, bg);
        context.drawCenteredTextWithShadow(client.textRenderer, key, kX + 11, kY + 7, txt);
    }
}