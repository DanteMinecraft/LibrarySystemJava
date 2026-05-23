package net.dante.users;

// klass som har hand om all data för users

import com.google.gson.annotations.SerializedName;

public class User implements Comparable<User> {

    // Variables for user (as serialized to make sure they match the server structure)

    @SerializedName("id")
    private String userId;

    @SerializedName("name")
    private String userName;

    @SerializedName("email")
    private String userEmail;

    public User(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
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
    public String getUserName() {
        return userName;
    }

    /** 
     * @return String
     */
    /** 
     * @return String
     */
    public String getUserEmail() {
        return userEmail;
    }
    
    /** 
     * @return String
     */
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Användarnamn: " + userName + "\nMejladress: " + userEmail + "\nAnvändarens ID i system: " + userId + "\n";
    }

    /** 
     * @param other
     * @return int
     */
    /** 
     * @param other
     * @return int
     */
    @Override
    public int compareTo(User other) {
        return this.userName.compareToIgnoreCase(other.userName);
    }
    
}
