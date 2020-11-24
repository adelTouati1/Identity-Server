package server;

import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class IdServer extends UnicastRemoteObject implements ClientHandler {
	private static int DEFAULTPORT = 5141;
	private static boolean debugMode = false;
	private static int port;
	private String name; 
	private Map<String, UUID> userNames; 
	private Map<UUID, User> userAccount;
	//rmiregistry 5141 &
	
	//export CLASSPATH=`pwd`:$CLASSPATH
	//rmiregistry 5141 &
	

	protected IdServer(String s) throws RemoteException {
		super();
		name = s;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 359415499962419496L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// [--PortNum <port>] [--verbose]
		if(args.length == 0){
			//use default port
			port = DEFAULTPORT;
		}
		else{	
			int i = 0;
			boolean useDefault = true;
				if(args[0].equalsIgnoreCase("--numport")){
					useDefault = false;
					port = Integer.parseInt(args[1]);
					
					i+=2; 
				}
				if(i < args.length){
					//ensure i doesn't go out of bounds
					if(args[i].equalsIgnoreCase("--numport")){
						debugMode = true;
					}
				}
				
				if(useDefault){
					port = DEFAULTPORT;
				}
		}
		
		//create registry
		try{
			
			IdServer server = new IdServer("//IdServer");
			
			//establish connection to registry
			Registry registry = LocateRegistry.getRegistry(port);
			registry.rebind("//localhost:" + port + "/IdServer", server);
		
		
		} catch (Exception e){
			System.out.println("Failed to bind to reistry.");
			System.out.println("please try: \"rmiregistry <port> &\" and try again");
		}
		
	}

	@Override
	public void CreateUser(String userName) {
		new UUID(0,0);
		// TODO Auto-generated method stub
		UUID uuid = UUID.randomUUID();
		userNames.put(userName, uuid);
		String ip;
		try {
			ip = getClientHost();
			Date now = new Date();
			//String currentDate = new SimpleDateFormat("ddMMyyyy HH:mm").format(now);
			User client = new User(userName,ip,now);
			userAccount.put(uuid,client);
		} catch (ServerNotActiveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public User lookup(String userName) {
		// TODO Auto-generated method stub
		UUID uuid = userNames.get(userName);
		User client = userAccount.get(uuid);
		return client;
	}

	@Override
	public User reverseLookup(long uuid) {
		// TODO Auto-generated method stub
		User client = userAccount.get(uuid);
		return client;
	}

	@Override
	public void modify(String oldName, String newName) {
		// TODO Auto-generated method stub
		UUID uuid = userNames.get(oldName);
		userNames.remove(oldName);
		userNames.put(newName,uuid);
		
		User client = userAccount.get(uuid);
		Date now = new Date();
		client.updateName(newName,now);

	}

	public void deleteUser(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Test() {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
	}

	@Override
	public void getUser(String name, long UUID) {
		// TODO Auto-generated method stub
		
	}

}
