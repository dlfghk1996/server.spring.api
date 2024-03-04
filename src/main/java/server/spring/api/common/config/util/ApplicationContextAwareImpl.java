package server.spring.api.common.config.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

// the Spring IoC container that holds all the beans
// ApplicationContext 에 대한 참조를 얻고 다른 빈이나 리소스에 액세스할 수 있다.
@Component
public class ApplicationContextAwareImpl implements ApplicationContextAware {

  private static ApplicationContext context;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    context = applicationContext;
  }

  public static ApplicationContext getApplicationContext() {
    return context;
  }

  public static <T> T getBean(Class<T> beanClass) {
    return context.getBean(beanClass);
  }
}
