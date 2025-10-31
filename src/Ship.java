import java.util.*;

public class Ship {
    private int size;
    private boolean horizontal;
    private List<int[]> coordinates;
    private Set<String> hits = new HashSet<>();

    public Ship(int size, boolean horizontal) {
        this.size = size;
        this.horizontal = horizontal;
        this.coordinates = new ArrayList<>();
    }

    public void setCoordinates(List<int[]> coords) {
        this.coordinates = coords;
    }

    public void registerHit(int row, int col) {
        hits.add(row + "," + col);
    }

    public boolean isSunk() {
        return hits.size() == size;
    }

    public List<int[]> getCoordinates() {
        return coordinates;
    }
}
