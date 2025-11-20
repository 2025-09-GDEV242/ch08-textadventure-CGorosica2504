import java.util.Stack;
import java.util.HashMap;

/**
 * The Player class represents a player in the game.
 * 
 * This class is part of the "World of Zuul" Terraria based application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * A Player object stores the player's name, the current room they are in, the history
 * of rooms they have visited, and their inventory possessing any items they may have
 * chosen to pick up. 
 * 
 * This class provides methods to move to a new room and to return to the
 * previous room.
 * 
 * This class provides methods to pick up and drop items within their current room.
 *
 * @author Christian Gorosica
 * @version 2025.11.17
 */
public class Player
{
    private String name;
    private Room currentRoom;
    private Stack<Room> roomHistory;
    private HashMap<String, Item> inventory;
    
    /**
     * Create a player with a given name and starting room.
     */
    public Player(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
        this.roomHistory = new Stack<>();
        this.inventory = new HashMap<>();
    }
    
    /**
     * Returns the player's name.
     * @return The player's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the room the player is currently in.
     * @return The room the player is currently in
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    /**
     * Move the player to a new room.
     * The current room is stored and is pushed onto the room history stack before moving.
     */
    public void moveTo(Room nextRoom) {
        roomHistory.push(currentRoom);
        currentRoom = nextRoom;
    }
    
    /**
     * Moves the player back to the previous room, if possible.
     * The last visited room is popped from the room history stack.
     * @return true if the player moved back, false if there was no previous room to move into
     */
    public boolean goBack() {
        if (roomHistory.isEmpty()) {
            return false;
        }
        currentRoom = roomHistory.pop();
        return true;
    }
    
    /**
     * Allows the player to take an item from the current room and place it into their
     * inventory. If the item is not present in the room, no change occurs.
     * @param itemName The name of the item to take
     * @return true if the item was successfully taken, false otherwise
     */
    public boolean takeItem(String itemName) {
        Item item = currentRoom.removeItem(itemName);
        
        if (item != null) {
            inventory.put(itemName, item);
            return true;
        }
        return false;
    }
    
    /**
     * Allows the player to drop an item from their inventory into the current room. If the
     * player is not carrying the item, no change occurs.
     * @param itemName The name of the item to drop
     * @return true if the item was successfully dropped, false otherwise
     */
    public boolean dropItem(String itemName) {
        Item item = inventory.remove(itemName);
        
        if (item != null) {
            currentRoom.addItem(item);
            return true;
        }
        return false;
    }
    
    /**
     * Returns a string listing all items carried by the player.
     * If the player is carrying no items, a message indicating so is returned
     * @return A string representing the player's inventory contents
     */
    public String getInventoryString() {
        if (inventory.isEmpty()) {
            return "You are not carrying anything.";
        }
        
        String inv = "Inventory:";
        for (String key : inventory.keySet()) {
            inv += " " + inventory.get(key).toString();
        }
        return inv;
    }
}
