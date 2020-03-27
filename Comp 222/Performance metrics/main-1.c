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

int main(){
    int choice = 0;

    while(choice != 5){

        printf("\nplease enter the number from the option.");

        printf("\n[1]Enter Parameter \n[2]Calculate avg CPI of seq of instruction \n[3]Calculate avg exec time\n[4]Calculate avg MIPS \n[5]Exit \n");

        scanf("%d", &choice);

        switch(choice){

            case 1:

                enter_params();

            break;



            case 2:

                calc_cpi(cycle_total, instr_total);

            break;



            case 3:

                calc_time(cycle_total, final_freq);

            break;



            case 4:

                calc_mips(avg_cpi, final_freq);

            break;



            case 5:

                choice = 5;

            break;



            default:

                printf("typed wrong number!");

        }

    }

    return 0;

}





void enter_params(){

    int i;

    int cpi_class;

    int instr_countint;

    int freq;

    int num_classes;

    cycle_total = 0;

    instr_total = 0;

    final_freq = 0;

    printf("Enter number of instruction classes: ");

    scanf("%d", &num_classes);

    printf("Enter frequency of machine : ");

    scanf("%d", &freq);

    final_freq = final_freq + freq;

    for(i = 1; i<= num_classes; i++){

        printf("Enter CPI of class %d: ", i);

        scanf("%d", &cpi_class);

        printf("Enter instruction count of class %d:", i);

        scanf("%d", &instr_countint);

        cycle_total = cycle_total + (cpi_class * instr_countint);

        instr_total = instr_total + instr_countint;

    }

    printf("\n your freq is %d", final_freq);

    printf("\nyour cycle total is %d", cycle_total);

    printf("\nyour instr total is %d\n", instr_total);

    return;

}

void calc_cpi(int cycle_total, int instr_total){

    int instr_total2 = instr_total;
    int cycle_total2 = cycle_total;

    avg_cpi = (1.0 * cycle_total2) / instr_total2;

    printf("\nthe average CPI of the sequence is : %.2f \n", avg_cpi);

    return;

}



void calc_time(int cycle_total, int fianl_freq){

    int cycle_total3 = cycle_total;
    int freq2 = fianl_freq;

    time = ((1.0 *cycle_total3)/(1.0 * freq2))*1000.0;

    printf("\nThe total CPU time of the sequence is : %.2f \n", time);

    return;

}

void calc_mips(){

    float avg_cpi2 = avg_cpi;

    int freq3 = final_freq;

    mip = (1.0 * freq3)/avg_cpi2;

    printf("\nthe total MIPS of a sequence is: %.2f \n", mip);

    return;

}

