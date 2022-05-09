import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;


public class Level4 {
    public static void main(String[] args) throws Exception {
        File invoiceInfoFile = new File("Level4InvoiceInformation.txt"); //read file from file path
        Scanner sc = new Scanner(invoiceInfoFile);
        List<String> allLinesListFromInvoiceInfo = new ArrayList<>(Collections.emptyList());
        while (sc.hasNextLine()) {
            allLinesListFromInvoiceInfo.add(sc.nextLine());
        }
        sc.close();

        File accountsFile = new File("Level4Accounts.txt"); //read file from file path
        BufferedReader br = new BufferedReader(new FileReader(accountsFile));
        String st;
        List<String> allLinesListFromAccounts = new ArrayList<>();
        while ((st = br.readLine()) != null) {
            allLinesListFromAccounts.add(st);
        }
//        System.out.println("allLinesListFromAccounts: " + allLinesListFromAccounts);
//        File accountsFile = new File("/Users/allenwang/Downloads/Level4Accounts_copy.txt"); //read file from file path
//        Scanner sc1 = new Scanner(accountsFile);
//        while (sc1.hasNextLine()) { // TODO: this method will stop scanning while scanning non utf-8 characters
//            allLinesListFromAccounts.add(sc1.nextLine());
//        }
//        sc1.close();

        File productListFile = new File("Level4ProductList.txt"); //read file from file path
        Scanner sc2 = new Scanner(productListFile);
        List<String> allLinesListFromProductList = new ArrayList<>();
        while (sc2.hasNextLine()) {
            allLinesListFromProductList.add(sc2.nextLine());
        }
        sc2.close();

        Print print = new Print();
        int invoiceCopySerialNumber = 1;

        for (int i = 0; i < allLinesListFromInvoiceInfo.size(); i += 5) {
            String invoiceNum = allLinesListFromInvoiceInfo.get(i);
            Path path = Paths.get( invoiceNum + ".txt");
            if (Files.notExists(path)) {
                invoiceCopySerialNumber = 1;
//                System.out.println("invoiceNum: " + invoiceNum);
                FileWriter myWriter = new FileWriter(invoiceNum + ".txt");
                String invoiceDate = allLinesListFromInvoiceInfo.get(i + 1);
                String accountNum = allLinesListFromInvoiceInfo.get(i + 2);
                String productNum = allLinesListFromInvoiceInfo.get(i + 3);
                String quantitySold = allLinesListFromInvoiceInfo.get(i + 4);
//                System.out.println("accountNum: " + accountNum);
//                System.out.println("allLinesListFromInvoiceInfo: " + allLinesListFromInvoiceInfo);
//                System.out.println("allLinesListFromAccounts: " + allLinesListFromAccounts);
//                System.out.println("allLinesListFromProductList: " + allLinesListFromProductList);

                for (int j = 0; j < allLinesListFromAccounts.size(); j++) {
                    if (Objects.equals(accountNum.trim(), allLinesListFromAccounts.get(j))) {
//                        System.out.println("accountNum = info from accounts file: " + Objects.equals(accountNum.trim(), allLinesListFromAccounts.get(j)));
                        String customerName = allLinesListFromAccounts.get(j + 1);
                        String customerStreetName = allLinesListFromAccounts.get(j + 2);
                        String customerCity = allLinesListFromAccounts.get(j + 3);
                        String customerProvince = allLinesListFromAccounts.get(j + 4);
                        String customerPostalCode = allLinesListFromAccounts.get(j + 5);
                        String shippingMethod = allLinesListFromAccounts.get(j + 6);

//                        System.out.println("customerStreetName: " + customerStreetName);

                        double shippingFee = calculateShippingFee(customerProvince, shippingMethod);
//                        System.out.println("customerProvince: " + customerProvince);
//                        System.out.println("shippingFee: " + shippingFee);

                        print.printInvoice(myWriter, customerName, customerStreetName, customerCity, customerProvince, customerPostalCode, invoiceDate,
                                shippingFee, invoiceNum, allLinesListFromProductList, productNum, quantitySold);
                    }
                }
                myWriter.close();
            } else {
                invoiceCopySerialNumber += 1;
                FileWriter myWriter = new FileWriter( invoiceNum + "_(" + invoiceCopySerialNumber + ")" + ".txt");
                String invoiceDate = allLinesListFromInvoiceInfo.get(i + 1);
                String accountNum = allLinesListFromInvoiceInfo.get(i + 2);
                String productNum = allLinesListFromInvoiceInfo.get(i + 3);
                String quantitySold = allLinesListFromInvoiceInfo.get(i + 4);
//                System.out.println("accountNum: " + accountNum);
//                System.out.println("allLinesListFromInvoiceInfo: " + allLinesListFromInvoiceInfo);
//                System.out.println("allLinesListFromAccounts: " + allLinesListFromAccounts);
//                System.out.println("allLinesListFromProductList: " + allLinesListFromProductList);

                for (int j = 0; j < allLinesListFromAccounts.size(); j++) {
                    if (Objects.equals(accountNum.trim(), allLinesListFromAccounts.get(j))) {
//                        System.out.println("accountNum = info from accounts file: " + Objects.equals(accountNum.trim(), allLinesListFromAccounts.get(j)));
                        String customerName = allLinesListFromAccounts.get(j + 1);
                        String customerStreetName = allLinesListFromAccounts.get(j + 2);
                        String customerCity = allLinesListFromAccounts.get(j + 3);
                        String customerProvince = allLinesListFromAccounts.get(j + 4);
                        String customerPostalCode = allLinesListFromAccounts.get(j + 5);
                        String shippingMethod = allLinesListFromAccounts.get(j + 6);

//                        System.out.println("customerStreetName: " + customerStreetName);

                        double shippingFee = calculateShippingFee(customerProvince, shippingMethod);
//                        System.out.println("customerProvince: " + customerProvince);
//                        System.out.println("shippingFee: " + shippingFee);

                        print.printInvoice(myWriter, customerName, customerStreetName, customerCity, customerProvince, customerPostalCode, invoiceDate,
                                shippingFee, invoiceNum, allLinesListFromProductList, productNum, quantitySold);
                    }
                }
                myWriter.close();
            }

//            if (i != 0 && Objects.equals(invoiceNum, allLinesListFromInvoiceInfo.get(i - 5))) { }
        }
    }

