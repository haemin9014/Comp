// File 1, pid.h
// This header file is included in pid.c and test.c

#include <pthread.h>

#define PID_MIN  	300
#define PID_MAX 	350

/* mutex lock for accessing pid_map */
pthread_mutex_t mutex;
int pid_map[PID_MAX-PID_MIN];


//File 2, pid.c

#include "pid.h"
#include <pthread.h>
#include <stdio.h>

/**
 * Allocates the pid map.
 */

//Creates and initializes data structure for representing pids; returns -1 if unsuccessful, 1 if successful
int allocate_map(void){
	//we need to have array to count how many we have this will save at j
	int i;
	int number = PID_MAX - PID_MIN;
	//count the number
	for(i = 0; i < number; i++){
		pid_map[i] = 0;
	}
	return 1;
}

/**
 * Allocates a pid
 */
//Allocates and returns the smallest PID available; returns -1 if unable to allocate a PID (all pids are in use)
int allocate_pid(void){
	int k;
	int number2 = PID_MAX - PID_MIN;
	//something wrong fix it.
	//look for open spot
	for(k = 0; k < number2; k++){
		//if it is not open		
		if(pid_map[k] == 0){
			//returning smallest pid
			pid_map[k] = 1;
			return k;
		}
	}
	return -1;
}
/**
 * Releases a pid
 */
void release_pid(int pid){
	//release = mean kill ,so we just need to make all pid_map element to 0 
	pid_map[pid] = 0;
}


//File 3, test.c 
#include <pthread.h>
#include <unistd.h>
#include <stdio.h>
#include <time.h>
#include "pid.h"


#define NUM_THREADS 100
#define ITERATIONS	3
#define SLEEP		5

/**
 * mutex lock used when accessing data structure
 * to ensure there are no duplicate pid's in use.
 */
int used[PID_MAX +1];
int fail = 0;
pthread_mutex_t test_mutex;



//i need to allocate one by one i must lock
void *allocator(void *param){
//Each thread needs to acquire and release a PID for 3 times. 
	//allocate a pid

	//use for loop because professor wants us to release and get pid 3times.
	for(int o = 0; o < ITERATIONS; o++){
		pthread_mutex_lock(&test_mutex);
		int pid = allocate_pid();
		pthread_mutex_unlock(&test_mutex);


		//if it false returns -1
		if(pid == -1){
			//it failed
			// it will count how many times
			printf("**error pid is -1***\n");

			fail++;
		}
		else{
			//lock &test_mutex
			printf("allocate pid: %d\n", pid + PID_MIN);
			//unlock &test_mutex
			
			//turn in to sleap because getting the pid and releasing it			
			//search may this is wrong
			//lock &test_mutex
			sleep((int)(random()%SLEEP));
			pthread_mutex_lock(&test_mutex);

			//sleep
			//sleep((int)(random()%SLEEP));

			release_pid(pid);
			//unlock &test_mutex
			pthread_mutex_unlock(&test_mutex);

			printf("release_pid: %d\n", pid + PID_MIN);


		}
	}
}


int main(void){


	pthread_t tids[NUM_THREADS];

	if (allocate_map() == -1){
		return -1;
	}

	//Todo:
	for(int l = 0; l < NUM_THREADS; l++){
		pthread_create(&tids[l], NULL, allocator, NULL);
	}
	
	//initalize mutex
	pthread_mutex_init(&test_mutex, NULL);
	
	//wait for all of them are finished(tread) before print out failed of temp
	for(int n = 0; n < NUM_THREADS; n++){
		pthread_join(tids[n], NULL);
	}

	//
	//Todo:
	
	printf("%d times process can not obtain a PID", fail);
	printf("***DONE***\n");

	return 0;
}


//File 34, Makefile
all: testpid

testpid: test.o pid.o
	gcc  -o testpid test.o pid.o -lpthread

pid.o: pid.c pid.h
	gcc -c pid.c

test.o: test.c pid.h
	gcc -c test.c -lpthread
 