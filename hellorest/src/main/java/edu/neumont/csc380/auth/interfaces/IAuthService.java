package edu.neumont.csc380.auth.interfaces;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Path("/auth")
public interface IAuthService {
	@POST
	@Path("/authorize")
	@Produces("application/json")
	@Consumes("application/json")
	String authorizeUser();
	
	@POST
	@Path("/updatePass")
	@Produces("application/json")
	@Consumes("application/json")
	String updateUserPassword();
	
	@POST
	@Path("/deleteUser")
	@Produces("application/json")
	@Consumes("application/json")
	String deleteUser();
	
	@GET
	@Path("/create")
	@Produces("application/json")
	String createUser();
	
	@GET
	@Path("/retrieve")
	@Produces("application/json")
	@Consumes("application/json")
	String retrieveUser();
	
}
