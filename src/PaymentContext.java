public class PaymentContext {
    private final PaymentStrategy paymentStrategy;

    public PaymentContext(String paymentStrategy) {
        switch (paymentStrategy) {
            case "wallet" -> this.paymentStrategy = new WalletStrategy();
            case "bankcard" -> this.paymentStrategy = new BankCardStrategy();
            case "visa" -> this.paymentStrategy = new VisaStrategy();
            case "mastercard" -> this.paymentStrategy = new MasterCardStrategy();
            default -> this.paymentStrategy = new DefaultStrategy();
        }
    }
    public double processTransaction(double transactionAmount) {
        double transactionFee = paymentStrategy.calculateTransactionFee(transactionAmount);
        return transactionAmount - transactionFee;
    }
}
