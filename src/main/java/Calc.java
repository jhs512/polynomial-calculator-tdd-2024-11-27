import java.util.ArrayList;
import java.util.List;

public class Calc {

    class Node {
        String op;
        Node left;
        Node right;


        public Node(String value) {
            op=value;
            left=null;
            right=null;
        }

    }

    public int run(String input) {
       int result=0;

       return result;
    }

    public int calDigit(String input) {
        int result=0;

        String[] token=makeToken(input);
        Node root=new Node(token[0]);

        for(int i=1;i<token.length;i+=2) {
            String op=token[i];
            String digit=token[i+1];

            Node newNode=new Node(op);
            newNode.left=root;
            newNode.right=new Node(digit);

            root=newNode;
        }

        result =calNode(root);

        return result;
    }

    String[] makeToken(String input) {

        final int START =0;
        final int NUMBER =1;
        final int OP =2;

        int state=START;
        StringBuilder token=new StringBuilder();
        List<String> result=new ArrayList<>();

        for(int i=0;i<input.length();i++) {
            char c=input.charAt(i);

            switch (state) {
                case START :
                    if(Character.isDigit(c) || c =='-') {
                        token.append(c);
                        state = NUMBER;
                    } else if (isOp(c)) {
                        token.append(c);
                        state= OP;
                    }
                    break;
                case NUMBER:
                    if(Character.isDigit(c) ) {
                        token.append(c);
                    } else if (isOp(c)) {
                        result.add(token.toString());
                        token.setLength(0);
                        token.append(c);
                        state=OP;
                    }
                    break;
                case OP:
                    result.add(token.toString());
                    token.setLength(0);

                    if(Character.isDigit(c) || c =='-') {
                        token.append(c);
                        state=NUMBER;
                    }
                    break;
            }
        }

        if(token.length() >0) {
            result.add(token.toString());
        }

        return result.toArray(new String[0]);
    }

    int calNode(Node node) {

        if(node.left==null) {
            return Integer.parseInt(node.op);
        }

        int left=calNode(node.left);
        int right=calNode(node.right);

        switch (node.op) {
            case "+" -> {
                return left + right;
            }
            case "-" -> {
                return left - right;
            }
            case "*" -> {
                return left * right;
            }
            case "/" -> {
                return left / right;
            }
        }

        return 0;
    }

    boolean isOp(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
