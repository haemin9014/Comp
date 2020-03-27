class sudoku {
	private int[][] board;
	
	public sudoku() {
	
	}
	
	//set a sudoku in a string
	public sudoku(String[] s) {
		board = new int[9][9];
		for(int row = 0; row < 9; row++){
			for(int col = 0; col < 9; col++){
				board[row][col] = (int) (s[row].charAt(col +col/3)) - 48;
			}
		}
	}
	
	//set int to string
	public sudoku(sudoku p) {
		board = new int[9][9];
			for(int row = 0; row < 9; row++){
				for(int col = 0; col < 9; col++){
					board[row][col] = p.board[row][col];
				}
			}
	}

	public String toString() {
		String temp = "";
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				if((col != 0) && (col % 3 == 0)){
					temp = temp + " | " + board[row][col];
				}
				else {
					temp = temp + board[row][col];
				}
	
			}
			temp = temp + "\n";
			if((row + 1 )% 3 == 0) {
				temp = temp + "----------------\n";
			}
		}
		return temp;
	}

	public String toString2() {
		String result = new String();
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				result = result + String.valueOf(board[row][col]);
			}
		}
		return result;
	}

	public void rotate() {
		int[][] temp = new int[9][9];
		int row, col;
		for(row=0; row<9; row++) {
			for(col = 0; col <9; col++) {
				temp[col][8-row] = board[row][col];
			}
		}
		for(row = 0; row < 9; row++) {
			for(col = 0; col < 9; col++) {
				board[row][col] = temp[row][col];
			}
		}
	}

	//not working correctly
	public boolean isValid() {
	//does sudoku code could be complete?
	//check does board have 0, and does number contain twice or more
		boolean result = true;
		int row;
		int col;
		for(row = 0; row < 9; row++) {
			for(col = 0; col < 9; col++) {			
					int val = board[row][col];
					if(board[row][col] != 0) {
							board[row][col] = 0;
						if(doesRowContain(row, val) || doesColContain(col, val) || doesBoxContain(row, col, val)) {
							result = false;
						}
						board[row][col] = val;
						
				}	
			}			
		}		
		return result;
	}

	public boolean isComplete() {
		boolean temp = true;
			for(int row = 0; row < 9; row++) {
				for(int  col = 0; col < 9; col++) { 
					if(board[row][col] == 0) {
						temp = false;
					}
				}
			}
		//}
		if(!isValid()) {
			temp = false;
		}
		return temp;
	}
		
	public boolean doesRowContain(int row, int val) {
		boolean temp = false;
		int rCount = 0;
		for(int cValue = 0; cValue < 9; cValue++) {	
			if(board[row][cValue] == val) {
				rCount++;
			}
		}
		if(rCount != 0){
			temp = true; //count it has value, and if it have 1 or more, it will turn true   
		}

		return temp;
	}

	private boolean doesColContain(int col, int val) {
		//same with row
		boolean temp = false;
		int cCount = 0;
		for(int rValue = 0; rValue < 9; rValue++) {
			if(board[rValue][col]==val) {
				cCount++;
			}
		}
		if(cCount != 0) {
			temp = true;
		}
	
		return temp;
	}

	public boolean doesBoxContain(int row, int col, int val) {
		boolean temp = false;
		int bCount = 0;
		for(int rValue = row/3*3; rValue < (row/3*3+3); rValue++) {
			for(int cValue = col/3*3; cValue < (col/3*3+3); cValue++) {
				if(board[rValue][cValue] == val) {
					bCount++;
				}
			}
		}
		if(bCount != 0) {
			temp = true;
		}
		return temp;
	}

	private int fillSpot(Spot sq) {
		int countSpot = 0;
		int temp = 0;
		int rTemp = 0;
		for(int val = 1; val < 10; val++) {
			if(board[sq.getRow()][sq.getCol()]==0 && !doesColContain(sq.getCol(), val) && !doesBoxContain(sq.getRow(), sq.getCol(), val) && !doesRowContain(sq.getRow(), val)) {
				countSpot++;
    			temp = val;
    		}   	  
		}
		if(countSpot == 1) {
			rTemp = temp;
		}
		if(countSpot != 1) {
			rTemp =  0;
		}
      	return rTemp;		
	}

	public Spot rowFill(int row, int val) {
		int countRow = 0;
		int colNumber = 0;
		Spot rTemp = new Spot(row, 0);
		if(!doesRowContain(row, val)) {
			for(int col = 0; col < 9; col++) {
				if(board[row][col]==0 && !doesColContain(col, val) && !doesBoxContain(row, col, val) && !doesRowContain(row, val)) {
					countRow++;
					colNumber = col;
				}
			}
		}
		if(countRow != 1) {
			rTemp = null;
		}
		if(countRow == 1) {
			rTemp.setCol(colNumber);					
		}
		return rTemp;
	}
	
	private Spot colFill(int col, int val) {
		int countCol = 0;
		int rowNumber = 0;
		Spot cTemp = new Spot(0, col);
		if(!doesColContain(col, val)) {
			for(int row = 0; row < 9; row++) {
				if(board[row][col]==0 && !doesRowContain(row, val) && !doesColContain(col, val) && !doesBoxContain(row, col, val)) {
					countCol++;
					rowNumber = row;
				}
			}
			
		}
		if(countCol != 1) {
			cTemp = null;
		}
		if(countCol == 1) {
			cTemp.setRow(rowNumber);
		}
		return cTemp;
	}
   
	private Spot boxFill(int rowbox, int colbox, int val) {
		int countBox=0; //count does it have same number multiple time or one
		int rowNumber = 0;//value of row which will put in to row
		int colNumber = 0;//value of col which will put in to col
		int rValue = 0;//using in row loop
		int cValue = 0;//using in col loop
		Spot bTemp = new Spot(rowbox, colbox);
		if(!doesBoxContain(rowbox, colbox, val)) {
			for(rValue = rowbox/3*3 ; rValue < (rowbox/3*3)+3; rValue++){
				for(cValue = colbox/3*3; cValue < (colbox/3*3)+3; cValue++){
					if(board[rValue][cValue] == 0 && !doesColContain(cValue, val) && !doesRowContain(rValue, val) && !doesBoxContain(rValue, cValue, val)) {
						countBox++;//count the number
						rowNumber = rValue;
						colNumber = cValue;
					}
				}
			}
		}
		if(countBox != 1) {
			bTemp = null;
		}
		if(countBox == 1) {//put in number
			bTemp.setRow(rowNumber);
			bTemp.setCol(colNumber);			
		}
		return bTemp;      
	}

	public void solve() {
		//we must call boxfill,rowfill, colfill,fillspot
		boolean change = true;
		Spot spot = null;
		while(change) {//open while
			
			change = false;
			
			for(int row = 0; row < 9; row++) {//start row fill, search the row
				for(int val = 1 ; val <=9 ; val++) {//put in number 1~9 to figure out
					spot = rowFill(row, val);						
					if(spot != null) {//if spot is not null
							board[spot.getRow()][spot.getCol()] = val;
							if(board[spot.getRow()][spot.getCol()] != 0) {//check and number is not zero, change 'change' to true
								change = true;
							}
					}//closed if						
				}//closed for
			}//closed for
		 

			for(int col = 0; col < 9; col++) {//strat for loop colfill --same with row
				for(int val = 1 ; val <=9 ; val++) {//open for
					spot = colFill(col, val);
					if(spot != null) {//open if
							board[spot.getRow()][spot.getCol()] = val;
							if(board[spot.getRow()][spot.getCol()] != 0) {
								change = true;
							}
					}//closed if
				}//close for
			}//closed for

			for(int row = 0 ; row < 9; row++){//start for loop boxfill, since box method have row, col and val. use 3 loop
				for(int col = 0; col< 9; col++){//open for loop
					for(int val = 1 ; val <=9 ; val++) {//open for loop for val
						spot = boxFill(row, col, val);
						if(spot != null) {
								board[spot.getRow()][spot.getCol()] = val;
								if(board[spot.getRow()][spot.getCol()] != 0) {
									change = true;
								}
						}//closed if
					}//close for	
				}//closed for
			}//close last for
			
			//fillspot
			for(int row = 0 ; row <9 ; row++) {//do fill spot by using only row and col loop.
				for(int col = 0; col < 9; col++){
					spot = new Spot(row, col);
					if(board[row][col] == 0){
						board[row][col] = fillSpot(spot);
						if(board[spot.getRow()][spot.getCol()] != 0) {
							change = true;
						}
					}
							
				}
					
			}
		}//close while
	
	}
	
	public static String myName() {
		return "Comp 282 Monday/Wednesday 2:00pm class Haemin Lee";
	}
	
}

