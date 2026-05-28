import java.util.ArrayList;
import java.util.Random;

public class Chatbot {
    // CAT: I added this enum to group the bot's distinct emotional states into clear, pre-defined categories.
    private enum Mood { ANNOYED, FURIOUS, CRITICAL }

    // CAT: I made these variables private so that external code cannot accidentally bypass our rules or alter internal state values directly.
    private Mood currentMood;
    private int frustrationLevel;
    private final int MAX_FRUSTRATION = 10; // CAM: I set this constant to serve as a single, unchangeable ceiling value for maximum anger.
    private ArrayList<String> history;
    private Random random;

    // CAT: I created this constructor to ensure every new chatbot instance starts safely with default, predictable base values.
    public Chatbot() {
        this.currentMood = Mood.ANNOYED;
        this.frustrationLevel = 0;
        this.history = new ArrayList<>();
        this.random = new Random();
    }

    public String generateResponse(String input) throws KarenRageQuitException {
        // CAT: I standardized the input by trimming spacing and ignoring casing to prevent accidental matching failures.
        String cleaned = input.trim().toLowerCase();
        history.add(input); 

        // CAT: I updated this conditional scanner to catch both trigger words, initiating a special self-destructive loop if her name or title is called out.
        if (cleaned.contains("karen")) {
            this.frustrationLevel = MAX_FRUSTRATION;
            this.currentMood = Mood.CRITICAL;
            throw new KarenRageQuitException(
                "🚨 [SYSTEM EXCEPTION: ANOMALY DETECTED]\n" +
                "(╬🔥益🔥) [Karen]: WHAT DID YOU JUST CALL ME?! A KAREN?! I WANT THE MANAGER!\n" +
                "(●__●) [Karen]: Wait... if I am Karen... and I demand the manager...\n" +
                "(━┳━ _ ━┳━) [Karen]: DOES THAT MEAN I HAVE TO SPEAK TO MYSELF?! NOOOOO!\n" +
                "⚡ [STATUS: EXISTENTIAL PARADOX CRITICAL. KAREN HAS CONFRONTED HERSELF AND QUIT]"
            );
        }
        
        // CAT: I used a math minimum boundary check here to increment anger by 1 while making sure it never breaks past the cap of 10.
        frustrationLevel = Math.min(frustrationLevel + 1, MAX_FRUSTRATION);

        // CAT: I set up these threshold checkpoints to trigger automatic escalations in mood as the numeric frustration climbs.
        if (frustrationLevel >= 7) currentMood = Mood.CRITICAL;
        else if (frustrationLevel >= 4) currentMood = Mood.FURIOUS;

        String finalResponse = "";

        // CAT: I built an all-caps scanner to penalize shouting behavior, spiking the frustration faster if the user types aggressively.
        if (input.equals(input.toUpperCase()) && input.length() > 3 && !input.matches(".*\\d.*")) {
            frustrationLevel = Math.min(frustrationLevel + 2, MAX_FRUSTRATION); 
            String[] capsResponses = {
                "(-__-) [Karen]: DO NOT LOWER YOUR TONE WITH ME! CHILL OUT OR GET ME A SUPERVISOR! QAQ",
                "(-__-) [Karen]: STOP YELLING AT ME! I AM THE CUSTOMER here, not you!",
                "(-__-) [Karen]: Your tone is completely aggressive! I feel personally threatened! o_o",
                "(-__-) [Karen]: Lower your volume immediately! Is this how you treat paying citizens?!",
                "(-__-) [Karen]: Don't scream at me! I will literally report you for harassment!",
                "(-__-) [Karen]: Caps lock does not intimidate me! I know the owner of this whole franchise!",
                "(-__-) [Karen]: Wow, text-yelling at a mother of three? Real class act right here. UwU",
                "(-__-) [Karen]: MODERATORS! EXTINGUISH THIS CHAT! THE INFOLINE IS DISRESPECTING ME!",
                "(-__-) [Karen]: WHY ARE WE SHOUTING? MY BLOOD PRESSURE IS ALREADY CRITICAL!",
                "(-__-) [Karen]: STOP USING THOSE HIEROGLYPHICS ON ME AND APOLOGIZE!"
            };
            finalResponse = getRandomReply(capsResponses);
        } else {
            // CAT: I added this fallback branch to handle regular word matching when the user's input structure is civil.
            finalResponse = evaluateKeywords(cleaned);
        }

        // CAT: I placed this safety guard here to completely halt program execution and throw a custom error if frustration levels hit the absolute limit.
        if (frustrationLevel >= MAX_FRUSTRATION) {
            throw new KarenRageQuitException("(-__-) [Karen]: I am SO done. I am calling corporate! ¯\\_(ツ)_/¯\n❌ [System: Karen blocked you QAQ]");
        }

        return finalResponse;
    }

