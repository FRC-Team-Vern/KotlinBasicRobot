package frc.team5461.robot
import frc.team5461.subsystems.ExampleSubsystem
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard

class Robot : TimedRobot() {
    companion object {
        val exampleSubsystem = ExampleSubsystem()
        // We choose to have a true default command instead of possibly leaving this null
        var m_autonomousCommand: Command? = null
        val oi = OI()
    }

    var m_chooser = SendableChooser<Command>()

    override fun robotInit(){
        m_chooser.addDefault("Default Auto", exampleSubsystem.exampleCommand)
        SmartDashboard.putData("Auto Mode", m_chooser)
    }

    override fun disabledPeriodic() {
        Scheduler.getInstance().run()
    }

    override fun autonomousInit() {
        m_autonomousCommand = m_chooser.selected
        m_autonomousCommand?.start()
    }

    override fun autonomousPeriodic() {
        Scheduler.getInstance().run()
    }

    override fun teleopInit() {
        m_autonomousCommand?.cancel()
    }

    override fun teleopPeriodic() {
        Scheduler.getInstance().run()
    }

    override fun testPeriodic() {
    }
}