import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String s) {

        Stack<String> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            String c = s.charAt(i) + "";

            if ( (c.equals("]") || c.equals("}") || c.equals(")")) && stack.empty()) {
                return false;
            }

            if (c.equals("[") || c.equals("{") || c.equals("(")) {
                stack.push(c);
                continue;
            }

            String peek = stack.peek();

            if ( (!peek.equals("[") && c.equals("]")) || (!peek.equals("{") && c.equals("}")) || (!peek.equals("(") && c.equals(")")) ) {
                return false;
            }

            if ( (peek.equals("[") && c.equals("]")) || (peek.equals("{") && c.equals("}")) || (peek.equals("(") && c.equals(")")) ) {
                stack.pop();
            }

        }
        return true;

    }

    public static void main(String[] args) {

        String s = "[{}(])";
        boolean b = isBalanced(s);

        if (b) System.out.println("String " + s + " is balanced.");
        else System.out.println("String " + s + " is not balanced.");

        String s2 = "[{}()]";
        boolean b2 = isBalanced(s2);

        if (b2) System.out.println("String " + s2 + " is balanced.");
        else System.out.println("String " + s2 + " is not balanced.");

    }

}
