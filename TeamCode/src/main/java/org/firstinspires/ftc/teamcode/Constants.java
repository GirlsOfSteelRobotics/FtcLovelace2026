package org.firstinspires.ftc.teamcode;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {

    // Insert drivetrain config here
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("FL");
        c.frontRightName.set("FR");
        c.backLeftName.set("BL");
        c.backRightName.set("BR");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });

    // Insert localization config here
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("Pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);
        c.xPodOffset.set(-7.588631337083231);
        c.yPodOffset.set(1.6737365722656252);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    // Insert Foresignt config here
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.1936378433364089);
                Controller secondaryTranslationalForward = Controller.proportional(0.07154400901433638);
                Controller primaryTranslationalLateral = Controller.proportional(0.38383069990157337);
                Controller secondaryTranslationalLateral = Controller.proportional(0.14181518746843985);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.01716278581383329));
                c.brake.set(Controller.proportionalFeedforward(0.014588367941758294));

                c.headingFeedback.set(Controller.proportional(2.920676453025459));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.04258888734766021, 0.009806759271206465));

                c.linearBrakeCoefficients.set(Matrix.diag(0.03915329459284395, 0.032263262265479854));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0028163179577476464, 0.0029853703943381327));

                c.maxAchievableForwardVelocity.set(60.3207022918236);
                c.maxAchievableStrafeVelocity.set(50.12832729021902);
                c.naturalForwardDeceleration.set(30.80485074714153);
                c.naturalStrafeDeceleration.set(52.865977117783814);
            }
    );

    // Insert Follower create() here

    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
}