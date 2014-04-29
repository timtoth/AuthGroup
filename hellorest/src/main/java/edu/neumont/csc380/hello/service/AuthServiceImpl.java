package edu.neumont.csc380.hello.service;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import edu.neumont.csc380.auth.Authorization.AuthorityLevel;
import edu.neumont.csc380.auth.Authorization.Encryptor;
import edu.neumont.csc380.auth.interfaces.IAuthService;

@Service("authService")
public class AuthServiceImpl implements IAuthService {
private UserFactory userFactory = new UserFactory();
	
	public Response deleteUser(AuthCredentialsV1 streetCred) {
		userFactory.deleteUser(streetCred.getUserName());
		return Response.ok("{\"token\": \"1098as7dfasfdGIOas09fd\" }").build();
	}

	public Response createUser(AuthCredentialsV1 streetCred)
	{
		Response response = null;
			User u = new User();
			u.setAuthLevel(streetCred.getUpdatedAuthLevel());
			u.setPassword(streetCred.getPassword());
			u.setUsername(streetCred.getUserName());
			userFactory.createNewUser(u);
			AuthUser authUser = new AuthUser(u.getId(), u.getAuthLevel(),20);
			String message = "User " + u.getUsername() + " with the user id " + u.getId() + " and the authority level " + u.getAuthLevel() + " has been created";
			
			try {
				Encryptor encryptor = new Encryptor();
				response = Response.ok(encryptor.encryptUser(authUser, message)).build();
			} catch (Exception e) {
				response = Response.status(500).entity("There was a problem with the encryption of the user").build();
				e.printStackTrace();
			}
			finally
			{
				return response;
			}
	}
	
	public Response updateUserPassword() {
		// TODO Auto-generated method stub
		System.out.println("Updatin");
		return Response.status(200).entity(new User(1,"asdf","3",AuthorityLevel.User)).build();
	}

	@Override
	public Response authorizeUser(AuthCredentialsV1 streetCred) {
		// TODO Auto-generated method stub
		return null;
	}
}
