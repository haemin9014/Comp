//written by Daniel Cardenas and Haemin
//for comp429 Project 2

#include <stdio.h>
#include <string.h> //strlen
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>	//close
#include <arpa/inet.h> //close
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <sys/time.h> //FD_SET, FD_ISSET, FD_ZERO macros
#include <sys/select.h>
#include <stdbool.h>

//testing to get IP
#include <netdb.h>
#include <arpa/inet.h>

#include <pthread.h>
#include <time.h>

#define STDIN 0

bool opt = true;
bool crashed = false;
int addrlen, new_socket, connection_socket[30], max_clients = 30, activity, i, valread, sd;
int max_sd;
int server_port; //THIS IS THE PORT THE USER PROVIDED
int socket_to_test = 0;
int socket_receiving = 0;

//other variables
int myServID;
int numServers;
int numNeighbors;
char neighborIP[4][20];
int neighborPort[4];
int neighbor[4] = {-1, -1, -1, -1}; //if value is greater than 0, it is a neighbor
int neighborSocket[4] = {-1, -1, -1, -1};
//more variables
int array[4][4] = {
	{-1, -1, -1, -1},
	{-1, -1, -1, -1},
	{-1, -1, -1, -1},
	{-1, -1, -1, -1},
};
int *arrayPTR = &array[0][0];
int j;
int numPacketsReceived = 0;
int update_interval; //in seconds
int min;
int pathFound = 0; //flag for finding a path not infinite
int infinitePathFound = 0;
int value;
time_t lastTimeRecieved[4] = {0};

//function to display array matrix
void displayArray(int array[4][4])
{
	int i, j;
	printf("\n");
	printf("  |  1\t|   2\t|   3\t|   4\t|\n");
	printf("---------------------------------");
	for (i = 0; i < 4; i++)
	{
		printf("\n");
		if (i == 0)
			printf("1 | ");
		else if (i == 1)
			printf("2 | ");
		else if (i == 2)
			printf("3 | ");
		else if (i == 3)
			printf("4 | ");

		for (j = 0; j < 4; j++)
		{
			printf(" %d\t| ", array[i][j]);
		}
	}
	printf("\n");
}

void step(void)
{
	//Send distance vector to all neighbors
	int k;
	for (k = 0; k < 4; k++)
	{
		if (neighbor[k] > 0)
		{
			int socket_test = neighborSocket[k];
			char temp_a[256];
			char messageArray[50];
			sprintf(messageArray, "%d %d %d %d %d %s", array[myServID - 1][0], array[myServID - 1][1], array[myServID - 1][2], array[myServID - 1][3], myServID, "a");
			//send message
			send(socket_test, messageArray, 50, 0);
			//clearing messageArray
			memset(messageArray, 0, sizeof(messageArray));
			int temp = k + 1;
			printf("Message sent to Server %d\n\n", temp);
		}
	}
}

