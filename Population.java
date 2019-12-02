import java.util.ArrayList;
import java.util.Random;

public class Population extends Global
{
    // Attributes
    Random rand = new Random();


    public double health=40+rand.nextInt(20);
    public double hunger=0.0;

    public Population()
    {
        //addDisease("Name", "Health","HP/Turn","Worsening","Immunity","Infectivity","Difficulty 1-100", "Improvement Rate","Does it spread?", "false (just leave it)");
        diseaseNames.add("Flu");
        addDisease("Flu", "Health","-0.10","-.01","80","15","10", ".1","true", "false");
        diseaseNames.add("Smallpox");
        addDisease("Smallpox", "Health","-0.15","-.02","60","20","15", ".1","true", "false");
        diseaseNames.add("Dysentery");
        addDisease("Dysentery", "Health","-0.05","-.01","85","15","10", ".1","true", "false");
        diseaseNames.add("Bubonic Plague");
        addDisease("Bubonic Plague", "Health","-0.20","-.01","75","25","25", ".1","true", "false");
        diseaseNames.add("Yellow Fever");
        addDisease("Yellow Fever", "Health","-0.15","-.01","80","10","10", ".1","true", "false");
        diseaseNames.add("Chicken Pox");
        addDisease("Chicken Pox", "Health","-0.05","-.01","80","15","5", ".5","true", "false");
        diseaseNames.add("Typhus");
        addDisease("Typhus", "Health","-0.15","-.01","80","15","10", ".1","true", "false");
        diseaseNames.add("Typhoid");
        addDisease("Typhoid", "Health","-0.10","-.01","80","15","10", ".1","true", "false");
        diseaseNames.add("Malaria");
        addDisease("Malaria", "Health","-0.05","-.01","75","10","15", ".1","true", "false");
        diseaseNames.add("Measles");
        addDisease("Measles", "Health","-0.05","-.01","80","15","10", ".5","true", "false");
        diseaseNames.add("Tuberculosis");
        addDisease("Tuberculosis", "Health","-0.30","-.05","60","05","10", ".5","true", "false");
        diseaseNames.add("Super Smallpox");
        addDisease("Super Smallpox", "Health","-1.85","-.02","20","40","15", ".1","true", "false");
    }

     /************************************************************************
     * METHOD: addDisease                                                    *
     * USE: creates and adds a Disease with given strings                    *
     * INPUT: Strings a - j                                                  *
     ************************************************************************/

    public void addDisease(String a,String b,String c,String d,String e,String f,String g,String h,String i,String j)
    {
        ArrayList out = new ArrayList();
        out.add(a);
        out.add(b);
        out.add(c);
        out.add(d);
        out.add(e);
        out.add(f);
        out.add(g);
        out.add(h);
        out.add(i);
        out.add(j);
        this.diseaseStats.add(out);
    }

     /************************************************************************
     * METHOD: addPerson                                                     *
     * USE: adds a given person to the population                            *
     * INPUT: Person p                                                       *
     ************************************************************************/

    public void addPerson(Person p)
    {
        this.People.add(p);
    }

     /************************************************************************
     * METHOD: getPopHap                                                     *
     * USE: returns average population hap                                   *
     * INPUT: Person p                                                       *
     ************************************************************************/

    public double getPopHap()
    {
        double pophap=0;
        for (Person pep:People)
        {
            if (!pep.lifestage.equals("Deceased")){
            pophap+=pep.getHap();}
        }
        return pophap/popSize();
    }

     /************************************************************************
     * METHOD: hungerThePeople                                               *
     * USE: increases hunger of all people and returns what people died from *
     * starvation.                                                           *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public ArrayList<String> hungerThePeople()
    {
        ArrayList<String> out = new ArrayList<String>();
        for(Person p:People)
        {
            p.hungerAmount+=2;
            if ((p.hungerAmount>=40)&(!p.lifestage.equals("Deceased")))
            {
                p.health=-1000;
                p.deathReason="starvation";
                out.add(p.update());
            }
        }
        return out;
    }

     /************************************************************************
     * METHOD: thirstThePeople                                               *
     * USE: increases thirst of all people and returns what people died from *
     * dehydration.                                                          *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public ArrayList<String> thirstThePeople()
    {
        ArrayList<String> out = new ArrayList<String>();
        for(Person p:People)
        {
            p.thirstAmount+=.5;
            if ((p.thirstAmount>=2.5)&(!p.lifestage.equals("Deceased")))
            {
                p.health=-1000;
                p.deathReason="dehydration";
                out.add(p.update());
            }
        }
        return out;
    }

     /************************************************************************
     * METHOD: diseaseThePeople                                              *
     * USE: Spreads the diseases in circulation in the population.           *
     * INPUT: health value, decrease infectivity of diseases                 *
     ************************************************************************/

