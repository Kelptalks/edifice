package World.TerrainGen;

import java.util.Random;

public class PerlinNoiseGen {

    //IDEAS
    //I can use a seed random number to set other seeded random numbers.

    private int maxVectorLength = 5;

    private int[] points = new int[15];

    public PerlinNoiseGen(){
        genRandomVectors();
    }

    public void genRandomVectors(){
        Random random = new Random();
        for (int x = 0; x < points.length; x++){
            points[x] = random.nextInt(-100, 100);
        }
    }

    //generate a 1D octave
    public int[] genOctave(int octave){
        int[] octavePoints = new int[octave * octave];
        
        return null;
    }

    public int[] getPoints(){
        return this.points;
    }

    public void genNoise(){

    }
}
