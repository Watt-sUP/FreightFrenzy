package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

public class GeometryHelper {
    public static final double LENGTH = 17.75;
    public static final double WIDTH = 17.5;

    public static final Vector2d tower = new Vector2d(-72, -36);

    public static double getHeadingForPoint(Vector2d point, Vector2d target) {
        double left = Math.toRadians(-60), right = Math.toRadians(60);
        double X = target.getX(), Y = target.getY();

        for(int i = 0; i < 20; i++) {
            double ang = (left + right) / 2.0;

            Vector2d p = point.plus(new Vector2d(0, WIDTH / 2).rotated(ang));

            double myX = 0; // formula for x, when y = Y;
            if(myX <= X)    left = ang;
            else            right = ang;
        }

        return (left + right) / 2.0;
    }

    public static double getHeadingForTower(Vector2d point) {
        return getHeadingForPoint(point, tower);
    }
}
