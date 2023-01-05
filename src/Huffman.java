import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Huffman implements Compressor {
    StringBuilder compressedStream;     //the output stream after compression
    String stream;                      //the input stream
    Tree binaryTree;                    //tree used to generate the code

    //tables
    public Map<Character, Node> tableCompress = new HashMap();
    public Map<String, Node> tableDeCompress = new HashMap();


    Huffman(String stream){
        this.stream = stream;

        //fill the compress Table
        fillCompressTable();

        //construct tree from the above tableCompress
        this.binaryTree = new Tree(new ArrayList<>(tableCompress.values()));

        //encode each node left:0 right:1
        encodeBinary(binaryTree.root);

        //fill the Decompress table for decompressing
        fillDeCompressTable(new ArrayList<>(tableCompress.values()));
    }


    //each char associated with a Node with a given binaryValue for the compress function
    private void fillCompressTable() {
        char c;
        double p;

        for(int i = 0; i < stream.length(); i++){
            c = stream.charAt(i);

            //if character already in table then skip it
            if(tableCompress.containsKey(c)) continue;
            //else calculate its probability and add it to the table
            p = calcProbability(c);

            tableCompress.put(c, new Node(String.valueOf(c), p, ""));
        }
    }


    //just reverse the CompressTable (from binaryValue to Node) for easier implementation of the decompress function
    private void fillDeCompressTable(ArrayList<Node> nodeList) {
        for(int i = 0; i < nodeList.size(); i++)
            tableDeCompress.put(nodeList.get(i).binaryValue, nodeList.get(i));
    }


    //just counts the number of occurrence of the given character
    public double calcProbability(char c) {
        double charCounter = 0;

        //each time similar char found increment counter
        for(int i = 0; i < stream.length(); i++)
            if(c == stream.charAt(i)) charCounter++;

        //finally divide by length to return probability
        return charCounter/stream.length();
    }


    //code the tree values
    private void encodeBinary(Node node){
        if(node != null) {
            //each time you go left you add a 0 and complete recursively
            if (node.left != null){
                node.left.binaryValue = node.binaryValue + "0";
                encodeBinary(node.left);
            }
            //each time you go right you add a 1 and complete recursively
            if(node.right != null) {
                node.right.binaryValue = node.binaryValue + "1";
                encodeBinary(node.right);
            }
        }
    }


    @Override
    public String compress() {
        compressedStream = new StringBuilder();
        Node node;
        String code;
        char c;

        //for each character just get it's corresponding binary from the table created above and add to the output
        for(int i = 0; i < stream.length(); i++){
            c = stream.charAt(i);                   //get the character from the input
            node = tableCompress.get(c);            //get its corresponding node in the Table
            code = node.binaryValue;                //get its code from the node fields
            compressedStream.append(code);          //finally pipe it to the output
        }

        return compressedStream.toString();
    }


    @Override
    public String decompress() {
        StringBuilder output = new StringBuilder();
        StringBuilder match = new StringBuilder();


        //search the table for the given code and add it's corresponding char to the output
        for(int i = 0; i < compressedStream.length(); i++){
            match.append(compressedStream.charAt(i)); //get the current binary (0 or 1)

            //if the table contains a char with the given binary code return it and reset the match buffer
            if(tableDeCompress.containsKey(match.toString())){
                Node node = tableDeCompress.get(match.toString());
                output.append(node.c);
                match.delete(0, match.length());
            }
        }

        return output.toString();
    }
}
