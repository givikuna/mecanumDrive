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

  public void drive(Supplier<Double> leftX, Supplier<Double> leftY, Supplier<Double> rightX, Supplier<Double> rightY) {
    double vertical = leftY.get().doubleValue();
    double horizontal = leftX.get().doubleValue();
    double pivot = rightX.get().doubleValue();
    if (!nearZero(pivot)) { // turn
      group1.set(pivot);
      group2.set(-pivot);
    }
    if (!nearZero(vertical) && !nearZero(horizontal)) { // diagonal
      if (vertical > 0 && horizontal < 0) { // left diagonal forwards
        frontRight.set(vertical);
        backLeft.set(vertical);
      }
      if (vertical < 0 && horizontal < 0) { // left diagonal backwards
        frontLeft.set(vertical);
        backRight.set(vertical);
      }
      if (vertical > 0 && horizontal > 0) { // right diagonal forwards
        frontLeft.set(vertical);
        frontRight.set(vertical);
      }
      if (vertical < 0 && horizontal > 0) { // right diagonal backwards
        frontRight.set(vertical);
        backLeft.set(vertical);
      }
    }
    if (nearZero(vertical)) { // go left/right
      frontLeft.set(-horizontal);
      backRight.set(-horizontal);
      frontRight.set(horizontal);
      backLeft.set(horizontal);
    }
    if (nearZero(horizontal)) { // go forwards/backwards
      group1.set(vertical);
      group2.set(vertical);
    }
  }

  public boolean nearZero(double d) {
    return d > 0.05 || d < 0.05;
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
