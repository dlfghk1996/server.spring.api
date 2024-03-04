package server.spring.api.common.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import server.spring.api.common.util.GenericReflectionUtils;
import server.spring.api.common.domain.AbstractEntity;
import server.spring.api.common.domain.vo.AbstractData;
import server.spring.api.common.enums.ResponseCode;
import server.spring.api.common.exception.CustomException;
import server.spring.api.common.repository.BaseRevisionRepository;

@Slf4j
@RequiredArgsConstructor
@Transactional
public abstract class AbstractRevisionServiceImpl<
	E,
	P extends Serializable,
	R extends BaseRevisionRepository<E, P, ? extends EntityPath<E>, N>,
	N extends Number & Comparable<N>,
	RQ extends AbstractData,
	DO > implements AbstractRevisionService<E, P, R, N, RQ, DO> {

	//@Autowired
	private final R repository;

	@Autowired
	protected ModelMapper mapper;

	protected Class<E> entityClass;
	protected Class<RQ> reqClass;
	protected Class<DO> dataClass;

	@PostConstruct
	public void init() {
		this.entityClass = GenericReflectionUtils.getGenericTypeParam(getClass(), 0);
		this.reqClass = GenericReflectionUtils.getGenericTypeParam(getClass(), 4);
		this.dataClass = GenericReflectionUtils.getGenericTypeParam(getClass(), 5);
	}

	public E add(E entity) {
		return repository.save(entity);
	}

	public DO addData(RQ request) {
		E entity = mapper.map(request, entityClass);
		entity = add(entity);
		return mapper.map(entity, dataClass);
	}

	public E update(E entity) {
		return repository.save(entity);
	}

	public DO updateData(RQ request) {
		E entity = repository.getById((P)request.getId());
		mapper.map(request, entity);
		entity = update(entity);
		return mapper.map(entity, dataClass);
	}

	@Override
	public void deleteById(P id) {
		repository.deleteById(id);
	}

	@Override
	public void delete(E entity) {
		repository.delete(entity);
	}

	@Override
	public E get(P id) {
		//        return repository.getOne(id);
		return repository
			.findById(id)
			.orElseThrow(() -> new CustomException("결과가 없습니다. : " + id, ResponseCode.RESULT_NOT_FOUND));
	}

	public DO getData(P id) {
		//        return repository.findById(id).map(e -> mapper.map(e, dataClass)).orElseThrow(() ->
		// new ResourceNotFoundException("Not found :: " + id));
		return repository
			.findById(id)
			.map(e -> mapper.map(e, dataClass))
			.orElseThrow(() -> new CustomException("결과가 없습니다. : " + id, ResponseCode.RESULT_NOT_FOUND));
	}

	@Override
	public E find(P id) {
		//        return repository.findById(id).orElse(null);
		//        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not
		// found :: " + id));
		return repository
			.findById(id)
			.orElseThrow(() -> new CustomException("결과가 없습니다. : " + id, ResponseCode.RESULT_NOT_FOUND));
	}

	@Override
	public E enable(E entity) {
		if (entity instanceof AbstractEntity) {
			((AbstractEntity)entity).setDisabled(false);
		}
		return update(entity);
	}

	@Override
	public E disable(E entity) {
		if (entity instanceof AbstractEntity) {
			((AbstractEntity)entity).setDisabled(true);
		}
		return update(entity);
	}

	@Override
	public Page<E> search(Predicate predicate, Pageable pageable) {
		return repository.findAll(
			Optional.ofNullable(predicate).orElse(new BooleanBuilder()), pageable);
	}
//
//	@Override
//	public Revisions<N, E> findRevisions(P id) {
//		return repository.findRevisions(id);
//	}
//
//	@Override
//	public Page<Revision<N, E>> findRevisions(P id, Pageable pageable) {
//		return repository.findRevisions(id, pageable);
//	}
//
//	@Override
//	public Revision<N, E> findRevision(P id, N revisionNumber) {
//		return repository
//			.findRevision(id, revisionNumber)
//			.orElseThrow(() -> new CustomException(ResponseCode.RESULT_NOT_FOUND.getLabel()));
//	}
//
//	@Override
//	public Revision<N, E> findLastChangeRevision(P id) {
//		return repository
//			.findLastChangeRevision(id)
//			.orElseThrow(() -> new CustomException(ResponseCode.RESULT_NOT_FOUND.getLabel()));
//	}

	@Override
	public R getRepository() {
		return repository;
	}
}
