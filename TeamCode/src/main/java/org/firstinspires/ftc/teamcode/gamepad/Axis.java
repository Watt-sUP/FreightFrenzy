package org.firstinspires.ftc.teamcode.gamepad;

import android.util.Pair;

import java.util.ArrayList;

public class Axis {
    private ArrayList<Pair<Double, Button>> buttons;
    Axis() {
        buttons = new ArrayList< >();
    }
    public double raw = 0.0;
    public void update(double value) {
        raw = value;
        for(Pair<Double, Button> b: buttons)
            b.second.update(value >= b.first);
    }

    public Button toButton(double threshold) {
        for(Pair<Double, Button> b: buttons)
            if( Math.abs(threshold - b.first) <= 1e-6 )
                return b.second;
        Button b = new Button();
        b.update(raw >= threshold);
        buttons.add(new Pair<>(threshold, b));
        return b;
    }
}
