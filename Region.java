import java.util.ArrayList;

public class Region {

    //Attributes
    String biome="plains";
    String mainFeature="";
    String defico="⛆";
    String a1=defico;
    String a2=defico;
    String a3=defico;
    String b1=defico;
    String b2=defico;
    String b3=defico;
    String c1=defico;
    String c2=defico;
    String c3=defico;
    int row;
    int pos;
    int index;
    int slots=0;
    boolean seeded=true;
    boolean riverSeeded=true;
    boolean discovered=false;
    int disccost=0;
    int _1;
    int _2;
    int _3;
    int _4;
    int _5;
    int _6;
    int _7;
    int _8;
    ArrayList<String> features = new ArrayList<>();
    ArrayList<String> resources = new ArrayList<>();
    ArrayList<String> discresources = new ArrayList<>();
    ArrayList<Integer> discresourcesaman = new ArrayList<>();

    public Region(int r, int p, int ind)
    {
        this.row=r;
        this.pos=p;
        this.index=ind;
    }

     /************************************************************************
     * METHOD: setAll                                                        *
     * USE: Sets all display tile to a given string/ico/char                 *
     * INPUT: char/ico/string                                                *
     ************************************************************************/

    public void setAll(String a)
    {
        a1=a;
        a2=a;
        a3=a;
        b1=a;
        b2=a;
        b3=a;
        c1=a;
        c2=a;
        c3=a;
    }

