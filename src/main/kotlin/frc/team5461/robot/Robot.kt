package frc.team5461.robot
import frc.team5461.subsystems.ExampleSubsystem
import edu.wpi.first.wpilibj.IterativeRobot

class Robot : IterativeRobot() {
    companion object {
        var exampleSubsystem = ExampleSubsystem()
    }
    override fun robotInit() {
        println("Hello World from Kotlin!")
    }
}