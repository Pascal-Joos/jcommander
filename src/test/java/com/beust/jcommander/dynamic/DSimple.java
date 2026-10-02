package com.beust.jcommander.dynamic;

import com.beust.jcommander.DynamicParameter;
import java.util.Map;
import org.testng.collections.Maps;

public class DSimple {

  @DynamicParameter(names = "-D", description = "Dynamic parameters go here")
  public Map<String, String> params = Maps.newHashMap();

  @DynamicParameter(names = "-A", assignment = "@")
  public Map<String, String> params2 = Maps.newHashMap();
}
