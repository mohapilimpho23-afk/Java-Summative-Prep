package summative;

// Provided helper class for Question 4. Do not modify.
public class ClientConnection {
    private final String clientId;
    private final String state;

    public ClientConnection(String clientId, String state) {
        this.clientId = clientId;
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
