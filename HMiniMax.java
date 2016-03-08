public final class HMiniMax {


	//The node class
    public static class Node {
        char[] state;
        //init method for Node;
        public Node() {
            this.state = new char[9];
        }
    }
    
    //terminal method which check the terminal
    //0, for not terminal, 1 for player 1, 2 for player 2, 3 for even
    public static int terminal(Node n){
    	int result = 0;
    	if((n.state[0] == n.state[1]) && (n.state[1] == n.state[2])&& (n.state[0] == 'x')){
    		result = 1;
    	}else if((n.state[0] == n.state[1]) && (n.state[1] == n.state[2])&& (n.state[0] == 'o')){
    		result = 2;
    	}else if((n.state[3] == n.state[4]) && (n.state[4] == n.state[5])&& (n.state[3] == 'x')){
    		result = 1;
    	}else if((n.state[3] == n.state[4]) && (n.state[4] == n.state[5])&& (n.state[3] == 'o')){
    		result = 2;
    	}else if((n.state[6] == n.state[7]) && (n.state[7] == n.state[8])&& (n.state[6] == 'x')){
    		result = 1;
    	}else if((n.state[6] == n.state[7]) && (n.state[7] == n.state[8])&& (n.state[6] == 'o')){
    		result = 2;
    	}else if((n.state[0] == n.state[3]) && (n.state[3] == n.state[6])&& (n.state[3] == 'x')){
    		result = 1;
    	}else if((n.state[0] == n.state[3]) && (n.state[3] == n.state[6])&& (n.state[3] == 'o')){
    		result = 2;
    	}else if((n.state[1] == n.state[4]) && (n.state[4] == n.state[7])&& (n.state[4] == 'x')){
    		result = 1;
    	}else if((n.state[1] == n.state[4]) && (n.state[4] == n.state[7])&& (n.state[4] == 'o')){
    		result = 2;
    	}else if((n.state[2] == n.state[5]) && (n.state[5] == n.state[8])&& (n.state[2] == 'x')){
    		result = 1;
    	}else if((n.state[2] == n.state[5]) && (n.state[5] == n.state[8])&& (n.state[2] == 'o')){
    		result = 2;
    	}else if((n.state[0] == n.state[4]) && (n.state[4] == n.state[8])&& (n.state[4] == 'x')){
    		result = 1;
    	}else if((n.state[0] == n.state[4]) && (n.state[4] == n.state[8])&& (n.state[4] == 'o')){
    		result = 2;
    	}else if((n.state[2] == n.state[4]) && (n.state[4] == n.state[6])&& (n.state[4] == 'x')){
    		result = 1;
    	}else if((n.state[2] == n.state[4]) && (n.state[4] == n.state[6])&& (n.state[4] == 'o')){
    		result = 2;
    	}else{
    		int numberOfempty =0;
    		for (int i = 0; i < 9; i++){
    			if (n.state[i] == '.'){
    				numberOfempty ++;
    			}
    		}
    		if (numberOfempty == 0){
    			result = 3;
    		}
    	}	
    	return result;
    }
    
    // Max method, return max utility value
    public static int max(Node n, int c){
    	int result =-11;
    	int cutoff =c;
    	Node pMax = new Node();
    	pMax.state = n.state.clone();
    	if(terminal(pMax) != 0){
    		if(terminal(pMax)==1){
    			result = 10;
    		}else if(terminal(pMax) == 2){
    			result = -10;
    		}else{
    			result = 0;
    		}
    	}else if(cutoff == 0){
    		result = eval(pMax);
    	}else{
    		for (int i = 0; i <9; i++){
        		if(pMax.state[i] == '.'){
        			Node child = new Node();
        			child.state = pMax.state.clone();
        			child.state[i] = 'x';
        			int temp = mini(child,cutoff -1);
        			if(temp > result){
        				result = temp;
        			}
        		}
        	}
    	}
    	return result;
    }
    
    // Max method, return max utility value
    public static int mini(Node n, int c){
    	int result =11;
    	int cutoff = c;
    	Node pMini = new Node();
    	pMini.state = n.state.clone();
    	if(terminal(pMini) != 0){
    		if(terminal(pMini)==1){
    			result = 10;
    		}else if(terminal(pMini) == 2){
    			result = -10;
    		}else{
    			result = 0;
    		}
    	}else if(cutoff == 0){
    		result = eval(pMini);
    	}else{
    		for (int i = 0; i <9; i++){
        		if(pMini.state[i] == '.'){
        			Node child = new Node();
        			child.state = pMini.state.clone();
        			child.state[i] = 'o';
        			int temp = max(child, cutoff -1);
        			if(temp < result){
        				result = temp;
        			}
        		}
        	}
    	}
    	return result;
    }
    public static Node nextStepMax(Node n){
    	Node parent = new Node();
    	parent.state = n.state.clone();
    	Node result = new Node();
    	int u = -11;
    	int temp = -11;
    	int cutoff = 4;
    	for(int i = 0; i < 9; i++){
    		if (parent.state[i] == '.'){
    			Node child = new Node();
    			child.state = parent.state.clone();
    			child.state[i] = 'x';
    			temp = mini(child,cutoff);
    			if(u < temp){
    				u = temp;
    				result.state = child.state.clone();
    			}
    		}
    	}
    	return result;
    }
    public static Node nextStepMini(Node n){
    	Node parent = new Node();
    	parent.state = n.state.clone();
    	Node result = new Node();
    	int u = 11;
    	int temp = 11;
    	int cutoff = 4;
    	for(int i = 0; i < 9; i++){
    		if (parent.state[i] == '.'){
    			Node child = new Node();
    			child.state = parent.state.clone();
    			child.state[i] = 'o';
    			temp = max(child, cutoff);
    			if(u > temp){
    				u = temp;
    				result.state = child.state.clone();
    			}
    		}
    	}
    	return result;
    }
    
    //print for a node state
    public static void printState(Node n){
    	for (int i =0; i < 9; i++){
        	System.out.print(n.state[i]+" ");
        	if(i%3 == 2){
            	System.out.println("");
        	}
    	}
    }

    public static int eval(Node n){
    	int result = 11;

    	char[] state = new char[9];
    	state = n.state.clone();
    	char[] r1 = new char[3];
    	r1[0] = state[0];
    	r1[1] = state[1];
    	r1[2] = state[2];
    	char[] r2 = new char[3];
    	r2[0] = state[3];
    	r2[1] = state[4];
    	r2[2] = state[5];
    	char[] r3 = new char[3];
    	r3[0] = state[6];
    	r3[1] = state[7];
    	r3[2] = state[8];
    	char[] c1 = new char[3];
    	c1[0] = state[0];
    	c1[1] = state[3];
    	c1[2] = state[6];
    	char[] c2 = new char[3];
    	c2[0] = state[1];
    	c2[1] = state[4];
    	c2[2] = state[7];
    	char[] c3 = new char[3];
    	c3[0] = state[2];
    	c3[1] = state[5];
    	c3[2] = state[8];
    	char[] d1 = new char[3];
    	d1[0] = state[0];
    	d1[1] = state[4];
    	d1[2] = state[8];
    	char[] d2 = new char[3];
    	d2[0] = state[2];
    	d2[1] = state[4];
    	d2[2] = state[6];
    	result = check2(r1) + check2(r2) + check2(r3) + check2(c1) + check2(c2) + check2(c3) + check2(d1) + check2(d2) + check1(r1) + check1(r2) + check1(r3) + check1(c1) + check1(c2) + check1(c3) + check1(d1) + check1(d2);
    	return result;
    }

    //check two same in a row, column, diag
    public static int check2(char[] check){
    	int result = 0;
    	if(check[0] == 'x'){
    		if(((check[1] == 'x') || (check[2] == 'x')) && (check[1] != 'o') && (check[2] != 'o')){
    			result = 3;
    		}
    	}else if(check[0] == 'o'){
    		if(((check[1] == 'o') || (check[2] == 'o')) && (check[1] != 'x') && (check[2] != 'x')){
    			result = -3;
    		}
    	}else{
    		if((check[1] == check[2]) && (check[1] != '.')){
    			if(check[1] =='x'){
    				result = 3;
    			}else{
    				result =-3;
    			}
    		}
    	}
    	return result;
    }
    
    //check one in a row, column, diag
    public static int check1(char[] check){
    	int result = 0;
    	if(check[0] != '.'){
    		if((check[1] == check[2]) && (check[1] == '.')){
    			if(check[0] == 'x'){
    				result = 1;
    			}else{
    				result = -1;
    			}
    		}
    	}else if(check[1] != '.'){
    		if((check[0] == check[2]) && (check[0] == '.')){
    			if(check[1] == 'x'){
    				result = 1;
    			}else{
    				result = -1;
    			}
    		}
    	}else if(check[2] != '.'){
    		if((check[1] == check[0]) && (check[0] == '.')){
    			if(check[2] == 'x'){
    				result = 1;
    			}else{
    				result = -1;
    			}
    		}
    	}
    	
		return result;
    }
    public static void main(String[] args) {
    	Node current = new Node();
    	char[] root = {'.','.','.','.','.','.','.','.','.'};
    	current.state = root.clone();
    	
    	int[] x = new int[9];
    	for (int i = 0; i <9; i++){
    		Node xChild = new Node();
    		xChild.state = current.state.clone();
    		xChild.state[i] = 'x';
    		int u = mini(xChild, 3);
    		x[i] = u;
    	}
    	System.out.println("The H-MiniMax values for X's first move");
    	for (int i =0; i < 9; i++){
        	System.out.print(x[i]+" ");
        	if(i%3 == 2){
            	System.out.println("");
        	}
    	}
    	System.out.println("The optimal play from the start to the end: ");
    	int m =0;
    	int step =1;
    	for(int i =0; i <9; i++){
    		int check = 0;
    		m = m % 2;
    		check = terminal(current);
			Node next = new Node();

    		if(check != 0){
    			break;
    		}else if(m == 0){
    			next.state = nextStepMax(current).state.clone();
    		}else if(m ==1){
    			next.state = nextStepMini(current).state.clone();	
    		}
    		System.out.println("Step "+ step +": ");
			printState(next);
    		System.out.println(" ");
			current.state = next.state.clone();
    		m++;
    		step ++;
    	}
    	
    	/*Node test = new Node();
    	char[] hh = {'x','x','o','.','o','.','x','.','.'};
    	test.state = hh.clone();
    	int tt = eval(test);
    	System.out.println("test: " + tt);*/
    	
    	


    }
}