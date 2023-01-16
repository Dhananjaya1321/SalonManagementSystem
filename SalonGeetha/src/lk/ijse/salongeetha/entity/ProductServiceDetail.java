package lk.ijse.salongeetha.entity;

public class ProductServiceDetail {
    private String proId;
    private String sevId;
    private int qty;

    public ProductServiceDetail() {
    }

    public ProductServiceDetail(String proId, String sevId, int qty) {
        this.proId = proId;
        this.sevId = sevId;
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
}
