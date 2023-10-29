package World.TerrainGen;

import java.util.Random;

public class PerlinNoiseGen {

    //IDEAS
    //I can use a seed random number to set other seeded random numbers.

    private int maxVectorLength = 5;

    private int[] points = new int[16]; //needs to be a factor of 2

    public PerlinNoiseGen(){
        genRandomVectors();
    }

    public void genRandomVectors(){
        Random random = new Random();
        for (int x = 0; x < points.length; x++){
            points[x] = random.nextInt(-300, 300);
        }
    }

    //generate a 1D octave
    public int[] genOctave(int octave){
        int[] octavePoints = new int[points.length];


        /* Basically I need set value in an array based off scale.
         * I need to add to an array based on the current octave size
         *
         * The first array will be all the same
         * So fill the entire octave array with the first value in points
         *
         * The second array will be split into 2,
         * So fill the entire octave array with the first value in points
         * up until 8, then fill the next 8 with the other point.
         *
         * The third array is 4, then 8, then 16...
         */
        int scaleFactor = 16;
        double amplitude = 1;

        //cycle through all octaves
        for (int currentOctave = 0; currentOctave < octave; currentOctave++){
            //when a scale factor is 1 point 0
            //when a scale factor is 2 point 0, 8, 15

            System.out.println(scaleFactor);

            //cycle through all points
            for (int x = 0; x < points.length; x++){
                octavePoints[x] += (int) (points[((x/scaleFactor) * scaleFactor)] * amplitude);
                System.out.println((x/scaleFactor));
            }

            scaleFactor = scaleFactor / 2;
            amplitude = amplitude/2;
        }
        return octavePoints;
    }

    public int[] getPoints(){
        return this.points;
    }

    public void genNoise(){

    }
}
