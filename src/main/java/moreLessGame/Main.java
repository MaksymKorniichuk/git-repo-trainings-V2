package moreLessGame;

/**
 * Main class, that initializes and run program.
 * @author Maksym
 */
public class Main {
    /**
     * Main method, that initializes and run program.
     * @param args arguments from command line.
     */
    public static void main(String[] args) {
        // Initialization
        Model model = new Model(GlobalConstants.DEFAULT_MIN_LIMIT_OF_RANGE, GlobalConstants.DEFAULT_MAX_LIMIT_OF_RANGE);
        View view = new View();
        Controller controller = new Controller(model, view);

        // Run
        controller.mainProcess();
    }
}
