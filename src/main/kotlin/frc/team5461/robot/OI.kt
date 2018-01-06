package frc.team5461.robot

import edu.wpi.first.wpilibj.*
import edu.wpi.first.wpilibj.buttons.JoystickButton
import frc.team5461.subsystems.ExampleSubsystem

class OI {
    val stick = Joystick(0)
    var button = JoystickButton(stick, 0)
    init {
        button.whenPressed(Robot.exampleSubsystem.exampleCommand)
    }
}