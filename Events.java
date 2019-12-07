import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//Ideas:
//Add a delayed event type (or simply a delay parameter) that gives you time to prepare for an event (example: sends a message that signs of sickness have been seen in someone, a few days later an infection breaks out)
//Make some events that allow decisions. (Need to figure out how to do allow this in the event adder. Could be difficult.)

public class Events extends Global{

    static String name = "none";
    static String description = "none";
    static boolean choice1 = false;
    static boolean choice2 = false;
    static Logistics log = new Logistics(); //Note, this is temporary. Move all the logistics stuff to regions later.
    static boolean firstChoice = false;
    static boolean secondChoice = false;
    static String firstChoiceText = "";
    static String secondChoiceText = "";
    double chanceModifierGlobal = 0;
    double getChanceModifierAggression = 0;
    int currentEventID = 0;
    static Random ran = new Random();
    ArrayList<Integer> effects = new ArrayList<Integer>();

    public static void initializer(){
        addEvent("Region Discovery", "Map", "0.005", "1");

        addEvent("Test", "None", "0.0", "2");
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
        name = "none";
        description = "none";
        choice1 = false;
        choice2 = false;
        firstChoice = false;
        secondChoice = false;
        firstChoiceText = "";
        secondChoiceText = "";
        Global.currentEvent = false;
    }

    public static void callRandomEvent(String genre){
        //todo
        ArrayList event = new ArrayList();
        int choice;
        if (genre.equals("Any")){
            choice = ran.nextInt(events.size()-1);
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

    public static void callEvent(int eventID){
        double chance = ran.nextDouble();
        for (ArrayList e: events){
            if (Integer.parseInt((String)e.get(e.size()-1)) == eventID && chance <= Double.parseDouble((String)e.get(2))){
                    if (eventID == 1){
                        discoverTileEvent();
                    }
                    if (eventID == 2){
                        break;

                }
            }
        }
    }

    private static void discoverTileEvent(){
        reset();
        name = "Region Discovery";
        description = "You discovered a nearby region";
        choice1 = true;
        firstChoiceText="Ok!";
        discoverTileEffect();
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

    private void infectRandomEvent(){
        //todo
    }

    private void choiceTestEvent(){

    }


    //todo
    //make some event effects (e.g. infectRandom(disease type), killRandom(number of people), findResource, come up with some more later.)

    //Event: Fish rain from sky, you gain 100 resources
}