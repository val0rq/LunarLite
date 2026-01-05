package com.user.lunarlite.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class MainMenuScreen extends Screen {
    public MainMenuScreen() { super(Text.literal("LunarLite")); }

    @Override
    protected void init() {
        addDrawableChild(ButtonWidget.builder(Text.literal("Close"),
            b -> this.close()).dimensions(width/2-50, height/2, 100, 20).build());
    }

    @Override
    public void render(DrawContext ctx, int mx, int my, float d) {
        renderBackground(ctx);
        ctx.drawCenteredTextWithShadow(textRenderer, "LunarLite Menu", width/2, height/2-30, 0xFFFFFF);
        super.render(ctx, mx, my, d);
    }
}