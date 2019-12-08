import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.util.ArrayList;
import java.util.Scanner;

public class Global {
    static double food=10000;
    static double water=1000;
    static double waterMade=0;
    static double waterProduction=0;
    static double foodMade=0;
    static double foodProduction=0;
    static double foodPreservation=0;
    static double unrest=0;
    static double popHappiness=0;
    static int housingAvailable;
    static double influence=100;
    static double influenceMade=0.01;
    static int day=1;
    static int year=1;
    static String date="";
    static double gold=0;
    static double goldMade=0;
    static double doctorLevel=1;
    static double healthcareLevel=1;
    static String season = "Spring";
    static int firewood=0;
    static int stone=0;
    static Boolean overridesActive;
    static Boolean turnTimer=false;
    static Boolean currentEvent = false;
    static double productionWork=1.0;
    static double scienceWork=1.0;
    static int focusRegion;
    static int focusScience=-1;
    static int focusPerson=-1;
    static String buttonClicked="";
    static public ArrayList<String> preRecsAttained=new ArrayList<String>();
    static public ArrayList<String> sciencesAttained=new ArrayList<String>();
    static public ArrayList<String> discResources = new ArrayList<>();
    static public ArrayList<ArrayList> ownedResources = new ArrayList<ArrayList>();
    static public ArrayList<Region> regions = new ArrayList<Region>();
    static public ArrayList<Policy> policies = new ArrayList<Policy>();
    static public ArrayList<Person> People=new ArrayList<Person>();
    static public ArrayList<String> diseaseNames=new ArrayList<String>();
    static public ArrayList<ArrayList> diseaseStats=new ArrayList<ArrayList>();
    static public ArrayList<ArrayList> foodRes=new ArrayList<ArrayList>();
    static public ArrayList<String> sciencesNames=new ArrayList<String>();
    static public ArrayList<Boolean> sciencesAqe=new ArrayList<Boolean>();
    static public ArrayList<Double> sciencesWork=new ArrayList<Double>();
    static public ArrayList<ArrayList> sciencesPreRecs=new ArrayList<ArrayList>();
    static public ArrayList<ArrayList> buildings=new ArrayList<ArrayList>();
    static public ArrayList<ArrayList> upgrades=new ArrayList<ArrayList>();
    static public ArrayList<Building> constructedBuildings = new ArrayList<Building>(); //todo
    static public ArrayList<ArrayList> occupations = new ArrayList<ArrayList>(); //todo
    static public ArrayList<String> availableOccupations = new ArrayList<String>();
    static public ArrayList<ArrayList> abilities = new ArrayList<ArrayList>(); //todo
    static public ArrayList<ArrayList> events = new ArrayList<ArrayList>(); //todo
    static public Clip clip1;
    static public Clip clip;
    static public Map mp = new Map();

    //food res (name of food, calories in thousands, lifespan in days)

    public Global()
    {
    }

    public int popSize()
    {
        int out = 0;
        for (Person x : this.People) {
            if (x.lifestage!="Deceased") { out++; }
        }
        return out;
    }

    public int totalPop()
    {
        return People.size();
    }

    public void calcFood()
    {
        int lengthFood=this.foodRes.size();
        food=0;
        while (true)
        {
            int changes = 0;
            int remove = -1;
            for (ArrayList fd:this.foodRes)
            {
                if (Integer.parseInt(""+fd.get(2))<=0)
                {
                    remove=this.foodRes.indexOf(fd);
                    changes++;
                    break;
                }
            }
            if (remove!=-1){this.foodRes.remove(remove);}
            if (changes==0){break;}
        }
        for (ArrayList f:this.foodRes)
        {
            int lifespan=Integer.parseInt(""+f.get(2));
            lifespan+=-1;
            f.remove(2);
            f.add(lifespan);
            food+=Double.parseDouble(""+f.get(1));
        }
    }

    /************************************************************************
     * METHOD: input                                                         *
     * USE: Takes user input and returns it (makes input easier)             *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public String input()
    {
        Scanner reader2 = new Scanner(System.in);
        System.out.print("=>");
        String out = reader2.nextLine();
        playSoundEasy("click.wav");
        return out;
    }

    public static double feedFood(double hunger)
    {
        while (foodRes.size()>0)
        {
            int foodToUse=-1;
            int temp=9999999;
            for (int x=0; x<foodRes.size();x++)
            {
                if (Integer.parseInt(""+foodRes.get(x).get(2))<temp)
                {
                    temp=Integer.parseInt(""+foodRes.get(x).get(2));
                    foodToUse=x;
                }
            }
            if (Double.parseDouble(""+foodRes.get(foodToUse).get(1))<hunger)
            {
                hunger+=-Double.parseDouble(""+foodRes.get(foodToUse).get(1));
                foodRes.remove(foodToUse);
            }
            else
            {
                double fr = Double.parseDouble(""+foodRes.get(foodToUse).get(1));
                fr=fr-hunger;
                foodRes.get(foodToUse).remove(1);
                foodRes.get(foodToUse).add(1,fr);
                hunger=0;
                return hunger;
            }
        }
        return hunger;
    }

    public void addFood(String name,double cal, int days)
    {
        ArrayList hm = new ArrayList();
        //days += this.foodPreservation;
        hm.add(name);
        hm.add(cal);
        hm.add(days);
        this.foodRes.add(hm);
    }

    public void playSound(String sound,int play) {
        if (sound.equals("mainTest"))
        {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("MainMenuMusic.wav"));
                clip1 = AudioSystem.getClip();
                clip1.open(audioInputStream);
                if (play==0)
                {
                    clip1.start();
                }
                if (play==1)
                {
                    clip1.start();
                    clip1.loop(99999);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void playSoundEasy(String sound)
    {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(sound));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void stopSounds()
    {
        clip1.stop();
    }

    /************************************************************************
     * METHOD: find                                                          *
     * USE: returns the index of a region with a given row and position      *
     * INPUT: int row, int pos                                               *
     ************************************************************************/

    public int find(int row, int pos)
    {
        for (Region reg:regions)
        {
            if ((reg.row==row)&(reg.pos==pos))
            {
                return reg.index;
            }
        }
        return -1;
    }

    /************************************************************************
     * METHOD: discoverable                                                  *
     * USE: returns a true or false value if a surrounding tile has been disc*
     * INPUT: N/A                                                            *
     ************************************************************************/

    public boolean discoverable(Region reg)
    {
        if (reg._1!=-1){if (regions.get(reg._1).discovered){return true;}}
        if (reg._2!=-1){if (regions.get(reg._2).discovered){return true;}}
        if (reg._3!=-1){if (regions.get(reg._3).discovered){return true;}}
        if (reg._4!=-1){if (regions.get(reg._4).discovered){return true;}}
        if (reg._5!=-1){if (regions.get(reg._5).discovered){return true;}}
        if (reg._6!=-1){if (regions.get(reg._6).discovered){return true;}}
        if (reg._7!=-1){if (regions.get(reg._7).discovered){return true;}}
        if (reg._8!=-1){if (regions.get(reg._8).discovered){return true;}}
        return false;
    }

    public double getRes(String res)
    {
        for (ArrayList ar:ownedResources)
        {
            if (ar.get(0).equals(res))
            {
                try
                {
                    return Double.parseDouble(""+ar.get(1));}
                catch (Exception e){}
            }
        }
        return 0.0;
    }

    public void setRes(String res, double am)
    {
        for (ArrayList ar:ownedResources)
        {
            if (ar.get(0).equals(res))
            {
                try
                {
                    ar.set(1,am);}
                catch (Exception e){}
            }
        }
    }
}