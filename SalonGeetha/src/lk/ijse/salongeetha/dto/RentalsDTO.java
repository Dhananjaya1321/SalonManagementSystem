package lk.ijse.salongeetha.dto;

public class RentalsDTO {
    private String rntId;
    private String name;
    private String description;
    private int avaliableCount;
    private double pricePreDay;
    private double discount;

    public RentalsDTO() {
    }

    public RentalsDTO(String rntId, String name, String description, int avaliableCount, double pricePreDay, double discount) {
        this.rntId = rntId;
        this.name = name;
        this.description = description;
        this.avaliableCount = avaliableCount;
        this.pricePreDay = pricePreDay;
        this.discount = discount;
    }

    public String getRntId() {
        return rntId;
    }

    public void setRntId(String rntId) {
        this.rntId = rntId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvaliableCount() {
        return avaliableCount;
    }

    public void setAvaliableCount(int avaliableCount) {
        this.avaliableCount = avaliableCount;
    }

    public double getPricePreDay() {
        return pricePreDay;
    }

    public void setPricePreDay(double pricePreDay) {
        this.pricePreDay = pricePreDay;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "RentalsDTO{" +
                "rntId='" + rntId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", avaliableCount='" + avaliableCount + '\'' +
                ", pricePreDay=" + pricePreDay +
                ", discount=" + discount +
                '}';
    }
}
