
public class Policy {

    //Attributes
    String name;
    String genre;
    String desc;
    int cost;
    int index=-1;
    int c1;
    int c2;
    int c3;
    int c4;
    int c5;
    int c6;
    int c7;
    int c8;
    int c9;
    int c10;
    boolean known;
    boolean active;

    public Policy(String name, String genre, String desc, int cost, boolean known, boolean active,int c1, int c2,
                  int c3, int c4, int c5,int c6, int c7,
                  int c8, int c9, int c10) {
        this.name=name;
        this.genre=genre;
        this.desc=desc;
        this.cost=cost;
        this.known=known;
        this.active=active;
        this.c1=c1;
        this.c2=c2;
        this.c3=c3;
        this.c4=c4;
        this.c5=c5;
        this.c6=c6;
        this.c7=c7;
        this.c8=c8;
        this.c9=c9;
        this.c10=c10;
    }
}