package net.dante;

public class SuspendedUser {

    // Variables for user
    private String suspendedId;
    private String userId;
    private String reason;

    // Constructor for user
    public SuspendedUser(String suspendedId, String userId, String reason) {
        this.suspendedId = suspendedId;
        this.userId = userId;
        this.reason = reason;
    }

    // Getters
    public String getSuspendedId() {
        return suspendedId;
    }
    
    public String getSuspendedUsersID() {
        return userId;
    }

    public String getReason() {
        return reason;
    }

    // Setters (for future use)
    //blank

    @Override
    public String toString() {
        return "Anledning: " + reason + "\nAnvändarens ID i system: " + userId + "\nAvstängnings-ID i system: " + userId + "\n";
    }
}
