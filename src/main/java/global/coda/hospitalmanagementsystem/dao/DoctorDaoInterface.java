package global.coda.hospitalmanagementsystem.dao;

import java.sql.SQLException;
import java.util.List;

import global.coda.hospitalmanagementsystem.models.Doctor;
import global.coda.hospitalmanagementsystem.models.Patient;

public interface DoctorDaoInterface {
	public List<Doctor> readAll() throws SQLException;


	boolean insert(Doctor doctor);

	Patient read(String username) throws SQLException;



	boolean update(String userName,Doctor doctor) throws SQLException;


	boolean delete(String userName) throws SQLException;
	

}
