public class Node {
    public String c;
    public double p;
    public String binaryValue;
    public Node left, right;

    Node(String s, double p, String binaryValue){
        this.c = s;
        this.p = p;
        this.binaryValue = binaryValue;
    }


    @Override
    public String toString() {
        if(binaryValue == null) binaryValue = "";
        return "{" + this.c + ": " + this.p + ", " + this.binaryValue + " }";

    }
}
