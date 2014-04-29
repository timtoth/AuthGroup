package edu.neumont.csc380.auth.interfaces;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import edu.neumont.csc380.hello.service.AuthCredentialsV1;

@Path("/auth")
public interface IAuthService {
	@POST
	@Path("/authorize")
	@Produces("application/vnd.neumont.auth.edu-v1+json")
	@Consumes("application/vnd.neumont.auth.edu-v1+json")
	public Response authorizeUser(AuthCredentialsV1 streetCred);

	@Path("/updatePass")
	@Produces("application/vnd.neumont.auth.edu-v1+json")
	@Consumes("application/vnd.neumont.auth.edu-v1+json")
	public Response updateUserPassword();
	
	@DELETE
	@Path("/deleteUser")
	@Produces("application/vnd.neumont.auth.edu-v1+json")
	@Consumes("application/vnd.neumont.auth.edu-v1+json")
	public Response deleteUser(AuthCredentialsV1 streetCred);
	
	@POST
	@Path("/create")
	@Produces("application/vnd.neumont.auth.edu-v1+json")
	@Consumes("application/vnd.neumont.auth.edu-v1+json")
	public Response createUser(AuthCredentialsV1 streetCred);
}
