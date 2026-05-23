package net.dante.client;

public class Loans {

    private String id;

    private String userId;
    private String itemId;

    public Loans(String userId, String itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getItemId() {
        return itemId;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + "\nItem ID: " + itemId + "\n";
    }

}