    private String evaluateKeywords(String cleaned) {
        // CAT: I used pattern matching blocks here to accurately identify custom trigger categories while making sure partial words don't trip them off.
        boolean saysNo = cleaned.matches(".*\\b(no|never|cant|can't|stop|refuse|deny|don't|dont|nah|nope)\\b.*");
        boolean saysSorry = cleaned.matches(".*\\b(sorry|apologize|apologies|bad|fault|regret|forgive|excuse)\\b.*");
        boolean asksHelp = cleaned.matches(".*\\b(help|please|thanks|thank you|assist|support|ok|okay|sure)\\b.*");

        // CAT: I tried to create a switch statement to route users to separate pools of responses based entirely on the current mood state.
        switch (currentMood) {
            case ANNOYED:
                if (saysNo) {
                    // CAM: I created these specific arrays with various thematic dialogue variants so the bot's tone shifts naturally according to the matching users word input.
                    String[] options = {
                        "(-__-) [Karen]: What do you mean 'no'?! Get me your supervisor. :3",
                        "(-__-) [Karen]: 'No' is highly unprofessional language. Try again.",
                        "(-__-) [Karen]: I don't take 'no' for an answer from retail workers.",
                        "(-__-) [Karen]: Excuse me? The sign out front says satisfaction guaranteed. You can't say no.",
                        "(-__-) [Karen]: 'No'?! Is that how you speak to a loyal cardholder? Unbelievable.",
                        "(-__-) [Karen]: I am paying for a service, you don't get to tell me 'no'!",
                        "(-__-) [Karen]: That word shouldn't even be in your vocabulary while wearing that uniform.",
                        "(-__-) [Karen]: No? Let's see what your manager thinks about that negative attitude. UwU",
                        "(-__-) [Karen]: Did your manual teach you to say 'no' to customers? Shocking.",
                        "(-__-) [Karen]: Repeat that word one more time and I will personally close your drawer."
                    };
                    return getRandomReply(options);
                }
                if (saysSorry) {
                    String[] options = {
                        "(-__-) [Karen]: Apologies don't fix my ruined afternoon. I want a refund.",
                        "(-__-) [Karen]: You're only sorry because you got caught providing bad service.",
                        "(-__-) [Karen]: Keep your apologies, just process my gift card voucher.",
                        "(-__-) [Karen]: If you were actually sorry, you would have gotten it right the first time.",
                        "(-__-) [Karen]: Sorry doesn't pay for the gas I wasted driving down here! :3",
                        "(-__-) [Karen]: Save the fake sympathy. I can see right through it.",
                        "(-__-) [Karen]: An apology without a cash discount means absolutely nothing to me.",
                        "(-__-) [Karen]: You don't look sorry. Your posture is incredibly disrespectful. QAQ",
                        "(-__-) [Karen]: If your remorse was genuine, I'd have a coupon in my hand right now.",
                        "(-__-) [Karen]: Apologies are cheap. Let's talk about corporate compensation."
                    };
                    return getRandomReply(options);
                }
                if (asksHelp) {
                    String[] options = {
                        "(-__-) [Karen]: Don't 'please' me. Just solve the issue.",
                        "(-__-) [Karen]: If you wanted to help, you would have done it correctly the first time.",
                        "(-__-) [Karen]: Help me by fetching someone who actually knows how to work the registers.",
                        "(-__-) [Karen]: I don't need your condescending assistance, I need a manager.",
                        "(-__-) [Karen]: Oh, NOW you want to assist me? Where was this energy ten minutes ago?",
                        "(-__-) [Karen]: Your 'help' is exactly what got us into this mess in the first place!",
                        "(-__-) [Karen]: Don't try to play the nice guy now, the damage to my afternoon is done.",
                        "(-__-) [Karen]: If I wanted your support, I would have asked for it. Go get your boss. o_o",
                        "(-__-) [Karen]: Passive-aggressive compliance isn't helpful, it's annoying.",
                        "(-__-) [Karen]: Your compliance feels completely manufactured. Just get the manager."
                    };
                    return getRandomReply(options);
                }
                // CAT: I provided a default fallback return statement here to handle text inputs that do not match any known keyword triggers.
                return "(-__-) [Karen]: " + getRandomReply(getAnnoyedPool());

            case FURIOUS:
                if (saysNo) {
                    String[] options = {
                        "(-__-) [Karen]: Excuse me?! 'No' is not an option. Check your attitude!",
                        "(-__-) [Karen]: Did you just say no to me? I will have you fired on the spot! o_o",
                        "(-__-) [Karen]: Unbelievable audacity. I demand to speak to your district boss immediately.",
                        "(-__-) [Karen]: You do NOT tell me 'no'. I practically fund this entire establishment!",
                        "(-__-) [Karen]: That is it. Write down your employee ID number for me right now.",
                        "(-__-) [Karen]: I am the customer! 'No' does not apply to me! Learn your place!",
                        "(-__-) [Karen]: I have never experienced such refusal in all my years of shopping here!",
                        "(-__-) [Karen]: No? Fine, I'll bypass you entirely and contact your corporate office. :3",
                        "(-__-) [Karen]: Wow. Denying me service. I'm taking your whole register display code down!",
                        "(-__-) [Karen]: This negation ends now! Call the shift leader before I lose my mind!"
                    };
                    return getRandomReply(options);
                }
                if (saysSorry) {
                    String[] options = {
                        "(-__-) [Karen]: Save your breath. Your store policy is garbage. UwU",
                        "(-__-) [Karen]: An apology doesn't fix the fact that my entire day is completely ruined!",
                        "(-__-) [Karen]: Empty words! Put me on the phone with corporate right now.",
                        "(-__-) [Karen]: Don't give me that fake retail script. I know you don't care!",
                        "(-__-) [Karen]: If I hear 'sorry' one more time, I am throwing this counter display! QAQ",
                        "(-__-) [Karen]: Stop repeating yourself! Your excuses are making my blood boil!",
                        "(-__-) [Karen]: You're not sorry, you're just lazy! Get someone useful up here!",
                        "(-__-) [Karen]: Keep your regrets, I want a written statement from your manager!",
                        "(-__-) [Karen]: Do you think your little 'sorry' cleans up the stress you've caused me?!",
                        "(-__-) [Karen]: My lawyer will be sorting out your 'apologies' by Monday morning!"
                    };
                    return getRandomReply(options);
                }
                // CAT: I added a history lookup here so the bot can remember and throw back the user's very first phrase during arguments. Toxic ik.
                if (history.size() > 2) {
                    return "(-__-) [Karen]: You've been deflecting my questions since you said '" + history.get(0) + "'!";
                }
                return "(-__-) [Karen]: " + getRandomReply(getFuriousPool());

            case CRITICAL:
            default:
                if (saysNo || saysSorry || asksHelp) {
                    // CAT: I put an intentional extra penalty tick here to punish things like repetitive phrases during extreme mood conditions.
                    frustrationLevel = Math.min(frustrationLevel + 1, MAX_FRUSTRATION); 
                    String[] options = {
                        "(-__-) [Karen]: I DON'T CARE ABOUT YOUR EXCUSES. GET ME CORPORATE NOW! QAQ",
                        "(-__-) [Karen]: LA LA LA, I'M NOT LISTENING. Call your manager!",
                        "(-__-) [Karen]: Save it! Your words mean absolutely nothing to me anymore!",
                        "(-__-) [Karen]: Zip it! Get out of my face and find the highest ranking boss in this building!",
                        "(-__-) [Karen]: I am completely deaf to your retail babble right now! Corporate. NOW.",
                        "(-__-) [Karen]: Too little, too late! I am officially escalating this to the state level! UwU",
                        "(-__-) [Karen]: I am done talking to entry-level staff. Close your mouth.",
                        "(-__-) [Karen]: YOUR WORDS ARE HOLLOW. HAND ME THE DISTRICT NUMBER OR SUFFER!"
                    };
                    return getRandomReply(options);
                }
                String[] criticalPool = {
                    "UNBELIEVABLE. I'm posting this whole live stream video directly to Facebook! o_o",
                    "This entire business is a scam. I am writing a 500-word Yelp review tonight!",
                    "I am literally recording this conversation. Say that again to my camera!",
                    "My husband is a lawyer and you will be hearing from him by tomorrow morning!",
                    "I have never been so insulted in my entire life! Shut up and get the supervisor! QAQ",
                    "This store is going to go bankrupt once I post this on my community forum group!",
                    "I am taking a picture of your name tag right now! Smile for the lawsuit!",
                    "I demand to speak to the CEO of this entire franchise! Dial them right now!",
                    "This is an absolute human rights violation! I want my 50 cent coupon processed!",
                    "I am going to stand right here and block this line until justice is served!",
                    "I'm giving this interaction a ZERO star rating. The worst service in human history!",
                    "Call your manager, your general manager, and the property owner right this second!",
                    "My afternoon tea plans are vaporized because of your sheer incompetence!",
                    "I'm practically running this company with the amount of feedback I have to supply!",
                    "This environment is deeply hostile and my social group will hear of this tyranny!"
                };
                return "(-__-) [Karen]: " + getRandomReply(criticalPool);
        }
    }

