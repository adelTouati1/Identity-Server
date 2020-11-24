package Client;

import java.rmi.registry.*;


import org.apache.commons.cli.*;

import server.ClientHandler;




public class IdClient {
	
	public static void main(String[] args) {
	//Not sure what to put instead of null	
	ClientHandler h = null;
		// TODO Auto-generated method stub
		if (args.length < 2) {
		   System.out.println("java IdClient --server <serverhost> [--numport <port#>] <query>");
		  }
		
		CommandLine commandLine;
        Option option_server = Option.builder("s")
            .required(true)
            .desc("server")
            .longOpt("server")
            .build();
        Option option_n = Option.builder("n")
            .required()
            .desc("n")
            .longOpt("n")
            .build();
        Option option_c = Option.builder("c")
            .required()
            .desc("create")
            .longOpt("create")
            .build();
        Option option_l = Option.builder("l")
        		.required()
                .desc("lookup")
                .longOpt("lookup")
                .build();
        Option option_r = Option.builder("r")
                .required()
                .desc("reverse-lookup")
                .longOpt("reverse-lookup")
                .build();
        Option option_m = Option.builder("m")
                .required()
                .valueSeparator(' ')
                .hasArgs()
                .desc("modify")
                .longOpt("modify")
                .build();
        Option option_d = Option.builder("d")
                .required()
                .desc("delete")
                .longOpt("delete")
                .build();
        Option option_g = Option.builder("g")
                .required()
                .desc("get")
                .longOpt("get")
                .build();
        Option option_p = Option.builder("p")
                .required()
                .desc("password")
                .longOpt("password")
                .build();
     
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();


        options.addOption(option_server);
        options.addOption(option_n);
        options.addOption(option_c);
        options.addOption(option_l);
        options.addOption(option_r);
        options.addOption(option_m);
        options.addOption(option_d);
        options.addOption(option_g);
        options.addOption(option_p);

        try
        {
            commandLine = parser.parse(options, args);

            if (commandLine.hasOption("server"))
            {
                System.out.print("Option server is present.  The value is: ");
                System.out.println(commandLine.getOptionValue("server"));
              
                
            }

            if (commandLine.hasOption("n"))
            {
                System.out.print("Option n is present.  The value is: ");
                System.out.println(commandLine.getOptionValue("n"));
                
            }

            if (commandLine.hasOption("c"))
            {
                System.out.print("Option c new loging name is present.  The value is: ");
                System.out.println(commandLine.getOptionValue("c"));
                //if getArgs not working we can change it to getOptionValue
                String[] userName = commandLine.getArgs();
                h.CreateUser(userName[1]);
            }
            if (commandLine.hasOption("l"))
            {
                System.out.print("Option l lookup is present.  The value is: ");
                System.out.println(commandLine.getOptionValue("l"));
                String[] name = commandLine.getArgs();
                h.lookup(name[1]);
            }
            if (commandLine.hasOption("r"))
            {
                System.out.print("Option r reverse-lookup is present.  The value is: ");
                System.out.println(commandLine.getOptionValue("r"));
                String[] UUID = commandLine.getArgs();
                h.reverseLookup(Long.parseLong(UUID[1]));
            }
            if (commandLine.hasOption("m"))
            {
                System.out.print("Option m modify is present.  The value is: ");
                System.out.println(commandLine.getOptionValue("m"));
                String[] name = commandLine.getArgs();
                h.modify(name[1], name[2]);
            }
            if (commandLine.hasOption("d"))
            {
                System.out.print("Option d delete is present.  The value is: ");
                System.out.println(commandLine.getOptionValue("d"));
                String[] name = commandLine.getArgs();
                h.deleteUser(name[1]);
            }
            if (commandLine.hasOption("g"))
            {
                System.out.print("Option g get is present.  The value is: ");
                System.out.println(commandLine.getOptionValue("g"));
                String[] name = commandLine.getArgs();
                if(name[1] == "users") {
                	//print all users 
                }else if(name[1] == "UUIDS"){
                	//print all UUIDS
                }else if(name[1] == "all") {
                	//print users and uuids
                }
              
            }
            if (commandLine.hasOption("p"))
            {
                System.out.print("Option p password is present.  The value is: ");
                System.out.println(commandLine.getOptionValue("p"));
            }


        }
        catch (ParseException exception)
        {
            System.out.print("Parse error: ");
            System.out.println(exception.getMessage());
        }
    
		
		try{
			Registry registry = LocateRegistry.getRegistry("localhost", 5141);
			//Registry registry = LocateRegistry.getRegistry(host, registryPort);
			ClientHandler stub = (ClientHandler) registry.lookup("IdServer");
			stub.Test();
		}catch(Exception e){
			
		}
		
	}

}
