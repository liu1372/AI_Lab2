
public final class MiniMax{
    
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
    public static int max(Node n){
        int result =-11;
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
        }else{
            for (int i = 0; i <9; i++){
                if(pMax.state[i] == '.'){
                    Node child = new Node();
                    child.state = pMax.state.clone();
                    child.state[i] = 'x';
                    int temp = mini(child);
                    if(temp > result){
                        result = temp;
                    }
                }
            }
        }
        return result;
    }
    
    // Max method, return max utility value
    public static int mini(Node n){
        int result =11;
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
        }else{
            for (int i = 0; i <9; i++){
                if(pMini.state[i] == '.'){
                    Node child = new Node();
                    child.state = pMini.state.clone();
                    child.state[i] = 'o';
                    int temp = max(child);
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
        for(int i = 0; i < 9; i++){
            if (parent.state[i] == '.'){
                Node child = new Node();
                child.state = parent.state.clone();
                child.state[i] = 'x';
                temp = mini(child);
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
        for(int i = 0; i < 9; i++){
            if (parent.state[i] == '.'){
                Node child = new Node();
                child.state = parent.state.clone();
                child.state[i] = 'o';
                temp = max(child);
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
            System.out.print(n.state[i]);
            if(i%3 == 2){
                System.out.println("");
            }
        }
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
            int u = mini(xChild);
            x[i] = u;
        }
        System.out.println("The MiniMax values for X's first move");
        for (int i =0; i < 9; i++){
            System.out.print(x[i]);
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
        
        
        
        
    }
}