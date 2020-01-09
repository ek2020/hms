package global.coda.hospitalmanagementsystem.models;

public class Doctor extends User{
String doctorName;
String doctorSalary;
String doctorSpecialist;

public Doctor(String doctorName2, String doctorSalary2, String doctorSpecialist2) {
	// TODO Auto-generated constructor stub
}
public Doctor() {
	// TODO Auto-generated constructor stub
}
public String getDoctorSalary() {
	return doctorSalary;
}
public void setDoctorSalary(String doctorSalary) {
	this.doctorSalary = doctorSalary;
}
public String getDoctorSpecialist() {
	return doctorSpecialist;
}
public void setDoctorSpecialist(String doctorSpecialist) {
	this.doctorSpecialist = doctorSpecialist;
}
public String getDoctorName() {
	return doctorName;
}
public void setDoctorName(String doctorName) {
	this.doctorName = doctorName;
}
@Override
public String toString() {
	return "Doctor [doctorName=" + doctorName + ", doctorSalary=" + doctorSalary + ", doctorSpecialist="
			+ doctorSpecialist + "]";
}



}
