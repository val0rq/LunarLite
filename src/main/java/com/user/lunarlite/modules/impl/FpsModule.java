package com.user.lunarlite.modules.impl;
import com.user.lunarlite.modules.HudModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class FpsModule extends HudModule {
    public FpsModule() { super("FPS", 10, 10); }

    @Override
    public void render(DrawContext context, MinecraftClient client) {
        String textStr = client.getCurrentFps() + " FPS";
        Text text = Text.literal(textStr);
        this.width = client.textRenderer.getWidth(text);
        this.height = client.textRenderer.fontHeight;
        context.drawText(client.textRenderer, text, x, y, getColor(), true);
    }
}