//name: Haemin Lee
//date: 04/22/2017
//application: stack
//purpose:learn how to use stack

class Stack{//stack class open

   final int MAX_SIZE = 10;
   int top;
   int[] array;
   
   public Stack(){//constructor open
     array = new int[MAX_SIZE];
     top = -1;
   }//constructor close
   
   public void push(int item){//open push
      if(MAX_SIZE -1 == top){//open if
         System.out.println("reached to top");
         return;
      }//close if
      top++;
      array[top] = item;
      System.out.println("process finished");     
   }//close push
   
   public int pop(){//open pop
      if(top == -1){//open if
         System.out.println("stack is empty");
         return -1;
      }//close if
      return array[top--];
   }//close pop
   
   public void peek(){//open peek
      System.out.println(array[top]);
   }//close peek
   
   public boolean isEmpty(){//open isEmpty
      if(top == -1){//open if
         System.out.println("it is empty");
         return true;
      }//close if
      System.out.println("it is not empty");
      return false;
   }//close isempty
   public void search(int item){//open search
      for(int j = top; j >= 0; --j){//open for
         if(array[j] == item){//open if
            System.out.println("item foud at " + (top - j));
            return;
         }//close if
       }//close for
       System.out.println("item not foud");   
   }//close search                        

}//close class      