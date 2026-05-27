import java.util.LinkedList;
import java.util.Queue;

public class LeaveApprovalSystem {
    private Queue<LeaveRequest> pendingRequests = new LinkedList<>();

    public void addPendingRequest(LeaveRequest request) {
        pendingRequests.add(request);
    }

    public LeaveRequest getNextPendingRequest() {
        return pendingRequests.poll(); // Retrieves and removes the head
    }

    public int getPendingRequestCount() {
        return pendingRequests.size();
    }

    public boolean hasPendingRequests() {
        return !pendingRequests.isEmpty();
    }
}