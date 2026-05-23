package net.dante.client;

public class Loans {

    private String id;

    private String userId;
    private String itemId;

    public Loans(String userId, String itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }

    /** 
     * @return String
     */
    public String getId() {
        return id;
    }

    /** 
     * @return String
     */
    public String getUserId() {
        return userId;
    }

    /** 
     * @return String
     */
    public String getItemId() {
        return itemId;
    }

    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "User ID: " + userId + "\nItem ID: " + itemId + "\n";
    }

}
