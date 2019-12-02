import java.util.Random;
import java.util.Scanner;

public class Map extends Global{

    int oceanSideAlpha;
    int oceanSideBeta;
    int mountainSideAlpha;
    int mountainSideBeta;
    int riverDirection=1;

    //1         3
    //
    //
    //2         4

     /************************************************************************
     * METHOD: Map                                                           *
     * USE: Generates a map                                                  *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public Map()
    {
        Random rand = new Random();
        oceanSideAlpha= rand.nextInt(3)+1; //makes a random corner a ocean and mountain
        if (oceanSideAlpha==1)
        {
            oceanSideBeta=2;
            mountainSideAlpha=4;
            mountainSideBeta=3;
        }
        if (oceanSideAlpha==2)
        {
            oceanSideBeta=1;
            mountainSideAlpha=3;
            mountainSideBeta=4;
        }
        if (oceanSideAlpha==3)
        {
            oceanSideBeta=4;
            mountainSideAlpha=2;
            mountainSideBeta=1;
        }
        if (oceanSideAlpha==4)
        {
            oceanSideBeta=3;
            mountainSideAlpha=1;
            mountainSideBeta=2;
        }
        int pos=0;
        int row=0;

        while (row<15)
        {
            regions.add(new Region(row,pos,regions.size()));
            pos++;
            if (pos==31)
            {
                pos=0;
                row++;
            }
        }

        for (Region reg: regions)
        {
            reg._1=find((reg.row-1),(reg.pos-1));
            reg._2=find((reg.row-1),(reg.pos));
            reg._3=find((reg.row-1),(reg.pos+1));
            reg._4=find((reg.row),(reg.pos+1));
            reg._5=find((reg.row+1),(reg.pos+1));
            reg._6=find((reg.row+1),(reg.pos));
            reg._7=find((reg.row+1),(reg.pos-1));
            reg._8=find((reg.row),(reg.pos-1));
        }

        //Initial biome seeding

        if (oceanSideAlpha==1)
        {
            regions.get(find(0,0)).biome="ocean";
            regions.get(find(0,0)).seeded=false;

            regions.get(find(1,0)).biome="ocean";
            regions.get(find(1,0)).seeded=false;

            regions.get(find(2,0)).biome="ocean";
            regions.get(find(2,0)).seeded=false;

            regions.get(find(3,0)).biome="ocean";
            regions.get(find(3,0)).seeded=false;

            regions.get(find(4,0)).biome="ocean";
            regions.get(find(4,0)).seeded=false;

            regions.get(find(10,30)).biome="mountain";
            regions.get(find(10,30)).seeded=false;

            regions.get(find(9,30)).biome="mountain";
            regions.get(find(9,30)).seeded=false;

            regions.get(find(8,30)).biome="mountain";
            regions.get(find(8,30)).seeded=false;

            regions.get(find(7,30)).biome="mountain";
            regions.get(find(7,30)).seeded=false;

            regions.get(find(6,30)).biome="mountain";
            regions.get(find(6,30)).seeded=false;
        }

        if (oceanSideAlpha==2)
        {
            regions.get(find(10,0)).biome="ocean";
            regions.get(find(10,0)).seeded=false;

            regions.get(find(9,0)).biome="ocean";
            regions.get(find(9,0)).seeded=false;

            regions.get(find(8,0)).biome="ocean";
            regions.get(find(8,0)).seeded=false;

            regions.get(find(7,0)).biome="ocean";
            regions.get(find(7,0)).seeded=false;

            regions.get(find(6,0)).biome="ocean";
            regions.get(find(6,0)).seeded=false;

            regions.get(find(0,30)).biome="mountain";
            regions.get(find(0,30)).seeded=false;

            regions.get(find(1,30)).biome="mountain";
            regions.get(find(1,30)).seeded=false;

            regions.get(find(2,30)).biome="mountain";
            regions.get(find(2,30)).seeded=false;

            regions.get(find(3,30)).biome="mountain";
            regions.get(find(3,30)).seeded=false;

            regions.get(find(4,30)).biome="mountain";
            regions.get(find(4,30)).seeded=false;
        }

        if (oceanSideAlpha==3)
        {
            regions.get(find(10,0)).biome="mountain";
            regions.get(find(10,0)).seeded=false;

            regions.get(find(9,0)).biome="mountain";
            regions.get(find(9,0)).seeded=false;

            regions.get(find(8,0)).biome="mountain";
            regions.get(find(8,0)).seeded=false;

            regions.get(find(7,0)).biome="mountain";
            regions.get(find(7,0)).seeded=false;

            regions.get(find(6,0)).biome="mountain";
            regions.get(find(6,0)).seeded=false;

            regions.get(find(0,30)).biome="ocean";
            regions.get(find(0,30)).seeded=false;

            regions.get(find(1,30)).biome="ocean";
            regions.get(find(1,30)).seeded=false;

            regions.get(find(2,30)).biome="ocean";
            regions.get(find(2,30)).seeded=false;

            regions.get(find(3,30)).biome="ocean";
            regions.get(find(3,30)).seeded=false;

            regions.get(find(4,30)).biome="ocean";
            regions.get(find(4,30)).seeded=false;
        }

        if (oceanSideAlpha==4)
        {
            regions.get(find(0,0)).biome="mountain";
            regions.get(find(0,0)).seeded=false;

            regions.get(find(4,0)).biome="mountain";
            regions.get(find(4,0)).seeded=false;

            regions.get(find(10,30)).biome="ocean";
            regions.get(find(10,30)).seeded=false;

            regions.get(find(6,30)).biome="ocean";
            regions.get(find(6,30)).seeded=false;
        }

        //Generates ocean and mountain biomes from seeds

        while (true)
        {
            int seeded=0;
            int mountains = countBiome("mountain");
            int oceans = countBiome("ocean");

            for (Region reg:regions)
            {
                boolean alert=true;
                if (reg.biome.equals("mountain") & mountains>30){alert = false;}
                if (reg.biome.equals("ocean") & oceans>30){alert = false;}

                if ((!reg.seeded)&alert)
                {
                    int choice = rand.nextInt(100);
                    if (choice>96)
                    {
                        if (reg._5!=-1){if (regions.get(reg._5).biome.equals("plains")){regions.get(reg._5).biome=reg.biome;
                            regions.get(reg._5).seeded=false;
                        }}
                        seeded++;
                    }
                    else if (choice>91)
                    {
                        if (reg._1!=-1){if (regions.get(reg._1).biome.equals("plains")){regions.get(reg._1).biome=reg.biome;
                        regions.get(reg._1).seeded=false;
                        }}
                        seeded++;
                    }
                    else if (choice>89)
                    {
                        if (reg._3!=-1){if (regions.get(reg._3).biome.equals("plains")){regions.get(reg._3).biome=reg.biome;
                            regions.get(reg._3).seeded=false;
                        }}
                        seeded++;
                    }
                    else if (choice>85)
                    {
                        if (reg._7!=-1){if (regions.get(reg._7).biome.equals("plains")){regions.get(reg._7).biome=reg.biome;
                            regions.get(reg._7).seeded=false;
                        }}
                        seeded++;
                    }
                    else if (choice>65)
                    {
                        if (reg._2!=-1){if (regions.get(reg._2).biome.equals("plains")){regions.get(reg._2).biome=reg.biome;
                            regions.get(reg._2).seeded=false;
                        }}
                        seeded++;
                    }
                    else if (choice>45)
                    {
                        if (reg._4!=-1){if (regions.get(reg._4).biome.equals("plains")){regions.get(reg._4).biome=reg.biome;
                            regions.get(reg._4).seeded=false;
                        }}
                        seeded++;
                    }
                    else if (choice>25)
                    {
                        if (reg._6!=-1){if (regions.get(reg._6).biome.equals("plains")){regions.get(reg._6).biome=reg.biome;
                            regions.get(reg._6).seeded=false;
                        }}
                        seeded++;
                    }
                    else if (choice>5)
                    {
                        if (reg._8!=-1){if (regions.get(reg._8).biome.equals("plains")){regions.get(reg._8).biome=reg.biome;
                            regions.get(reg._8).seeded=false;
                        }}
                        seeded++;
                    }
                    else
                        {
                            reg.seeded=true;
                        }
                }
            }
            if (seeded==0)
            {
                break;
            }
        }

        //Creates coasts and hills next to oceans and mountains

        for (Region reg:regions)
        {
            if (reg.biome.equals("plains"))
            {
                if (reg._1!=-1){
                if ((regions.get(reg._1).biome.equals("mountain"))||(regions.get(reg._1).biome.equals("ocean")))
                {
                    if (rand.nextInt(100)>60)
                    { if (regions.get(reg._1).biome.equals("mountain")){reg.biome="hills";}}
                    if (regions.get(reg._1).biome.equals("ocean")){reg.biome="coast";}
                }}
                if (reg._2!=-1){
                if ((regions.get(reg._2).biome.equals("mountain"))||(regions.get(reg._2).biome.equals("ocean")))
                {
                    if (rand.nextInt(100)>20)
                    { if (regions.get(reg._2).biome.equals("mountain")){reg.biome="hills";}}
                    if (regions.get(reg._2).biome.equals("ocean")){reg.biome="coast";}
                }}
                if (reg._3!=-1){
                if ((regions.get(reg._3).biome.equals("mountain"))||(regions.get(reg._3).biome.equals("ocean")))
                {
                    if (rand.nextInt(100)>60)
                    { if (regions.get(reg._3).biome.equals("mountain")){reg.biome="hills";}}
                    if (regions.get(reg._3).biome.equals("ocean")){reg.biome="coast";}
                }}
                if (reg._4!=-1){
                if ((regions.get(reg._4).biome.equals("mountain"))||(regions.get(reg._4).biome.equals("ocean")))
                {
                    if (rand.nextInt(100)>20)
                    { if (regions.get(reg._4).biome.equals("mountain")){reg.biome="hills";}}
                    if (regions.get(reg._4).biome.equals("ocean")){reg.biome="coast";}
                }}
                if (reg._5!=-1){
                if ((regions.get(reg._5).biome.equals("mountain"))||(regions.get(reg._5).biome.equals("ocean")))
                {
                    if (rand.nextInt(100)>60)
                    { if (regions.get(reg._5).biome.equals("mountain")){reg.biome="hills";}}
                    if (regions.get(reg._5).biome.equals("ocean")){reg.biome="coast";}
                }}
                if (reg._6!=-1){
                if ((regions.get(reg._6).biome.equals("mountain"))||(regions.get(reg._6).biome.equals("ocean")))
                {
                    if (rand.nextInt(100)>20)
                    { if (regions.get(reg._6).biome.equals("mountain")){reg.biome="hills";}}
                    if (regions.get(reg._6).biome.equals("ocean")){reg.biome="coast";}
                }}
                if (reg._7!=-1){
                if ((regions.get(reg._7).biome.equals("mountain"))||(regions.get(reg._7).biome.equals("ocean")))
                {
                    if (rand.nextInt(100)>60)
                    { if (regions.get(reg._7).biome.equals("mountain")){reg.biome="hills";}}
                    if (regions.get(reg._7).biome.equals("ocean")){reg.biome="coast";}
                }}
                if (reg._8!=-1){
                if ((regions.get(reg._8).biome.equals("mountain"))||(regions.get(reg._8).biome.equals("ocean")))
                {
                    if (rand.nextInt(100)>20)
                    { if (regions.get(reg._8).biome.equals("mountain")){reg.biome="hills";}}
                    if (regions.get(reg._8).biome.equals("ocean")){reg.biome="coast";}
                }}
            }
        }

        for (Region reg:regions)
        {
            reg.seeded=false;
        }

        int random2 = rand.nextInt(5);
        String highB="";
        if (random2>=4) { highB=""; }
        else if (random2>=2){highB="alpine";}
        else {highB="desert";}
        random2 = rand.nextInt(5);
        String lowB="";
        if (random2>=4) { lowB=""; }
        else if (random2>=2){lowB="swamp";}
        else {lowB="jungle";}

        if (oceanSideAlpha==1)
        {
            if (!lowB.equals(""))
            {
                if (regions.get(find(10,0)).biome.equals("plains")){
                regions.get(find(10,0)).biome=lowB;
                regions.get(find(10,0)).seeded=false;}

                if (regions.get(find(9,0)).biome.equals("plains")){
                regions.get(find(9,0)).biome=lowB;
                regions.get(find(9,0)).seeded=false;}
            }

            if (!highB.equals(""))
            {
                if (regions.get(find(0,30)).biome.equals("plains")){
                regions.get(find(0,30)).biome=highB;
                regions.get(find(0,30)).seeded=false;}

                if (regions.get(find(1,30)).biome.equals("plains")){
                regions.get(find(1,30)).biome=highB;
                regions.get(find(1,30)).seeded=false;}
            }
        }

        if (oceanSideAlpha==2)
        {
            if (!lowB.equals(""))
            {
                if (regions.get(find(0,0)).biome.equals("plains")){
                regions.get(find(0,0)).biome=lowB;
                regions.get(find(0,0)).seeded=false;}

                if (regions.get(find(1,0)).biome.equals("plains")){
                regions.get(find(1,0)).biome=lowB;
                regions.get(find(1,0)).seeded=false;}
            }
            if (!highB.equals(""))
            {
                if (regions.get(find(9,30)).biome.equals("plains")){
                regions.get(find(9,30)).biome=highB;
                regions.get(find(9,30)).seeded=false;}

                if (regions.get(find(10,30)).biome.equals("plains")){
                regions.get(find(10,30)).biome=highB;
                regions.get(find(10,30)).seeded=false;}
            }
        }

        if (oceanSideAlpha==3)
        {
            if (!lowB.equals(""))
            {
                if (regions.get(find(10,30)).biome.equals("plains")){
                regions.get(find(10,30)).biome=lowB;
                regions.get(find(10,30)).seeded=false;}

                if (regions.get(find(9,30)).biome.equals("plains")){
                regions.get(find(9,30)).biome=lowB;
                regions.get(find(9,30)).seeded=false;}
            }
            if (!highB.equals(""))
            {
                if (regions.get(find(0,0)).biome.equals("plains")){
                regions.get(find(0,0)).biome=highB;
                regions.get(find(0,0)).seeded=false;}

                if (regions.get(find(1,0)).biome.equals("plains")){
                regions.get(find(1,0)).biome=highB;
                regions.get(find(1,0)).seeded=false;}
            }
        }

        if (oceanSideAlpha==4)
        {
            if (!lowB.equals(""))
            {
                if (regions.get(find(0,30)).biome.equals("plains")){
                regions.get(find(0,30)).biome=lowB;
                regions.get(find(0,30)).seeded=false;}

                if (regions.get(find(1,30)).biome.equals("plains")){
                regions.get(find(1,30)).biome=lowB;
                regions.get(find(1,30)).seeded=false;}
            }
            if (!highB.equals(""))
            {
                if (regions.get(find(10,0)).biome.equals("plains")){
                regions.get(find(10,0)).biome=highB;
                regions.get(find(10,0)).seeded=false;}

                if (regions.get(find(9,0)).biome.equals("plains")){
                regions.get(find(9,0)).biome=highB;
                regions.get(find(9,0)).seeded=false;}
            }
        }

        while (true) {
            int seeded = 0;
            int highBc = countBiome(highB);
            int lowBc = countBiome(lowB);

            for (Region reg : regions) {
                boolean alert = true;
                if (reg.biome.equals(highB) & highBc > 20) {
                    alert = false;
                }
                if (reg.biome.equals(lowB) & lowBc > 20) {
                    alert = false;
                }

                if ((!reg.seeded) & alert & (reg.biome.equals(lowB)||reg.biome.equals(highB))) {
                    int choice = rand.nextInt(100);
                    if (choice > 96) {
                        if (reg._5 != -1) {
                            if (regions.get(reg._5).biome.equals("plains")) {
                                regions.get(reg._5).biome = reg.biome;
                                regions.get(reg._5).seeded = false;
                            }
                        }
                        seeded++;
                    } else if (choice > 91) {
                        if (reg._1 != -1) {
                            if (regions.get(reg._1).biome.equals("plains")) {
                                regions.get(reg._1).biome = reg.biome;
                                regions.get(reg._1).seeded = false;
                            }
                        }
                        seeded++;
                    } else if (choice > 89) {
                        if (reg._3 != -1) {
                            if (regions.get(reg._3).biome.equals("plains")) {
                                regions.get(reg._3).biome = reg.biome;
                                regions.get(reg._3).seeded = false;
                            }
                        }
                        seeded++;
                    } else if (choice > 85) {
                        if (reg._7 != -1) {
                            if (regions.get(reg._7).biome.equals("plains")) {
                                regions.get(reg._7).biome = reg.biome;
                                regions.get(reg._7).seeded = false;
                            }
                        }
                        seeded++;
                    } else if (choice > 65) {
                        if (reg._2 != -1) {
                            if (regions.get(reg._2).biome.equals("plains")) {
                                regions.get(reg._2).biome = reg.biome;
                                regions.get(reg._2).seeded = false;
                            }
                        }
                        seeded++;
                    } else if (choice > 45) {
                        if (reg._4 != -1) {
                            if (regions.get(reg._4).biome.equals("plains")) {
                                regions.get(reg._4).biome = reg.biome;
                                regions.get(reg._4).seeded = false;
                            }
                        }
                        seeded++;
                    } else if (choice > 25) {
                        if (reg._6 != -1) {
                            if (regions.get(reg._6).biome.equals("plains")) {
                                regions.get(reg._6).biome = reg.biome;
                                regions.get(reg._6).seeded = false;
                            }
                        }
                        seeded++;
                    } else if (choice > 10) {
                        if (reg._8 != -1) {
                            if (regions.get(reg._8).biome.equals("plains")) {
                                regions.get(reg._8).biome = reg.biome;
                                regions.get(reg._8).seeded = false;
                            }
                        }
                        seeded++;
                    } else {
                        reg.seeded = true;
                    }
                }
            }
            if (seeded == 0) {
                break;
            }
        }

        //Sets all tiles in ocean mountain coast and hills regions to their given icon

        for (Region reg:regions)
        {
            if (reg.biome=="ocean")
            {
                reg.setAll("\uD83C\uDF0A");
            }
            if (reg.biome=="mountain")
            {
                reg.setAll("⛰");
            }
            if (reg.biome=="coast")
            {
                reg.setAll("\uD83C\uDFD4");
            }
            if (reg.biome=="hills")
            {
                reg.setAll("\uD83D\uDDFB");
            }
            if (reg.biome.equals("desert"))
            {
                reg.setAll("\uD83C\uDF35");
                if (rand.nextInt(100)>15)
                {
                    int tempRand = rand.nextInt(10);
                    if (tempRand==1) { reg.a1="desertCact"; }
                    if (tempRand==2) { reg.a2="desertCact"; }
                    if (tempRand==3) { reg.a3="desertCact"; }
                    if (tempRand==4) { reg.b1="desertCact"; }
                    if (tempRand==5) { reg.b2="desertCact"; }
                    if (tempRand==6) { reg.b3="desertCact"; }
                    if (tempRand==7) { reg.c1="desertCact"; }
                    if (tempRand==8) { reg.c2="desertCact"; }
                    if (tempRand==9) { reg.c3="desertCact"; }
                }
            }
            if (reg.biome.equals("alpine"))
            {
                reg.setAll("A");
            }
            if (reg.biome.equals("swamp"))
            {
                reg.setAll("\uD83C\uDF3F");
            }
            if (reg.biome.equals("jungle"))
            {
                reg.setAll("\uD83C\uDF34");
            }
        }

        //Generates forests randomly and sets their tiles

        for (Region reg:regions)
        {
            if (reg.biome.equals("plains"))
            {
                if (rand.nextInt(100)>50){reg.biome="forest";}
            }

            if (reg.biome.equals("forest"))
            {
                if (rand.nextInt(100)<80){reg.a1="\uD83C\uDF33";}
                else {reg.a1="\uD83C\uDF32";}
                if (rand.nextInt(100)<80){reg.a2="\uD83C\uDF33";}
                else {reg.a2="\uD83C\uDF32";}
                if (rand.nextInt(100)<80){reg.a3="\uD83C\uDF33";}
                else {reg.a3="\uD83C\uDF32";}
                if (rand.nextInt(100)<80){reg.b1="\uD83C\uDF33";}
                else {reg.b1="\uD83C\uDF32";}
                if (rand.nextInt(100)<80){reg.b2="\uD83C\uDF33";}
                else {reg.b2="\uD83C\uDF32";}
                if (rand.nextInt(100)<80){reg.b3="\uD83C\uDF33";}
                else {reg.b3="\uD83C\uDF32";}
                if (rand.nextInt(100)<80){reg.c1="\uD83C\uDF33";}
                else {reg.c1="\uD83C\uDF32";}
                if (rand.nextInt(100)<80){reg.c2="\uD83C\uDF33";}
                else {reg.c2="\uD83C\uDF32";}
                if (rand.nextInt(100)<80){reg.c3="\uD83C\uDF33";}
                else {reg.c3="\uD83C\uDF32";}
            }
        }

        String riverico="\uD83D\uDCA2";

        //Sets village region

        regions.get(find(7,15)).b2="\uD83C\uDFEF";
        regions.get(find(7,15)).mainFeature="village";
        regions.get(find(7,15)).features.add("village");
        regions.get(find(7,15)).discovered=true;

        //Initial river seeding

        if (mountainSideAlpha==4)
        {
            int riveramount=rand.nextInt(2)+1;
            riverDirection=1;
            for (int x = 0 ; x < riveramount;x++)
            {
                int randrow=rand.nextInt(5)+5;
                int randpos=30-rand.nextInt(3);
                if (regions.get(find(randrow,randpos)).mainFeature.equals(""))
                {
                    regions.get(find(randrow,randpos)).mainFeature="river";
                    regions.get(find(randrow,randpos)).features.add("river");
                    regions.get(find(randrow,randpos)).b2=riverico;
                    regions.get(find(randrow,randpos)).riverSeeded=true;
                }
            }
        }

        if (mountainSideAlpha==3)
        {
            int riveramount=rand.nextInt(2)+1;
            riverDirection=2;
            for (int x = 0 ; x < riveramount;x++)
            {
                int randrow=rand.nextInt(5);
                int randpos=30-rand.nextInt(3);
                if (regions.get(find(randrow,randpos)).mainFeature.equals(""))
                {
                    regions.get(find(randrow,randpos)).mainFeature="river";
                    regions.get(find(randrow,randpos)).features.add("river");
                    regions.get(find(randrow,randpos)).b2=riverico;
                    regions.get(find(randrow,randpos)).riverSeeded=true;
                }
            }
        }

        if (mountainSideAlpha==2)
        {
            int riveramount=rand.nextInt(2)+1;
            riverDirection=3;
            for (int x = 0 ; x < riveramount;x++)
            {
                int randrow=rand.nextInt(5)+5;
                int randpos=rand.nextInt(3);
                if (regions.get(find(randrow,randpos)).mainFeature.equals(""))
                {
                    regions.get(find(randrow,randpos)).mainFeature="river";
                    regions.get(find(randrow,randpos)).features.add("river");
                    regions.get(find(randrow,randpos)).b2=riverico;
                    regions.get(find(randrow,randpos)).riverSeeded=true;
                }
            }
        }

        if (mountainSideAlpha==1)
        {
            int riveramount=rand.nextInt(2)+1;
            riverDirection=4;
            for (int x = 0 ; x < riveramount;x++)
            {
                int randrow=rand.nextInt(5);
                int randpos=rand.nextInt(3);
                if (regions.get(find(randrow,randpos)).mainFeature.equals(""))
                {
                    regions.get(find(randrow,randpos)).mainFeature="river";
                    regions.get(find(randrow,randpos)).features.add("river");
                    regions.get(find(randrow,randpos)).b2=riverico;
                    regions.get(find(randrow,randpos)).riverSeeded=true;
                }
            }
        }

        //Generates rivers from initial seeds.

        while (true)
        {
            int changes = 0;
            for (Region reg:regions)
            {
                if (reg.mainFeature.equals("river")&(reg.riverSeeded))
                {
                    int random = rand.nextInt(100);
                    if (random>80)
                    {
                        if (riverDirection==1)
                        {
                            if (reg._1!=-1)
                            {
                                if (!regions.get(reg._1).biome.equals("ocean")){
                                regions.get(reg._1).mainFeature="river";
                                regions.get(reg._1).c3=riverico;
                                regions.get(reg._1).b2=riverico;
                                regions.get(reg._1).features.add("river");
                                reg.riverSeeded=false;
                                changes++;}
                            }
                            reg.a1=riverico;
                        }
                        if (riverDirection==2)
                        {
                            if (reg._7!=-1)
                            {
                                if (!regions.get(reg._7).biome.equals("ocean")){
                                regions.get(reg._7).mainFeature="river";
                                regions.get(reg._7).a3=riverico;
                                regions.get(reg._7).b2=riverico;
                                regions.get(reg._7).features.add("river");
                                reg.riverSeeded=false;
                                changes++;}
                            }
                            reg.c1=riverico;
                        }
                        if (riverDirection==3)
                        {
                            if (reg._3!=-1)
                            {
                                if (!regions.get(reg._3).biome.equals("ocean")){
                                regions.get(reg._3).mainFeature="river";
                                regions.get(reg._3).c1=riverico;
                                regions.get(reg._3).b2=riverico;
                                regions.get(reg._3).features.add("river");
                                reg.riverSeeded=false;
                                changes++;}
                            }
                            reg.a3=riverico;
                        }
                        if (riverDirection==4)
                        {
                            if (reg._5!=-1)
                            {
                                if (!regions.get(reg._5).biome.equals("ocean")){
                                regions.get(reg._5).mainFeature="river";
                                regions.get(reg._5).a1=riverico;
                                regions.get(reg._5).b2=riverico;
                                regions.get(reg._5).features.add("river");
                                reg.riverSeeded=false;
                                changes++;}
                            }
                            reg.c3=riverico;
                        }
                    }
                    else
                        {
                            if (riverDirection==1)
                            {
                                if (reg._8!=-1)
                                {
                                    if (!regions.get(reg._8).biome.equals("ocean")){
                                    regions.get(reg._8).mainFeature="river";
                                    regions.get(reg._8).features.add("river");
                                    reg.riverSeeded=false;
                                    regions.get(reg._8).b3=riverico;
                                    regions.get(reg._8).b2=riverico;
                                    changes++;}
                                }
                                reg.b1=riverico;
                            }
                            if (riverDirection==2)
                            {
                                if (reg._8!=-1)
                                {
                                    if (!regions.get(reg._8).biome.equals("ocean")){
                                    regions.get(reg._8).mainFeature="river";
                                    regions.get(reg._8).features.add("river");
                                    reg.riverSeeded=false;
                                    regions.get(reg._8).b3=riverico;
                                    regions.get(reg._8).b2=riverico;
                                    changes++;}
                                }
                                reg.b1=riverico;
                            }
                            if (riverDirection==3)
                            {
                                if (reg._4!=-1)
                                {
                                    if (!regions.get(reg._4).biome.equals("ocean")){
                                    regions.get(reg._4).mainFeature="river";
                                    regions.get(reg._4).features.add("river");
                                    reg.riverSeeded=false;
                                    regions.get(reg._4).b1=riverico;
                                    regions.get(reg._4).b2=riverico;
                                    changes++;}
                                }
                                reg.b3=riverico;
                            }
                            if (riverDirection==4)
                            {
                                if (reg._4!=-1)
                                {
                                    if (!regions.get(reg._4).biome.equals("ocean")){
                                    regions.get(reg._4).mainFeature="river";
                                    regions.get(reg._4).features.add("river");
                                    reg.riverSeeded=false;
                                    regions.get(reg._4).b1=riverico;
                                    regions.get(reg._4).b2=riverico;
                                    changes++;}
                                }
                                reg.b3=riverico;
                            }
                        }
                }
            }
            if (changes==0){break;}
        }

        //Generates random lakes creeks and caves.

        for (Region reg:regions)
        {
            if (reg.mainFeature.equals("")&(rand.nextInt(100)>94)&
                    (reg.biome.equals("plains")||reg.biome.equals("forest")||reg.biome.equals("jungle")||reg.biome.equals("alpine")))
            {
                reg.mainFeature="lake";
                reg.features.add("lake");
                reg.b2="\uD83C\uDF00";
            }
        }
        for (Region reg:regions)
        {
            if (reg.mainFeature.equals("")&(rand.nextInt(100)>80)&
                    (reg.biome.equals("plains")||reg.biome.equals("forest")||reg.biome.equals("jungle")||reg.biome.equals("alpine")))
            {
                reg.features.add("creek");
            }
        }
        for (Region reg:regions)
        {
            if ((rand.nextInt(100)>96)&
                    (reg.biome.equals("plains")||reg.biome.equals("forest")||
                            reg.biome.equals("hills")||reg.biome.equals("mountain")||reg.biome.equals("jungle")
                            ||reg.biome.equals("desert")||reg.biome.equals("alpine")||reg.biome.equals("swamp")))
            {
                reg.features.add("cave");
            }
        }

        for (Region reg:regions)
        {
            if (reg.mainFeature.equals("")&reg.features.contains("cave"))
            {
                reg.b2="\uD83C\uDF59";
            }
        }

        //Determine slot amount

        for (Region reg:regions)
        {
            if (reg.biome.equals("plains")){reg.slots=18+rand.nextInt(10);}
            if (reg.biome.equals("forest")){reg.slots=8+rand.nextInt(10);}
            if (reg.biome.equals("hills")){reg.slots=12+rand.nextInt(10);}
            if (reg.biome.equals("mountain")){reg.slots=2+rand.nextInt(10);}
            if (reg.biome.equals("coast")){reg.slots=12+rand.nextInt(10);}
        }

        //Determine disc cost

        for (Region reg:regions)
        {
            if (reg.biome.equals("plains")){reg.disccost=15+rand.nextInt(10);}
            if (reg.biome.equals("forest")){reg.disccost=15+rand.nextInt(10);}
            if (reg.biome.equals("hills")){reg.disccost=17+rand.nextInt(10);}
            if (reg.biome.equals("mountain")){reg.disccost=25+rand.nextInt(10);}
            if (reg.biome.equals("coast")){reg.disccost=17+rand.nextInt(10);}
            if (reg.biome.equals("ocean")){reg.disccost=21+rand.nextInt(10);}
        }

        //Resource population

        //populateRegions(String name, int lakeB, int lakeBA, int riverB, int riverBA, int creekB, int creekBA,
        //                                int caveB, int caveBA, int forestC, int forestA, int plainsC, int plainsA,
        //                                int mountainC, int mountainA, int hillsC, int hillsA,
        //                                int coastC, int coastA, int oceanC, int oceanA)

        populateRegions("clay", 80, 10,80, 8, 80, 4, 0,
                0, 15, 4, 15, 2,-40,0,-20,1,
                -10,2,-100,0,70,4,75,6,0,
                0,0,0);

        populateRegions("wood", 30, 4,30, 4, 30, 3, 0,
                0, 100, 16, 30, 2,70,8,70,6,
                30,4,-100,0,70,7,90,10,70,
                10,20,3);

        populateRegions("water", 100, 14,100, 12, 100, 8, 0,
                0, 15, 3, 15, 3,15,3,15,3,
                15,1,-100,0,100,6,60,3,15,
                3,5,2);

        populateRegions("obsidian", 5, 2,5, 2, 5, 2, 25,
                5, 5, 2, 5, 2,25,3,25,3,
                5,2,-100,0,5,2,15,4,15,
                2,20,3);

        populateRegions("galena (lead)", 15, 4,15, 4, 8, 3, 35,
                8, 30, 4, 30, 4,30,5,30,4,
                30,2,-100,0,30,4,30,4,30
                ,4,30,4);

        populateRegions("goethite (iron)", 8, 3,8, 3, 6, 2, 30,
                6, 10, 2, 10, 2,20,3,20,3,
                5,1,-100,0,10,2,10,2,10,
                2,15,4);

        populateRegions("chalcopyrite (copper)", 15, 4,15, 4, 8, 3, 35,
                8, 30, 4, 30, 4,30,5,30,4,
                30,2,-100,0,30,4,30,4,30,
                4,30,4);

        populateRegions("sphalerite (zinc)", 15, 4,15, 4, 8, 3, 35,
                8, 30, 4, 30, 4,30,5,30,4,
                30,2,-100,0,30,4,30,4,30,
                4,30,4);

        populateRegions("silica sand", 25, 4,25, 4, 8, 3, 0,
                0, 0, 0, 0, 0,0,0,0,0,
                65,8,-100,0,0,0,0,0,0,
                4,75,8);

        populateRegions("quartz", 8, 3,8, 3, 6, 2, 30,
                4, 2, 2, 2, 2,10,3,10,3,
                5,1,-100,0,2,2,2,2,2,
                2,70,4);

        populateRegions("fish", 90, 8,50, 6, 30, 3, 0,
                0, 0, 0, 0, 0,0,0,0,0,
                0,0,80,6,40,4,0,0,0,
                0,0,0);

        populateRegions("shellfish", 30, 4,25, 2, 15, 1, 0,
                0, 0, 0, 0, 0,0,0,0,0,
                0,0,50,5,20,2,0,0,0,
                0,0,0);

        populateRegions("small game", 25, 3,15, 2, 10, 1, 0,
                0, 30, 2, 10, 2,10,2,10,2,
                0,0,0,0,20,2,35,3,20,
                2,10,2);

        populateRegions("large game", 20, 2,10, 2, 5, 1, 0,
                0, 25, 2, 5, 2,5,2,5,2,
                0,0,0,0,15,2,25,3,15,
                2,5,1);

        discoverResource("clay");
        discoverResource("wood");
        discoverResource("water");
        discoverResource("galena (lead)");
        discoverResource("goethite (iron)");
        discoverResource("chalcopyrite (copper)");
        discoverResource("sphalerite (zinc)");
        discoverResource("obsidian");
        discoverResource("silica sand");
        discoverResource("quartz");
        discoverResource("fish");
        discoverResource("shellfish");
        discoverResource("small game");
        discoverResource("large game");

        regions.get(find(7,15)).b2="\uD83C\uDFEF";
        regions.get(find(7,15)).slots+=10;
    }


     /************************************************************************
     * METHOD: countBiome                                                    *
     * USE: counts the number of region with a give biome                    *
     * INPUT: String (a biome)                                               *
     ************************************************************************/

