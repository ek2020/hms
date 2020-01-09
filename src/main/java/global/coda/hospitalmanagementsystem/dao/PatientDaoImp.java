package global.coda.hospitalmanagementsystem.dao;

//import java.beans.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import global.coda.hospitalmanagementsystem.constants.ApplicationConstant;
import global.coda.hospitalmanagementsystem.models.Patient;

//import com.sun.jdi.connect.spi.Connection;

public class PatientDaoImp implements PatientDaoInterface {
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientDaoImp.class);
	ResourceBundle mybundle = ResourceBundle.getBundle(ApplicationConstant.HMS1000D);

	@Override
	public boolean insert(Patient patient) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement statement = null;
		PreparedStatement statement1 = null;
		ResultSet result1 = null;
		String query = "INSERT into t_user(user_name,password,phonenumber) VALUES (?,?,?)";
		Connection con = null;
		try {
			con = DriverManager.getConnection(mybundle.getString(ApplicationConstant.JDBCLOCATION),
					mybundle.getString(ApplicationConstant.JDBCUNAME),
					mybundle.getString(ApplicationConstant.JDBCPASS));
			statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			//statement.setString(1, patient.getUserStreet());
			statement.setString(8, patient.getPhoneNumber());
//			statement.setInt(3, patient.getRollId());
//			statement.setString(4, patient.getUserCity());
//			statement.setString(5, patient.getUserState());
//			statement.setString(6, patient.getUserStreet());
//			statement.setString(7, patient.getName());
			statement.setString(3, patient.getUserName());
			statement.setString(4, patient.getPassword());

			
			result1 = statement.executeQuery();

		} catch (SQLException e) {
			System.out.print(e);
		}
/**		int generatedKey = 0;
		try {
			if (result1.next()) {
				try {
					generatedKey = result1.getInt(1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String query1 = "INSERT into t_patient(patient_weight,patient_diagnosis,patient_age,fk_user_id,fk_role_id) VALUES (?,?,?,?,?)";
		try {
			statement1 = con.prepareStatement(query1);
			statement1.setString(1, patient.getPatientWeight());
			statement1.setString(2, patient.getPatientDiagnosis());
			statement1.setInt(3, patient.getPatientAge());
			statement1.setInt(4, result1.getInt(0));
			statement1.setInt(5, patient.getRollId());
			result = statement1.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		**/
		//user update
		boolean res=false;
		try {
			if (result1.next())
				res=true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return res;
	
		

	}

	@Override
	public Patient read(String username) {

		ResultSet result = null;
		Patient patient = null;
		Connection con = null;
		try {
			con = DriverManager.getConnection(mybundle.getString(ApplicationConstant.JDBCLOCATION),
					mybundle.getString(ApplicationConstant.JDBCUNAME),
					mybundle.getString(ApplicationConstant.JDBCPASS));
			String patientDiagnosis = "", patientWieght = "";
			int patientAge = 0;
			String query1 = "SELECT patient_weight,patient_diagnosis,patient_age from t_patient WHERE fk_user_id=(SELECT pk_user_id from t_user WHERE user_name=? AND is_active=1) AND is_active=1 ";
			PreparedStatement statement1 = null;
			statement1 = con.prepareStatement(query1);
			statement1.setString(1, username);
			result = statement1.executeQuery();
			patientWieght = result.getString(1);
			patientDiagnosis = result.getString(2);
			patientAge = result.getInt(3);
			patient = new Patient(patientWieght, patientDiagnosis, patientAge);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return patient;

	}

	@Override
	public List<Patient> readAll() throws SQLException {
		// TODO Auto-generated method stub
		String query = "SELECT patient_weight,patient_diagnosis,patient_age from t_patient";
		Connection con = DriverManager.getConnection(mybundle.getString(ApplicationConstant.JDBCLOCATION));
		PreparedStatement statement = con.prepareStatement(query);
		ResultSet result = statement.executeQuery();
		String patientDiagnosis = "", patientWieght = "";
		int patientAge;
		List<Patient> patientInfo = new ArrayList<>();
		while (result.next()) {
			if (result.getBoolean(5)) {
				patientWieght = result.getString(1);
				patientDiagnosis = result.getString(2);
				patientAge = result.getInt(3);
				patientInfo.add(new Patient(patientWieght, patientDiagnosis, patientAge));
			}
		}
		con.close();
		return patientInfo;
	}

	@Override
	public boolean update(String userName, Patient patient) throws SQLException {

		String query = "UPDATE t_patient SET patient_weight=?,patient_diagnosis=?,patient_age=?  WHERE user_name=? AND is_active=1";
		Connection con = DriverManager.getConnection(mybundle.getString(ApplicationConstant.JDBCLOCATION));
		PreparedStatement statement = con.prepareStatement(query);
		statement.setString(1, patient.getPatientWeight());
		statement.setString(2, patient.getPatientDiagnosis());
		statement.setInt(3, patient.getPatientAge());
		statement.setString(4, userName);
		boolean result = false;
		result = statement.executeUpdate() > 0;
		con.close();
		return result;
	}

	@Override
	public boolean delete(String userName) throws SQLException {
		ResultSet result = null;
		String query = "SELECT is_active from t_patient WHERE user_name=? and is_active=1";
		Connection con = DriverManager.getConnection(mybundle.getString(ApplicationConstant.JDBCLOCATION),
				mybundle.getString(ApplicationConstant.JDBCUNAME), mybundle.getString(ApplicationConstant.JDBCPASS));
		PreparedStatement statement = con.prepareStatement(query);
		statement.setString(1, userName);
		result = statement.executeQuery();
		boolean result1 = false;
		if (result != null) {
			String query1 = "UPDATE t_patient SET is_active=0  WHERE user_name=?";
			Connection con1 = DriverManager.getConnection(mybundle.getString(ApplicationConstant.JDBCLOCATION));
			PreparedStatement statement1 = con1.prepareStatement(query);
			statement.setString(1, userName);
			result1 = statement.executeUpdate() > 0;
			con1.close();

		}
		con.close();
		return result1;
	}

	public PatientDaoImp() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public static void main(String args[]) throws SQLException{  
//		Connection con;
//		ResultSet rs;
//		try{  
//		con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hms_db","root","admin");  
//		PreparedStatement statement=con.prepareStatement("select * from t_user");
//		rs=statement.executeQuery();
//		while(rs.next()) { 
//		String s = rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);
//		System.out.println(s);
//		
//		}
//		//con.close();
//		}
//		catch(Exception e)
//		{ System.out.println(e);
//		} 
//		finally{
//			
//			rs.close();
//			
//		}
//		}  

}
