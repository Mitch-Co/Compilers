/*
  Created by: Fei Song
  File Name: Main.java
  To Build: 
  After the scanner, tiny.flex, and the parser, tiny.cup, have been created.
    javac Main.java
  
  To Run: 
    java -classpath /usr/share/java/cup.jar:. Main gcd.tiny

  where gcd.tiny is an test input file for the tiny language.
*/
   
import java.io.*;
import absyn.*;
import java.io.*;
   
class CM {
  public final static boolean SHOW_TREE = true;
  static public void main(String argv[]) {    
    /* Start the parser */
    try {
      parser p = new parser(new Lexer(new FileReader(argv[0])));
      Absyn result = (Absyn)(p.parse().value);      
      if (SHOW_TREE && result != null) {
         System.out.println("The abstract syntax tree is:");
         ShowTreeVisitor visitor = new ShowTreeVisitor();
         result.accept(visitor, 0); 
         MitchsMarvelousMachinecodeMaker MMMM = new MitchsMarvelousMachinecodeMaker();
         String output = MMMM.generateAssembly(result);
         
         try 
         {
          FileWriter fp = new FileWriter(argv[0].substring(0,argv[0].length() - 3) + ".tm");
          fp.write(output);
          fp.close();
          
         }
         catch (Exception e)
         {

         }
         

         System.out.println("\nASSEMBLY:");
         System.out.println(output);

      }
    } catch (Exception e) {
      /* do cleanup here -- possibly rethrow e */
      e.printStackTrace();
    }
  }
}


