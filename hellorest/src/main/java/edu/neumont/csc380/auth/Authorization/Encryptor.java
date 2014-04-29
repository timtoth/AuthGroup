package edu.neumont.csc380.auth.Authorization;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.ws.rs.core.Response;

import org.apache.cxf.common.util.Base64Exception;
import org.apache.cxf.common.util.Base64Utility;
import edu.neumont.csc380.exceptions.InvalidTokenException;
import edu.neumont.csc380.exceptions.UserDoesNotExistException;
import edu.neumont.csc380.hello.service.AuthTokenV1;
import edu.neumont.csc380.hello.service.AuthUser;

public class Encryptor {
	private final static String ID_STRING = "ID:";
	private final static String AUTH_STRING = ";AUTH:";
	
	public AuthTokenV1 encryptUser(AuthUser user,String message) throws IOException
	{
		int gioValue = calculateGioValue(message);
		String encode = Base64Utility.encode((ID_STRING + user.getId() + AUTH_STRING + user.getAuthorityLevel()).getBytes());
		AuthTokenV1 token = new AuthTokenV1(encode,gioValue,message);
		return token;
	}
	
	private int calculateGioValue(String message) throws IOException
	{
		final String IMAGE_PATH = "../Images/GioImage.jpg";

		String path = this.getClass().getResource(IMAGE_PATH).getPath();
		
		final BufferedImage gioImage = ImageIO.read(new File(path));
		int gioValue = message.length();
		gioValue %= gioImage.getHeight() * gioImage.getWidth();
		gioValue = gioImage.getRGB(gioValue / gioImage.getHeight(), gioValue % gioImage.getWidth());
		return gioValue;
	}
	
	public AuthUser DecryptCredentials(AuthTokenV1 token) throws InvalidTokenException, IOException, UserDoesNotExistException
	{
		checkGio(token.getGioValue(), token.getMessage());
		try {
			String decrypt = new String(Base64Utility.decode(token.getToken()),"UTF8");
			String[] values = decrypt.split(AUTH_STRING);
			if(values.length != 2)
			{
				throw new InvalidTokenException();
			}
			int id = Integer.parseInt(values[0].replace(ID_STRING, ""));
			AuthorityLevel authority = Enum.valueOf(AuthorityLevel.class, values[1]);
			if(authority.equals(AuthorityLevel.NotAUser))
			{
				throw new UserDoesNotExistException();
			}
			return new AuthUser(id, authority,token.getExpiryMinutes());
		} catch (UnsupportedEncodingException | Base64Exception | IllegalArgumentException e) {
			throw new InvalidTokenException();
		}
	}
	
	private void checkGio(int gio,String message) throws InvalidTokenException, IOException
	{
		if(gio != calculateGioValue(message))
		{
			throw new InvalidTokenException();
		}
	}
}
