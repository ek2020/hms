package global.coda.hospitalmanagementsystem.services;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import global.coda.hospitalmanagementsystem.customexception.CustomException;
import global.coda.hospitalmanagementsystem.models.PatientForFile;

public interface HospitalManagementServices 
{

	public boolean addPatient(int patientId,PatientForFile patient) throws IOException, NumberFormatException, SAXException, ParserConfigurationException;
	
	public boolean deletePatient(int patientId) throws CustomException, NumberFormatException, IOException, ParserConfigurationException, SAXException;
	
	public boolean updatePatient(int patientId,PatientForFile patient) throws CustomException, IOException, ParserConfigurationException, SAXException;
	
	public boolean readPatient(int patientId) throws ParserConfigurationException, SAXException, NumberFormatException, IOException;
	

}
