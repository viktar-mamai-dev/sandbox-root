/*
 * Copyright (c) 2023
 */
package com.mamay.task2;

import java.lang.reflect.Method;
import org.springframework.beans.factory.support.MethodReplacer;

public class EReplacer implements MethodReplacer {

  public Object reimplement(Object obj, Method method, Object[] args) {
    Class<?>[] parameterTypes = method.getParameterTypes();
    if (isParameterOfTypeInt(parameterTypes[0]) && isParameterOfTypeInt(parameterTypes[1])) {
      if ((Integer) args[0] < (Integer) args[1]) {
        return "First parameter is smaller";
      } else {
        return "First parameter is bigger or equals";
      }
    }
    return "Parameter are not Integer types. Comparison unavailable";
  }

  private boolean isParameterOfTypeInt(Class<?> parameter) {
    return Integer.class.isAssignableFrom(parameter) || int.class.isAssignableFrom(parameter);
  }
}
