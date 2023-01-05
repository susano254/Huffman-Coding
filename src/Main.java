import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String strDefault = "aaaabbbcde";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a stream or leave blank to use default: ");
        //read stream
        String stream = scanner.nextLine();
        //if empty just use the default value
        if(stream.isEmpty()) stream = strDefault;

        Huffman huffman = new Huffman(stream);

        System.out.println(huffman.tableCompress);
        System.out.println(huffman.tableDeCompress);
        System.out.println(huffman.compress());
        System.out.println(huffman.decompress());
        System.out.println(huffman.decompress().equals(stream));
    }
}