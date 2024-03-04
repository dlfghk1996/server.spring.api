package server.spring.api.common.repository;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.history.RevisionRepository;

@NoRepositoryBean
public interface BaseRevisionRepository<
	E, P extends Serializable, EP extends EntityPath<E>, N extends Number & Comparable<N>>
	extends JpaRepository<E, P>,
	QuerydslPredicateExecutor<E>,
	QuerydslBinderCustomizer<EP>{
	//RevisionRepository<E, P, N> // entity 이력관리


	@Override
	default void customize(QuerydslBindings bindings, EP root) {
		// boolean eq 로 검사
		bindings.bind(Boolean.class).first((BooleanPath path, Boolean value) -> path.eq(value));
		// like '%%'
		bindings
			.bind(String.class)
			.first((SingleValueBinding<StringPath, String>)StringExpression::containsIgnoreCase);

		bindings
			.bind(Integer.class)
			.all(
				(final NumberPath<Integer> path, final Collection<? extends Integer> values) -> {
					final int size = values.size();
					// 검색 조건이 한개 일 경우 단순 equals 비교
					if (size == 1) {
						return Optional.of(path.eq(values.iterator().next()));
						// 두개 이상 일 경우 between 비교
					} else if (size == 2) {
						List<Integer> numbers = new ArrayList<>(values);
						Collections.sort(numbers);
						return Optional.of(path.between(numbers.get(0), numbers.get(1)));
					}
					throw new IllegalArgumentException(
						"1 or 2 number params(from & to) expected for:" + path + " found:" + values);
				});

		bindings
			.bind(Long.class)
			.all(
				(final NumberPath<Long> path, final Collection<? extends Long> values) -> {
					final int size = values.size();
					if (size == 1) {
						return Optional.of(path.eq(values.iterator().next()));
					} else if (size == 2) {
						List<Long> numbers = new ArrayList<>(values);
						Collections.sort(numbers);
						return Optional.of(path.between(numbers.get(0), numbers.get(1)));
					}
					throw new IllegalArgumentException(
						"1 or 2 number params(from & to) expected for:" + path + " found:" + values);
				});

		bindings
			.bind(LocalDateTime.class)
			.all(
				(path, value) -> {
					List<? extends LocalDateTime> dateTimes = new ArrayList<>(value);
					if (dateTimes.size() == 2) {
						LocalDateTime from = dateTimes.get(0);
						LocalDateTime to = dateTimes.get(1);
						return Optional.of(((DateTimePath)path).between(from, to));
					} else {
						// 검색 조건이 하나 일 경우
						return Optional.of(
							((DateTimePath)path)
								.between(
									dateTimes.get(0),
									dateTimes
										.get(0)
										.toLocalTime()
										.format(DateTimeFormatter.ISO_TIME)//  // 14:28:59.1467
										.startsWith("00:00:00")
										? dateTimes.get(0).plusDays(1) // 정오 이므로 하루 더해줌
										: dateTimes.get(0).plusSeconds(1)));
					}
				});
		bindings
			.bind(LocalDate.class)
			.all(
				(final DatePath<LocalDate> path, final Collection<? extends LocalDate> values) -> {
					final int size = values.size();
					if (size == 2) {
						List<? extends LocalDate> dates = new ArrayList<>(values);
						Collections.sort(dates);
						return Optional.of(path.between(dates.get(0), dates.get(1)));
					} else {
						LocalDate date = values.iterator().next();
						return Optional.of(((DatePath)path).between(date, date.plusDays(1)));
					}
				});
	}

	//    D find(D domain);
	//
	//    Page<E> search(Predicate predicate, Pageable pageable);
	//
	//    <E> Page<E> findAll(Predicate predicate, Pageable pageable);
	//
	//    Revisions<N, E> findRevisions(P id);
	//
	//    Page<Revision<N, E>> findRevisions(P id, Pageable pageable);
	//
	//    Optional<Revision<N, E>> findRevision(P id, N revisionNumber);

}
