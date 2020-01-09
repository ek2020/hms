package global.coda.hospitalmanagementsystem.models;

import java.util.*;



/**
 * Model 
 * Parameter as patientId,patientNames,patientDiagnosis
 * @author EK
 *
 */

public class PatientForFile {
	
	
	
	private String patientNames;
	private String patientDiagnosis;
	private String patientAge;
	private List<String> address;
	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}


	
	public List<String> getAddress() {
		return address;
	}

	public void setAddress(List<String> address) {
		this.address = address;
	}

	
	/*
	 * Generate Getters and Setters for above Variables
	 */
	

	public String getPatientNames() {
		return patientNames;
	}

	public void setPatientNames(String patientNames) {
		this.patientNames = patientNames;
	}

	public String getPatientDiagnosis() {
		return patientDiagnosis;
	}

	public void setPatientDiagnosis(String patientDiagnosis) {
		this.patientDiagnosis = patientDiagnosis;
	}

	@Override
	public String toString() {
		return "Patient [patientNames=" + patientNames + ", patientDiagnosis=" + patientDiagnosis + ", patientAge="
				+ patientAge + ", address=" + address + "]";
	}

	
	

	/*
	 * Create Constructor its not so important....
	 */
	
	
	
	
	
	

}
