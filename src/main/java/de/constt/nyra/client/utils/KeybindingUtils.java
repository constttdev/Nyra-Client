package de.constt.nyra.client.utils;

import org.lwjgl.glfw.GLFW;

public class KeybindingUtils {
    public static int getKeyCode(String keybindArg) {
        int keyCode = 0;

        if (keybindArg.length() == 1) {
            char c = keybindArg.charAt(0);
            if (c >= 'A' && c <= 'Z') keyCode = GLFW.GLFW_KEY_A + (c - 'A');
            else if (c >= '0' && c <= '9') keyCode = GLFW.GLFW_KEY_0 + (c - '0');
        } else if (keybindArg.equals("SPACE")) keyCode = GLFW.GLFW_KEY_SPACE;

        else if (keybindArg.equals("SHIFT")) keyCode = GLFW.GLFW_KEY_LEFT_SHIFT;
        return keyCode;
    }

    public static String getKeyName(int keyCode, boolean showNone) {
        if (keyCode == -1 && showNone) {
            return "NONE";
        }

        if (keyCode == GLFW.GLFW_KEY_UNKNOWN || keyCode == -1) {
            return "UNKNOWN";
        }

        String name = GLFW.glfwGetKeyName(keyCode, 0);

        if (name != null) {
            return name.toUpperCase();
        }

        return switch (keyCode) {
            case GLFW.GLFW_KEY_SPACE -> "SPACE";
            case GLFW.GLFW_KEY_ENTER -> "ENTER";
            case GLFW.GLFW_KEY_ESCAPE -> "ESC";
            case GLFW.GLFW_KEY_TAB -> "TAB";
            case GLFW.GLFW_KEY_BACKSPACE -> "BACKSPACE";
            case GLFW.GLFW_KEY_INSERT -> "INSERT";
            case GLFW.GLFW_KEY_DELETE -> "DELETE";
            case GLFW.GLFW_KEY_RIGHT -> "RIGHT";
            case GLFW.GLFW_KEY_LEFT -> "LEFT";
            case GLFW.GLFW_KEY_DOWN -> "DOWN";
            case GLFW.GLFW_KEY_UP -> "UP";
            case GLFW.GLFW_KEY_PAGE_UP -> "PAGE UP";
            case GLFW.GLFW_KEY_PAGE_DOWN -> "PAGE DOWN";
            case GLFW.GLFW_KEY_HOME -> "HOME";
            case GLFW.GLFW_KEY_END -> "END";
            case GLFW.GLFW_KEY_CAPS_LOCK -> "CAPS LOCK";
            case GLFW.GLFW_KEY_SCROLL_LOCK -> "SCROLL LOCK";
            case GLFW.GLFW_KEY_NUM_LOCK -> "NUM LOCK";
            case GLFW.GLFW_KEY_PRINT_SCREEN -> "PRINT SCREEN";
            case GLFW.GLFW_KEY_PAUSE -> "PAUSE";
            case GLFW.GLFW_KEY_LEFT_SHIFT -> "LEFT SHIFT";
            case GLFW.GLFW_KEY_LEFT_CONTROL -> "LEFT CTRL";
            case GLFW.GLFW_KEY_LEFT_ALT -> "LEFT ALT";
            case GLFW.GLFW_KEY_LEFT_SUPER -> "LEFT SUPER";
            case GLFW.GLFW_KEY_RIGHT_SHIFT -> "RIGHT SHIFT";
            case GLFW.GLFW_KEY_RIGHT_CONTROL -> "RIGHT CTRL";
            case GLFW.GLFW_KEY_RIGHT_ALT -> "RIGHT ALT";
            case GLFW.GLFW_KEY_RIGHT_SUPER -> "RIGHT SUPER";
            default -> {
                if (keyCode >= GLFW.GLFW_KEY_F1 && keyCode <= GLFW.GLFW_KEY_F25) {
                    yield "F" + (keyCode - GLFW.GLFW_KEY_F1 + 1);
                }

                if (keyCode >= GLFW.GLFW_KEY_0 && keyCode <= GLFW.GLFW_KEY_9) {
                    yield String.valueOf((char) ('0' + keyCode - GLFW.GLFW_KEY_0));
                }

                yield "UNKNOWN";
            }
        };
    }
}
