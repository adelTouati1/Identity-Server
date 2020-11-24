package server;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName; 
	private String realName; 
	private String password;
	//store date and time of last request
	private String LastRequest;
	//store date and time of each update to username
	private String LastUpdate;
	// last Access
	private String ipAccess;
	public User(String userName, String ip, Date now) {
		// TODO Auto-generated constructor stub
		this.userName = userName; 
		ipAccess = ip; 
		LastUpdate = new SimpleDateFormat("ddMMyyyy HH:mm").format(now);
		LastRequest = LastUpdate;
	}
	public void updateName(String newName, Date now) {
		// TODO Auto-generated method stub
		userName = newName;
		
		LastUpdate = new SimpleDateFormat("ddMMyyyy HH:mm").format(now);
		LastRequest = LastUpdate;
	}
}
