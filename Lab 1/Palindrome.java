public class Palindrome {
    public static void main(String[] args){
        for (int i = 0; i < args.length; i++){
            String s = args[i];
            System.out.println(reverseString(s));
            System.out.println(isPalindrome(s));
        }
    }

    public static boolean isPalindrome(String s){
        String reverse_wprd = reverseString(s);
        return s.equals(reverse_wprd);
    }
    public static String reverseString(String s){
        String result = "";
        char[] s_array = s.toCharArray();
        for (int i = s_array.length - 1; i >= 0; i--){
            result += s_array[i];
        }

        return result;
    }
}



