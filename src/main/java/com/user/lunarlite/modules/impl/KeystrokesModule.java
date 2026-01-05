package com.user.lunarlite.modules.impl;
import com.user.lunarlite.modules.HudModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;

public class KeystrokesModule extends HudModule {
    public KeystrokesModule() { super("Keystrokes", 10, 50); }

    @Override
    public void render(DrawContext context, MinecraftClient client) {
        this.width = 75;
        this.height = 75;
        
        int boxSize = 22;
        int gap = 3;
        
        long win = client.getWindow().getHandle();
        boolean w = GLFW.glfwGetKey(win, GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS;
        boolean a = GLFW.glfwGetKey(win, GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS;
        boolean s = GLFW.glfwGetKey(win, GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS;
        boolean d = GLFW.glfwGetKey(win, GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS;

        drawKey(context, client, "W", x + 25, y, w);
        drawKey(context, client, "A", x, y + 25, a);
        drawKey(context, client, "S", x + 25, y + 25, s);
        drawKey(context, client, "D", x + 50, y + 25, d);
    }
    
    private void drawKey(DrawContext context, MinecraftClient client, String key, int kX, int kY, boolean pressed) {
        int bg = pressed ? 0x90FFFFFF : 0x60000000;
        int txt = pressed ? 0xFF000000 : getColor();
        context.fill(kX, kY, kX + 22, kY + 22, bg);
        
        // Manual centering to avoid 'drawCenteredText' crash
        int txtWidth = client.textRenderer.getWidth(key);
        int txtX = kX + (22 - txtWidth) / 2;
        int txtY = kY + (22 - client.textRenderer.fontHeight) / 2 + 1;
        
        context.drawText(client.textRenderer, key, txtX, txtY, txt, true);
    }
}