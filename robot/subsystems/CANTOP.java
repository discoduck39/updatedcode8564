// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.DrivetrainConstants.*;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;


public class CANTOP extends SubsystemBase {
  CANSparkMax leftin;
  CANSparkMax rightin;
  CANSparkMax leftms; 
  CANSparkMax rightms;
  CANSparkMax leftst;
  CANSparkMax rightst;
  CANSparkMax leftH;
  CANSparkMax rightH;
  
public Runnable run;
public boolean amping;
public boolean speaker;
public boolean inmid;
public boolean kok;
public boolean IN;


 public CANTOP() {
  amping = false;
  speaker = false;
  inmid = false;
  kok = false;
  
    leftst = new CANSparkMax(7, CANSparkLowLevel.MotorType.kBrushless);
    rightst = new CANSparkMax(8, CANSparkLowLevel.MotorType.kBrushless);
    rightms  = new CANSparkMax(9, CANSparkLowLevel.MotorType.kBrushless);
    leftms = new CANSparkMax(10, CANSparkLowLevel.MotorType.kBrushless);
    leftin = new CANSparkMax(5, CANSparkLowLevel.MotorType.kBrushless);
    rightin = new CANSparkMax(6, CANSparkLowLevel.MotorType.kBrushless);
    leftH = new CANSparkMax(11, CANSparkLowLevel.MotorType.kBrushless);
    rightH = new CANSparkMax(12, CANSparkLowLevel.MotorType.kBrushless);
    
    leftst.setSmartCurrentLimit(kCurrentLimit);
    rightst.setSmartCurrentLimit(kCurrentLimit);
    rightms.setSmartCurrentLimit(kCurrentLimit);
    leftms.setSmartCurrentLimit(kCurrentLimit);
    leftin.setSmartCurrentLimit(kCurrentLimit);
    rightin.setSmartCurrentLimit(kCurrentLimit);
    leftH.setSmartCurrentLimit(kCurrentLimit);
    rightH.setSmartCurrentLimit(kCurrentLimit);

    leftH.setIdleMode(IdleMode.kBrake);
    rightH.setIdleMode(IdleMode.kBrake);
  }
   // top shooter program
  public void shoot(){
    leftst.set(-1);
    rightst.set(1);
    leftms.set(-1);
    rightms.set(1);
  }
  public void amp(){
    leftst.set(-.3);
    rightst.set(.25);
    leftms.set(-.3);
    rightms.set(.25);
  }
  public void stop(){
leftst.set(0);
rightst.set(0);
leftms.set(0);
rightms.set(0);
  }
  public Command stopShooterCommand(){
    return runOnce(() -> {
      this.stop();
      this.speaker = false;
      this.amping = false;
    });
  }
  public Command startSpeakerCommand(){
    return runOnce(() -> {
      if (!amping) {
        this.speaker = true;
        this.shoot();
      }
    });
  }
  public Command startAMPCommand(){
    return runOnce(() ->{
      if (!speaker){
        this.amping = true;
        this.amp();
      }
    });
  }

  // intake and mid 
public void midIN(){
  leftin.set(-.6);
  // leftms.set(-.5);
  rightin.set(-.6);
  // rightms.set(.5);
}
public void startsIN(){
  leftin.set(.6);
  // leftms.set(-.5);
  rightin.set(.6);
  // rightms.set(.5);
}
public void STOP(){
   leftin.set(0);
  leftms.set(0);
  rightin.set(0);
  rightms.set(0);
  leftH.set(0);
  rightH.set(0);
}

public Command startINMID(){
return runOnce(() ->{
  this.inmid = true;
  this.midIN();
});
}
public Command startIN(){
return runOnce(() ->{
  this.IN = true;
  this.startsIN();
});
}
public Command STOPINMID(){
  return runOnce(() ->{
    this.STOP();
    this.inmid = false;
  });
}
public Command SToPIN(){
  return runOnce(() ->{
    this.STOP();
    this.IN = false;
  });
}
// hook do thing thing 

public void up(){
  leftH.set(-.5);
  rightH.set(-.5);
}
public void down(){
  leftH.set(.5);
  rightH.set(.5);
}
// public void StOp(){
//   hang.set(0);
// }

public Command STARTUp(){
return runOnce(() ->{
  this.kok = true;
  this.up();
});
}
public Command Down(){
  return runOnce(() ->{
    this.kok = true;
    this.down();
  });
}
public Command StOp(){
  return runOnce(() ->{
    this.STOP(); 
    this.kok = false;
  });
}







  /**
   * This method is an example of the 'subsystem factory' style of command creation. A method inside
   * the subsytem is created to return an instance of a command. This works for commands that
   * operate on only that subsystem, a similar approach can be done in RobotContainer for commands
   * that need to span subsystems. The Subsystem class has helper methods, such as the startEnd
   * method used here, to create these commands.
   */

  

  // An accessor method to set the speed (technically the output percentage) of the launch wheel
  // public void setLaunchWheel(double speed) {
  //   right_top_shooter.set(speed);
  // }

  // An accessor method to set the speed (technically the output percentage) of the feed wheel
 

//   public Command start(){
// return run (() -> {
//   this.left_top_shooter.set(-1);
//   this.right_top_shooter.set(1);
// });
//   }
//     public Command stop(){
// return run (() -> {
//   this.left_top_shooter.set(0);
//   this.right_top_shooter.set(0);
// });
//   }
//   public void amp(){
// this.left_top_shooter.set(-.13);
// this.right_top_shooter.set(.13);

  }

//   public Command startAmpCommand() {
//    public Command start(){
// return run (() -> {
//   this.left_top_shooter.set(-1);
//   this.right_top_shooter.set(1);
// });
//   }
//     public Command stop(){
// return run (() -> {
//   this.left_top_shooter.set(0);
//   this.right_top_shooter.set(0);
// });
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'startAmpCommand'");
//   }

  // A helper method to stop both wheels. You could skip having a method like this and call the
  // individual accessors with speed = 0 instead
 
  

