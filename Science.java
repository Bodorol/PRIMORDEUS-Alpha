import java.util.*;

public class Science extends Global
{
    // Attributes
    Random rand = new Random();
    public int focus=-1;

    Scanner reader = new Scanner(System.in);

    public Science()
    {
        //addScience("Name","Work Needed",prerecs)
        //addScience("Bronzeworking",200.0,"R_Fire/R_AdvanceFire/D_Tin/D_Copper")
        preRecsAttained.add("");
        //addScience("Toolmaking",-1.0,"");
        addScience("Basic Shipbuilding",250.0,"");
        addScience("Fiber Preparation",40.0,"");
        addScience("Slings",50.0,"R_Fiber Preparation");
        addScience("Bone Working",90.0,"");
        addScience("Herbalism",90.0,"D_Herb");
        addScience("Beadmaking",90.0,"R_Bone Working");
        addScience("Basketry",150.0,"R_Fiber Preparation");
        addScience("Basic Trapping",110.0,"R_Basketry");
        addScience("Basic Shelter Construction",150.0,"R_Fiber Preparation");
        addScience("Basic Construction",150.0,"R_Mudworking");
        addScience("Basic Smoking",50.0,"");
        addScience("Basic Agriculture",350.0,"D_Veg-Seed");
        addScience("Basic Domestication",300.0,"D_DomestAni");
        addScience("Mudworking",80.0,"");
        addScience("Advanced Firemaking",80.0,"R_Mudworking");
        addScience("Basic Clayworking",100.0,"D_Clay/R_Advanced Firemaking");
        addScience("Vessels",80.0,"R_Basic Clayworking");
        addScience("Early Smelting",380.0,"R_Basic Clayworking");
        addScience("Construction",260.0,"R_Early Smelting/D_Copper");
        addScience("Basic Gears",200.0,"R_Early Smelting/D_Copper");
        addScience("Early Writing",420.0,"R_Basic Clayworking");
        addScience("Early Mathematics",460.0,"R_Early Writing");
        addScience("Stone Working",80.0,"D_Chisel/D_Stone");

        this.sciencesAttained.add("Firemaking");
        this.preRecsAttained.add("R_"+"Firemaking");
        this.sciencesAttained.add("Toolmaking");
        this.preRecsAttained.add("R_"+"Toolmaking");
    }

     /************************************************************************
     * METHOD: addScience                                                    *
     * USE: Adds a science to possible sciences in the game                  *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public void addScience(String name, Double work, String pre)
    {
        ArrayList out = new ArrayList();
        this.sciencesNames.add(name);
        this.sciencesWork.add(work);
        this.sciencesAqe.add(false);

        String[] items = pre.split("/");
        List<String> itemList = new ArrayList<String>(Arrays.asList(items));
        for (String i:itemList)
        {
            out.add(i);
        }
        this.sciencesPreRecs.add(out);
    }

     /************************************************************************
     * METHOD: updateScience                                                 *
     * USE: checks if the science has been researched yet and adds it to     *
     * acquired sciences.                                                    *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public String updateScience()
    {
        int size = sciencesNames.size();
        for (int index = 0;index<size;index++)
        {
            if ((sciencesWork.get(index)<=0)&(!sciencesAttained.contains(sciencesNames.get(index))))
            {
                sciencesAttained.add(sciencesNames.get(index));
                preRecsAttained.add("R_"+sciencesNames.get(index));
                focusScience=-1;
                return "Science attained "+sciencesNames.get(index)+"\n";
            }
        }
        return "";
    }

     /************************************************************************
     * METHOD: work                                                          *
     * USE: this is used to add work to the science and returns a lesser     *
     * event if completed                                                    *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public String work(double work)
    {
        if (focusScience!=-1)
        {
            double workLeft = sciencesWork.get(focusScience);
            sciencesWork.remove(focusScience);
            workLeft=workLeft-work;
            sciencesWork.add(focusScience,workLeft);
            return updateScience();
        }
        else{return"";}
    }
}