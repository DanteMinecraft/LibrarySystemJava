package net.dante.users;

// klass som tar hand om all data om suspended users (bannade användare)

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

    /** 
     * @return String
     */
    /** 
     * @return String
     */
    public String getSuspendedId() {
        return suspendedId;
    }

    /** 
     * @return String
     */
    /** 
     * @return String
     */
    public String getUserId() {
        return userId;
    }

    /** 
     * @return String
     */
    /** 
     * @return String
     */
    public String getReason() {
        return reason;
    }

    /** 
     * @return String
     */
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "\nAnvändarens ID i system: " + userId + "\nAnledning: " + reason + "\nAvstängnings-ID i system: " + suspendedId + "\n";
    }
}
