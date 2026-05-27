import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Employee {
    // Properties (attributes)
    private int employeeId;
    private String name;
    private String department;
    private String email;

    // Constructor
    public Employee(int employeeId, String name, String department, String email) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.email = email;
    }
    // In the Employee class
    private int leaveBalance = 20; // Annual leave balance in days
    // Getter method
    public int getLeaveBalance() {
        return leaveBalance;
    }
    // Setter method with validation
    public void setLeaveBalance(int leaveBalance) {
        if (leaveBalance >= 0) {
            this.leaveBalance = leaveBalance;
        } else {
            System.out.println("Leave balance cannot be negative.");
        }
    }

    public int getEmployeeId(){
        return employeeId;
    }

    public String getDepartment(){
        return department;
    }

    private ArrayList<LeaveRequest> leaveHistory = new ArrayList<>();

    public void addLeaveRequest(LeaveRequest request) {
        leaveHistory.add(request);
    }

    public ArrayList<LeaveRequest> getLeaveHistory() {
        return leaveHistory;
    }

    public LeaveRequest getLeaveRequestById(int requestId) {
        for (LeaveRequest request : leaveHistory) {
            if (request.getRequestId() == requestId) {
                return request;
            }
        }
        return null; // Request not found
    }
}