package server.spring.api.common.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericReflectionUtils {

  public static <T> Class<T> getGenericTypeParam(Class<?> clazz, Integer idx) {
    ParameterizedType genericSuperclass = (ParameterizedType) clazz.getGenericSuperclass();
    // 제네릭 파라미터 클래스 정보를 가져온다.
    Type type = genericSuperclass.getActualTypeArguments()[idx];

    if (type instanceof ParameterizedType) {
      return (Class<T>) ((ParameterizedType) type).getRawType();
    } else {
      return (Class<T>) type;
    }
  }

  public static <T> Class<T> getIfGenericTypeParam(
      Class<?> clazz, int interfaceIdx, int parameterIdx) {
    ParameterizedType genericInterfaces =
        (ParameterizedType) clazz.getGenericInterfaces()[interfaceIdx];
    Type type = genericInterfaces.getActualTypeArguments()[parameterIdx];

    if (type instanceof ParameterizedType) {
      return (Class<T>) ((ParameterizedType) type).getRawType();
    } else {
      return (Class<T>) type;
    }
  }
}