    public void diseaseThePeople(double health)
    {
        for (Person p:People)
        {
            int ind=0;
            for (ArrayList x: p.effects)
            {
                if (this.diseaseNames.contains(x.get(0)))
                {
                    int diseaseIND=diseaseNames.indexOf(x.get(0));
                    if ((Boolean.parseBoolean(""+x.get(9)))&(rand.nextInt(100)/health)>45)
                    {
                        int inf = rand.nextInt(100);
                        if (inf<50)
                        {
                            if ((rand.nextInt(2)==1)&(p.spouse!="None")) //Spread to spouse
                            {
                                if ((!(People.get(p.spouseIndex).immune.contains(diseaseNames.get(diseaseIND))))&People.get(p.spouseIndex).lifestage!="Deceased")
                                {
                                    People.get(p.spouseIndex).addEffect(""+diseaseStats.get(diseaseIND).get(0),
                                            ""+diseaseStats.get(diseaseIND).get(1),""+diseaseStats.get(diseaseIND).get(2),
                                            ""+diseaseStats.get(diseaseIND).get(3),""+diseaseStats.get(diseaseIND).get(4),
                                            ""+diseaseStats.get(diseaseIND).get(5),""+diseaseStats.get(diseaseIND).get(6),
                                            ""+diseaseStats.get(diseaseIND).get(7),""+diseaseStats.get(diseaseIND).get(8),
                                            ""+diseaseStats.get(diseaseIND).get(9));
                                    x.remove(9);
                                    x.add("false");
                                }
                            }
                        }
                        else if (inf<80) //Spread to child
                        {
                            if (p.children.size()>0)
                            {
                                int kid = Integer.parseInt(p.children.get(rand.nextInt(p.children.size())).get(2));
                                if ((!(People.get(kid).immune.contains(diseaseNames.get(diseaseIND))))&People.get(kid).lifestage!="Deceased")
                                {
                                    People.get(kid).addEffect(""+diseaseStats.get(diseaseIND).get(0),
                                            ""+diseaseStats.get(diseaseIND).get(1),""+diseaseStats.get(diseaseIND).get(2),
                                            ""+diseaseStats.get(diseaseIND).get(3),""+diseaseStats.get(diseaseIND).get(4),
                                            ""+diseaseStats.get(diseaseIND).get(5),""+diseaseStats.get(diseaseIND).get(6),
                                            ""+diseaseStats.get(diseaseIND).get(7),""+diseaseStats.get(diseaseIND).get(8),
                                            ""+diseaseStats.get(diseaseIND).get(9));
                                    x.remove(9);
                                    x.add("false");
                                }
                            }
                        }
                        else //Spread to random
                        {
                            int random = rand.nextInt(People.size());
                            if ((!(People.get(random).immune.contains(diseaseNames.get(diseaseIND))))&People.get(random).lifestage!="Deceased")
                            {
                                People.get(random).addEffect(""+diseaseStats.get(diseaseIND).get(0),
                                        ""+diseaseStats.get(diseaseIND).get(1),""+diseaseStats.get(diseaseIND).get(2),
                                        ""+diseaseStats.get(diseaseIND).get(3),""+diseaseStats.get(diseaseIND).get(4),
                                        ""+diseaseStats.get(diseaseIND).get(5),""+diseaseStats.get(diseaseIND).get(6),
                                        ""+diseaseStats.get(diseaseIND).get(7),""+diseaseStats.get(diseaseIND).get(8),
                                        ""+diseaseStats.get(diseaseIND).get(9));
                                x.remove(9);
                                x.add("false");
                            }
                        }
                    }
                }
                ind++;
            }
        }
    }

     /************************************************************************
     * METHOD: feedThePeople                                                 *
     * USE: feeds the population in a certain way using global food var      *
     * INPUT: String feedtype                                                *
     ************************************************************************/

