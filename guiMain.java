import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class guiMain extends JFrame
{
    private JPanel rootPanel;
    private JPanel popPanel;
    private JPanel sciPanel;
    private JButton nextDayButton;
    private JLabel mainLabel;
    private JLabel waterLabel;
    private JLabel foodLabel;
    private JLabel dateLabel;
    private JLabel popLabel;
    private JLabel infLabel;
    private JLabel hapLabel;
    private JLabel unrestLabel;
    private JLabel goldLabel;
    private JLabel gamespeedLabel;
    private JButton eventLogButton;
    private JButton resourcesButton;
    private JButton sciencesButton;
    private JButton populationButton;
    private JButton buildingAndConstructionButton;
    private JButton policiesAndInfluenceButton;
    private JButton abilityButton;
    private JButton mapButton;
    private JButton diplomacyButton;
    private JButton governmentButton;
    private JButton changeGameSpeedButton;
    private JButton mainMenuButton;
    public JPanel mainPanel;
    private JPanel eventPanel;
    private JPanel resPanel;
    private JLabel eventScrollCont;
    private JButton clearEventLogButton;
    private JPanel mapPanel;
    private JPanel mapMainPanel;
    private JButton regionButton;
    private JTextField textField1;
    private JPanel regionPanel;
    private JLabel regionHeader;
    private JButton regionDiscButton;
    private JButton regionBackButton;
    private JLabel regionFetsLabel;
    private JLabel regionRessLabel;
    private JTextPane resTextPane;
    private JPanel semiRootPanel;
    private JScrollPane eventScroll;
    private JScrollPane resScrollPane;
    private JPanel mapBottompanel;
    private JPanel regionPanelButtons;
    private JScrollPane regionResourcesBox;
    private JScrollPane regionFeaturesBox;
    private JLabel regionRescLabel;
    private JLabel regionFeatLabel;
    private JLabel resScrollCont;
    private JPanel popPanelInner;
    private JTextPane personTextFrame;
    private JTextPane popPanelCont;
    private JButton openPerson;
    private JTextField openPersonText;
    private JButton nicknameButton;
    private JTextField nicknameText;
    private JButton setScienceFocus;
    private JComboBox scienceCombo;
    private JLabel scienceText;
    private JTextPane aqeTextPane;
    private JButton EnablePolicyButton;
    private JComboBox policyCombo;
    private JTextPane policyTextPane;
    private JPanel policyPanel;
    private JButton changeTimeStyle;
    private JScrollPane sciTextScroll;
    private JLabel aqeTextLabel;
    private JLabel popContLabel;
    private JLabel regionImageLabel;
    private JPanel greaterEventPanel;
    private JPanel buildingPanel;
    private JButton ConstructBuildingsButton;
    private JButton SeeBuildingQueueButton;
    private JButton UpgradeBuildingsButton;
    private JButton SeeConstructedBuildingsButton;
    private JPanel constructionMenuPanel;
    private JTextPane buildingListPane;
    private JButton ConstructBuildingButton;
    private JPanel upgradeMenuPanel;
    private JTextPane instructionTextPane;
    private JPanel abilityPanel;
    private JPanel constructedMenuPanel;
    private JPanel buildingQueuePanel;
    private JTextPane textPane1;
    private JComboBox abilitySelector;
    private JButton UseAbilityButton;
    private JTextPane constructedBuildingsPane;
    private JTextPane buildingQueuePane;
    private JTextPane buildingHeaderPane;
    private JButton constructBuildingsButton;
    private JButton upgradeBuildingsButton;
    private JButton buildingQueueButton;
    private JButton constructedBuildingsButton;
    private JTextField buildingRegionSelector;
    private JComboBox comboBox2;
    private JButton BuyUpgradeButton;
    private JComboBox buildingTypeSelector;
    private JButton choice1;
    private JButton choice2;
    private JTextPane eventTextPane;
    private JTextField regionNameField;
    private JButton regionNameButton;
    private JComboBox buildingRegionSelectorCombo;
    private JLabel resScrollLabel;
    private JLabel regionResPanel;
    public String focusPanel = "eve";
    public EmptyBorder border = new EmptyBorder(1,1,1,1);
    public ArrayList<Integer> namedRegions = new ArrayList<Integer>();

    public  guiMain()
    {
        Global glb = new Global();
        add(rootPanel);
        setTitle("PRIMORDEUS");
        setSize(1705,900);
        updateMap();
        mainPanel.removeAll();
        mainPanel.add(mapPanel);
        mainPanel.repaint();
        mainPanel.revalidate();

        nextDayButton.setBorder(null);
        nextDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                if (!focusPanel.equals("majorEvent")){
                glb.buttonClicked="main";}
            }
        });

        eventLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                if (!focusPanel.equals("majorEvent")){
                glb.buttonClicked="1";
                focusPanel="eve";
                mainPanel.removeAll();
                mainPanel.add(eventPanel);
                mainPanel.repaint();
                mainPanel.revalidate();}
            }
        });

        resourcesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                if (!focusPanel.equals("majorEvent")){
                glb.buttonClicked="2";
                focusPanel="res";
                mainPanel.removeAll();
                mainPanel.add(resPanel);
                mainPanel.repaint();
                mainPanel.revalidate();}
            }
        });

        sciencesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                if (!focusPanel.equals("majorEvent")){
                glb.buttonClicked="3";
                focusPanel="sci";
                updateScience();
                mainPanel.removeAll();
                mainPanel.add(sciPanel);
                mainPanel.repaint();
                mainPanel.revalidate();}
            }
        });

        populationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                if (!focusPanel.equals("majorEvent")){
                glb.buttonClicked="";
                updatePopulation();
                focusPanel="pop";
                mainPanel.removeAll();
                mainPanel.add(popPanel);
                mainPanel.repaint();
                mainPanel.revalidate();}
            }
        });

        buildingAndConstructionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                if (!focusPanel.equals("majorEvent")){
                glb.buttonClicked="5";
                focusPanel="building";
                String temp = "";
                temp += "╔══════════════════════════════════════════════════════════════════════════════════════╗\n";
                temp += "║                                                                                      ║\n";
                temp += "║                                                                                      ║\n";
                temp += "║   ____     _    _   _____   _        _____    _____   _    _   _____     _____       ║\n";
                temp += "║  |  _ \\\\  | |  | | |_   _| | |      |  __ \\\\  |_  _| | \\\\ | | / ____|   / ____|      ║\n";
                temp += "║  | |_) |  | |  | |   | |   | |      | |  | |   | |   |  \\\\| | | |__    | (___        ║\n";
                temp += "║  |   _ <  | |  | |   | |   | |      | |  | |   | |   | .` | | | |_ |   \\\\___ \\\\      ║\n";
                temp += "║  | |_) |  | |__| |  _| |_  | |____  | |__| |  _| |_  | |\\\\  | | |__| |  ____) |      ║\n";
                temp += "║  |____/   \\\\____/  |_____| |______| |_____/  |_____| |_| \\\\_| \\\\_____| |_____/       ║\n";
                temp += "║                                                                                      ║\n";
                temp += "║                                                                                      ║\n";
                temp += "╚══════════════════════════════════════════════════════════════════════════════════════╝\n";
                buildingHeaderPane.setText(temp);
                mainPanel.removeAll();
                mainPanel.add(buildingPanel);
                mainPanel.repaint();
                mainPanel.revalidate();}
            }
        });

        policiesAndInfluenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                if (!focusPanel.equals("majorEvent")){
                glb.buttonClicked="6";
                focusPanel="pol";
                mainPanel.removeAll();
                updatePolicy();
                mainPanel.add(policyPanel);
                mainPanel.repaint();
                mainPanel.revalidate();}
            }
        });

        abilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                if (!focusPanel.equals("majorEvent")){
                glb.buttonClicked="7"; }
            }
        });
        mapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                if (!focusPanel.equals("majorEvent")){
                glb.buttonClicked="";
                focusPanel="map";
                updateMap();
                mainPanel.removeAll();
                mainPanel.add(mapPanel);
                mainPanel.repaint();
                mainPanel.revalidate();}
            }
        });

        diplomacyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                if (!focusPanel.equals("majorEvent")){
                glb.buttonClicked="9";
                }
            }
        });

        governmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                if (!focusPanel.equals("majorEvent")){
                glb.buttonClicked="10";
                }
            }
        });

        changeGameSpeedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                if (!focusPanel.equals("majorEvent")){
                glb.buttonClicked="11";
                }
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                glb.buttonClicked="12";
            }
        });

        clearEventLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                if (!focusPanel.equals("majorEvent")){
                glb.buttonClicked="clearEvents";
                }
            }
        });

        regionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.buttonClicked="regionOpen";
                glb.playSoundEasy("click.wav");
                focusPanel="reg";
                String input = textField1.getText();
                textField1.setText("");
                try
                {
                    if (input.contains("///"))
                    {
                        glb.buttonClicked="update";
                    }
                    if (input.equals("///discall")&Global.overridesActive)
                    { for (Region reg:glb.regions){reg.discovered=true;}
                        updateMap();
                        return; }
                    if (input.equals("///undiscall")&Global.overridesActive)
                    { for (Region reg:glb.regions){reg.discovered=false;}
                        updateMap();
                        return; }
                    if (input.equals("///addfood")&Global.overridesActive)
                    {
                        glb.addFood("Ambrosia", 100000,500);
                        return;
                    }
                    if (input.equals("///addwater")&Global.overridesActive)
                    {
                        Global.water+=10000;
                        return;
                    }
                    if (input.equals("///addinf")&Global.overridesActive)
                    {
                        Global.influence+=1000;
                        return;
                    }
                    if (input.equals("///addgold")&Global.overridesActive)
                    {
                        Global.gold+=1000;
                        return;
                    }
                    if (input.equals("///stabilize")&Global.overridesActive)
                    {
                        Global.unrest=0;
                        return;
                    }
                    if (input.equals("///addpep")&Global.overridesActive)
                    {
                        for (int i = 1; i < 35;i++) { Global.People.add(new Person(Global.People.size())); }
                        return;
                    }
                    if (input.equals("///CIAAOPW")&Global.overridesActive)
                    {
                        Global.mp=new Map();
                        updateMap();
                    }
                    if (input.equals("///CIAAOPW+")&Global.overridesActive)
                    {
                        Global.mp=new Map();
                        updateMap();
                        { for (Region reg:glb.regions){reg.discovered=true;}
                            updateMap();
                            return; }
                    }
                }
                catch (Exception e){}
                try {
                    int rw=0;
                    int ps;

                    if (input.contains("a")){rw=0;}
                    if (input.contains("b")){rw=1;}
                    if (input.contains("c")){rw=2;}
                    if (input.contains("d")){rw=3;}
                    if (input.contains("e")){rw=4;}
                    if (input.contains("f")){rw=5;}
                    if (input.contains("g")){rw=6;}
                    if (input.contains("h")){rw=7;}
                    if (input.contains("i")){rw=8;}
                    if (input.contains("j")){rw=9;}
                    if (input.contains("k")){rw=10;}
                    if (input.contains("l")){rw=11;}
                    if (input.contains("m")){rw=12;}
                    if (input.contains("n")){rw=13;}
                    if (input.contains("o")){rw=14;}
                    if (input.contains("p")){rw=15;}
                    input = input.replaceAll("[^\\d]", "");
                    ps=Integer.parseInt(input)-1;
                    if (rw>=0&rw<=15&ps>=0&ps<=31)
                    {
                        Region reg = glb.regions.get(glb.find(rw, ps));
                        glb.focusRegion=glb.find(rw, ps);

                        regionHeader.setText("Biome: "+reg.biome +" | Name: "+ reg.regionName);
                        if (glb.discoverable(reg)&!reg.discovered)
                        {
                            regionNameButton.setVisible(false);
                            regionNameField.setVisible(false);
                            regionHeader.setVisible(false);
                            regionDiscButton.setVisible(true);
                            regionDiscButton.setText(" Discover Region ("+reg.disccost+" influence) ");
                        }
                        else
                        {
                            regionDiscButton.setVisible(false);
                            regionNameButton.setVisible(true);
                            regionNameField.setVisible(true);
                            regionHeader.setVisible(true);

                        }
                        updateRegion();
                    }
                }
                catch (Exception e){}
            }
        });

        regionDiscButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                if (glb.influence>=glb.regions.get(glb.focusRegion).disccost)
                {
                    glb.influence=glb.influence-glb.regions.get(glb.focusRegion).disccost;
                    glb.regions.get(glb.focusRegion).discovered=true;
                    regionDiscButton.setVisible(false);
                    Region reg = glb.regions.get(glb.focusRegion);
                    String temp = "";
                    for (int x = 0; x<reg.discresources.size();x++)
                    {
                        temp+=" ";
                        temp+=(""+reg.discresources.get(x)+" ("+reg.discresourcesaman.get(x)+") ");
                    }
                    regionRessLabel.setText(temp);
                    temp = "   Building Slots: (";
                    temp+=reg.slots+") ";
                    for (int x = 0; x<reg.features.size();x++)
                    {
                        temp+=" "+reg.features.get(x);
                    }
                    regionFetsLabel.setText(temp);
                    regionNameButton.setVisible(true);
                    regionHeader.setVisible(true);
                    regionNameField.setVisible(true);
                    updateRegion();
                }
            }
        });

        regionBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.buttonClicked="";
                glb.playSoundEasy("click.wav");
                updateMap();
                mainPanel.removeAll();
                mainPanel.add(mapPanel);
                mainPanel.repaint();
                mainPanel.revalidate();
            }
        });

        openPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try
                {
                    int pers = Integer.parseInt(""+openPersonText.getText());
                    String temp = ""+Global.People.get(pers);
                    personTextFrame.setText(temp);
                    openPersonText.setText("");
                    Global.focusPerson=pers;
                }
                catch (Exception e){}
            }
        });

        nicknameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (Global.focusPerson>-1)
                {
                    if (Global.influence>=1)
                    {
                        Global.influence+=-1;
                        Global.People.get(Global.focusPerson).nickName=nicknameText.getText().substring(0,
                                Math.min(nicknameText.getText().length(), 14));
                        updatePopulation();
                    }
                }
            }
        });

        setScienceFocus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ArrayList sciAva = new ArrayList();
                int size = Global.sciencesNames.size();
                glb.playSoundEasy("click.wav");
                for (int index = 0;index<size;index++)
                {
                    boolean add = true;
                    ArrayList recs = Global.sciencesPreRecs.get(index);
                    for (int rec = 0;rec<recs.size();rec++)
                    {
                        if ((Global.preRecsAttained.contains(recs.get(rec)))&Global.sciencesWork.get(index)>0){}
                        else {
                            add=false;
                        }
                    }
                    if (add)
                    {
                        sciAva.add(index);
                    }
                }
                int select = scienceCombo.getSelectedIndex();
                Global.focusScience= (int) sciAva.get(select);
                updateScience();
            }
        });
        EnablePolicyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                try {
                    int polind=policyCombo.getSelectedIndex()+1;
                    for (Policy pol: Global.policies)
                    {
                        if (pol.index==polind)
                        {
                            if (pol.active){
                                pol.active=false;
                            }
                            else if (!pol.active& Global.influence>=pol.cost)
                            {
                                Global.influence= Global.influence-pol.cost;
                                pol.active=true;
                                if (pol.c1!=-1){Global.policies.get(pol.c1).active=false;}
                                if (pol.c2!=-1){Global.policies.get(pol.c2).active=false;}
                                if (pol.c3!=-1){Global.policies.get(pol.c3).active=false;}
                                if (pol.c4!=-1){Global.policies.get(pol.c4).active=false;}
                                if (pol.c5!=-1){Global.policies.get(pol.c5).active=false;}
                                if (pol.c6!=-1){Global.policies.get(pol.c6).active=false;}
                                if (pol.c7!=-1){Global.policies.get(pol.c7).active=false;}
                                if (pol.c8!=-1){Global.policies.get(pol.c8).active=false;}
                                if (pol.c9!=-1){Global.policies.get(pol.c9).active=false;}
                                if (pol.c10!=-1){Global.policies.get(pol.c10).active=false;}
                            }
                        }
                    }
                }
                catch (Exception e){}
                updatePolicy();
            }
        });

        changeTimeStyle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                if (Global.turnTimer)
                {
                    Global.turnTimer=false;
                    changeTimeStyle.setText("Turn Style");
                }
                else
                    {
                        Global.turnTimer=true;
                        changeTimeStyle.setText("Time Style");
                    }
                repaint();
                revalidate();
            }
        });
        constructBuildingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    namedRegions.clear();
                    buildingRegionSelectorCombo.removeAll();
                    namedRegions.add(-1);
                    buildingRegionSelectorCombo.addItem("");
                    for (Region reg:glb.regions)
                    {
                        if (!reg.regionName.equals(""))
                        {
                            buildingRegionSelectorCombo.addItem(reg.regionName);
                            namedRegions.add(reg.index);
                        }
                    }
                    glb.buttonClicked="5";
                    glb.playSoundEasy("click.wav");
                    focusPanel="construction";
                    constructBuildings();
                    mainPanel.removeAll();
                    mainPanel.add(constructionMenuPanel);
                    mainPanel.repaint();
                    mainPanel.revalidate();
            }
        });
        upgradeBuildingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                glb.playSoundEasy("click.wav");
                focusPanel="upgrade";
                String temp = "";
                int index = 1;
                for (Building b: Global.constructedBuildings){
                    if(b.buildingID != 0){
                        if (b.workNeeded == 0 && b.canUpgrade()) {
                            temp+="\n";
                            temp+="======================================================";
                            temp+="\n";
                            temp+=index + ".";
                            temp+="\n";
                            temp+="Name: " + b.name;
                            temp+="\n";
                            temp+="Description: " + b.description;
                            temp+="\n";
                            temp+="Size: " + b.size;
                            temp+="\n";
                            temp+="Region: " + b.regionName;
                            temp+="\n";
                            temp+="Upkeep Cost: " + b.upkeepCost;
                            temp+="\n";
                            temp+="Capacity: " + b.capacity;
                            temp+="\n";
                            temp+="Workers: " + b.workers;
                            temp+="\n";
                            index++;
                        }
                    }
                }
                constructedBuildingsPane.setText(temp);
                constructedBuildingsPane.invalidate();
                constructedBuildingsPane.validate();
                constructedBuildingsPane.repaint();
                mainPanel.removeAll();
                mainPanel.add(upgradeMenuPanel);
                mainPanel.repaint();
                mainPanel.revalidate();
            }
        });
        constructedBuildingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                glb.playSoundEasy("click.wav");
                focusPanel="constructed";
                String temp = "";
                int index = 1;
                for (Building b: Global.constructedBuildings){
                    if(b.buildingID != 0){
                        if (b.workNeeded == 0) {
                            temp+="\n";
                            temp+="======================================================";
                            temp+="\n";
                            temp+=index + ".";
                            temp+="\n";
                            temp+="Name: " + b.name;
                            temp+="\n";
                            temp+="Description: " + b.description;
                            temp+="\n";
                            temp+="Size: " + b.size;
                            temp+="\n";
                            temp+="Region: " + b.regionName;
                            temp+="\n";
                            temp+="Upkeep Cost: " + b.upkeepCost;
                            temp+="\n";
                            temp+="Capacity: " + b.capacity;
                            temp+="\n";
                            temp+="Workers: " + b.workers;
                            temp+="\n";
                            index++;
                        }
                    }
                }

                constructedBuildingsPane.setText(temp);
                constructedBuildingsPane.invalidate();
                constructedBuildingsPane.validate();
                constructedBuildingsPane.repaint();
                mainPanel.removeAll();
                mainPanel.add(constructedMenuPanel);
                mainPanel.repaint();
                mainPanel.revalidate();
            }
        });
        buildingQueueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                glb.playSoundEasy("click.wav");
                focusPanel="queue";
                String temp = "";
                int index = 1;
                for (Building b: Global.constructedBuildings){
                    if(b.buildingID != 0){
                        if (b.workNeeded > 0) {
                            temp+="\n";
                            temp+="======================================================";
                            temp+="\n";
                            temp+=index + ".";
                            temp+="\n";
                            temp+="Name: " + b.name;
                            temp+="\n";
                            temp+="Description: " + b.description;
                            temp+="\n";
                            temp+="Size: " + b.size;
                            temp+="\n";
                            temp+="Region: " + b.regionName;
                            temp+="\n";
                            temp+="Work Left: " + b.workNeeded;
                            temp+="\n";
                            index++;
                        }
                    }
                }

                buildingQueuePane.setText(temp);
                buildingQueuePane.invalidate();
                buildingQueuePane.validate();
                buildingQueuePane.repaint();
                mainPanel.removeAll();
                mainPanel.add(buildingQueuePanel);
                mainPanel.repaint();
                mainPanel.revalidate();
            }
        });
        ConstructBuildingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> resourceRequirements = new ArrayList<String>();
                ArrayList<String> resourceCost = new ArrayList<String>();
                String buildingChoice = (String)buildingTypeSelector.getItemAt(buildingTypeSelector.getSelectedIndex());
                String input = buildingRegionSelector.getText();
                String originalInput = input;
                String buildingName = "Null";
                String biomeRequired = "";
                String temp = "Null";
                int buildingID = -1;
                try {
                    int rw = 0;
                    int ps = 0;
                    if (input.contains("a")) {
                        rw = 0;
                    }
                    if (input.contains("b")) {
                        rw = 1;
                    }
                    if (input.contains("c")) {
                        rw = 2;
                    }
                    if (input.contains("d")) {
                        rw = 3;
                    }
                    if (input.contains("e")) {
                        rw = 4;
                    }
                    if (input.contains("f")) {
                        rw = 5;
                    }
                    if (input.contains("g")) {
                        rw = 6;
                    }
                    if (input.contains("h")) {
                        rw = 7;
                    }
                    if (input.contains("i")) {
                        rw = 8;
                    }
                    if (input.contains("j")) {
                        rw = 9;
                    }
                    if (input.contains("k")) {
                        rw = 10;
                    }
                    if (input.contains("l")) {
                        rw = 11;
                    }
                    if (input.contains("m")) {
                        rw = 12;
                    }
                    if (input.contains("n")) {
                        rw = 13;
                    }
                    if (input.contains("o")) {
                        rw = 14;
                    }
                    if (input.contains("p")) {
                        rw = 15;
                    }
                    if (!input.equals(""))
                    {
                        input = input.replaceAll("[^\\d]", "");
                        ps = Integer.parseInt(input) - 1;
                    }
                    if (rw >= 0 & rw <= 15 & ps >= 0 & ps <= 31) {
                        Region reg;
                        boolean named = false;
                        if (buildingRegionSelectorCombo.getSelectedIndex()!=0)
                        {
                            reg = glb.regions.get(namedRegions.get(buildingRegionSelectorCombo.getSelectedIndex()));
                            rw = glb.regions.get(namedRegions.get(buildingRegionSelectorCombo.getSelectedIndex())).row;
                            ps = glb.regions.get(namedRegions.get(buildingRegionSelectorCombo.getSelectedIndex())).pos;
                            named=true;
                        }
                        else
                        {
                            reg = glb.regions.get(glb.find(rw, ps));
                        }
                        for (ArrayList building : glb.buildings) {
                            if (buildingChoice.equals((String) building.get(0))) {
                                buildingID = Integer.parseInt((String) building.get(building.size() - 1));
                                buildingName = (String) building.get(0);
                                break;
                            }
                        }
                        if (buildingID != -1 && reg.discovered) {
                            if(Building.regionCheck(buildingID, reg)) {
                                if(Building.materialsCheck(buildingID)){
                                    if (originalInput.equals(""))
                                    {
                                        Global.constructedBuildings.add(new Building(buildingID, reg, reg.regionName));
                                    }
                                    else
                                        {
                                            Global.constructedBuildings.add(new Building(buildingID, reg, originalInput));
                                        }
                                    Building.deductMaterials(buildingID);
                                    temp = "Constructing ";
                                    temp += buildingName;
                                    temp += " at ";
                                    if (named)
                                    {
                                        temp += reg.regionName;
                                    }
                                    else
                                        {
                                            temp += originalInput;
                                        }
                                }
                                else{
                                    temp = "You lack the required resources";
                                }
                            }
                            else{
                                temp = "Cannot construct this building in this region: wrong biome type or lacks the required resources";
                            }
                        } else {
                            temp = "Region not discovered";
                        }
                        instructionTextPane.setText(temp);
                        instructionTextPane.invalidate();
                        instructionTextPane.validate();
                        instructionTextPane.repaint();
                    }
                }
                catch(Exception b){
                    System.out.println(b);
                    instructionTextPane.setText("Invalid Region");
                    instructionTextPane.invalidate();
                    instructionTextPane.validate();
                    instructionTextPane.repaint();
                }
            }
        });
        choice1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMap();
                if(Events.eventID == 2){
                    Events.fishFiascoEffect("choice1");
                    event();
                    return;
                }
                if(Events.eventID == 3){
                    Events.newVillagerEffect("choice1");
                    event();
                    return;
                }
                else {
                    mainPanel.removeAll();
                    mainPanel.add(mapPanel);
                    focusPanel = "map";
                    mainPanel.repaint();
                    mainPanel.revalidate();
                    glb.playSoundEasy("click.wav");
                    focusPanel = "map";
                    Events.reset();
                }
            }
        });
        choice2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMap();
                if(Events.eventID == 2){
                    Events.fishFiascoEffect("choice2");
                    event();
                    return;
                }
                if(Events.eventID == 3){
                    Events.newVillagerEffect("choice2");
                    event();
                    return;
                }
                else {
                    mainPanel.removeAll();
                    mainPanel.add(mapPanel);
                    mainPanel.repaint();
                    mainPanel.revalidate();
                    glb.playSoundEasy("click.wav");
                    focusPanel = "map";
                    Events.reset();
                }
            }
        });
        regionNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                glb.playSoundEasy("click.wav");
                if (glb.influence>=1.0&!regionNameField.getText().equals(""))
                {
                    glb.regions.get(glb.focusRegion).regionName=regionNameField.getText();
                    glb.influence+=-1;
                    regionNameField.setText("");
                    regionHeader.setText("Biome: "+glb.regions.get(glb.focusRegion).biome +" | Name: "+
                            glb.regions.get(glb.focusRegion).regionName);
                }
                mainPanel.repaint();
                mainPanel.revalidate();
            }
        });
    }

    public void setWaterLabel(String x) {
        this.waterLabel.setText(x);
    }
    public void setFoodLabel(String x) {
        this.foodLabel.setText(x);
    }
    public void setDateLabel(String x) {
        this.dateLabel.setText(x);
    }
    public void setPopLabel(String x) {
        this.popLabel.setText(x);
    }
    public void setInfLabel(String x) {
        this.infLabel.setText(x);
    }
    public void setHapLabel(String x) {
        this.hapLabel.setText(x);
    }
    public void setUnrestLabel(String x) {
        this.unrestLabel.setText(x);
    }
    public void setGoldLabel(String x) {
        this.goldLabel.setText(x);
    }
    public void setGamespeedLabel(String x) {
        this.gamespeedLabel.setText(x);
    }

    public void addEventPanel(ArrayList<String> x)
    {
        eventScroll.setAutoscrolls(true);
        String lab = "<html>";
        for (String y:x)
        {
            lab+=(y+"<br/>");
        }
        lab += "</html>";
        eventScrollCont.setText(lab);
        JScrollBar vertical = eventScroll.getVerticalScrollBar();
        vertical.setValue( vertical.getMaximum() );
    }

    public JLabel icoToLab(String x)
    {
        JLabel temp = new JLabel();
        ImageIcon ico = new ImageIcon(strToIco(x));
        temp.setIcon(ico);
        temp.setBackground(Color.BLUE);
        return temp;
    }

    public void setResPanel(String x)
    {
        resScrollCont.setText(x);
    }

    public void updateRegion()
    {
        Global glb = new Global();
        mainPanel.removeAll();
        Region reg = glb.regions.get(glb.focusRegion);
        if (reg.discovered)
        {
            String temp = "";
            for (int x = 0; x<reg.discresources.size();x++)
            {
                temp+=" ";
                temp+=(""+reg.discresources.get(x)+" ("+reg.discresourcesaman.get(x)+") ");
            }
            regionRessLabel.setText(temp);
            temp = "   Building Slots: (";
            temp+=reg.slots+") ";
            for (int x = 0; x<reg.features.size();x++)
            {
                temp+=", "+reg.features.get(x);
            }
            regionFetsLabel.setText(temp);

            if (reg.biome.equals("forest"))
            {
                regionImageLabel.setIcon(new ImageIcon("bin/treeRegionImage.png"));
            }
            if (reg.biome.equals("hills"))
            {
                regionImageLabel.setIcon(new ImageIcon("bin/hillRegionImage.png"));
            }
            if (reg.biome.equals("desert"))
            {
                regionImageLabel.setIcon(new ImageIcon("bin/desertRegionImage.png"));
            }
            if (reg.biome.equals("mountain"))
            {
                regionImageLabel.setIcon(new ImageIcon("bin/mountainRegionImage.png"));
            }
            if (reg.biome.equals("alpine"))
            {
                regionImageLabel.setIcon(new ImageIcon("bin/alpineRegionImage.png"));
            }
            if (reg.biome.equals("jungle"))
            {
                regionImageLabel.setIcon(new ImageIcon("bin/jungleRegionImage.png"));
            }
            if (reg.biome.equals("coast"))
            {
                regionImageLabel.setIcon(new ImageIcon("bin/coastRegionImage.png"));
            }
            if (reg.biome.equals("swamp"))
            {
                regionImageLabel.setIcon(new ImageIcon("bin/marshRegionImage.png"));
            }
            if (reg.biome.equals("plains"))
            {
                regionImageLabel.setIcon(new ImageIcon("bin/plainRegionImage.png"));
            }
            if (reg.biome.equals("ocean"))
            {
                regionImageLabel.setIcon(new ImageIcon("bin/oceanRegionImage.png"));
            }
            if (reg.features.contains("lake"))
            {
                regionImageLabel.setIcon(new ImageIcon("bin/lakeRegionImage.png"));
            }
            if (reg.features.contains("village"))
            {
                regionImageLabel.setIcon(new ImageIcon("bin/villageRegionImage.png"));
            }
        }
        else
        {
            regionRessLabel.setText("");
            regionFetsLabel.setText("");
            regionImageLabel.setIcon(new ImageIcon("bin/fogRegionImage.png"));
        }
        mainPanel.add(regionPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    public void updateMap()
    {
        Global glb = new Global();
        mapMainPanel.removeAll();
        for (int z = 0; z<=31;z++)
        {
            JPanel tile = new JPanel();
            JLabel temp = new JLabel();
            temp.setIcon(new ImageIcon("bin/"+z+".gif"));
            tile.add(temp);
            tile.setBorder(new EmptyBorder(0,0,0,0));
            tile.setBackground(Color.black);
            tile.setAlignmentX(0);
            mapMainPanel.add(tile);

        }
        int tileInd=0;
        for (int x = 0; x < 480; x++)
        {
            GridLayout tileLay = new GridLayout();
            JPanel tile = new JPanel();
            JLabel temp = new JLabel();
            tile.setBorder(new EmptyBorder(0,0,0,0));
            if (x==0)
            {
                ImageIcon ico = new ImageIcon("bin/tileA.gif");
                temp.setIcon(ico);
                tile.add(temp);
            }
            else if (x==32)
            {
                ImageIcon ico = new ImageIcon("bin/tileB.gif");
                temp.setIcon(ico);
                tile.add(temp);
            }
            else if (x==64)
            {
                ImageIcon ico = new ImageIcon("bin/tileC.gif");
                temp.setIcon(ico);
                tile.add(temp);
            }
            else if (x==96)
            {
                ImageIcon ico = new ImageIcon("bin/tileD.gif");
                temp.setIcon(ico);
                tile.add(temp);
            }
            else if (x==128)
            {
                ImageIcon ico = new ImageIcon("bin/tileE.gif");
                temp.setIcon(ico);
                tile.add(temp);
            }
            else if (x==160)
            {
                ImageIcon ico = new ImageIcon("bin/tileF.gif");
                temp.setIcon(ico);
                tile.add(temp);
            }
            else if (x==192)
            {
                ImageIcon ico = new ImageIcon("bin/tileG.gif");
                temp.setIcon(ico);
                tile.add(temp);
            }
            else if (x==224)
            {
                ImageIcon ico = new ImageIcon("bin/tileH.gif");
                temp.setIcon(ico);
                tile.add(temp);
            }
            else if (x==256)
            {
                ImageIcon ico = new ImageIcon("bin/tileI.gif");
                temp.setIcon(ico);
                tile.add(temp);
            }
            else if (x==288)
            {
                ImageIcon ico = new ImageIcon("bin/tileJ.gif");
                temp.setIcon(ico);
                tile.add(temp);
            }
            else if (x==320)
            {
                ImageIcon ico = new ImageIcon("bin/tileK.gif");
                temp.setIcon(ico);
                tile.add(temp);
            }
            else if (x==352)
            {
                ImageIcon ico = new ImageIcon("bin/tileL.gif");
                temp.setIcon(ico);
                tile.add(temp);
            }
            else if (x==384)
            {
                ImageIcon ico = new ImageIcon("bin/tileM.gif");
                temp.setIcon(ico);
                tile.add(temp);
            }
            else if (x==416)
            {
                ImageIcon ico = new ImageIcon("bin/tileN.gif");
                temp.setIcon(ico);
                tile.add(temp);
            }
            else if (x==448)
            {
                ImageIcon ico = new ImageIcon("bin/tileM.gif");
                temp.setIcon(ico);
                tile.add(temp);
            }
            else
            {
                Region reg = glb.regions.get(tileInd);
                if (reg.discovered)
                {
                    tile.add(icoToLab(reg.a1));
                    tile.add(icoToLab(reg.a2));
                    tile.add(icoToLab(reg.a3));

                    tile.add(icoToLab(reg.b1));
                    tile.add(icoToLab(reg.b2));
                    tile.add(icoToLab(reg.b3));

                    tile.add(icoToLab(reg.c1));
                    tile.add(icoToLab(reg.c2));
                    tile.add(icoToLab(reg.c3));
                }
                else
                {
                    for (int y = 0; y<9;y++)
                    {
                        tile.add(icoToLab("fog"));
                    }
                }
                tileInd++;
                tileLay = new GridLayout(3,3);
                int borAma=4;
                tile.setBorder(new EmptyBorder(borAma,4,borAma,4));
            }
            tile.setBackground(Color.black);
            tile.setLayout(tileLay);
            tile.setAlignmentX(0);
            GridLayout lay = new GridLayout(16,32);
            lay.setVgap(-6);
            lay.setHgap(-6);
            mapMainPanel.setLayout(lay);
            mapMainPanel.setBorder(null);
            mapMainPanel.setBackground(Color.BLACK);
            mapMainPanel.add(tile);
            validate();
        }
    }

    public String strToIco(String x)
    {
        if (x.equals("\uD83C\uDF33")) { return "bin/desTreesTile.gif";}
        else if (x.equals("⛆")) { return "bin/grassTile.gif";}
        else if (x.equals("\uD83C\uDF32")) { return "bin/pineTreesTile.gif";}
        else if (x.equals("\uD83C\uDF35")) { return "bin/desertTile.gif";}
        else if (x.equals("\uD83C\uDF0A")) { return "bin/oceanTile.gif";}
        else if (x.equals("caveDef")) { return "bin/caveTileGreen.gif";}
        else if (x.equals("caveAlp")) { return "bin/caveTileTundra.gif";}
        else if (x.equals("caveJun")) { return "bin/caveTileJungle.gif";}
        else if (x.equals("caveMar")) { return "bin/caveTileMarsh.gif";}
        else if (x.equals("caveDes")) { return "bin/caveTileDesert.gif";}
        else if (x.equals("lakeDef")) { return "bin/lakeTileGreen.gif";}
        else if (x.equals("lakeAlp")) { return "bin/lakeTileTundra.gif";}
        else if (x.equals("lakeJun")) { return "bin/lakeTileJungle.gif";}
        else if (x.equals("lakeMar")) { return "bin/lakeTileMarsh.gif";}
        else if (x.equals("riverL")) { return "bin/riverTileL.gif";}
        else if (x.equals("riverR")) { return "bin/riverTileR.gif";}
        else if (x.equals("oasis")) { return "bin/oasisTile.gif";}
        else if (x.equals("\uD83C\uDFD4")) { return "bin/coastTile.gif";}
        else if (x.equals("⛰")) { return "bin/mountainTile.gif";}
        else if (x.equals("\uD83D\uDCA2")) { return "bin/riverTile.gif";}
        else if (x.equals("\uD83C\uDF00")) { return "bin/lakeTile.gif";}
        else if (x.equals("\uD83C\uDF59")) { return "bin/caveTile.gif";}
        else if (x.equals("\uD83D\uDDFB")) { return "bin/hillTile.gif";}
        else if (x.equals("A")) { return "bin/alpineTile.gif";}
        else if (x.equals("\uD83C\uDFEF")) { return "bin/villageTile.gif";}
        else if (x.equals("\uD83C\uDF3F")) { return "bin/swampTile.gif";}
        else if (x.equals("\uD83C\uDF34")) { return "bin/jungleTile.gif";}
        else if (x.equals("desertCact")) { return "bin/desertCactusTile.gif";}
        else if (x.equals("fog")) { return "bin/fogTile.gif";}
        else {return "bin/grassTile.gif";}
    }

    public void updatePopulation()
    {
        ArrayList<String> temp = new ArrayList<>();
        temp.add(" ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        temp.add(" ┃ Id     Nickname            Last Name           First Name       Age         Per  Int  Chr  Str  Dex  Mar   Class     Happiness ┃");
        temp.add(" ┣━━━━━━┳━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━┳━━━━┳━━━━┳━━━━┳━━━━┳━━━━┳━━━━┳━━━━━━━━━┳━━━━━━━━━━━━┫");

        for (int x=0; x<Global.People.size();x++)
        {
            if (!(Global.People.get(x).lifestage.equals("Deceased")))
            {
                temp.add(String.format(" ┃%-6d┃%17s ┃%17s ┃%17s ┃%5d      ┃%3d ┃%3d ┃%3d ┃%3d ┃%3d ┃%3d ┃%8s ┃%10d%% ┃",Global.People.get(x).charid,
                        Global.People.get(x).nickName,Global.People.get(x).lastName,Global.People.get(x).name
                        ,Global.People.get(x).age,Global.People.get(x).perception,Global.People.get(x).intelligence,Global.People.get(x).charisma
                        ,Global.People.get(x).strength,Global.People.get(x).dexterity,Global.People.get(x).martial,
                        Global.People.get(x).clss,Math.round(Global.People.get(x).happiness+Global.People.get(x).happinessMod)));
            }
        }

        temp.add(" ┗━━━━━━┻━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━┻━━━━┻━━━━┻━━━━┻━━━━┻━━━━┻━━━━┻━━━━━━━━━┻━━━━━━━━━━━━┛");
        String lab = "<html> <pre>";
        for (String y:temp)
        {
            lab+=(y+"<br/>");
        }
        lab += "</html>";
        popContLabel.setText(lab);
        popContLabel.invalidate();
        popContLabel.validate();
        popContLabel.repaint();
    }

    public void updateScience()
    {
        int size = Global.sciencesNames.size();
        ArrayList sciAva = new ArrayList();
        sciTextScroll.setAutoscrolls(true);
        for (int index = 0;index<size;index++)
        {
            boolean add = true;
            ArrayList recs = Global.sciencesPreRecs.get(index);
            for (int rec = 0;rec<recs.size();rec++)
            {
                if ((Global.preRecsAttained.contains(recs.get(rec)))&Global.sciencesWork.get(index)>0){}
                else {
                    add=false;
                }
            }
            if (add)
            {
                sciAva.add(index);
            }
        }
        ArrayList<String> temp=new ArrayList<String>();
        temp.add("");
        scienceCombo.removeAllItems();
        for (int x = 0;x<sciAva.size();x++)
        {
            temp.add(" +--------------------------------------------------------------------------------------+\n");
            temp.add(String.format("| Name: %-40s ",(Global.sciencesNames.get(Integer.parseInt(""+sciAva.get(x))))));
            temp.add(String.format("| Work Left: %-20.1f  ",(Global.sciencesWork.get(Integer.parseInt(""+sciAva.get(x))))));
            if (Global.focusScience==Integer.parseInt(""+sciAva.get(x)))
            {
                temp.add("| Current Focus: True     \n");
            }
            else
            {
                temp.add("| Current Focus: False    \n");
            }
            temp.add(" +--------------------------------------------------------------------------------------+\n");
            temp.add("");
            scienceCombo.addItem((Global.sciencesNames.get(Integer.parseInt(""+sciAva.get(x)))));
        }
        String lab = "<html>";
        for (String y:temp)
        {
            lab+=(y+"<br/>");
        }
        lab += "</html>";
        scienceText.setText(lab);
        temp.clear();
        temp.add("Sciences Attained: \n");
        for (String x:Global.sciencesAttained)
        {
            temp.add(x+"\n");
        }

        lab = "<html>";
        for (String y:temp)
        {
            lab+=(y+"<br/>");
        }
        lab += "</html>";

        aqeTextLabel.setText(lab);
        sciTextScroll.invalidate();
        sciTextScroll.revalidate();
        sciTextScroll.repaint();
    }

    public void updatePolicy()
    {
        String temp = "";
        int indx = 1;
        temp+=("Rationing Policies:\n");
        temp+=("\n");
        policyCombo.removeAllItems();
        for (Policy pol: Global.policies)
        {
            if (pol.known&pol.genre.equals("rationing"))
            {
                pol.index=indx;
                indx++;
                temp+=("  "+pol.index+" ");
                temp+=(""+pol.name+" ");
                if (pol.active){temp+=("(Enabled)");}
                else {temp+=("(Disabled)");}
                temp+=(" ("+pol.cost+" influence) "+pol.desc+"\n");
                temp+=("\n");
                policyCombo.addItem(pol.name+" (rationing)");
            }
        }
        temp+=("\n");
        temp+=("Death Policies:\n");
        temp+=("\n");
        for (Policy pol: Global.policies)
        {
            if (pol.known&pol.genre.equals("death"))
            {
                pol.index=indx;
                indx++;
                temp+=("  "+pol.index+" ");
                temp+=(""+pol.name+" ");
                if (pol.active){temp+=("(Enabled)");}
                else {temp+=("(Disabled)");}
                temp+=(" ("+pol.cost+" influence) "+pol.desc+"\n");
                temp+=("\n");
                policyCombo.addItem(pol.name+" (death)");
            }
        }
        temp+=("\n");
        policyTextPane.setText(temp);
        policyTextPane.invalidate();
        policyTextPane.validate();
        policyTextPane.repaint();
    }

    public void constructBuildings(){
        String temp = "";
        buildingTypeSelector.removeAllItems();
        int index = 1;
        for (ArrayList b: Global.buildings){
            if(Integer.parseInt((String)b.get(b.size() - 1)) != 0 && Building.buildCheck(Integer.parseInt((String)b.get(b.size() - 1)))){
                temp+="\n";
                temp+="======================================================";
                temp+="\n";
                temp+=index + ".";
                temp+="\n";
                temp+="Name: " + (String)b.get(0);
                temp+="\n";
                temp+="Description: " + (String)b.get(1);
                temp+="\n";
                temp+="Output: " + (String)b.get(8);
                temp+="\n";
                temp+="Size: " + (String)b.get(2);
                temp+="\n";
                temp+="Work Required: " + (String)b.get(3);
                temp+="\n";
                temp+="Occupation: " + (String)b.get(5);
                temp+="\n";
                temp+="Capacity: " + (String)b.get(6);
                temp+="\n";
                temp+="Cost: " + (String)b.get(12);
                index++;
                buildingTypeSelector.addItem((String)b.get(0));
            }

        }
        buildingListPane.setText(temp);
        buildingListPane.invalidate();
        buildingListPane.validate();
        buildingListPane.repaint();
    }


    public void guiCorrection()
    {
        mainPanel.invalidate();
        mainPanel.validate();
        mainPanel.repaint();
    }

    public void event(){
        String temp ="";
        temp+="======================================================";
        temp+="\n";
        temp+=Events.name;
        temp+="\n";
        temp+=Events.description;
        temp+="\n";
        temp+="======================================================";
        eventTextPane.setText(temp);
        if(Events.choice1){
            choice1.setVisible(true);
            choice1.setText(Events.firstChoiceText);
        }
        else{
            choice1.setVisible(false);
        }
        if(Events.choice2){
            choice2.setVisible(true);
            choice2.setText(Events.secondChoiceText);
        }
        else{
            choice2.setVisible(false);
        }
        mainPanel.removeAll();
        mainPanel.add(greaterEventPanel);
        focusPanel="majorEvent";
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
