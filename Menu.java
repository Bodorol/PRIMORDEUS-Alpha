import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Menu extends Global{
    //Variables and objects

    Boolean auto = false;
    Scanner reader = new Scanner(System.in);
    Population People=new Population();
    Science sci = new Science();
    Building bld = new Building();
    boolean init=true;
    String inp="";
    String foodString="";
    String waterString="";
    String infString="";
    String goldString="";
    int autoLevel=0;
    int gameSpeed=1;
    int tme=1;
    guiMain guiMain = new guiMain();
    guiMainMenu mainMenuGui = new guiMainMenu();

    ArrayList<String> lesserEvents = new ArrayList<String>();

    public Menu()
    {
        addPols();
        bld.initializer();
        Events.initializer();
    }

     /************************************************************************
     * METHOD: mainMenu                                                      *
     * USE: Starts the main menu and leads into the main loop                *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public void mainMenu() throws InterruptedException {
        guiMain.setVisible(false);
        mainMenuGui.setVisible(true);
        playSound("mainTest",1);
        while (true)
        {
            if (new Random().nextInt(1000)<2)
            {
                mainMenuGui.setImage("bin/mainMenuImageBW.png");
                mainMenuGui.validate();
                playSoundEasy("thunderWav.wav");
                Thread.sleep(190);
                mainMenuGui.setImage("bin/mainMenuImage.png");
                mainMenuGui.validate();
            }
            else
            {
            Thread.sleep(100);
            }
            if (buttonClicked.equals("startGame"))
            {
                buttonClicked="";
                mainMenuGui.setStartGameButton("Continue Game");
                stopSounds();
                guiMain.setVisible(true);
                mainMenuGui.setVisible(false);
                if (init)
                {
                    init=false;
                    main();
                }
                else {return;}
            }
            if (inp.equals("2"))
            {
            }
            if (inp.equals("3"))
            {
            }
            if (inp.equals("exitGame"))
            {
            }
        }
    }

     /************************************************************************
     * METHOD: main                                                          *
     * USE: Starts the main game loop                                        *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public void main() throws InterruptedException {
        popHappiness=0;
        date = String.format("[%04d,%4s,%02d]",year,season,day);
        //Temp person for testing
        Person Jo = new Person(0);
        Jo.addBloodTrait("Bloodline Of Jo", 50, 0,0,0,0,0,0,false,40,0,"false","","");
        Jo.name="Jo";
        Jo.nameDom=true;
        Jo.lastName="Smith";
        Jo.gender="Male";
        Jo.age=18;
        //Jo.addEffect("Super Smallpox", "Health","-1.85","-.02","20","40","15", ".1","true", "false");
        People.addPerson(Jo);


        addFood("ambrosia", 970,350);

        //Add people
        for (int i = 1; i < 24;i++) { People.addPerson(new Person(i)); }

        housingAvailable=30;

        //Main loop
        while (true)
        {
            if (day==92)
            {
                day=1;
                if (season=="Spring"){season="Summer";}
                else if (season=="Summer"){season="Fall";}
                else if (season=="Fall"){season="Winter";}
                else if (season=="Winter"){
                    season="Spring";
                    year++;
                }
            }

            //addFood("ambrosia", 970,350);
            //water+=1000;

            //Event gathering
            if (popSize()<housingAvailable){
            People.pollinate();}
            ArrayList temp = People.hungerThePeople();
            for (int x=0; x<temp.size();x++) { if (!(temp.get(x).equals(""))){lesserEvents.add(date+" "+temp.get(x));} }
            temp.clear();
            temp =  People.thirstThePeople();
            for (int x=0; x<temp.size();x++) { if (!(temp.get(x).equals(""))){lesserEvents.add(date+" "+temp.get(x));} }
            People.diseaseThePeople(healthcareLevel);
            for (String x: People.match()){lesserEvents.add(date+" "+x+"\n");}
            for (String x: People.birth(season,day,doctorLevel)){lesserEvents.add(date+" "+x+"\n");}
            for (String x: People.agePeople(season,day,healthcareLevel)){lesserEvents.add(date+" "+x+"");}

            calcFood(); //Calculates amount of food
            printRes();

            int turnTimerInt = -5200;

            while (true)
            {
                valUpdate();
                if ((buttonClicked.equals("1"))||(inp.contains("event"))||(inp.equals("events")))
                {
                    guiMain.addEventPanel(lesserEvents);
                    buttonClicked="";
                }
                else if ((buttonClicked.equals("2"))||(inp.contains("resource")))
                {
                    buttonClicked="";
                    printRes();
                }
                else if ((buttonClicked.equals("3"))||(inp.contains("science")))
                {
                    buttonClicked="";
                }
                else if ((buttonClicked.equals("4"))||(inp.contains("people"))||(inp.contains("pop")))
                {
                    buttonClicked="";
                }
                else if ((buttonClicked.equals("5"))||(inp.contains("construction")))
                {
                    buttonClicked="";
                }
                else if ((buttonClicked.equals("6"))||(inp.contains("policy")))
                {
                    buttonClicked="";
                }
                else if ((buttonClicked.equals("7"))||(inp.contains("project")))
                {
                    buttonClicked="";
                }
                else if ((buttonClicked.equals("8"))||(inp.contains("map")))
                {
                    buttonClicked="";
                }
                else if ((buttonClicked.equals("9"))||(inp.contains("diplomacy")))
                {
                    buttonClicked="";
                }
                else if ((buttonClicked.equals("10"))||(inp.contains("government")))
                {
                    buttonClicked="";
                }
                else if ((buttonClicked.equals("11"))||(inp.contains("speed")))
                {
                    buttonClicked="";
                    if (gameSpeed==5){gameSpeed=1;}
                    else {gameSpeed=gameSpeed+1;}
                    tme=1;
                }
                else if ((buttonClicked.equals("12"))||(inp.contains("main"))||(inp.contains("menu")))
                {
                    buttonClicked="";
                    mainMenu();
                }
                else if (buttonClicked.equals("clearEvents"))
                {
                    buttonClicked="";
                    lesserEvents.clear();
                    guiMain.addEventPanel(lesserEvents);
                }
                else if (buttonClicked.equals("update"))
                {
                    buttonClicked="";
                    valUpdate();
                    guiMain.mainPanel.repaint();
                    guiMain.mainPanel.revalidate();
                }
                else if (buttonClicked.equals("main"))
                {
                    if (tme<gameSpeed)
                    {
                        tme++;
                    }
                    else
                    {
                        buttonClicked="";
                        guiMain.addEventPanel(lesserEvents);
                        guiMain.updateScience();
                        guiMain.updatePopulation();
                        guiMain.mainPanel.repaint();
                        guiMain.mainPanel.revalidate();
                        tme=1;
                    }
                    break;
                }
                else
                    {
                        if (turnTimer&!guiMain.focusPanel.equals("majorEvent"))
                        {
                            turnTimerInt+=10;
                            Thread.sleep(10);
                            if (turnTimerInt>-(gameSpeed*1000))
                            {
                                turnTimerInt=-5200;
                                buttonClicked="";
                                guiMain.addEventPanel(lesserEvents);
                                break;
                            }
                            guiMain.updateScience();
                            guiMain.updatePopulation();
                        }
                        else
                            {
                                Thread.sleep(10);
                            }
                    }
                if (guiMain.focusPanel.equals("res")||guiMain.focusPanel.equals("sci")||guiMain.focusPanel.equals("pop"))
                {
                    guiMain.guiCorrection();
                }
            }

            //End of day stuff
            day++;
            Events.callRandomEvent("Any");
            if(Global.currentEvent){
                guiMain.event();
            }
            water+=waterProduction;
            food+=foodProduction;
            bld.updateBuildings();
            String sciwork = sci.work(150);
            guiMain.updateScience();
            if (sciwork!=""){lesserEvents.add(date+" "+sciwork);}
            People.feedThePeople("class");
            People.hydrateThePeople("class");
            influence+=influenceMade;
            adjustUnrest();
            if ((People.popSize()==0)){break;}
            if (inp.contains("auto")){
                auto=(true);
                if (autoLevel==0)
                {
                    try {
                        System.out.println("Auto to year?");
                        autoLevel = Integer.parseInt(input());
                    }
                    catch (Exception e){}
                }
            }
            if (year>=autoLevel){
                inp="";
                auto=false;
                autoLevel=0;
            }
        }

        //END OF GAME STUFF STARTS HERE========================================

        System.out.println("");
        System.out.println("                                                                    ╔══════════════════" +
                "════════════════════════════════════════════════════════════════════════════╗");
        String score = "Final Score: "+People.People.size()*year;
        int width = 94;

        int padSize = width - score.length();
        int padStart = score.length() + padSize / 2;

        score = String.format("%" + padStart + "s", score);
        score = String.format("%-" + width  + "s", score);

        System.out.print("                                                                    ║");
        System.out.print(score);
        System.out.println("║");
        System.out.println("                                                                    ╚════════════════" +
                "══════════════════════════════════════════════════════════════════════════════╝");
        System.out.println("");

        // allows user to display people details

        while (true)
        {
            int inp=reader.nextInt();
            System.out.println(People.getPerson(inp));
        }
    }

     /************************************************************************
     * METHOD: valUpdate                                                     *
     * USE: Updates game values                                              *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public void valUpdate()
    {

        date = String.format("[%04d,%4s,%02d]",year,season,day);

        popHappiness=People.getPopHap();
        popHappiness=Math.round(popHappiness*100);
        popHappiness=popHappiness/100;
        String pophap=""+popHappiness+"%";
        
        double popUnrest = Math.round(unrest*100);
        popUnrest=popUnrest/100;
        String popunr=""+popUnrest+"";

        String gameSpeedFormat="";
        for (int x = 0; x<gameSpeed;x++)
        {
            gameSpeedFormat+="> ";
        }

        //Food String Format
        foodString="";
        waterString="";
        infString="";
        goldString="";
        foodString+=Math.round(food)+"kc";
        foodMade=0;
        foodMade+=-People.popSize()*2;
        foodMade+=foodProduction;
        if (foodMade*2>=0){foodString+="(+"+foodMade+"kc)";}
        else {foodString+="("+(foodMade)+"kc)";}

        //Water String Format
        waterString+=Math.round(water)+"g";
        waterMade=0;
        waterMade+=-People.popSize()*.5;
        waterMade+=waterProduction;
        if (waterMade*2>=0){waterString+="(+"+waterMade+"g)";}
        else {waterString+="("+(waterMade)+"g)";}

        infString+=String.format("%.2f", influence);
        if (influenceMade>0)
        {
            infString+=" (+"+(influenceMade)+")";
        }

        //gui stuff
        guiMain.setDateLabel(String.format(" %-30s ","   Date: "+date));
        guiMain.setFoodLabel(String.format(" %-30s ","Food: "+foodString));
        guiMain.setWaterLabel(String.format(" %-30s ","Water: "+waterString));
        guiMain.setPopLabel(String.format(" %-30s ","Population: "+People.popSize()+"/"+housingAvailable));
        guiMain.setInfLabel(String.format(" %-30s ","Influence: "+infString));
        guiMain.setHapLabel(String.format(" %-30s ","Happiness: "+pophap));
        guiMain.setUnrestLabel(String.format(" %-30s ","Unrest: "+popunr));
        guiMain.setGoldLabel(String.format(" %-30s ","Gold: "+gold));
        guiMain.setGamespeedLabel(String.format(" %-30s ","Game Speed: "+gameSpeedFormat));
        }

    public void printRes()
    {
        String lab = "<html>";
        lab+="<br/>";
        lab+=(String.format("    Total Food: %-52s     ",""+Math.round(food)+"kc") +"<br/>");
        for (ArrayList fd:foodRes)
        {
            lab+=(String.format("<&nbsp;>        %-10s  %11d k calories %5d days until spoiled      ",fd.get(0),
                    Math.round(Double.parseDouble(""+fd.get(1))),fd.get(2))+"<br/>");
        }
        lab+="<br/>";
        lab+=(String.format("    Water: %-52s          ",""+Math.round(water)+"g")+"<br/>");

        for (ArrayList res:ownedResources)
        {
            if (Double.parseDouble(""+res.get(1))>0.001)
            {
                lab+=(String.format("    "+res.get(0)+": %-26f       ",res.get(1))+"<br/>");
                lab+="<br/>";
            }
        }
        
        lab += "</html>";
        guiMain.setResPanel(lab);
    }

    public void adjustUnrest()
    {
        if (People.getPopHap()>95)
        {
            if (unrest>0)
            {
                if (unrest>4)
                {
                    unrest+=-4;
                }
                else {unrest=0;}
            }
        }
        else if (People.getPopHap()>90)
        {
            if (unrest>0)
            {
                if (unrest>3)
                {
                    unrest+=-3;
                }
                else {unrest=0;}
            }
        }
        else if (People.getPopHap()>80)
        {
            if (unrest>0)
            {
                if (unrest>1)
                {
                    unrest+=-1;
                }
                else {unrest=0;}
            }
        }
        else if (People.getPopHap()>60)
        {
            if (unrest>0)
            {
                if (unrest>.5)
                {
                    unrest+=-.5;
                }
                else {unrest=0;}
            }
        }
        else if (People.getPopHap()>45)
        {
            if (unrest>0)
            {
                if (unrest>.05)
                {
                    unrest+=-.05;
                }
                else {unrest=0;}
            }
        }
        else if (People.getPopHap()>40)
        {
        }
        else if (People.getPopHap()>30)
        {
            if (unrest<100)
            {
                if (unrest<99.5)
                {
                    unrest+=.5;
                }
                else {unrest=100;}
            }
        }
        else if (People.getPopHap()>20)
        {
            if (unrest<100)
            {
                if (unrest<98.5)
                {
                    unrest+=1.5;
                }
                else {unrest=100;}
            }
        }
        else if (People.getPopHap()>10)
        {
            if (unrest<100)
            {
                if (unrest<97.5)
                {
                    unrest+=2.5;
                }
                else {unrest=100;}
            }
        }
        else
            {
                if (unrest<100)
                {
                    if (unrest<95)
                    {
                        unrest+=5;
                    }
                    else {unrest=100;}
                }
            }
    }

    public void addPols()
    {
        //Do not change order of policies!
        //To save cpu the policies are hard coded to their indexes
        policies.add(new Policy("cannibalism", "death"
                , "Adds food when a citizen dies (at the cost of happiness and unrest)",
                55,true,false,1,2,3,-1,-1,-1,-1,-1,-1
                ,-1)); // this policy is in the update mth in the person obj

        policies.add(new Policy("burial", "death"
                , "A citizen is buried after death (costs influence) (default)",
                0,true,true,0,2,3,-1,-1,-1,-1,-1,-1
                ,-1)); // this policy is in the update mth in the person obj

        policies.add(new Policy("funeral pyre", "death"
                , "Adds influence when a citizen dies (at the cost firewood)",
                55,true,false,0,1,3,-1,-1,-1,-1,-1,-1
                ,-1)); // this policy is in the update mth in the person obj

        policies.add(new Policy("dolmen", "death"
                , "Adds influence when a citizen dies (at the cost stone)",
                55,true,false,0,1,2,-1,-1,-1,-1,-1,-1
                ,-1)); // this policy is in the update mth in the person obj

        policies.add(new Policy("age", "rationing"
                , "prioritizes oldest citizens (default)",
                0,true,true,5,6,-1,-1,-1,-1,-1,-1,-1
                ,-1)); // this policy is in the update mth in the person obj

        policies.add(new Policy("class", "rationing"
                , "prioritizes highest class citizens",
                25,true,false,4,6,-1,-1,-1,-1,-1,-1,-1
                ,-1)); // this policy is in the update mth in the person obj

        policies.add(new Policy("equal", "rationing"
                , "gives food and water equally (live or die together)",
                25,true,false,4,5,-1,-1,-1,-1,-1,-1,-1
                ,-1)); // this policy is in the update mth in the person obj
    }
}
