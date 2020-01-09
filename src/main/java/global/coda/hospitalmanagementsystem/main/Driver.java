package global.coda.hospitalmanagementsystem.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.constants.ApplicationConstant;
import global.coda.hospitalmanagementsystem.models.Login;
import global.coda.hospitalmanagementsystem.models.Patient;
import global.coda.hospitalmanagementsystem.models.User;
import global.coda.hospitalmanagementsystem.services.AuthenticationService;
import global.coda.hospitalmanagementsystem.services.PatientServices;
import global.coda.hospitalmanagementsystem.services.UserService;

public class Driver {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Driver.class);
	/*
	 * create logger for output stream
	 */
	ResourceBundle mybundle = ResourceBundle.getBundle(ApplicationConstant.HMS1000D);

	public Driver() {
		AuthenticationService authObj = null;
		PatientServices callObj = null;
		Utility roleObj = new Utility();
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		// LOGGER.info("Enter the Choice to Access Patient_Table");
		LOGGER.info(mybundle.getString(ApplicationConstant.LOGIN));
		Login login = new Login();
		LOGGER.info(mybundle.getString(ApplicationConstant.USERNAME));
		login.setUserName(sc.next());
		LOGGER.info(mybundle.getString(ApplicationConstant.PASSWORD));
		login.setPassword((sc.next()));
		authObj = new AuthenticationService();
		User result=new User();
		result = authObj.login(login);
		//System.out.print(result.getUserName());
		if (result!=null) {
			System.out.println(result.getUserName());
			System.out.println(result.getPassword());
			System.out.println(result.getPhoneNumber());
			LOGGER.info(mybundle.getString(ApplicationConstant.LOGINSUCCESS));
			//try
			UserService userService=new UserService();
			try {
				userService.insert(result);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			if (result.getRollId()==1) {
				LOGGER.info(mybundle.getString(ApplicationConstant.CreatePatient));
				LOGGER.info(mybundle.getString(ApplicationConstant.ReadAllPatient));
				LOGGER.info(mybundle.getString(ApplicationConstant.ReadPatient));
				LOGGER.info(mybundle.getString(ApplicationConstant.UpdatePatient));
				LOGGER.info(mybundle.getString(ApplicationConstant.DeletePatient));
				choice = sc.nextInt();
				while (true) {
					Patient patient;
					callObj = new PatientServices();
					switch (choice) {
					case 1:
						LOGGER.info(mybundle.getString(ApplicationConstant.userdetails));
						patient = new Patient();
						LOGGER.info(mybundle.getString(ApplicationConstant.Name));
						patient.setName(sc.next());
						LOGGER.info(mybundle.getString(ApplicationConstant.PhoneNumber));
						patient.setPhoneNumber(sc.next());
						LOGGER.info(mybundle.getString(ApplicationConstant.UserStreet));
						patient.setUserStreet(sc.next());
						LOGGER.info(mybundle.getString(ApplicationConstant.userCity));
						patient.setUserCity(sc.next());
						LOGGER.info(mybundle.getString(ApplicationConstant.userState));
						patient.setUserState(sc.next());
						LOGGER.info(mybundle.getString(ApplicationConstant.userRollId));
						patient.setRollId(sc.nextInt());
						LOGGER.info(mybundle.getString(ApplicationConstant.patientWeight));
						patient.setPatientWeight(sc.next());
						LOGGER.info(mybundle.getString(ApplicationConstant.PatinetDiagnosis));
						patient.setPatientDiagnosis(sc.next());
						LOGGER.info(mybundle.getString(ApplicationConstant.patientAge));
						patient.setPatientAge(sc.nextInt());
						LOGGER.info(mybundle.getString(ApplicationConstant.userName));
						patient.setUserName(sc.next());
						LOGGER.info(mybundle.getString(ApplicationConstant.userPassword));
						patient.setPassword(sc.next());
						roleObj.utilityFunctions(result.getRollId());
						try {
							callObj.insert(patient);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case 2:
						List<Patient> patientInfo = new ArrayList<>();
						try {
							patientInfo = callObj.readAll();
							for (int i = 0; i < patientInfo.size(); i++) {
								LOGGER.info(null, patientInfo.get(i));
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case 3:
						LOGGER.info("Enter the UserName");
						String userName = sc.next();
						try {
							callObj.read(userName);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case 4:
						patient = new Patient();
						LOGGER.info("Enter the USERNAME");
						String userName1 = "";
						userName1 = sc.next();
						patient.setPatientWeight(sc.next());
						LOGGER.info("Enter the patientWeight");
						patient.setPatientWeight(sc.next());
						LOGGER.info("Enter the patientDiagnosis");
						patient.setPatientDiagnosis(sc.next());
						LOGGER.info("Enter the patientDiagnosis");
						patient.setPatientAge(sc.nextInt());
						try {
							callObj.update(userName1, patient);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case 5:
						LOGGER.info("Enter the USERNAME");
						String userName11 = sc.next();
						try {
							boolean result1 = callObj.delete(userName11);
							if (result1) {
								LOGGER.info("Successfully uploaded ");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					default:
						break;
					}
				}

			} else if (result.getRollId()==3) {

				LOGGER.info("1.Create the Patient\n");
				LOGGER.info("2.ReadAll Patients\n");
				LOGGER.info("3.Read the Patient By User_Name\n");
				LOGGER.info("4.Update the Patient By User_Name\n");
				LOGGER.info("5.Delete the Patient By User_Name\n");
				while (true) {
//					System.out.println("Enter the Choice to Access Patient_Table");
//					System.out.println("1.Insert into table\n2.ReadAll details in the Table\n3.Read with PatientId\n4.Update the Table\n5.Delete the table");
					Patient patient;
					callObj = new PatientServices();
					LOGGER.info("Enter the USER_DETAILS:");

					switch (choice) {
					case 1:
						patient = new Patient();
						LOGGER.info("Enter the Name");
						patient.setName(sc.next());
						LOGGER.info("Enter the UserPhoneNumber");
						patient.setPhoneNumber(sc.next());
						LOGGER.info("Enter the UserStreet");
						patient.setUserStreet(sc.next());
						LOGGER.info("Enter the UserCity");
						patient.setUserCity(sc.next());
						LOGGER.info("Enter the UserState");
						patient.setUserState(sc.next());
						LOGGER.info("Enter the UserRollId");
						patient.setRollId(sc.nextInt());
						LOGGER.info("Enter the patientWeight");
						patient.setPatientWeight(sc.next());
						LOGGER.info("Enter the patientDiagnosis");
						patient.setPatientDiagnosis(sc.next());
						LOGGER.info("Enter the patientAge");
						patient.setPatientAge(sc.nextInt());
						LOGGER.info("Enter the UserName");
						patient.setUserName(sc.next());
						LOGGER.info("Enter the Password");
						patient.setPassword(sc.next());
						try {
							callObj.insert(patient);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case 2:
						List<Patient> patientInfo = new ArrayList<>();
						try {
							patientInfo = callObj.readAll();
							for (int i = 0; i < patientInfo.size(); i++) {
								LOGGER.info(null, patientInfo.get(i));
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case 3:
						LOGGER.info("Enter the UserName");
						String userName = sc.next();
						try {
							callObj.read(userName);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case 4:
						patient = new Patient();
						LOGGER.info("Enter the USERNAME");
						String userName1 = "";
						userName1 = sc.next();
						patient.setPatientWeight(sc.next());
						LOGGER.info("Enter the patientWeight");
						patient.setPatientWeight(sc.next());
						LOGGER.info("Enter the patientDiagnosis");
						patient.setPatientDiagnosis(sc.next());
						LOGGER.info("Enter the patientDiagnosis");
						patient.setPatientAge(sc.nextInt());
						try {
							callObj.update(userName1, patient);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case 5:
						LOGGER.info("Enter the patientId");
						String userName11 = sc.next();
						try {
							boolean result1 = callObj.delete(userName11);
							if (result1) {
								LOGGER.info("Successfully uploaded ");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					default:
						break;
					}
				}

			} else if (result.getRollId()==3) {

			}
			choice = sc.nextInt();
			while (true) {
//			System.out.println("Enter the Choice to Access Patient_Table");
//			System.out.println("1.Insert into table\n2.ReadAll details in the Table\n3.Read with PatientId\n4.Update the Table\n5.Delete the table");
				Patient patient;
				callObj = new PatientServices();
				switch (choice) {
				case 1:
					patient = new Patient();
					LOGGER.info("Enter the Name");
					patient.setName(sc.next());
					LOGGER.info("Enter the UserPhoneNumber");
					patient.setPhoneNumber(sc.next());
					LOGGER.info("Enter the UserStreet");
					patient.setUserStreet(sc.next());
					LOGGER.info("Enter the UserCity");
					patient.setUserCity(sc.next());
					LOGGER.info("Enter the UserState");
					patient.setUserState(sc.next());
					LOGGER.info("Enter the UserRollId");
					patient.setRollId(sc.nextInt());
					LOGGER.info("Enter the patientWeight");
					patient.setPatientWeight(sc.next());
					LOGGER.info("Enter the patientDiagnosis");
					patient.setPatientDiagnosis(sc.next());
					LOGGER.info("Enter the patientAge");
					patient.setPatientAge(sc.nextInt());
					LOGGER.info("Enter the UserName");
					patient.setUserName(sc.next());
					LOGGER.info("Enter the Password");
					patient.setPassword(sc.next());
					try {
						callObj.insert(patient);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 2:
					List<Patient> patientInfo = new ArrayList<>();
					try {
						patientInfo = callObj.readAll();
						for (int i = 0; i < patientInfo.size(); i++) {
							LOGGER.info(null, patientInfo.get(i));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 3:
					LOGGER.info("Enter the UserName");
					String userName = sc.next();
					try {
						callObj.read(userName);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 4:
					patient = new Patient();
					LOGGER.info("Enter the USERNAME");
					String userName1 = "";
					userName1 = sc.next();
					patient.setPatientWeight(sc.next());
					LOGGER.info("Enter the patientWeight");
					patient.setPatientWeight(sc.next());
					LOGGER.info("Enter the patientDiagnosis");
					patient.setPatientDiagnosis(sc.next());
					LOGGER.info("Enter the patientDiagnosis");
					patient.setPatientAge(sc.nextInt());
					try {
						callObj.update(userName1, patient);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 5:
					LOGGER.info("Enter the patientId");
					String userName11 = sc.next();
					try {
						boolean result1 = callObj.delete(userName11);
						if (result1) {
							LOGGER.info("Successfully uploaded ");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				default:
					break;
				}
			}

		} else {
			LOGGER.info("Invalid User");

			new Driver();
		}
	}
}