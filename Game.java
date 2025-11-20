/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @author Christian Gorosica
 * @version 2025.11.17
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms, link their exits together, and add any items that could be present
     * within them.
     */
    private void createRooms()
    {
        Room Forest, Desert, Ocean, Jungle, Dungeon, Tundra, Corruption, Underworld;
      
        // create the rooms
        Forest = new Room("in a green forest. There are tall trees and ambient wildlife all around");
        Desert = new Room("in a desert. It is a dry expanse of sand and sparse vegetation under the harsh sunlight");
        Ocean = new Room("at the ocean coast of crashing waves, deep water, and scattered debris");
        Jungle = new Room("in a a jungle. It is a humid overgrowth of thick vines, towering flora, and thorns");
        Dungeon = new Room("in an abandoned dungeon. This cursed structure is haunted with relics, traps, and restless souls");
        Tundra = new Room("in a tundra. This frozen landscape is full of snowdrifts, icy winds, and brittle terrain");
        Corruption = new Room("in the corruption. This is a decayed wasteland of warped terrain, pulsating growths, and hostile energy");
        Underworld = new Room("at the underworld. These depths are filled with ash, hellstone, and flowing magma");
        
        //create and initialize the items that are present in some rooms
        Forest.addItem(new Item("Copper Shortword", 2.0));
        Forest.addItem(new Item("Copper Pickaxe", 2.0));
        Forest.addItem(new Item("Copper Axe", 2.0));
        
        Desert.addItem(new Item("Flying Carpet", 4.5));
        Desert.addItem(new Item("Sandstorm in a Bottle", 3.75));
        
        Ocean.addItem(new Item("Trident", 6));
        Ocean.addItem(new Item("Diving Helmet", 4.0));
        Ocean.addItem(new Item("Flippers", 7.0));
        
        Jungle.addItem(new Item("Boomstick", 7.0));
        Jungle.addItem(new Item("Anklet of the Wind", 5.0));
        Jungle.addItem(new Item("The Bee's Knees", 11.0));
        
        Dungeon.addItem(new Item("Waterbolt", 4.5));
        Dungeon.addItem(new Item("Muramasa", 8.0));
        Dungeon.addItem(new Item("Handgun", 7.5));
        Dungeon.addItem(new Item("Magic Missile", 9.0));
        
        Tundra.addItem(new Item("Snowball Cannon", 12.0));
        Tundra.addItem(new Item("Ice Blade", 10.0));
        Tundra.addItem(new Item("Flurry Boots", 8.0));
        
        Corruption.addItem(new Item("Musket", 15.0));
        Corruption.addItem(new Item("Ball O' Hurt", 13.0));
        Corruption.addItem(new Item("Vilethorn", 11.0));
        
        Underworld.addItem(new Item("Dark Lance", 13.0));
        Underworld.addItem(new Item("Sunfury", 15.0));
        Underworld.addItem(new Item("Hellwing Bow", 16.0));
        Underworld.addItem(new Item("Flamelash", 12.0));
        Underworld.addItem(new Item("Demon Scythe", 10.0));
        
        // initialise room exits
        Forest.setExit("west", Desert);
        Forest.setExit("east", Jungle);
        Forest.setExit("south", Tundra);

        Desert.setExit("west", Ocean);
        Desert.setExit("east", Forest);
        Desert.setExit("south", Dungeon);

        Ocean.setExit("east", Desert);

        Jungle.setExit("west", Forest);
        Jungle.setExit("south", Corruption);

        Dungeon.setExit("north", Desert);
        Dungeon.setExit("east", Tundra);
        Dungeon.setExit("south", Underworld);
        
        Tundra.setExit("north", Forest);
        Tundra.setExit("west", Dungeon);
        Tundra.setExit("east", Corruption);
        
        Corruption.setExit("north", Jungle);
        Corruption.setExit("west", Tundra);
        
        Underworld.setExit("north", Dungeon);

        currentRoom = Forest;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Terraria!");
        System.out.println("World of Terraria is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case LOOK:
                look(); 
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost in the world of Terraria. You are alone. You wander");
        System.out.println("around.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * When "look" is entered, the description of the room and its exists are
     * printed out again.
     */
    private void look() {
        System.out.println(currentRoom.getLongDescription());
    }
}
