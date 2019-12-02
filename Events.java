import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//Ideas:
//Add a delayed event type (or simply a delay parameter) that gives you time to prepare for an event (example: sends a message that signs of sickness have been seen in someone, a few days later an infection breaks out)
//Make some events that allow decisions. (Need to figure out how to do allow this in the event adder. Could be difficult.)

public class Events extends Global{


    double chanceModifierGlobal = 0;
    double getChanceModifierAggression = 0;
    int currentEventID = 0;
    Random ran = new Random();
    ArrayList<Integer> effects = new ArrayList<Integer>();

    public Events(){
        addEvent("Outbreak With Doctors", "Disease", "Three doctors arrive in your town... However, something seems wrong.",
                "A citizen has contracted the plague!", "0.1", "2", "1", new ArrayList(Arrays.asList(new String[]{"0"})), "1");


    }


    private void addEvent(String name, String genre, String description1, String description2, String chance, String delay, String uniqueness, ArrayList effectIDs, String EventID){
        /************************************************************************
         * METHOD: addBuilding                                                  *
         * USE: creates and adds a Building with given strings                  *
         * INPUT: Strings name, size, effect, occupation, buildingID            *
         ************************************************************************/
        //add description of what each of these does
        //Hmm, maybe the following parameters:
        //Name: Obvious
        //EventID: Obvious (well, maybe not. It's like the building ID.)
        //Genre: Type of event (aggression event, resource event, etc.)
        //Description1: Description that shows when event is initially called
        //Description2: Description that shows when effect is called (after delay)
        //EffectID: Obvious.
        //Chance: Determines how likely it is for the event to occur. Percentage.
        //Delay: Determines how long for the effect to take place after event is called
        //Uniqueness: 0 if the event can be called multiple times, 1 if it can only be called/occur once.
        //And I'm sure some other stuff too
        //todo
        ArrayList out = new ArrayList();
        out.add(name);
        out.add(genre);
        out.add(description1);
        out.add(description2);
        out.add(chance);
        out.add(delay);
        out.add(uniqueness);
        out.add(effectIDs);
        out.add(EventID);
        events.add(out);
    }

    private void callRandomEvent(String genre){
        //todo
        //make sure you have the bound on the random number right
        ArrayList event = new ArrayList();
        int choice;
        if (genre.toLowerCase().equals("any")){
            choice = ran.nextInt(events.size() - 2) + 1;
            event = events.get(choice);
            callEvent(Integer.parseInt((String)event.get(0)));
        }
        else{
            ArrayList genreBasedList = new ArrayList();
            for(ArrayList e: events){
                if(e.contains(genre)){
                    genreBasedList.add(e);
                }
            }
            choice = ran.nextInt(genreBasedList.size() - 2) + 1;
            event = (ArrayList)genreBasedList.get(choice);
            callEvent(Integer.parseInt((String)event.get(0)));
        }
    }

    private void callSpecificEvent(int eventID){
        //allows you to call a specific event
        //bypasses the chance
        for (ArrayList e: events){
            for(Object x: (ArrayList)e.get(7)){
                int effect = Integer.parseInt((String)x);
                if (effect == 1){
                    testEffect();
                }
                if (effect == 2){
                    testEffect2();
                }
            }
        }
    }

    public void callEvent(int eventID){
        double chance = ran.nextDouble();
        for (ArrayList e: events){
            if ((int)e.get(0) == eventID && chance <= (double)e.get(4)){
                for(Object x: (ArrayList)e.get(7)){
                    int effect = Integer.parseInt((String)x);
                        if (effect == 1){
                            testEffect();
                        }
                        if (effect == 2){
                            testEffect2();
                        }
                }
            }
        }
    }

    public void testEffect(){
        this.popHappiness += 10;
    }

    public void testEffect2(){
        this.gold += 100;
    }

    //todo
    //make some event effects (e.g. infectRandom(disease type), killRandom(number of people), findResource, come up with some more later.)
}