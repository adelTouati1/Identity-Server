package server;

public interface ClientHandler  extends java.rmi.Remote{
	//Create new User

/**
 * --create <loginname> [<real name>] [--password <password>] 
 *  With this option, the client contacts the server and attempts to 
 *  create the new login name. The client optionally provides the real
 *   user name and password along with the request. In Java, we 
 *   can merely pass the user.name property as the user�s real name. Use 
 *   System.getProperty("user.name") to obtain this information. 
 *   If successful, the client prints an appropriate message with 
 *   the generated UUID for that account. Otherwise it returns an 
 *   appropriate error message.
 *   

 * @param userName
 */
	void CreateUser(String userName);
	
	/**
	 * --lookup <loginname>
	 * > With this option, the client connects with the server and looks
	 * up the loginname and displays all information found associated with 
	 * the login name (except for the encrypted password)
	 * @param userName
	 * @return
	 */
	User lookup(String userName);
	/**
	 * --reverse-lookup <UUID> 
	 * With this option, the client connects with the server and looks 
	 * up the UUID and displays all information found associated with 
	 * the UUID (except for the encrypted password)
	 */
	User reverseLookup(long UUID);
	
	/**
	 * --modify <oldloginname> <newloginname> [--password <password>] 
	 * The client contacts the server and requests a loginname change. If the new login name is available,
the server changes the name (note that the UUID does not ever change, once it has been
assigned). If the new login name is taken, then the server returns an error.
	 */
	void modify(String oldName, String newName);
	
	/**
	 * --delete <loginname> [--password <password>] 
	 * The client contacts the server and requests to delete 
	 * their loginname. The client must supply the correct 
	 * password for this operation to succeed.
	 */
	void deleteUser(String name);
	void getUser(String name, long UUID);
	
	void Test();
	
}
