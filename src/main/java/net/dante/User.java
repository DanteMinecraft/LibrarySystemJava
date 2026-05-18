package net.dante;

public class User {

    // Variables for user
    private String userId;
    private String userName;
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

    // Setters (for future use)
    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    
    @Override
    public String toString() {
        return "Användarnamn: " + userName + "\nMejladress: " + userEmail + "\nAnvändarens ID i system: " + userId + "\n";
    }
    
}
