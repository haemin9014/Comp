/**
 *
 * This program copies files using a pipe.
 *
 * Usage:
 *	filecopy <input file> <output file>
 */

#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/time.h>

#define READ_END 0
#define WRITE_END 1
#define BUFFER_SIZE 25

int main(int argc, char *argv[]){
	int rv;
	pid_t pid;
	int c;
	char rb[BUFFER_SIZE], wb[BUFFER_SIZE];	/* bytes for reading/writing */
	/* file descriptor */
	int ffd[2];
	
	/* 
	if i forget, read 
	http://man7.org/linux/man-pages/man2/gettimeofday.2.html
	to remind this for final */
	
	//set up the time
	struct timeval stop, start;
	/* Step1: Create pipe descriptor */
	/* pipe */
	int fd[2];
	//check pipe, if pipe is negative one, it means pipe is failed
	if(pipe(fd) == -1){
		fprintf(stderr, "pipe failed");
		return 1;
	}

	/* open the input file */
	ffd[READ_END] = open(argv[1], O_RDONLY);
	
	if (ffd[READ_END] < 0) {
		fprintf(stderr,"Unable to open %s\n",argv[1]);
		return 1;
	}
	
	/* open the output file */
	ffd[1] = open(argv[2], O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);

	if (ffd[1] < 0) {
		fprintf(stderr,"Unable to open %s\n",argv[2]);

		/* close the input file */
		close(ffd[0]);

		return 1;
	}

	/* Step2: set up the pipe */
	/* make sure your program closes file handles*/
	//do pipe(fd) again to make sure
	pipe(fd);
	//fork it
	pid = fork();
	//if fork is < 0, it means fork failed
	if(pid < 0){
		fprintf(stderr, "fork failed! it printed out -1");
		return 1;
	}

	/* Step3: create the processes */

	/* read from the input file and write to the pipe */
	/*parent when pid is > 0*/
	if(pid > 0){
		//close pipe(0) which is read_end
		close(fd[READ_END]);
		//use while loop to read input file
		//int savedValue = read(ffd[READ_END], wb, BUFFER_SIZE);
		int savedValue = read(ffd[READ_END], wb, BUFFER_SIZE);
		while(savedValue > 0){

			if(write(fd[WRITE_END], wb, savedValue) < 0){				
				fprintf(stderr, "Error!(pipe)");			
				return 1;
			}
			savedValue = read(ffd[READ_END], wb, BUFFER_SIZE);
		}
		//if savedValue is less, it must be error
		if(savedValue < 0){
			//arvg[1] is input
			fprintf(stderr, "Error! in arvg[1](input) %s", argv[1]);
			return 1;			
		}
		//close input file
		close(ffd[READ_END]);
	}
	/* read from the pipe and write to the output file */
	/*child when pid == 0*/
	else{
		//start time
		gettimeofday(&start, NULL);
		//close pipe(1)
		close(fd[WRITE_END]);
		//set value
		//use while loop to write input file
		//int secondSavedValue = read(fd[READ_END], rb, BUFFER_SIZE);
		int secondSavedValue = read(fd[READ_END], rb, BUFFER_SIZE);
		//while loop helps to reading the test file, unitl there is no more to read
		while(secondSavedValue > 0){
			//it is writing to the out put file
			if(write(ffd[WRITE_END], rb, secondSavedValue) < 0){
				//arvg[2] is output
				fprintf(stderr, "Error! in arvg[2](output) %s", argv[2]);
				return 1;

			}
			secondSavedValue = read(fd[READ_END], rb, BUFFER_SIZE);
		}
		//if secondSavedValue is less, it must be error
		if(secondSavedValue < 0){
			fprintf(stderr, "Error!");
			return 1;
		}
		//close output file
		close(ffd[WRITE_END]);
		//end time
		gettimeofday(&stop, NULL);
		//we start from stop because
		fprintf(stderr, "Time: %lu ms \n", stop.tv_usec-start.tv_usec);
		//we start stop because stop will have bigger number, and start
		//will have smaller number
		/*
		u is a specifier meaning "unsigned decimal integer".
		l is a length modifier meaning "long".
		therefore, we are using microsecond, it will be better to use lu
		*/
	}

	return 1;
}
