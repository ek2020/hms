package global.coda.hospitalmanagementsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.constants.ApplicationConstant;
import global.coda.hospitalmanagementsystem.models.Doctor;
import global.coda.hospitalmanagementsystem.models.Patient;

public class DoctorDaoImp implements DoctorDaoInterface{
	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorDaoImp.class);
	ResourceBundle mybundle = ResourceBundle.getBundle(ApplicationConstant.HMS1000D);

	@Override
	public List<Doctor> readAll() throws SQLException {
		// TODO Auto-generated method stub
		String query = "SELECT t_user.name,t_doctor.doctor_salary,t_doctor.specialist FROM t_user,t_doctor WHERE  t_user.fk_role_id =t_doctor.pk_doctor_id and t_user.is_active_user=1 and t_doctor.is_active=1";
		Connection con = DriverManager.getConnection(mybundle.getString(ApplicationConstant.JDBCLOCATION));
		PreparedStatement statement = con.prepareStatement(query);
		ResultSet result = statement.executeQuery();
		String doctorName;
		String doctorSalary;
		String doctorSpecialist;
		List<Doctor> doctorInfo = new ArrayList<>();
		while (result.next()) {
			doctorName = result.getString(1);
			doctorSalary = result.getString(2);
			doctorSpecialist = result.getString(3);
			doctorInfo.add(new Doctor(doctorName,doctorSalary,doctorSpecialist));
		}
		LOGGER.info("SUCCESSFULLY READED THE PATIENT DATA");
		return doctorInfo;
	}

	@Override
	public boolean insert(Doctor doctor) {
		// TODO Auto-generated method stub
		int result =0;
		PreparedStatement statement = null;
		PreparedStatement statement1 = null;
		ResultSet result1 = null;
		String query = "INSERT into t_user(name,user_phone_number,fk_role_id,user_street,user_city,use_state,user_country,password) VALUES (?,?,?,?,?,?,?,?)";
		Connection con = null;
		try {
			con = DriverManager.getConnection(mybundle.getString(ApplicationConstant.JDBCLOCATION),
					mybundle.getString(ApplicationConstant.JDBCUNAME),
					mybundle.getString(ApplicationConstant.JDBCPASS));
			statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, doctor.getUserStreet());
			statement.setString(2, doctor.getPhoneNumber());
			statement.setInt(3, doctor.getRollId());
			statement.setString(4, doctor.getUserCity());
			statement.setString(5, doctor.getUserState());
			statement.setString(6, doctor.getUserStreet());
			statement.setString(7, doctor.getName());
			statement.setString(8, doctor.getPassword());
			
			statement.executeUpdate();
			result1 = statement.getGeneratedKeys();

		} catch (SQLException e) {
			System.out.print(e);
		}
		int generatedKey = 0;
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
		String query1 = "INSERT into t_doctor() VALUES (?,?,?,?,?)";
		try {
			statement1 = con.prepareStatement(query1);
			statement1.setString(1, doctor.getDoctorName());
			statement1.setString(2, doctor.getDoctorSalary());
			statement1.setString(3, doctor.getDoctorSpecialist());
			statement1.setInt(4, generatedKey);
			statement1.setInt(5, doctor.getRollId());
			result = statement1.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Patient read(String username) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(String userName, Doctor doctor) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String userName) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
