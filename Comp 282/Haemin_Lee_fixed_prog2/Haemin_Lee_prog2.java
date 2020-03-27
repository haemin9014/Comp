class StringAVLNode {
 
	private String item;
	private int balance;
	private StringAVLNode left, right;
	public StringAVLNode(String str) {
		item = str;
		left = null;
		right = null;
		balance = 0;
	}
 
	public int getBalance() {
		return balance;
	}
 
	public void setBalance(int bal) {
		balance = bal;
	}
 
	public String getItem() {
		return item;
	}
 
	public StringAVLNode getLeft() {
		return left;
	}
 
	public void setLeft(StringAVLNode pt) {
		left = pt;
	}
 
	public StringAVLNode getRight() {
		return right;
	}
 
	public void setRight(StringAVLNode pt) {
		right = pt;
	} 
}

class StringAVLTree {
	StringAVLNode root;
	
 
	public StringAVLTree() {
		root = null;
	}
	
 	private static StringAVLNode rotateRight(StringAVLNode t) {	
 		StringAVLNode inputP;
 		inputP = t.getLeft();//set left of node t in node j
  		t.setLeft(inputP.getRight());
 		inputP.setRight(t);					
	 	return inputP;
	}
  	private static StringAVLNode rotateLeft(StringAVLNode t) {
 		StringAVLNode inputP; 
		inputP = t.getRight();
		t.setRight(inputP.getLeft());
		inputP.setLeft(t);
		return inputP;
	}

  	public int height() {
		return height(root);
	}
 
	private int height(StringAVLNode t) {
		int totalHeight = 0;
		int leftHeight = 0;
		int rightHeight = 0;
		if(t == null){
			totalHeight = 0;
		}
		else if(t.getLeft() == null && t.getRight() == null) {
			totalHeight = 1;
		}
		else if(t.getLeft() == null || t.getRight() == null) {
			if(t.getLeft() == null) {
				rightHeight = height(t.getRight());
				totalHeight = rightHeight +1;
			}
			else {
				leftHeight = height(t.getLeft());
				totalHeight= leftHeight+1;
			}
		}
		else {
			leftHeight = height(t.getLeft());
			rightHeight = height(t.getRight());				
			if(leftHeight < rightHeight) {
				totalHeight = rightHeight +1;
			}
			else{
				totalHeight = leftHeight +1;
			}
		}
		return totalHeight;
	}
 
	public int leafCt() {
		return leafCt(root);
	}
	
	private int leafCt(StringAVLNode t) {
		int numberLeaf = 0;
		if(t == null){
			numberLeaf = 0;
		}
		else if(t.getLeft() != null && t.getRight() == null) {
			numberLeaf = leafCt(t.getLeft());
			
		}
		else if(t.getRight() != null && t.getLeft() == null) {
			numberLeaf = leafCt(t.getRight());
		}
		else if(t.getRight() != null && t.getLeft() != null) {
			numberLeaf = leafCt(t.getRight()) + leafCt(t.getLeft());
		}
		else{
			numberLeaf++;
		}
		return numberLeaf;
	}
	//return the number of perfectly balanced avl nodes
	public int balanced() {
		return balanced(root);
	}
	
	public int balanced(StringAVLNode t) {
		int numberBalance = 0;;
		if(t == null){
			numberBalance = 0;
		}
		else{
			if(t.getLeft() != null && t.getRight() == null) {
				numberBalance = balanced(t.getLeft());
				
			}
			else if(t.getRight() != null && t.getLeft() == null) {
				numberBalance = balanced(t.getRight());
			}
			else if(t.getRight() != null && t.getLeft() != null) {
				numberBalance = balanced(t.getRight()) + balanced(t.getLeft());
				//since right and left is not null, it might have 0 in balance.
				if(t.getBalance() == 0) {
					numberBalance++;
				}
			}
			else{
				//since it both side are null(ex:leaf), it must have 0 in balance.
				//however, to make sure, check balance.
				numberBalance = balanced(t.getRight()) + balanced(t.getLeft());
				if(t.getBalance() == 0) {
					numberBalance++;
				}
			}
		}
		return numberBalance;
	}
	public String successor(String str) {
		//String lastLeft;
		String lastLeft = null;
		return successor( str, root, lastLeft);
	}
	
