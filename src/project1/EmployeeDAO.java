//package project1;
//
//import java.sql.*;
//public class EmployeeDAO {
////insert operation
//    public static void addEmployees(Employee emp) {
//        String sql="INSERT INTO Employees(name,department,salary,email,phone) VALUES (?,?,?,?,?)";
//                try(Connection connect= Databaseconnection.connect();
//                    PreparedStatement stmt= connect.prepareStatement(sql)) {
//                    stmt.setString(1,emp.getName());
//                    stmt.setString(2, emp.getDepartment());
//                    stmt.setDouble(3,emp.getSalary());
//                    stmt.setString(4,emp.getDepartment());
//                    stmt.setString(5,emp.getPhone());
//
//        }
//    }
//}
