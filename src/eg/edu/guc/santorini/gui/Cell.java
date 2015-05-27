package eg.edu.guc.santorini.gui;

import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import eg.edu.guc.santorini.utilities.Location;

@SuppressWarnings("serial")
public class Cell extends Geometry {

    private int column, row, level;

    public Cell(String name, Mesh m) {
        super(name, m);
        level = 0;
    }

    public Cell(int col, int row) {
        column = col;
        this.row = row;
        level = 0;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    public float getDepth() {
        return 0.2f * (level + 1);
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int col) {
        this.column = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Location getLocation() {
        return new Location(row, column);
    }

    public void setLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void incrementLevel() {
        ++level;
    }
}