	public String successor(String str, StringAVLNode t, String lastLeft) {
		String s = null;
		//i did to long! and forget that when == 0, it means same!
		if(t == null) {
			s = null;
		}
		else if(str.compareTo(t.getItem()) < 0){
			lastLeft = t.getItem();
			s= successor( str, t.getLeft(), lastLeft);
		}
		else if(str.compareTo(t.getItem()) == 0) {
			if(t.getRight()!=null) {
				StringAVLNode begin = t.getRight();;
				//set getRight for begin
				while(begin != null) {
					//save previews left node item to s
					s = begin.getItem();					
					//and set being left or next left.
					begin = begin.getLeft();
					//this will repeat until begin is null	
				}
			}
			else {
				s = lastLeft;
			}
		}
		else{
			s = successor(str, t.getRight(), lastLeft);
		}
		return s;
	}

	public void insert(String str) {
		root = insert(str, root);
	}
	
	private StringAVLNode insert(String str, StringAVLNode t) {
		if(t == null) {//open
			//add when it is empty, it will happen mostly at root
			t = new StringAVLNode(str);	
		}///close

		else if(t.getItem() == str) {//open
			//close else if when it is same, we are doing nothing	
		}//close	
		
		else if(str.compareTo(t.getItem()) > 0){//open else if
			//when str is bigger than getItem(), it will give positive
			//so it will be bigger than 0.
			
			//which means it will send to right.
			if(t.getRight() == null) {
				//when t.getright() is null, put in str
				t.setRight(insert(str, t.getRight()));
				//as we add one node at right side, balance will increase one.
				t.setBalance(t.getBalance()+1);			
			}
			else if(t.getRight() != null){
			//when t.getRight is not empty, fist, save balance of t.egtRight()
				int pastBalance = t.getRight().getBalance();
				//next, insert the number
				t.setRight(insert(str, t.getRight()));
				//as we inserted, balance will be increased by one.
				if(pastBalance == 0 && t.getRight().getBalance() != 0) {
					//when past balance is 0 and new balance is not 0, add 1 to t node
					t.setBalance(t.getBalance()+1);
					if(t.getBalance() > 1) {
						if(str.compareTo(t.getRight().getItem()) > 0){
							t = rotateLeft(t);							
							t.setBalance(0);
							t.getLeft().setBalance(0);
						}
						else {
							//right left rotation
							//t.right rotate right
							int checkRLBalance = t.getRight().getLeft().getBalance();
							t.setRight(rotateRight(t.getRight()));
							//t rotate left
							t = rotateLeft(t);
							if(checkRLBalance == 1) {
								t.setBalance(0);
								t.getRight().setBalance(0);
								t.getLeft().setBalance(-1);									
							}
							else if(checkRLBalance == -1) {
								t.setBalance(0);
								t.getRight().setBalance(1);
								t.getLeft().setBalance(0);									
							}
							else {
								t.setBalance(0);
								t.getRight().setBalance(0);
								t.getLeft().setBalance(0);
								
							}
						}
					}
				}				
			}//end for checking when getRight() != null
		}//close else if for searching right
		
		else if(str.compareTo(t.getItem()) < 0){//start else if for searching left
			if(t.getLeft() == null) {//open if
				t.setLeft(insert(str, t.getLeft()));
				t.setBalance(t.getBalance() - 1);
				//when t.getLeft() is null, put in str
			}//close if
			//when t.getLeft() is not null
			else if(t.getLeft() != null){//open else if
				int pastBalance = t.getLeft().getBalance();
				t.setLeft(insert(str, t.getLeft()));
				if(pastBalance == 0 && t.getLeft().getBalance() != 0) {//open if
					t.setBalance(t.getBalance() - 1);				
					if(t.getBalance() < -1) {//open if(nested if)
						if(str.compareTo(t.getLeft().getItem()) < 0) {
							t = rotateRight(t);
							t.setBalance(0);
							t.getRight().setBalance(0);
						}
					
						else {
							//left right rotation
							//t.left rotate left
							int checkRLBalance = t.getLeft().getRight().getBalance();
							t.setLeft(rotateLeft(t.getLeft()));
							//t rotate right
							t = rotateRight(t);
								if(checkRLBalance == 1) {
									t.setBalance(0);
									t.getRight().setBalance(0);
									t.getLeft().setBalance(-1);								
								}
								else if(checkRLBalance == -1) {
									t.setBalance(0);
									t.getRight().setBalance(1);
									t.getLeft().setBalance(0);									
								}
								else {
									t.setBalance(0);
									t.getRight().setBalance(0);
									t.getLeft().setBalance(0);
								}
						}
					}	
				}
			}//closed else if for searching left
		}
		return t;
	}
	
		
	
	public static String myName() {
		return "Haemin Lee";
	}
}
 
