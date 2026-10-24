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
public class ChloeOp extends OpMode {
    private Follower follower; // Add this
    private final PoseFactory p = PoseFactory.degrees(); // add this
    private final Pose startPose = p.of(55, 33, 90);
    //
    private final Pose poseOne = p.of(44, 39, 165);
    //
    // other poses...
    private final Pose controlPose = p.of(31, 49, 135);
    private final Pose poseTwo = p.of(31, 66, 90);
    private final Pose poseThree = p.of(31, 87, 90);
    //
    private final Pose poseFour = p.of(30, 104, 90);
    private final Pose poseFive = p.of(31, 121, 0);
    private final Pose poseSix = p.of(46, 121, 280);
    private final Pose poseSeven = p.of(53, 108, 45);
    private final Pose poseEight = p.of(62, 120, 180);
    private final Pose poseNine = p.of(41, 121, 180);
    private final Pose poseTen = p.of(20, 122, 260);
    private final Pose poseEleven = p.of(15, 105, 270);
    private final Pose poseTwelve = p.of(14, 87, 270);
    private final Pose poseThirteen = p.of(14, 66, 285);
    private final Pose poseFourteen = p.of(17, 46, 300);
    private final Pose poseFifteen = p.of(35, 30, 320);
    private final Pose poseSixteen = p.of(46, 24, 290);
    private final Pose scorePose = p.of(53, 9, 90);
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
                follow(startPose park())
                follow(poseOne, park())
                follow(poseTwo, park())
                follow(poseThree, park())
                follow(poseFour, park())
                follow(poseFive, park())
                follow(follower, park())
                follow(follower, park())
                follow(follower, park())
                follow(follower, park())
                follow(follower, park())
                follow(follower, park())
                follow(follower, park())
                follow(follower, park())
                follow(follower, park())
                follow(follower, park())
                follow(follower, park())
                follow(follower, park())
                follow(follower, park())
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
        telemetry.addData("x", follower.pose().x());
        telemetry.addData("y", follower.pose().y());
        telemetry.addData("heading", Math.toDegrees(follower.pose().heading()));
    }
}