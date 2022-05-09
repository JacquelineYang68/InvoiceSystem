import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class test {
//    public static void invoiceNum(){
//        int uniqueID = 0;
//        Scanner scanner = new Scanner(System.in);
//        String invoiceDate = scanner.nextLine();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
//        LocalDate date = LocalDate.parse(invoiceDate,formatter);
//
//        System.out.println(date);
//        DecimalFormat fourDigits = new DecimalFormat("0000");
//        DecimalFormat twoDigits = new DecimalFormat("00");
//        //uniqueID += 1;
//        System.out.println("Invoice Number: " + twoDigits.format(date.getMonthValue()) + String.valueOf(twoDigits.format(date.getDayOfMonth())) + String.valueOf(date.getYear()) + fourDigits.format(uniqueID));
//
//    }

    public static void inputAge(){
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.print("input: ");
            if (scanner.hasNextInt()){
                System.out.println("sqdqdfwfwrw");
                int age = scanner.nextInt();
                System.out.println("age: " + age);
                break;
            }else {
                System.out.println("please input again: ");
            }
        }
    }
    public static void main(String[] args) {
        inputAge();
    }
}
 //System.out.println("!!!!!!!!!!!input errors, please re-enter!!!!!!!!!!!");