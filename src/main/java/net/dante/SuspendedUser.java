package net.dante;

import com.google.gson.annotations.SerializedName;

public class SuspendedUser {

    private String reason;
    
    @SerializedName("id")
    private String suspendedId;

    @SerializedName("customer_id")
    private String userId;

    public SuspendedUser(String userId, String reason) {
        this.userId = userId;
        this.reason = reason;
    }

    public String getSuspendedId() {
        return suspendedId;
    }

    public String getUserId() {
        return userId;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "\nAnvändarens ID i system: " + userId + "\nAnledning: " + reason + "\nAvstängnings-ID i system: " + suspendedId + "\n";
    }
}
