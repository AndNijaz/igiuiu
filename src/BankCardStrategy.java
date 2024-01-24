public class BankCardStrategy implements PaymentStrategy {
    @Override
    public double calculateTransactionFee(double transactionAmount) {
        return transactionAmount * 0.05;
    }
}