void disable(int toServer_ID)
{
	//check if toServer_ID is a valid neighbor
	if (neighbor[toServer_ID - 1] > 0)
	{
		printf("\nValid neighbor at serverID : %d\n", toServer_ID);

		//update own neighbor link costs AND update array at my server's row
		neighbor[toServer_ID - 1] = -1;
		array[myServID - 1][0] = neighbor[0];
		array[myServID - 1][1] = neighbor[1];
		array[myServID - 1][2] = neighbor[2];
		array[myServID - 1][3] = neighbor[3];

		//clear timer
		lastTimeRecieved[toServer_ID - 1] = -1;

		//run DV algorithm TODO
		for (i = 0; i < 4; i++)
		{ //i represents server we're checking distance to, server i+1
			pathFound = 0;
			if (i != (myServID - 1))
			{			  //every other server ** i represents destination server
				min = -1; //min = array[myServID-1][i];  //initialize the minimum to current value MAJOR TESTING MAJOR TESTING TODO TODO
				//cycle through every neighbor
				for (j = 0; j < 4; j++)
				{ //j represents server 1's neighbor (server (j+1))
					if (neighbor[j] > 0 && array[j][i] != -1)
					{ //if j is a neighbor server and that neighbors path to endpoint exists ; array[j][i] != -1
						//printf("\nServer %d is a neighbor\n", neighbor[j]);
						//printf("Cost from my Server %d to Server %d is %d\n", myServID, j+1, neighbor[j]);
						//printf("Cost from Server %d to destination Server %d is %d\n", j+1, i+1, array[j][i]);
						value = neighbor[j] + array[j][i];
						pathFound++;
						if (min == -1 || value < min)
						{ //minimum currently infinite or greater than new value
							min = value;
						}
					}
				}
				//min should be found for server (i+1), thus update array appropriately
				array[myServID - 1][i] = min;
				if (pathFound == 0 && neighbor[i] < 1) // && neighbor[i]<1)  //if no path found and server is not a neighbor
					array[myServID - 1][i] = -1;
			}
		}

		//send vector update to all neighbors AND send neighbor that is being disconnected an indication of disconnect
		int k;
		for (k = 0; k < 4; k++)
		{
			if (neighbor[k] > 0 || (k + 1 == toServer_ID))
			{ //if a neighbor OR the neighbor we disabled
				int socket_test = neighborSocket[k];
				char temp_a[256];
				char messageArray[50]; //{neighbor[0], neighbor[1], neighbor[2], neighbor[3], myServID};

				if (k + 1 == toServer_ID)
				{ //if neighbor is one we are disabling, let them know link is disabled
					sprintf(messageArray, "%d %d %d %d %d %s", array[myServID - 1][0], array[myServID - 1][1], array[myServID - 1][2], array[myServID - 1][3], myServID, "d");
				}
				else
				{
					sprintf(messageArray, "%d %d %d %d %d %s", array[myServID - 1][0], array[myServID - 1][1], array[myServID - 1][2], array[myServID - 1][3], myServID, "a");
				}

				//send message
				send(socket_test, messageArray, 50, 0);
				//clearing messageArray
				memset(messageArray, 0, sizeof(messageArray));
				int temp = k + 1;
				printf("Message sent to Server %d\n\n", temp);
			}
		}
	}
	else
	{
		printf("\nNot a valid neighbor\n");
	}
}

void *timer(void *vargp)
{
	int i;
	time_t lastUpdateTime;
	time_t currentTime;
	time(&lastUpdateTime);
	printf("Timer thread started\n");
	for (i = 0; i < 4; i++)
	{
		if (neighbor[i] < 0)
		{
			lastTimeRecieved[i] = -1;
		}
	}
	while (true)
	{
		// wait for 1 second
		sleep(1);
		
		// if the server is in a crash state, close the thread
		if(crashed){
			return 0;
		}
		time(&currentTime);
		if (currentTime - lastUpdateTime > update_interval)
		{
			// push updates to neighbors
			step();
			//printf("Time to Step\n");
			time(&lastUpdateTime);
		}

		for (i = 0; i < 4; i++)
		{
			if (lastTimeRecieved[i] >= 0 && ((currentTime - lastTimeRecieved[i]) > (update_interval * 3)))
			{
				lastTimeRecieved[i] = -1;
				printf("Server %d exceeded 3 message timeout\n", i + 1);
				if (neighbor[i] != -1)
				{
					disable(i + 1);
				}
			}
		}
	}
}

