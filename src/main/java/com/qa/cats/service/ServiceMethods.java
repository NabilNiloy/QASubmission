package com.qa.cats.service;

import java.util.List;

public interface ServiceMethods<T> {

	T create(T cat);
	
	List<T> readAll();
	
	T readById(long id);
	
	T update(long id, T cat);
	
	boolean delete(long id);
	
}
