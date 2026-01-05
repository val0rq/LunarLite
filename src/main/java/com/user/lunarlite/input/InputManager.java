package com.user.lunarlite.input;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class InputManager {
    public static boolean w, a, s, d;

    private static boolean lastShift;

    public static void tick(MinecraftClient client) {
        if (client.getWindow() == null) return;

        long h = client.getWindow().getHandle();

        w = GLFW.glfwGetKey(h, GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS;
        a = GLFW.glfwGetKey(h, GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS;
        s = GLFW.glfwGetKey(h, GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS;
        d = GLFW.glfwGetKey(h, GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS;

        boolean shift = GLFW.glfwGetKey(h, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
        if (shift && !lastShift && client.currentScreen == null) {
            client.setScreen(new com.user.lunarlite.gui.MainMenuScreen());
        }
        lastShift = shift;
    }
}