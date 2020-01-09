package global.coda.hospitalmanagementsystem.dao;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import global.coda.hospitalmanagementsystem.models.PatientForFile;

public interface HospitalManagementDao {
	public Map<Integer,PatientForFile> readAll() throws NumberFormatException, IOException, ParserConfigurationException, SAXException;
	public boolean write(Map<Integer,PatientForFile> patientInfo1) throws IOException;
}
