package global.coda.hospitalmanagementsystem.services;

import global.coda.hospitalmanagementsystem.dao.AuthenticationDao;
import global.coda.hospitalmanagementsystem.models.Login;
import global.coda.hospitalmanagementsystem.models.User;

public class AuthenticationService {
	public User login(Login login)
	{


		User result=new User();
		AuthenticationDao auth=new AuthenticationDao();
		result=auth.login(login);
		//System.out.println(result.getUserName());
		return result;
		
	}

}
