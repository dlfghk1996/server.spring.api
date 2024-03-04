package server.spring.api.common.service;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import java.io.Serializable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import server.spring.api.common.repository.BaseRevisionRepository;

public interface AbstractRevisionService<
    E,
    P extends Serializable,
    R extends BaseRevisionRepository<E, P, ? extends EntityPath<E>, N>,
    N extends Number & Comparable<N>,
    RO,
    DO> {

  E add(E entity);

  DO addData(RO request);

  E update(E entity);

  DO updateData(RO request);

  void deleteById(P id);

  void delete(E entity);

  E get(P id);

  DO getData(P id);

  E find(P id);

  E enable(E entity);

  E disable(E entity);

  Page<E> search(Predicate predicate, Pageable pageable);
//
//  Revisions<N, E> findRevisions(P id);
//
//  Page<Revision<N, E>> findRevisions(P id, Pageable pageable);
//
//  Revision<N, E> findRevision(P id, N revisionNumber);
//
//  Revision<N, E> findLastChangeRevision(P id);

  R getRepository();
}
