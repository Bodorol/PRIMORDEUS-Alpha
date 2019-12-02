
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//todo:
//make it so buildings are assigned to a region
//add more buildings
//add more effects
//Make it so you can only make one building at a time (use a focus variable like in science?) Do this after you move to GUI.
//Make addInfluence method for town hall building
//Add queue features to constructing buildings
//Add abilities module. Not just for manual building effects, but for abilities in general (for example, allows you to spend influence to gain food or something). Might want to move to separate module later.
//Add upkeep cost
//Add some gosh-darned documentation to your methods, you slacker
//Add region restrictions to buildings (e.g. can only be built near ocean, in forest, etc.)
//Add a toString method
//Add an autobuild method of some sort which automatically fills a region with buildings.

//Ideas for Buildings:
//Town hall, has a leader occupation and that's about it. (maybe produces influence?) Triggers the Leader effect which sets global hasLeader to true which enables certain abilities.
//Different types of farms. Produces food types. Has a farmer occupation.
//Theater building. Produces happiness, and has a performer occupation.
//Church/shrine. Produces influence. Not sure what occupation (priest?)
//Hospital. Treats sick, has doctors.
//Temple. Has priests or whatever. Treats sick and provides influence (and maybe gold?).
//Stable. Has horses that help with logistics. Not sure on occupation. (Stable person?) Finish logistics before adding this.
//Watch tower. Adds guards, increases defense.
//Road. No occupation. It's a road. Allows connections with other regions through the hasRoad effect

//Occupation Ideas:
//Doctor. Has treatSick method
//Soldier. Increases attack stat (which doesn't exist yet.)
//Guard. Increases defense stat (which doesn't exist yet.)
//Leader. Adds influence and allows the use of certain abilities (which I haven't come up with yet.)

public class Building extends Global
{

    //Takes up resource
    //Takes up a building slot
    //Attributes
    private Scanner reader = new Scanner(System.in);
    int buildingID;
    int uniqueID;
    String name;
    String description;
    int size;
    double workNeeded;
    String occupation;
    int capacity;
    int workers = 0;
    int limit;
    double modifier;
    int delay = 0;
    int counter = 0;
    int manualDelay = 0;
    int manualCounter = 0;
    int focus = 0;
    int upgradeLevel = 0;
    double efficiency = 0.1;
    int maxUpgrade;
    //todo: Region region;
    ArrayList<String> preRecsNeeded = new ArrayList<String>();
    ArrayList<Integer> effects = new ArrayList<Integer>();
    ArrayList<Person> workersList = new ArrayList<Person>();





    public Building(int buildingID){
        //todo:
        //Add a region parameter in the caller
        for (ArrayList b:buildings){
            if (Integer.parseInt((String)b.get(b.size() - 1)) == buildingID){
                this.name =  (String)b.get(0);
                this.description = (String)b.get(1);
                this.size = Integer.parseInt((String)b.get(2));
                this.workNeeded = Double.parseDouble((String)b.get(3));
                for (Object e: (ArrayList)b.get(4)){
                    int effect = (int)e;
                    this.effects.add(effect);
                }
                this.occupation = (String)b.get(5);
                this.capacity = Integer.parseInt((String)b.get(6));
                this.limit = Integer.parseInt((String)b.get(7));
                this.modifier = Double.parseDouble((String)b.get(8));
                this.maxUpgrade = Integer.parseInt((String)b.get(9));
                this.preRecsNeeded = (ArrayList)b.get(10);
                //todo: this.region = region;
                this.buildingID = buildingID;
                if (this.effects.contains(-1)){
                    initializer();
                }
            }
        }

        }

