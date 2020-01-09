package global.coda.hospitalmanagementsystem.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import global.coda.hospitalmanagementsystem.constants.ApplicationConstant;
import global.coda.hospitalmanagementsystem.models.Login;
import global.coda.hospitalmanagementsystem.models.User;

public class AuthenticationDao {
	private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationDao.class);
	static ResourceBundle mybundle = ResourceBundle.getBundle(ApplicationConstant.HMS1000D);

	public User login(Login login) {
		String query = "SELECT * from t_user WHERE username=? AND password=? ";
		ResultSet result = null;
		boolean flag=false;
		User loginUser=new User();
		try {
			Connection con = DriverManager.getConnection(mybundle.getString(ApplicationConstant.JDBCLOCATION),
					mybundle.getString(ApplicationConstant.JDBCUNAME),
					mybundle.getString(ApplicationConstant.JDBCPASS));
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, login.getUserName());
			statement.setString(2, login.getPassword());
			result = statement.executeQuery();
//			loginUser.setName(result.getString(1));
//			loginUser.setPhoneNumber(result.getString(2));
//			loginUser.setRollId(result.getInt(3));
//			loginUser.setUserCity(result.getString(7));
//			loginUser.setUserState(result.getString(8));
//			loginUser.setUserCountry(result.getString(9));
			//System.out.println(loginUser);
			if(result.next())
			{
			loginUser.setUserName(result.getString(3));
			System.out.println(loginUser);
			loginUser.setPassword(result.getString(4));
			loginUser.setPhoneNumber(result.getString(8));
			
			flag=true;
			}
		} catch (SQLException e) {
			LOGGER.debug(e.toString());
		}
//		System.out.println(loginUser.getUserName());
//		System.out.println(loginUser.getPassword());
//		System.out.println(loginUser.getPhoneNumber());
		if(flag)
		{
		return loginUser;
		}
		else
		return null;

	}

}
