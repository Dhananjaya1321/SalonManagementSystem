package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.BookingBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.BookingDAO;
import lk.ijse.salongeetha.dao.castom.BookingRentalsDAO;
import lk.ijse.salongeetha.dao.castom.CustomerDAO;
import lk.ijse.salongeetha.dao.castom.RentalsDAO;
import lk.ijse.salongeetha.dao.castom.impl.RentalsDAOImpl;
import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.entity.Book;
import lk.ijse.salongeetha.entity.BookRentalsDetail;
import lk.ijse.salongeetha.entity.Customer;
import lk.ijse.salongeetha.entity.Rentals;
import lk.ijse.salongeetha.dto.BookDTO;
import lk.ijse.salongeetha.dto.BookRentalsDetailDTO;
import lk.ijse.salongeetha.dto.CustomerDTO;
import lk.ijse.salongeetha.dto.RentalsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookingBOImpl implements BookingBO {
    RentalsDAO rentalsDAO = (RentalsDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.RENTALS);
    CustomerDAO customerDAO = (CustomerDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.CUSTOMER);
    BookingDAO bookingDAO = (BookingDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.BOOKING);
    BookingRentalsDAO bookingRentalsDAO = (BookingRentalsDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.BOOING_RENTALS);
    ArrayList<Rentals> rentals;
    ArrayList<RentalsDTO> rentalsDTOS;
    ArrayList<Customer> customers;
    ArrayList<CustomerDTO> customerDTOS;

    @Override
    public ArrayList<RentalsDTO> searchRentalsDetails(RentalsDTO dto) throws SQLException, ClassNotFoundException {
        /*ArrayList<Rentals>*/
        rentals = rentalsDAO.searchRentalsDetails(new Rentals(dto.getRntId(), dto.getName(), dto.getDescription(),
                dto.getAvaliableCount(), dto.getPricePreDay(), dto.getDiscount()));
        /*ArrayList<RentalsDTO>*/
        rentalsDTOS = new ArrayList<>();
        for (Rentals r : rentals) {
            rentalsDTOS.add(new RentalsDTO(r.getRntId(), r.getName(), r.getDescription(), r.getAvaliableCount(),
                    r.getPricePreDay(), r.getDiscount()));
        }
        return rentalsDTOS;

    }

    @Override
    public ArrayList<CustomerDTO> searchCustomerDetails(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        /*ArrayList<Customer>*/
        customers = customerDAO.searchCustomerDetails(new Customer(dto.getNic(), dto.getName(), dto.getPhoneNumber(), dto.getEmail()
                , dto.getDob(), dto.getUserName()));
        /*ArrayList<CustomerDTO>*/
        customerDTOS = new ArrayList<>();
        for (Customer c : customers) {
            customerDTOS.add(new CustomerDTO(c.getNic(), c.getName(), c.getPhoneNumber(), c.getEmail(),
                    c.getDob(), c.getUserName()));
        }
        return customerDTOS;
    }

    @Override
    public String checkIdBooking() throws SQLException, ClassNotFoundException {
        return bookingDAO.checkId();
    }

    @Override
    public ArrayList<RentalsDTO> getAllRentals() throws SQLException, ClassNotFoundException {
        /*ArrayList<Rentals>*/
        rentals = rentalsDAO.getAll();
        /*ArrayList<RentalsDTO>*/
        rentalsDTOS = new ArrayList<>();
        for (Rentals r : rentals) {
            rentalsDTOS.add(new RentalsDTO(r.getRntId(), r.getName(), r.getDescription(), r.getAvaliableCount(),
                    r.getPricePreDay(), r.getDiscount()));
        }
        return rentalsDTOS;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        /*ArrayList<Customer>*/
        customers = customerDAO.getAll();
        /*ArrayList<CustomerDTO>*/
        customerDTOS = new ArrayList<>();
        for (Customer c : customers) {
            customerDTOS.add(new CustomerDTO(c.getNic(), c.getName(), c.getPhoneNumber(), c.getEmail(), c.getDob(), c.getUserName()));
        }
        return customerDTOS;
    }

    @Override
    public boolean addBooking(BookDTO bookDTO, ArrayList<BookRentalsDetailDTO> bookRentalsDetailDTOS) throws SQLException, ClassNotFoundException {
        DBConnection.getDBConnection().getConnection().setAutoCommit(false);
        try {

            Book book = new Book(bookDTO.getBokId(), bookDTO.getDate(), bookDTO.getNic(), bookDTO.getStatus());
            ArrayList<BookRentalsDetail> bookRentalsDetails = new ArrayList<>();
            for (BookRentalsDetailDTO b : bookRentalsDetailDTOS) {
                bookRentalsDetails.add(new BookRentalsDetail(b.getRentId(), b.getBokId(), b.getForHowManyDays(), b.getQty()));
            }

            boolean isAdded = bookingDAO.addBooking(book, bookRentalsDetails);
            if (isAdded) {
                boolean addDetails = bookingRentalsDAO.addDetails(bookRentalsDetails);
                if (addDetails) {
                    RentalsDAO rentalsDAO = new RentalsDAOImpl();
                    boolean updateRentalQty = rentalsDAO.update(bookRentalsDetails);
                    if (updateRentalQty) {
                        {
                            DBConnection.getDBConnection().getConnection().commit();
                            return true;
                        }
                    }
                }
            }
            DBConnection.getDBConnection().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getDBConnection().getConnection().setAutoCommit(true);
        }
    }
}