    public int countBiome(String a)
    {
        int out=0;
        for (Region reg:regions){if (reg.biome.equals(a)){out++;}}
        return out;
    }


     /************************************************************************
     * METHOD: discAll                                                       *
     * USE: sets all regions to discovered                                   *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public void discAll()
    {
        for (Region reg:regions)
        {
            reg.discovered=true;
        }
    }

     /************************************************************************
     * METHOD: discoverable                                                  *
     * USE: sets a certain resource to discovered                            *
     * INPUT: String resource                                                *
     ************************************************************************/

    public void discoverResource(String res)
    {
        for (Region reg:regions)
        {
            if (!reg.discresources.contains(res))
            {
                int amount = 0;
                for (String str:reg.resources)
                {
                    if (str.equals(res))
                    {
                        amount++;
                    }
                }
                if (amount>0)
                {
                    reg.discresources.add(res);
                    reg.discresourcesaman.add(amount);
                }
            }
        }
    }

     /************************************************************************
     * METHOD: discoverable                                                  *
     * USE: populates all regions with a given resource                      *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public void populateRegions(String name, int lakeB, int lakeBA, int riverB, int riverBA, int creekB, int creekBA,
                                int caveB, int caveBA, int forestC, int forestA, int plainsC, int plainsA,
                                int mountainC, int mountainA, int hillsC, int hillsA,
                                int coastC, int coastA, int oceanC, int oceanA, int swampC, int swampA, int jungleC,
                                int jungleA, int alpineC, int alpineA, int desertC, int desertA)
    {
        for (Region reg:regions)
        {
            Random rand = new Random();
            int choice = rand.nextInt(100);
            int amount=0;
            if (reg.biome.equals("forest"))
            {
                if (forestA!=0){amount+=rand.nextInt(forestA);}
                if (reg.mainFeature.equals("river"))
                {
                    choice+=-riverB;
                    amount+=rand.nextInt(riverBA);
                    if (choice<forestC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("lake"))
                {
                    choice+=-lakeB;
                    amount+=rand.nextInt(lakeBA);
                    if (choice<forestC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("creek"))
                {
                    choice+=-creekB;
                    amount+=rand.nextInt(creekBA);
                    if (choice<forestC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.features.contains("cave"))
                {
                    choice+=-caveB;
                    if (caveBA>0){amount+=rand.nextInt(caveBA);}
                    if (choice<forestC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else
                {
                    if (choice<forestC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
            }
            else if (reg.biome.equals("plains"))
            {
                if (plainsA!=0){amount+=rand.nextInt(plainsA);}
                if (reg.mainFeature.equals("river"))
                {
                    choice+=-riverB;
                    amount+=rand.nextInt(riverBA);
                    if (choice<plainsC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("lake"))
                {
                    choice+=-lakeB;
                    amount+=rand.nextInt(lakeBA);
                    if (choice<plainsC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("creek"))
                {
                    choice+=-creekB;
                    amount+=rand.nextInt(creekBA);
                    if (choice<plainsC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.features.contains("cave"))
                {
                    choice+=-caveB;
                    if (caveBA>0){amount+=rand.nextInt(caveBA);}
                    if (choice<plainsC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else
                {
                    if (choice<plainsC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
            }
            else if (reg.biome.equals("mountain"))
            {
                if (mountainA!=0){amount+=rand.nextInt(mountainA);}
                if (reg.mainFeature.equals("river"))
                {
                    choice+=-riverB;
                    amount+=rand.nextInt(riverBA);
                    if (choice<mountainC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("lake"))
                {
                    choice+=-lakeB;
                    amount+=rand.nextInt(lakeBA);
                    if (choice<mountainC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("creek"))
                {
                    choice+=-creekB;
                    amount+=rand.nextInt(creekBA);
                    if (choice<mountainC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.features.contains("cave"))
                {
                    choice+=-caveB;
                    if (caveBA>0){amount+=rand.nextInt(caveBA);}
                    if (choice<mountainC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else
                {
                    if (choice<mountainC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
            }
            else if (reg.biome.equals("hills"))
            {
                if (hillsA!=0){amount+=rand.nextInt(hillsA);}
                if (reg.mainFeature.equals("river"))
                {
                    choice+=-riverB;
                    amount+=rand.nextInt(riverBA);
                    if (choice<hillsC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("lake"))
                {
                    choice+=-lakeB;
                    amount+=rand.nextInt(lakeBA);
                    if (choice<hillsC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("creek"))
                {
                    choice+=-creekB;
                    amount+=rand.nextInt(creekBA);
                    if (choice<hillsC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.features.contains("cave"))
                {
                    choice+=-caveB;
                    if (caveBA>0){amount+=rand.nextInt(caveBA);}
                    if (choice<hillsC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else
                {
                    if (choice<hillsC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
            }
            else if (reg.biome.equals("coast"))
            {
                if (coastA!=0){amount+=rand.nextInt(coastA);}
                if (reg.mainFeature.equals("river"))
                {
                    choice+=-riverB;
                    amount+=rand.nextInt(riverBA);
                    if (choice<coastC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("lake"))
                {
                    choice+=-lakeB;
                    amount+=rand.nextInt(lakeBA);
                    if (choice<coastC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("creek"))
                {
                    choice+=-creekB;
                    amount+=rand.nextInt(creekBA);
                    if (choice<coastC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.features.contains("cave"))
                {
                    choice+=-caveB;
                    if (caveBA>0){amount+=rand.nextInt(caveBA);}
                    if (choice<coastC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else
                {
                    if (coastA!=0){amount+=rand.nextInt(coastA);}
                    if (choice<coastC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
            }
            else if (reg.biome.equals("ocean"))
            {
                if (oceanA!=0){amount+=rand.nextInt(oceanA);}
                if (choice<oceanC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
            }
            else if (reg.biome.equals("jungle"))
            {
                if (jungleA!=0){amount+=rand.nextInt(jungleA);}
                if (reg.mainFeature.equals("river"))
                {
                    choice+=-riverB;
                    amount+=rand.nextInt(riverBA);
                    if (choice<jungleC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("lake"))
                {
                    choice+=-lakeB;
                    amount+=rand.nextInt(lakeBA);
                    if (choice<jungleC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("creek"))
                {
                    choice+=-creekB;
                    amount+=rand.nextInt(creekBA);
                    if (choice<jungleC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.features.contains("cave"))
                {
                    choice+=-caveB;
                    if (caveBA>0){amount+=rand.nextInt(caveBA);}
                    if (choice<jungleC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else
                {
                    if (choice<jungleC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
            }
            else if (reg.biome.equals("desert"))
            {
                if (desertA!=0){amount+=rand.nextInt(desertA);}
                if (reg.mainFeature.equals("river"))
                {
                    choice+=-riverB;
                    amount+=rand.nextInt(riverBA);
                    if (choice<desertC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("lake"))
                {
                    choice+=-lakeB;
                    amount+=rand.nextInt(lakeBA);
                    if (choice<desertC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("creek"))
                {
                    choice+=-creekB;
                    amount+=rand.nextInt(creekBA);
                    if (choice<desertC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.features.contains("cave"))
                {
                    choice+=-caveB;
                    if (caveBA>0){amount+=rand.nextInt(caveBA);}
                    if (choice<desertC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else
                {
                    if (choice<desertC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
            }
            else if (reg.biome.equals("alpine"))
            {
                if (alpineA!=0){amount+=rand.nextInt(alpineA);}
                if (reg.mainFeature.equals("river"))
                {
                    choice+=-riverB;
                    amount+=rand.nextInt(riverBA);
                    if (choice<alpineC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("lake"))
                {
                    choice+=-lakeB;
                    amount+=rand.nextInt(lakeBA);
                    if (choice<alpineC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("creek"))
                {
                    choice+=-creekB;
                    amount+=rand.nextInt(creekBA);
                    if (choice<alpineC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.features.contains("cave"))
                {
                    choice+=-caveB;
                    if (caveBA>0){amount+=rand.nextInt(caveBA);}
                    if (choice<alpineC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else
                {
                    if (choice<alpineC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
            }
            else if (reg.biome.equals("swamp"))
            {
                if (swampA!=0){amount+=rand.nextInt(swampA);}
                if (reg.mainFeature.equals("river"))
                {
                    choice+=-riverB;
                    amount+=rand.nextInt(riverBA);
                    if (choice<swampC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("lake"))
                {
                    choice+=-lakeB;
                    amount+=rand.nextInt(lakeBA);
                    if (choice<swampC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.mainFeature.equals("creek"))
                {
                    choice+=-creekB;
                    amount+=rand.nextInt(creekBA);
                    if (choice<swampC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else if (reg.features.contains("cave"))
                {
                    choice+=-caveB;
                    if (caveBA>0){amount+=rand.nextInt(caveBA);}
                    if (choice<swampC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
                else
                {
                    if (choice<swampC) { for (int x = 0; x<amount;x++) { reg.resources.add(name); } }
                }
            }
        }
    }
}
