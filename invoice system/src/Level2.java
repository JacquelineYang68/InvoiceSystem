import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Level2 {

    //Scanner scanner = new Scanner(System.in);

    public static String[] inputVoiceInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input invoice date(MMM DD, YYYY): ");
        String date = scanner.nextLine();

        System.out.print("Please input company or individual name: ");
        String name = scanner.nextLine();

        System.out.print("Please input street address: ");
        String street = scanner.nextLine();

        System.out.print("Please input city: ");
        String city = scanner.next();

        System.out.println("1. BC");
        System.out.println("2. AB");
        System.out.println("3. SK");
        System.out.println("4. MB");
        System.out.println("5. ON");
        System.out.println("6. QC");
        System.out.println("7. NB");
        System.out.println("8. NS");
        System.out.println("9. PE");
        System.out.println("10. NL");
//        System.out.print("Please input the province code（You only need to input one number from 1 to 10.): ");
//        int pc = scanner.nextInt();
        int pc;
        while (true){
            Scanner scanner1 = new Scanner(System.in);
            System.out.print("Please input the province code（You only need to input one number from 1 to 10.): ");
            if (scanner1.hasNextInt()){
                pc = scanner1.nextInt();
                if (pc<11&&pc>0){
                    break;
                }else {
                    System.out.println("!!!!!!!!!!!input errors, please re-enter!!!!!!!!!!!");
                }
            }else {
                System.out.println("!!!!!!!!!!!input errors, please re-enter!!!!!!!!!!!");
            }
        }

        String province = null;
        String pst = null;
        switch (pc){
            case 1:
                pst = "0.07";
                province = "BC";
                break;
            case 2:
                pst = "0";
                province = "AB";
                break;
            case 3:
                pst = "0.05";
                province = "SK";
                break;
            case 4:
                pst = "0.07";
                province = "MB";
                break;
            case  5:
                pst = "0.08";
                province = "ON";
                break;
            case 6:
                pst = "0.075";
                province = "QC";
                break;
            case 7:
                pst = "0.08";
                province = "NB";
                break;
            case 8:
                pst = "0.08";
                province = "NS";
                break;
            case 9:
                pst = "0.1";
                province = "PE";
                break;
            case 10:
                pst = "0.08";
                province = "NL";
                break;
        }

        scanner.nextLine();
        System.out.print("Please input postal code: ");
        String postalCode = scanner.nextLine();

        String[] voiceInfo = new String[]{date, name, street, city, province, postalCode, pst};
        return voiceInfo;
    }


    public static  double inputSC(){//ask for shipping code
        System.out.println("a. PC - Priority Courier");
        System.out.println("b. XP - Express Post");
        System.out.println("c. RP - Regular Post");
//        System.out.print("Please input the shipping code (You only need to input a/b/c.): ");
//        String sc = scanner.next();
//        double shippingFee = 0;
//        switch(sc){
//            case "a":
//                shippingFee += 35;
//                break;
//            case "b":
//                shippingFee += 20;
//                break;
//            case"c":
//                shippingFee += 10;
//                break;
//        }
        String sc;
        double shippingFee=0;
        while (true){
            System.out.print("Please input the shipping code (You only need to input a/b/c.): ");
            Scanner scanner1 = new Scanner(System.in);
            if (scanner1.hasNext()){
                sc = scanner1.next();
                switch(sc){
                    case "a":
                        shippingFee += 35;
                        break;
                    case "b":
                        shippingFee += 20;
                        break;
                    case"c":
                        shippingFee += 10;
                        break;
                    default:
                        System.out.println("!!!!!!!!!!!input errors, please re-enter!!!!!!!!!!!");
                        continue;
                }
                break;
            }else {
                System.out.println("!!!!!!!!!!!input errors, please re-enter!!!!!!!!!!!");
            }

        }
        return shippingFee;
    }


    public static void outputInvoice(){
        Scanner scanner = new Scanner(System.in);
        String[] voiceInfo = inputVoiceInfo();

        String[] itemDescription = new String[100];
        int[] quantity = new int[100];
        double[] unitPrice = new double[100];
        double[] total = new double[100];
        double totalPlus =0;
        double subtotal=0;
        double gst = 0;
        double pstValue = 0;
        double finalFee = 0;

        int itemNum = 0;
        for (int i = 0; ; i++) {
            System.out.print("Please input the item description: ");
            itemDescription[i] = scanner.nextLine();
            if (itemDescription[i].equals("done")) {
                break;
            }

//            System.out.print("Please input the quantity: ");
//            quantity[i] = scanner.nextInt();
            while (true){
                System.out.print("Please input the quantity: ");
                Scanner scanner1 = new Scanner(System.in);
                if (scanner1.hasNextInt()){
                    quantity[i] = scanner1.nextInt();
                    break;
                }else {
                    System.out.println("!!!!!!!!!!!input errors, please re-enter!!!!!!!!!!!");
                }
            }

//            System.out.print("Please input the unit price: ");
//            unitPrice[i] = scanner.nextDouble();
            while (true){
                System.out.print("Please input the unit price: ");
                Scanner scanner1 = new Scanner(System.in);
                if (scanner1.hasNextDouble()){
                    unitPrice[i] = scanner1.nextDouble();
                    scanner1.nextLine();
                    break;
                }else {
                    System.out.println("!!!!!!!!!!!input errors, please re-enter!!!!!!!!!!!");
                }
            }

            //scanner.nextLine();

            total[i] = quantity[i] * unitPrice[i];

            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            System.out.println("Item Description              Quantity     Unit Price     Total");
            System.out.println("--------------------          ---------    ----------     -----");
            for (int j = 0; j <= i; j++) {
                System.out.println(itemDescription[j] + "               " + quantity[j] + "           "
                        + decimalFormat.format(unitPrice[j]) + "        " + decimalFormat.format(total[j]));
            }


            totalPlus += total[i];
            //System.out.println("total plus: " + totalPlus);

            System.out.println("(If you finished, please input \"done\")");
            itemNum = i;
        }

        double shippingFee = inputSC();
        subtotal = totalPlus + shippingFee;
        gst = subtotal * 0.05;
        double doublePST = Double.parseDouble(voiceInfo[6]);
        pstValue = subtotal * doublePST;
        finalFee = subtotal + gst + pstValue;

        //create invoice number
        int uniqueID=0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(voiceInfo[0],formatter);
        //System.out.println(date);
        DecimalFormat fourDigits = new DecimalFormat("0000");
        DecimalFormat twoDigits = new DecimalFormat("00");
        uniqueID += 1;
        //System.out.println("Invoice Number: " + twoDigits.format(date.getMonthValue()) + String.valueOf(twoDigits.format(date.getDayOfMonth())) + String.valueOf(date.getYear()) + fourDigits.format(uniqueID));
        String invoiceNum = twoDigits.format(date.getMonthValue()) + String.valueOf(twoDigits.format(date.getDayOfMonth()))
                + String.valueOf(date.getYear()) + fourDigits.format(uniqueID);

        //write file into txt
        try{
            File file = new File(invoiceNum+".txt");
           FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("------------------------------------------------------------\r");
            //fileWriter.write("\r");
            fileWriter.write("    S A F E T Y    F I R S T    S U P P L I E S    L T D    \r");
            fileWriter.write("         2346 Industrial Ave, Burlington, ON L7S 2E1        \r");
            fileWriter.write("                         (095)-SAFETY1                      \r");
            fileWriter.write("\r");
            fileWriter.write("\r");

            fileWriter.write("Invoice Number                                Invoice Date\r");
            fileWriter.write("--------------                                 ------------\r");

            fileWriter.write(" " + invoiceNum + "                                " + voiceInfo[0]+"\r");
            fileWriter.write("\r");
            fileWriter.write("\r");

            fileWriter.write("SOLD TO:     "+voiceInfo[1]+"\r");
            fileWriter.write("             "+voiceInfo[2]+"\r");
            fileWriter.write("             "+voiceInfo[3]+", "+voiceInfo[4]+"\r");
            fileWriter.write("             "+voiceInfo[5]+"\r");
            fileWriter.write("\r");
            fileWriter.write("\r");


            DecimalFormat decimalFormat = new DecimalFormat("0.00");
           fileWriter.write("Item Description                Quantity     Unit Price     Total\r");
            fileWriter.write("--------------------            ---------    ----------     -----\r");
            for (int k = 0; k<=itemNum; k++){
                fileWriter.write(itemDescription[k]+"                      "+quantity[k]+"              "
                        +decimalFormat.format(unitPrice[k])+"       "+decimalFormat.format(total[k])+"\r");
            }

            fileWriter.write("\r");
            fileWriter.write("                                             Shipping:   "+decimalFormat.format(shippingFee)+"\r");
            fileWriter.write("                                             ----------\r");

            //rounding-off
            BigDecimal bigDecimal = new BigDecimal(String.valueOf(subtotal)).setScale(2, RoundingMode.HALF_UP);
            fileWriter.write("                                             Subtotal:   "+ bigDecimal.doubleValue()+"\r");
            fileWriter.write("\r");


            bigDecimal = new BigDecimal(String.valueOf(gst)).setScale(2,RoundingMode.HALF_UP);
            fileWriter.write("                                             GST:   "+bigDecimal.doubleValue()+"\r");


            bigDecimal = new BigDecimal(String.valueOf(pstValue)).setScale(2,RoundingMode.HALF_UP);
            fileWriter.write("                                             PST:   "+bigDecimal.doubleValue()+"\r");
            fileWriter.write("                                             =========\r");

            bigDecimal = new BigDecimal(String.valueOf(finalFee)).setScale(2,RoundingMode.HALF_UP);
            fileWriter.write("                                             Total:   "+bigDecimal.doubleValue()+"\r");

            fileWriter.write("\r");
            fileWriter.write("\r");
            fileWriter.write("           (Your safety & your business are important to us!)              \r");

            fileWriter.close();
            System.out.println("Your invoice has been saved as " + invoiceNum + ".txt");
//
//            FileReader fileReader = new FileReader(file);
//            char[] buf = new char[1024];
//            int num;
//            while ((num = fileReader.read(buf))!=-1){
//                for (int i=0; i<num; i++){
//                    System.out.println(buf[i]);
//                }
//            }
//
//            fileReader.close();


        }catch(IOException e){

        }finally {

        }


//        System.out.println("------------------------------------------------------------");
//        System.out.println("    S A F E T Y    F I R S T    S U P P L I E S    L T D    ");
//        System.out.println("         2346 Industrial Ave, Burlington, ON L7S 2E1        ");
//        System.out.println("                         (095)-SAFETY1                      ");
//        System.out.println();
//        System.out.println();


//        System.out.println("Invoice Number                                Invoice Date");
//        System.out.println("--------------                                 ------------");
//
//        System.out.println(" " + invoiceNum + "                                      " + voiceInfo[0]);
//        System.out.println();
//        System.out.println();

//        System.out.println("SOLD TO:     "+voiceInfo[1]);
//        System.out.println("             "+voiceInfo[2]);
//        System.out.println("             "+voiceInfo[3]+", "+voiceInfo[4]);
//        System.out.println("             "+voiceInfo[5]);
//
//        System.out.println();
//        System.out.println();

//        DecimalFormat decimalFormat = new DecimalFormat("0.00");
//        System.out.println("Item Description                     Quantity     Unit Price     Total");
//        System.out.println("------------------------             ---------    ----------     -----");
//        for (int k = 0; k<=itemNum; k++){
//            System.out.println(itemDescription[k]+"                             "+quantity[k]+"           "
//                    +decimalFormat.format(unitPrice[k])+"        "+decimalFormat.format(total[k]));
//        }


//        System.out.println();
//        System.out.println("                                             Shipping:   "+decimalFormat.format(shippingFee));
//        System.out.println("                                             ----------");

//        //rounding-off
//        BigDecimal bigDecimal = new BigDecimal(String.valueOf(totalPlus)).setScale(2, RoundingMode.HALF_UP);
//        System.out.println("                                             Subtotal:   "+ decimalFormat.format(subtotal));
//        System.out.println();
//
//
//        bigDecimal = new BigDecimal(String.valueOf(subtotal)).setScale(2,RoundingMode.HALF_UP);
//        System.out.println("                                             GST:   "+decimalFormat.format(gst));
//
//
//        bigDecimal = new BigDecimal(String.valueOf(pstValue)).setScale(2,RoundingMode.HALF_UP);
//        System.out.println("                                             PST:   "+bigDecimal.doubleValue());
//        System.out.println("                                             =========");
//
//        bigDecimal = new BigDecimal(String.valueOf(finalFee)).setScale(2,RoundingMode.HALF_UP);
//        System.out.println("                                             Total:   "+bigDecimal.doubleValue());


    }

    public static void main(String[] args) {
        outputInvoice();

    }
}
