package by.tms.projectinterpol.dao;

import by.tms.projectinterpol.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseDAO<PK extends Serializable, E extends BaseEntity<PK>> {

    PK save(E entity);

    void update(E entity);

    void delete(E entity);

    List<E> findAll();

    Optional<E> findById(PK id);


}