    public void feedThePeople(String feedtype)
    {
        if (policies.get(5).active)
        {
            for(Person p:People)
            {
                if ((p.clss=="High")&(!p.lifestage.equals("Deceased")))
                {
                    p.hungerAmount=feedFood(p.hungerAmount);
                }
            }
            for(Person p:People)
            {
                if ((p.clss=="Middle")&(!p.lifestage.equals("Deceased")))
                {
                    p.hungerAmount=feedFood(p.hungerAmount);
                }
            }
            for(Person p:People)
            {
                if ((p.clss=="Low")&(!p.lifestage.equals("Deceased")))
                {
                    p.hungerAmount=feedFood(p.hungerAmount);
                }
            }
        }
        else if (policies.get(6).active)
        {
            if (food>popSize()*2)
            {
                for (Person pep:People)
                {
                    if (!pep.lifestage.equals("Deceased")){
                        pep.hungerAmount=feedFood(pep.hungerAmount);
                    }
                }
            }
            else
                {
                    double foodRation = food/popSize();
                    for (Person p:People)
                    {
                        if (!p.lifestage.equals("Deceased")){
                        double fr = foodRation;
                        if (p.hungerAmount<fr)
                        {
                            p.hungerAmount=feedFood(p.hungerAmount);
                        }
                        else
                        {
                            p.hungerAmount=p.hungerAmount-(fr+feedFood(fr));
                        }
                        food+=fr;}
                    }
                }
        }
        else
        {
            for (Person p:People)
            {
                if ((!p.lifestage.equals("Deceased")))
                {
                    p.hungerAmount=feedFood(p.hungerAmount);
                }
            }
        }
    }

     /************************************************************************
     * METHOD: hydrateThePeople                                              *
     * USE: hydrates the population in a certain way using global water var  *
     * INPUT: String drinktype                                               *
     ************************************************************************/

    public void hydrateThePeople(String drinktype)
    {
        if (policies.get(5).active)
        {
            for(Person p:People)
            {
                if ((p.clss=="High")&(!p.lifestage.equals("Deceased")))
                {
                    if (p.thirstAmount<water)
                    {
                        water=water-p.thirstAmount;
                        p.thirstAmount=0;
                    }
                    else
                    {
                        p.thirstAmount=p.thirstAmount-water;
                        water=0;
                    }
                }
            }
            for(Person p:People)
            {
                if ((p.clss=="Middle")&(!p.lifestage.equals("Deceased")))
                {
                    if (p.thirstAmount<water)
                    {
                        water=water-p.thirstAmount;
                        p.thirstAmount=0;
                    }
                    else
                    {
                        p.thirstAmount=p.thirstAmount-water;
                        water=0;
                    }
                }
            }
            for(Person p:People)
            {
                if ((p.clss=="Low")&(!p.lifestage.equals("Deceased")))
                {
                    if (p.thirstAmount<water)
                    {
                        water=water-p.thirstAmount;
                        p.thirstAmount=0;
                    }
                    else
                    {
                        p.thirstAmount=p.thirstAmount-water;
                        water=0;
                    }
                }
            }
        }
        else if (policies.get(6).active)
        {
            if (water>popSize()*.5)
            {
                for (Person pep:People)
                {
                    if (!pep.lifestage.equals("Deceased")){
                    water=water-pep.thirstAmount;
                    pep.thirstAmount=0;}
                }
            }
            else
            {
                double waterRation = water/popSize();
                water=0;
                for (Person p:People)
                {
                    if (!p.lifestage.equals("Deceased")){
                    double wr = waterRation;
                    if (p.thirstAmount<wr)
                    {
                        wr=wr-p.thirstAmount;
                        p.thirstAmount=0;
                    }
                    else
                    {
                        p.thirstAmount=p.thirstAmount-wr;
                        wr=0;
                    }
                    water+=wr;}
                }
            }
        }
        else
        {
            for (Person p:People)
            {
                if ((!p.lifestage.equals("Deceased")))
                {
                    if (p.thirstAmount<water)
                    {
                        water=water-p.thirstAmount;
                        p.thirstAmount=0;
                    }
                    else
                    {
                        p.thirstAmount=p.thirstAmount-water;
                        water=0;
                    }
                }
            }
        }
    }

