package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.*;

/** An example command that uses an example subsystem. */
public class VerticalDrive extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final DriveTrain driveTrain;
  private final Supplier<Double> leftSpeed;
  private final Supplier<Double> rightSpeed;

  /**
   * Creates a new ExampleCommand.
   *
   * @param m_driveTrain The subsystem used by this command
   * @param aSpeed       The speed of the motors at group A
   * @param bSpeed       The speed of the motors at group B
   */
  public VerticalDrive(DriveTrain m_driveTrain, Supplier<Double> leftSpeed, Supplier<Double> rightSpeed) {
    this.driveTrain = m_driveTrain;
    addRequirements(driveTrain);
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("VerticalDrive initiliazed");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.verticalDrive(leftSpeed, rightSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