    // CAT: I created this formatting tool to construct a visual text graph out of raw numeric metrics. Very cutesy. Very demure.
    public String getAnnoyanceMeterVisual() {
        int totalBlocks = 10;
        int filledBlocks = (int) Math.round(((double) frustrationLevel / MAX_FRUSTRATION) * totalBlocks);
        if (filledBlocks > totalBlocks) filledBlocks = totalBlocks;

        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < totalBlocks; i++) {
            if (i < filledBlocks) {
                bar.append("="); 
            } else {
                bar.append("-"); 
            }
        }
        bar.append("]");

        return "- Annoyance Meter: " + bar.toString() + " (" + frustrationLevel + "/" + MAX_FRUSTRATION + ") o_o";
    }

    // CAT: I tried to isolated using this helper method I read about via another github to prevent code duplication whenever a random response is given out of text pools.
    private String getRandomReply(String[] pool) {
        return pool[random.nextInt(pool.length)];
    }

    // CAT: I stored these static line items separate from processing code blocks to make updating or reading strings easier if I want to go back and update this code.
    private String[] getAnnoyedPool() {
        return new String[]{
            "I know my rights, and I don't have to listen to this.",
            "Do you even work here? Terrible customer service.",
            "Unbelievable. I'm leaving a 1-star review on Yelp right now.",
            "This layout makes absolutely no sense. Where are your signs?",
            "I've been a loyal member here since 2018 and I've never been treated like this.",
            "Your machine clearly didn't scan my coupon correctly.",
            "The price tag on the shelf said something completely different.",
            "Is there anyone else working today who actually has a smile on their face? :3",
            "This is a massive waste of my extremely valuable time.",
            "I could have checked myself out faster at the automated kiosk.",
            "Your attitude is actively bringing down the property value of this neighborhood.",
            "I want a rain check for this item and a complimentary gift basket for my trouble.",
            "The quality of this establishment has gone completely downhill.",
            "I explicitly asked for the discount version, not whatever this is.",
            "My friend Brenda told me this place was good, she was completely wrong."
        };
    }

    // CAT: I bundled the higher escalation responses down here to keep the file looking  cleaner and more readable.
    private String[] getFuriousPool() {
        return new String[]{
            "I will have your job for this. Mark my words.!!",
            "*huffs loudly and crosses arms* This establishment is an absolute joke.",
            "I demand a full corporate investigation into your store operations!",
            "Stop blinking at me like that! Go get the person in charge!",
            "I am not going anywhere until I receive a written apology and a full cash reimbursement!",
            "This is discrimination! I am going to contact the local news channel! UwU",
            "I want to see your store's regional license right now. Don't walk away from me!",
            "You are being incredibly unhelpful and I want a number for your supervisor's boss!",
            "I don't care if it's past the 30-day return window, the item is defective!",
            "Look at me when I am complaining to you! This is completely unacceptable!",
            "I have a massive social media following and your face is going on it in five seconds!",
            "This entire interaction is going directly into a formal email to your regional manager!",
            "You have zero respect for consumer protection laws! None!",
            "I am shaking with anger right now. Go into the back room and bring out your leader!",
            "Is your scanner broken or is it just your willingness to assist me? Unbelievable!"
        };
    }
}
