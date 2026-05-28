import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // CAT: I initialized the console input scanner and our stateful chatbot instance to prepare the runtime loop environment.
        Scanner scanner = new Scanner(System.in);
        Chatbot karen = new Chatbot();

        // CAT: I printed this initial greeting message to immediately simulate a realistic, high-friction customer service scenario. (Yes I'm joking.)
        System.out.println("（╬━益━) [Karen]: Excuse me! I've been waiting here for five minutes. What is going on? :3");

        // CAT: I used an infinite while loop here to maintain the interactive chat experience to simulate a chat.
        while (true) {
            // CAT: I inserted some  dynamic prompt labels directly into the loop body to visually show exactly when the terminal is waiting for user text.
            System.out.print("\nYou: ");
            String userInput = scanner.nextLine();

            // CAT: I tried to isolate this conditional guard statement to check for exit keywords and safely break out of the application lifecycle if the user is trying to quit or exit.
            if (userInput.trim().toLowerCase().equals("bye")) {
                System.out.println("（─__─) [Karen]: Finally. Don't come back! UwU");
                break;
            }

            // CAT: Because the chatbot object may raise a fatal custom exception if the thresholds are exceeded so I tried to get around it using a generator method in a try catch architecture.
            try {
                // CAT: In order to automatically extract the text response payload, I just had it directly sent to the record line imput into the engine logic.
                String response = karen.generateResponse(userInput);
                System.out.println(response);
                
                // CAT: I just wanted it to show a visual on the Annoyance Meter for aesthetics.
                System.out.println(karen.getAnnoyanceMeterVisual());
                
            } catch (KarenRageQuitException e) {
                // CAT: Routed the program intercept down here to keep the program looking clean.
                System.out.println("\n" + e.getMessage());
                break;
            }
        }
        
        // CAT: Added to close the program after the scanner reaches 10 rage points.
        scanner.close();
    }
}
