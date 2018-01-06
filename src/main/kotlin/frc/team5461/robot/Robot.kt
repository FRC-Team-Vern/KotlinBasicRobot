package frc.team5461.robot
import frc.team5461.subsystems.ExampleSubsystem
import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.livewindow.LiveWindow
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import java.io.File
import java.util.TimeZone









class Robot : IterativeRobot() {
    companion object {
        var exampleSubsystem = ExampleSubsystem()
        var autonomousCommand = exampleSubsystem.exampleCommand
        var oi = OI()
    }

    var chooser = SendableChooser<Command>()

    override fun robotInit(){
        TimeZone.setDefault(TimeZone.getTimeZone("America/Denver"))
        chooser = SendableChooser()
    }

    override fun teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }
    override fun teleopPeriodic() {
    }
    override fun autonomousInit() {
        autonomousCommand = chooser.getSelected()
        if (autonomousCommand == null) {
            autonomousCommand = chooser.getSelected() as Command
            if (autonomousCommand != null) {
                autonomousCommand.start()
            }
        }
        if (autonomousCommand != null) autonomousCommand.start()

    }
    override fun autonomousPeriodic() {
        Scheduler.getInstance().run();
    }
    override fun testPeriodic() {
        LiveWindow.run()
    }
    override fun testInit() {

    }

    fun findLogDirectory(root: File): File? {
        // does the root directory exist?
        if (!root.isDirectory) return null

        val logDirectory = File(root, "logs")
        return if (!logDirectory.isDirectory) null else logDirectory

    }
}