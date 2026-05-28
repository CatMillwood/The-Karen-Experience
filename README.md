# The Karen Experience

Ever worked in retail? This is your worst nightmare, now in Java. 

I built this terminal simulator to practice Java logic and custom exceptions, but mostly just to see how much chaos I could pack into a CLI tool before I destroy my own brain from the brain rot gods themselves. 

She tracks her own "rage" as you talk, and if you say the wrong thing—or the right thing—she'll eventually lose it, write a scathing review, and rage-quit the app to go call corporate.

## How it works

* **The Annoyance Meter:** Keeps track of how much you're bugging her.
* **Volume Control:** If you type in ALL CAPS, she takes it as yelling and her rage jumps up.
* **Trigger Words:** Mentioning "karen" it may or may not trigger an existential crisis and an immediate crash.
* **The Rage Quit:** If her meter hits 10/10, she's done. Don't take it personally. 

## Running the project

Make sure you have Java installed, then open your terminal and run:

```bash
# Get into the source folder
cd src

# Compile everything
javac Main.java Chatbot.java KarenRageQuitException.java

# Start the simulation
java Main
