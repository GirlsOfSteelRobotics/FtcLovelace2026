package org.firstinspires.ftc.teamcode.autos;

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
import org.firstinspires.ftc.teamcode.OpModeStorage;

@Autonomous(group = "Autos", name = "Practice auto")
public class PracticeAuto extends OpMode {
    // Set up bot
    private Follower follower;

    // Set up paths and poses
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose startPose = poseFactory.of(-15, -63.5, 90);
    private final Pose gardenPose = poseFactory.of(-60, -63.5, 90);
    private final Pose scorePose = poseFactory.of(-15, 60, 90);

    private final Pose safetyPose = poseFactory.of(-15,40,90);

    private final Pose parkPose = poseFactory.of(-60, 40, 90);

    // Path methods
    private Path goToGarden() {
        return line(startPose, gardenPose).linear(startPose, gardenPose);
    }

    private Path backToStart(){
        return line(gardenPose, startPose).linear(gardenPose, startPose);
    }

    private Path crossField(){
        return line(startPose, scorePose).linear(startPose, scorePose);
    }

    private Path safetyPositionGo(){
        return line(scorePose, safetyPose).linear(scorePose, safetyPose);
    }

    private Path goPark(){
        return line(safetyPose, parkPose).linear(safetyPose, parkPose);
    }
    private Command autoRoutine() {
        return sequential(
                follow(follower, goToGarden()),
                // Add mechanism commands here.
                follow(follower, backToStart()),
                follow(follower, crossField()),
                follow(follower, safetyPositionGo()),
                follow(follower, goPark())
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
        schedule(autoRoutine());
    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();

        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("Follower Mode", follower.mode());
        telemetry.update();
    }

    public void stop()
    {
        OpModeStorage.autonmousEndPose = follower.pose();
    }
}
