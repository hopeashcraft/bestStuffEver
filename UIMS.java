import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.Random.*;

public class UIMS {

	int counter = 0;
	int m = 2;
	int lists = 0;
	int b = (int)(Math.log(m) / Math.log(2)); //base
	int key;

	SLItemList[] users = new SLItemList[m + 1];

	public boolean isAvailable(String userName) {
		for (int i = 0; i < users.length; i++) {
			if (users[i] != null) 
				if (users[i].find(userName).equals(userName)) 
					return false;
		}
		return true;
	}

	public void add(String userName) {

		if (isAvailable(userName) == false) {
			return;
		}

		int index = hash(userName);
		
		System.out.println("username: " + userName + " index: " + index);

		if (this.users[index] == null) {
			this.users[index] = new SLItemList();
			this.lists++;
		}

		this.users[index].pushFront(userName, counter);
		this.counter++;
	}

	public int lookupCustomerId(String userName) {
		return 0;
	}

	/*public int hash(String userName) {
		int sum = 0, mod = 0;

		Conversion convert = new Conversion();

		int[] hashing = convert.bitseqToDigitseq(convert.stringToBitseq(userName), b);

		for (int i = 0; i < hashing.length; i++) {
			sum += hashing[i] * saltGen();
		}
		System.out.print("SUM " + sum + " M " + m + " LENGTH " + hashing.length + " ");
		mod = sum % m;


		/* FIRST BINARY CONVERSION USED
		byte[] bytes = userName.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b: bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
			binary.append(' ');
		}

		for (byte i: bytes) sum += i;
		mod = this.m % sum;
              
              


		return mod;
	}
	*/
	
	public int hash(String userName) {
		Random rand = new Random();
		
		int scale = rand.nextInt(this.m - 1) + 1;
		int shift = rand.nextInt(this.m);
		
  	return Math.abs(userName.hashCode() * scale + shift) % this.m;
  }

	//𝑙𝑜𝑎𝑑(): 𝑛𝑢𝑚𝑏𝑒𝑟
	//𝑙𝑜𝑎𝑑() = 𝑐𝑢𝑠𝑡𝑜𝑚𝑒𝑟𝐼𝑑/𝑚
	//invariant 𝑙𝑜𝑎𝑑() ≤ 0.75
	// when adding a user id would put the load factor over 0.75 then reallocate the hash
	// table, doubling its size. You may want to experiment with other load factors.

	public void loadFactor() {
		if ((double) this.counter / (double) this.m >= 0.75) reallocate(users);
	}

	// Generates random num for salt
	public int saltGen() {
		int max = b;
		int min = 0;
		Random r = new Random();
		int n = r.nextInt((max - min));

		int salt = n % b;
		return salt;
	}
	
	public SLItemList[] reallocate(SLItemList[] users) {
		
		SLItemList[] temp = this.users;

		this.m = this.m * 2;
		this.users = new SLItemList[this.m + 1];
		this.counter = 0;

		for (int i = 0; i < users.length; i++) {
			if (temp[i] != null) {
				SItem item = temp[i].header;


				while (item != null) {
					this.add(item.uid);
					item = item.next;
				}
			}
		}
		System.out.println("this is m: " + m);
		return temp;
	}
	public void debug() {
		System.out.println(this.lists);

		for (int i = 0; i < this.users.length; i++) {
			SLItemList list = this.users[i];

			if (list != null) {
				System.out.println("Bucket " + i + ": Count = " + list.length);
				list.displayList();
			}
		}
	}
}