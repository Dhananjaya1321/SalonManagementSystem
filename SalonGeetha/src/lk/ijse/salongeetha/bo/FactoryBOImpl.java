package lk.ijse.salongeetha.bo;

import lk.ijse.salongeetha.bo.castom.impl.*;

public class FactoryBOImpl {
    private static FactoryBOImpl factoryBO;

    public FactoryBOImpl() {

    }

    public static FactoryBOImpl getFactoryBO() {
        if (factoryBO == null) {
            return factoryBO = new FactoryBOImpl();
        } else {
            return factoryBO;
        }
    }

    public SuperBOImpl setBO(BOImplTypes types) {
        switch (types) {
            case ADD_ADMIN:
                return new AddAdminDetailsBOImpl();
            case ADD_CUSTOMER:
                return new AddCustomerBOImpl();
            case APPOINTMENT:
                return new AppointmentBOImpl();
            case BILL:
                return new BillBOImpl();
            case BOOKING:
                return new BookingBOImpl();
            case CREATE_PASSWORD:
                return new CreateNewPasswordBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case EMPLOYEE_SERVICE:
                return new EmployeeServiceBOImpl();
            case FORGOT_PASSWORD:
                return new ForgotPasswordBOImpl();
            case HOME:
                return new HomeFormBOImpl();
            case LOGIN:
                return new LoginFormBOImpl();
            case MAIN:
                return new MainFormBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case PRODUCT_SERVICE:
                return new ProductServiceBOImpl();
            case RECEPTIONIST_HOME:
                return new ReceptionistHomeBOImpl();
            case RENTALS:
                return new RentalsBOImpl();
            case REPORTS:
                return new ReportsBOImpl();
            case SERVICE:
                return new ServiceBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case UPDATE_CUSTOMER:
                return new UpdateCustomerBOImpl();
            default:
                return null;
        }
    }
}
