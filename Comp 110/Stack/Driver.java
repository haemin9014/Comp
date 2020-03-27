//name: Haemin Lee
//date: 04/22/2017
//application: stack
//purpose:learn how to use stack

class Driver{

   public static void main (String args[]){
      int[] array = {243, 456, 8678, 432, 8658, 356, 754, 879, 253, 3245, 734 ,1324, 123};
      Stack s = new Stack();
      
      s.isEmpty();
      
      s.pop();
      
      for(int j = 0; j < array.length; j++){
         s.push(array[j]);   
      }
      
      s.search(243);
      
      s.peek();
      
      s.pop();
      
      s.peek();
      
      s.isEmpty();
      
      for(int k = 0; k < 15; k++){
         s.pop();
      }   
           
   }
}      