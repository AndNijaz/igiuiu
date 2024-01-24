import java.io.FileWriter;
import java.util.ArrayList;

public class TotalRevenueReport implements Runnable {
    ArrayList<Order> ordersList;

    public TotalRevenueReport(ArrayList<Order> ordersList){
        this.ordersList = ordersList;
    }

    public int calculateToralRevenue() {
        int revenue = 0;
        for (Order o : ordersList) {
            revenue += 40;
        }
        return revenue;
    }

    public void generateRevenueReport(int revenue){
        try {
            FileWriter writer = new FileWriter("Total Revenue Report.txt");
            writer.write("Total Revenue: $" + revenue );
            writer.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        int revenue = calculateToralRevenue();
        generateRevenueReport(revenue);
    }
}