     /************************************************************************
     * METHOD: getPerson                                                     *
     * USE: returns a person with a given index                              *
     * INPUT: int i (index)                                                  *
     ************************************************************************/

    public Person getPerson(int i)
    {
        return this.People.get(i);
    }

     /************************************************************************
     * METHOD: agePeople                                                     *
     * USE: Ages all people and detects any sort of deaths and returns them  *
     * INPUT: String season int day double healthcareLevel                   *
     ************************************************************************/

    public ArrayList<String> agePeople(String season,int day,double healthcareLevel)
    {
        ArrayList<String> out = new ArrayList<String>();
        for (Person p: this.People)
        {
            String ageDeath = p.age(season,day,healthcareLevel);
            if (ageDeath!=""){ out.add(ageDeath);}
        }
        return out;
    }

    /************************************************************************
     * METHOD: popSize                                                       *
     * USE: returns size of living population                                *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public int popSize()
    {
        int out = 0;
        for (Person x : People) {
            if (x.lifestage!="Deceased") { out++; }
        }
        return out;
    }

     /************************************************************************
     * METHOD: pollinate                                                     *
     * USE: pollinates certain people in the population                      *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public void pollinate()
    {
        Random rand = new Random();
        for (Person p: this.People) {
            if ((p.gender == "Female") & (p.spouse != "None") & (p.isPregnant == false) & (p.age>=18) &
                    (p.children.size()<7)&(p.lifestage!="Deceased")&(this.People.get(p.spouseIndex).lifestage!="Deceased"))
            {
                if (p.age<30) { if (rand.nextInt(100)<4){p.isPregnant=true;} }
                else if (p.age<40) { if (rand.nextInt(100)<2){p.isPregnant=true;} }
                else { if (rand.nextInt(100)<1){p.isPregnant=true;} }
            }
        }
    }

     /************************************************************************
     * METHOD: birth                                                         *
     * USE: makes a new person when a birth is needed                        *
     * INPUT: String season int day double health                            *
     ************************************************************************/

    public ArrayList<String> birth(String season, int day, double health)
    {
        ArrayList<String> outAr = new ArrayList<String>();
        Random rand = new Random();
        int popsize=this.People.size();
        for (int i=0; i<popsize;i++) {
            if ((this.People.get(i).isPregnant==true)&(this.People.get(i).lifestage!="Deceased")) {this.People.get(i).pregnantDays++;}
            if (this.People.get(i).pregnantDays==280)
            {
                int temp = rand.nextInt(100);
                int numchil=1;
                if (temp>=95){numchil=3;}
                else if (temp>=90){numchil=2;}
                this.People.get(i).pregnantDays=0;
                this.People.get(i).isPregnant=false;
                for (int ic=0;ic<numchil;ic++)
                {
                    ArrayList traits1 = this.People.get(i).traitsSleeping;
                    ArrayList traits2 = this.People.get(this.People.get(i).spouseIndex).traitsSleeping;

                    Person baby = new Person((popsize),season, day, this.People.get(this.People.get(i).spouseIndex),this.People.get(i),
                            this.People.get(i).spouseIndex,i,traits1,traits2);

                    String birthDetails=baby.birth(health);
                    double temp2 = rand.nextInt(100)*health;
                    if ((this.People.get(i).age < 35)&(this.People.get(i).deathReason!="died during childbirth"))
                    {
                        if (temp2 < 10) {
                            this.People.get(i).deathReason = "died during childbirth";
                            this.People.get(i).health = 0;
                            this.People.get(i).update();
                            outAr.add(""+this.People.get(i).name+", "+this.People.get(i).lastName+" died during childbirth at " +
                                    "age "+this.People.get(i).age);
                            break;
                        }
                    }
                    else if((this.People.get(i).deathReason!="died during childbirth")) {
                        if (temp2 < this.People.get(i).age-20) {
                            this.People.get(i).deathReason = "died during childbirth";
                            this.People.get(i).health = 0;
                            this.People.get(i).update();
                            outAr.add(""+this.People.get(i).name+", "+this.People.get(i).lastName+" died during childbirth at " +
                                    "age "+this.People.get(i).age);
                            break;
                        }
                    }
                    this.People.add(baby);
                    popsize++;
                    ArrayList ch =new ArrayList<>();
                    ch.add("hasChild");
                    ch.add(baby.name+" "+baby.lastName);
                    ch.add(""+this.People.indexOf(baby));
                    this.People.get(this.People.get(i).spouseIndex).children.add(ch);
                    this.People.get(i).children.add(ch);
                    if (baby.deathReason!="birth complications"){outAr.add(
                            ""+baby.name+", "+baby.lastName+" has been born to "+this.People.get(i).name+", "+
                                    this.People.get(i).lastName+" and "+this.People.get(this.People.get(i).spouseIndex).name+", "+
                                    this.People.get(this.People.get(i).spouseIndex).lastName);}
                    else if (baby.deathReason=="birth complications") {outAr.add(""+baby.name+", "+baby.lastName+" has died at birth");}
                }
            }
        }
        return outAr;
    }

