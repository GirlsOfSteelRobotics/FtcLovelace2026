package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(group = "Examples", name = "GitanjaliTeleop")
public class GitanjaliTeleop extends OpMode {

    private Follower follower;

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
    }

    @Override
    public void loop() {
       double forward = -gamepad1.left_stick_y;
       double lateral = -gamepad1.left_stick_x;
       double turn = -gamepad1.right_stick_x;

       telemetry.addData("forwawrd", forward);
       telemetry.addData("lateral", lateral);
       telemetry.addData("turn", turn);

        follower.manual(forward,lateral,turn);
        follower.update();

        telemetry.addData("x", follower.pose().x());
        telemetry.addData("y", follower.pose().y());
        telemetry.addData("heading", Math.toDegrees(follower.pose().heading()));
        telemetry.update();
    }
}
