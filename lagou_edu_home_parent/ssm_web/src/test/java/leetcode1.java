import java.util.Arrays;

/*
* 给定一个非空整数数组，除了某一个元素只出现一次外，其余每个元素均出现两次。找出那个只出现一次的元素
* 用异或
* 异或运算满足以下规律：
* （1）交换律：a^b^c=a^c^b
* （2）任何数与0异或为任何数 0^n=n
* （3）相同的数异或为0 n^n=0
* */
public class leetcode1 {
    public static void main(String[] args) {
        int[] arr = new int[]{2,3,4,1,2,3,4,1,5,6,5};
        int s1 = solution(arr);
        System.out.println(s1);
    }
    public static int solution(int[] arr) {
        int solut = 0;
        for (int i = 0; i < arr.length; i++) {
            solut = solut ^ arr[i];
        }
        int sum = Arrays.stream(arr)
                .sum();
        return solut;
    }
}
