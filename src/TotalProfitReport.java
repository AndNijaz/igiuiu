import java.io.FileWriter;
import java.util.ArrayList;

public class TotalProfitReport implements Runnable {
    ArrayList<Order> ordersList;
    public TotalProfitReport(ArrayList<Order> ordersList){
        this.ordersList = ordersList;
    }

    public Double calculateProfit(){
        Double profit = 0.0;
        Double transaction = 0.0;
        for(Order o : ordersList){
            PaymentContext p = new PaymentContext(o.getPayment());
            transaction = p.processTransaction(40);
            if(o.getWithDesign()) transaction -= 2;
            if(o.getWithHoodie()) transaction-=3;
            transaction -= 14;
            profit = profit += transaction;
        }
        return Math.round(profit * 100.0) / 100.0;
    }

    public void generateProfitReport(Double profit){
        try {
            FileWriter writer = new FileWriter("Total Profit Report.txt");
            writer.write("Total Profit: $" + profit );
            writer.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        Double profit = calculateProfit();
        generateProfitReport(profit);
    }
}
