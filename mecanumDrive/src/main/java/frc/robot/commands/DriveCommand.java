package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.*;

/** An example command that uses an example subsystem. */
public class DriveCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final DriveTrain driveTrain;
  private Supplier<Double> leftX;
  private Supplier<Double> leftY;
  private Supplier<Double> rightX;
  private Supplier<Double> rightY;

  /**
   * Creates a new ExampleCommand.
   *
   * @param m_driveTrain The subsystem used by this command
   * @param aSpeed       The speed of the motors at group A
   * @param bSpeed       The speed of the motors at group B
   */
  public DriveCommand(DriveTrain m_driveTrain, Supplier<Double> leftX, Supplier<Double> leftY, Supplier<Double> rightX, Supplier<Double> rightY) {
    if (leftX == null)
      leftX = () -> 0.0;
    if (leftY == null)
      leftY = () -> 0.0;
    if (rightX == null)
      rightX = () -> 0.0;
    if (rightY == null)
      rightY = () -> 0.0;

    this.driveTrain = m_driveTrain;
    addRequirements(driveTrain);
    this.leftX = leftX;
    this.leftY = leftY;
    this.rightX = rightX;
    this.rightY = rightY;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("DriveCommand initiliazed");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.drive(leftX, leftY, rightX, rightY);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    final Supplier<Double> ZERO = () -> 0.0;
    driveTrain.drive(ZERO, ZERO, ZERO, ZERO);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
