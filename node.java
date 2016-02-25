public class Node{
    
    private int[] state;
    private Node parent;
    
    //init for root
    public Node(int[] s){
        state = s;
        parent = null;
    }
    //init for child node
    public Node(int[] s, Node p){
        state = s;
        parent = p;
    }
    //get the parent
    public Node getParent(){
        return this.parent;
        
    }
    //get the child
    public int[] getState(){
        return this.state;
    }



}