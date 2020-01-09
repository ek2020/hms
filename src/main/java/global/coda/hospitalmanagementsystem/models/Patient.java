package global.coda.hospitalmanagementsystem.models;

public class Patient extends User {
	private int patientAge;
	private String patientWeight;
	private String patientDiagnosis;

	public Patient(String patientWieght, String patientDiagnosis2, int patientAge2) {
		// TODO Auto-generated constructor stub
	}

	public Patient() {
		// TODO Auto-generated constructor stub
	}

	public int getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	public String getPatientWeight() {
		return patientWeight;
	}

	public void setPatientWeight(String patientWeight) {
		this.patientWeight = patientWeight;
	}

	public String getPatientDiagnosis() {
		return patientDiagnosis;
	}

	public void setPatientDiagnosis(String patientDiagnosis) {
		this.patientDiagnosis = patientDiagnosis;
	}

}
