package com.user.lunarlite.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class MainMenuScreen extends Screen {
    public MainMenuScreen() { super(Text.literal("LunarLite Menu")); }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Edit HUD Positions"), button -> {
            this.client.setScreen(new HudEditorScreen(this));
        }).dimensions(centerX - 75, centerY - 25, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Close"), button -> {
            this.client.setScreen(null);
        }).dimensions(centerX - 75, centerY + 5, 150, 20).build());
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, "LunarLite Menu", this.width / 2, this.height / 2 - 50, 0xFFFFFF);
    }
}