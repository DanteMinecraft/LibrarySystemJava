package net.dante;

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

    @Override
    public int compareTo(User other) {
        return this.userName.compareToIgnoreCase(other.userName);
    }
    
}