int main(int argc, char *argv[])
{
	pthread_t thread_id;
	server_port = atoi(argv[1]);

	//initialise all connection_socket[] to 0 so not checked
	for (i = 0; i < max_clients; i++)
	{
		connection_socket[i] = 0;
	}

	char buffer[256]; //data buffer of 256

	fd_set readfds;

	//create a master socket
	int master_socket;
	if ((master_socket = socket(AF_INET, SOCK_STREAM, 0)) == 0)
	{
		perror("socket failed");
		exit(EXIT_FAILURE);
	}

	//type of socket created
	struct sockaddr_in address;

	bzero(&address, sizeof(address));

	address.sin_family = AF_INET;		   //IPv4
	address.sin_addr.s_addr = INADDR_ANY;  //any address on this computer
	address.sin_port = htons(server_port); // port is provided by user

	//bind the master socket to users ip and chosen port
	if (bind(master_socket, (struct sockaddr *)&address, sizeof(address)) < 0)
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
	puts("Please enter 'server' command to establish connections.");

	while (true)
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
		for (i = 0; i < max_clients; i++)
		{
			//socket descriptor
			sd = connection_socket[i];

			//if valid socket descriptor then add to read list
			if (sd > 0)
				FD_SET(sd, &readfds);

			//highest file descriptor number, need it for the select function
			if (sd > max_sd)
				max_sd = sd;
		}

		//wait for an activity on one of the sockets,
		activity = select(max_sd + 1, &readfds, NULL, NULL, NULL);
		if ((activity < 0) && (errno != EINTR))
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

				char *token = strtok(buffer, " ");
				while (token != NULL)
				{

					if ((strcmp(token, "server")) == 0)
					{

						//PERFORM SERVER COMMAND
						FILE *fp = NULL;
						char *firstString = strtok(NULL, " ");
						char *fileName = strtok(NULL, " ");
						char *thirdString = strtok(NULL, " ");
						update_interval = atoi(strtok(NULL, " "));

						pthread_create(&thread_id, NULL, timer, NULL);

						printf("File Name: %s\n", fileName);
						printf("Routing Update Interval: %d\n", update_interval);

						fp = fopen(fileName, "r");
						if (fp == NULL)
						{
							printf("could not open!\n");
						}
						else
						{
							printf("File found and opened!\n");
							// fgets return a single line of text
							// example: fgets(string_buffer, 100, fp) != NULL
							char string_buffer[100];
							int line = 1;
							char *my_token;
							while (fgets(string_buffer, 100, fp) != NULL)
							{
								if (line == 1)
								{ //number of servers
									numServers = atoi(string_buffer);
									//printf("\nNumber of Servers: %d\n", numServers);
								}
								else if (line == 2)
								{ //number of neighbors/edges
									numNeighbors = atoi(string_buffer);
									//printf("Number of Neighbors: %d\n", numNeighbors);
								}
								else if (line == 3)
								{ //serverid 1, ip, port
									my_token = strtok(string_buffer, " ");
									int current_server = atoi(my_token) - 1;

									strcpy(neighborIP[current_server], strtok(NULL, " "));
									neighborPort[current_server] = atoi(strtok(NULL, " "));

									//printf("\nServer ID %d ; Neighbor IP : %s\n", current_server, neighborIP[current_server]);
									//printf("\nServer ID %d ; Neighbor Port : %d\n", current_server, neighborPort[current_server]);
								}
								else if (line == 4)
								{ //serverid 2, ip, port
									my_token = strtok(string_buffer, " ");
									int current_server = atoi(my_token) - 1;

									strcpy(neighborIP[current_server], strtok(NULL, " "));
									neighborPort[current_server] = atoi(strtok(NULL, " "));

									//printf("\nServer ID %d ; Neighbor IP : %s\n", current_server, neighborIP[current_server]);
									//printf("\nServer ID %d ; Neighbor Port : %d\n", current_server, neighborPort[current_server]);
								}
								else if (line == 5)
								{ //serverid 3, ip, port
									my_token = strtok(string_buffer, " ");
									int current_server = atoi(my_token) - 1;

									strcpy(neighborIP[current_server], strtok(NULL, " "));
									neighborPort[current_server] = atoi(strtok(NULL, " "));

									//printf("\nServer ID %d ; Neighbor IP : %s\n", current_server, neighborIP[current_server]);
									//printf("\nServer ID %d ; Neighbor Port : %d\n", current_server, neighborPort[current_server]);
								}
								else if (line == 6)
								{ //serverid 4, ip, port
									my_token = strtok(string_buffer, " ");
									int current_server = atoi(my_token) - 1;

									strcpy(neighborIP[current_server], strtok(NULL, " "));
									neighborPort[current_server] = atoi(strtok(NULL, " "));

									//printf("\nServer ID %d ; Neighbor IP : %s\n", current_server, neighborIP[current_server]);
									//printf("\nServer ID %d ; Neighbor Port : %d\n", current_server, neighborPort[current_server]);
								}
								else if (line == 7)
								{ //neighbor and cost (first)
									my_token = strtok(string_buffer, " ");
									myServID = atoi(my_token);
									int myNeighb = atoi(strtok(NULL, " "));
									neighbor[myServID - 1] = 0; //self server link cost of 0
									int cost = atoi(strtok(NULL, " "));
									neighbor[myNeighb - 1] = cost; //neighbor link cost set
																   //printf("from Server %d to Server %d cost %d\n", myServID, myNeighb, cost);
								}
								else if (line == 8)
								{ //neighbor and cost (second)
									my_token = strtok(string_buffer, " ");
									myServID = atoi(my_token);
									int myNeighb = atoi(strtok(NULL, " "));
									int cost = atoi(strtok(NULL, " "));
									neighbor[myNeighb - 1] = cost; //neighbor link cost set
																   //printf("from Server %d to Server %d cost %d\n", myServID, myNeighb, cost);
								}
								else if (line == 9)
								{ //neighbor and cost (third)
									my_token = strtok(string_buffer, " ");
									myServID = atoi(my_token);
									int myNeighb = atoi(strtok(NULL, " "));
									int cost = atoi(strtok(NULL, " "));
									neighbor[myNeighb - 1] = cost; //neighbor link cost set
																   //printf("from Server %d to Server %d cost %d\n", myServID, myNeighb, cost);
								}
								line++;
							}
						} // end of parsing text file

						//Connect to neighbors
						int k;
						for (k = 0; k < 4; k++)
						{
							if (neighbor[k] > 0)
							{
								//create a socket
								int network_socket;
								network_socket = socket(AF_INET, SOCK_STREAM, 0);
								//boolean flag for detecting if connection successful
								int isConnectSuccess = 0;

								//specify an address for the socket
								struct sockaddr_in server_address;
								server_address.sin_family = AF_INET;
								server_address.sin_port = htons(neighborPort[k]);
								server_address.sin_addr.s_addr = inet_addr(neighborIP[k]);

								int connection_status = connect(network_socket, (struct sockaddr *)&server_address, sizeof(server_address));

								if (connection_status == -1)
								{
									printf("There was an error making a connection to the remote socket \n\n");
								}
								else
								{
									printf("Connection was successfull\n");
									isConnectSuccess = 1;
								}

								if (isConnectSuccess)
								{
									//add new socket to array of sockets
									for (i = 0; i < max_clients; i++)
									{
										//if position is empty
										if (connection_socket[i] == 0)
										{
											connection_socket[i] = network_socket;
											neighborSocket[k] = network_socket;
											//printf("Adding to list of sockets as %d\n\n" , i);

											break;
										}
									}
								}

								//Send distance vector to neighbor
								int socket_test = neighborSocket[k];
								char temp_a[256];
								char messageArray[50]; //{neighbor[0], neighbor[1], neighbor[2], neighbor[3], myServID};
								sprintf(messageArray, "%d %d %d %d %d %s", neighbor[0], neighbor[1], neighbor[2], neighbor[3], myServID, "a");
								//send message
								send(socket_test, messageArray, 50, 0);
								//clearing messageArray
								memset(messageArray, 0, sizeof(messageArray));
								int temp = k + 1;
								printf("Message sent to Server %d\n\n", temp);

							} //end of specific neighbor

						} // end of connecting to neighbors

						//UPDATE array matrix at my server's row, with neighbor's link costs
						for (j = 0; j < 4; j++)
						{
							array[myServID - 1][j] = neighbor[j];
						}

						fclose(fp);

						break;
					}
					else if ((strcmp(token, "update")) == 0)
					{
						if(crashed){
							printf("Cannot update.\nThis server is in a crashed state.\n");
						}
						else{							
							//PERFORM UPDATE COMMAND
							//printf("Perform update command!\n");
							char *myServIDTemp = strtok(NULL, " ");
							char *toServIDTemp = strtok(NULL, " ");
							char *newValueTemp = strtok(NULL, " ");
							int mySID = atoi(myServIDTemp);
							int toSID = atoi(toServIDTemp);
							int newValue;
							if ((strcmp(newValueTemp, "inf")) == 0)
							{
								newValue = -1; //SAME AS DISABLING LINK TODO
							}
							else
							{
								newValue = atoi(newValueTemp);
							}

							//update neighbor[] value
							if (mySID != myServID || neighbor[toSID - 1] < 1)
							{
								break;
							}
							neighbor[toSID - 1] = newValue;

							//update my vector row in my array
							array[myServID - 1][0] = neighbor[0];
							array[myServID - 1][1] = neighbor[1];
							array[myServID - 1][2] = neighbor[2];
							array[myServID - 1][3] = neighbor[3];
							//update changed to vector's row element as well
							array[toSID - 1][myServID - 1] = newValue;

							//TESTING display current neighbor values
							printf("Here is the current nighbor values, where -1 indicates not a neighbor:\n");
							for (i = 0; i < 4; i++)
							{
								printf("\tServer %d cost : %d\n", i + 1, neighbor[i]);
							}

							//Not sure if I should run DV algorithm again here??

							//TESTING send message to all neighbors
							for (i = 0; i < 4; i++)
							{
								if (neighbor[i] > 0)
								{
									int socket_test = neighborSocket[i];
									char temp_a[256];
									char messageArray[50];
									sprintf(messageArray, "%d %d %d %d %d %s", array[myServID - 1][0], array[myServID - 1][1], array[myServID - 1][2], array[myServID - 1][3], myServID, "u");
									//send message
									send(socket_test, messageArray, 50, 0);
									//clearing messageArray
									memset(messageArray, 0, sizeof(messageArray));
									int temp = i + 1;
									printf("Message sent to Server %d\n\n", temp);
								}
							}
						}
						break;
					}
					else if ((strcmp(token, "step\n")) == 0)
					{
						if(crashed){
							
							printf("Cannot step.\nThis server is in a crashed state.\n");
						}
						else{
							step();
						}
						break;
					}
					else if ((strcmp(token, "packets\n")) == 0)
					{

						//DISPLAY NUMBER OF DISTANCE VECTOR (PACKETS) THIS SERVER HAS RECEIVED SINCE LAST INVOCATION
						printf("Number of Packets Received : %d\n", numPacketsReceived);
						numPacketsReceived = 0;

						break;
					}
					else if ((strcmp(token, "display\n")) == 0)
					{

						//PERFORM DISPLAY COMMAND
						displayArray(array);

						break;
					}
					else if ((strcmp(token, "disable")) == 0)
					{
						if(crashed){
							printf("Cannot disable.\nThis server is in a crashed state.\n");
						}
						else{
							char *toServer_char = strtok(NULL, " ");
							int toServer_ID = atoi(toServer_char);

							disable(toServer_ID);
						}
						break;
					}
					else if ((strcmp(token, "crash\n")) == 0)
					{
						if(crashed){
							printf("Cannot crash.\nThis server is in a crashed state.\n");
						}
						else{
							//Disable ALL neighbors
							int k;
							crashed = true;
							for (k = 0; k < 4; k++)
							{
								if (neighbor[k] > 0)
								{ //neighbor found
									//1. send message to all neighbors with my vector as all -1
									int socket_test = neighborSocket[k];
									char temp_a[256];
									char messageArray[50];

									sprintf(messageArray, "%d %d %d %d %d %s", -1, -1, -1, -1, myServID, "c");

									//send message
									send(socket_test, messageArray, 50, 0);
									//clearing messageArray
									memset(messageArray, 0, sizeof(messageArray));
									int temp = k + 1;
									printf("Message sent to Server %d\n\n", temp);
									//2. set my own array to all -1
									array[myServID - 1][0] = -1;
									array[myServID - 1][1] = -1;
									array[myServID - 1][2] = -1;
									array[myServID - 1][3] = -1;
								}
							}
						}
						break;
					}
					else
					{
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
									 (struct sockaddr *)&address, (socklen_t *)&addrlen)) < 0)
			{
				perror("accept");
				exit(EXIT_FAILURE);
			}

			//inform user of socket number - used in send and receive commands
			printf("New connection , socket fd is %d , ip is : %s , port : %d \n", new_socket, inet_ntoa(address.sin_addr), ntohs(address.sin_port));

			//add new socket to array of sockets
			for (i = 0; i < max_clients; i++)
			{
				//if position is empty
				if (connection_socket[i] == 0)
				{
					connection_socket[i] = new_socket;
					printf("Adding to list of sockets as %d\n\n", i);

					break;
				}
			}
		} //END of Listening Socket check

		int isFound = 0;
		for (i = 0; i < max_clients; i++)
		{
			if (isFound == 1)
				break;

			sd = connection_socket[i];
			if (FD_ISSET(sd, &readfds) && (sd > 0))
			{
				isFound = 1;
				char buffer_recv[256];
				//receive message in 'buffer_recv' and 'rval' holds status
				int rval = recv(sd, buffer_recv, sizeof(buffer_recv), 0);
				if (rval > 0) //a message is being sent
				{
					int b_length = strlen(buffer_recv);

					//get the sockets information and display it
					getpeername(sd, (struct sockaddr *)&address, (socklen_t *)&addrlen);

					printf("Message received from %s\n", inet_ntoa(address.sin_addr));
					printf("Sender's Port: %d\n", ntohs(address.sin_port));
					printf("Message: %s \n", buffer_recv);

					//update appropriate distance vector
					char *msg_token = strtok(buffer_recv, " ");
					int serv1 = atoi(msg_token);
					int serv2 = atoi(strtok(NULL, " "));
					int serv3 = atoi(strtok(NULL, " "));
					int serv4 = atoi(strtok(NULL, " "));
					int fromServ = atoi(strtok(NULL, " "));

					time(&lastTimeRecieved[fromServ - 1]);

					//will pull a "d" for disabled, "c" for crash
					char *check_if_disabled = strtok(NULL, " ");
					//((strcmp(token, "exit\n")) == 0)
					if ((strcmp(check_if_disabled, "d")) == 0)
					{ //neighbors link has been disabled
						printf("\nThe link has been DISABLED from Server %d\n, updating my vector\n", fromServ);
						//update disabled neighbor link cost AND update array at my server's row
						neighbor[fromServ - 1] = -1;
						array[myServID - 1][0] = neighbor[0];
						array[myServID - 1][1] = neighbor[1];
						array[myServID - 1][2] = neighbor[2];
						array[myServID - 1][3] = neighbor[3];
						printf("neighbor: %d %d %d %d \n", neighbor[0], neighbor[1], neighbor[2], neighbor[3]);
					}
					else if ((strcmp(check_if_disabled, "c")) == 0)
					{ //neighbors link crashed
						//update column to all -1, i
						printf("\nThey have Crashed at Server %d\n, updating my vector\n", fromServ);
						//update disabled neighbor link cost AND update array at my server's row
						neighbor[fromServ - 1] = -1;
						array[myServID - 1][0] = neighbor[0];
						array[myServID - 1][1] = neighbor[1];
						array[myServID - 1][2] = neighbor[2];
						array[myServID - 1][3] = neighbor[3];
						//update array column for that server (fromServ)
						array[0][fromServ - 1] = -1;
						array[1][fromServ - 1] = -1;
						array[2][fromServ - 1] = -1;
						array[3][fromServ - 1] = -1;
						step();
					}
					else if ((strcmp(check_if_disabled, "u")) == 0)
					{ //neighbors link updated

						//temp fromServ vector
						int tempFromServ[4] = {serv1, serv2, serv3, serv4};
						// printf("serv: %d %d %d %d \n", serv1, serv2, serv3, serv4);
						//update neighbor[] to reflect new link cost
						for (i = 0; i < 4; i++)
						{
							if (myServID - 1 == i)
							{
								neighbor[fromServ - 1] = tempFromServ[i]; //value from other server to me
							}
						}
						//FOR TESTING display updated neighbor costs
						printf("Here is the current neighbor values, where -1 indicates not a neighbor:\n");
						for (i = 0; i < 4; i++)
						{
							printf("\tServer %d cost : %d\n", i + 1, neighbor[i]);
						}

						//update my vector row in my array
						array[myServID - 1][0] = neighbor[0];
						array[myServID - 1][1] = neighbor[1];
						array[myServID - 1][2] = neighbor[2];
						array[myServID - 1][3] = neighbor[3];
						//update received from vector's row in my array
						array[fromServ - 1][myServID - 1] = neighbor[fromServ - 1];
					}

					//update appropriate distance vector
					printf("Distance Vector : %d, %d, %d, %d\n", serv1, serv2, serv3, serv4);
					printf("From Server : %d\n", fromServ);
					array[fromServ - 1][0] = serv1;
					array[fromServ - 1][1] = serv2;
					array[fromServ - 1][2] = serv3;
					array[fromServ - 1][3] = serv4;

					//MAJOR TESTING MAJOR TESTING TESTING
					printf("Display PRIOR to running algorithm\n");
					displayArray(array);
					printf("Display AFTER running algorithm\n");

					//Perform DV algorithm TODO
					for (i = 0; i < 4; i++)
					{ //i represents server we're checking distance to, server i+1
						pathFound = 0;
						if (i != (myServID - 1))
						{			  //every other server ** i represents destination server
							min = -1; //min = array[myServID-1][i];  //initialize the minimum to current value MAJOR TESTING MAJOR TESTING TODO TODO
							//cycle through every neighbor
							for (j = 0; j < 4; j++)
							{ //j represents server 1's neighbor (server (j+1))
								if (neighbor[j] > 0 && array[j][i] != -1)
								{ //if j is a neighbor server and that neighbors path to endpoint exists ; array[j][i] != -1
									//printf("\nServer %d is a neighbor\n", neighbor[j]);
									//printf("Cost from my Server %d to Server %d is %d\n", myServID, j+1, neighbor[j]);
									//printf("Cost from Server %d to destination Server %d is %d\n", j+1, i+1, array[j][i]);
									value = neighbor[j] + array[j][i];
									pathFound++;
									if (min == -1 || value < min)
									{ //minimum currently infinite or greater than new value
										min = value;
									}
								}
							}
							//min should be found for server (i+1), thus update array appropriately
							array[myServID - 1][i] = min;
							if (pathFound == 0 && neighbor[i] < 1) // && neighbor[i]<1)  //if no path found and server is not a neighbor
								array[myServID - 1][i] = -1;
						}
					}
					displayArray(array);

					memset(buffer_recv, 0, sizeof(buffer_recv));

					//Counter for packets received
					numPacketsReceived++;
				}
				else if (rval == 0) //other client is disconnecting
				{
					//Somebody disconnected , get his details and print
					getpeername(sd, (struct sockaddr *)&address,
								(socklen_t *)&addrlen);
					printf("Host disconnected , ip %s , port %d \n\n",
						   inet_ntoa(address.sin_addr), ntohs(address.sin_port));

					//Close the socket and mark as 0 in list for reuse
					close(sd);
					connection_socket[i] = 0;
				}
				else if (rval < 0) //error in receiving
				{
					//error message
					printf("There was an ERROR in receiving!\n\n");
				}
			}

		} //END of all Connection Sockets checked

	} // infinite while loop

	return 0;
}