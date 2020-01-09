package global.coda.hospitalmanagementsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.constants.ApplicationConstant;
import global.coda.hospitalmanagementsystem.models.User;

public class UserDaoImp implements UserDaoInterface {



	private static final Logger LOGGER = LoggerFactory.getLogger(PatientDaoImp.class);
	ResourceBundle mybundle = ResourceBundle.getBundle(ApplicationConstant.HMS1000D);

	@Override
	public boolean insert(User user) {
		PreparedStatement statement = null;
		int result1;
		boolean flag=false;
		String query = "INSERT into t_user(password,phone_number) VALUES (?,?) where username=?";
		Connection con = null;
		try {
			con = DriverManager.getConnection(mybundle.getString(ApplicationConstant.JDBCLOCATION),
					mybundle.getString(ApplicationConstant.JDBCUNAME),
					mybundle.getString(ApplicationConstant.JDBCPASS));
			statement = con.prepareStatement(query);
			statement.setString(2, user.getPhoneNumber());
			statement.setString(1, user.getPassword());
			statement.setString(3, user.getUserName());
			flag=true;
			result1 = statement.executeUpdate();

		} catch (SQLException e) {
			System.out.print(e);
		}
		//user update
		
		return flag;
	}
	
		


	@Override
	public boolean update(String userName, User user) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public User read(String username) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
