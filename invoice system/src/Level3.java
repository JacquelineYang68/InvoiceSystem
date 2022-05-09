import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;




public class Level3 {
    public static void main(String[] args) throws Exception {
        File file = new File("Level3InvoiceInformation.txt"); //read file from file path
        Scanner sc = new Scanner(file);
        List<String> allLinesList = new ArrayList<>(Collections.emptyList());
        while (sc.hasNextLine()) {
            allLinesList.add(sc.nextLine());
        }
        System.out.println(allLinesList + "\n" + allLinesList.size());

        List<Integer> indexesOfH = new ArrayList<>();
        for (int i = 0; i < allLinesList.size(); i++) {
            if (Objects.equals(allLinesList.get(i), "H")) {
                indexesOfH.add(i);
            }
        }
        indexesOfH.add(allLinesList.size());
        System.out.println(indexesOfH + "\n");

        FileWriter myWriter = new FileWriter("AllInvoicesSummaryForLevel3.txt"); //save file to local path
        int uniqueID = 0;
        for (int x = 0; x < indexesOfH.size() - 1; x++) {
            List<String> oneInvoiceList = new ArrayList<>(Collections.emptyList()); //every time in loop, list should be replaced with all contents in new invoice
            List<Integer> indexesOfD = new ArrayList<>();
            System.out.println();
            System.out.println("Another Invoice: ");
            for (int y = indexesOfH.get(x); y < indexesOfH.get(x + 1); y++) {
                if (Objects.equals(allLinesList.get(y), "H")) {
                    continue;
                }
                oneInvoiceList.add(allLinesList.get(y));
                System.out.println(allLinesList.get(y));
            }
            System.out.println(oneInvoiceList);

            for (int j = 0; j < oneInvoiceList.size(); j++) {
                if (Objects.equals(oneInvoiceList.get(j), "D")) {
                    indexesOfD.add(j);
                }
            }
            System.out.println(indexesOfD + "\n");

            try {
//                myWriter.write("Another Invoice:" + "\n");
//                for (String element : oneInvoiceList) {
//                    myWriter.write(element);
//                    myWriter.write("\n");
//                }
//                myWriter.write("\n");
                String customerName = oneInvoiceList.get(1);
                String customerStreetName = oneInvoiceList.get(2);
                String customerCity = oneInvoiceList.get(3);
                String customerProvince = oneInvoiceList.get(4);
                String customerPostalCode = oneInvoiceList.get(5);
                String invoiceDate = oneInvoiceList.get(6);
                String shippingMethod = oneInvoiceList.get(7);
                int shippingFee = 0;
                double subtotal = 0;
                double gstValue = 0;
                double pst = 0;
                double pstValue = 0;
                double finalFee = 0;
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
                LocalDate date = LocalDate.parse(invoiceDate.substring(1, invoiceDate.length() - 1), dateTimeFormatter);
                DecimalFormat fourDigits = new DecimalFormat("0000");
                DecimalFormat twoDigits = new DecimalFormat("00");
                uniqueID += 1;
                String invoiceNum = twoDigits.format(date.getMonthValue()) + String.valueOf(twoDigits.format(date.getDayOfMonth())) + String.valueOf(date.getYear() + fourDigits.format(uniqueID));
                myWriter.write("--------------------------------------------------------------------------------\r");
                //fileWriter.write("\r");
                myWriter.write("          S A F E T Y    F I R S T    S U P P L I E S    L T D    \r");
                myWriter.write("                2346 Industrial Ave, Burlington, ON L7S 2E1        \r");
                myWriter.write("                                (095)-SAFETY1                      \r");
                myWriter.write("\r");
                myWriter.write("\r");

                myWriter.write("Invoice Number                                                      Invoice Date\r");
                myWriter.write("--------------                                                      ------------\r");

                myWriter.write(" " + invoiceNum + "                                                       "
                        + invoiceDate.substring(1, invoiceDate.length() - 1)+"\r");
                myWriter.write("\r");
                myWriter.write("\r");

                myWriter.write("SOLD TO:   "+customerName+"\r");
                myWriter.write("           "+customerStreetName+"\r");
                myWriter.write("           "+customerCity+", "+ customerProvince+"\r");
                myWriter.write("           "+customerPostalCode+"\r");
                myWriter.write("\r");
                myWriter.write("\r");


                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                myWriter.write("Item Description                        Quantity          Unit Price        Total\r");
                myWriter.write("--------------------                    ---------         ----------        -----\r");
                for (int z = 0; z < indexesOfD.size(); z++) {
                    int k = indexesOfD.get(z);
                    myWriter.write(oneInvoiceList.get(k+2) +"                      "+oneInvoiceList.get(k+3) +"              "
                            +oneInvoiceList.get(k+4)+"           "+decimalFormat.format(Double.parseDouble(oneInvoiceList.get(k+3)) * Double.parseDouble(oneInvoiceList.get(k+4)))+"\r");
                    subtotal += Double.parseDouble(oneInvoiceList.get(k+3)) * Double.parseDouble(oneInvoiceList.get(k+4));
                }
                myWriter.write("\r");
                if (shippingMethod.equalsIgnoreCase("PC")) {
                    shippingFee = 35;
                }
                if (shippingMethod.equalsIgnoreCase("XP")) {
                    shippingFee = 20;
                }
                if (shippingMethod.equalsIgnoreCase("RP")) {
                    shippingFee = 10;
                }

                myWriter.write("                                                          Shipping:   "+decimalFormat.format(shippingFee)+"\r");
                myWriter.write("                                                          ----------\r");

                subtotal += shippingFee;
                //rounding-off
                BigDecimal bigDecimal = new BigDecimal(String.valueOf(subtotal)).setScale(2, RoundingMode.HALF_UP);
                myWriter.write("                                                          Subtotal:   "+ bigDecimal.doubleValue()+"\r");
                myWriter.write("\r");

                gstValue = subtotal * 0.05;
                bigDecimal = new BigDecimal(String.valueOf(gstValue)).setScale(2, RoundingMode.HALF_UP);
                myWriter.write("                                                          GST:   "+bigDecimal.doubleValue()+"\r");

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
                myWriter.write("                                                          PST:   "+bigDecimal.doubleValue()+"\r");
                myWriter.write("                                                          =========\r");

                finalFee = subtotal + gstValue + pstValue;
                bigDecimal = new BigDecimal(String.valueOf(finalFee)).setScale(2,RoundingMode.HALF_UP);
                myWriter.write("                                                          Total:   "+bigDecimal.doubleValue()+"\r");

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
        myWriter.close();
    }
}
