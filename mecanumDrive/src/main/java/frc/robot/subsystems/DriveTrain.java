package frc.robot.subsystems;

import static frc.robot.Constants.motorIDs.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class DriveTrain extends SubsystemBase {
  public final WPI_TalonFX frontRight;
  public final WPI_TalonFX frontLeft;
  public final WPI_TalonFX backRight;
  public final WPI_TalonFX backLeft;

  public DriveTrain() {
    //
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
