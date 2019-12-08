import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//Ideas:
//Add a delayed event type (or simply a delay parameter) that gives you time to prepare for an event (example: sends a message that signs of sickness have been seen in someone, a few days later an infection breaks out)
//Make some events that allow decisions. (Need to figure out how to do allow this in the event adder. Could be difficult.)

public class Events extends Global{

    static int eventID = -1;
    static String name = "none";
    static String description = "none";
    static boolean choice1 = true;
    static boolean choice2 = false;
    static boolean choice3 = false;
    static boolean choice4 = false;
    static Logistics log = new Logistics(); //Note, this is temporary. Move all the logistics stuff to regions later.
    static String firstChoiceText = "";
    static String secondChoiceText = "";
    static String thirdChoiceText = "";
    static String fourthChoiceText = "";
    double chanceModifierGlobal = 0;
    double getChanceModifierAggression = 0;
    int currentEventID = 0;
    static Random ran = new Random();
    ArrayList<Integer> effects = new ArrayList<Integer>();

    public static void initializer(){
        addEvent("Region Discovery", "Map", "0.005", "1");

        addEvent("Fish Fiasco", "Fish", "0.0001", "2");

        addEvent("Wanderer", "Population", "1", "3");

    }


    private static void addEvent(String name, String genre, String chance, String EventID){
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
        //EffectID: Obvious.
        //Chance: Determines how likely it is for the event to occur. Percentage.
        //And I'm sure some other stuff too
        ArrayList out = new ArrayList();
        out.add(name);
        out.add(genre);
        out.add(chance);
        out.add(EventID);
        events.add(out);
    }

    public static void reset(){
        eventID = -1;
        name = "none";
        description = "none";
        choice2 = false;
        choice3 = false;
        choice4 = false;
        firstChoiceText = "";
        secondChoiceText = "";
        thirdChoiceText = "";
        fourthChoiceText = "";
        Global.currentEvent = false;
    }

    public static void callRandomEvent(String genre){
        //todo
        ArrayList event = new ArrayList();
        int choice;
        if (genre.equals("Any")){
            choice = ran.nextInt(events.size());
            event = events.get(choice);
            callEvent(Integer.parseInt((String) event.get(event.size()-1)));
        }
        else{
            ArrayList genreBasedList = new ArrayList();
            for(ArrayList e: events){
                if(e.contains(genre)){
                    genreBasedList.add(e);
                }
            }
            choice = ran.nextInt(genreBasedList.size() - 1);
            event = (ArrayList)genreBasedList.get(choice);
            callEvent(Integer.parseInt((String)event.get(event.size()-1)));
        }
    }

    private void callSpecificEvent(int eventID){
        //allows you to call a specific event
        //bypasses the chance
        for (ArrayList e: events){
            for(Object x: (ArrayList)e.get(7)){
                int effect = Integer.parseInt((String)x);
                if (effect == 1){

                }
                if (effect == 2){

                }
            }
        }
    }

    private static void callEvent(int eventID){
        double chance = ran.nextDouble();
        for (ArrayList e: events){
            if (Integer.parseInt((String)e.get(e.size()-1)) == eventID && chance <= Double.parseDouble((String)e.get(2))){
                    if (eventID == 1){
                        discoverTileEvent();
                    }
                    if (eventID == 2){
                        fishFiascoEvent();
                }
                    if(eventID == 3){
                        wandererEvent();
                    }
            }
        }
    }

    private static void discoverTileEvent(){
        reset();
        name = "Region Discovery";
        description = "You discovered a nearby region";
        firstChoiceText="Ok!";
        Global.currentEvent = true;
    }

    private static void fishFiascoEvent(){
        reset();
        eventID = 2;
        name = "Fish Fiasco!";
        choice2 = true;
        description = "The sky suddenly turns dark as thousands of fish appear in the sky and blot out the sun.\nA moment passes and the fish begin to rain down upon your village.";
        firstChoiceText = "Try to salvage what you can as food";
        secondChoiceText = "These fish are clearly cursed. Remove them from the village immediately";
        Global.currentEvent = true;
    }