    private void initializer(){
        //todo
        //write explanation on how to create your own building and upgrade with explanations for each parameter

        //Alright, here's how to use! You call addBuilding with the following parameters in the order they're listed.
        //1: Name. Pretty self explanatory.
        //2: Description. Brief description of what the building does. Maybe include occupation and what it produces.
        //3: Size. This isn't functional yet, but this will determine how many building slots it takes up.
        //4: Work Needed. This determines how much work it will take before the building is fully constructed.
        //5: Effects. This determines the effects the building will have. Make it as an arraylist. You'll need to find the effect IDs from the callEffect method. (Note to self: list the effectIDs somewhere later on for easier use.)
        //6: Occupation. Determines the type of occupation the building will create.
        //7: Capacity. Determines the number of people that can have the occupation per building.
        //8: Limit. Determines the amount of this type of building that can be built.
        //9: Modifier. This determines how "powerful" the building is. In other words, how much of an effect it has. Look at the effect methods to get a better idea of what this does.
        //10: Building ID. Just gives the building its own ID that allows it to be constructed. Must be unique for each building type, otherwise you end up overwriting buildings.
        //11: Max upgrade level. Allows multiple upgrades on this building type, with the max upgrade level being the maximum (I guess that's kinda obvious. Re-write explanation later.)
        //12: Prerequisites for constructing the building. Make as an arraylist with the science and resource prerequisites you want. (Note to self: it would be better if you did this like the effects parameter. Change later)

        //Note to self: Do another explanation for addUpgrade and addOccupation

        addBuilding("Null", "Important please do not delete, also if this shows up in game something is wrong", "0", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{-1})),
                "Null", "0", "1", "0", "0", "0",
                new ArrayList<String>(Arrays.asList(new String[]{"Null"})));

        addBuilding("Test Building", "For Testing", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{1, 2})),
                "Tester", "1", "1", "1", "1", "3",
                new ArrayList<String>(Arrays.asList(new String[]{"Stone", "science stuff"})));

        addBuilding("Town Hall", "Allows you to have a leader", "1", "1", new ArrayList<Integer>(Arrays.asList(new Integer[]{-1})),
                "Leader", "1", "1", "1", "2", "3",
                new ArrayList<String>(Arrays.asList(new String[]{"Stone", "science stuff"})));

        addBuilding("Farm", "Produces food", "1", "1", new ArrayList<Integer>(Arrays.asList(new Integer[]{-1})),
                "Farmer", "1", "1", "1", "3", "3",
                new ArrayList<String>(Arrays.asList(new String[]{"Stone", "science stuff"})));
    }


    private void addBuilding(String name, String description, String size, String workNeeded, ArrayList effectIDs, String occupation, String capacity, String limit, String modifier, String buildingID, String maxUpgradeLevel, ArrayList preRecsNeeded){
        /************************************************************************
         * METHOD: addBuilding                                                  *
         * USE: creates and adds a Building with given strings                  *
         * INPUT: Strings name, size, effect, occupation, buildingID            *
         ************************************************************************/
        //addBuilding("Name", "Description", "Size", "Work needed", "Effect ID", "Occupation", "Capacity", "Limit", "Building ID", "Prerequirements needed")
        //add description of what each of these does
        ArrayList out = new ArrayList();
        out.add(name);
        out.add(description);
        out.add(size);
        out.add(workNeeded);
        out.add(effectIDs);
        out.add(occupation);
        out.add(capacity);
        out.add(limit);
        out.add(modifier);
        out.add(maxUpgradeLevel);
        out.add(preRecsNeeded);
        out.add(buildingID);
        buildings.add(out);
        }

    private void addUpgrade(String buildingID, String description, String newModifier, String newCapacity, String newEfficiency, String newDelay, String wokrRequired, ArrayList additionalEffects, ArrayList preRecsNeeded, String upgradeNumber){
        /************************************************************************
         * METHOD: addBuilding                                                  *
         * USE: creates and adds a Building with given strings                  *
         * INPUT: Strings name, size, effect, occupation, buildingID            *
         ************************************************************************/

        //todo
        //needs to have a work required parameter
        ArrayList out = new ArrayList();
        out.add(buildingID);
        out.add(description);
        out.add(newModifier);
        out.add(newCapacity);
        out.add(newEfficiency);
        out.add(newDelay);
        out.add(wokrRequired);
        out.add(additionalEffects);
        out.add(preRecsNeeded);
        out.add(upgradeNumber);
        upgrades.add(out);

    }

    private void addOccupation(String name, String description, ArrayList requiredBuildings, ArrayList statRequirements, String occupationID){
        ArrayList out = new ArrayList();
        out.add(name);
        out.add(description);
        out.add(requiredBuildings);
        out.add(statRequirements);
        out.add("0"); //What the heck is this? I don't remember doing this. Maybe this was meant for something else?
        out.add(occupationID);
        occupations.add(out);
    }

    public void assignOccupationAuto(ArrayList occupation){
        for (Person p: People){
            if(assignCheck(p, occupation) && this.capacity > 0){
                p.occupation = (String)occupation.get(0);
                this.capacity -= 1;
                this.workers += 1;
                this.workersList.add(p);
            }
        }
    }

    public void assignOccupationManual(Person person, ArrayList occupation){
        if(assignCheck(person, occupation)){
            person.occupation = (String)occupation.get(0);
            this.capacity -= 1;
            this.workers += 1;
            this.workersList.add(person);
        }
    }

    public boolean assignCheck(Person person, ArrayList occupation){
        //checks stats and tells you if they can take the job. Returns true if they meet requirements, returns false if they don't
        ArrayList statRequirements = (ArrayList)(occupation.get(3));
        if(person.strength >= (int)statRequirements.get(0) && person.perception >= (int)statRequirements.get(1) && person.intelligence >= (int)statRequirements.get(2) &&
                person.martial >= (int)statRequirements.get(3) && person.charisma >= (int)statRequirements.get(4) && person.dexterity >= (int)statRequirements.get(5)){
            return true;
        }
        return false;
    }

    public void upgradeBuilding(){
        for (ArrayList u: upgrades){
            if (Integer.parseInt((String)u.get(0)) == this.buildingID){
                if (Integer.parseInt((String)u.get(u.size() - 1)) == this.upgradeLevel + 1){
                    this.modifier += Integer.parseInt((String)u.get(2));
                    this.upgradeLevel += 1;
                    for (Object e: (ArrayList)u.get(3)){
                        int effectNum = (int)e;
                        this.effects.add(effectNum);
                    }
                }
                else{
                    System.out.println("Cannot upgrade building");
                }
            }
            else{
                System.out.println("Error: update error message when you move this stuff to the gui because this doesn't matter right now. Please remember to do this Brenner, don't leave this weird error message in the actual game. If you're seeing this, I was dumb and forgot to change it (Oh, and something went wrong.)");
            }
        }
    }

    public boolean canUpgrade(){
        //todo
        for(ArrayList u: upgrades){
            if (Integer.parseInt((String)u.get(0)) == this.buildingID && Integer.parseInt((String)u.get(u.size() - 1)) == this.upgradeLevel + 1){
                    for(Object p: (ArrayList)u.get(8)){
                        String preRec = (String)p;
                        if(!preRecsAttained.contains(preRec)){
                            return false;
                    }
                }
            }
        }
        return true;
    }

     public void buildingMenu(){
        //todo
        //temporary header until I can ask Ian how he did his, wanna make things consistent
        //also the header messes up in the actual game. Guess it doesn't matter 'cause it's temporary but it's still annoying.
         // fix glitch where the banner keeps showing up even after making a selection (also kinda doesn't matter)
         while (true)
         {
             System.out.print("\033[H\033[2J");
             System.out.flush();
             System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════╗");
             System.out.println("║                                                                                      ║");
             System.out.println("║   ____     _    _   _____   _        _____    _____   _    _   _____     _____       ║");
             System.out.println("║  |  _ \\\\  | |  | | |_   _| | |      |  __ \\\\  |_  _| | \\\\ | | / ____|   / ____|      ║");
             System.out.println("║  | |_) |  | |  | |   | |   | |      | |  | |   | |   |  \\\\| | | |__    | (___        ║");
             System.out.println("║  |   _ <  | |  | |   | |   | |      | |  | |   | |   | .` | | | |_ |   \\\\___ \\\\      ║");
             System.out.println("║  | |_) |  | |__| |  _| |_  | |____  | |__| |  _| |_  | |\\\\  | | |__| |  ____) |      ║");
             System.out.println("║  |____/   \\\\____/  |_____| |______| |_____/  |_____| |_| \\\\_| \\\\_____| |_____/       ║");
             System.out.println("║                                                                                      ║");
             System.out.println("║                                                                                      ║");
             System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════╝");
             System.out.println();
             System.out.println("    1.   Construct Buildings");
             System.out.println();
             System.out.println("    2.   Upgrade Buildings");
             System.out.println();
             System.out.println("    3.   Buildings Under Construction");
             System.out.println();
             System.out.println("    4.   Constructed Buildings");
             System.out.println();

             System.out.print("=>");
             String inp = reader.nextLine();
             if (inp.contains("1"))
             {
                 constructionMenu();
             }
             else if (inp.contains("2"))
             {
                 break; //change to upgradeMenu(); when you have that done
             }
             else if(inp.contains("3")){
                 underConstructionMenu();
            }
            else if(inp.contains("4")){
                constructedMenu();
            }
            else {break;}

         }
     }

     public void constructionMenu(){
        //todo
        //actually allows you to build buildings
        //psuedocode:
        //select building type (if buildable)
        //select region (if discovered)
        //then it sets focus on making that building and consumes work
        int buildingChoice = -1;
        for (ArrayList b: buildings){
            if(Integer.parseInt((String)b.get(b.size() - 1)) != 0){
                System.out.println("");
                System.out.println("┏━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.printf("┃ ID: %-3d ┃",(Integer.parseInt((String)b.get(b.size() - 1))));
                System.out.printf(" Name: %-23s ┃", (String)b.get(0));
                System.out.printf(" Work Required: %-3d  ┃", Integer.parseInt((String)b.get(3)));
                if (buildCheck(Integer.parseInt((String)b.get(b.size() - 1))))
                {
                    System.out.println(" Can Be Built: True  ┃");
                }
                else
                {
                    System.out.println(" Can Be Built: False ┃");
                }
                System.out.println("┗━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━┛");
                }
            }
        while(true){
         System.out.println("Enter the ID of the building you want to build:");
         System.out.print("=>");
         String inp = reader.next();
         for (ArrayList b: buildings) {
             if (inp.equals((String)b.get(b.size() - 1)) && buildCheck(Integer.parseInt(inp))) {
                 System.out.println("Constructing Building: " + (String)b.get(0));
                 buildingChoice = Integer.parseInt((String) b.get(b.size() - 1));
             }
             else if (inp.equals((String) b.get(b.size() - 1)) && !(buildCheck(Integer.parseInt(inp)))) {
                 System.out.println("Cannot build this building yet");
             }
         }
         if (buildingChoice != -1){
             for (Building b: constructedBuildings){
                 b.focus = 0;
             }
             constructedBuildings.add(new Building(buildingChoice));
         }
         break;
        }
     }

     public void upgradeMenu(){
        //todo
        //construction menu extension that allows you to upgrade a building
        //displays list of constructed buildings and allows you to call upgradeBuilding() on it
     }

     public void underConstructionMenu(){
        //todo
        //shows buildings that have had some progress or are the current focus
         for (Building b: constructedBuildings){
             if(b.buildingID != 0){
                 if (b.workNeeded != 0) {
                     System.out.println("");
                     System.out.println("┏━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━┓");
                     System.out.printf("┃ ID: %-3d ┃", b.buildingID);
                     System.out.printf(" Name: %-23s ┃", b.name);
                     System.out.printf(" Work Required: %-3d  ┃", b.workNeeded);
                     System.out.println("┗━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━┛");
                 }
             }
         }
     }

     public void constructedMenu(){
        //todo
        //shows buildings that have already been built
         for (Building b: constructedBuildings){
             if(b.buildingID != 0){
                 if (b.workNeeded == 0) {
                     System.out.println("");
                     System.out.println("┏━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━┓");
                     System.out.printf("┃ ID: %-3d ┃", b.buildingID);
                     System.out.printf(" Name: %-23s ┃", b.name);
                     System.out.println("┗━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━┛");
                 }
             }
         }
     }

     public void updateBuildings(){
         for(Building b: constructedBuildings){
            if (b.workNeeded > 0 && b.focus == 1){
                b.work(10);
             }
             else {
                 b.callEffect();
             }
        }
     }

     public void work(double work){
        //Allows work to be done on a building
        if (this.focus == 1){
            if(work <= workNeeded) {
                this.workNeeded -= work;
            }
            else{
                this.workNeeded = 0;
            }
            if (this.workNeeded == 0){
                for(Building b: constructedBuildings) {
                    if (b.workNeeded > 0 && b.focus == 0) {
                        b.focus = 1;
                        break;
                    }
                }
            }
        }
     }

     public void countBuildings(int buildingID){
        //todo
        //counts the number of type of building that has been made
        //Don't really need this yet, finish it later. It's for when buildings are region-specific.
     }


    public void callEffect(){
        for (int e: effects){
            if (e != -1) {
                if (e == 1) {
                    produceResource();
                }
                if (e == 2) {
                    addHappiness();
                }
                if (e == 3) {
                    addWork();
                }
                if (e == 4) {
                    addScience();
                }
                if (e == 5) {
                    foodPreservation();
                }
                if (e == 6) {
                    produceGold();
                }
            }
        }
    }

    public void reverseEffects(){
        //todo
        //makes it so it undoes any reversable effects when a building is remove
        //make it like callEffect
        //Just build this when you actually have some reversable effects
    }

    public void removeBuilding(){
        //todo
        //make it so it actually removes building
        reverseEffects();
        constructedBuildings.remove(this);
        //and then it removes building from building list
    }

    public boolean buildCheck(int buildingID) {
        for (ArrayList b: buildings) {
            if (Integer.parseInt((String) b.get(b.size() - 1)) == buildingID) {
                for (Object p : (ArrayList)b.get(10)) {
                    String s = (String)p;
                    if (s.equals("Null")) {
                        return true;
                    }
                    if (!preRecsAttained.contains(s)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean buildCheck() {
        //todo
        //make it so it checks limit
        //same for the other buildCheck
        //Do this once you have region specificity
                for (String p : preRecsNeeded) {
                    if (p.equals("Null")) {
                        return true;
                    }
                    if (!preRecsAttained.contains(p)) {
                        return false;
                    }
                }
                return true;
        }

            //todo
            //add guide on how to make an effect (including how to make it unique, manual, etc.)
            private void addRoad(){
                //todo
                //adds a road to the tile the building is made on
                //Make this once you have region specificity
            }

            private void testdelayEffect(){
                //todo
                if (this.counter == 0%(delay+1)){
                    this.popHappiness += modifier + efficiency * workers;
                }
            }

            private void testmanualEffect(int activated){
                //todo
                //make an effect to test manually activated effects (with a delay)
                if (activated == 1 && manualCounter%manualDelay == 0){
                    //actual effect here
                }
                else if(activated == 0){
                    manualCounter++;
                    manualCounter %= manualDelay;
                }
            }

          //  private void testuniqueEffect(){
                //todo: just do this later
                //make an effect to text an effect that can only be used once
                //Idea: make it so it changes the effect id for the unique effect to the negative of the effect id
                //then reverseEffects checks for that negative number when it's called
               // actualEffectHere;
               // for (int e: effects){
               //     if(e == effectIDforThisEffect){
               //         e *= -1;
              //      }
              //  }
          //  }

            private void treatSick(){
                //todo
                //helps sick people recover
            }

            private void addHousing(){
                //todo
                //adds housings slots
            }

            private void foodPreservation () {
                //todo
                //find food lifespan variable
                //Might have to modify the addFood method. Talk with Ian before doing anything.
                //foodLifeSpan = modifier + efficiency*workers;
            }

            private void addScience () {
                //todo
                //find science variable (or make global science variable? Feel like I need to talk to Ian about this before doing anything.)
                //science += modifier + efficiency*workers;
            }

            private void addWork () {
                //todo
                //find work variable (or make global work variable? Feel like I need to talk to Ian about this before doing anything.)
                //work += modifier + efficiency*workers;
            }

            private void addHappiness () {
                this.popHappiness += modifier + efficiency*workers;
            }

            private void produceResource () {
                //todo
                //need to make new method for each individual resource
                //need to figure out how to add resources
                //resource += modifier;
                //or for food:
                //food.add(something or other);
                //Do I have to do that for all resource types?
                //I'm not sure yet, need to ask Ian
            }

            private void AbilityCureSick(){
                //todo
                //Ability that allows you to spend influence to cure one sick person of any disease. Will require some sort of temple or holy building or something
                //Probably has to take in a person as parameter
            }

            private void AbilityCureAllSick(){
                //todo
                //Stronger version of cure sick that allows you to cure everyone in your village for a large amount of influence. Requires and upgrade probably.
            }

            private void produceGold () {
                this.gold += modifier + efficiency*workers ;
            }

            public String getName () {
                return name;
            }

            public String getDescription(){
                return description;
            }

            public int getSize(){
                return size;
            }

            public double getWorkNeeded(){
                return workNeeded;
            }

            public void printEffects () {
                for (int e: effects){
                        if (e == 1) {
                            System.out.println("produceResource();");
                        }
                        if (e == 2) {
                            System.out.println("addHappiness();");
                        }
                        if (e == 3) {
                            System.out.println("addWork();");
                        }
                        if (e == 4) {
                            System.out.println("addScience();");
                        }
                        if (e == 5) {
                            System.out.println("foodPreservation();");
                        }
                        if (e == 6) {
                            System.out.println("produceGold();");
                        }
                    }
            }

            public String getOccupation () {
                return occupation;
            }

            public int getCapacity(){
                return capacity;
            }

            public int getLimit(){
                return limit;
            }

            public int getUpgradeLevel(){
                return upgradeLevel;
            }

            public void printPrerecs(){
                for (String p: preRecsNeeded){
                    System.out.println(p);
                }
            }

    public String toString() {
        //todo
        //make it like the person toString()
        return "";
    }
}