import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Person extends Global
{
    // Attributes
    Random rand = new Random();
    public ArrayList<ArrayList<String>> traits=new ArrayList<ArrayList<String>>();
    public ArrayList<String> traitsNames=new ArrayList<String>();
    public ArrayList<ArrayList<String>> traitsSleeping=new ArrayList<ArrayList<String>>();
    public ArrayList<ArrayList<String>> children=new ArrayList<ArrayList<String>>();
    public ArrayList<ArrayList<String>> effects=new ArrayList<ArrayList<String>>();
    public ArrayList<String> immune = new ArrayList<>();

    public double health=40+rand.nextInt(30);
    public double healtMod=0.0;
    public double healtModOld=0.0;
    public double hungerAmount=0.0;
    public double thirstAmount=0.0;
    public double happiness=(rand.nextInt(11)-5)+50;
    public double happinessMod=0.0;

    public String name;
    public String lastName;
    public String nickName="";
    public String gender;
    public String parent1="Unknown";
    public String parent2="Unknown";
    public String passion="None";
    public String occupation="None";
    public String spouse="None";
    public String lifestage="Adult";
    public String clss="";
    public String birthSeason="";
    public String deathReason="old age";
    public String superDeathReason="";

    public boolean isPregnant=false;
    public boolean nameDom=false;

    public int pregnantDays=0;
    public int birthDay=1;
    public double jobskill=1;
    public int jobdays=0;
    public int work=0;
    public int spouseIndex;
    public int charid;
    public int age=0;

    public int strength;
    public int perception;
    public int intelligence;
    public int martial;
    public int charisma;
    public int dexterity;

    public int parent1ID;
    public int parent2ID;


    public Person(int charD)
    {
        Random rand = new Random();
        this.charid=charD;

        //Name and gender
        if (rand.nextInt(2)==0){this.nameDom=false;}
        else {this.nameDom=true;}

        int temp = rand.nextInt(2);
        if (temp==0) { this.gender="Female"; }
        else { this.gender="Male"; }
        this.Name();
        try {
            ArrayList<String> names=new ArrayList<String>();
            Scanner scanner = new Scanner(new File("bin/namesL.txt"));
            while (scanner.hasNextLine()) {
                names.add(scanner.nextLine());
            }
            scanner.close();
            this.lastName=(names.get(rand.nextInt(999)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Stats
        this.strength=rand.nextInt(21);
        this.perception=rand.nextInt(21);
        this.dexterity=rand.nextInt(21);
        this.martial=rand.nextInt(21);
        this.intelligence=rand.nextInt(21);
        this.charisma=rand.nextInt(21);

        this.age=rand.nextInt(20)+15;

        //Birthday and Age
        temp = rand.nextInt(4);
        if (temp==0) { this.birthSeason="Spring"; }
        if (temp==1) { this.birthSeason="Summer"; }
        if (temp==2) { this.birthSeason="Fall"; }
        if (temp==3) { this.birthSeason="Winter"; }

        if (this.age<2){this.lifestage="Baby";}
        else if (this.age<12){this.lifestage="Adolescent";}
        else if (this.age<40){this.lifestage="Adult";}
        else {this.lifestage="Elder";}
        temp = rand.nextInt(91)+1;
        birthDay=temp;
        this.Verify();

        //Set Health
        for (int i =0; i<this.age;i++)
        {
            this.health=this.health-1;
        }

        int broughtTrait=rand.nextInt(40);
        if (broughtTrait==0) {addBloodTrait("Good Eyesight", 20, 4,0,0,0,1,1,false,0,0,"false","Bad Eyesight","");}
        if (broughtTrait==1) {addBloodTrait("Strong", 20, 0,0,0,6,0,6,false,10,0,"false","Weak","Sickly");}
        if (broughtTrait==2) {addBloodTrait("Genius", 20, 0,11,0,0,0,0,false,0,-5,"false", "Dumb","");}
        if (broughtTrait==3) {addBloodTrait("Fast Reflexes", 20, 0,0,0,0,4,4,false,0,0,"false", "Slow","");}
        if (broughtTrait==4) {addBloodTrait("Smart", 20, 0,7,0,0,0,0,false,0,0,"false", "Dumb","");}
        if (broughtTrait==5) {addBloodTrait("Clever", 20, 0,6,0,0,0,4,false,0,0,"false", "Dumb","");}
        if (broughtTrait==6) {addBloodTrait("Dumb", 20, 0,-4,0,0,0,-2,false,0,0,"false","Smart","Clever");}
        if (broughtTrait==7) {addBloodTrait("Slow", 20, 0,0,0,0,-4,-2,false,0,0,"false","Fast Reflexes","");}
        if (broughtTrait==8) {addBloodTrait("Attractive", 20, 0,0,8,0,0,0,false,0,0,"false","Ugly","Unattractive");}
        if (broughtTrait==9) {addBloodTrait("Ugly", 20, 0,0,-4,0,0,0,false,0,0,"false","Attractive","");}
        if (broughtTrait==10) {addBloodTrait("Sickly", 20, 0,0,0,-5,-4,-5,false,-15,0,"false","Strong","Resilient");}
        if (broughtTrait==11) {addBloodTrait("Resilient", 20, 0,0,0,0,0,0,false,15,0,"false","Sickly","");}
        if (broughtTrait==12) {addBloodTrait("Weak", 20, 0,0,0,-6,0,0,false,-5,0,"false","Strong","");}
        if (broughtTrait==13) {addBloodTrait("Bad Eyesight", 20, -4,0,0,0,-1,-1,false,0,0,"false","Good Eyesight","");}
        if (broughtTrait==14) {addBloodTrait("Unattractive", 20, 0,0,-8,0,0,0,false,0,0,"false","Attractive","Ugly");}
        if (broughtTrait==15) {addBloodTrait("Charming", 20, 0,0,5,0,0,1,false,0,0,"false","","");}
        if ((broughtTrait==16)&(rand.nextInt(100)>80)) {
            addBloodTrait("Incapable", 20, -10,-10,-10,-10,-10,-10,false,-35,0,"false","Genius","Resilient");}
        if (broughtTrait==17) {addBloodTrait("Albino", 40, 0,0,8,0,0,0,false,-10,0,"false","Resilient","");}
        if ((broughtTrait==18)&(rand.nextInt(100)>90)) {
            addBloodTrait("Loved By The World", 5, 10,10,10,10,10,10,false,20,20,"false","Incapable","");}
        if (broughtTrait==19) {addBloodTrait("Optimistic", 30, 0,0,2,0,0,0,false,0,15,"false","Pessimistic","");}
        if (broughtTrait==20) {addBloodTrait("Pessimistic", 30, 0,2,0,0,0,0,false,0,-15,"false","Optimistic","");}
        if (broughtTrait==21) {addBloodTrait("Diligent", 15, 1,1,1,1,1,1,false,-5,-5,"false","Lazy","");}
        if (broughtTrait==22) {addBloodTrait("Lazy", 15, -1,-1,-1,-1,-1,-1,false,5,5,"false","Diligent","");}
        if (broughtTrait==23) {addBloodTrait("Weak Willed", 15, 0,0,0,0,0,0,false,-5,-5,"false","Iron Willed","");}
        if (broughtTrait==24) {addBloodTrait("Iron Willed", 15, 0,0,0,0,0,0,false,5,5,"false","Weak Willed","");}

        VerifyLow();
        updateTraits();
    }

    public Person(int charD,String season,int day,Person p1, Person p2, int one, int two, ArrayList<ArrayList> tr1,ArrayList<ArrayList> tr2)
    {
        this.parent1=(p1.name+", "+p1.lastName);
        this.parent2=(p2.name+", "+p2.lastName);
        this.parent1ID=one;
        this.parent2ID=two;
        this.birthSeason=season;
        this.birthDay=day;
        this.charid=charD;
        Random rand = new Random();

        //Name and gender
        int temp = rand.nextInt(2);

        if (rand.nextInt(2)==0){this.nameDom=false;}
        else {this.nameDom=true;}

        if (temp==0) { this.gender="Female"; }
        else { this.gender="Male"; }

        if ((p1.clss=="High")&(p2.clss!="High")){this.lastName=p1.lastName;}
        else if ((p2.clss=="High")&(p1.clss!="High")){this.lastName=p2.lastName;}
        else if (p1.nameDom) { this.lastName=p1.lastName; }
        else if (p2.nameDom) { this.lastName=p2.lastName;}
        else if (p1.StatSum()>=p2.StatSum()) { this.lastName=p1.lastName; }
        else { this.lastName=p2.lastName; }

        this.Name();

        //Lifestage
        if (this.age<2){this.lifestage="Baby";}
        else if (this.age<12){this.lifestage="Adolescent";}
        else if (this.age<40){this.lifestage="Adult";}
        else {this.lifestage="Elder";}

        //Stats (given from parents)
        this.strength=(((p1.strength+p2.strength)/2)+(rand.nextInt(7)-3));
        this.perception=(((p1.perception+p2.perception)/2)+(rand.nextInt(7)-3));
        this.martial=(((p1.martial+p2.martial)/2)+(rand.nextInt(7)-3));
        this.dexterity=(((p1.dexterity+p2.dexterity)/2)+(rand.nextInt(7)-3));
        this.intelligence=(((p1.intelligence+p2.intelligence)/2)+(rand.nextInt(7)-3));
        this.charisma=(((p1.charisma+p2.charisma)/2)+(rand.nextInt(7)-3));
        this.Verify();

        for (ArrayList x:tr1)
        {
            String chance_ = ""+x.get(1);
            int chance = Integer.parseInt(chance_);
            if ((!this.traitsNames.contains(x.get(0)))&(!this.traitsNames.contains(""+x.get(12))&(!this.traitsNames.contains(""+x.get(13)))))
            {
                if (chance>rand.nextInt(100))
                {
                    addBloodTrait(""+x.get(0),Integer.parseInt(""+x.get(1)),Integer.parseInt(""+x.get(2)),
                            Integer.parseInt(""+x.get(3)),Integer.parseInt(""+x.get(4)),Integer.parseInt(""+x.get(5)),
                            Integer.parseInt(""+x.get(6)),Integer.parseInt(""+x.get(7)), false,
                            Integer.parseInt(""+x.get(9)),Integer.parseInt(""+x.get(10)),"false", (""+x.get(12)),(""+x.get(13)));
                    this.traitsNames.add(""+x.get(0));
                }
                else {
                    addBloodTrait(""+x.get(0),Integer.parseInt(""+x.get(1)),Integer.parseInt(""+x.get(2)),
                            Integer.parseInt(""+x.get(3)),Integer.parseInt(""+x.get(4)),Integer.parseInt(""+x.get(5)),
                            Integer.parseInt(""+x.get(6)),Integer.parseInt(""+x.get(7)), true,
                            Integer.parseInt(""+x.get(9)),Integer.parseInt(""+x.get(10)),"false", (""+x.get(12)),(""+x.get(13)));
                    this.traitsNames.add(""+x.get(0));
                }
            }
        }
        for (ArrayList x:tr2)
        {
            String chance_ = ""+x.get(1);
            int chance = Integer.parseInt(chance_);
            if ((!this.traitsNames.contains(x.get(0)))&(!this.traitsNames.contains(""+x.get(12))&(!this.traitsNames.contains(""+x.get(13)))))
            {
                if (chance>rand.nextInt(100))
                {
                    addBloodTrait(""+x.get(0),Integer.parseInt(""+x.get(1)),Integer.parseInt(""+x.get(2)),
                            Integer.parseInt(""+x.get(3)),Integer.parseInt(""+x.get(4)),Integer.parseInt(""+x.get(5)),
                            Integer.parseInt(""+x.get(6)),Integer.parseInt(""+x.get(7)), false,
                            Integer.parseInt(""+x.get(9)),Integer.parseInt(""+x.get(10)),"false", (""+x.get(12)),(""+x.get(13)));
                    this.traitsNames.add(""+x.get(0));
                }
                else {
                    addBloodTrait(""+x.get(0),Integer.parseInt(""+x.get(1)),Integer.parseInt(""+x.get(2)),
                            Integer.parseInt(""+x.get(3)),Integer.parseInt(""+x.get(4)),Integer.parseInt(""+x.get(5)),
                            Integer.parseInt(""+x.get(6)),Integer.parseInt(""+x.get(7)), true,
                            Integer.parseInt(""+x.get(9)),Integer.parseInt(""+x.get(10)),"false", (""+x.get(12)),(""+x.get(13)));
                    this.traitsNames.add(""+x.get(0));
                }
            }
        }
        updateTraits();
    }

     /************************************************************************
     * METHOD: birht                                                         *
     * USE: determines if the child dies in birth                            *
     * INPUT: double he                                                      *
     ************************************************************************/

    public String birth(double he)
    {
        Random rand = new Random();

        //Health
        double temp = rand.nextInt(100)*he;
        if (temp<25)
        {
            this.health=0;
            this.deathReason="birth complications";
            return update();
        }
        else if (temp<50)
        {
        }
        return "";
    }

     /************************************************************************
     * METHOD: StatSum                                                       *
     * USE: returns the sum of all stats                                     *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public int StatSum()
    {
        int sum=0;
        sum+=this.charisma;
        sum+=this.intelligence;
        sum+=this.strength;
        sum+=this.martial;
        sum+=this.dexterity;
        sum+=this.perception;
        return sum;
    }

     /************************************************************************
     * METHOD: Attraction                                                    *
     * USE: returns Attraction / class level                                 *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public int Attraction()
    {
        int attraction=0;
        attraction+=(this.charisma*2);
        attraction+=this.intelligence*2;
        attraction+=this.strength;
        attraction+=this.martial;
        attraction+=this.dexterity;
        attraction+=this.perception;
        return attraction;
    }

     /************************************************************************
     * METHOD: addBloodTrait                                                 *
     * USE: adds a bloodline trait to this person                            *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public void addBloodTrait(String traitname,int passChance, int per,int ite, int chr, int str, int dex, int mar, boolean sleeping,int he,int ha,String enabled,String oppose,String oppose2)
    {
        ArrayList trait = new ArrayList();
        trait.add(traitname);
        trait.add(passChance);
        trait.add(chr);
        trait.add(ite);
        trait.add(str);
        trait.add(mar);
        trait.add(dex);
        trait.add(per);
        trait.add(sleeping);
        trait.add(he);
        trait.add(ha);
        trait.add(enabled);
        trait.add(oppose);
        trait.add(oppose2);
        traitsSleeping.add(trait);
        if (sleeping==false)
        {
            traits.add(trait);
        }
        updateTraits();
    }

     /************************************************************************
     * METHOD: addEffect                                                     *
     * USE: Adds a effect to this person                                     *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public void addEffect(String effectName, String focus, String mod, String decay, String imz, String trs, String dif, String dpot, String dis, String spread)
    {
        ArrayList effect = new ArrayList();
        effect.add(effectName);
        effect.add(focus);
        effect.add(mod);
        effect.add(decay);
        effect.add(imz);
        effect.add(trs);
        effect.add(dif);
        effect.add(dpot);
        effect.add(dis);
        effect.add(spread);
        effects.add(effect);
    }

     /************************************************************************
     * METHOD: updateEffect                                                  *
     * USE: updates the health and happy values of a trait                   *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public void updateEffect()
    {
        if (this.lifestage!="Deceased")
        {
            double modHap=0;
            double modHel=0;
            int siz=effects.size();
            for (int i=0;i<siz;i++)
            {
                ArrayList x = effects.get(i);
                if (Boolean.parseBoolean(""+x.get(8))){
                    x=disease(x);
                }
                ArrayList old = new ArrayList();
                for (int y=0;y<x.size();y++){old.add(x.get(y));}

                x.clear();
                x.add(old.get(0));
                x.add(old.get(1));
                if ((old.get(8).equals("true")) & (Double.parseDouble("" + old.get(2)) > -0.001)) {
                    effects.remove(i);
                    siz+=-1;
                }
                else if (((Double.parseDouble(""+old.get(2))<-0.01)||(Double.parseDouble(""+old.get(2))>0.01)))
                {
                    if (old.get(1).equals("Health"))
                    {
                        modHel+=(Double.parseDouble(""+old.get(2))+Double.parseDouble(""+old.get(3)));
                        x.add(Double.parseDouble(""+old.get(2))+Double.parseDouble(""+old.get(3)));
                    }
                    else if (old.get(1).equals("Happy"))
                    {
                        modHap+=(Double.parseDouble(""+old.get(2))+Double.parseDouble(""+old.get(3)));
                        x.add(Double.parseDouble(""+old.get(2))+Double.parseDouble(""+old.get(3)));
                    }
                }
                else {
                    effects.remove(i);
                    siz+=-1;
                }

                x.add(old.get(3));
                x.add(old.get(4));
                x.add(old.get(5));
                x.add(old.get(6));
                x.add(old.get(7));
                x.add(old.get(8));
                x.add(old.get(9));
            }
            this.happinessMod=modHap;
            this.healtMod+=modHel;
        }
    }

     /************************************************************************
     * METHOD: disease                                                       *
     * USE: attempts to give this person a disease (effect)                  *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public ArrayList disease(ArrayList effect)
    {
        if (!(immune.contains(effect.get(0))))
        {
            immune.add(""+effect.get(0));
        }
        if (rand.nextInt(100)>Double.parseDouble(""+effect.get(6))){
            double imz=Double.parseDouble(""+effect.get(4));
            effect.remove(4);
            effect.add(4,(imz+1));
        }
        if (Double.parseDouble(""+effect.get(4))>=100)
        {
            effect.remove(3);
            effect.add(3,(effect.get(6)));
        }
        if (rand.nextInt(100)<Double.parseDouble(""+effect.get(5)))
        {
            effect.remove(9);
            effect.add("true");
        }
        return effect;
    }

     /************************************************************************
     * METHOD: addTrait                                                      *
     * USE: adds a trait to this person                                      *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public void addTrait(String traitname,int passChance, int per,int ite, int chr, int str, int dex, int mar,int he,int ha,String enabled)
    {
        ArrayList trait = new ArrayList();
        trait.add(traitname);
        trait.add(passChance);
        trait.add(chr);
        trait.add(ite);
        trait.add(str);
        trait.add(mar);
        trait.add(dex);
        trait.add(per);
        trait.add(he);
        trait.add(ha);
        trait.add(enabled);
        traits.add(trait);
        updateTraits();
    }

     /************************************************************************
     * METHOD: updateTraits                                                  *
     * USE: adds stats from traits to stats                                  *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public void updateTraits()
    {
        for (ArrayList x:traits)
        {
            if (x.get(x.size()-3)=="false")
            {
                x.remove(x.size()-3);
                x.add(11,"true");
                this.charisma+=Integer.parseInt(""+x.get(2));
                this.intelligence+=Integer.parseInt(""+x.get(3));
                this.strength+=Integer.parseInt(""+x.get(4));
                this.martial+=Integer.parseInt(""+x.get(5));
                this.dexterity+=Integer.parseInt(""+x.get(6));
                this.perception+=Integer.parseInt(""+x.get(7));
                this.health+=Integer.parseInt(""+x.get(9));
                this.happiness+=Integer.parseInt(""+x.get(10));
            }
        }
    }

     /************************************************************************
     * METHOD: getHap                                                        *
     * USE: returns this persons happiness value                             *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public double getHap()
    {
        if (this.happiness+this.happinessMod<0){return 0;}
        if (this.happiness+this.happinessMod>100){return 100;}
        return (this.happiness+this.happinessMod);
    }

     /************************************************************************
     * METHOD: Name                                                          *
     * USE: gives this person a random name                                  *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public void Name()
    {
        Random rand = new Random();
        if (this.gender=="Female")
        {
            try {
                ArrayList<String> names=new ArrayList<String>();
                Scanner scanner = new Scanner(new File("bin/namesF.txt"));
                while (scanner.hasNextLine()) {
                    names.add(scanner.nextLine());
                }
                scanner.close();
                this.name=(names.get(rand.nextInt(999)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                ArrayList<String> names=new ArrayList<String>();
                Scanner scanner = new Scanner(new File("bin/namesM.txt"));
                while (scanner.hasNextLine()) {
                    names.add(scanner.nextLine());
                }
                scanner.close();
                this.name=(names.get(rand.nextInt(999)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

     /************************************************************************
     * METHOD: Verify                                                        *
     * USE: checks to make sure no stat is below 0 or above 20               *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public void Verify()
    {
        if (this.strength<0) { this.strength=0; }
        if (this.perception<0) { this.perception=0; }
        if (this.dexterity<0) { this.dexterity=0; }
        if (this.intelligence<0) { this.intelligence=0; }
        if (this.martial<0) { this.martial=0; }
        if (this.charisma<0) { this.charisma=0; }
        if (this.strength>20) { this.strength=20; }
        if (this.perception>20) { this.perception=20; }
        if (this.martial>20) { this.martial=20; }
        if (this.dexterity>20) { this.dexterity=20; }
        if (this.intelligence>20) { this.intelligence=20; }
        if (this.charisma>20) { this.charisma=20; }
    }

     /************************************************************************
     * METHOD: Verify                                                        *
     * USE: checks to make sure no stat is below 0                           *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public void VerifyLow()
    {
        if (this.strength<0) { this.strength=0; }
        if (this.perception<0) { this.perception=0; }
        if (this.dexterity<0) { this.dexterity=0; }
        if (this.intelligence<0) { this.intelligence=0; }
        if (this.martial<0) { this.martial=0; }
        if (this.charisma<0) { this.charisma=0; }
    }

     /************************************************************************
     * METHOD: age                                                           *
     * USE: this ages the person returns any deaths                          *
     * INPUT: Season, day, health                                            *
     ************************************************************************/

    public String age(String season,int day,double he)
    {
        if (this.healtMod==this.healtModOld)
        {
            if (this.healtMod<0)
            {
                this.healtMod+=he;
            }
        }
        this.healtModOld=this.healtMod;
        String temp=update();
        if (temp!=""){return temp;}
        if ((season==this.birthSeason)&(this.birthDay==day))
        {
            if (this.lifestage=="Deceased") {return "";}
            this.age++;
            if (this.age<2){this.lifestage="Baby";}
            else if (this.age<12){this.lifestage="Adolescent";}
            else if (this.age<40){this.lifestage="Adult";}
            else {this.lifestage="Elder";}
            if ((this.age>0)&(this.age<50))
            {
                this.health=this.health-1+(-1+he);
            }
            else if (this.age>50)
            {
                this.health=this.health-1.5+(-1+he);
            }
        }
        return "";
    }

     /************************************************************************
     * METHOD: update                                                        *
     * USE: checks if this person has died                                   *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public String update()
    {
        this.updateEffect();
        if (this.lifestage!="Deceased")
        {
            if ((this.health+this.healtMod<=0)||this.health==0)
            {
                this.lifestage="Deceased";
                if (policies.get(0).active)
                {
                    //addDisease("Name", "Health","HP/Turn","Worsening","Immunity","Infectivity","Difficulty 1-100", "Improvement Rate","Does it spread?", "false (just leave it)");
                    if (!spouse.equals("None"))
                    {
                        People.get(spouseIndex).addEffect("Spouse cannibalised", "Happy", "-30","0.1","100","0","0","0","0","false");
                    }
                    for (ArrayList chil:children)
                    {
                        People.get(Integer.parseInt(""+chil.get(2))).addEffect("Parent cannibalised", "Happy", "-27","0.1","100","0","0","0","0","false");
                    }
                    for (Person pep:People)
                    {
                        pep.addEffect("cannibalism", "Happy", "-10","0.1","100","0","0","0","0","false");
                    }
                    double tmp = (10/(popSize()+.001))*5;
                    unrest+=tmp;
                    addFood("human meat", 81.5, 2);
                }
                else if (policies.get(2).active)
                {
                    if (firewood>=100)
                    {
                        influence+=.3;
                        firewood+=-100;
                    }
                }
                else if (policies.get(3).active)
                {
                    if (stone>=35)
                    {
                        influence+=.6;
                        firewood+=35;
                    }
                }
                else
                    {
                        influence=influence-0.5;
                        for (Person pep:People)
                        {
                            pep.addEffect("dishonored dead", "Happy", "-4","0.1","100","0","0","0","0","false");
                        }
                    }
                double minEf=0;
                for (ArrayList x: effects)
                {
                    if (x.get(1).equals("Health"))
                    {
                        if (Double.parseDouble(""+x.get(2))<minEf)
                        {
                            minEf=Double.parseDouble(""+x.get(2));
                            this.deathReason=(""+x.get(0));
                        }
                    }
                }
                if (this.superDeathReason.equals("")){return (this.name+" "+this.nickName+this.lastName+" has died at age "+this.age+ " due to "+deathReason+"\n");}
                else return (this.name+" "+this.nickName+this.lastName+" has died at age "+this.age+ " due to "+superDeathReason+"\n");
            }
            else if (this.happiness<10&rand.nextInt(5)==1)
            {
                this.health=0;
                this.superDeathReason="suicide";
                return this.update();
            }
            else {return "";}
        }
        else{return "";}
    }

     /************************************************************************
     * METHOD: workDone                                                      *
     * USE: determines the amount of work done from required stats           *
     * INPUT: per, in, chr, str, dex, mar (if these are required)            *
     ************************************************************************/

    public double workDone(int per, int in, int chr, int str, int dex, int mar)
    {
        double work=0;
        int div=0;
        if (per==1){work+=this.perception;div++;}
        if (in==2){work+=this.intelligence;div++;}
        if (chr==3){work+=this.charisma;div++;}
        if (str==4){work+=this.strength;div++;}
        if (dex==5){work+=this.dexterity;div++;}
        if (mar==6){work+=this.martial;div++;}
        double out = (((work/div)*this.jobskill)/10);
        if (lifestage.equals("Elder")){out=out*0.7;}
        if (lifestage.equals("Adolescent")){out=out*0.2;}
        return (out);
    }

     /************************************************************************
     * METHOD: toString                                                      *
     * USE: returns person stats for printing                                *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public String toString()
    {
        String returnString = "";
        returnString += ""+"\n";
        returnString += "Char ID:        "+this.charid+"\n";
        returnString += "Name:           "+this.name+"\n";
        returnString += "Last Name:      "+this.lastName+"\n";
        returnString += "Gender:         "+this.gender+"\n";
        returnString += "Is Pregnant:    "+this.isPregnant+"\n";
        returnString += "Pregnant Days:  "+this.pregnantDays+"\n";
        returnString += "Death Reason:   "+this.deathReason+"\n";
        returnString += "Age:            "+this.age+" ("+this.lifestage+")"+"\n";
        returnString += "Adjusted Health:"+(this.health+this.healtMod)+"\n";
        returnString += "Natural Health: "+(this.health)+"\n";
        returnString += "Birth Season:   "+this.birthSeason+"\n";
        returnString += "Birth Day:      "+this.birthDay+"\n";
        returnString += "Spouse:         "+this.spouse+"\n";
        returnString += "Spouse ID:      "+this.spouseIndex+"\n";
        returnString += "Occupation:     "+this.occupation+"\n";
        returnString += "Passion:        "+this.passion+"\n";
        returnString += "Class:          "+this.clss+"\n";
        returnString += "Parent 1:       "+this.parent1+"\n";
        returnString += "Parent 1 ID:    "+this.parent1ID+"\n";
        returnString += "Parent 2:       "+this.parent2+"\n";
        returnString += "Parent 2 ID:    "+this.parent2ID+"\n";
        returnString += "Happiness:      "+(this.happiness+this.happinessMod)+"\n";
        returnString += "Effects:        "+this.effects+"\n";
        returnString += "Diseases Had:   "+this.immune+"\n";
        returnString += "Children:       "+this.children+"\n";
        returnString += "Traits:         ";
        for (ArrayList ar:traits)
        {
            returnString += ", "+ar.get(0);
        }
        returnString+="\n";
        returnString += ""+"\n";
        returnString += "Per:           "+this.perception+"\n";
        returnString += "Int:           "+this.intelligence+"\n";
        returnString += "Chr:           "+this.charisma+"\n";
        returnString += "Str:           "+this.strength+"\n";
        returnString += "Dex:           "+this.dexterity+"\n";
        returnString += "Mar:           "+this.martial+"\n";
        returnString += ""+"\n";

        return returnString;
    }
}