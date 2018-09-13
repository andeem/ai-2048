package Game2048;

import java.util.Random;

/**
 *
 * @author emil
 */
public class StubRandom extends Random{

    int[] numbers;
    int i;
    
    public StubRandom(int... numbers){
        this.numbers = numbers;
        this.i = 0;
    }
    @Override
    public int nextInt(int x){
        if (i < numbers.length) {
            return numbers[i++];
        }
        return 0;
    }
    
    @Override
    public double nextDouble() {
        return 0.5;
    }
    
}
