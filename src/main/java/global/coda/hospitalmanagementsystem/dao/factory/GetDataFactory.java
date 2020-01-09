package global.coda.hospitalmanagementsystem.dao.factory;

import java.io.IOException;
import java.util.Map;

import global.coda.hospitalmanagementsystem.dao.HospitalManagementDao;
import global.coda.hospitalmanagementsystem.dao.HospitalManagementDaoImpCSV;
import global.coda.hospitalmanagementsystem.dao.HospitalManagementDaoImpXML;
import global.coda.hospitalmanagementsystem.dao.PatientCriticality;
import global.coda.hospitalmanagementsystem.models.PatientForFile;

public class GetDataFactory {
 public HospitalManagementDao getDataFactory(int patientId) throws NumberFormatException, IOException
 {
	 FindPatient findPatient=new FindPatient();
	 HospitalManagementDao obj;
	 if(findPatient.getPatientPriority(patientId)==PatientCriticality.TEMPORARY)
	 {
		 obj=new HospitalManagementDaoImpCSV();
	 }
	 else 
	 {
		 obj=new HospitalManagementDaoImpXML();
	 }
	 
	return obj;
	 
 }
	

}
