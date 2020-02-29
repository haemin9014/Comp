//name:Haemin Lee
//Date:04/08/2017
//application: how to use extend and default 
//purpose: get used to use extend and default

class Driver {

   public static void main(String[] args) {
      String name;
      //default name
      AlaskanMalamute myA = new AlaskanMalamute("");
      System.out.println("My dogs name is " + myA.getName());
      System.out.println("breed of my dog is Alaskan Malamute");
      System.out.println(myA.getName() + " barks like this [" + myA.bark() +"]");
      System.out.println("The color of " + myA.getName() + " is " + myA.getColor());
      System.out.println(myA.getName() + " has an average speed of: " + myA.getSpeed());
      System.out.println("The size of " + myA.getName() + " is " + myA.getSize() + "\n");
      //default bark
      name = "bella";
      AppenzellerSennenhunde myS = new AppenzellerSennenhunde(name);
      System.out.println("My dogs name is " + myS.getName());
      System.out.println("breed of my dog is Appenzeller Sennenhunde");
      System.out.println(myS.getName() + " barks like this [" + myS.bark() +"]");
      System.out.println("The color of " + myS.getName() + " is " + myS.getColor());
      System.out.println(myS.getName() + " has an average speed of: " + myS.getSpeed());
      System.out.println("The size of " + myS.getName() + " is " + myS.getSize() + "\n");      
      //default color
      name = "snow";
      AmericanEskimo myE = new AmericanEskimo(name);
      System.out.println("My dogs name is " + myE.getName());
      System.out.println("breed of my dog is American Eskimo");      
      System.out.println(myE.getName() + " barks like this [" + myE.bark() +"]");
      System.out.println("The color of " + myE.getName() + " is " + myE.getColor());
      System.out.println(myE.getName() + " has an average speed of: " + myE.getSpeed());
      System.out.println("The size of " + myE.getName() + " is " + myE.getSize() + "\n");
      //default speed
      name = "Moli";
      AustralianShepherd myP = new AustralianShepherd(name);
      System.out.println("My dogs name is " + myP.getName());
      System.out.println("breed of my dog is Australian Shepherd");
      System.out.println(myP.getName() + " barks like this [" + myP.bark() +"]");
      System.out.println("The color of " + myP.getName() + " is " + myP.getColor());
      System.out.println(myP.getName() + " has an average speed of: " + myP.getSpeed());
      System.out.println("The size of " + myP.getName() + " is " + myP.getSize() + "\n");
      //default size
      name = "Wiskey";      
      Akita myAkita = new Akita(name);
      System.out.println("My dogs name is " + myAkita.getName());
      System.out.println("breed of my dog is Akita");
      System.out.println(myAkita.getName() + " barks like this [" + myAkita.bark() +"]");
      System.out.println("The color of " + myAkita.getName() + " is " + myAkita.getColor());
      System.out.println(myAkita.getName() + " has an average speed of: " + myAkita.getSpeed());
      System.out.println("The size of " + myAkita.getName() + " is " + myAkita.getSize() + "\n");

   }

}
