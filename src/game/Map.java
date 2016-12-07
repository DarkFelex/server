package game;

/**
 * Created by nmikutskiy on 02.10.16.
 */
public class Map {
    public static final int SIZE_X = 3;
    public static final int SIZE_Y = 2;

    private Place[][] mapField = new Place[SIZE_Y][SIZE_X];

    public Map saveMapInDB() {
// TODO: записать в базу сгенерированную карту целиком
        return null;
    }

    public Map getMap() {
        return null;
    }

    public Place getPlace(int X, int Y) {
        return null;
    }

    public static void main(String[] args) {
        Map map = new Map();
        map.createCleanMap();

        map.createVillage(5, 5, "test_user", "new village");
        map.createVillage(5, 6, "test_user", "new village 2");


        System.out.println();
        System.out.println(map.getRegion(5, 5));
        System.out.println(map.getRegion(5, 6));
    }

    public static void generateTestMap() {
        int[][] mapField = new int[SIZE_Y][SIZE_X];
        for (int i = SIZE_Y; i > 0; i--) {
            for (int j = 1; j <= SIZE_X; j++) {
                System.out.print(j + "|" + i + "\t");
            }
            System.out.println();
        }
    }

    public void createCleanMap() {// print map
        for (int i = SIZE_Y; i > 0; i--) {
            for (int j = 1; j <= SIZE_X; j++) {
                mapField[i - 1][j - 1] = new Place(i, j, 0);
//                System.out.print(mapField[i-1][j-1].toString() + "\t");
                System.out.print(mapField[i - 1][j - 1].getJson() + "\t");
            }
            System.out.println();
        }
    }

    public void createVillage(int x, int y, String ownerUser, String villageName) {
        mapField[x - 1][y - 1] = new Place(x, y, 0).enableVillageBuildingOnPlace().addVillageOnPlace(villageName, ownerUser);
    }

    public String getRegion(int x, int y) {//поменять возврат на Place
        return mapField[x - 1][y - 1].getJson();
    }

//    public String getRegion(int x1, int y1, int x2, int y2) {
//        for (x1, x1 <= x2, x1++) {
//            for (y1, y1++, y2) {
//
//            }
//        }
//    }
}
