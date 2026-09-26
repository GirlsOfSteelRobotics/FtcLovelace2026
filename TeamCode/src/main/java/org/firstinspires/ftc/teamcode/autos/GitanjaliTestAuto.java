package org.firstinspires.ftc.teamcode.autos;

import static com.pedropathing.api.Paths.curve;
import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Constants;

@Autonomous
public class GitanjaliTestAuto extends OpMode {
    private Follower follower; // Add this
    private final PoseFactory p = PoseFactory.degrees(); // add this
    private final Pose startPose = p.of(24, 24, 0);
    private final Pose park = p.of(48, 48, 90);
    // other poses...
    private final Pose controlPose = p.of(36, 60, 45);
    private final Pose parkPose = p.of(72, 48, 90);
    private final Pose scorePose = p.of(48, 48, 90);
    private Path parkCurved() {
        return curve(startPose, controlPose, park).linear(startPose, park);
    }
    private Path park() {
        return line(startPose, park).linear(startPose, park);
    }
    private Path startToScore() {
        return line(startPose, scorePose).linear(startPose, scorePose);
    }
    private Command autoRoutine() {
        return sequential(
                follow(follower, startToScore()),
                follow(follower, park())
        );
    }
    @Override
    public void init() {
        Scheduler.reset();

        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
        follower.update();
    }
    @Override
    public void start() {
        schedule(follow(follower, park()));
    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();

    }
}