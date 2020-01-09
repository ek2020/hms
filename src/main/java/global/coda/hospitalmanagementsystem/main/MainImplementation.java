package global.coda.hospitalmanagementsystem.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.constants.ApplicationConstant;
import global.coda.hospitalmanagementsystem.customexception.CustomException;
import global.coda.hospitalmanagementsystem.models.PatientForFile;
import global.coda.hospitalmanagementsystem.models.Patient;
import global.coda.hospitalmanagementsystem.services.HospitalManagementServicesImpl;

public class MainImplementation {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainImplementation.class);

	public static void main(String args[]) {
		String query="SELECT patient_weight,patient_diagnosis,patient_age,is_active from t_patient";
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet result = null;
		try {
			result = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String patientDiagnosis="",patientWieght="";
		int patientAge;
		List<Patient> patientInfo=new ArrayList<>();
		try {
			while(result.next())
			{

				System.out.print(result.getString(2));
				patientWieght=result.getString(2);
				patientDiagnosis=result.getString(3);
				patientAge=result.getInt(3);
				patientInfo.add(new Patient(patientWieght,patientDiagnosis,patientAge));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(patientInfo);
		
		/*
		ResourceBundle mybundle = ResourceBundle.getBundle(ApplicationConstant.HMS1000D);
		HospitalManagementServicesImpl patient = new HospitalManagementServicesImpl();
		Scanner sc = new Scanner(System.in);
		while (true) {
			LOGGER.info(mybundle.getString(ApplicationConstant.HMS3005D));
			int choice = sc.nextInt();
			if (choice == 1) {
				LOGGER.info(mybundle.getString(ApplicationConstant.HMS3006D));
				int patientId = sc.nextInt();
				String patientName = sc.next();
				String patientDiagnosis = sc.next();
				String patientAge = sc.next();
				List<String> address = new ArrayList<>();
				for (int i = 0; i <= 3; i++) {
					address.add(sc.nextLine());
				}
				Patient patient1 = new Patient();
				patient1.setAddress(address);
				patient1.setPatientAge(patientAge);
				patient1.setPatientNames(patientName);
				patient1.setPatientDiagnosis(patientDiagnosis);
				HospitalManagementServicesImpl myService = new HospitalManagementServicesImpl();
				try {
					if (myService.addPatient(patientId, patient1)) {
						LOGGER.info(mybundle.getString(ApplicationConstant.HMS3001D));
					} else {
						LOGGER.error(mybundle.getString(ApplicationConstant.HMS4000D));
					}
				} catch (Exception e) {
				}

			} else if (choice == 2) {
				LOGGER.info(mybundle.getString(ApplicationConstant.HMS3007D));
				int patientId = sc.nextInt();
				try {
					if (patient.readPatient(patientId)) {

						LOGGER.info(mybundle.getString(ApplicationConstant.HMS3002D));
					} else {
						LOGGER.error(mybundle.getString(ApplicationConstant.HMS4000D));
					}

				} catch (Exception e) {
					LOGGER.error(mybundle.getString(ApplicationConstant.notFound));

				}
			} 
			else if (choice == 3) {
				LOGGER.info(mybundle.getString(ApplicationConstant.HMS3008D));
				int patientId = sc.nextInt();
				String patientName = sc.next();
				String patientDiagnosis = sc.next();
				String patientAge = sc.next();
				List<String> address = new ArrayList<>();
				for (int i = 0; i <= 3; i++) {
					address.add(sc.nextLine());
				}
				Patient updatedPatient = new Patient();
				updatedPatient.setAddress(address);
				updatedPatient.setPatientAge(patientAge);
				updatedPatient.setPatientDiagnosis(patientDiagnosis);
				updatedPatient.setPatientNames(patientName);
				try {
	patient.updatePatient(patientId, updatedPatient);
				} catch (CustomException e) {
					LOGGER.error(e.toString());
				} catch (Exception e) {
					LOGGER.error(mybundle.getString(ApplicationConstant.notFound));
				}
			} else if (choice == 4) {
				LOGGER.info(mybundle.getString(ApplicationConstant.HMS3009D));
				int patientId = sc.nextInt();
				try {
					if (patient.deletePatient(patientId)) {
						LOGGER.info(mybundle.getString(ApplicationConstant.HMS3004D));
					} else {
						LOGGER.error(mybundle.getString(ApplicationConstant.HMS4000D));
					}
				} catch (Exception e) {
					LOGGER.error(mybundle.getString(ApplicationConstant.notFound));
				}
			} else
				break;

		}
		sc.close();*/

	}

}
