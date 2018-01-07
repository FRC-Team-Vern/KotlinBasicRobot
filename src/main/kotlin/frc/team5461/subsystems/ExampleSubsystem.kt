package frc.team5461.subsystems

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.Subsystem
import frc.team5461.robot.Robot

class ExampleSubsystem: Subsystem() {
    val exampleCommand = object : Command(){
        init {
            requires(this@ExampleSubsystem)
        }
        override fun isFinished(): Boolean {
            return true
        }

    }
    override fun initDefaultCommand() {
        defaultCommand = exampleCommand
    }

}