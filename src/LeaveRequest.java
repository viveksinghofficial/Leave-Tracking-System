import java.util.ArrayList;

// Interface
interface Approvable {
    boolean approve(String approvalName);
    boolean deny(String approvalName, String reason);
}

public abstract class LeaveRequest implements Approvable {
    private int requestId;
    private Employee employee;
    private String startDate;
    private String endDate;
    private String status; // "Pending", "Approved", "Denied"
    private String reason;

    // Constructor
    public LeaveRequest(int requestId, Employee employee, String startDate,
                        String endDate, String reason) {
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "Pending"; // Default status
        this.reason = reason;
    }

    @Override
    public boolean approve(String approvalName) {
        if (approvalName.equals("Approved")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deny(String approvalName, String reason) {
        if (approvalName.equals("Denied")) {
            return true;
        }
        return false;
    }

    public int getRequestId(){
        return requestId;
    }

    public String getStatus(){
        return status;
    }

    public Employee getEmployee(){
        return employee;
    }

    // Abstract method that subclasses must implement
    public abstract int calculateLeaveDays();


    private ArrayList<StatusChange> statusHistory = new ArrayList<>();

    // Inner class to track status changes
    public class StatusChange {
        private String oldStatus;
        private String newStatus;
        private String changeDate;
        private String changedBy;

        public StatusChange(String oldStatus, String newStatus,
                            String changeDate, String changedBy) {
            this.oldStatus = oldStatus;
            this.newStatus = newStatus;
            this.changeDate = changeDate;
            this.changedBy = changedBy;
        }

        // Getters for the fields
        String getOldStatus() {
            return this.oldStatus;
        }

        String getNewStatus() {
            return this.newStatus;
        }

        String getChangeDate() {
            return changeDate;
        }

        String getChangedBy() {
            return this.changedBy;
        }
    }

    String getCurrentDate(){
        return this.startDate;
    }

    // Method to change status and record the change
    public void changeStatus(String newStatus, String changedBy) {
        String oldStatus = this.status;
        this.status = newStatus;

        // Create a new status change record
        StatusChange change = new StatusChange(
                oldStatus, newStatus, getCurrentDate(), changedBy);
        statusHistory.add(change);
    }
}

// Child class SickLeaveRequest inheriting LeaveRequest
class SickLeaveRequest extends LeaveRequest{
    private boolean medicalCertificateProvided;

    public SickLeaveRequest(int requestId, Employee employee,
                            String startDate, String endDate,
                            boolean medicalCertificateProvided) {
        super(requestId, employee, startDate, endDate, "Sick Leave");
        this.medicalCertificateProvided = medicalCertificateProvided;
    }

    // Additional methods specific to sick leave
    public boolean isMedicalCertificateProvided() {
        return medicalCertificateProvided;
    }

    @Override
    public int calculateLeaveDays(){
        return 7;
    }
}

//Child class VacationLeaveRequest inheriting LeaveRequest
class VacationLeaveRequest extends LeaveRequest{
    private boolean applicationProvided;

    public VacationLeaveRequest(int requestId, Employee employee,
                                String startDate, String endDate,
                                boolean applicationProvided){
        super(requestId,employee,startDate,endDate,"Vacation leave");

    }

    //Additional methods specific to vacation leave
    public boolean isApplicationProvided(boolean applicationProvided){
        return applicationProvided;
    }

    @Override
    public int calculateLeaveDays(){
        return 15;
    }
}

//Child class MaternityLeaveRequest inheriting LeaveRequest
class MaternityLeaveRequest extends LeaveRequest{
    private boolean hospitalApproval;

    public MaternityLeaveRequest(int requestId, Employee employee,
                                String startDate, String endDate,
                                boolean hospitalApproval){
        super(requestId,employee,startDate,endDate,"Maternity leave");

    }

    //Additional methods specific to vacation leave
    public boolean isApplicationProvided(boolean hospitalApproval){
        return hospitalApproval;
    }

    public int calculateLeaveDays(){
        return 20;
    }
}