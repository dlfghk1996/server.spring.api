package server.spring.api.common.config.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
//@EnableJpaRepositories(
//	basePackages = {"server.spring.api.initial_api.repository"},
//	repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@Configuration
public class AuditConfig {
}