     /************************************************************************
     * METHOD: match                                                         *
     * USE: makes certain eligible people marry                              *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public ArrayList<String> match() {
        Random rand = new Random();
        ArrayList<String> out = new ArrayList<String>();
        ArrayList<Integer> hcp = new ArrayList<Integer>();
        ArrayList<Integer> mcp = new ArrayList<Integer>();
        ArrayList<Integer> lcp = new ArrayList<Integer>();
        ArrayList<Integer> acp = new ArrayList<Integer>();
        int sum = 0;

        for (int i = 0; i < this.People.size(); i++) {
            int temp = this.People.get(i).Attraction();
            acp.add(temp);
            sum += temp;
        }

        double mean = sum / acp.size();
        double squareSum = 0;
        for (int i = 0; i < acp.size(); i++) {
            squareSum += Math.pow(acp.get(i) - mean, 2);
        }
        double std = Math.sqrt((squareSum) / (acp.size() - 1));

        for (int i = 0; i < this.People.size(); i++) {
            if (this.People.get(i).Attraction() < (mean - (std * .5))) {
                lcp.add(i);
                this.People.get(i).clss = "Low";
            } else if (this.People.get(i).Attraction() > (mean + (std * .75))) {
                hcp.add(i);
                this.People.get(i).clss = "High";

            } else {
                mcp.add(i);
                this.People.get(i).clss = "Middle";

            }
        }
        for (Person p : People) {
            int clssSelec = rand.nextInt(101);
            int potential=0;
            try {
                if (p.clss == "High") {
                    if (clssSelec < 92) {
                        potential = hcp.get(rand.nextInt(hcp.size()));
                    } else if (clssSelec < 98) {
                        potential = mcp.get(rand.nextInt(mcp.size()));
                    } else {
                        potential = lcp.get(rand.nextInt(lcp.size()));
                    }
                } else if (p.clss == "Middle") {
                    if (clssSelec < 92) {
                        potential = mcp.get(rand.nextInt(mcp.size()));
                    } else if (clssSelec < 98) {
                        potential = hcp.get(rand.nextInt(hcp.size()));
                    } else {
                        potential = lcp.get(rand.nextInt(lcp.size()));
                    }
                } else if (p.clss == "Low") {
                    if (clssSelec < 92) {
                        potential = lcp.get(rand.nextInt(lcp.size()));
                    } else if (clssSelec < 98) {
                        potential = mcp.get(rand.nextInt(mcp.size()));
                    } else {
                        potential = hcp.get(rand.nextInt(hcp.size()));
                    }
                } else {
                    System.out.println("BAD CLASS");
                    break;
                }
            }
            catch (Exception e)
            {
            }

            if ((p.lastName != this.People.get(potential).lastName)) {
                if ((p.lifestage == "Adult") & (this.People.get(potential).lifestage == "Adult")) {
                    if (p.gender != this.People.get(potential).gender) {
                        if (((this.People.get(potential).spouse == "None")||(getPerson(this.People.get(potential).
                                spouseIndex).lifestage=="Deceased")) & ((p.spouse == "None")||(getPerson(p.spouseIndex).
                                lifestage=="Deceased"))) {
                            p.spouse = this.People.get(potential).name + " " + this.People.get(potential).lastName;
                            this.People.get(potential).spouse = p.name + " " + p.lastName;
                            out.add(""+getPerson(potential).spouse+" has married "+p.spouse);
                            p.spouseIndex = potential;
                            this.People.get(potential).spouseIndex = p.charid;
                        }
                    }
                }
            }
        }
        return out;
    }
}