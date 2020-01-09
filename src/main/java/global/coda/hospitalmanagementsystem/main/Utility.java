package global.coda.hospitalmanagementsystem.main;

import java.util.ResourceBundle;
import java.util.Scanner;

import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.constants.ApplicationConstant;
import global.coda.hospitalmanagementsystem.models.Doctor;
import global.coda.hospitalmanagementsystem.models.Patient;

public class Utility {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Utility.class);
	/*
	 * create logger for output stream
	 */
	ResourceBundle mybundle = ResourceBundle.getBundle(ApplicationConstant.HMS1000D);
	
	Object utilityFunctions(int roleId)
	{
		Scanner sc = new Scanner(System.in);
		if(roleId==1)
		{

			Patient patient=new Patient();
			LOGGER.info("Enter the patient_weight");
			patient.setPatientWeight(sc.next());
			LOGGER.info("Enter the patient_diagnosis");
			patient.setPatientDiagnosis(sc.next());
			LOGGER.info("Enter the patient_age");
			patient.setPatientAge(sc.nextInt());
			return patient;
		}
		else if(roleId==2)
		{
			Doctor doctor=new Doctor();
			LOGGER.info("Enter the Doctor_Specialist");
			doctor.setDoctorSpecialist(sc.next());
			LOGGER.info("Enter the Doctor_Salary");
			doctor.setDoctorSalary(sc.next());
			return doctor;
		
		}
		else if(roleId==3)
		{
			
		}
		return 0;
	}

}
