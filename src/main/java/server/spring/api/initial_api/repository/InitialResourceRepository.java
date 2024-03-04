package server.spring.api.initial_api.repository;

import com.querydsl.core.types.EntityPath;
import server.spring.api.common.repository.BaseRevisionRepository;
import server.spring.api.initial_api.domain.InitialResource;
import server.spring.api.initial_api.domain.QInitialResource;

public interface InitialResourceRepository extends
    BaseRevisionRepository<InitialResource, Long, QInitialResource, Long> {
}
