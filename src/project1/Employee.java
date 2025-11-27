package project1;

public class Employee {
    private int employeeID;
    private String name;
    private String department;
    private double salary;
    private String email;
    private String phone;

public Employee(int employeeID,String name,String department,double salary,String email,String phone) {
    this.employeeID=employeeID;
    this.name=name;
    this.department=department;
    this.salary=salary;
    this.email=email;
    this.phone=phone;
}
    public int getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
