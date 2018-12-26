package com.dws.resources;

import java.util.List;
import java.util.Optional;

public interface CRUD<T> {
	Boolean save(T entity);

	Boolean update(T entity);

	Optional<T> getById(int id);

	List<Optional<T>> getAll();
}
