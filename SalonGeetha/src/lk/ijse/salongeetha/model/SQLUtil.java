package lk.ijse.salongeetha.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SQLUtil<T> {
    boolean add(T user) throws SQLException, ClassNotFoundException;
//    boolean delete(T user, T employee) throws SQLException, ClassNotFoundException;
    boolean delete(T user) throws SQLException, ClassNotFoundException;
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean update(T supplier) throws SQLException, ClassNotFoundException;
    String checkId() throws SQLException, ClassNotFoundException;
    ArrayList<T> search(T supplier) throws SQLException, ClassNotFoundException;
}