    public static double calculateShippingFee(String customerProvince, String shippingMethod) {
        double shippingFee = 0;
        switch (customerProvince){
            case "BC":
                switch (shippingMethod) {
                    case "PC":
                        shippingFee = 45.15;
                        break;
                    case "XP":
                        shippingFee = 27.80;
                        break;
                    case "RP":
                        shippingFee = 12.85;
                        break;
                }
                break;
            case "AB":
                switch (shippingMethod) {
                    case "PC":
                        shippingFee = 39;
                        break;
                    case "XP":
                        shippingFee = 25.65;
                        break;
                    case "RP":
                        shippingFee = 12.25;
                        break;
                }
                break;
            case "SK":
                switch (shippingMethod) {
                    case "PC":
                        shippingFee = 40.85;
                        break;
                    case "XP":
                        shippingFee = 25.90;
                        break;
                    case "RP":
                        shippingFee = 11.60;
                        break;
                }
                break;
            case "MB":
                switch (shippingMethod) {
                    case "PC":
                        shippingFee = 36.75;
                        break;
                    case "XP":
                        shippingFee = 21.30;
                        break;
                    case "RP":
                        shippingFee = 10.80;
                        break;
                }
                break;
            case "ON":
                switch (shippingMethod) {
                    case "PC":
                        shippingFee = 17.00;
                        break;
                    case "XP":
                        shippingFee = 9.00;
                        break;
                    case "RP":
                        shippingFee = 7.45;
                        break;
                }
                break;
            case "QC":
                switch (shippingMethod) {
                    case "PC":
                        shippingFee = 18.70;
                        break;
                    case "XP":
                        shippingFee = 9.85;
                        break;
                    case "RP":
                        shippingFee = 8.40;
                        break;
                }
                break;
            case "NB":
            case "PE":
                switch (shippingMethod) {
                    case "PC":
                        shippingFee = 34.80;
                        break;
                    case "XP":
                        shippingFee = 20.50;
                        break;
                    case "RP":
                        shippingFee = 10.55;
                        break;
                }
                break;
            case "NS":
                switch (shippingMethod) {
                    case "PC":
                        shippingFee = 36.75;
                        break;
                    case "XP":
                        shippingFee = 21.30;
                        break;
                    case "RP":
                        shippingFee = 10.50;
                        break;
                }
                break;
            case "NL":
                switch (shippingMethod) {
                    case "PC":
                        shippingFee = 39.00;
                        break;
                    case "XP":
                        shippingFee = 25.65;
                        break;
                    case "RP":
                        shippingFee = 12.25;
                        break;
                }
                break;
        }
        return shippingFee;
    }
}

