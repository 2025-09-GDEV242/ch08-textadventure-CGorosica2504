import java.util.Stack;
/**
 * The Player class represents a player in the game.
 * 
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * A Player object stores the player's name, the current room they are in, and the history
 * of rooms they have visited. It provides methods to move to a new room and to return to the
 * previous room.
 *
 * @author Christian Gorosica
 * @version 2025.11.17
 */
public class Player
{
    private String name;
    private Room currentRoom;
    private Stack<Room> roomHistory;
    
    /**
     * Create a player with a given name and starting room
     */
    public Player(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
        this.roomHistory = new Stack<>();
    }
    
    /**
     * Return the player's name
     * 
     * @return The player's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the room the player is currently in
     * 
     * @return The room the player is currently in
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    /**
     * Move the player to a new room
     * The current room is stored is pushed onto the room history stack before moving
     */
    public void moveTo(Room nextRoom) {
        roomHistory.push(currentRoom);
        currentRoom = nextRoom;
    }
    
    /**
     * Move the player back to the previous, if any
     * The last visited room is popped from the room history stack
     * 
     * @return true if the player moved back, false if there was no previous room to move into
     */
    public boolean goBack() {
        if (roomHistory.isEmpty()) {
            return false;
        }
        currentRoom = roomHistory.pop();
        return true;
    }
}
