import java.util.List;

public class Tree {
    Node root;

    Tree(List<Node> list){
        Node parent = null;
        while(list.size() > 1) {
            Node firstNode = findMin(list);
            Node secondNode = findMin(list);

            //construct parent for this two minimum nodes
            String parentStr = firstNode.c +"+"+ secondNode.c;
            double parentP = firstNode.p + secondNode.p;
            parent = new Node(parentStr, parentP, null);
            parent.left = firstNode;
            parent.right = secondNode;

            //add the parent to the list
            list.add(parent);
        }
        root = parent;
        root.binaryValue = "";          //this prevents printing null in front of all codes
    }

    public Node findMin(List<Node> list){
        Node node, minNode;
        int minIndex = 0;
        minNode = list.get(minIndex);
        for(int i = 0; i < list.size(); i++){
            node = list.get(i);
            if(node.p < minNode.p) {
                minNode = node;
                minIndex = i;
            }
        }
        list.remove(minIndex);
        return minNode;
    }

}
