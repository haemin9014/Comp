//haemin lee
//comp 222

#include <stdio.h>
#include <stdlib.h>
int mm_size;
int cache_size;
int block_size;
int num_lines;

struct node{
    int tag;
    int *block;
} *cache = NULL;

typedef struct node line;

int *mm = NULL;


void params(){
    //enter this method by typing 1
    int i;
    int j;
   //enter for main memory size, cache size, block size
    //entered 65536
    printf("Enter main memory size: ");
    scanf("%d", &mm_size);
    //entered 1024
    printf("Enter cache size: ");
    scanf("%d", &cache_size);
    //entered 16
    printf("Enter block size: ");
    scanf("%d", &block_size);

    /* allocate and initialize main memory--value at index i = size of main memory-i*/
    mm = (int *)malloc(mm_size * sizeof(int));
    for(i = 0; i <mm_size; i++){
        mm[i] = mm_size -i;
    }
    /* allocate memory for cache based on number of lines in cache*/
    num_lines = cache_size/block_size;

    cache = (line *)malloc(num_lines * sizeof(line));
    /* initialize each tag to -1 and each block to NULL */
    for(j = 0; j < num_lines; j++){
        cache[j].block = NULL;

        cache[j].tag = -1;
    }
    return;
}

void access_cache(){
//enter here by typing 2
/* declare local var's */
int read_write;
int mm_addr;
int data;
int tag;
int block;
int word;
int base;
int k;
/* Prompt for read/write signal */
    //enter 1
    printf("Enter read/write signal: ");
    scanf("%d", &read_write);
/* Prompt for main memory address to read/write */
    //enter 65535
/* Prompt for main memory address to read/write */
    if(read_write == 0 || read_write == 1){
        //enter 14
        printf("Access cache for reading/writing: ");
        scanf("%d", &mm_addr);
    }
    /*if write, prompt for data to write*/
    if(read_write == 1){
        printf("Enter value to write:: ");
        scanf("%d", &data);

    }
    //it just end after here!!

    /* Translate main mem addr to cache addr fields*/
    //k = 1
    base = (mm_addr/block_size)*block_size;
    block = (mm_addr%(cache_size/1))/block_size;//use set formula
    tag = mm_addr /(cache_size/1);
    word = mm_addr%block_size;//calculated
    //k(set associativity) is 1
/* Case 1: MISS--BLOCK NOT ALLOCATED */
	if(cache[block].tag == -1){
        /* Allocate cache block based on block size */
		cache[block].block = (int*)malloc(block_size*sizeof(int));
	}

/* Case 2: MISS--NON-MATCHING TAGS */
	if(cache[block].tag != mm_addr /(cache_size/1))	{
		/* Print message of Read/Write miss */
        printf("Write is missed!");
		/* Overwrite tag */
        //put int tag value again.
        //cache size /set associativity
		cache[block].tag = mm_addr /(cache_size/1);

        /* Transfer equivalent block of main memory to cache--one word at a time */
		for(k = 0; k <block_size; k++){
            cache[block].block[k] = mm[base + k];
		}
	}

/* Case 3:HIT DUE TO MATCHING TAGS */
	else{
		printf("read got hit!");
		/* print message of Read/Write hit*/
	}
/* reference cache word, transfer data to/from cache depending on read/write signal*/
	if(read_write == 0){
        data = cache[block].block[word];
	}
	else{
        mm[mm_addr] = data;
	}

/* Print message of word, block, tag, data value */
	printf("\nword: %d \n", word);
	printf("block: %d \n", block);
    printf("tag: %d \n", tag);
    //this part is getting wrong
    printf("contents: %d \n", data);

    return;
}


int main()
{
    int choice = 0;
    while(choice != 3){
        printf("\nplease enter the number from the option.");
        printf("\n[1]Enter Parameter \n[2]access cache \n[3]Exit \n");
        scanf("%d", &choice);
        switch(choice){
            case 1:
                params();
            break;

            case 2:
                access_cache();
            break;

            case 3:
                choice = 3;
            break;

            default:
                printf("typed wrong number!");
        }
    }
    return 0;
}

