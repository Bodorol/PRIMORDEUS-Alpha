import java.util.*;

//todo:
//Add some gosh-darned documentation to your methods, you slacker
//Add region restrictions to buildings (e.g. can only be built near ocean, in forest, etc.)
//Add a toString method
//Add an autobuild method of some sort which automatically fills a region with buildings.
//Make it so you can remove buildings from the queue
//Add some notes on methods that would be useful for Ian to use
//Make an abilityCheck method that tells you if you can use an ability or not

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
    String name;
    String description;
    int size;
    double workNeeded;
    String occupation;
    int capacity;
    Boolean init = false;
    int workers = 0;
    int productivity = 0;
    int limit;
    double modifier;
    int delay = 0;
    int counter = 0;
    int manualDelay = 0;
    int manualCounter = 0;
    int focus = 0;
    int upgradeLevel = 0;
    double upkeepCost = 0;
    double efficiencyModifier = 0.01;
    double efficiency = productivity*efficiencyModifier; //todo: move this somewhere so it'll be updated when productivity increases
    int maxUpgrade;
    Region region;
    String regionName = "null";
    public ArrayList<String> preRecsNeeded = new ArrayList<String>();
    public ArrayList<String> resourceCost = new ArrayList<String>();
    ArrayList<Integer> effects = new ArrayList<Integer>();
    ArrayList<Person> workersList = new ArrayList<Person>();





    public Building(int buildingID, Region region, String regionName){
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
                if(this.capacity > 0){
                    if(!availableOccupations.contains(occupation)) {
                        availableOccupations.add(occupation);
                    }
                }
                this.limit = Integer.parseInt((String)b.get(7));
                this.modifier = Double.parseDouble((String)b.get(8));
                this.maxUpgrade = Integer.parseInt((String)b.get(9));
                this.preRecsNeeded = (ArrayList)b.get(10);
                this.upkeepCost = Integer.parseInt((String)b.get(11));
                this.resourceCost = (ArrayList)b.get(13);
                this.region = region;
                this.region.slots -= size;
                this.regionName = regionName;
                this.buildingID = buildingID;
                for (Building bld: constructedBuildings){
                    if (bld.focus == 1){
                        bld.focus = 0;
                    }
                }
                this.focus = 1;
                break;
            }
        }

    }

    public Building(){
        this.name = "Null";
        this.description = "Null";
        this.size = 0;
        this.workNeeded = 0;
        this.effects = new ArrayList<Integer>(Arrays.asList(new Integer[]{-1}));
        this.occupation = "Null";
        this.capacity = 0;
        this.limit = 1;
        this.modifier = 0;
        this.buildingID = 0;
        this.maxUpgrade = 0;
        this.preRecsNeeded = new ArrayList<String>(Arrays.asList(new String[]{"Null"}));
    }

    public void initializer(){
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

        addBuilding("Town Hall", "Allows you to have a leader", "1", "1", new ArrayList<Integer>(Arrays.asList(new Integer[]{-1})),
                "Leader", "1", "1", "1", "2", "3", "5", "10",
                new ArrayList<String>(Arrays.asList(new String[]{"testing"})), new ArrayList<String>(Arrays.asList(new String[]{"Fish 1"})), new ArrayList<String>(Arrays.asList(new String[]{"Wood"})), "plains");

        addBuilding("Farm", "Produces food", "1", "1", new ArrayList<Integer>(Arrays.asList(new Integer[]{-1})),
                "Farmer", "1", "1", "1", "3", "3", "5", "10",
                new ArrayList<String>(Arrays.asList(new String[]{"testing"})), new ArrayList<String>(Arrays.asList(new String[]{"Fish 1"})), new ArrayList<String>(Arrays.asList(new String[]{"Wood"})), "plains");

        addBuilding("Charcuterie", "Allows you to slaughter livestock to produce meat", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{-1})),
                "Butcher", "1", "1", "1", "4", "3", "5", "10",
                new ArrayList<String>(Arrays.asList(new String[]{"testing"})), new ArrayList<String>(Arrays.asList(new String[]{"Fish 1"})), new ArrayList<String>(Arrays.asList(new String[]{"Wood"})), "plains");

        addBuilding("Theater", "Provides happiness for your citizens", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{-1})),
                "Performer", "1", "1", "1", "5", "3", "5", "10",
                new ArrayList<String>(Arrays.asList(new String[]{"testing"})), new ArrayList<String>(Arrays.asList(new String[]{"Fish 1"})), new ArrayList<String>(Arrays.asList(new String[]{"None"})), "None");

        addBuilding("Church", "Provides influence", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{-1})),
                "Priest", "1", "1", "1", "6", "3", "5", "10",
                new ArrayList<String>(Arrays.asList(new String[]{"testing"})), new ArrayList<String>(Arrays.asList(new String[]{"Fish 1"})), new ArrayList<String>(Arrays.asList(new String[]{"Wood"})), "plains");

        addBuilding("Hospital", "Treats sick people", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{-1})),
                "Doctor", "1", "1", "1", "7", "3", "5", "10",
                new ArrayList<String>(Arrays.asList(new String[]{"testing"})), new ArrayList<String>(Arrays.asList(new String[]{"Fish 1"})), new ArrayList<String>(Arrays.asList(new String[]{"Wood"})), "plains");

        addBuilding("Watch tower", "Provides defense", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{-1})),
                "Guard", "1", "1", "1", "8", "3", "5", "10",
                new ArrayList<String>(Arrays.asList(new String[]{"testing"})), new ArrayList<String>(Arrays.asList(new String[]{"Fish 1"})), new ArrayList<String>(Arrays.asList(new String[]{"Wood"})), "plains");

        addBuilding("Road", "Allows you to connect regions", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{-1})),
                "None", "1", "1", "1", "9", "3", "5", "10",
                new ArrayList<String>(Arrays.asList(new String[]{"testing"})), new ArrayList<String>(Arrays.asList(new String[]{"Fish 1"})), new ArrayList<String>(Arrays.asList(new String[]{"Wood"})), "plains");

        addBuilding("Water Gathering Spot", "Allows people to gather water", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{1})),
                "Water Collector", "0", "1", "1", "10", "3", "0", "0",
                new ArrayList<String>(Arrays.asList(new String[]{"Null"})), new ArrayList<String>(Arrays.asList(new String[]{"None"})), new ArrayList<String>(Arrays.asList(new String[]{"water"})), "None");

        addBuilding("Foraging Spot", "Allows people to forage for food", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{8})),
                "Forager", "2", "0", "1", "11", "3", "0", "0",
                new ArrayList<String>(Arrays.asList(new String[]{"Null"})), new ArrayList<String>(Arrays.asList(new String[]{"None"})), new ArrayList<String>(Arrays.asList(new String[]{"wild edibles"})), "None");

        addBuilding("Hunting Spot", "Allows people to hunt for food", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{8,1})),
                "Forager", "3", "0", "1", "12", "3", "0", "0",
                new ArrayList<String>(Arrays.asList(new String[]{"Null"})), new ArrayList<String>(Arrays.asList(new String[]{"None"})), new ArrayList<String>(Arrays.asList(new String[]{"small game"})), "None");

        addBuilding("Hovel", "Place for people to live (terrible)", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{2,9})),
                "None", "0", "0", "2", "13", "3", "0", "0",
                new ArrayList<String>(Arrays.asList(new String[]{"R_Basic Shelter Construction"})), new ArrayList<String>(Arrays.asList(new String[]{"wood 20"})), new ArrayList<String>(Arrays.asList(new String[]{"None"})), "None");

        addBuilding("Hut", "Place for people to live (bad)", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{2,9})),
                "None", "0", "0", "4", "14", "3", "0", "0",
                new ArrayList<String>(Arrays.asList(new String[]{"R_Basic Construction"})), new ArrayList<String>(Arrays.asList(new String[]{"wood 40","mud 40"})), new ArrayList<String>(Arrays.asList(new String[]{"None"})), "None");

        addBuilding("Fish Trap", "Trap for fish", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{8})),
                "None", "0", "0", "4", "15", "3", "0", "0",
                new ArrayList<String>(Arrays.asList(new String[]{"R_Basic Trapping"})), new ArrayList<String>(Arrays.asList(new String[]{"wood 25"})), new ArrayList<String>(Arrays.asList(new String[]{"fish"})), "None");

        addBuilding("Shellfish Trap", "Trap for fish", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{8})),
                "None", "0", "0", "4", "16", "3", "0", "0",
                new ArrayList<String>(Arrays.asList(new String[]{"R_Basic Trapping"})), new ArrayList<String>(Arrays.asList(new String[]{"wood 25"})), new ArrayList<String>(Arrays.asList(new String[]{"shellfish"})), "None");

        addBuilding("Grand Hall", "Place for people to eat and be entertained", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{2})),
                "None", "0", "0", "4", "17", "3", "0", "0",
                new ArrayList<String>(Arrays.asList(new String[]{"R_Basic Construction"})), new ArrayList<String>(Arrays.asList(new String[]{"wood 460","mud 80"})), new ArrayList<String>(Arrays.asList(new String[]{"None"})), "None");

        addBuilding("Herbalists Hut", "Heals people and produces science", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{4})),
                "Herbalist", "1", "0", "0", "18", "3", "0", "0",
                new ArrayList<String>(Arrays.asList(new String[]{"R_Basic Construction","R_Herbalism"})), new ArrayList<String>(Arrays.asList(new String[]{"wood 80","mud 45"})), new ArrayList<String>(Arrays.asList(new String[]{"None"})), "None");

        addBuilding("Mud Gathering Spot", "Allows people to gather mud", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{1})),
                "Water Collector", "0", "1", "1", "19", "3", "0", "0",
                new ArrayList<String>(Arrays.asList(new String[]{"Null"})), new ArrayList<String>(Arrays.asList(new String[]{"None"})), new ArrayList<String>(Arrays.asList(new String[]{"water"})), "None");

        addBuilding("Wood Gathering Spot", "Allows people to gather mud", "1", "0", new ArrayList<Integer>(Arrays.asList(new Integer[]{1})),
                "Water Collector", "0", "1", "1", "20", "3", "0", "0",
                new ArrayList<String>(Arrays.asList(new String[]{"R_Mudworking"})), new ArrayList<String>(Arrays.asList(new String[]{"None"})), new ArrayList<String>(Arrays.asList(new String[]{"wood"})), "None");



        addOccupation("Leader", "Unlocks new abilities", new ArrayList(Arrays.asList(new String[]{"None"})), new ArrayList(Arrays.asList(new Integer[]{2, 0, 0, 0, 0, 0})),
                new ArrayList(Arrays.asList(new String[]{"Strength"})), "2");

        addOccupation("Butcher", "Works at the charcuterie", new ArrayList(Arrays.asList(new String[]{"None"})), new ArrayList(Arrays.asList(new Integer[]{2, 0, 0, 0, 0, 0})),
                new ArrayList(Arrays.asList(new String[]{"Strength"})), "3");

        addOccupation("Doctor", "Works at the hospital", new ArrayList(Arrays.asList(new String[]{"None"})), new ArrayList(Arrays.asList(new Integer[]{2, 0, 0, 0, 0, 0})),
                new ArrayList(Arrays.asList(new String[]{"Strength"})), "4");

        addOccupation("Farmer", "Works on a farm", new ArrayList(Arrays.asList(new String[]{"None"})), new ArrayList(Arrays.asList(new Integer[]{2, 0, 0, 0, 0, 0})),
                new ArrayList(Arrays.asList(new String[]{"Strength"})), "5");

        addOccupation("Guard", "Keeps watch over the village", new ArrayList(Arrays.asList(new String[]{"None"})), new ArrayList(Arrays.asList(new Integer[]{2, 0, 0, 0, 0, 0})),
                new ArrayList(Arrays.asList(new String[]{"Strength"})), "6");



        addOccupation("Water Collector", "Collects Water", new ArrayList(Arrays.asList(new String[]{"None"})), new ArrayList(Arrays.asList(new Integer[]{2, 0, 0, 0, 0, 0})),
                new ArrayList(Arrays.asList(new String[]{"Strength"})), "7");

        addOccupation("Forager", "Forages for Food", new ArrayList(Arrays.asList(new String[]{"None"})), new ArrayList(Arrays.asList(new Integer[]{2, 0, 0, 0, 0, 0})),
                new ArrayList(Arrays.asList(new String[]{"Dexterity","Perception"})), "8");

        addOccupation("Herbalist", "Does things with herbs", new ArrayList(Arrays.asList(new String[]{"None"})), new ArrayList(Arrays.asList(new Integer[]{2, 0, 0, 0, 0, 0})),
                new ArrayList(Arrays.asList(new String[]{"Intelligence"})), "9");


    }




    private void addBuilding(String name, String description, String size, String workNeeded, ArrayList effectIDs, String occupation, String capacity, String limit,
                             String modifier, String buildingID, String maxUpgradeLevel, String upkeepCost, String initialCost, ArrayList preRecsNeeded, ArrayList resourceCost, ArrayList resourceRequirements, String biomeRestriction){
        /************************************************************************
         * METHOD: addBuilding                                                  *
         * USE: creates and adds a Building with given strings                  *
         * INPUT: Strings name, size, effect, occupation, buildingID            *
         ************************************************************************/
        //addBuilding("Name", "Description", "Size", "Work needed", "Effect ID", "Occupation", "Capacity", "Limit", "Building ID", "Prerequirements needed")
        //add description of what each of these does
        ArrayList out = new ArrayList();
        out.add(name); //0
        out.add(description); //1
        out.add(size); //2
        out.add(workNeeded); //3
        out.add(effectIDs); //4
        out.add(occupation); //5
        out.add(capacity); //6
        out.add(limit); //7
        out.add(modifier); //8
        out.add(maxUpgradeLevel); //9
        out.add(preRecsNeeded); //10
        out.add(upkeepCost); //11
        out.add(initialCost); //12
        out.add(resourceCost); //13
        out.add(resourceRequirements); //14
        out.add(biomeRestriction); //15
        out.add(buildingID); //16
        buildings.add(out);
    }

    private void addUpgrade(String buildingID, String description, String newModifier, String newCapacity, String newEfficiency, String newDelay, String wokrRequired,
                            ArrayList additionalEffects, ArrayList preRecsNeeded, String upgradeNumber){
        /************************************************************************
         * METHOD: addBuilding                                                  *
         * USE: creates and adds a Building with given strings                  *
         * INPUT: Strings name, size, effect, occupation, buildingID            *
         ************************************************************************/

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

    private void addOccupation(String name, String description, ArrayList requiredBuildings, ArrayList statRequirements, ArrayList relevantStats, String occupationID){
        ArrayList out = new ArrayList();
        out.add(name);
        out.add(description);
        out.add(requiredBuildings);
        out.add(statRequirements);
        out.add(relevantStats);
        //out.add("0"); //What the heck is this? I don't remember doing this. Maybe this was meant for something else? Kinda afraid to remove it now. Gonna comment it out for now and see if anything goes wrong.
        out.add(occupationID);
        occupations.add(out);
    }

    private void addAbility(String name, String description, ArrayList requiredBuildings, ArrayList requiredOccupations, ArrayList preRecsNeeded, String abilityID){
        ArrayList out = new ArrayList();
        out.add(name);
        out.add(description);
        out.add(requiredBuildings);
        out.add(requiredOccupations);
        out.add(preRecsNeeded);
        out.add(abilityID);
        abilities.add(out);
    }

    public static String bestOccupation(Person person) {
        int maxRank = 0;
        String occupationName = "None";
        for (ArrayList occupation : occupations) {
            ArrayList statRequirements = (ArrayList) (occupation.get(3));
            if (assignCheck(person, occupation)) {
                int rank = 0;
                for (Object s : (ArrayList) occupation.get(4)) {
                    String stat = (String) s;
                    if (stat.equals("Strength")) {
                        rank += person.strength - (int) statRequirements.get(0);
                    }
                    if (stat.equals("Perception")) {
                        rank += person.perception - (int) statRequirements.get(1);
                    }
                    if (stat.equals("Intelligence")) {
                        rank += person.intelligence - (int) statRequirements.get(2);
                    }
                    if (stat.equals("Martial")) {
                        rank += person.martial - (int) statRequirements.get(3);
                    }
                    if (stat.equals("Charisma")) {
                        rank += person.charisma - (int) statRequirements.get(4);
                    }
                    if (stat.equals("Dexterity")) {
                        rank += person.dexterity - (int) statRequirements.get(5);
                    }
                }
                if(rank > maxRank){
                    maxRank = rank;
                    occupationName = (String)occupation.get(0);
                }
            }
        }
        return occupationName;
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

    public void assignOccupationAutoNoReassign(ArrayList occupation){
        //Same as assignOccupationAuto but doesn't reassign workers if they already have a job.
        for (Person p: People){
            if(assignCheck(p, occupation) && this.capacity > 0 && p.occupation.equals("None")){
                p.occupation = (String)occupation.get(0);
                this.capacity -= 1;
                this.workers += 1;
                this.workersList.add(p);
            }
        }
    }

    public void assignOccupationAutoBestNoReassign(ArrayList occupation){
        //Same as assignOccupationAutoBest but doesn't reassign workers if they already have a job.
        ArrayList<ArrayList> bestWorkers = occupationOptimizerBest(occupation);
        for(ArrayList workerList: bestWorkers){
            Person worker = (Person)workersList.get(0);
            int rank = (int)workerList.get(1);
            if(this.capacity > 0 && worker.occupation.equals("None")){
                worker.occupation = (String)occupation.get(0);
                this.capacity -= 1;
                this.workers += 1;
                this.productivity += rank;
                this.workersList.add(worker);
            }
        }
    }

    public void assignOccupationAutoBest(ArrayList occupation){
        ArrayList<ArrayList> bestWorkers = occupationOptimizerBest(occupation);
        for(ArrayList workerList: bestWorkers){
            Person worker = (Person)workerList.get(0);
            int rank = (int)workerList.get(1);
            if(this.capacity > 0){
                worker.occupation = (String)occupation.get(0);
                this.capacity -= 1;
                this.workers += 1;
                this.productivity += rank;
                this.workersList.add(worker);
            }
        }
    }

    public ArrayList occupationOptimizerBest(ArrayList occupation){
        ArrayList statRequirements = (ArrayList)(occupation.get(3));
        ArrayList<ArrayList> bestWorkers = new ArrayList<ArrayList>();
        for(Person p: People){
            if(assignCheck(p, occupation)){
                int rank = 0;
                ArrayList out = new ArrayList();
                for(Object s: (ArrayList)occupation.get(4)) {
                    String stat = (String) s;
                    if (stat.equals("Strength")) {
                        rank +=  p.strength - (int) statRequirements.get(0);
                    }
                    if (stat.equals("Perception")) {
                        rank +=  p.perception - (int) statRequirements.get(1);
                    }
                    if (stat.equals("Intelligence")) {
                        rank += p.intelligence- (int) statRequirements.get(2);
                    }
                    if (stat.equals("Martial")) {
                        rank += p.martial- (int)statRequirements.get(3);
                    }
                    if (stat.equals("Charisma")) {
                        rank +=  p.charisma - (int) statRequirements.get(4);
                    }
                    if (stat.equals("Dexterity")) {
                        rank +=  p.dexterity - (int) statRequirements.get(5);
                    }
                }
                out.add(p);
                out.add(rank);
                bestWorkers.add(out);
            }
        }
        ArrayList<ArrayList> tempList = new ArrayList<ArrayList>();
        tempList.add(new ArrayList(Arrays.asList(new String[]{"None"})));
        tempList.get(0).add(0);
        for(ArrayList x: bestWorkers){
            for(int i = 0; i < tempList.size(); i++){
                if((int)x.get(1) >= (int)tempList.get(i).get(1)){
                    tempList.add(i, x);
                    break;
                }
            }
        }
        int removalIndex = -1;
        for(ArrayList t: tempList){
            if(t.get(0).equals("None") && (int)t.get(1) == 0){
                removalIndex = tempList.indexOf(t);
            }
        }
        if(removalIndex != -1) {
            tempList.remove(removalIndex);
        }
        bestWorkers = tempList;
        return bestWorkers;
    }

    public void assignOccupationManual(Person person, ArrayList occupation){
        if(assignCheck(person, occupation)){
            person.occupation = (String)occupation.get(0);
            this.capacity -= 1;
            this.workers += 1;
            this.workersList.add(person);
        }
    }

    public static boolean assignCheck(Person person, ArrayList occupation){
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
                //constructedBuildings.add(new Building(buildingChoice));
            }
            break;
        }
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
                //if(gold >= b.upkeepCost) {
                if (!b.init)
                {
                    if (b.name.equals("Hovel")) { housingAvailable+=2; }
                    if (b.name.equals("Hut")) { housingAvailable+=4; }
                    if (b.name.equals("Herbalists Hut")) {scienceWork+=2;}
                    b.init=true;
                }
                b.callEffect();

                //gold -= b.upkeepCost;
                //}
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
                    produceWater();
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
                if (e == 7) {
                    produceRes(this.name);
                }
                if (e == 8) {
                    produceFood(this.name);
                }
                if (e == 9) {
                    //addhouse
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
        reverseEffects();
        constructedBuildings.remove(this);
    }

    public static boolean buildCheck(int buildingID) {
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

    public static boolean regionCheck(int buildingID, Region region){
        String biome = "";
        String resource = "";
        ArrayList resourceRequirements = new ArrayList();
        for (ArrayList b: buildings) {
            if (Integer.parseInt((String) b.get(b.size() - 1)) == buildingID){
                biome = (String)b.get(15);
                resourceRequirements = (ArrayList)b.get(14);
                if(biome.equals("None") || biome.equals(region.biome)){
                    for(Object r: resourceRequirements){
                        resource = (String)r;
                        if(resource.equals("None")){
                            return true;
                        }
                        //ASK IAN IF THIS IS RIGHT
                        else if(!region.resources.contains(resource)){
                            return false;
                        }
                    }
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean materialsCheck(int buildingID){
        String resourceName;
        double resourceAmount;
        ArrayList resourceCost = new ArrayList();
        for (ArrayList b: buildings) {
            if (Integer.parseInt((String) b.get(b.size() - 1)) == buildingID) {
                resourceCost = (ArrayList)b.get(13);
                for(Object r: resourceCost){
                    String resource = (String)r;
                    resourceName = resource.replaceAll("[^A-Za-z]+", "");
                    if(resourceName.equals("None")){
                        return true;
                    }
                    resourceAmount = Double.parseDouble(resource.replaceAll("[^\\d]", ""));
                    System.out.println(resourceAmount);
                    for(ArrayList o: ownedResources){
                        if(((String)o.get(0)).equals(resourceName)){
                            if(resourceAmount > (double)o.get(1)){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void deductMaterials(int buildingID){
        String resourceName;
        double resourceAmount;
        ArrayList resourceCost = new ArrayList();
        for (ArrayList b: buildings) {
            if (Integer.parseInt((String) b.get(b.size() - 1)) == buildingID) {
                resourceCost = (ArrayList)b.get(13);
                for(Object r: resourceCost){
                    String resource = (String)r;
                    resourceName = resource.replaceAll("[^A-Za-z]+", "");
                    if(!resourceName.equals("None")){
                        resourceAmount = Double.parseDouble(resource.replaceAll("[^\\d]", ""));
                        for(ArrayList o: ownedResources){
                            if(((String)o.get(0)).equals(resourceName)) {
                                o.set(1, (double)o.get(1)-resourceAmount);
                            }
                        }
                    }
                }
            }
        }
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
        this.region.hasRoad = true;
        //todo: Should run connectMap
        //todo: changes effect ID afterwards so effect only runs once
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
        housingAvailable += modifier;
    }

    private void foodPreservation () {
        foodPreservation = modifier;
    }

    private void addScience () {
        scienceWork += modifier + efficiency*workers;
    }

    private void addWork () {
        productionWork += modifier + efficiency*workers;
    }

    private void addHappiness () {
        if (this.name.equals("Hovel"))
        {
            hapPeople(2,-10,2,"Lives in Hovel");
        }
        if (this.name.equals("Hut"))
        {
            hapPeople(4,-5,2,"Lives in Hut");
        }
        if (this.name.equals("Grand Hall"))
        {
            hapPeople(10,4,3,"Wined and Dined at Grand Hall");
        }
    }

    private void hapPeople(int amonPep, double hap, int days,String reason)
    {
        Random rand = new Random();
        int counter = 0;
        while (true)
        {
            Person p = People.get(rand.nextInt(People.size()));
            if (!p.lifestage.equals("Deceased"))
            {
                double decay=0;
                if (hap>0)
                {
                    decay=hap/days;
                }
                else
                {
                    decay=0-hap/days;
                }
                p.addEffect(reason, "Happy", ""+hap,""+decay,"100","0","0","0","0","false");
                counter++;
            }
            if (counter>=amonPep){break;}
        }
    }

    private void produceRes(String x)
    {
        String res="super fish";
        if (x.equals("clay gathering spot")){res="clay";}
        if (x.equals("Mud Gathering Spot")){res="mud";}
        if (x.equals("Wood Gathering Spot")){res="wood";}
        if (x.equals("Hunting Spot")){res="animal products";}
        Boolean temp = false;
        for (ArrayList resource : ownedResources) {
            if (resource.get(0).equals(res)) {
                resource.set(1, (double) resource.get(1) + modifier + efficiency * workers);
                temp=true;
            }
        }
        if (!temp)
        {
            ownedResources.add(new ArrayList(Arrays.asList(new String[]{"Fish"})));
            for (ArrayList resource : ownedResources) {
                if (resource.get(0).equals(res)) {
                    resource.add(1, modifier + efficiency * workers);
                }
            }
        }
    }

    private void produceFood(String x)
    {
        String res="super fish";
        int days = 666;
        if (x.equals("Foraging Spot"))
        {
            res="wild food";
            days=7;
        }
        if (x.equals("Hunting Spot"))
        {
            res="raw meat";
            days=3;
        }
        if (x.equals("Fish Trap"))
        {
            res="raw fish";
            days=3;
        }
        if (x.equals("Shellfish Trap"))
        {
            res="raw shellfish";
            days=3;
        }
        Boolean temp = false;
        addFood(res,modifier + efficiency * workers,days);
    }

    private void produceWater(){
        water += modifier + efficiency*workers;
    }

    private void addInfluence(){
        influence += modifier + workers*efficiency;
    }


    private void abilityCureSick(){
        //todo
        //Ability that allows you to spend influence to cure one sick person of any disease. Will require some sort of temple or holy building or something
        //Probably has to take in a person as parameter
    }

    private void abilityCureAllSick(){
        //todo
        //Stronger version of cure sick that allows you to cure everyone in your village for a large amount of influence. Requires and upgrade probably.
    }

    private void abilityAddHappiness(){
        //todo
    }

    private void produceGold () {
        this.gold += modifier + efficiency*workers;
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