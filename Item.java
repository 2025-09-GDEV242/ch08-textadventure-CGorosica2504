
/**
 * Class Item - a item in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Item" represents an object which can be found in the scenery of the game.
 * An item has a description and a weight.
 *
 * @author Christian Gorosica
 * @version 2025.11.17
 */
public class Item
{
    private String description;
    private double weight;
    
    /**
     * Create a new "item" which can be assigned to rooms.
     * @param description The name or description of the item
     * @param weight The weeight of the item in kilograms
     */
    public Item(String description, double weight) {
        this.description = description;
        this.weight = weight;
    }
    /**
     * Get the descruption of the item
     * @return The item's description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Get the weight of the item
     * @return The weight in kilograms
     */
    public double getWeight() {
        return weight;
    }
    
    /**
     * Return a string representation of the item.
     * Includes description and weight
     * @return A formatted string of the item.
     */
    @Override
    public String toString() {
        return description + " (" + weight + "kg)";
    }
}