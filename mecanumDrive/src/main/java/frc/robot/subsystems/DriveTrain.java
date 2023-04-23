package frc.robot.subsystems;

import static frc.robot.Constants.motorIDs.*;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class DriveTrain extends SubsystemBase {
  private CANSparkMax frontLeft;
  private CANSparkMax backLeft;

  private CANSparkMax frontRight;
  private CANSparkMax backRight;

  private MecanumDrive mecanumDrive;

  public DriveTrain() {
    frontLeft = new CANSparkMax(frontLeftID, MotorType.kBrushless);
    backLeft = new CANSparkMax(backLeftID, MotorType.kBrushless);
    frontRight = new CANSparkMax(frontRightID, MotorType.kBrushless);
    backRight = new CANSparkMax(backRightID, MotorType.kBrushless);
    mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

    frontRight.setInverted(true);
    backRight.setInverted(true);
  }

  public void drive(Supplier<Double> leftX, Supplier<Double> leftY, Supplier<Double> leftZ, Supplier<Double> rightX, Supplier<Double> rightY, Supplier<Double> rightZ) {
    mecanumDrive.driveCartesian(-leftY.get().doubleValue(), leftX.get().doubleValue(), rightZ.get().doubleValue());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
