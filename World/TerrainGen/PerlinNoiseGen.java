package World.TerrainGen;

import java.util.Random;

public class PerlinNoiseGen {

    double[][] vectors;
    public PerlinNoiseGen(){
        setVectors();
    }

    public void setVectors(){
        double inverseRootOf2 = 1.0 / Math.sqrt(2); // This is the inverse of sqrt(2)
        this.vectors = new double[][]{
                {inverseRootOf2, inverseRootOf2},
                {inverseRootOf2, -inverseRootOf2},
                {-inverseRootOf2, inverseRootOf2},
                {-inverseRootOf2, -inverseRootOf2},
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1},
        };
    }

    // Generate random vectors of the same length.
    public double[] generateVector(){
        Random random = new Random();
        //double angle = random.nextDouble() * 2 * Math.PI;
        return vectors[random.nextInt(0, vectors.length - 1)];
    }
}
