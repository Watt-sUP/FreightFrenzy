package org.firstinspires.ftc.teamcode.gamepad;

public class Button {
    public boolean raw = false;
    public boolean toggle = false;

    private boolean isPressed = false;
    private boolean pending = false;

    Button() { }

    public void update(boolean value) {
        raw = value;
        if(value) {
            if (!isPressed) {
                toggle = !toggle;
                isPressed = true;
                pending = true;
            }
        } else {
            isPressed = false;
            pending = false;
        }
    }

    public boolean pressed() {
        boolean ans = pending;
        pending = false;
        return ans;
    }
}
