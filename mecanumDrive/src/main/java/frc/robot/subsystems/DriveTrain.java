package frc.robot.subsystems;

import static frc.robot.Constants.motorIDs.*;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveTrain extends SubsystemBase {
  private CANSparkMax frontLeft = new CANSparkMax(frontLeftID, MotorType.kBrushless);
  private CANSparkMax backLeft = new CANSparkMax(backLeftID, MotorType.kBrushless);

  private CANSparkMax frontRight = new CANSparkMax(frontRightID, MotorType.kBrushless);
  private CANSparkMax backRight = new CANSparkMax(backRightID, MotorType.kBrushless);

  private MotorControllerGroup group1 = new MotorControllerGroup(frontLeft, backLeft);
  private MotorControllerGroup group2 = new MotorControllerGroup(frontRight, backRight);

  public DriveTrain() {
    //
  }

  public void verticalDrive(Supplier<Double> leftSpeed, Supplier<Double> rightSpeed) {
    group1.set(leftSpeed.get().doubleValue());
    group2.set(rightSpeed.get().doubleValue());
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
