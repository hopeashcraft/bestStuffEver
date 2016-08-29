/*This class will contain helper methods for generating test data sets and running UIMS on the test
data sets. You want to be able to (randomly) generate lists of user ids, for instance. More on this
soon.
*/
import java.util.Random;

public class TestDrivers {
  
  public static void main ( String[] args ) {
    TestDrivers test = new TestDrivers();
    test.getRandomNumberInRange(); 
  }

	static public int getRandomNumberInRange() {

    int min = 1;
    int max = 16;

    Random r = new Random();
    int output = r.nextInt((max - min) + 1);
  
    TestDrivers.randomGen(output);
    return output;
  }
		
  static String randomGen(int output) {
    Random rng = new Random();
		String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		
    char[] text = new char[output];
    
      for (int i = 0; i < output; i++) {
        text[i] = characters.charAt(rng.nextInt(characters.length()));
      }
    System.out.println(text);
    return new String(text);
    
  }
}