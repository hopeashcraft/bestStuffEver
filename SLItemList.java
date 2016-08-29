public class SLItemList {

    // ℎ𝑒𝑎𝑑𝑒𝑟: 𝐼𝑡𝑒𝑚
    // initially its 𝑛𝑒𝑥𝑡 attribute points to itself
    // can be used to store sentinel when searching list
    SItem header;
    int length = 0;

    //𝑝𝑢𝑠ℎ𝐹𝑟𝑜𝑛𝑡(𝑢𝑖𝑑: 𝑠𝑡𝑟𝑖𝑛𝑔, 𝑐𝑖𝑑: 𝑛𝑢𝑚𝑏𝑒𝑟)
    //Inserts new item 𝑖𝑡 at the beginning of list;
    //𝑖𝑡. 𝑢𝑠𝑒𝑟𝐼𝑑 ≔ 𝑢𝑖𝑑
    //𝑖𝑡. 𝑐𝑢𝑠𝑡𝑜𝑚𝑒𝑟𝐼𝑑 ≔ 𝑐𝑖𝑑
    void pushFront (String uid, int cid) {	    
      SItem newItem = new SItem();

      newItem.uid = uid;
      newItem.cid = cid;

	newItem.next = this.header;
	this.header = newItem;
			
	this.length++;
    }
    
    //𝑓𝑖𝑛𝑑(𝑢𝑖𝑑: 𝑠𝑡𝑟𝑖𝑛𝑔)
    //returns the item with 𝑖𝑡𝑒𝑚. 𝑢𝑠𝑒𝑟𝐼𝑑 = 𝑢𝑖𝑑
    // if using sentinel in header, returning header means uid was not found
    SItem find(String uid) {
      SItem element = this.header;

      while(element != null) {
        if(uid.equals(element.uid))
          return element;

				element = element.next;
      }
      
      return header;
    }
    
    void displayList ()
    {
      SItem item = this.header;
      
      while(item != null)
      {
        System.out.print(item.uid + " " + item.cid);
        item = item.next;
      }
      
      System.out.println("\n");
    }

}