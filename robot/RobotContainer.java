// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.lang.reflect.Method;
import java.util.Arrays;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CANTOP;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.CANDrivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import frc.robot.subsystems.CANDrivetrain;
// import frc.robot.subsystems.CANLauncher;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // private final CANLauncher m_intake = new CANLauncher();
// private final CANMID m_mid = new CANMID();
private final CANTOP m_top = new CANTOP();


// public class Robot extends TimedRobot {
//   @Override
//   public void robotInit() {
//     CameraServer.startAutomaticCapture();
//   }
// }

  // The robot's subsystems are defined here.
  private final CANDrivetrain m_drivetrain = new CANDrivetrain();

  
  
  // private final CANDrivetrain m_drivetrain = new CANDrivetrain();
  
  // private final CANLauncher m_launcher = new CANLauncher();

  /*The gamepad provided in the KOP shows up like an XBox controller if the mode switch is set to X mode using the
   * switch on the top.*/
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    populateAutoCommands();
  
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be accessed via the
   * named factory methods in the Command* classes in edu.wpi.first.wpilibj2.command.button (shown
   * below) or via the Trigger constructor for arbitary conditions
   */
  private void configureBindings() {

    
    // Set the default command for the drivetrain to drive using the joysticks
    m_drivetrain.setDefaultCommand(
        new RunCommand(
            () ->
                m_drivetrain.arcadeDrive(
                    m_driverController.getLeftY(), m_driverController.getLeftX()),
            m_drivetrain));

   m_driverController.a().onTrue(
    (m_top.startINMID()));
  m_driverController.a().whileFalse(m_top.STOPINMID());
   m_driverController.y().onTrue(
    (m_top.startIN()));
  m_driverController.y().whileFalse(m_top.SToPIN());
  
    
    

   m_operatorController.rightBumper().onTrue(
    (m_top.startSpeakerCommand()));

    m_operatorController.leftBumper().onTrue(
      (m_top.startAMPCommand()));

    m_operatorController.rightBumper().or(m_operatorController.leftBumper()).whileFalse(m_top.stopShooterCommand());

    m_operatorController.leftTrigger().onTrue(
      (m_top.STARTUp()));
    m_operatorController.rightTrigger().onTrue(
      (m_top.Down()));
      m_operatorController.leftTrigger().or(m_operatorController.rightTrigger()).whileFalse(m_top.StOp());
  
    }

  //   m_driverController.rightTrigger().onTrue(
  //  (m_topamp.start()));
  //   m_driverController.rightTrigger().onFalse(
  //  (m_topamp.stop()));



  //   m_operatorController.leftBumper().onTrue(
  //   m_mid.runOnce(m_mid.run));
    
  //   m_operatorController.rightBumper().onTrue(
  //   m_top.runOnce(m_top.run));
    
   

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   * 
  //  */
  private void populateAutoCommands() {
    Method[] methods = Autos.class.getDeclaredMethods();
    Arrays.stream(methods)
          .filter(method -> Command.class.isAssignableFrom(method.getReturnType()) && method.getParameterCount() == 1)
          .forEach(method -> {
              try {
                  Command command = (Command) method.invoke(null, m_drivetrain);
                  autoChooser.addOption(method.getName(), command);
              } catch (Exception e) {
                  e.printStackTrace();
              }
          });

 SmartDashboard.putData("Auto Mode", autoChooser);
        }

 public Command getAutonomousCommand() {
  return m_drivetrain.run(() -> {});
}

public String getSelectedAutoName() {
return autoChooser.getSelected().getName();
}

 }
   
    

