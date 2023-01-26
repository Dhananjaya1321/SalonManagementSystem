package lk.ijse.salongeetha.entity;

public class CustomEntity {
    private String userName;
    private String eid;
    private String password;
    private String supId;
    private String description;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String aptId;
    private String sevId;
    private double price;
    private double discount;
    private String rntId;
    private int avaliableCount;
    private double pricePreDay;
    private String proId;
    private int qty;
    private String category;
    private String brand;
    private double unitPrice;
    private int qtyOnHand;
    private String empId;
    private String dob;
    private String nic;
    private String jobTitle;
    private String rentId;
    private String bokId;
    private int forHowManyDays;
    String Pay_Id;
    String Payment_method;
    String NIC;
    double Amount_due;
    String Bok_Id;
    private String date;
    private String status;
    private String bilId;
    private String title;
    private double amountPaid;
    String Apt_Id;
    private String time;
    private String Status;
    private String sevName;
    private String empName;
    public CustomEntity(String empId, String sevId, String empName, String sevName) {
        this.empId = empId;
        this.sevId = sevId;
        this.empName = empName;
        this.sevName = sevName;
    }

    public CustomEntity(int qty, int forHowManyDays, double discount, double price) {
        this.qty=qty;
        this.forHowManyDays=forHowManyDays;
        this.price=price;
        this.discount=discount;
    }
    public CustomEntity(double price, double discount) {
        this.price=price;
        this.discount=discount;
    }
    public CustomEntity(int avaliableCount, String date) {
        this.avaliableCount=avaliableCount;
        this.date=date;
    }

    public CustomEntity(String proId, String sevId, int qty, String name) {
        this.proId=proId;
        this.sevId=sevId;
        this.qty=qty;
        this.name=name;
    }


    @Override
    public String toString() {
        return "CustomEntity{" +
                "userName='" + userName + '\'' +
                ", eid='" + eid + '\'' +
                ", password='" + password + '\'' +
                ", supId='" + supId + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", aptId='" + aptId + '\'' +
                ", sevId='" + sevId + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", rntId='" + rntId + '\'' +
                ", avaliableCount=" + avaliableCount +
                ", pricePreDay=" + pricePreDay +
                ", proId='" + proId + '\'' +
                ", qty=" + qty +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyOnHand=" + qtyOnHand +
                ", empId='" + empId + '\'' +
                ", dob='" + dob + '\'' +
                ", nic='" + nic + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", rentId='" + rentId + '\'' +
                ", bokId='" + bokId + '\'' +
                ", forHowManyDays=" + forHowManyDays +
                ", Pay_Id='" + Pay_Id + '\'' +
                ", Payment_method='" + Payment_method + '\'' +
                ", NIC='" + NIC + '\'' +
                ", Amount_due=" + Amount_due +
                ", Bok_Id='" + Bok_Id + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", bilId='" + bilId + '\'' +
                ", title='" + title + '\'' +
                ", amountPaid=" + amountPaid +
                ", Apt_Id='" + Apt_Id + '\'' +
                ", time='" + time + '\'' +
                ", Status='" + Status + '\'' +
                ", sevName='" + sevName + '\'' +
                ", empName='" + empName + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAptId() {
        return aptId;
    }

    public void setAptId(String aptId) {
        this.aptId = aptId;
    }

    public String getSevId() {
        return sevId;
    }

    public void setSevId(String sevId) {
        this.sevId = sevId;
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

    public String getRntId() {
        return rntId;
    }

    public void setRntId(String rntId) {
        this.rntId = rntId;
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

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getRentId() {
        return rentId;
    }

    public void setRentId(String rentId) {
        this.rentId = rentId;
    }

    public String getBokId() {
        return bokId;
    }

    public void setBokId(String bokId) {
        this.bokId = bokId;
    }

    public int getForHowManyDays() {
        return forHowManyDays;
    }

    public void setForHowManyDays(int forHowManyDays) {
        this.forHowManyDays = forHowManyDays;
    }

    public String getPay_Id() {
        return Pay_Id;
    }

    public void setPay_Id(String pay_Id) {
        Pay_Id = pay_Id;
    }

    public String getPayment_method() {
        return Payment_method;
    }

    public void setPayment_method(String payment_method) {
        Payment_method = payment_method;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public double getAmount_due() {
        return Amount_due;
    }

    public void setAmount_due(double amount_due) {
        Amount_due = amount_due;
    }

    public String getBok_Id() {
        return Bok_Id;
    }

    public void setBok_Id(String bok_Id) {
        Bok_Id = bok_Id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBilId() {
        return bilId;
    }

    public void setBilId(String bilId) {
        this.bilId = bilId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getApt_Id() {
        return Apt_Id;
    }

    public void setApt_Id(String apt_Id) {
        Apt_Id = apt_Id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getSevName() {
        return sevName;
    }

    public void setSevName(String sevName) {
        this.sevName = sevName;
    }
}
