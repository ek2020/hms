package global.coda.hospitalmanagementsystem.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.constants.ApplicationConstant;
import global.coda.hospitalmanagementsystem.customexception.FileNotFound;
import global.coda.hospitalmanagementsystem.models.PatientForFile;
import global.coda.hospitalmanagementsystem.services.HospitalManagementServicesImpl;

public class HospitalManagementDaoImpCSV implements HospitalManagementDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(HospitalManagementServicesImpl.class);
	/*
	 * create logger for output stream
	 */
	ResourceBundle mybundle = ResourceBundle.getBundle(ApplicationConstant.HMS1000D);
	/*
	 * public boolean addPatients(int patientId,Patient patient) throws IOException
	 * { String filePath="C:\\Users\\CSS\\Desktop\\Data.csv"; File f1=new
	 * File(filePath); int flag=0; if(f1.createNewFile()) { flag=1; } BufferedWriter
	 * bufferedWriter=new BufferedWriter(new FileWriter(filePath));
	 * bufferedWriter.write(patientId); bufferedWriter.write(patient.toString());
	 * return true;
	 * 
	 * }
	 */
	public Map<Integer, PatientForFile> readAll() throws NumberFormatException, IOException {
		String filePath = mybundle.getString(ApplicationConstant.CSVFILEPATH);
		String currLine;
		File flag1 = new File(filePath);
		int flag = 0;
		if (flag1.createNewFile()) {
			flag = 1;
		}
		BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
		Map<Integer, PatientForFile> patientInfo = new LinkedHashMap<>();
		while ((currLine = bufferedReader.readLine()) != null) {
			String tempElement[] = currLine.split(",");
			PatientForFile patient = new PatientForFile();
			patient.setPatientNames(tempElement[1]);
			patient.setPatientDiagnosis(tempElement[2]);
			patient.setPatientAge(tempElement[3]);
			List<String> address = new ArrayList<>();
			String address1[] = tempElement[4].split("-");
			for (int i = 0; i < address1.length; i++)
				address.add(address1[i]);
			patient.setAddress(address);
			patientInfo.put(Integer.parseInt(tempElement[0]), patient);

		}
		bufferedReader.close();
		// System.out.print(patientInfo);
		return patientInfo;

	}

	/*
	 * Patient readById(int patientId) throws FileNotFound { String
	 * filePath="C:\\Users\\CSS\\Desktop\\Data.csv"; String currLine; File f1=new
	 * File(filePath); Map<Integer,Patient> patientInfo=new LinkedHashMap<>(); int
	 * flag=0; try { if(!f1.isFile()) { throw new FileNotFound("File Not Found"); }
	 * } catch(FileNotFound e) { return patientInfo; } BufferedReader bufferedReader
	 * = null; try { bufferedReader = new BufferedReader(new FileReader(filePath));
	 * } catch (FileNotFoundException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * try { while ((currLine=bufferedReader.readLine())!=null) { String
	 * tempElement[]=currLine.split(" "); Patient patient=new Patient();
	 * patient.setPatientNames(tempElement[1]);
	 * patient.setPatientDiagnosis(tempElement[2]);
	 * patient.setPatientAge(tempElement[3]); List<String> address=new
	 * ArrayList<>(); for(int temp=4;temp<tempElement.length;temp++) {
	 * address.add(tempElement[temp]); }
	 * 
	 * patient.setAddress(address);
	 * patientInfo.put(Integer.parseInt(tempElement[0]), patient);
	 * 
	 * } } catch (NumberFormatException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); }
	 * 
	 * return patientInfo.get(patientId);
	 * 
	 * }
	 */
	public boolean writeAll(int patientId, PatientForFile patient) throws IOException {

		String filePath = "C:\\Users\\CSS\\Desktop\\Data.csv";
		String currLine;
		File f1 = new File(filePath);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
		Map<Integer, PatientForFile> patientInfo = new LinkedHashMap<>();
		patientInfo = readAll();

		// Patient patient1=new Patient();
		for (Integer key : patientInfo.keySet()) {

			if (key == patientId) {
				PatientForFile patient1 = new PatientForFile();
				patient1 = patientInfo.get(key);
				patient1.setPatientNames(patient.getPatientNames());
				patient1.setPatientDiagnosis(patient.getPatientDiagnosis());
				patient1.setPatientAge(patient.getPatientAge());
				patient1.setAddress(patient.getAddress());
				break;
			}
		}

		// BufferedWriter bufferedWriter=new BufferedWriter(new
		// FileWriter(filePath,true));
		File fileObject = new File(filePath);
		fileObject.delete();
		/*
		 * if(fileObject.exists()){ fileObject.delete(); try {
		 * fileObject.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
		 * }
		 */
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
		for (Integer key : patientInfo.keySet()) {
			System.out.print(patientInfo.get(key));

			String stringToWrite = String.valueOf(key) + ",";
			PatientForFile patient11 = patientInfo.get(key);

			stringToWrite += patient11.getPatientNames() + ",";
			stringToWrite += patient11.getPatientDiagnosis() + ",";
			stringToWrite += patient11.getPatientAge() + ",";
			stringToWrite += "\"" + String.join("-", patient11.getAddress());
			bufferedWriter.write(stringToWrite);
			bufferedWriter.newLine();

		}
		// bufferedWriter.write("welcome");
		bufferedWriter.flush();

		return true;
	}

	public boolean write(Map<Integer, PatientForFile> patientInfo1) throws IOException {

		String filePath = "C:\\Users\\CSS\\Desktop\\Data.csv";

		File fileObject = new File(filePath);
		System.out.println(fileObject);
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));

		for (Integer key : patientInfo1.keySet()) {

			String stringToWrite = String.valueOf(key) + ",";
			PatientForFile patient11 = patientInfo1.get(key);

			stringToWrite += patient11.getPatientNames() + ",";
			stringToWrite += patient11.getPatientDiagnosis() + ",";
			stringToWrite += patient11.getPatientAge() + ",";
			stringToWrite += "\"" + String.join("-", patient11.getAddress());
			bufferedWriter.write(stringToWrite);
			bufferedWriter.newLine();

		}
		bufferedWriter.flush();
		return true;
	}

}
