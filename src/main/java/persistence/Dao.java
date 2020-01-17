package persistence;

import java.util.List;

public interface Dao<T> {
	
	void save(T object);
	
	List<T> retrieve(T object);
	List<T> retrieveBy(String column, Object value);
	List<T> retrieveAll();
	
	void update(T object);
	
	void delete(T object);
}