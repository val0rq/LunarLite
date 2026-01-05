package com.user.lunarlite.modules.impl;
import com.user.lunarlite.modules.HudModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class CoordsModule extends HudModule {
    public CoordsModule() { super("Coords", 10, 25); }

    @Override
    public void render(DrawContext context, MinecraftClient client) {
        if (client.player == null) return;
        String textStr = String.format("%.0f, %.0f, %.0f", client.player.getX(), client.player.getY(), client.player.getZ());
        Text text = Text.literal(textStr);
        this.width = client.textRenderer.getWidth(text);
        this.height = client.textRenderer.fontHeight;
        context.drawText(client.textRenderer, text, x, y, getColor(), true);
    }
}