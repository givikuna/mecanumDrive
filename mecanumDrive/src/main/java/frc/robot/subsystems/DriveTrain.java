package frc.robot.subsystems;

import static frc.robot.Constants.motorIDs.*;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveTrain extends SubsystemBase {
  private CANSparkMax frontLeft;
  private CANSparkMax backLeft;

  private CANSparkMax frontRight;
  private CANSparkMax backRight;

  private MotorControllerGroup group1;
  private MotorControllerGroup group2;

  public DriveTrain() {
    frontLeft = new CANSparkMax(frontLeftID, MotorType.kBrushless);
    backLeft = new CANSparkMax(backLeftID, MotorType.kBrushless);
    frontRight = new CANSparkMax(frontRightID, MotorType.kBrushless);
    backRight = new CANSparkMax(backRightID, MotorType.kBrushless);
    group1 = new MotorControllerGroup(frontLeft, backRight);
    group2 = new MotorControllerGroup(frontRight, backLeft);
  }

  public void drive(Supplier<Double> leftX, Supplier<Double> leftY, Supplier<Double> rightX, Supplier<Double> rightY) {
    double vertical = leftY.get().doubleValue();
    double horizontal = leftX.get().doubleValue();
    double pivot = rightX.get().doubleValue();
    double g1 = 0.0;
    double g2 = 0.0;
    if (!nearZero(pivot)) { // turns
      group1 = new MotorControllerGroup(frontLeft, backLeft);
      group2 = new MotorControllerGroup(frontRight, backRight);

      g1 = pivot;
      g2 = -pivot;
    } else if (!nearZero(horizontal)) { // goes forwards/backwards
      g1 = vertical;
      g2 = vertical;
    } else if (!nearZero(vertical)) { // goes left/right
      g1 = -horizontal;
      g2 = horizontal;
    } else { // diagonals
      if ((vertical > 0 && horizontal < 0) || (vertical < 0 && horizontal > 0)) {
        g1 = horizontal;
        g2 = vertical;
      } else {
        g1 = vertical;
        g2 = horizontal;
      }
    }
    group1.set(g1);
    group2.set(g2);
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
