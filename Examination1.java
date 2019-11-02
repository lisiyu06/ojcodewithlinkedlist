package package3;

import java.util.Scanner;

/**
 * Author: lisiyu
 * Created: 2019/11/2
 */
public class Examination1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char temp = str.charAt(i);
                if (!builder.toString().contains(temp + "")) {
                    builder.append(temp);
                }
            }
            System.out.println(builder.toString());
        }
    }
}
