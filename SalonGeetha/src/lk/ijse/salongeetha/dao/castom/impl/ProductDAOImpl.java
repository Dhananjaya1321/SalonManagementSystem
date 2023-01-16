package lk.ijse.salongeetha.dao.castom.impl;

import lk.ijse.salongeetha.dao.CrudUtil;
import lk.ijse.salongeetha.dao.castom.ProductDAO;
import lk.ijse.salongeetha.to.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAOImpl implements ProductDAO {
    public boolean add(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("INSERT INTO Product VALUES (?,?,?,?,?,?,?)", productDTO.getProId()
                , productDTO.getDescription(), productDTO.getCategory(), productDTO.getBrand(), productDTO.getUnitPrice(), productDTO.getQtyOnHand(), productDTO.getSupId());
    }

    public boolean delete(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("DELETE FROM Product WHERE Pro_Id=?", productDTO.getProId());
    }

    public boolean update(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("UPDATE Product SET Description=?,Category=?,Brand=?,Unit_price=?,Qty_on_hand=?,Sup_Id=?" +
                " WHERE Pro_Id=?", productDTO.getDescription(), productDTO.getCategory(), productDTO.getBrand(), productDTO.getUnitPrice(), productDTO.getQtyOnHand(), productDTO.getSupId(), productDTO.getProId());
    }

    /*public ArrayList<ProductDTO> search(ProductDTO product) throws SQLException, ClassNotFoundException {
        ArrayList<ProductDTO> products = new ArrayList<>();
        String setColumn;
        Pattern userNamePattern = Pattern.compile("[a-zA-Z]{1,}");
        Matcher matcher = userNamePattern.matcher(product.getBrand());
        if (matcher.matches()) {
            setColumn = "SELECT * FROM ProductDTO WHERE Brand LIKE ?";
        } else {
            setColumn = "SELECT * FROM ProductDTO WHERE Pro_Id LIKE ?";
        }
        ResultSet resultSet = CrudUtil.setQuery(setColumn, "%" + product.getBrand() + "%");
        while (resultSet.next()) {
            ProductDTO searchProduct = new ProductDTO();
            searchProduct.setProId(String.valueOf(resultSet.getObject(1)));
            searchProduct.setDescription(String.valueOf(resultSet.getObject(2)));
            searchProduct.setCategory(String.valueOf(resultSet.getObject(3)));
            searchProduct.setBrand(String.valueOf(resultSet.getObject(4)));
            searchProduct.setUnitPrice((Double) resultSet.getObject(5));
            searchProduct.setQtyOnHand((Integer) resultSet.getObject(6));
            searchProduct.setSupId(String.valueOf(resultSet.getObject(7)));
            products.add(searchProduct);
        }
        return products;
    }
*/
    public String checkId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.setQuery("SELECT Pro_Id FROM Product ORDER BY Pro_Id DESC LIMIT 1");
        if (resultSet.next()) {
            return String.valueOf(resultSet.getObject(1));
        }
        return null;
    }

    @Override
    public ResultSet search(boolean value, ProductDTO to) throws SQLException, ClassNotFoundException {
        String setColumn;
        if (value) {
            setColumn = "SELECT * FROM Product WHERE Brand LIKE ?";
        } else {
            setColumn = "SELECT * FROM Product WHERE Pro_Id LIKE ?";
        }
        return CrudUtil.setQuery(setColumn, "%" + to.getBrand() + "%");
    }

    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return CrudUtil.setQuery("SELECT * FROM Product");
    }
}
