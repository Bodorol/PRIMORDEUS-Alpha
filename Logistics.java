import java.util.*;

public class Logistics extends Global {
    //todo: note: res = 1280x1024
    //We might want to just put call of this in the region module later.

    public Logistics(){
    }

    public boolean isConnected(Region reg1, Region reg2){
        if (reg1.hasRoad && reg2.hasRoad) {
            if(reg1.connectedRegions.contains(reg2)){
                return true;
            }
        }
        return false;
    }

    public ArrayList getNearbyTiles(Region center, int radius){
        int centerRow = center.row;
        int centerPos = center.pos;
        ArrayList<Region> tilesWithinRadius = new ArrayList<Region>();
        for(int i = -radius; i<=radius; i++){
            for(int j = -radius; i<=radius; i++){
                try{
                    Region reg = regions.get(find(centerRow + i, centerPos + j));
                    tilesWithinRadius.add(reg);
                }
                catch(Exception e){}
            }
        }
        return tilesWithinRadius;
    }

    public void connectTiles(Region reg){
        if (reg.hasRoad) {
            ArrayList<Region> nearbyTiles = getNearbyTiles(reg, 1);
            for(Region nearbyReg: nearbyTiles){
                if (nearbyReg.hasRoad){
                    reg.connectedRegions.add(nearbyReg);
                    nearbyReg.connectedRegions.add(reg);
                    for(Region connected: reg.connectedRegions){
                        if(!reg.connectedRegions.contains(connected)){
                            reg.connectedRegions.add(connected);
                        }
                    }
                    for(Region connected2: nearbyReg.connectedRegions){
                        if(!nearbyReg.connectedRegions.contains(connected2)){
                            nearbyReg.connectedRegions.add(connected2);
                        }
                    }
                }
            }
        }
    }

    public void connectMap(){
        //Note: connects all tiles on the map that have a road and are adjacent.
        //Does not connect all tiles regardless of whether they are actually connected or not.
        //Should be run every time a new road is built.
        for(Region reg: regions){
            connectTiles(reg);
        }
    }

    public double getDistance(Region reg1, Region reg2){
        int reg1Row = reg1.row;
        int reg1Pos = reg1.pos;
        int reg2Row = reg2.row;
        int reg2Pos = reg2.pos;
        double distance = -1;
        if(!(reg1 == reg2)){
            distance = Math.sqrt((reg1Row - reg2Row)*(reg1Row - reg2Row) + (reg1Pos - reg2Pos)*(reg1Pos - reg2Pos));
        }
        else if(reg1 == reg2){
            distance = 0;
        }
        return distance;
    }
}
