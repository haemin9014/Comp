#include <stdio.h>
#include <stdlib.h>
//Haemin Lee
//Comp 222

void calc_cpi();
void calc_time();
void calc_mips();
int main();
void enter_params();
int cycle_total;
int instr_total;
int final_freq;
float avg_cpi;
float time;
float mip;
/* Define structure for instruction containing fields for destination register, 2 source registers, and individual instruction delay
and a variable as pointer to structure for creating a dynamic array of instructions */
    struct node{
        int dest;
        int src1;
        int src2;
        int delay;
    } *instr_set = NULL;
    typedef struct node instr_type;
/*************************************************************/
int total_num;
int pipeline_delay;

void print_chart(){
/* Declare local variables */
/* For each instruction, align instructions by tabbing according to delay, and print out stages with proper tabbing (FI\tDI\tCO\tFO\tEI\tWO) */
    int i;
    int j;
    for (i = 1; i <= total_num; i++){
        printf("%d) ", i);
        for(j = 1; j <= instr_set[i].delay; j++){
            printf("\t");
        }
    printf("FI\tDI\tCO\tFO\tEI\tWO\n");
    }
  return;
}


/*************************************************************/
void set_instruction(){
/* Declare local variables, including an array of characters to store user input */
    //depend on compiler
    char instr_string[9];
    int i;
/* Prompt for total number of instructions */
    printf("please enter total number of instruction: \n");
    //total_num is global number
    scanf("%d", &total_num);
/* Allocate memory to hold a set of instructions based on total number of instructions+1 (instruction 0 used for dependency checking)*/
    instr_set = (instr_type *)malloc((total_num + 1)* sizeof(instr_type));
/* Initialize instruction 0's destination register to -1 to prevent false RAW dependency w/ instruction 2 */
    instr_set[0].dest = -1;
/* For each instruction, prompt for user input with instruction number, such as: 1)
and read instruction as a string and store at proper field of appropriate index within dynamic array of instructions */
    for(i = 1; i <= total_num; i++){
        printf("%d) ", i);
        scanf("%s", instr_string);
        instr_set[i].dest = instr_string[1];
        instr_set[i].src1 = instr_string[4];
        instr_set[i].src2 = instr_string[7];
    }
    return;
}


/*****************************************************/
void calc_pipeline(){
    int i;
    int overlap = 0;
    int delay;
    int total_delay;
    instr_set[1].delay = 0;
    for(i = 2; i <= total_num; i++){
        delay = 0;
        if((instr_set[i-2].dest == instr_set[i].src1)||
        (instr_set[i-2].dest == instr_set[i].src2)){
            if(overlap == 0){
                delay = 1;
                overlap =1;
            }
            else{
                delay = 0;
                overlap = 0;
            }
        }
        else{
            overlap = 0;
        }
        if((instr_set[i-1].dest == instr_set[i].src1)||
        (instr_set[i-1].dest == instr_set[i].src2)){
            delay = 2;
            overlap = 1;
        }
    instr_set[i].delay = instr_set[i-1].delay + delay +1;
    } /* end for-loop */

/* print chart and total delay*/
total_delay = instr_set[total_num].delay + 6;
printf("total number of cycles: %d\n", total_delay);
print_chart();
return;
}

/***************************************************************/

void calc_superscalar(){
/* Declare local variables */
int total_delay;
int i;
pipeline_delay = 0;
int overlap1;
int overlap2;
int overlap3;
int overlap4;
int overlap5;
int delay;
/* For each instruction i from 2 to total number of instructions */

    for(i = 2; i <= total_num; i++){
        delay = 0;
        pipeline_delay = 1 - pipeline_delay;
        /**************************check between instruction(i - 5 ) & instruction i***************************/
        if(i > 5){
            if((instr_set[i-5].dest == instr_set[i].src1)||(instr_set[i-5].dest == instr_set[i].src2)){
            pipeline_delay = 1;
                if((overlap5 == 0) && (overlap4 == 0)&&(overlap3 == 0)&&(overlap2 == 0)&&(overlap1 == 0)){
                    delay = 0;
                    overlap5 =1;
                }
                else{
                    delay = 0;
                    overlap5 = 0;
                }
            }
            else{
                overlap5 = 0;
            }
        }//if (i>5)

        /**************************check between instruction(i - 4 ) & instruction i***************************/
        if(i > 4){
            if((instr_set[i-4].dest == instr_set[i].src1)||(instr_set[i-4].dest == instr_set[i].src2)){
                pipeline_delay = 1;
                if((overlap4 == 0)&&(overlap3 == 0)&&(overlap2 == 0)&&(overlap1 == 0)&&(pipeline_delay == 1)){
                    delay = 1;
                    overlap4 = 1;
                }
                else{
                    delay = 0;
                    overlap4 = 0;
                }
            }
            else{/*independent*/
                overlap4 = 0;
            }
        }//if (i>5)
        /**************************check between instruction(i - 3 ) & instruction i***************************/
        if(i > 3){
            if((instr_set[i-3].dest == instr_set[i].src1)||(instr_set[i-3].dest == instr_set[i].src2)){
                pipeline_delay = 1;
                if((overlap3 == 0)&&(overlap2 == 0)&&(overlap1 == 0)){
                    delay = 1;
                    overlap3 = 1;
                }
                else{
                    delay = 0;
                    overlap3 = 0;
                }
            }
            else{
                overlap3 = 0;
            }
        }//if (i>5)
        /**************************check between instruction(i - 2 ) & instruction i***************************/
        if(i > 2){
            if((instr_set[i-2].dest == instr_set[i].src1)||(instr_set[i-2].dest == instr_set[i].src2)){
                pipeline_delay = 1;
                if((overlap2 == 0)&&(overlap1 == 0)&&(pipeline_delay == 1)&&(pipeline_delay==1)){
                    delay = 2;
                    overlap2 = 1;
                }
                else{
                    delay = 1;
                    overlap2 = 0;
                }
            }
            else{/*independent*/
                overlap2 = 0;
        }
          }//if (i>5)
          /**************************check between instruction(i - 1 ) & instruction i***************************/
        if((instr_set[i-1].dest == instr_set[i].src1)||(instr_set[i-1].dest == instr_set[i].src2)){
            delay = 2;
            overlap1 = 1;
        }
        /* Calculate individual delay for current instruction */
        instr_set[i].delay = instr_set[i-1].delay + delay + pipeline_delay;
        } /* end for-loop */
    total_delay = instr_set[total_num].delay + 6;
    /* print chart */
    printf("total number of cycles: %d\n", total_delay);
    print_chart();
    return;
}

int main(){
    int choice = 0;

    while(choice != 4){

        printf("\nplease enter the number from the option.");

        printf("\n[1]Enter instructions \n[2]Calculate total cycle count on a 6-stage pipelined architecture \n[3]Calculate total cycle count on a 6-stage superscalar architecture \n[4]Exit \n");

        scanf("%d", &choice);

        switch(choice){

            case 1:

                set_instruction();

            break;



            case 2:

                calc_pipeline();

            break;



            case 3:

                calc_superscalar();

            break;



            case 4:

                choice = 4;

            break;

            default:

                printf("typed wrong number!");

        }

    }

    return 0;

}
