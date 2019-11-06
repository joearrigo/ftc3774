## Class Overview

* MotorController:    This is a universal class where components are "gotten" from the Phone
                      configuration.
                      Rather than defining them in each OpMode, this keeps them consistent in each
                      one, regardless of updates.
                      MotorController also contains the different methods for controlling the
                      mecanum wheels on the bot, however gamepadToDrive() is used most often
* VuforiaHandler:     This contains all of the Vuforia code. Based on examples online and past
                      code I've written for 4890 (specifically for Velocity Vortex: 2016-2017)
* TeleOp3774:         This is 3774's TeleOp program. Nothing spectacular here in terms of control.
* Auto3774:           This is 3774's Autonomous program. It utilizes Vuforia.