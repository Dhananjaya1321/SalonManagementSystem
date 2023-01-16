package lk.ijse.salongeetha.entity;

public class Service {
    private String sevId;
    private String description;
    private String name;
    private double price;
    private double discount;

    public Service() {
    }

    public Service(String sevId, String description, String name, double price, double discount) {
        this.sevId = sevId;
        this.description = description;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Service{" +
                "sevId='" + sevId + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }

    public String getSevId() {
        return sevId;
    }

    public void setSevId(String sevId) {
        this.sevId = sevId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
