package net.dante;

import com.google.gson.annotations.SerializedName;

public class User {

    // Variables for user (as serialized to make sure they match the server structure)

    @SerializedName("id")
    private String userId;

    @SerializedName("name")
    private String userName;

    @SerializedName("email")
    private String userEmail;

    // Constructor for user
    public User(String userId, String userName, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
    
    @Override
    public String toString() {
        return "Användarnamn: " + userName + "\nMejladress: " + userEmail + "\nAnvändarens ID i system: " + userId + "\n";
    }
    
}
