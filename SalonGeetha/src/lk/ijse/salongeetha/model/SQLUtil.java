package lk.ijse.salongeetha.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLUtil<T> {
    boolean add(T to) throws SQLException, ClassNotFoundException;

    boolean delete(T to) throws SQLException, ClassNotFoundException;

    ResultSet getAll() throws SQLException, ClassNotFoundException;

    boolean update(T to) throws SQLException, ClassNotFoundException;

    String checkId() throws SQLException, ClassNotFoundException;

    ResultSet search(boolean value,T to) throws SQLException, ClassNotFoundException;
}
