//name: Haemin Lee
//date: 04/22/2017
//application: Queue
//purpose:learn how to use Queue

class Queue{

   final int MAX_SIZE = 10;
   int front;
   int rear;
   int[] array;
   
   public Queue(){
      array = new int[MAX_SIZE];
      front = -1;
      rear = -1;
   }
   
   public void add(int item){
      if(rear == MAX_SIZE-1){
         System.out.println("queue is full");
         return;
      }    
      rear += 1;
      array[rear] = item;      
      if(front == -1){
         front = 0;
      }
   }
   
   public void poll(){
      if(front == -1){
         System.out.println("queue is empty(for poll)");
         return;
      }      
      System.out.println("processing the value of the item at index: front = " + front);
      if(front == rear){
         front = -1;
         rear = -1;
      }
      else{
         front += 1;
      }
   }
   
   public void peek(){
      if(front > -1){
         System.out.println(array[front]);
      }
      else{
         System.out.println("queue is empty(for peek)");
      }
   }
}            
      
         
                           
 