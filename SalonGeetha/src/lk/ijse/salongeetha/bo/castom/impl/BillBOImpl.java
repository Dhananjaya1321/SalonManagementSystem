package lk.ijse.salongeetha.bo.castom.impl;

import lk.ijse.salongeetha.bo.castom.BillBO;
import lk.ijse.salongeetha.dao.DAOImplTypes;
import lk.ijse.salongeetha.dao.FactoryDAOImpl;
import lk.ijse.salongeetha.dao.castom.BillDAO;
import lk.ijse.salongeetha.dao.castom.EmployeeDAO;
import lk.ijse.salongeetha.entity.BillPayment;
import lk.ijse.salongeetha.entity.Employee;
import lk.ijse.salongeetha.dto.BillPaymentDTO;
import lk.ijse.salongeetha.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class BillBOImpl implements BillBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.EMPLOYEE);
    BillDAO billPaymentDAO = (BillDAO) FactoryDAOImpl.getFactoryDAOImpl().setDAOImpl(DAOImplTypes.BILL);

    private ArrayList<BillPayment> billPayments;
    private ArrayList<BillPaymentDTO> dtos;

    @Override
    public boolean deleteBillPayment(BillPaymentDTO dto) throws SQLException, ClassNotFoundException {
        return billPaymentDAO.delete(new BillPayment(dto.getBilId(), dto.getEmpId(), dto.getDate(), dto.getDescription(),
                dto.getTitle(), dto.getAmountPaid()));
    }

    @Override
    public boolean addBillPayment(BillPaymentDTO dto) throws SQLException, ClassNotFoundException {
        return billPaymentDAO.add(new BillPayment(dto.getBilId(), dto.getEmpId(), dto.getDate(), dto.getDescription(),
                dto.getTitle(), dto.getAmountPaid()));
    }

    @Override
    public boolean updateBillPayment(BillPaymentDTO dto) throws SQLException, ClassNotFoundException {
        return billPaymentDAO.update(new BillPayment(dto.getBilId(), dto.getEmpId(), dto.getDate(), dto.getDescription(),
                dto.getTitle(), dto.getAmountPaid()));
    }

    @Override
    public String checkIdBillPayment() throws SQLException, ClassNotFoundException {
        return billPaymentDAO.checkId();
    }

    @Override
    public ArrayList<BillPaymentDTO> searchBillPayment(boolean value, BillPaymentDTO dto) throws SQLException, ClassNotFoundException {
        /*ArrayList<BillPayment>*/
        billPayments = billPaymentDAO.search(value, new BillPayment(dto.getBilId(), dto.getEmpId(), dto.getDate(), dto.getDescription(),
                dto.getTitle(), dto.getAmountPaid()));
        /*ArrayList<BillPaymentDTO>*/
        dtos = new ArrayList<>();
        for (BillPayment b : billPayments) {
            dtos.add(new BillPaymentDTO(b.getBilId(), b.getEmpId(), b.getDate(), b.getDescription(), b.getTitle(), b.getAmountPaid()));
        }
        return dtos;
    }

    @Override
    public ArrayList<BillPaymentDTO> getAllBillPayment() throws SQLException, ClassNotFoundException {
        /*ArrayList<BillPayment> */
        billPayments = billPaymentDAO.getAll();
        /*ArrayList<BillPaymentDTO>*/
        dtos = new ArrayList<>();
        for (BillPayment b : billPayments) {
            dtos.add(new BillPaymentDTO(b.getBilId(), b.getEmpId(), b.getDate(), b.getDescription(), b.getTitle(), b.getAmountPaid()));
        }
        return dtos;
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> all = employeeDAO.getAll();
        ArrayList<EmployeeDTO> employeeDTO = new ArrayList<>();
        for (Employee e : all) {
            employeeDTO.add(new EmployeeDTO(e.getEmpId(), e.getName(), e.getAddress(), e.getDob(),
                    e.getPhoneNumber(), e.getDescription(), e.getEmail(), e.getNic(), e.getJobTitle()));
        }
        return employeeDTO;
    }
}
