# Identity-Server

Implementation of a RMI based identity server. The basic idea is that client
can connect to this server and submit a new login name request. The server checks its database
and responds back either with a Universally Unique ID (UUID) if the new login id hasn’t been
taken by anyone before or returns back with an error. As part of the request to create a new login
name, the client submit a real user name as well. The server also permits reverse lookups,
where a client submits a UUID and ask for the login name and other information associated with
that UUID. The server periodically saves its state on to the disk so that it can survive crashes and
shutdowns.

#Server
```
java IdServer --numport <port#> and --verbose

```
The --verbose option makes
the server print detailed messages on the operations as it executes them.

#client
```
java IdClient --server <serverhost> [--numport <port#>] <query>

```
support six types of command line queries as follows:

- --create <loginname> [<real name>] [--password <password>] With this option,the client contacts the server and attempts to create the new login name. 
  The client optionally provides the real user name and password along with the request. In Java, we can merely pass the user.
  name property as the user’s real name. Use System.getProperty("user.name") to obtain this information. If successful, the
client prints an appropriate message with the generated UUID for that account. Otherwise it returns an appropriate error message.
  
- --lookup <loginname> With this option, the client connects with the server and looks
up the loginname and displays all information found associated with the login name
(except for the encrypted password).
- --reverse-lookup <UUID> With this option, the client connects with the server and looks
up the UUID and displays all information found associated with the UUID (except for
the encrypted password).
- --modify <oldloginname> <newloginname> [--password <password>] The client
contacts the server and requests a loginname change. If the new login name is available,
the server changes the name (note that the UUID does not ever change, once it has been
assigned). If the new login name is taken, then the server returns an error.
- --delete <loginname> [--password <password>] The client contacts the server and
requests to delete their loginname. The client must supply the correct password for this
operation to succeed.
- --get users|uuids|all The client contacts the server and obtains either a list all login
names, list of all UUIDs or a list of user, UUID and string description all accounts
(don’t show encrypted passwords).
The above options can be abbreviated as -s, -n, -c, -l, -r, -m, -d, -p (for password) and -g.
Note that we can supply only one query at a time.
  
## License
[MIT](https://choosealicense.com/licenses/mit/)