    @Override
    public String toString() {
        if (discovered)
        {
            if (biome.equals("forest")&mainFeature.equals("river"))
            {
                System.out.println("+-----------------------------------------------------------------------------------------------------+\n" +
                        "| X                                                                                                   |\n" +
                        "| XX                                   X                                     X                        |\n" +
                        "|  XX                                 XX                                    XX                        |\n" +
                        "|   X                     X          XX                                   XX                         X|\n" +
                        "|   X                     X        XX                                    XX                         XX|\n" +
                        "|   X            X        X       XX                          X        XX                          XX |\n" +
                        "|    XXXXXXXX XXXX  XX     X    XX   X                       XXXXX XXXX                           XX  |\n" +
                        "|               X   XXXXXXXXX XX     X       X               X X  XX         X                 X XX   |\n" +
                        "|              XX XX        XX       X   X   X              XX X             X      XX         XXX    |\n" +
                        "|             XX  X           XX     X   X   X XXX         XX  X            XX     XX         XX      |\n" +
                        "|             X  XX             XXXX X   X   X    X  X X XXX    X           X    X X         XX       |\n" +
                        "|            X  XX                   X   X   X                  XXXX       XX    X XXX XXXXXX         |\n" +
                        "|           XX X X                   X   X  X                        X XXXXX     X X                  |\n" +
                        "|          XX   XX                  XX   X  X                              X    X  X                  |\n" +
                        "|         XX  XX X                  X    X  X                             X    X  XX                  |\n" +
                        "|       XX   X   X                  X   X   X                            XX    X  X                   |\n" +
                        "|      XX    X                     XX   X   X                           XX    X  XX                   |\n" +
                        "|XXX   X                          XX    X   XX                         XX    XX  X                    |\n" +
                        "|  XXXXXXXXXXXXXXXXX            XXX    XX    X                        XX         X                    |\n" +
                        "|                   XXXXXX     XX      X     XXXX                    XX     X    X                    |\n" +
                        "|  XXXXX                  XXXXX                 XX                  XX    XX     X                    |\n" +
                        "|       XXXXXXX   XX XXX X     XXXXXX                             XXX    XX       X                   |\n" +
                        "|   X         XXXXX       XX  XX     XXXXXXXXXXXXXXXXXXXX        XX               X                   |\n" +
                        "|    XX XX X      XXXXXX        XX                       XXXXXXXXXXXXXXXXXX       X                   |\n" +
                        "|XXXXXX       X X XX    XX XXXXXXX      XXX X X  X X X XXXXX               XXXXXXXX                   |\n" +
                        "|     XXXXXXXX       X X          XXXXXXXXX XXX             XXXXXXXXXX            XXXXXXXXX           |\n" +
                        "|            XXXXXXX     XXX        XX XX     XXXXX X XXXXXXXXXXXXXXX X XXXXX              XXXXXXXX   |\n" +
                        "|                  XXXX       X XX X                                       XXXX XXXXX X            XXX|\n" +
                        "+-----------------------------------------------------------------------------------------------------+\n");
            }
            else if (biome.equals("forest")&mainFeature.equals("lake"))
            {
                System.out.println("+-----------------------------------------------------------------------------------------------------+\n" +
                        "|                                                                                                     |\n" +
                        "|                                                                                                     |\n" +
                        "|                       X                                                                             |\n" +
                        "|     X                 X                              X                                              |\n" +
                        "|  XXXX                  XX                           X X                                             |\n" +
                        "|  XX                      XXXX                      X  X                                          XX |\n" +
                        "|   X        X XX             X             X        X   XX X  X                                  X   |\n" +
                        "|   XX  XX X              X   X             XXX    XX        XXX            X                    XX   |\n" +
                        "|    XXXX                 XXXX  X          X    XXXX            XX         X    X       XX       X    |\n" +
                        "|     X                   X     X        XX                      XX        X    X       XXXX X XX     |\n" +
                        "|      X                 X      X   XXXXXX                         X XX    X    XXX    XX             |\n" +
                        "|       X      X       XX       X X X                                  XXXXX    X  XXXX               |\n" +
                        "|       XXX    X XX X           X X  X                                    X   X X                     |\n" +
                        "|         X  XX                 X    XX                                  XX   X  X                    |\n" +
                        "|        X  X X                XX     X                                 XX    X  X                    |\n" +
                        "|        X  X X               X                XXX                     XX     X   X                   |\n" +
                        "|       XX  X X                XXXXXXXXX XXXXXX   XXXXXXXXXXXXXXXXX  XX            X                  |\n" +
                        "|     XXX     X         XXXXXXX                                    X XX XXXX XX    X                  |\n" +
                        "|              XXXXXXXXX                                                      XXXXXXX                 |\n" +
                        "|        XXXXXXX                                      X   XXX                        XXXXXXX          |\n" +
                        "|XXXXXXXXX                                             XXXX  XXX                            XXXXXX    |\n" +
                        "|                                       XX                                                       XXXXX|\n" +
                        "|                                 XX  XXXXXXXXX XXXXX              XX                                 |\n" +
                        "|         XXXX      XX              XXX        X              XX XX  XXX                              |\n" +
                        "|   XXXXXXX  XXXXXXX                                           XX       XXXXX                 X       |\n" +
                        "|                                                                                        XXXXXXXXXXXXX|\n" +
                        "|                                                                                      XX             |\n" +
                        "|                                                                                                     |\n" +
                        "+-----------------------------------------------------------------------------------------------------+\n");
            }
            else if (biome.equals("plains")&mainFeature.equals("river"))
            {
                System.out.println("+-----------------------------------------------------------------------------------------------------+\n" +
                        "|                                                                                                     |\n" +
                        "|                                                                                                     |\n" +
                        "|           XXXXXXXXXXX                                                                               |\n" +
                        "|          XX         XX                                                                              |\n" +
                        "|         X            XX                                                                             |\n" +
                        "|         X             X                                                                             |\n" +
                        "|         X            XX                                                                             |\n" +
                        "|          XX       XXX                                                                               |\n" +
                        "|           XXXXXXXXX                                                                                 |\n" +
                        "|                                                                                         XXXXXXXXXXXX|\n" +
                        "|                                                     XXXXXXXXXX XX XXXXXXXXXXXXX XXXXX XX            |\n" +
                        "|                                   XXXXXXXXXXXXXXXXXX                                                |\n" +
                        "|                          XXXXXXXXX                                                                  |\n" +
                        "| XXXXXXXXXXXXXXXXXXX XXXXXX                                                                          |\n" +
                        "|                                                                                                     |\n" +
                        "|                                                                                                     |\n" +
                        "|                                                                                                     |\n" +
                        "|                                                                                                  XXX|\n" +
                        "|                                                                                           XXXXXXX   |\n" +
                        "|                                                                                    XXXXXXXX         |\n" +
                        "|                                                            XXXXXX XXXXXXXXXXXX X XX                 |\n" +
                        "|                                               XXXXXXXXXXXXX                                XXX      |\n" +
                        "|                                         XXXXXXX                                  XX      XX         |\n" +
                        "|                           XXXXXXXXXXXXXXX          XXX                   XXXXXXXXX                  |\n" +
                        "|         XXXXXXXXXXXXXXXXXX                    X XX        XXX X    XX XXX                        XXX|\n" +
                        "|XXXXXXXXX                              X   XXX      XXXXXX                          XXXXXXXXXXXXXXX  |\n" +
                        "|                X XX X XX    XXXX X  XX                            XXXXXXXXXXXXXXXXXX                |\n" +
                        "|              XX                                              XXXXXX                                 |\n" +
                        "+-----------------------------------------------------------------------------------------------------+\n");
            }
            else if (biome.equals("plains")&mainFeature.equals("lake"))
            {
                System.out.println("+-----------------------------------------------------------------------------------------------------+\n" +
                        "|                                                                                                     |\n" +
                        "|                                                                                                     |\n" +
                        "|                            XXXXXXXXXXX                                                              |\n" +
                        "|                           XX         XX                                                             |\n" +
                        "|                          X            X                                                             |\n" +
                        "|                          X           XX                                                             |\n" +
                        "|                          XXX     XXXX                                                               |\n" +
                        "|                             XXXXX                                                                   |\n" +
                        "|                                                                                                     |\n" +
                        "|                                                                                                     |\n" +
                        "|                                                                                                     |\n" +
                        "|                                                       XXXXXXXXXXX XX X                              |\n" +
                        "|   XXXXXXXXXXXXXXXXXXXXXX XXXXXXXXXXX X X X XXXXXXXXX X                 XX XXXX                      |\n" +
                        "|XXX                                                                           XXX XXXXXXXXXXXXXXXXXXX|\n" +
                        "|                                                                                                     |\n" +
                        "|                                                                                                     |\n" +
                        "|                                                                                                     |\n" +
                        "|                                                                                                     |\n" +
                        "|                                                                                                     |\n" +
                        "|                                          XXXXXX XXXX X XXXXXX                                       |\n" +
                        "|                              XXXXXXX XXXXX                  XXXXXXXXXXXXXXXXXXXXXXX                 |\n" +
                        "|                   XXXXXXXXXX                                                       XXXXX            |\n" +
                        "|            XXXXXXX                                                                     XXXX         |\n" +
                        "|      XXXXXXX                                                                               XXX      |\n" +
                        "|XXXXXX                                                                                         XXXX  |\n" +
                        "|                    XXXXXXXXXXXXXXX        XXXXX         XXXX    XXXXXXXX             XXX          XX|\n" +
                        "|                  XX                       X   X            XXXXXX                   XX  XXXXXXX     |\n" +
                        "|                                                                                                     |\n" +
                        "+-----------------------------------------------------------------------------------------------------+\n");
            }
            else if (biome.equals("forest"))
            {
                System.out.println("+-----------------------------------------------------------------------------------------------------+\n" +
                        "| XX                                    X     XXXXX               XX                           XX     |\n" +
                        "|  XXXXXX     XXX                       XX  XXX    XX               XXX                          XXX  |\n" +
                        "|    X     XXXXX               X     X   X          XX                X                            X  |\n" +
                        "|     XX  XX  X               X      X   X  X        X       XX      X             X               X  |\n" +
                        "|      XXX    X           X  X       X  X   X        X     XX   X   X              XX              X  |\n" +
                        "|     XX      X XXXXX  X         X X  X     X       XX     X    XXXX                XXX         XXX   |\n" +
                        "|    X X      X    X          X         X  X       XX      X  XXX         X         X X       XXX     |\n" +
                        "|   X   XX     X X XXX XX   X    XX XX  X XX    XXX         X   X         X       XXX  X   XXXX    X  |\n" +
                        "|  XX  XXXXXXX     X XX            XX XXX X   XX            X             XXXXX X     XXXXX   X    X  |\n" +
                        "|  X   X          X   X         XXX   X   XXXX               XXX        X             X X     X    X  |\n" +
                        "|  X   X          X    X             XXXXX      X   X    X    XXX X     X  X          X XX     X   X  |\n" +
                        "|  XX  XX        XX    XXX          X    XX   X X   XX   X  XX     XX   X  X         XX  XXXXXXX   X  |\n" +
                        "|   XXX  XX XXXXXX     X  XX      XX X    X XXX X  XX XX XX             X   X       X          X   X  |\n" +
                        "|     X  X      XX  X  XXXXXX X X   XX    X    XX  X      XX        XX  X   X XXXXXX           X   X  |\n" +
                        "|     X  X      X   X  X            X    XX    X   X        XXXXX XXX  XX   X                 XX   X  |\n" +
                        "|    XX X       X  X   X            X   XX     X   X                   X  X X                 X    X  |\n" +
                        "|    X  X       X  X   X            X   X      X   X                   X  X X                 X    X  |\n" +
                        "|    X XX      X  XX   X           X   X      X    X                  X  XX X                 X    X  |\n" +
                        "|   X  X       X  X    X          XX  XX      X   XX                  X  X  X                X     X  |\n" +
                        "|   X  X      X   X   XX          X  XXX     X    XXX                 X  X  X               XX  X  X  |\n" +
                        "|  XX  X      X   X   X          XX  X X     X   XX X                 X XX XX               X  X   X  |\n" +
                        "|  X   X     XX   X    XX        X  XX X    X    X  X                 X X  X               XX  X   X  |\n" +
                        "| XX   X     X   XX     XXX    XX   X  X   XX   XX  X               XX  X  XX             XX   X   X  |\n" +
                        "| X    X    XX   X        XX  XXXXXXX  X  XX    X   XX              X   X   XX           XX    X   XX |\n" +
                        "|XXXXXXX   XX       XXXXXXXXXXXXXXXXXXXXXXXXXXX      XX            X    X    XX XXXXXXXXXXXXXXXXXX  X |\n" +
                        "|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX    X         XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|\n" +
                        "|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|\n" +
                        "|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|\n" +
                        "+-----------------------------------------------------------------------------------------------------+\n");
            }
            else if (biome.equals("mountain"))
            {
                System.out.println("+-----------------------------------------------------------------------------------------------------+\n" +
                        "|                                                                                                     |\n" +
                        "|                      XX                                                                             |\n" +
                        "|                    XXX XX         XXXXXX                                             XXXXX          |\n" +
                        "|              XXXXXX     XX       XX    XX                                           XX   XX         |\n" +
                        "|            XX         XXXXXX     XX    XX                                          XX     XX        |\n" +
                        "|           XX        XXX    XX     XXXXXX           XXXXX                          XXXXXXXX X        |\n" +
                        "|        XXXXXXXXXXXXXX       X                    XXX   XXXX                      X       XXXX       |\n" +
                        "|       XX                    XX                 XXX        XXX                   XX           X      |\n" +
                        "|      XX                      XXX             XXXXXXXX       XX              XXXX             XX   XX|\n" +
                        "|    XX                          XX          XXX      XXXXXXXXXX            XXX                 XX XX |\n" +
                        "|  XX                             XX       XXX                  XX       XXXX                    XXX  |\n" +
                        "|XXX                                X    XXX                     XX    XXX                        X   |\n" +
                        "|                                   X XXXX                        XXXXX                            XX |\n" +
                        "|                                    XX                               X                             X |\n" +
                        "|                                    XX                               XX                            X |\n" +
                        "|                                      XX                              XX                           XX|\n" +
                        "|           XXXXXXX                     XX                               XX                         XX|\n" +
                        "|         XX XX   XX X                    XX                              XX                         X|\n" +
                        "|  X  XX    X X XX   XX XXX                XX                              XX        XXX              |\n" +
                        "|  X  X  X   XXXX   X  X X  XX  X       X   XX  X XX      X    X            XX     XXX XX   X         |\n" +
                        "|  X  X X XXX   X   XX X X   X    X  X  X     X XX X    XXX    X X   X XX     XXX  XXX  XX  XX XXXX   |\n" +
                        "|              XX    XX  X   XX  XX   X XX    X  X  X    XXX   X XX  XX XX    XX   XXX   X  XXXX XX   |\n" +
                        "|              XX                 XXXXX              XX   XX                                      X   |\n" +
                        "|    X X  X   XXXX X  X       XX             X       XXX         X       XX   X        X     X     X  |\n" +
                        "|     XXXXXXXXXXXXX  XXXX XXXXX XX      XXXXXX   XXXX   XX   XXXX X XX  XXX     XXXXX XX  XXXX X    X |\n" +
                        "|  XX X XX XX    X  X        XX    X    X XXXXXXX   XX X X XXX   XXXXXX XXXX XXX        XX XX XXX   X |\n" +
                        "|  XX           X   XX          XXXX XXX  XXX         X                     XX                X X   X |\n" +
                        "|                    X                                                                             X  |\n" +
                        "+-----------------------------------------------------------------------------------------------------+\n");
            }
            else
                {
                    System.out.println("+-----------------------------------------------------------------------------------------------------+\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "|                                                                                                     |\n" +
                            "+-----------------------------------------------------------------------------------------------------+\n");
                }

            return "";
        }
        else
        {
            System.out.println("+-----------------------------------------------------------------------------------------------------+\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "|                                                                                                     |\n" +
                    "+-----------------------------------------------------------------------------------------------------+\n");
            return "";
        }
    }

     /************************************************************************
     * METHOD: line1 line2 line3                                             *
     * USE: returns display tiles of their listed line                       *
     * INPUT: N/A                                                            *
     ************************************************************************/

    public String line1()
    {
        if (discovered)
        {
            return " "+a1+a2+a3+" ";
        }
        else{return " \uD83C\uDF2B\uD83C\uDF2B\uD83C\uDF2B ";}

    }
    public String line2()
    {
        if (discovered)
        {
            return " "+b1+b2+b3+" ";
        }
        else{return " \uD83C\uDF2B\uD83C\uDF2B\uD83C\uDF2B ";}
    }
    public String line3()
    {
        if (discovered)
        {
            return " "+c1+c2+c3+" ";
        }
        else{return " \uD83C\uDF2B\uD83C\uDF2B\uD83C\uDF2B ";}
    }
}
