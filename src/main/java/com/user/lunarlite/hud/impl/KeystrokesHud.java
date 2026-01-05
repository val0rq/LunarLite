package com.user.lunarlite.hud.impl;

import com.user.lunarlite.hud.HudModule;
import com.user.lunarlite.input.InputManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class KeystrokesHud extends HudModule {
    public KeystrokesHud() { super("Keys", 10, 50); }

    @Override
    public void tick(MinecraftClient client) {
        w = 75;
        h = 75;
    }

    @Override
    public void render(DrawContext ctx) {
        draw(ctx, "W", x+25, y, InputManager.w);
        draw(ctx, "A", x, y+25, InputManager.a);
        draw(ctx, "S", x+25, y+25, InputManager.s);
        draw(ctx, "D", x+50, y+25, InputManager.d);
    }

    private void draw(DrawContext ctx, String k, int x, int y, boolean p) {
        ctx.fill(x, y, x+22, y+22, p?0x90FFFFFF:0x60000000);
        ctx.drawText(MinecraftClient.getInstance().textRenderer, k, x+7, y+7, getColor(), true);
    }
}