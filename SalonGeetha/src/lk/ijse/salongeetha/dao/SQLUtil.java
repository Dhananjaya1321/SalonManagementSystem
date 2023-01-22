package lk.ijse.salongeetha.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SQLUtil<T> extends SuperDAOImpl {
    boolean add(T to) throws SQLException, ClassNotFoundException;

    boolean delete(T to) throws SQLException, ClassNotFoundException;

    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    boolean update(T to) throws SQLException, ClassNotFoundException;

    String checkId() throws SQLException, ClassNotFoundException;

    ArrayList<T> search(boolean value, T to) throws SQLException, ClassNotFoundException;
}
