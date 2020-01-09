package global.coda.hospitalmanagementsystem.services;

import global.coda.hospitalmanagementsystem.dao.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import global.coda.hospitalmanagementsystem.constants.ApplicationConstant;
import global.coda.hospitalmanagementsystem.customexception.CustomException;
import global.coda.hospitalmanagementsystem.models.PatientForFile;
import global.coda.hospitalmanagementsystem.dao.factory.GetDataFactory;

/**
 * 
 * Service Operations are performed which are Create,Read,Delete,Update
 * 
 * @author EK
 *
 */

public class HospitalManagementServicesImpl implements HospitalManagementServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(HospitalManagementServicesImpl.class);
	/*
	 * create logger for output stream
	 */
	ResourceBundle mybundle = ResourceBundle.getBundle(ApplicationConstant.HMS1000D);
	/*
	 * Map<Integer, Patient> patientInfo = new LinkedHashMap<>();
	 * HospitalManagementDaoImp daoObject=new HospitalManagementDaoImp();
	 * patientInfo=daoObject.readAll();
	 */
	/*
	 * Get the message.properties by application constant using key as HMS100D
	 * Create the LinkedHashMAp for maintain order of values
	 */

	public boolean addPatient(int patientId, PatientForFile patient)
			throws IOException, NumberFormatException, SAXException, ParserConfigurationException {

		Map<Integer, PatientForFile> patientInfo = new LinkedHashMap<>();
		GetDataFactory dao = new GetDataFactory();
		HospitalManagementDao daoObject = dao.getDataFactory(patientId);
		// System.out.print(daoObject);
		patientInfo = daoObject.readAll();
		System.out.print(patientInfo);
		patientInfo.put(patientId, patient);
		System.out.print(patientInfo);
		if (daoObject.write(patientInfo)) {
			System.out.print("yes");
		} else {
			System.out.print("No");

		}

		/* patientInfo.put(patientId, patient); */

		return true;
	}

	public boolean readPatient(int patientId)
			throws ParserConfigurationException, SAXException, NumberFormatException, IOException {

		// Read and Display the Particular PatientId Record
		Map<Integer, PatientForFile> patientInfo = new LinkedHashMap<>();
		PatientForFile patient = new PatientForFile();
		GetDataFactory dao = new GetDataFactory();
		HospitalManagementDao daoObject = dao.getDataFactory(patientId);
		try {
			patientInfo = daoObject.readAll();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		patient = patientInfo.get(patientId);
		System.out.print(patient.getPatientNames());
		System.out.print(patient.getPatientDiagnosis());
		System.out.print(patient.getPatientAge());
		System.out.print(patient.getAddress());
		return true;

	}

	public boolean updatePatient(int patientId, PatientForFile patient)
			throws CustomException, IOException, ParserConfigurationException, SAXException {
		Map<Integer, PatientForFile> patientInfo = new LinkedHashMap<>();
		GetDataFactory dao = new GetDataFactory();
		HospitalManagementDao daoObject = dao.getDataFactory(patientId);
		System.out.print(daoObject);
		try {
			patientInfo = daoObject.readAll();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Update the Patient record

		if (!patientInfo.containsKey(patientId)) {
			throw new CustomException(mybundle.getString(ApplicationConstant.notFound));
		}
		try {
			patientInfo.put(patientId, patient);
			daoObject.write(patientInfo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// patientInfo.put(patientId, patient);
		return true;

	}

	public boolean deletePatient(int patientId)
			throws CustomException, NumberFormatException, IOException, ParserConfigurationException, SAXException {
		Map<Integer, PatientForFile> patientInfo = new LinkedHashMap<>();
		GetDataFactory dao = new GetDataFactory();
		HospitalManagementDao daoObject = dao.getDataFactory(patientId);
		try {
			patientInfo = daoObject.readAll();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!patientInfo.containsKey(patientId)) {
			throw new CustomException(mybundle.getString(ApplicationConstant.notFound));
		}
		System.out.println(patientInfo);
		patientInfo.remove(patientId);
		System.out.println(patientInfo);
		// patientInfo=daoObject.readAll();

		daoObject.write(patientInfo);
		System.out.println(patientInfo);
		// DELETE the particular patientId along with data

		return true;

	}

}
