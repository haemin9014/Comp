//Example code: A simple server side code, which echos back the received message. 
//Handle multiple socket connections with select and fd_set on Linux  

#include <stdio.h>  
#include <string.h>   //strlen  
#include <stdlib.h>  
#include <errno.h>  
#include <unistd.h>   //close  
#include <arpa/inet.h>    //close  
#include <sys/types.h>  
#include <sys/socket.h>  
#include <netinet/in.h>  
#include <sys/time.h> //FD_SET, FD_ISSET, FD_ZERO macros  
#include <sys/select.h>
#include <unistd.h>

//testing to get IP
#include <netdb.h>
#include <arpa/inet.h>

#define STDIN  0    
#define TRUE   1  
#define FALSE  0  
     
int main(int argc, char *argv[])   
{   
    int opt = TRUE;   
    int addrlen , new_socket , connection_socket[30] , max_clients = 30 , activity, i , valread , sd;   
    int max_sd;   
	int server_port = atoi(argv[1]);  //THIS IS THE PORT THE USER PROVIDED
	int socket_to_test = 0;
	int socket_receiving = 0;
	
	//initialise all connection_socket[] to 0 so not checked  
    for (i = 0; i < max_clients; i++)   
    {   
        connection_socket[i] = 0;   
    }   
    
    char buffer[256];  //data buffer of 256  
         
    fd_set readfds;     
        
    //create a master socket  
	int master_socket;
    if( (master_socket = socket(AF_INET , SOCK_STREAM , 0)) == 0)   
    {   
        perror("socket failed");   
        exit(EXIT_FAILURE);   
    }   
	
    
	/* ******* GETTING IP ADDRESS ********* */
	char hostbuffer[256]; 
	char *IPbuffer; 
	struct hostent *host_entry; 
	int hostname; 
  
	// To retrieve hostname 
	hostname = gethostname(hostbuffer, sizeof(hostbuffer)); 
  
	// To retrieve host information 
	host_entry = gethostbyname(hostbuffer); 
  
	// To convert an Internet network 
	// address into ASCII string 
	IPbuffer = inet_ntoa(*((struct in_addr*) 
						   host_entry->h_addr_list[0]));
	/*****************************************/
     
    //type of socket created  
	struct sockaddr_in address;
	
	bzero(&address, sizeof(address));
	
    address.sin_family = AF_INET;   //IPv4
    address.sin_addr.s_addr = INADDR_ANY;  //any address on this computer  
    address.sin_port = htons(server_port);    // port is provided by user
	
    //bind the master socket to users ip and chosen port  
    if (bind(master_socket, (struct sockaddr *)&address, sizeof(address))<0)   
    {   
        perror("bind failed");   
        exit(EXIT_FAILURE);   
    }   
    printf("Listener on port %d \n", server_port);   
	
	//try to specify maximum of 3 pending connections for the master socket
	if (listen(master_socket, 3) < 0)
	{
		perror("listen");
		exit(EXIT_FAILURE);
	}
    
	//accept the incoming connection  
	//size of address is used to accept new socket
	addrlen = sizeof(address);   
	puts("Waiting for connections ..."); 
	
	  
    while(TRUE)   
    {   
        //clear the socket set  
        FD_ZERO(&readfds);   
		
		//add STDIN for commands from user
		FD_SET(STDIN, &readfds);
		
        //add master socket to set  
        FD_SET(master_socket, &readfds);   
		//set current highest file descriptor number ( to be used in select() )
        max_sd = master_socket;
		
		//add child sockets to set  
        for ( i = 0 ; i < max_clients ; i++)   
        {   
            //socket descriptor  
            sd = connection_socket[i];   
                 
            //if valid socket descriptor then add to read list  
            if(sd > 0)   
                FD_SET( sd , &readfds);   
                 
            //highest file descriptor number, need it for the select function  
            if(sd > max_sd)   
                max_sd = sd;   
        } 
		
		
		//wait for an activity on one of the sockets, 
		activity = select(max_sd+1 , &readfds, NULL, NULL, NULL);
		if ((activity < 0) && (errno!=EINTR))
		{
			printf("select error");
		}

		/* **** CHECKING COMMAND LINE COMMANDS ***** */
		
		if (FD_ISSET(STDIN, &readfds))
		{
			int valread = read(STDIN_FILENO, buffer, sizeof(buffer));
			if (valread == 0)
			{
				printf("valread is 0, thus number of bytes should be zero\n");
			}
			else
			{
				int index;
				int count = 0;
				
				buffer[valread] = '\0';
				
				char* token = strtok(buffer, " ");
				while(token != NULL)  //TODO TODO TODO GET RID OF THIS WHILE LOOP, I THINK ITS UNNECESSARY!!!
				{   
					
					if ((strcmp(token, "help\n")) == 0) {
						
						//provide instructions on how to use program
						printf("************************************\n");
						printf("This is your Help, You're Welcome!\n\n");
                        printf("myip: it will print out your ip address careful!\n"); 
                        printf("\tIf you run in window using cygwin, vmware, and etc it will print out  VMware Network Adapter\n");
						printf("\tIn this case, please consult 'ipconfig' in your main Windows Command Prompt\n\n");
                        printf("myport: It will print out your server port number!\n\n");
                        printf("connect: plesase use following format...\n"); 
                        printf("\tconnect 'serverIPaddress' 'portnumber'\n");
                        printf("\tFor example: connect 172.0.0.1 8888\n\n");
                        printf("list: It will print out a list of your current connections, showing User id, IP address, and Port\n\n");
                        printf("terminate: Terminates a single connection.  Requires User id of connection you are terminating.\n");
                        printf("\tFor example: terminate 0\n\n");
                        printf("send: Send a message to a specific connection.  Requires User id of connection you are sending to.\n");
                        printf("\tFor example: send 0 Hello There\n\n");
                        printf("exit: Exits the program and closes all existing connections\n");
						printf("************************************\n\n");
						
						break;
					}
					else if ((strcmp(token, "myip\n")) == 0) {
						printf("My IP is: %s\n\n", IPbuffer);
						break;
					}
					else if ((strcmp(token, "myport\n")) == 0) {
						printf("\nMy Port Number is: %d\n\n", server_port);
						break;
					}
					else if ((strcmp(token, "connect")) == 0) {  
						
						int isDuplicate = 0;
						char* ip_address_connect;  //ADDRESS CONNECTING TO
						char* temp_port;
						int port_number_connect;   //PORT CONNECTING TO
						ip_address_connect = strtok(NULL, " ");  //ip address initiated as string
						//printf("\nYour connect to IP address is: %s", ip_address_connect);
						temp_port = strtok(NULL, " ");
						//printf("\nYour connect to Port Number is: %s", temp_port);
						port_number_connect = atoi(temp_port);  //port number initiated as int
						
						
						/****** CHECK LIST TO MAKE SURE THE CHOSEN IP ADDRESS OR PORT IS ALREADY IN ****/
						// cycle through connection list
						for (i = 0; i < max_clients; i++)   
						{   
							//if position is not empty  
							if( connection_socket[i] > 0 )   
							{  
								sd = connection_socket[i];
								getpeername(sd , (struct sockaddr*)&address , (socklen_t*)&addrlen);
								char* temp_string = inet_ntoa(address.sin_addr);
								int temp_int = ntohs(address.sin_port);
								
								if ( !(strcmp(ip_address_connect, temp_string)) && port_number_connect == temp_int ) 
								{
									printf("Your connection request already exists.\n\n");
									isDuplicate = 1;
								}
								else if (!(strcmp(ip_address_connect, temp_string))) {
									printf("ip address matched ONLY\n");
								}
								else if (port_number_connect == temp_int) {
									printf("port matched ONLY\n");
								}
							}
						}
						// check if connecting to yourself
						if ( !(strcmp(ip_address_connect, IPbuffer)) && port_number_connect == server_port)
						{
							printf("You cannot connect to yourself\n\n");
							isDuplicate = 1;
						}
						/*******************************************************************************/
						
						if ( !(isDuplicate) ) {
							//create a socket
							int network_socket;
							network_socket = socket(AF_INET, SOCK_STREAM, 0);
							//boolean flag for detecting if connection successful
							int isConnectSuccess = 0;
							
							//specify an address for the socket
							struct sockaddr_in server_address;
							server_address.sin_family = AF_INET;
							server_address.sin_port = htons(port_number_connect);
							server_address.sin_addr.s_addr = inet_addr(ip_address_connect);
							
							int connection_status = connect(network_socket, (struct sockaddr *) &server_address, sizeof(server_address));
							
							if (connection_status == -1) {
								printf("There was an error making a connection to the remote socket \n\n");
							}
							else {
								printf("Connection was successfull\n");
								isConnectSuccess = 1;
							}
							
							if ( isConnectSuccess ) {
								//add new socket to array of sockets  
								for (i = 0; i < max_clients; i++)   
								{   
									//if position is empty  
									if( connection_socket[i] == 0 )   
									{   
										connection_socket[i] = network_socket;   
										printf("Adding to list of sockets as %d\n\n" , i);   
											 
										break;   
									}   
								}
							}
						}
						
						break;
					}
					else if ((strcmp(token, "list\n")) == 0) {    
						
						printf("Here is your list of Connection Sockets:\n");
						int total_connections = 0;
						// cycle through connection list
						for (i = 0; i < max_clients; i++)   
						{   
							//if position is empty  
							if( connection_socket[i] > 0 )   
							{  
								total_connections++;
								if (total_connections == 1) //header for first connection only
									printf("id: IP address \t Port No.\n");
								sd = connection_socket[i];
								getpeername(sd , (struct sockaddr*)&address , (socklen_t*)&addrlen);
								printf(" %d: %s \t %d\n", i, inet_ntoa(address.sin_addr) , ntohs(address.sin_port));
							} 
						} 
						printf("\n");
						// inform user if there are no connections
						if(total_connections == 0) {
								printf("You have no connections currently.\n\n");
							}
						
						break;
					}
					else if ((strcmp(token, "terminate")) == 0) {
						
						char* temp_id;
                        int socket_to_terminate;
						int isValidConnection = 0;
						
						//find connection socket to terminate using id given
                        temp_id = strtok(NULL, " ");
                        int id_to_terminate = atoi(temp_id);
						
						// cycle through connection list
						for (i = 0; i < max_clients; i++)   
						{   
							if (id_to_terminate == i)
							{
								if (connection_socket[i] > 0)
								{
									socket_to_terminate = connection_socket[id_to_terminate];
									
									//inform user of terminated connection socket
									getpeername(socket_to_terminate , (struct sockaddr*)&address , (socklen_t*)&addrlen);
									printf("You chose to terminate number %d\n\tIP is : %s \n\tPORT is : %d \n\n", id_to_terminate, inet_ntoa(address.sin_addr), ntohs(address.sin_port));
									
									//close connection socket
									close(socket_to_terminate);
									connection_socket[id_to_terminate] = 0;
									isValidConnection = 1;
								}
							}
						}
						// if not a valid connection, then let user know
						if ( !(isValidConnection)) {
							printf("Not a valid connection to terminate.\n\n");
						}
						
						
						break;
					}
					else if ((strcmp(token, "send")) == 0) {  //TODO ERROR if invalid id provided (cycle through connection array; i==id && connection_socket[i]>0)
						
						//get connection socket id and message in correct format
						char* id_temp = strtok(NULL, " ");
						int id = atoi(id_temp);  // id : used for connection list (index of connection_socket array)
						char* input = strtok(NULL, " ");
						char messageArray[256] = "";  // messageArray : used for message we are snding
						int counter = 0;
						while(input != NULL) {
							strcat(messageArray, input);
							strcat(messageArray, " ");
							input = strtok(NULL, " ");
						}
						
						//before sending message, make sure that the id provided is valid 
						//	i.e.: (it is a valid connection_socket[] index && the value at that index is > 0, 
						//		indicating a socket occupies that element
						int isValidConn = 0;	//flag to act as boolean for determining if chosen id is valid
						for (i = 0; i < max_clients; i++)   
						{
							if (id == i)
							{
								if (connection_socket[i] > 0)
								{
									//create socket
									int socket_test = connection_socket[id];
									//send message
									send(socket_test , messageArray, strlen(messageArray) , 0);
									//clearing messageArray
									memset(messageArray, 0, sizeof(messageArray));
									printf("Message sent to %d\n\n", id);
									//set flag to true
									isValidConn = 1;
								}
							}
						}
						//if not a valid connection, let user know
						if ( !(isValidConn) ) {
							printf("Not a valid connection to send to\n\n");
						}
						
						break;
					}
					else if ((strcmp(token, "exit\n")) == 0) {
						printf("EXITING THE APPLICATION\n");
						
						//closing all connection sockets
						// cycle through connection list
						for (i = 0; i < max_clients; i++)   
						{   
							//if element in connection array is occupied by a connection socket
							if( connection_socket[i] > 0 )   
							{  
								sd = connection_socket[i];
								close(sd);
							}   
						}
						
						exit(0);
					}
					else {
						//incorrect input
						printf("incorrect input\n\n");
						break;
					}
					
				} //WHILE MENU LOOP
				
			}
			
		}
		
		//FOR THE LISTENING SOCKET
		if (FD_ISSET(master_socket, &readfds))
		{
			if ((new_socket = accept(master_socket,  
                    (struct sockaddr *)&address, (socklen_t*)&addrlen))<0)   
            {   
                perror("accept");   
                exit(EXIT_FAILURE);   
            }   
             
            //inform user of socket number - used in send and receive commands  
            printf("New connection , socket fd is %d , ip is : %s , port : %d \n" , new_socket , inet_ntoa(address.sin_addr) , ntohs(address.sin_port));   
            
            //add new socket to array of sockets  
            for (i = 0; i < max_clients; i++)   
            {   
                //if position is empty  
                if( connection_socket[i] == 0 )   
                {   
                    connection_socket[i] = new_socket;   
                    printf("Adding to list of sockets as %d\n\n" , i);   
                         
                    break;   
                }   
            } 
		}	//END of Listening Socket check
		
		
		for (i = 0; i < max_clients; i++)   
        {
			sd = connection_socket[i];
			if (FD_ISSET( sd , &readfds) && (sd > 0))
			{
				char buffer_recv[256];
				//receive message in 'buffer_recv' and 'rval' holds status
				int rval = recv(sd , buffer_recv , sizeof(buffer_recv) , 0 ); 				
				if (rval > 0)  //a message is being sent
				{
					int b_length = strlen(buffer_recv);
					
					//inform user of terminated connection socket
					getpeername(sd , (struct sockaddr*)&address , (socklen_t*)&addrlen);
					
					printf("Message received from %s\n", inet_ntoa(address.sin_addr));
					printf("Sender's Port: %d\n", ntohs(address.sin_port));
					printf("Message: %s \n", buffer_recv);
					memset(buffer_recv, 0, sizeof(buffer_recv));
				}
				else if (rval == 0)  //other client is disconnecting
				{
					//Somebody disconnected , get his details and print  
                    getpeername(sd , (struct sockaddr*)&address , 
                        (socklen_t*)&addrlen);   
                    printf("Host disconnected , ip %s , port %d \n\n" ,  
                          inet_ntoa(address.sin_addr) , ntohs(address.sin_port));   
                         
                    //Close the socket and mark as 0 in list for reuse  
                    close( sd );   
                    connection_socket[i] = 0;
					
				}
				else if (rval < 0)  //error in receiving
				{
					//error message
					printf("There was an ERROR in receiving!\n\n");
				}
			}
			
		}	//END of all Connection Sockets checked
		
		
    }  // infinite while loop   
         
    return 0;   
}   