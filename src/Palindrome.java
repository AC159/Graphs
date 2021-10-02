public class Palindrome {

    public static boolean isPalindromeRecursive(String a, String b, String original) {

        if (a.equals("")) return b.equals(original);

        char c = a.charAt(0);
        b = c + b;
        a = a.substring(1);
        return isPalindromeRecursive(a, b, original);
    }

    public static boolean isPalindrome(String s) {
        return isPalindromeRecursive(s, "", s);
    }

    public static void main(String[] args) {

        String s = "abba";
        System.out.println(isPalindrome(s));

    }

}
