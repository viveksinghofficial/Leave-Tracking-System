import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class LeaveTrackingSystem {
//   Maps for quick lookup
    private HashMap<Integer, Employee> employeeById = new HashMap<>();
    private HashMap<Integer, LeaveRequest> requestById = new HashMap<>();

    // Lists for ordered access
    private ArrayList<LeaveRequest> allRequests = new ArrayList<>();

    // Sets for unique collections
    private HashSet<String> leaveTypes = new HashSet<>();

    // Queues for processing
    private Queue<LeaveRequest> pendingApprovals = new LinkedList<>();

    // Other fields and methods
    // ...

    private HashSet<String> departmentsWithPendingRequests = new HashSet<>();

    public void updateDepartmentsWithPendingRequests() {
        departmentsWithPendingRequests.clear();

        for (LeaveRequest request : allRequests) {
            if (request.getStatus().equals("Pending")) {
                departmentsWithPendingRequests.add(
                        request.getEmployee().getDepartment());
            }
        }
    }

    public boolean hasPendingRequests(String department) {
        return departmentsWithPendingRequests.contains(department);
    }

    private HashMap<Integer, Employee> employeeDirectory = new HashMap<>();

    public void addEmployee(Employee employee) {
        employeeDirectory.put(employee.getEmployeeId(), employee);
    }

    public Employee getEmployeeById(int employeeId) {
        return employeeDirectory.get(employeeId);
    }

    public boolean removeEmployee(int employeeId) {
        if (employeeDirectory.containsKey(employeeId)) {
            employeeDirectory.remove(employeeId);
            return true;
        }
        return false;
    }
}
