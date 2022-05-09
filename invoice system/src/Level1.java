import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Level1 {
    //Scanner scanner = new Scanner(System.in);
    //double shippingFee = 0;
    public  static double inputPC(){ //ask for province code
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

        //System.out.print("Please input the province code（You only need to input one number from 1 to 10.): ");
        //int pc = scanner.nextInt();
        //checking error
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


        double pst = 0;
        switch (pc){
            case 1:
                pst = 0.07;
                break;
            case 2:
                pst = 0;
                break;
            case 3:
                pst = 0.05;
                break;
            case 4:
                pst = 0.07;
                break;
            case  5:
                pst = 0.08;
                break;
            case 6:
                pst = 0.075;
                break;
            case 7:
                pst = 0.08;
                break;
            case 8:
                pst = 0.08;
                break;
            case 9:
                pst = 0.1;
                break;
            case 10:
                pst = 0.08;
                break;

        }
        return pst;
    }

    public static  double inputSC(){//ask for shipping code
        Scanner scanner = new Scanner(System.in);
        System.out.println("a. PC - Priority Courier");
        System.out.println("b. XP - Express Post");
        System.out.println("c. RP - Regular Post");
//        System.out.print("Please input the shipping code (You only need to input a/b/c.): ");
//        String sc = scanner.next();

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


    public static void showInfo(){
        Scanner scanner = new Scanner(System.in);
        String[] itemDescription = new String[100];
        int[] quantity = new int[100];
        double[] unitPrice = new double[100];
        double[] total = new double[100];
        double shippingFee = inputSC();
        System.out.println("---------------------------------------------------------------------------------");
        double pst = inputPC();
        System.out.println("---------------------------------------------------------------------------------");
        //scanner.nextLine();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double totalPlus =0;
        int itemNum = 0;
        double subtotal=0;
        double gst = 0;
        double pstValue = 0;
        double finalFee = 0;

        for (int i=0;;i++){
            System.out.print("Please input the item description: ");
            itemDescription[i] = scanner.nextLine();

            if (itemDescription[i].equals("done")){
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




            total[i] = quantity[i] * unitPrice[i];



            System.out.println("Item Description                     Quantity     Unit Price     Total");
            System.out.println("------------------------             ---------    ----------     -----");
            for (int j = 0; j<=i; j++){
                System.out.println(itemDescription[j]+"                             "+quantity[j]+"           "
                        +decimalFormat.format(unitPrice[j])+"        "+decimalFormat.format(total[j]));
            }

            totalPlus += total[i];
            //System.out.println("total plus: " + totalPlus);
            subtotal = totalPlus + shippingFee;
            gst = subtotal * 0.05;
            pstValue = subtotal * pst;
            finalFee = subtotal + gst + pstValue;

            System.out.println("(If you finished, please input \"done\")");

            itemNum = i;
        }

        System.out.println("Item Description                     Quantity     Unit Price     Total");
        System.out.println("------------------------             ---------    ----------     -----");
        for (int k = 0; k<=itemNum; k++){
            System.out.println(itemDescription[k]+"                             "+quantity[k]+"           "
                    +decimalFormat.format(unitPrice[k])+"        "+decimalFormat.format(total[k]));
        }

        System.out.println();
        System.out.println("                                             Shipping:   "+decimalFormat.format(shippingFee));
        System.out.println("                                             ----------");

        //rounding-off
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(subtotal)).setScale(2, RoundingMode.HALF_UP);
        System.out.println("                                             Subtotal:   "+ bigDecimal.doubleValue());
        System.out.println();


        bigDecimal = new BigDecimal(String.valueOf(gst)).setScale(2,RoundingMode.HALF_UP);
        System.out.println("                                             GST:   "+bigDecimal.doubleValue());


        bigDecimal = new BigDecimal(String.valueOf(pstValue)).setScale(2,RoundingMode.HALF_UP);
        System.out.println("                                             PST:   "+bigDecimal.doubleValue());
        System.out.println("                                             =========");

        bigDecimal = new BigDecimal(String.valueOf(finalFee)).setScale(2,RoundingMode.HALF_UP);
        System.out.println("                                             Total:   "+bigDecimal.doubleValue());

    }

    public static void main(String[] args){
        //Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------------------------------------------------------------");
        showInfo();



    }
}
