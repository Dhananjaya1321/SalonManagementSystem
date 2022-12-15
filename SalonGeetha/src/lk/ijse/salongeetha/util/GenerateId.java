package lk.ijse.salongeetha.util;

public class GenerateId {
    public static String generateNextId(String currentId,IdTypes idType) {
        switch (idType){
            case EMPLOYEE:
                if (currentId != null) {
                    String[] split = currentId.split("E00");
                    int id = Integer.parseInt(split[1]);
                    id += 1;
                    return "E00" + id;
                }
                return "E001";
            case SALARY:
                if (currentId != null) {
                    String[] split = currentId.split("S00");
                    int id = Integer.parseInt(split[1]);
                    id += 1;
                    return "S00" + id;
                }
                return "S001";
            case SERVICE:
                if (currentId != null) {
                    String[] split = currentId.split("SEV00");
                    int id = Integer.parseInt(split[1]);
                    id += 1;
                    return "SEV00" + id;
                }
                return "SEV001";
            case RENTAL:
                if (currentId != null) {
                    String[] split = currentId.split("R00");
                    int id = Integer.parseInt(split[1]);
                    id += 1;
                    return "R00" + id;
                }
                return "R001";
            case SUPPLIER:
                if (currentId != null) {
                    String[] split = currentId.split("SUP00");
                    int id = Integer.parseInt(split[1]);
                    id += 1;
                    return "SUP00" + id;
                }
                return "SUP001";
            case PRODUCT:
                if (currentId != null) {
                    String[] split = currentId.split("P00");
                    int id = Integer.parseInt(split[1]);
                    id += 1;
                    return "P00" + id;
                }
                return "P001";
            case BILL:
                if (currentId != null) {
                    String[] split = currentId.split("B00");
                    int id = Integer.parseInt(split[1]);
                    id += 1;
                    return "B00" + id;
                }
                return "B001";
            case APPOINTMENT:
                if (currentId != null) {
                    String[] split = currentId.split("APOINT00");
                    int id = Integer.parseInt(split[1]);
                    id += 1;
                    return "APOINT00" + id;
                }
                return "APOINT001";
            case BOOK:
                if (currentId != null) {
                    String[] split = currentId.split("BOK00");
                    int id = Integer.parseInt(split[1]);
                    id += 1;
                    return "BOK00" + id;
                }
                return "BOK001";
            case APAYMENT:
                if (currentId != null) {
                    String[] split = currentId.split("A-PAY00");
                    int id = Integer.parseInt(split[1]);
                    id += 1;
                    return "A-PAY00" + id;
                }
                return "A-PAY001";
            case BPAYMENT:
                if (currentId != null) {
                    String[] split = currentId.split("b-PAY00");
                    int id = Integer.parseInt(split[1]);
                    id += 1;
                    return "b-PAY00" + id;
                }
                return "b-PAY001";
        }
        return null;
    }
}
