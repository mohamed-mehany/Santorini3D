package eg.edu.guc.santorini.gui;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Dome;
import eg.edu.guc.santorini.Board;
import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Cube;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.tiles.Pyramid;
import eg.edu.guc.santorini.utilities.Location;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.lwjgl.input.Mouse;

public class Main extends SimpleApplication {

    Box[][] board;
    Cell[][] boardGeom;
    Piece toBeMoved;
    boolean isMoved;
    Board b;
    Player p1, p2;
    Node pivot;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        p1 = new Player("Mark", 2);
        p2 = new Player("Mohamed", 1);
        b = new Board(p1, p2);
        pivot = new Node("pivot");
        rootNode.attachChild(pivot);
        Mouse.setGrabbed(false);
        flyCam.setDragToRotate(true);
        flyCam.setMoveSpeed(20);
        flyCam.setEnabled(false);
        cam.setLocation(new Vector3f(-2, 10, 25));
        cam.setRotation(new Quaternion(0.034821264f, 0.96479154f, -0.19773142f, 0.16990307f));
        board = new Box[5][5];
        boardGeom = new Cell[5][5];
        Box base = new Box(14, 0.1f, 14);
        Geometry baseGeom = new Geometry("Base", base);
        baseGeom.setLocalTranslation(new Vector3f(7, -0.2f, 7));
        Material baseMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        baseMat.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
        baseMat.setColor("Color", ColorRGBA.Gray);
        baseGeom.setMaterial(baseMat);
        pivot.attachChild(baseGeom);
        inputManager.addMapping("Click", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, "Click");
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                board[i][j] = new Box(1, 0.1f, 1);
                boardGeom[i][j] = new Cell("Cell", board[i][j]);
                boardGeom[i][j].setLocation(i, j);
                boardGeom[i][j].setLocalTranslation(new Vector3f(3 * j, 0, 3 * i));
                Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                mat.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
                boardGeom[i][j].setMaterial(mat);
                pivot.attachChild(boardGeom[i][j]);
                if (i == 0 && j == 0) {
                    if (b.getP1().getT1().signature().equals("C")) {
                        Box p11 = new Box(1, 1, 1);
                        Piece geom1 = b.getP1().getT1();
                        geom1.init("Piece", p11);
                        geom1.setLocalTranslation(new Vector3f(3 * j, 1.1f, 3 * i));
                        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                        mat1.setColor("Color", ColorRGBA.Blue);
                        geom1.setMaterial(mat1);
                        pivot.attachChild(geom1);
                    }
                    if (b.getP1().getT1().signature().equals("P")) {
                        Dome p11 = new Dome(Vector3f.ZERO, 2, 4, 1f, false);
                        Piece geom1 = b.getP1().getT1();
                        geom1.init("Piece", p11);
                        geom1.setLocalTranslation(new Vector3f(3 * j, 0.1f, 3 * i));
                        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                        mat1.setColor("Color", ColorRGBA.Blue);
                        geom1.setMaterial(mat1);
                        pivot.attachChild(geom1);
                    }
                }
                if (i == 4 && j == 1) {
                    if (b.getP1().getT2().signature().equals("C")) {
                        Box p12 = new Box(1, 1, 1);
                        Piece geom1 = b.getP1().getT2();
                        geom1.init("Piece", p12);
                        geom1.setLocalTranslation(new Vector3f(3 * j, 1.1f, 3 * i));
                        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                        mat1.setColor("Color", ColorRGBA.Blue);
                        geom1.setMaterial(mat1);
                        pivot.attachChild(geom1);
                    }
                    if (b.getP1().getT2().signature().equals("P")) {
                        Dome p12 = new Dome(Vector3f.ZERO, 2, 4, 1f, false);
                        Piece geom1 = b.getP1().getT2();
                        geom1.init("Piece", p12);
                        geom1.setLocalTranslation(new Vector3f(3 * j, 0.1f, 3 * i));
                        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                        mat1.setColor("Color", ColorRGBA.Blue);
                        geom1.setMaterial(mat1);
                        pivot.attachChild(geom1);
                    }
                }
                if (i == 0 && j == 3) {
                    if (b.getP2().getT1().signature().equals("C")) {
                        Box p21 = new Box(1, 1, 1);
                        Piece geom1 = b.getP2().getT1();
                        geom1.init("Piece", p21);
                        geom1.setLocalTranslation(new Vector3f(3 * j, 1.1f, 3 * i));
                        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                        mat1.setColor("Color", ColorRGBA.Red);
                        geom1.setMaterial(mat1);
                        pivot.attachChild(geom1);
                    }
                    if (b.getP2().getT1().signature().equals("P")) {
                        Dome p21 = new Dome(Vector3f.ZERO, 2, 4, 1f, false);
                        Piece geom1 = b.getP2().getT1();
                        geom1.init("Piece", p21);
                        geom1.setLocalTranslation(new Vector3f(3 * j, 0.1f, 3 * i));
                        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                        mat1.setColor("Color", ColorRGBA.Red);
                        geom1.setMaterial(mat1);
                        pivot.attachChild(geom1);
                    }
                }
                if (i == 4 && j == 4) {
                    if (b.getP2().getT2().signature().equals("C")) {
                        Box p22 = new Box(1, 1, 1);
                        Piece geom1 = b.getP2().getT2();
                        geom1.init("Piece", p22);
                        geom1.setLocalTranslation(new Vector3f(3 * j, 1.1f, 3 * i));
                        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                        mat1.setColor("Color", ColorRGBA.Red);
                        geom1.setMaterial(mat1);
                        pivot.attachChild(geom1);
                    }
                    if (b.getP2().getT2().signature().equals("P")) {
                        Dome p22 = new Dome(Vector3f.ZERO, 2, 4, 1f, false);
                        Piece geom1 = b.getP2().getT2();
                        geom1.init("Piece", p22);
                        geom1.setLocalTranslation(new Vector3f(3 * j, 0.1f, 3 * i));
                        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                        mat1.setColor("Color", ColorRGBA.Red);
                        geom1.setMaterial(mat1);
                        pivot.attachChild(geom1);
                    }
                }
            }
        }
    }
    /**
     * Pick a Target Using the Mouse Pointer. <ol><li>Map "pick target" action
     * to a MouseButtonTrigger. <li>flyCam.setEnabled(false);
     * <li>inputManager.setCursorVisible(true); <li>Implement action in
     * AnalogListener (TODO).</ol>
     */
    public ActionListener actionListener = new ActionListener() {
        public void dehiglight() {
            for (Cell[] row : boardGeom) {
                for (Cell col : row) {
                    Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                    mat.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
                    col.setMaterial(mat);

                }
            }
        }

        public void onAction(String name, boolean isPressed, float tpf) {
            if (name.equals("Click")) {
                // Reset results list.
                CollisionResults results = new CollisionResults();
                // Convert screen click to 3d position
                Vector2f click2d = inputManager.getCursorPosition();
                Vector3f click3d = cam.getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).clone();
                Vector3f dir = cam.getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d);
                // Aim the ray from the clicked spot forwards.
                Ray ray = new Ray(click3d, dir);
                // Collect intersections between ray and all nodes in results list.
                rootNode.collideWith(ray, results);
                // (Print the results so we see what is going on:)
                for (int i = 0; i < results.size(); i++) {
                    // (For each "hit", we know distance, impact point, geometry.)
                    float dist = results.getCollision(i).getDistance();
                    Vector3f pt = results.getCollision(i).getContactPoint();
                    String target = results.getCollision(i).getGeometry().getName();
                    //System.out.println("Selection #" + i + ": " + target + " at " + pt + ", " + dist + " WU away.");
                }
                // Use the results -- we rotate the selected geometry.
                if (results.size() > 0 && !isPressed) {
                    // The closest result is the target that the player picked:
                    Geometry target = results.getClosestCollision().getGeometry();
                    // Here comes the action:
                    if (!isMoved && toBeMoved == null && target.getName().equals("Piece")) {
                        if (b.getTurn().getT1() == (Piece) target || b.getTurn().getT2() == (Piece) target) {
                            toBeMoved = (Piece) target;
                            for (Cell[] row : boardGeom) {
                                for (Cell col : row) {
                                    if (b.canMove(toBeMoved, col.getLocation())) {
                                        col.getMaterial().setColor("Color", ColorRGBA.Gray);
                                    }
                                }
                            }
                        }
                    } else if (!isMoved && toBeMoved != null && target.getName().equals("Cell")) {
                        float y = toBeMoved.getLocalTranslation().y;
                        try {
                            b.move(toBeMoved, ((Cell) target).getLocation());
                            toBeMoved.setLocalTranslation(target.getLocalTranslation());
                            toBeMoved.getLocalTranslation().setY(y);
                            isMoved = true;
                            dehiglight();
                            for (Cell[] row : boardGeom) {
                                for (Cell col : row) {
                                    if (b.canPlace(toBeMoved, col.getLocation())) {
                                        col.getMaterial().setColor("Color", ColorRGBA.Gray);
                                    }
                                }
                            }
                        } catch (InvalidMoveException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    } else if (isMoved && toBeMoved != null && target.getName().equals("Cell")) {
                        try {
                            Cell targetCell = (Cell) target;
                            b.place(toBeMoved, targetCell.getLocation());
                            dehiglight();
                            toBeMoved = null;
                            isMoved = false;
                            if (targetCell.getLevel() == 3) {
                            } else {
                                Box tile = new Box(1, 0.1f, 1);
                                Cell tileGeom = new Cell("Cell", tile);
                                tileGeom.setLevel(targetCell.getLevel());
                                tileGeom.setLocation(targetCell.getColumn(), targetCell.getRow());
                                tileGeom.setLocalTranslation(targetCell.getLocalTranslation());
                                tileGeom.getLocalTranslation().y = targetCell.getLocalTranslation().y + 0.2f;
                                Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                                mat.setColor("Color", ColorRGBA.White);
                                tileGeom.setMaterial(mat);
                                pivot.attachChild(tileGeom);
                                targetCell.incrementLevel();
                                tileGeom.incrementLevel();
                            }
                        } catch (InvalidPlacementException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    }
                }
            }
        }
    };
}
