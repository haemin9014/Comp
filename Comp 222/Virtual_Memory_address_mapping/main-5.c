//Haemin Lee
#include <stdio.h>
#include <stdlib.h>

int mm_size;
int page_size;
int num_entries;//global
int replacement_policy;
int offset;//global
int vp;
int pf;

struct node{
    int vp;
    int pf;
} *pt = NULL;
typedef struct node entry;


void set_params(){
    int j;
 //prompt for maximum haming code length and for even/odd parity (even/odd)
 //printf/scanf
    printf("Enter memory size: ");
    scanf("%d", &mm_size);

    printf("Enter page size: ");
    scanf("%d", &page_size);
 //allocate memory for hamming string based on maximum length and size of a character elements
    printf("Enter replacement_policy: ");
    scanf("%d", &replacement_policy);

    num_entries = mm_size/page_size;
    pt = (entry *)malloc(num_entries * sizeof(entry));

    for(j = 0; j < num_entries; j++){
        pt[j].vp = -1;
    }
    return;
}

void mapping(){//open mapping
    //declare local vars
    int pa;
    int i = 0;
    int j;
    int k;
    int virtual_address;

    printf("Enter virtual_address: ");
    scanf("%d", &virtual_address);
    vp = virtual_address/page_size;
    offset = virtual_address%page_size;
    while((i<num_entries) && (pt[i].vp!=-1) && (pt[i].vp!=vp)){//open
            i++;
    }//close
    //case1
    if(i == num_entries){//open if
        pf = pt[0].pf;
        for(j=0; j<num_entries-1; j++){//open for
            pt[j].vp = pt[j+1].vp;
            pt[j].pf = pt[j+1].pf;
        }//close for
        pt[num_entries-1].vp = vp;
        pt[num_entries-1].pf = pf;
        printf("page fault!! case 1(page table is full)\n");
    }//close if
    //case 2
    else if(pt[i].vp == -1){//open if
        pt[i].vp = vp;
        pt[i].pf = i;
        printf("page fault!! case 2(unused entry in table)\n");
    }//close if
    //case 3
    else if(pt[i].vp == vp){//open else if
        printf("hit!!\n");
        pa = pf*page_size+offset;
        if(replacement_policy == 0){
            pf = pt[i/*?*/].pf;
        }
        for (k = i/*?*/; k <= num_entries-2; k++){//open for
            if(pt[k+1].vp == -1 /*?*/){//open if
                break;
            }//close if
            pt[k] = pt[k+1];
        }//close for
        pt[k].vp = vp;
        pt[k].pf = pf;
        printf("Virtual address %d maps to physical address %d", virtual_address, pa);
    }//close else if
    return;
}//close mapping

void print_out(){
    int i;
    /* For each valid entry in page table */
    printf("---------------\n");
    printf("|  VP  ");
    printf("|  PF  |\n");
    printf("---------------\n");

    /* print virtual page number and corresponding page frame number */
        for(i = 0; i < num_entries; i++){
            if(pt[i].vp!=-1){
                printf("|  %d   ", pt[i].vp);
                printf("|  %d   |\n", pt[i].pf);
                printf("---------------\n");
            }
        }

}

int main(){
    int choice = 0;
    while(choice != 7){
        printf("\nplease enter the number from the option.");
        printf("\n[1]Enter Parameter \n[2]set mapping \n[3]print out \n[4]Exit \n");
        scanf("%d", &choice);
        switch(choice){
            case 1:
                set_params();
            break;

            case 2:
                mapping();
            break;

            case 3:
                print_out();
            break;

            case 4:
                choice = 7;
            break;

            default:
                printf("typed wrong number!");
        }
    }
    return 0;
}
