package server.spring.api.common.service;

import java.util.List;
import java.util.Optional;
import server.spring.api.common.dto.BaseDTO;

public interface CrudService<T extends BaseDTO>{
    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T dto);

    void delete(Long id);

    T update(Long id, T dto);
}
