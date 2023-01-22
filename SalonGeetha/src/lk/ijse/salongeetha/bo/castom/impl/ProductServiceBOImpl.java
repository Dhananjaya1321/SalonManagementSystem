package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.ProductServiceBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.ProductDAO;
import lk.ijse.salongeetha.dao.castom.ProductServiceDAO;
import lk.ijse.salongeetha.dao.castom.QueryDAO;
import lk.ijse.salongeetha.dao.castom.ServiceDAO;
import lk.ijse.salongeetha.entity.CustomEntity;
import lk.ijse.salongeetha.entity.Product;
import lk.ijse.salongeetha.entity.ProductServiceDetail;
import lk.ijse.salongeetha.entity.Service;
import lk.ijse.salongeetha.dto.ProductDTO;
import lk.ijse.salongeetha.dto.ProductServiceDetailDTO;
import lk.ijse.salongeetha.dto.ServiceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductServiceBOImpl implements ProductServiceBO {
    ProductDAO productDAO = (ProductDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.PRODUCT);
    ServiceDAO serviceDAO = (ServiceDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.SERVICE);
    ProductServiceDAO productServiceDAO = (ProductServiceDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.PRODUCT_SERVICE);
    QueryDAO queryDAO = (QueryDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.QUERY);
    private ArrayList<Service> services;
    private ArrayList<ServiceDTO> serviceDTOS;

    @Override
    public boolean checkAlreadyExists(ProductServiceDetailDTO dto) throws SQLException, ClassNotFoundException {
        return productServiceDAO.checkAlreadyExists(new ProductServiceDetail(dto.getProId(), dto.getSevId(), dto.getQty()));
    }

    @Override
    public boolean addProductAndServiceDetail(ProductServiceDetailDTO dto) throws SQLException, ClassNotFoundException {
        return productServiceDAO.add(new ProductServiceDetail(dto.getProId(), dto.getSevId(), dto.getQty()));
    }

    @Override
    public ArrayList<ServiceDTO> searchService(boolean value, ServiceDTO dto) throws SQLException, ClassNotFoundException {
        /*ArrayList<Service>*/
        services = serviceDAO.search(value, new Service(dto.getSevId(), dto.getDescription(),
                dto.getName(), dto.getPrice(), dto.getDiscount()));
        /*ArrayList<ServiceDTO>*/
        serviceDTOS = new ArrayList<>();
        for (Service s : services) {
            serviceDTOS.add(new ServiceDTO(s.getSevId(), s.getDescription(), s.getName(), s.getPrice(), s.getDiscount()));
        }
        return serviceDTOS;
    }

    @Override
    public boolean deleteProductAndServiceDetail(ProductServiceDetailDTO dto) throws SQLException, ClassNotFoundException {
        return productServiceDAO.delete(new ProductServiceDetail(dto.getProId(), dto.getSevId(), dto.getQty()));
    }

    @Override
    public ArrayList<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException {
        /*ArrayList<Service>*/
        services = serviceDAO.getAll();
        /*ArrayList<ServiceDTO>*/
        serviceDTOS = new ArrayList<>();
        for (Service s : services) {
            serviceDTOS.add(new ServiceDTO(s.getSevId(), s.getDescription(), s.getName(), s.getPrice(), s.getDiscount()));
        }
        return serviceDTOS;
    }

    @Override
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = productDAO.getAll();
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        for (Product p : products) {
            productDTOS.add(new ProductDTO(p.getProId(), p.getDescription(), p.getCategory(), p.getBrand(), p.getSupId(), p.getUnitPrice(), p.getQtyOnHand()));
        }
        return productDTOS;
    }

    @Override
    public ArrayList<ProductServiceDetailDTO> getAllProductAndServiceDAO() throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> allProductService = queryDAO.getAllProductService();
        ArrayList<ProductServiceDetailDTO> dtos = new ArrayList<>();
        for (CustomEntity c : allProductService) {
            dtos.add(new ProductServiceDetailDTO(c.getProId(), c.getSevId(), c.getName(), c.getQty()));
        }
        return dtos;
    }
}
