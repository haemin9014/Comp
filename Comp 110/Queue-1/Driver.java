//name: Haemin Lee
//date: 04/22/2017
//application: Queue
//purpose:learn how to use Queue

class Driver{

   public static void main(String args[]){
      int[] array = {124, 198, 5234, 5746, 1234, 634, 2345, 5434, 5233, 5234, 345, 1234, 765};
      Queue q = new Queue();
      
      q.peek();
      
      q.peek();
      
      q.poll();
      
      q.poll();
      
      for(int j = 0; j<array.length; j++){
         q.add(array[j]);
      }
      
      q.peek();
      
      q.poll();
      
      q.peek();
      
      for(int k = 0; k < 15; k++){
         q.poll();
      }   
      
      q.peek();
      
   }
}      