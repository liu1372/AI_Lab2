import java.util.ArrayList;


public final class MiniMax{

    public static int isTerminal(Node n){
        int result = 0;
        int[] s = new int[9];
        s = n.state;
        if((s[0] == s[1]) && (s[0] == s[2])){
            result =  s[0];
        }else if((s[3] == s[4]) && (s[3] == s[5])){
            result =  s[3];
        }else if((s[6] == s[7]) && (s[6] == s[8])){
            result =  s[6];
        }else if((s[0] == s[3]) && (s[0] == s[6])){
            result =  s[0];
        }else if((s[1] == s[4]) && (s[1] == s[7])){
            result =  s[1];
        }else if((s[2] == s[5]) && (s[2] == s[8])){
            result =  s[2];
        }else if((s[0] == s[4]) && (s[0] == s[8])){
            result =  s[0];
        }else if((s[2] == s[4]) && (s[2] == s[6])){
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
                result[j] = n.state[i];
                j++;
            }
        }
        return result;
        
    }
    //Max method return the untilioty for an action
    public static int Max(Node n){
        int utilityValue = -10;
        int result = isTerminal(n);
        int num = 0;
        int temp = -10;
        
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
                temp = Min(child);
                if (temp > utilityValue){
                    utilityValue = temp;
                }
            }
        }
        return utilityValue;
    }
    
    //Min method return the untilioty for an action
    public static int Min(Node n){
        int utilityValue = -10;
        int result = isTerminal(n);
        int num = 0;
        int temp = -10;
        
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
                temp = Max(child);
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
    
    
    
    public static void main(String[] args){
        int[] initState = {0,0,0,0,0,0,0,0,0};
        Node initNode = new Node();
        initNode.state= initState;
        
        int score;
        Node[] movehistory = new Node[9];
        System.out.println("MinMax steps: ");
        printNode(initNode);
        score = Max(initNode);
        System.out.print(score);
        
        
        
    }


}