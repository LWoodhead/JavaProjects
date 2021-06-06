package com.ss.march.lib;

import java.util.List;

public interface LibraryDAO<T> {
	List<T> getAll();
	
	void save(T t);
	
	void update(T t, String[] params);
	
	void delete(T t);
}
