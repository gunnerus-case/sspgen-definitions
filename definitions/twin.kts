#!sspgen

ssp("gunnerus-twin") {

    ssd("KPN Twinship Gunnerus case") {

        system("gunnerus-twwin") {

            elements {

                component("gunnerus", "resources/Gunnerus.fmu") {
                    connectors {
                       real("portAzimuth.rpm", output)
                       real("portAzimuth.angle", output)
                       real("starboardAzimuth.rpm", output)
                       real("starboardAzimuth.angle", output)
                       
                       real("wind.direction", output)
                       real("wind.speed", output)
                       
                       real("rotation.yaw", output)
                       real("current", output)
                    }
                    parameterBindings {
                        parameterSet("initialValues") {
                            string("csvPath", "docking_aalesund_9_nov.csv")
                        }
                    }
                }

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
                  
                        real("cg_x_rel_ap", output)
                        real("cg_y_rel_cl", output)
                        real("cg_z_rel_bl", output)
                        real("cgShipMotion.ned.north", output)
                        real("cgShipMotion.ned.east", output)
                        real("cgShipMotion.linearVelocity.surge", output)
                        real("cgShipMotion.linearVelocity.sway", output)
                        real("cgShipMotion.linearVelocity.yaw", output)
                        real("cgShipMotion.angularVelocity.yaw", output)
                        real("cgShipMotion.angularDisplacement.yaw", output)
                        
                        real("input_global_wind_dir", input)
                        real("input_global_wind_vel", input)
                        
                        real("global_cur_dir", input)
                        real("global_cur_vel", input)
                    }
                    parameterBindings {
                        parameterSet("initialValues") {
                            string("vesselZipFile", "%fmu%/resources/ShipModel-gunnerus-elongated.zip")
                            boolean("initialize_using_hydrostatics", false)
                        }
                    }
                }

                component(
                    "portAzimuth",
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
                }

                component(
                    "starboardAzimuth",
                    "fmu-proxy://localhost:9091?url=http://folk.ntnu.no/laht/files/cse/PMAzimuth.fmu"
                ) {
                    connectors {
                        copyFrom("portAzimuth")
                    }
                    parameterBindings {
                        copyFrom("azimuth0", "initialValues") {
                            real("input_y_rel_cl", 2)
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
                }
                
            }

            connections(inputsFirst=true) {
                
                ("portAzimuth.input_act_revs" to "gunnerus.portAzimuth.rpm").linearTransformation(factor=2.03)
                "portAzimuth.input_act_angle" to "gunnerus.portAzimuth.angle"
                "portAzimuth.input_cg_x_rel_ap" to "vesselModel.cg_x_rel_ap"
                "portAzimuth.input_cg_y_rel_cl" to "vesselModel.cg_y_rel_cl"
                "portAzimuth.input_cg_z_rel_bl" to "vesselModel.cg_z_rel_bl"
                "portAzimuth.input_cg_surge_vel" to "vesselModel.cgShipMotion.linearVelocity.surge"
                "portAzimuth.input_cg_sway_vel" to "vesselModel.cgShipMotion.linearVelocity.sway"
                "portAzimuth.input_yaw_vel" to "vesselModel.cgShipMotion.angularVelocity.yaw"
                
                ("starboardAzimuth.input_act_revs" to "gunnerus.portAzimuth.rpm").linearTransformation(factor=2.03)
                "starboardAzimuth.input_act_angle" to "gunnerus.portAzimuth.angle"
                "starboardAzimuth.input_cg_x_rel_ap" to "vesselModel.cg_x_rel_ap"
                "starboardAzimuth.input_cg_y_rel_cl" to "vesselModel.cg_y_rel_cl"
                "starboardAzimuth.input_cg_z_rel_bl" to "vesselModel.cg_z_rel_bl"
                "starboardAzimuth.input_cg_surge_vel" to "vesselModel.cgShipMotion.linearVelocity.surge"
                "starboardAzimuth.input_cg_sway_vel" to "vesselModel.cgShipMotion.linearVelocity.sway"
                "starboardAzimuth.input_yaw_vel" to "vesselModel.cgShipMotion.angularVelocity.yaw"
               
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
                
                "vesselModel.input_global_wind_dir" to "gunnerus.wind.direction"
                "vesselModel.input_global_wind_vel" to "gunnerus.wind.speed"
            }

        }
    }

    resources {
        url("https://github.com/gunnerus-case/gunnerus-fmus-bin/raw/master/VesselFmu.fmu")
        url("https://github.com/gunnerus-case/gunnerus-fmus-bin/raw/master/Gunnerus.fmu")
        url("https://github.com/gunnerus-case/gunnerus-fmus-bin/raw/master/ThrusterDrive.fmu")
    }

}
