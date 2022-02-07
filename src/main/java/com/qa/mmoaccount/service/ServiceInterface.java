package com.qa.mmoaccount.service;

import java.util.List;

public interface ServiceInterface<T> {

	T create(T t); // Create

	List<T> readAll(); // Read All

	T readById(Long id); // Read Id

	T update(Long id, T t); // Update

	T delete(Long id); // Delete
}
