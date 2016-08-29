import java.util.Scanner;
import java.io.*;
import java.util.*;

public class BestStuffEver {

  public static void main ( String[] args ) throws IOException {

    if ( args.length == 0 ) {  //test to see if command line has no arguments
      System.out.println("Error -- File not found"); // help message
      System.exit(0); //quits
    }
    BufferedReader in = new BufferedReader(new FileReader(args[0])); //buffers input on the command line
       
    UIMS hashtable = new UIMS();

    while (true) {
      String userName = in.readLine();
      
      if (userName == null || userName.length() == 0) break;
      
      if(hashtable.isAvailable(userName)) {
          hashtable.loadFactor();
          hashtable.add(userName);          
      }    
    } 

    in.close(); 
    hashtable.debug();
  }
}
