package lk.ijse.salongeetha.to;

public class ProductServiceDetail {
    private String proId;
    private String sevId;
    private String name;
    private int qty;

    public ProductServiceDetail() {
    }

    public ProductServiceDetail(String proId, String sevId, String name, int qty) {
        this.proId = proId;
        this.sevId = sevId;
        this.name = name;
        this.qty = qty;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getSevId() {
        return sevId;
    }

    public void setSevId(String sevId) {
        this.sevId = sevId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ProductServiceDetail{" +
                "proId='" + proId + '\'' +
                ", sevId='" + sevId + '\'' +
                ", qty=" + qty +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
