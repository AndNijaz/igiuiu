public class Order {
    private final String fullName;
    private final String size;
    private final Boolean withDesign;
    private final Boolean withHoodie;
    private final String payment;

    public Order(String fullName, String size, Boolean withDesign, Boolean withHoodie, String payment) {
        this.fullName = fullName;
        this.size = size;
        this.withDesign = withDesign;
        this.withHoodie = withHoodie;
        this.payment = payment;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSize() {
        return size;
    }

    public Boolean getWithDesign() {
        return withDesign;
    }

    public Boolean getWithHoodie() {
        return withHoodie;
    }

    public String getPayment() {
        return payment;
    }

    @Override
    public String toString() {
        return "Order{" +
                "fullName='" + fullName + '\'' +
                ", size='" + size + '\'' +
                ", withSize=" + withDesign +
                ", withHoodie=" + withHoodie +
                ", payment='" + payment + '\'' +
                '}';
    }
}
