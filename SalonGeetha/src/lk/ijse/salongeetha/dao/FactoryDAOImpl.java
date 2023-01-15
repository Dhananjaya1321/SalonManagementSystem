package lk.ijse.salongeetha.dao;

import lk.ijse.salongeetha.dao.castom.impl.*;

public class FactoryDAOImpl {
    private static FactoryDAOImpl factoryDAOImpl;

    private FactoryDAOImpl() {

    }

    public static FactoryDAOImpl getFactoryDAOImpl() {
        if (factoryDAOImpl == null) {
            return factoryDAOImpl = new FactoryDAOImpl();
        } else {
            return factoryDAOImpl;
        }
    }

    public SuperDAOImpl setDAOImpl(DAOImplTypes types) {
        switch (types) {
            case APPOINTMENT:
                return new AppointmentDAOImpl();
            case BILL:
                return new BillDAOImpl();
            case BOOKING:
                return new BookingDAOImpl();
            case BOOING_RENTALS:
                return new BookingRentalsDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case EMPLOYEE_SERVICE:
                return new EmployeeServiceDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case PRODUCT_SERVICE:
                return new ProductServiceDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case RENTALS:
                return new RentalsDAOImpl();
            case SERVICE_APPOINTMENT:
                return new ServiceAppointmentDAOImpl();
            case SERVICE:
                return new ServiceDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
