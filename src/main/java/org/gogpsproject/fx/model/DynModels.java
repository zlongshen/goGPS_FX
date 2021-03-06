package org.gogpsproject.fx.model;

import java.util.Arrays;
import java.util.List;

import org.gogpsproject.GoGPS;
import org.gogpsproject.fx.model.DynModel;

import net.java.html.json.Model;
import net.java.html.json.Property;

@Model(className = "DynModel", targetId = "", properties = {
    @Property(name = "name", type = String.class ),
    @Property(name = "value", type = int.class )
})
public class DynModels {
  public static List<DynModel> init(){
    return Arrays.asList( 
        new DynModel("Static",                GoGPS.DYN_MODEL_STATIC), 
        new DynModel("Constant Speed",        GoGPS.DYN_MODEL_CONST_SPEED), 
        new DynModel("Constant Acceleration", GoGPS.DYN_MODEL_CONST_ACCELERATION) );
  }
}
