package lk.ijse.salongeetha.entity;

public class Product {
    private String proId;
    private String description;
    private String category;
    private String brand;
    private String supId;
    private double unitPrice;
    private int qtyOnHand;

    public Product() {
    }

    public Product(String proId, String description, String category, String brand, String supId, double unitPrice, int qtyOnHand) {
        this.proId = proId;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.supId = supId;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "proId='" + proId + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", supId='" + supId + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
}

