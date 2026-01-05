package com.user.lunarlite.modules.impl;
import com.user.lunarlite.modules.HudModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

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