//Haemin Lee


#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
int max_length;
int hamming_parity;
int num_parity;
char *hamming_string;

void set_params(){

 //prompt for maximum haming code length and for even/odd parity (even/odd)
 //printf/scanf
    printf("Enter maximum haming code length: ");
    scanf("%d", &max_length);

    printf("Enter parity(even = 0 or odd = 1): ");
    scanf("%d", &hamming_parity);
 //allocate memory for hamming string based on maximum length and size of a character elements
    hamming_string = (char *)malloc(max_length * sizeof(char));
    return;
}

void set_check_fix(){
    //declare local vars
    int i;
    int j;
    int k;
    int actual_length;
    int parity_check;
    int error_bit = 0;

    //prompt for hamming code as a string
    printf("Enter maximum haming code: ");
    scanf("%s", hamming_string);

    //determine actual hamming code length && number of parity bits
    actual_length = strlen(hamming_string);
    num_parity = ceil(log(actual_length)/log(2));

    //outer loop: for each parity bit in the hamming code
    for(i = 1; i < actual_length; i = 2*i){
        parity_check = hamming_parity;
        for(j = i; j < actual_length; j = j + 2*i){
            //if i is 4, j will be 4 and k will start to check 4 ,5 ,6 ,7
            for(k = j; k <= actual_length && k <= j + i ; k++){
                parity_check ^= (hamming_string[actual_length - k] - '0');
            }
        }
        error_bit = error_bit + parity_check * i;
    }

    //report error in hamming code based on result from parity bit or report no error if none
    if(error_bit == 0){
        printf("There is no bit error");
    }
    else{
        //correct hamming code by flipping error bit
        if(hamming_string[actual_length - error_bit] == '0'){
            hamming_string[actual_length - error_bit] = '1';
        }
        else{
            hamming_string[actual_length - error_bit] = '0';
        }
        printf("There is an error in bit: %d", error_bit);
        printf("\nThe corrected hamming code is: %s", hamming_string);
    }
    return;
}

int main(){
    int choice = 0;
    while(choice != 3){
        printf("\nplease enter the number from the option.");
        printf("\n[1]Enter Parameter \n[2]set check fix \n[3]Exit \n");
        scanf("%d", &choice);
        switch(choice){
            case 1:
                set_params();
            break;

            case 2:
                set_check_fix();
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
