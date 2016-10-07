package game;

/**
 * Created by nmikutskiy on 02.10.16.
 */
public class Map {

    public static void main(String[] args) {
        Map map = new Map();
        map.generateMap();
    }

    public static final int SIZE_X = 15;
    public static final int SIZE_Y = 15;

    private Place[][] mapField  = new Place[SIZE_X][SIZE_Y];

    public Map createMapInDB(){
        return null;
    }

    public Map getMap(){
        return null;
    }

    public Place getPlace(int X, int Y){
        return null;
    }

    public static void generateTestMap() {
        int[][] mapField  = new int[SIZE_X][SIZE_Y];
        for (int i = SIZE_X; i > 0; i--) {
            for (int j = 1; j <= SIZE_Y; j++) {
                System.out.print(i + "|" + j + "\t");
            }
            System.out.println();
        }
    }

    public void generateMap(){
        for (int i = SIZE_X; i > 0; i--) {
            for (int j = 1; j <= SIZE_Y; j++) {
                mapField[i-1][j-1] = new Place(i, j);
                System.out.print(mapField[i-1][j-1].toString() + "\t");
//                System.out.print(mapField[i-1][j-1].getJson() + "\t");
            }
            System.out.println();
        }
    }

}
