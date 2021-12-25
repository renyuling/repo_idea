import java.util.Arrays;

public class SixAndNine {
    public static void main(String[] args) {
        int a = 6699;
        String s = a + "";
        int n = s.length();
        char c1 = s.charAt(2);
        char c = '6';
        int i = Integer.parseInt(c1 + "");
        System.out.println(maximum69Number(a));
        System.out.println(s.replace('6', '9'));

    }

    public static int maximum69Number (int num) {
        String s = num + "";
        StringBuffer s1 = new StringBuffer(s);
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == '6') {
                s1.deleteCharAt(i);
                s1.insert(i, '9');
                break;
            }
        }
        return Integer.parseInt(s1.toString());

    }
}
