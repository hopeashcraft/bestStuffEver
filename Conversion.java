import java.util.*;
import java.lang.Math;
import java.lang.*;

// helper class for hash function
public class Conversion {

    int m = 128;                // hash table size  -- size power of 2
    int l = 7;                  // 2^7
    int w = 96;                 // Keys: {0... 2^96 - 1}
    int a;          // salt : odd # between {0... 2^w  - 1}
    
    // stringToBitseq(s: string): array of {0.1}
    // returns an array of 0s and 1s constructed by 
    // representing each character in s by 
    // its assigned bit pattern 
    // (position in the list below represented as a numeral to base 2)
    
// Convert the bit seqence for a, the salt, and x (representing a user id)
    // into BigInters, computer the hash func using BigIntergers
    // convert the result back to int
    
    // converts a string into binary
    int[] stringToBitseq(String s) { 
        // all 62 possible options for username characters
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String bit2Int = "";       // empty string for bitseq
        
        char[] usernameArray = s.toCharArray();     // mapping username to an array
        //int charALength = usernameArr.length;
        int[] charNum = new int[usernameArray.length]; // numbers of chars from username
        
        String[] bitString = new String[charNum.length]; // bit string of number of char
        
        int[] intBits = new int[96];  // ARRAY TO RETURN -- 16 possible char options * 6 bits 
        // for each character in the username
        
        //String bi = "01";

        for (int i = 0; i < usernameArray.length; i++) {   
            charNum[i] = chars.indexOf(usernameArray[i]);   // map character to number
            bitString[i] = Integer.toBinaryString(charNum[i]);  // map number to binary string
        }
        
        // now that we have a binary string, we want to convert to a bit sequence
        
        // map it to 6 bits strings just in case bigger / smaller
        for (int i = 0; i < bitString.length; i++) {
            while (bitString[i].length() != 6) {
                bitString[i] = "0" + bitString[i];
            }
            bit2Int = bit2Int.concat(bitString[i]);
        }
        
        // uniform length of 96 aka 16 * 6 (the largest number of possible bit options)
        while (bit2Int.length() != 96) {
            bit2Int = "0" + bit2Int;
        }
        
        // convert STRINGS to INT for return statement
        for (int i = 0; i < bit2Int.length(); i++) {
            intBits[i] = Integer.parseInt(String.valueOf(bit2Int.charAt(i)));
        }
        return intBits;             
    }
 
    //(bs: array of {0,1}, k:number): array of digits to base 2^ k
    // returns an array of numbers in the range [0...2^k  - 1]
    // put into chunks of __ pass to
    // Integer.valueOf(binaryValue, baseCurrentlyIn)
    // store into array
   
    int[] bitseqToDigitseq(int[] bs, int k) { // k is base and int[] is bitseq
        
        String bitChunk = "";      
        int dSize = bs.length/k;         
        int[] digseq = new int[dSize];     
        int num = 0;
        int dig = 0;
        int loop = (bs.length+k)-(bs.length%k);
        for (int i = 0; i < loop; i++) {
            if ((i%(k+1) == 0) && (i>0)) {
                digseq[i/k-1] = Integer.valueOf(bitChunk,2);
                bitChunk = "";
            } else {
                if (i < bs.length) bitChunk = bitChunk+bs[i];
                else bitChunk = bitChunk + "0";
            }
        }     
        return digseq;      
    }
}
      
    
    // (bs: aray of {0,1}, k:number):BigNum
    // Returns a BigNum bn whose representation to the base 2 is bs
    