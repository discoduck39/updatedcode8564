// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.CANDrivetrain;
import frc.robot.subsystems.CANTOP;


public final class Autos {
  public static Command doublenoteAuto(CANDrivetrain drivetrain, CANTOP cantop) {

    return cantop.startSpeakerCommand()
    .andThen(new WaitCommand(2))
    .andThen(cantop.stopShooterCommand().withTimeout(1.5))
    .andThen(new RunCommand(() -> drivetrain.arcadeDrive(-.3, 0), drivetrain).withTimeout(1.5))
    .andThen(new RunCommand(() -> drivetrain.arcadeDrive(0, 0), drivetrain).withTimeout(1.5))
    .andThen(new WaitCommand(0))
    .andThen(cantop.startINMID().withTimeout(3))
    .andThen(new WaitCommand(3))
    .andThen(cantop.SToPIN().withTimeout(2))
    .andThen(new RunCommand(() -> drivetrain.arcadeDrive(.3, 0), drivetrain).withTimeout(1.5))
    .andThen(new RunCommand(() -> drivetrain.arcadeDrive(0, 0), drivetrain).withTimeout(1.5))
    .andThen(new WaitCommand(0))
    .andThen(cantop.startSpeakerCommand().withTimeout(2))
    .andThen(new WaitCommand(3))
    .andThen(cantop.stopShooterCommand().withTimeout(1.5))
    .andThen(new WaitCommand(0))
    .andThen(new RunCommand(() -> drivetrain.arcadeDrive(0, 0), drivetrain).withTimeout(1.5));
    
  }
  
  public static Command ampAuto(CANDrivetrain drivetrain, CANTOP cantop) {

    return new RunCommand(() -> drivetrain.arcadeDrive(-.3, 0), drivetrain).withTimeout(1.5)
    .andThen(new RunCommand(() -> drivetrain.arcadeDrive(0, 0), drivetrain).withTimeout(1.5))
    .andThen(cantop.startAMPCommand().withTimeout(1.5))
    .andThen(cantop.stopShooterCommand().withTimeout(1.5));

  }
public static Command blueampandoutAuto(CANDrivetrain drivetrain, CANTOP cantop){
  return new RunCommand(() -> drivetrain.arcadeDrive(-.3, 0), drivetrain).withTimeout(1.5)
    .andThen(new RunCommand(() -> drivetrain.arcadeDrive(0, 0), drivetrain).withTimeout(1.5))
    .andThen(cantop.startAMPCommand().withTimeout(1.5))
    .andThen(cantop.stopShooterCommand().withTimeout(1.5))
    .andThen(new RunCommand(() -> drivetrain.arcadeDrive(3, 0), drivetrain).withTimeout(1.5))
    .andThen(new RunCommand(() -> drivetrain.arcadeDrive(0, 3), drivetrain).withTimeout(1.5))
    .andThen(new RunCommand(() -> drivetrain.arcadeDrive(3, 0), drivetrain).withTimeout(5))
    .andThen(new RunCommand(() -> drivetrain.arcadeDrive(0, 0), drivetrain).withTimeout(1));

}
public static Command REDampandoutAuto(CANDrivetrain drivetrain, CANTOP cantop){
  return new RunCommand(() -> drivetrain.arcadeDrive(-.3, 0), drivetrain).withTimeout(1.5)
    .andThen(new RunCommand(() -> drivetrain.arcadeDrive(0, 0), drivetrain).withTimeout(1.5))
    .andThen(cantop.startAMPCommand().withTimeout(1.5))
    .andThen(cantop.stopShooterCommand().withTimeout(1.5))
    .andThen(new RunCommand(() -> drivetrain.arcadeDrive(3, 0), drivetrain).withTimeout(1.5))
    .andThen(new RunCommand(() -> drivetrain.arcadeDrive(0, -3), drivetrain).withTimeout(1.5))
    .andThen(new RunCommand(() -> drivetrain.arcadeDrive(3, 0), drivetrain).withTimeout(5))
    .andThen(new RunCommand(() -> drivetrain.arcadeDrive(0, 0), drivetrain).withTimeout(1));

}








  

  

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
