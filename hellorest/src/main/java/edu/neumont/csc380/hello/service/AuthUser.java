package edu.neumont.csc380.hello.service;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import edu.neumont.csc380.auth.Authorization.AuthorityLevel;

public class AuthUser {
	private int id;
	private AuthorityLevel authorityLevel;
	private Date expiry;
	
	
	public AuthUser(int id, AuthorityLevel authorityLevel,int expiryMinutes) {
		this.id = id;
		this.authorityLevel = authorityLevel;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE,expiryMinutes);
		this.expiry = cal.getTime();
	}
	
	public int getId() {
		return id;
	}
	public AuthorityLevel getAuthorityLevel() {
		return authorityLevel;
	}
	
	public boolean hasExpired()
	{
		Date now = new Date();
		return expiry.before(now);
	}
}
