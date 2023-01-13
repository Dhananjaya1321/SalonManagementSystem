package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.to.BookRentalsDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingRentalsModel {

    public static boolean addDetails(ArrayList<BookRentalsDetail> bookRentalsDetails) throws SQLException, ClassNotFoundException {
        int i = 0;
        for (BookRentalsDetail b : bookRentalsDetails) {
            boolean isAdded = CrudUtil.setQuery("INSERT INTO book_rentals_detail VALUES (?,?,?,?)", b.getRentId(), b.getBokId()
                    , b.getQty(), b.getForHowManyDays());
            if (isAdded) {
                i++;
            }
        }
        if (i == bookRentalsDetails.size()) {
            return true;
        }
        return false;
    }

    public static ArrayList<BookRentalsDetail> getAmountDue(BookRentalsDetail bookRentalsDetail) throws SQLException, ClassNotFoundException {
        ArrayList<BookRentalsDetail> bookRentalsDetails = new ArrayList<>();
        ResultSet resultSet = CrudUtil.setQuery("select bd.Qty,bd.For_how_many_days,r.Price_pre_day,r.Discount \n" +
                "from book_rentals_detail bd join rentals r on bd.Rent_Id = r.Rent_Id where Bok_Id = ?", bookRentalsDetail.getBokId());
        while (resultSet.next()) {
            BookRentalsDetail setBookRentalsDetail = new BookRentalsDetail();
            setBookRentalsDetail.setQty((Integer) resultSet.getObject(1));
            setBookRentalsDetail.setForHowManyDays((Integer) resultSet.getObject(2));
            setBookRentalsDetail.setPrice((Double) resultSet.getObject(3));
            setBookRentalsDetail.setDiscount((Double) resultSet.getObject(4));
            bookRentalsDetails.add(setBookRentalsDetail);
        }
        return bookRentalsDetails;
    }
}
