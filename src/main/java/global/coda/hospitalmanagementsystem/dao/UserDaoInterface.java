package global.coda.hospitalmanagementsystem.dao;

import java.sql.SQLException;

import global.coda.hospitalmanagementsystem.models.User;

public interface UserDaoInterface {
	boolean insert(User user);

	User read(String username) throws SQLException;



	boolean update(String userName, User user) throws SQLException;


	boolean delete(int id) throws SQLException;

}
