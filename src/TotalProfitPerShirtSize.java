import java.io.FileWriter;
import java.util.*;

public class TotalProfitPerShirtSize implements Runnable{

    private final ArrayList<Order> ordersList;
    private final Map<String, ArrayList<Order>> sizesMap;

    public TotalProfitPerShirtSize(ArrayList<Order> ordersList, Map<String, ArrayList<Order>> sizesMap){
        this.ordersList = ordersList;
        this.sizesMap = sizesMap;
    }

    private static Map<String, Double> calculateSizeProfit(Map<String, ArrayList<Order>> customerOrdersBySizeMap) {
        Map<String, Double> totalPerSizeProfitMap = new HashMap<>();

        for(String sizeKey : customerOrdersBySizeMap.keySet()){
            totalPerSizeProfitMap.put(sizeKey, calculateProfit(customerOrdersBySizeMap.get(sizeKey)));
        }


        return totalPerSizeProfitMap;


    }

    public static Double calculateProfit(ArrayList<Order> ordersList){
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

    public void generatePerSizeReport(Map<String, Double> sizeProfitMap){
        List<String> sizeOrder = Arrays.asList("XS", "S", "M", "L", "XL", "2XL", "3XL");

        Map<String, Double> sortedMap = new LinkedHashMap<>();

        for (String size : sizeOrder) {
            if (sizeProfitMap.containsKey(size)) {
                sortedMap.put(size, sizeProfitMap.get(size));
            }
        }

        StringBuilder content = new StringBuilder("Total Profit per Shirt Size:\n");
        sortedMap.forEach((key, value) -> content.append(key).append(": ").append(value).append("$").append("\n"));

        try {
            FileWriter writer = new FileWriter("Total Profit per Size Report" +
                    ".txt");
            writer.write(content.toString());
            writer.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        Map<String, Double> sizeProfitMap = calculateSizeProfit(sizesMap);
        generatePerSizeReport(sizeProfitMap);
    }
}