    private static void wandererEvent(){
        reset();
        eventID = 3;
        System.out.println(eventID);
        name = "Wanderer";
        choice2 = true;
        description = "A wayworn wanderer arrives at your village. They seem tired, and ask if they can stay for a while.";
        firstChoiceText = "Allow them to stay at your village";
        secondChoiceText = "Turn them away. It's hard enough to manage the people you already have.";
        Global.currentEvent = true;
    }

    private static void discoverTileEffect(){
        //todo: you can discover the same tile twice. Fix this later.
        Region randomRegion;
        Region discoveredRegion;
        ArrayList<Region> discRegions = new ArrayList<Region>();
        ArrayList<Region> possibleTiles = new ArrayList<Region>();
        int counter;
        for(Region r: regions){
            if(r.discovered){
                discRegions.add(r);
            }
        }
        if (discRegions.size()> 1) {
            randomRegion = discRegions.get(ran.nextInt(discRegions.size() - 1));
            possibleTiles = log.getNearbyTiles(randomRegion, 1);
            while(true) {
                counter = 0;
                for (Region reg : possibleTiles) {
                    if (reg.discovered) {
                        counter++;
                    }
                }
                if (counter >= possibleTiles.size()-1) {
                    randomRegion = discRegions.get(ran.nextInt(discRegions.size() - 1));
                    possibleTiles = log.getNearbyTiles(randomRegion, 1);
                }
                else if(counter < possibleTiles.size()){
                    break;
                }
            }
            discoveredRegion = possibleTiles.get(ran.nextInt(possibleTiles.size() - 1));
            counter = 0;
            while(discoveredRegion.discovered || counter >= 30){
                counter++;
                discoveredRegion = possibleTiles.get(ran.nextInt(possibleTiles.size() - 1));
                if(!discoveredRegion.discovered){
                    break;
                }
            }
            discoveredRegion.discovered = true;
        }
        else{
            for (Region reg:regions)
            {
                if ((reg.row==7)&(reg.pos==15))
                {
                    possibleTiles = log.getNearbyTiles(reg, 1);
                    discoveredRegion = possibleTiles.get(ran.nextInt(possibleTiles.size() - 1));
                    while(true) {
                        counter = 0;
                        for (Region region : possibleTiles) {
                            if (region.discovered) {
                                counter++;
                            }
                        }
                        if (counter == possibleTiles.size()) {
                            randomRegion = discRegions.get(ran.nextInt(discRegions.size() - 1));
                            possibleTiles = log.getNearbyTiles(randomRegion, 1);
                        }
                        else{break;}
                    }
                    counter = 0;
                    while((discoveredRegion.row == 8 && discoveredRegion.pos == 16) || discoveredRegion.discovered || counter == 30){
                        counter++;
                        discoveredRegion = possibleTiles.get(ran.nextInt(possibleTiles.size() - 1));
                        if(!discoveredRegion.discovered && !(discoveredRegion.row == 8 && discoveredRegion.pos == 16)){
                           break;
                        }
                    }
                    discoveredRegion.discovered = true;
                    break;
                }
            }
        }
    }

    public static void fishFiascoEffect(String choice){
            if (choice.equals("choice1")) {
                description = "You managed to salvage 30 fish";
                for (ArrayList resource : ownedResources) {
                    if (resource.get(0).equals("Fish")) {
                        resource.set(1, (double) resource.get(1) + 30);
                    }
                }
            } else if (choice.equals("choice2")) {
                description = "You managed to clear the fish out of your village. The smell, however, remains.";
            }
        firstChoiceText = "Ok!";
        choice2 = false;
        choice3 = false;
        choice4 = false;
        eventID = -1;
    }

    public static void newVillagerEffect(String choice){
        if(choice.equals("choice1")){
            Person person = new Person(People.size());
            description = person.name + " has joined your village!";
            People.add(person);
        }
        else if(choice.equals("choice2")){
            description = "You have turned the wanderer away. They venture off, their fate unknown.";
        }
        firstChoiceText = "Ok";
        choice2 = false;
        choice3 = false;
        choice4 = false;
        eventID = -1;
    }


    private void infectRandomEvent(){
        //todo
    }

    private void choiceTestEvent(){

    }

    //todo
    //make some event effects (e.g. infectRandom(disease type), killRandom(number of people), findResource, come up with some more later.)

    //Event: Fish rain from sky, you gain 100 resources
}