package lk.ijse.salongeetha.model;

import lk.ijse.salongeetha.db.DBConnection;
import lk.ijse.salongeetha.to.BookRentalsDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingRentalsModel {

    public static boolean addDetails(ArrayList<BookRentalsDetail> bookRentalsDetails) throws SQLException, ClassNotFoundException {
        int i = 0;
        for (BookRentalsDetail b : bookRentalsDetails) {
            PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection()
                    .prepareStatement("INSERT INTO book_rentals_detail VALUES (?,?,?,?)");
            prepareStatement.setObject(1,b.getRentId());
            prepareStatement.setObject(2,b.getBokId());
            prepareStatement.setObject(3,b.getQty());
            prepareStatement.setObject(4,b.getForHowManyDays());
            int executeUpdate = prepareStatement.executeUpdate();
            if (executeUpdate > 0) {
                i++;
            }
        }
        if (i == bookRentalsDetails.size()) {
            return true;
        }
        return false;
    }

    public static ArrayList<BookRentalsDetail> getAmountDue(BookRentalsDetail bookRentalsDetail) throws SQLException, ClassNotFoundException {
        ArrayList<BookRentalsDetail> bookRentalsDetails=new ArrayList<>();
        PreparedStatement prepareStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("select bd.Qty,bd.For_how_many_days,r.Price_pre_day,r.Discount \n" +
                        "from book_rentals_detail bd join rentals r on bd.Rent_Id = r.Rent_Id where Bok_Id = ?");
        prepareStatement.setObject(1,bookRentalsDetail.getBokId());
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            BookRentalsDetail setBookRentalsDetail=new BookRentalsDetail();
            setBookRentalsDetail.setQty((Integer) resultSet.getObject(1));
            setBookRentalsDetail.setForHowManyDays((Integer) resultSet.getObject(2));
            setBookRentalsDetail.setPrice((Double) resultSet.getObject(3));
            setBookRentalsDetail.setDiscount((Double) resultSet.getObject(4));
            bookRentalsDetails.add(setBookRentalsDetail);

        }
        return bookRentalsDetails;
    }
}
