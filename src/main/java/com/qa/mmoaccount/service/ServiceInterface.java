package com.qa.mmoaccount.service;

import java.util.List;

public interface ServiceInterface<T> {

	// Create
	T create(T t);

	// Read [All - ID]
	List<T> readAll(); // All

	T readById(Long id);

	// Update
	T update(Long id, T t);

	// Delete
	T delete(Long id);

}