class Print {
    public void printInvoice (FileWriter myWriter, String customerName, String customerStreetName, String customerCity,
                              String customerProvince, String customerPostalCode, String invoiceDate, double shippingFee,
                              String invoiceNum, List<String> allLinesListFromProductList, String productNum, String quantitySold) {

        for (int x = 0; x < allLinesListFromProductList.size(); x++) {
            if (Objects.equals(productNum, allLinesListFromProductList.get(x))) {
                String productName = allLinesListFromProductList.get(x+1);
                String productUnitPrice = allLinesListFromProductList.get(x+2);
                try {
                    double subtotal = 0;
                    double gstValue = 0;
                    double pst = 0;
                    double pstValue = 0;
                    double finalFee = 0;
//            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
//            LocalDate date = LocalDate.parse(invoiceDate.substring(1, invoiceDate.length() - 1), dateTimeFormatter);
//            DecimalFormat fourDigits = new DecimalFormat("0000");
//            DecimalFormat twoDigits = new DecimalFormat("00");
//            uniqueID += 1;
//            String invoiceNum = twoDigits.format(date.getMonthValue()) + String.valueOf(twoDigits.format(date.getDayOfMonth())) + String.valueOf(date.getYear() + fourDigits.format(uniqueID));
                    myWriter.write("----------------------------------------------------------------------\r");
                    //fileWriter.write("\r");
                    myWriter.write("           S A F E T Y    F I R S T    S U P P L I E S    L T D    \r");
                    myWriter.write("                2346 Industrial Ave, Burlington, ON L7S 2E1        \r");
                    myWriter.write("                                (095)-SAFETY1                      \r");
                    myWriter.write("\r");
                    myWriter.write("\r");

                    myWriter.write("Invoice Number                                           Invoice Date\r");
                    myWriter.write("--------------                                           ------------\r");

                    myWriter.write(" " + invoiceNum + "                                                    " + invoiceDate +"\r");
                    myWriter.write("\r");
                    myWriter.write("\r");

                    myWriter.write("SOLD TO:     "+customerName+"\r");
                    myWriter.write("             "+customerStreetName+"\r");
                    myWriter.write("             "+customerCity+", "+ customerProvince+"\r");
                    myWriter.write("             "+customerPostalCode+"\r");
                    myWriter.write("\r");
                    myWriter.write("\r");


                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    myWriter.write("Item Description                        Quantity     Unit Price     Total\r");
                    myWriter.write("--------------------                    ---------    ----------     -----\r");
                    myWriter.write(productName +"                      "+ quantitySold +"              "
                            + productUnitPrice +"           "+decimalFormat.format(Double.parseDouble(quantitySold) * Double.parseDouble(productUnitPrice))+"\r");
                    subtotal += Double.parseDouble(quantitySold) * Double.parseDouble(productUnitPrice);
                    myWriter.write("\r");

                    myWriter.write("                                                  Shipping:   "+decimalFormat.format(shippingFee)+"\r");
                    myWriter.write("                                                  ----------\r");

                    subtotal += shippingFee;
                    //rounding-off
                    BigDecimal bigDecimal = new BigDecimal(String.valueOf(subtotal)).setScale(2, RoundingMode.HALF_UP);
                    myWriter.write("                                                  Subtotal:   "+ bigDecimal.doubleValue()+"\r");
                    myWriter.write("\r");

                    gstValue = subtotal * 0.05;
                    bigDecimal = new BigDecimal(String.valueOf(gstValue)).setScale(2, RoundingMode.HALF_UP);
                    myWriter.write("                                                  GST:   "+bigDecimal.doubleValue()+"\r");

                    switch (customerProvince){
                        case "BC":
                            pst = 0.07;
                            break;
                        case "AB":
                            pst = 0;
                            break;
                        case "SK":
                            pst = 0.05;
                            break;
                        case "MB":
                            pst = 0.07;
                            break;
                        case "ON":
                            pst = 0.08;
                            break;
                        case "QC":
                            pst = 0.075;
                            break;
                        case "NB":
                            pst = 0.08;
                            break;
                        case "NS":
                            pst = 0.08;
                            break;
                        case "PE":
                            pst = 0.1;
                            break;
                        case "NL":
                            pst = 0.08;
                            break;
                    }
                    pstValue = subtotal * pst;
                    bigDecimal = new BigDecimal(String.valueOf(pstValue)).setScale(2,RoundingMode.HALF_UP);
                    myWriter.write("                                                  PST:   "+bigDecimal.doubleValue()+"\r");
                    myWriter.write("                                                  =========\r");

                    finalFee = subtotal + gstValue + pstValue;
                    bigDecimal = new BigDecimal(String.valueOf(finalFee)).setScale(2,RoundingMode.HALF_UP);
                    myWriter.write("                                                  Total:   "+bigDecimal.doubleValue()+"\r");

                    myWriter.write("\r");
                    myWriter.write("\r");
                    myWriter.write("           (Your safety & your business are important to us!)              \r");
                    myWriter.write("\r");
                    myWriter.write("\r");

                    System.out.println("Successfully wrote to the file");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        }
    }
}
