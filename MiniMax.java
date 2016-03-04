public final class MiniMax{

    public static int isTerminal(Node n){
        int result = 0;
        int[] s = new int[9];
        s = n.state;
        if((s[0] == s[1]) && (s[0] == s[2]) && (s[0] != 0)){
            result =  s[0];
        }else if((s[3] == s[4]) && (s[3] == s[5]) && (s[3] != 0)){
            result =  s[3];
        }else if((s[6] == s[7]) && (s[6] == s[8]) && (s[6] != 0)){
            result =  s[6];
        }else if((s[0] == s[3]) && (s[0] == s[6]) && (s[0] != 0)){
            result =  s[0];
        }else if((s[1] == s[4]) && (s[1] == s[7]) && (s[1] != 0)){
            result =  s[1];
        }else if((s[2] == s[5]) && (s[2] == s[8]) && (s[2] != 0)){
            result =  s[2];
        }else if((s[0] == s[4]) && (s[0] == s[8]) && (s[0] != 0)){
            result =  s[0];
        }else if((s[2] == s[4]) && (s[2] == s[6]) && (s[2] != 0)){
            result =  s[2];
        }else {
            result =  3;
            for (int i = 0; i < 9;i++){
                if(s[i] == 0){
                    result =0;
                }
            }
        }
        
        return result;
    }
    
    
    //get the score for player 1
    public static int utility(int result){
        if (result == 1){
            return 10;
        }else if(result ==2){
            return -10;
        }else{
            return 0;
        }
    }
    //get the state of child of current node
    public static int[] getStateOfChild(Node n){
        int k = 0;
        for( int i =0; i< 9;i++){
            if (n.state[i] == 0){
                k++;
            }
        }
        int[] result = new int[k];
        int j =0;
        for (int i = 0; i < 9;i++){
            if (n.state[i] == 0){
                result[j] = i;
                j++;
            }
        }
        return result;
        
    }
    //Max method return the utility for an action
    public static int max(Node node){
    	Node n = new Node();
    	n.state = node.state;
        int utilityValue = -11;
        int result = isTerminal(n);
        int num = 0;
        int temp = -11;
        
        if ( result != 0){
            utilityValue = utility(result);
        }else{
            num = getStateOfChild(n).length;
            int[] stateArray = new int[num];
            stateArray = getStateOfChild(n);
            for(int i = 0; i < num; i++){
                Node child = new Node();
                child.state = n.state;
                child.state[stateArray[i]] = 1;
                temp = min(child);
                if (temp > utilityValue){
                    utilityValue = temp;
                }
            }
        }
        return utilityValue;
    }
    
    //Min method return the utility for an action
    public static int min(Node node){
    	Node n = new Node();
    	n.state = node.state;
        int utilityValue = 11;
        int result = isTerminal(n);
        int num = 0;
        int temp = 11;
        
        
        if ( result != 0){
            utilityValue = utility(result);
        }else{
            num = getStateOfChild(n).length;
            int[] stateArray = new int[num];
            stateArray = getStateOfChild(n);
            for(int i = 0; i < num; i++){
                Node child = new Node();
                child.state = n.state;
                child.state[stateArray[i]] = 2;
                temp = max(child);
                if (temp < utilityValue){
                    utilityValue = temp;
                }
            }
        }
        return utilityValue;
    }
    
    //print state for a node
    public static void printNode(Node n){
        for (int i = 0; i < 9;i++){
            if (n.state[i] == 1){
                System.out.print("x");
            }else if(n.state[i] == 2){
                System.out.print("O");
            }else{
                System.out.print(".");
            }
            if (i%3 == 2){
                System.out.println();
                System.out.println();
            }
        }
    }
    public static Node miniMax(Node n, int check){
    	Node result = new Node();
    	int u = isTerminal(n);
    	int miniOrmax = check % 2;
    	int tempMax = -11;
    	int tempMini = 11;
    	int maxUtility = -11;
    	int miniUtility = 11;
    	if (u != 0){
    		result.state = n.state.clone();
    	}else if (miniOrmax == 0){
    		for(int i =0; i < 9; i++){
    			if(n.state[i] ==0){
    				Node nextChild = new Node();
    				nextChild.state = n.state.clone();
    				nextChild.state[i] = 1;
    				Node tempNode = new Node();
    				tempNode.state = nextChild.state.clone();
    				tempMax = min(tempNode);
    				if(tempMax > maxUtility){
    					result.state = nextChild.state;
    					maxUtility = tempMax;
    				}
    			}
    		}
    	}else if(miniOrmax == 1){
    		for(int i =0; i < 9; i++){
    			if(n.state[i] ==0){
    				Node nextChild = new Node();
    				nextChild.state = n.state.clone();
    				nextChild.state[i] = 2;
    				Node tempNode2 = new Node();
    				tempNode2.state = nextChild.state.clone();
    				tempMini = max(tempNode2);
    				if(tempMini < miniUtility){
    					result.state = nextChild.state.clone();
    					miniUtility = tempMini;
    				}
    				
    			}
    		}
    	}
    	
    	
    	
    	return result;
    }
    
    
    
    public static void main(String[] args){
        int[] initState = {0,0,0,0,0,0,0,0,0};
        Node initNode = new Node();
        initNode.state= initState;
        Node nextNode = new Node();

        //printNode(initNode);
        
        //nextNode.state = miniMax(initNode,0).state;
        

        for (int i =0;i<9;i++){
            System.out.println("MinMax next steps: ");
        	nextNode.state = miniMax(initNode,i).state.clone();
            printNode(nextNode);
            initNode.state = nextNode.state.clone();
            
        }
        /*int u =0;
        for (int i = 0; i<9;i++){
        	Node c = new Node();
        	c.state = initState.clone();
        	c.state[i] = 1;
        	u = min(c);
        	System.out.println(u);
        	
        }*/
        /*int u = isTerminal(initNode);
    	System.out.println(u);*/

        
        
        
    }


}
