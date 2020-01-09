package global.coda.hospitalmanagementsystem.dao;

import java.sql.SQLException;
import java.util.List;

import global.coda.hospitalmanagementsystem.models.Patient;
import global.coda.hospitalmanagementsystem.models.User;

public interface PatientDaoInterface {


public List<Patient> readAll() throws SQLException;


boolean insert(Patient patient);

Patient read(String username) throws SQLException;



boolean update(String userName, Patient patient) throws SQLException;


boolean delete(String userName) throws SQLException;

}
