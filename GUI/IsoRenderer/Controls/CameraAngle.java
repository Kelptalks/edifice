package GUI.IsoRenderer.Controls;

public enum CameraAngle {
    North(1, 1),
    East(-1, 1),
    South(-1, -1),
    West(1, -1);


    public int xDirection;
    public int yDirection;
    CameraAngle(int xDirection, int yDirection){
        this.xDirection = xDirection;
        this.yDirection = yDirection;
    }
}
