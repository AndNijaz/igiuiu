import javax.sound.midi.Soundbank;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Order> ordersList = new ArrayList<>();
        ordersList = readFiles();
        Map<String, ArrayList<Order>> sizesMap = createSizesMap(ordersList);


        TotalRevenueReport totalRevenue = new TotalRevenueReport(ordersList);
        TotalProfitReport totalProfit = new TotalProfitReport(ordersList);
        TotalProfitPerShirtSize totalProfitPerShirtSize = new TotalProfitPerShirtSize(ordersList, sizesMap);

        Thread t1 = new Thread(totalRevenue);
        Thread t2 = new Thread(totalProfit);
        Thread t3 = new Thread(totalProfitPerShirtSize);

        t1.start();
        t2.start();
        t3.start();
    }

    private static ArrayList<Order> readFiles() {
        ArrayList<Order> ordersList = new ArrayList<>();

        File ordersFile = new File("C:\\Users\\andel\\Desktop\\Irma Assigment\\src\\customer_orders.csv");

        try {
            Scanner s = new Scanner(ordersFile);
            if(s.hasNextLine()) s.nextLine();

            while(s.hasNextLine()){
                String line = s.nextLine();

                String[] lineParts = line.split(",");
                String fullName = lineParts[0];
                String size = lineParts[1];
                Boolean withSize = Boolean.parseBoolean(lineParts[2]);
                Boolean withHoodie = Boolean.parseBoolean(lineParts[3]);
                String payment = lineParts[4];

                Order o = new Order(fullName, size, withSize, withHoodie, payment);
                ordersList.add(o);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return ordersList;
    }
    private static Map<String, ArrayList<Order>> createSizesMap(ArrayList<Order> ordersList) {
        Map<String, ArrayList<Order>> ordersSizes = new HashMap();
        for (Order o : ordersList) {
            if(!ordersSizes.containsKey(o.getSize())){
                ordersSizes.put(o.getSize(), new ArrayList<Order>());
            }
            ordersSizes.get(o.getSize()).add(o);
        }

        return ordersSizes;
    }
}