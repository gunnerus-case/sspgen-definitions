#!sspgen

import kotlin.math.PI

ssp("gunnerus-palfinger") {

    ssd("KPN Twinship Gunnerus case") {

        system("gunnerus-palfinger") {

            elements {

                component("vesselModel", "resources/VesselFmu.fmu") {
                    connectors {
                        real("additionalBodyForce[0].force.heave", input)
                        real("additionalBodyForce[0].force.surge", input)
                        real("additionalBodyForce[0].force.sway", input)
                        real("additionalBodyForce[0].pointOfAttackRel2APAndBL.xpos", input)
                        real("additionalBodyForce[0].pointOfAttackRel2APAndBL.ypos", input)
                        real("additionalBodyForce[0].pointOfAttackRel2APAndBL.zpos", input)
                        
                        real("additionalBodyForce[1].force.heave", input)
                        real("additionalBodyForce[1].force.surge", input)
                        real("additionalBodyForce[1].force.sway", input)
                        real("additionalBodyForce[1].pointOfAttackRel2APAndBL.xpos", input)
                        real("additionalBodyForce[1].pointOfAttackRel2APAndBL.ypos", input)
                        real("additionalBodyForce[1].pointOfAttackRel2APAndBL.zpos", input)
                        
                        real("additionalBodyForce[2].force.heave", input)
                        real("additionalBodyForce[2].force.surge", input)
                        real("additionalBodyForce[2].force.sway", input)
                        real("additionalBodyForce[2].pointOfAttackRel2APAndBL.xpos", input)
                        real("additionalBodyForce[2].pointOfAttackRel2APAndBL.ypos", input)
                        real("additionalBodyForce[2].pointOfAttackRel2APAndBL.zpos", input)
                        
                        real("additionalBodyForce[3].force.heave", input)
                        real("additionalBodyForce[3].force.surge", input)
                        real("additionalBodyForce[3].force.sway", input)
                        real("additionalBodyForce[3].pointOfAttackRel2APAndBL.xpos", input)
                        real("additionalBodyForce[3].pointOfAttackRel2APAndBL.ypos", input)
                        real("additionalBodyForce[3].pointOfAttackRel2APAndBL.zpos", input)
                        
                        real("additionalNedForce[0].force.north", input)
                        real("additionalNedForce[0].force.east", input)
                        real("additionalNedForce[0].force.down", input)
                        real("additionalNedForce[0].pointOfAttackRel2APAndBL.xpos", input)
                        real("additionalNedForce[0].pointOfAttackRel2APAndBL.ypos", input)
                        real("additionalNedForce[0].pointOfAttackRel2APAndBL.zpos", input)
                        
                        real("additionalNedForce[1].force.north", input)
                        real("additionalNedForce[1].force.east", input)
                        real("additionalNedForce[1].force.down", input)
                        real("additionalNedForce[1].pointOfAttackRel2APAndBL.xpos", input)
                        real("additionalNedForce[1].pointOfAttackRel2APAndBL.ypos", input)
                        real("additionalNedForce[1].pointOfAttackRel2APAndBL.zpos", input)
                  
                        real("cg_x_rel_ap", output)
                        real("cg_y_rel_cl", output)
                        real("cg_z_rel_bl", output)
                        
                        real("cgShipMotion.ned.north", output)
                        real("cgShipMotion.ned.east", output)
                        real("cgShipMotion.ned.down", output)
                        
                        real("cgShipMotion.angularDisplacement.roll", output)
                        real("cgShipMotion.angularDisplacement.pitch", output)
                        real("cgShipMotion.angularDisplacement.yaw", output)
                        
                        real("cgShipMotion.linearVelocity.surge", output)
                        real("cgShipMotion.linearVelocity.sway", output)
                        real("cgShipMotion.angularVelocity.yaw", output)
                        
                        real("cgShipMotion.linearAcceleration.surge", output)
                        real("cgShipMotion.linearAcceleration.sway", output)
                        real("cgShipMotion.angularAcceleration.yaw", output)
                        
                        real("input_global_wind_dir", input)
                        real("input_global_wind_vel", input)
                        
                        real("global_cur_dir", input)
                        real("global_cur_vel", input)
                    }
                    parameterBindings {
                        parameterSet("initialValues") {
                            string("vesselZipFile", "%fmu%/resources/ShipModel-gunnerus-elongated.zip")
                            integer("wave_spectrum", 2)
                            
                            real("wave_height", 0.3)
                            real("peak_period", 10)
                            real("wave_direction", 135)
                            real("global_cur_vel", 0.5)
                            real("global_cur_dir", 135)
                            real("input_global_wind_dir", 135)
                            real("input_global_wind_vel", 1)
                        }
                    }
                    annotations {
                        annotation("com.opensimulationplatform") {
                        """
                            <osp:StepSizeHint value="0.05"/>
                        """
                        }
                    }
                }
                
                component("crane", "resources/PalfingerCrane.fmu") {
                    connectors {
                        real("shipMotionNorth", input)
                        real("shipMotionEast", input)
                        real("shipMotionDown", input)
                        real("shipMotionRoll", input)
                        real("shipMotionPitch", input)
                        real("shipMotionYaw", input)
                        
                        real("craneSetPoint1[1]", input)
                        real("craneSetPoint1[2]", input)
                        real("craneSetPoint1[3]", input)
                        real("craneSetPoint1[4]", input)
                        real("winchSetPoint", input)
                        
                        real("input_x_rel_ap", input)
                        real("input_y_rel_cl", input)
                        real("input_z_rel_bl", input)
                        real("input_cg_x_rel_ap", input)
                        real("input_cg_y_rel_cl", input)
                        real("input_cg_z_rel_bl", input)
                        real("input_theta1_init", input)
                        real("input_cl1_init", input)
                        real("input_cl2_init", input)
                        real("input_cl3_init", input)
                        
                        real("tipPositionShipFrame[1]", output)
                        real("tipPositionShipFrame[2]", output)
                        real("tipPositionShipFrame[3]", output)
                        real("loadForce[1]", output)
                        real("loadForce[2]", output)
                        real("loadForce[3]", output)
                        real("cranePosition[1]", output)
                        real("cranePosition[2]", output)
                        real("cranePosition[3]", output)
                        real("craneForce[1]", output)
                        real("craneForce[2]", output)
                        real("craneForce[3]", output)
                        
                        //real("output_x_rel_ap", output)
                        //real("output_y_rel_cl", output)
                        //real("output_z_rel_bl", output)
                    }
                    parameterBindings {
                        parameterSet("initialValues") {
                            real("input_x_rel_ap", 5.5166121)
                            real("input_y_rel_cl", -3)
                            real("input_z_rel_bl", 6.6240001)
                            real("input_theta1_init", -1.57)
                            real("input_cl1_init", 1.8)
                            real("input_cl2_init", 1.2)
                            real("input_cl3_init", 1.5)
                        }
                    }
                }

                component(
                    "azimuth0",
                    "fmu-proxy://localhost:9090?url=http://folk.ntnu.no/laht/files/cse/PMAzimuth.fmu"
                ) {
                    connectors {
                        real("input_act_revs", input)
                        real("input_act_angle", input)
                        real("input_cg_x_rel_ap", input)
                        real("input_cg_y_rel_cl", input)
                        real("input_cg_z_rel_bl", input)
                        real("input_cg_surge_vel", input)
                        real("input_cg_sway_vel", input)
                        real("input_yaw_vel", input)

                        real("output_torque", output)
                        real("output_force_heave", output)
                        real("output_force_surge", output)
                        real("output_force_sway", output)
                        real("output_x_rel_ap", output)
                        real("output_y_rel_cl", output)
                        real("output_z_rel_bl", output)
                    }
                    parameterBindings {
                        parameterSet("initialValues") {
                            real("input_x_rel_ap", 1.5)
                            real("input_y_rel_cl", -2)
                            real("input_z_rel_bl", 0)
                            real("input_prop_diam", 1.9)
                            real("input_distancetohull", 1.5)
                            real("input_bilgeradius", 3)
                            real("input_rho", 1025)
                            real("input_lpp", 33.9)
                        }
                    }
                    annotations {
                        annotation("com.opensimulationplatform") {
                        """
                            <osp:StepSizeHint value="0.05"/>
                        """
                        }
                    }
                }

                component(
                    "azimuth1",
                    "fmu-proxy://localhost:9091?url=http://folk.ntnu.no/laht/files/cse/PMAzimuth.fmu"
                ) {
                    connectors {
                        copyFrom("azimuth0")
                    }
                    parameterBindings {
                        copyFrom("azimuth0", "initialValues") {
                            real("input_y_rel_cl", 2)
                        }
                    }
                    annotations {
                        annotation("com.opensimulationplatform") {
                        """
                            <osp:StepSizeHint value="0.05"/>
                        """
                        }
                    }
                }
                
                component("tunnelThruster", "resources/TunnelThruster.fmu") {
                    connectors {
                        real("input_act_revs", input)
                        real("input_cg_x_rel_ap", input)
                        real("input_cg_y_rel_cl", input)
                        real("input_cg_z_rel_bl", input)
                        real("input_cg_surge_vel", input)
                        real("input_cg_sway_vel", input)
                        real("input_yaw_vel", input)
                        
                        real("output_force_heave", output)
                        real("output_force_surge", output)
                        real("output_force_sway", output)
                        real("output_x_rel_ap", output)
                        real("output_y_rel_cl", output)
                        real("output_z_rel_bl", output)
                        real("output_torque", output)
                    }
                    parameterBindings {
                        parameterSet("initialValues") {
                            real("input_x_rel_ap", 30)
                            real("input_y_rel_cl", 0)
                            real("input_z_rel_bl", 0)
                            real("input_prop_diam", 1)
                            real("input_max_pitch", 1)
                            real("input_rho", 1025)
                        }
                    }
                    annotations {
                        annotation("com.opensimulationplatform") {
                        """
                            <osp:StepSizeHint value="0.05"/>
                        """
                        }
                    }
                }
                
                component("azimuth0_rpmActuator", "fmu-proxy://localhost:9090?file=resources/ThrusterDrive.fmu") {
                    connectors {
                        real("d_in.e", input)
                        real("q_in.e", input)
                        real("ThrustCom", input)
                        real("Shaft.e", input)
                        
                        real("d_in.f", output)
                        real("q_in.f", output)
                        real("Shaft.f", output)
                    }
                    annotations {
                        annotation("com.opensimulationplatform") {
                        """
                            <osp:StepSizeHint value="0.05"/>
                        """
                        }
                    }
                }
                
                component("azimuth1_rpmActuator", "fmu-proxy://localhost:9091?file=resources/ThrusterDrive.fmu") {
                    connectors {
                        copyFrom("azimuth0_rpmActuator")
                    }
                    annotations {
                        annotation("com.opensimulationplatform") {
                        """
                            <osp:StepSizeHint value="0.05"/>
                        """
                        }
                    }
                }
                          
                component("tunnelThruster_rpmActuator", "fmu-proxy://localhost:9092?file=resources/ThrusterDrive.fmu") {
                    connectors {
                        copyFrom("azimuth0_rpmActuator")
                    }
                    annotations {
                        annotation("com.opensimulationplatform") {
                        """
                            <osp:StepSizeHint value="0.05"/>
                        """
                        }
                    }
                }
                
                component("powerPlant", "resources/PowerPlant.fmu") {
                    connectors {
                        real("p.f[1]", input)
                        real("p.f[2]", input)
                        real("p1.f[1]", input)
                        real("p1.f[2]", input)
                        real("p2.f[1]", input)
                        real("p2.f[2]", input)
                       
                        real("p.e[1]", output)
                        real("p.e[2]", output)
                        real("p1.e[1]", output)
                        real("p1.e[2]", output)
                        real("p2.e[1]", output)
                        real("p2.e[2]", output)
                    }
                    annotations {
                        annotation("com.opensimulationplatform") {
                        """
                            <osp:StepSizeHint value="0.05"/>
                        """
                        }
                    }
                }
                
                component("dpController", "resources/DPController.fmu") {
                    connectors {
                        real("ref.north", input)
                        real("ref.east", input)
                        real("ref.psi", input)
                        
                        real("sensor.northPosition", input)
                        real("sensor.eastPosition", input)
                        real("sensor.headingAngle", input)
                        real("sensor.surgeVelocity", input)
                        real("sensor.swayVelocity", input)
                        real("sensor.yawVelocity", input)
                        real("sensor.surgeAcceleration", input)
                        real("sensor.swayAcceleration", input)
                        real("sensor.yawAcceleration", input)
                        
                        real("cmd.force.x", output)
                        real("cmd.force.y", output)
                        real("cmd.force.psi", output)
                    }
                    parameterBindings {
                        parameterSet("initialValues") {
                            real("autopilot.surge.kp", 1000)
                            real("autopilot.surge.ki", 10)
                            real("autopilot.surge.kd", 30000)
                            real("autopilot.sway.kp", 500)
                            real("autopilot.sway.ki", 10)
                            real("autopilot.sway.kd", 20000)
                            real("autopilot.heading.kp", 1000)
                            real("autopilot.heading.ki", 10)
                            real("autopilot.heading.kd", 50000)
                            boolean("autotune", false)
                            boolean("enable", true)
                            boolean("shouldLog", false)
                        }
                    }
                    annotations {
                        annotation("com.opensimulationplatform") {
                        """
                            <osp:StepSizeHint value="0.05"/>
                        """
                        }
                    }
                }
                
                component("allocator", "resources/ControlAllocatorGIFixedAngles.fmu") {
                    connectors {
                        real("desiredGenForce.north", input)
                        real("desiredGenForce.east", input)
                        real("desiredGenForce.yaw", input)
                        real("vessel.cg_x_rel_ap", input)
                        real("vessel.cg_y_rel_cl", input)

                        real("alloc.force.north", output)
                        real("alloc.force.east", output)
                        real("alloc.torque.yaw", output)

                        real("azimuth0.angle", output)
                        real("azimuth1.angle", output)
                        real("azimuth0.force", output)
                        real("azimuth1.force", output)
                        real("tunnel.force", output)
                    }
                    parameterBindings {
                        parameterSet("initialValues") {
                            boolean("shouldLog", false)
                            boolean("enable", false)
                        }
                    }
                    annotations {
                        annotation("com.opensimulationplatform") {
                            """
                            <osp:StepSizeHint value="0.05"/>
                        """
                        }
                    }
                }
                
                component("waypointProvider", "resources/WaypointProvider3DOF.fmu") {
                    connectors {
                        real("targetWP.north", output)
                        real("targetWP.east", output)
                        real("targetWP.yaw", output)
                    }
                    parameterBindings {
                        parameterSet("initialValues") {
                            integer("mode", 0)
                            boolean("shouldLog", false)
                            real("input.targetWP.north", 0)
                            real("input.targetWP.east", 0)
                            real("input.targetWP.yaw", 0)
                        }
                    }
                    annotations {
                        annotation("com.opensimulationplatform") {
                        """
                            <osp:StepSizeHint value="0.05"/>
                        """
                        }
                    }
                }
                
                component("observer", "resources/NonlinearPassiveObserver.fmu") {
                    connectors {
                        real("sensor.northPosition", input)
                        real("sensor.eastPosition", input)
                        real("sensor.headingAngle", input)
                        real("force.surge", input)
                        real("force.sway", input)
                        real("torque.yaw", input)

                        real("est.north", output)
                        real("est.east", output)
                        real("est.yaw", output) 
                    }
                    parameterBindings {
                        parameterSet("initialValues") {
                            boolean("shouldLog", false)
                        }
                    }
                    annotations {
                        annotation("com.opensimulationplatform") {
                        """
                            <osp:StepSizeHint value="0.05"/>
                        """
                        }
                    }
                }

            }

            connections(inputsFirst=true) {
                "observer.sensor.northPosition" to "vesselModel.cgShipMotion.ned.north"
                "observer.sensor.eastPosition" to "vesselModel.cgShipMotion.ned.east"
                "observer.sensor.headingAngle" to "vesselModel.cgShipMotion.angularDisplacement.yaw"
                "observer.force.surge" to "allocator.alloc.force.north"
                "observer.force.sway" to "allocator.alloc.force.east"
                "observer.torque.yaw" to "allocator.alloc.torque.yaw"
                
                "dpController.ref.north" to "waypointProvider.targetWP.north"
                "dpController.ref.east" to "waypointProvider.targetWP.east"
                "dpController.ref.psi" to "waypointProvider.targetWP.yaw"

                "allocator.desiredGenForce.north" to "dpController.cmd.force.x"
                "allocator.desiredGenForce.east" to "dpController.cmd.force.y"
                "allocator.desiredGenForce.yaw" to "dpController.cmd.force.psi"
                "allocator.vessel.cg_x_rel_ap" to "vesselModel.cg_x_rel_ap"
                "allocator.vessel.cg_y_rel_cl" to "vesselModel.cg_y_rel_cl"
                
                "dpController.sensor.northPosition" to "observer.est.north"
                "dpController.sensor.eastPosition" to "observer.est.east"
                "dpController.sensor.headingAngle" to "observer.est.yaw"
                "dpController.sensor.surgeVelocity" to "vesselModel.cgShipMotion.linearVelocity.surge"
                "dpController.sensor.swayVelocity" to "vesselModel.cgShipMotion.linearVelocity.sway"
                "dpController.sensor.yawVelocity" to "vesselModel.cgShipMotion.angularVelocity.yaw"
                "dpController.sensor.surgeAcceleration" to "vesselModel.cgShipMotion.linearAcceleration.surge"
                "dpController.sensor.swayAcceleration" to "vesselModel.cgShipMotion.linearAcceleration.sway"
                "dpController.sensor.yawAcceleration" to "vesselModel.cgShipMotion.angularAcceleration.yaw"
                
                "powerPlant.p1.f[1]" to "azimuth0_rpmActuator.d_in.f"
                "powerPlant.p1.f[2]" to "azimuth0_rpmActuator.q_in.f"
                "powerPlant.p2.f[1]" to "azimuth1_rpmActuator.d_in.f"
                "powerPlant.p2.f[2]" to "azimuth1_rpmActuator.q_in.f"
                "powerPlant.p.f[1]" to "tunnelThruster_rpmActuator.d_in.f"
                "powerPlant.p.f[2]" to "tunnelThruster_rpmActuator.q_in.f"
                
                "azimuth0_rpmActuator.d_in.e" to "powerPlant.p1.e[1]"
                "azimuth0_rpmActuator.q_in.e" to "powerPlant.p1.e[2]"
                "azimuth0_rpmActuator.ThrustCom" to "allocator.azimuth0.force"
                "azimuth0_rpmActuator.Shaft.e" to "azimuth0.output_torque"
                
                "azimuth1_rpmActuator.d_in.e" to "powerPlant.p2.e[1]"
                "azimuth1_rpmActuator.q_in.e" to "powerPlant.p2.e[2]"
                "azimuth1_rpmActuator.ThrustCom" to "allocator.azimuth1.force"
                "azimuth1_rpmActuator.Shaft.e" to "azimuth1.output_torque"
                
                "tunnelThruster_rpmActuator.d_in.e" to "powerPlant.p.e[1]"
                "tunnelThruster_rpmActuator.q_in.e" to "powerPlant.p.e[2]"
                "tunnelThruster_rpmActuator.ThrustCom" to "allocator.tunnel.force"
                "tunnelThruster_rpmActuator.Shaft.e" to "tunnelThruster.output_torque"
                
                ("azimuth0.input_act_revs" to "azimuth0_rpmActuator.Shaft.f").linearTransformation(factor=60.0/(2*PI))
                "azimuth0.input_act_angle" to "allocator.azimuth0.angle"
                "azimuth0.input_cg_x_rel_ap" to "vesselModel.cg_x_rel_ap"
                "azimuth0.input_cg_y_rel_cl" to "vesselModel.cg_y_rel_cl"
                "azimuth0.input_cg_z_rel_bl" to "vesselModel.cg_z_rel_bl"
                "azimuth0.input_cg_surge_vel" to "vesselModel.cgShipMotion.linearVelocity.surge"
                "azimuth0.input_cg_sway_vel" to "vesselModel.cgShipMotion.linearVelocity.sway"
                "azimuth0.input_yaw_vel" to "vesselModel.cgShipMotion.angularVelocity.yaw"
                
                ("azimuth1.input_act_revs" to "azimuth1_rpmActuator.Shaft.f").linearTransformation(factor=60.0/(2*PI))
                "azimuth1.input_act_angle" to "allocator.azimuth1.angle"
                "azimuth1.input_cg_x_rel_ap" to "vesselModel.cg_x_rel_ap"
                "azimuth1.input_cg_y_rel_cl" to "vesselModel.cg_y_rel_cl"
                "azimuth1.input_cg_z_rel_bl" to "vesselModel.cg_z_rel_bl"
                "azimuth1.input_cg_surge_vel" to "vesselModel.cgShipMotion.linearVelocity.surge"
                "azimuth1.input_cg_sway_vel" to "vesselModel.cgShipMotion.linearVelocity.sway"
                "azimuth1.input_yaw_vel" to "vesselModel.cgShipMotion.angularVelocity.yaw"
                
                ("tunnelThruster.input_act_revs" to "tunnelThruster_rpmActuator.Shaft.f").linearTransformation(factor=60.0/(2*PI))
                "tunnelThruster.input_cg_x_rel_ap" to "vesselModel.cg_x_rel_ap"
                "tunnelThruster.input_cg_y_rel_cl" to "vesselModel.cg_y_rel_cl"
                "tunnelThruster.input_cg_z_rel_bl" to "vesselModel.cg_z_rel_bl"
                "tunnelThruster.input_cg_surge_vel" to "vesselModel.cgShipMotion.linearVelocity.surge"
                "tunnelThruster.input_cg_sway_vel" to "vesselModel.cgShipMotion.linearVelocity.sway"
                "tunnelThruster.input_yaw_vel" to "vesselModel.cgShipMotion.angularVelocity.yaw"
                
                "crane.input_cg_x_rel_ap" to "vesselModel.cg_x_rel_ap"
                "crane.input_cg_y_rel_cl" to "vesselModel.cg_y_rel_cl"
                "crane.input_cg_z_rel_bl" to "vesselModel.cg_z_rel_bl"
                "crane.shipMotionNorth" to "vesselModel.cgShipMotion.ned.north"
                "crane.shipMotionEast" to "vesselModel.cgShipMotion.ned.east"
                "crane.shipMotionDown" to "vesselModel.cgShipMotion.ned.down"
                "crane.shipMotionRoll" to "vesselModel.cgShipMotion.angularDisplacement.roll"
                "crane.shipMotionPitch" to "vesselModel.cgShipMotion.angularDisplacement.pitch"
                "crane.shipMotionYaw" to "vesselModel.cgShipMotion.angularDisplacement.yaw"
               
                "vesselModel.additionalBodyForce[0].force.heave" to "azimuth0.output_force_heave"
                "vesselModel.additionalBodyForce[0].force.surge" to "azimuth0.output_force_surge"
                "vesselModel.additionalBodyForce[0].force.sway" to "azimuth0.output_force_sway"
                "vesselModel.additionalBodyForce[0].pointOfAttackRel2APAndBL.xpos" to "azimuth0.output_x_rel_ap"
                "vesselModel.additionalBodyForce[0].pointOfAttackRel2APAndBL.ypos" to "azimuth0.output_y_rel_cl"
                "vesselModel.additionalBodyForce[0].pointOfAttackRel2APAndBL.zpos" to "azimuth0.output_z_rel_bl"
                
                "vesselModel.additionalBodyForce[1].force.heave" to "azimuth1.output_force_heave"
                "vesselModel.additionalBodyForce[1].force.surge" to "azimuth1.output_force_surge"
                "vesselModel.additionalBodyForce[1].force.sway" to "azimuth1.output_force_sway"
                "vesselModel.additionalBodyForce[1].pointOfAttackRel2APAndBL.xpos" to "azimuth1.output_x_rel_ap"
                "vesselModel.additionalBodyForce[1].pointOfAttackRel2APAndBL.ypos" to "azimuth1.output_y_rel_cl"
                "vesselModel.additionalBodyForce[1].pointOfAttackRel2APAndBL.zpos" to "azimuth1.output_z_rel_bl"
                
                "vesselModel.additionalBodyForce[2].force.heave" to "tunnelThruster.output_force_heave"
                "vesselModel.additionalBodyForce[2].force.surge" to "tunnelThruster.output_force_surge"
                "vesselModel.additionalBodyForce[2].force.sway" to "tunnelThruster.output_force_sway"
                "vesselModel.additionalBodyForce[2].pointOfAttackRel2APAndBL.xpos" to "tunnelThruster.output_x_rel_ap"
                "vesselModel.additionalBodyForce[2].pointOfAttackRel2APAndBL.ypos" to "tunnelThruster.output_y_rel_cl"
                "vesselModel.additionalBodyForce[2].pointOfAttackRel2APAndBL.zpos" to "tunnelThruster.output_z_rel_bl"
                
                "vesselModel.additionalNedForce[0].force.north" to "crane.craneForce[1]"
                "vesselModel.additionalNedForce[0].force.east" to "crane.craneForce[2]"
                "vesselModel.additionalNedForce[0].force.down" to "crane.craneForce[3]"
                "vesselModel.additionalNedForce[0].pointOfAttackRel2APAndBL.xpos" to "crane.cranePosition[1]"
                "vesselModel.additionalNedForce[0].pointOfAttackRel2APAndBL.ypos" to "crane.cranePosition[2]"
                "vesselModel.additionalNedForce[0].pointOfAttackRel2APAndBL.zpos" to "crane.cranePosition[3]"
                "vesselModel.additionalNedForce[1].force.north" to "crane.loadForce[1]"
                "vesselModel.additionalNedForce[1].force.east" to "crane.loadForce[2]"
                "vesselModel.additionalNedForce[1].force.down" to "crane.loadForce[3]"
                "vesselModel.additionalNedForce[1].pointOfAttackRel2APAndBL.xpos" to "crane.tipPositionShipFrame[1]"
                "vesselModel.additionalNedForce[1].pointOfAttackRel2APAndBL.ypos" to "crane.tipPositionShipFrame[2]"
                "vesselModel.additionalNedForce[1].pointOfAttackRel2APAndBL.zpos" to "crane.tipPositionShipFrame[3]"
            }

        }
    }
    
    namespaces {
        namespace("osp", "http://opensimulationplatform.com/SSP/OSPAnnotations")
    }

    resources {
        url("https://github.com/gunnerus-case/gunnerus-fmus-bin/raw/master/VesselFmu.fmu")
        url("https://github.com/gunnerus-case/gunnerus-fmus-bin/raw/master/PowerPlant.fmu")
        url("https://github.com/gunnerus-case/gunnerus-fmus-bin/raw/master/ThrusterDrive.fmu")
        url("https://github.com/gunnerus-case/gunnerus-fmus-bin/raw/master/TunnelThruster.fmu")
        url("https://github.com/gunnerus-case/gunnerus-fmus-bin/raw/master/PalfingerCrane.fmu")
        url("https://github.com/gunnerus-case/gunnerus-fmus-bin/raw/master/DPController.fmu")
        url("https://github.com/gunnerus-case/gunnerus-fmus-bin/raw/master/NonlinearPassiveObserver.fmu")
        url("https://github.com/gunnerus-case/gunnerus-fmus-bin/raw/master/WaypointProvider3DOF.fmu")
        url("https://github.com/gunnerus-case/gunnerus-fmus-bin/raw/master/ControlAllocatorGIFixedAngles.fmu")
    }

}
