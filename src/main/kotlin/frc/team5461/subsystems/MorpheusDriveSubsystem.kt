package frc.team5461.subsystems

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import edu.wpi.first.wpilibj.CircularBuffer
import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.Subsystem
import edu.wpi.first.wpilibj.drive.MecanumDrive
import frc.team5461.robot.Robot

class MorpheusDriveSubsystem: Subsystem()  {
    val frontLeft = WPI_TalonSRX(3)
    val frontRight = WPI_TalonSRX(1)
    val backLeft = WPI_TalonSRX(4)
    val backRight = WPI_TalonSRX(2)
    val mecanumDrive = MecanumDrive(frontLeft,backLeft,frontRight,backRight)

    override fun initDefaultCommand() {
        defaultCommand = morpheusDriveCommand
    }
        val morpheusDriveCommand = object: Command(){
            val threshold = 0.3
            var prevAboveThresholdY = false
            var imuAngle = 0.0
            var imuDiff = 0.0
            init {
                requires(this@MorpheusDriveSubsystem)
            }

            override fun initialize() {
                mecanumDrive.stopMotor()
            }

            override fun execute() {
                val y = Robot.oi.stick.y
                val x = Robot.oi.stick.x
                val leftTrigger = Robot.oi.stick.getTriggerAxis(GenericHID.Hand.kLeft)
                val rightTrigger = Robot.oi.stick.getTriggerAxis(GenericHID.Hand.kRight)
                val strafe = rightTrigger-leftTrigger
                val thisTimeAboveThresholdY = y >= threshold
                if (thisTimeAboveThresholdY && !prevAboveThresholdY){
                    imuAngle = Robot.imu.angleZ
                    imuDiff = 0.0
                }
                else{
                    imuDiff = Robot.imu.angleZ - imuAngle
                }
                mecanumDrive.driveCartesian(y,strafe,x,imuDiff)
            }

            override fun isFinished(): Boolean {
                return false
            }

        }

}