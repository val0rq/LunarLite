package com.user.lunarlite.gui;

import com.user.lunarlite.config.ConfigManager;
import com.user.lunarlite.modules.HudManager;
import com.user.lunarlite.modules.HudModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class HudEditorScreen extends Screen {
    private final Screen parent;
    private HudModule draggingModule = null;
    private int dragX, dragY;

    public HudEditorScreen(Screen parent) {
        super(Text.literal("HUD Editor"));
        this.parent = parent;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        
        // Draw grid or instructions
        context.drawCenteredTextWithShadow(this.textRenderer, "Drag to move. Right-Click to change Color. ESC to Save.", this.width/2, 10, 0xFFFFFF);

        for (HudModule m : HudManager.modules) {
            if (m.isEnabled()) {
                m.renderDummy(context);
                
                // Highlight if hovering
                if (isHovering(m, mouseX, mouseY)) {
                    context.drawBorder(m.x - 1, m.y - 1, m.width + 2, m.height + 2, 0xFF00FF00); // Green selection
                }
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (HudModule m : HudManager.modules) {
            if (isHovering(m, (int)mouseX, (int)mouseY)) {
                if (button == 0) { // Left Click -> Drag
                    draggingModule = m;
                    dragX = (int)mouseX - m.x;
                    dragY = (int)mouseY - m.y;
                    return true;
                } else if (button == 1) { // Right Click -> Change Color
                    m.nextColor();
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (draggingModule != null) {
            draggingModule.x = (int)mouseX - dragX;
            draggingModule.y = (int)mouseY - dragY;
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        draggingModule = null;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void close() {
        ConfigManager.save(); // Save on exit
        this.client.setScreen(parent);
    }

    private boolean isHovering(HudModule m, int mx, int my) {
        return mx >= m.x && mx <= m.x + m.width && my >= m.y && my <= m.y + m.height;
    }
}