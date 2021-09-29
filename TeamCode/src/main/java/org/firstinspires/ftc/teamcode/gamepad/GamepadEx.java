package org.firstinspires.ftc.teamcode.gamepad;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadEx {
    private Gamepad gamepad;
    public Button dpad_up, dpad_down, dpad_left, dpad_right, a, b, x, y, start, back, left_bumper, right_bumper, left_joystick, right_joystick;
    public Axis left_x, left_y, right_x, right_y, left_trigger, right_trigger;

    public GamepadEx(Gamepad _gamepad) {
        gamepad = _gamepad;

        dpad_up = new Button();
        dpad_down = new Button();
        dpad_left = new Button();
        dpad_right = new Button();
        a = new Button();
        b = new Button();
        x = new Button();
        y = new Button();
        start = new Button();
        back = new Button();
        left_bumper = new Button();
        right_bumper = new Button();
        left_joystick = new Button();
        right_joystick = new Button();

        left_x = new Axis();
        left_y = new Axis();
        right_x = new Axis();
        right_y = new Axis();
        left_trigger = new Axis();
        right_trigger = new Axis();
    }

    public void update() {
        dpad_up.update(gamepad.dpad_up);
        dpad_down.update(gamepad.dpad_down);
        dpad_left.update(gamepad.dpad_left);
        dpad_right.update(gamepad.dpad_right);
        a.update(gamepad.a);
        b.update(gamepad.b);
        x.update(gamepad.x);
        y.update(gamepad.y);
        start.update(gamepad.start);
        back.update(gamepad.back);
        left_bumper.update(gamepad.left_bumper);
        right_bumper.update(gamepad.right_bumper);
        left_joystick.update(gamepad.left_stick_button);
        right_joystick.update(gamepad.right_stick_button);

        left_x.update(gamepad.left_stick_x);
        left_y.update(gamepad.left_stick_y);
        right_x.update(gamepad.right_stick_x);
        right_y.update(gamepad.right_stick_y);
        left_trigger.update(gamepad.left_trigger);
        right_trigger.update(gamepad.right_trigger);
    }
}
