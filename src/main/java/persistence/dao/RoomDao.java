package persistence.dao;

import java.util.List;

import model.Room;

public interface RoomDao {
	
	public void save(Room Room);  // Create
	public List<Room> findAll();       
	public void update(Room Room); //Update
	public void delete(Room Room); //Delete	
}