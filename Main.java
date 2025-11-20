
/**
 * Entry point for launcing the "World of Zuul" Terraria based text adventure game.
 * This class contains the main method, which creates a Game object and starts the
 * game loop.
 *
 * @author Christian Gorosica
 * @version 2025.11.17
 */
public class Main
{
    /**
     * The application's entry point. A Game instance is created and its play routine is
     * executed, starting user interaction.
     * 
     * @param args Command-line arguments (not used)
     */
   public static void main(String[] args) {
    Game game = new Game();
    game.play();
 }   
}