package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;
import java.util.List;

public class AsIntStream implements IntStream {
    private List<Integer> numbers;

    private AsIntStream(int[] values) {
        numbers = new ArrayList<>();
        for(int number: values)numbers.add(number);
    }

    private AsIntStream(List<Integer> values) {
        numbers = new ArrayList<>();
        numbers.addAll(values);
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Double average() {
        return (double)sum()/(double)count();
    }

    @Override
    public Integer max() {
        int maximum = 0;
        for(int number:numbers)
            if(maximum<number)
                maximum = number;
            return  maximum;
    }

    @Override
    public Integer min() {
        int minimum = Integer.MAX_VALUE;
        for(int number:numbers)
            if(minimum>number)
                minimum = number;
        return  minimum;
    }

    @Override
    public long count() {
        return numbers.size();
        }

    @Override
    public Integer sum() {
        Integer sum = 0;
        for(Integer number:numbers) sum += number;
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        List<Integer> newNumbers = new ArrayList<>();
        for(Integer number:numbers)
            if(predicate.test(number))
                newNumbers.add(number);
            return new AsIntStream(newNumbers);
    }

    @Override
    public void forEach(IntConsumer action) {
        for(int number: numbers){
            action.accept(number);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        List<Integer> newNumbers = new ArrayList<>();
        for(int number: numbers){
            newNumbers.add(mapper.apply(number));
        }
        return new AsIntStream(newNumbers);
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        List<Integer> newNumbers = new ArrayList<>();
        for(int number: numbers){
            for(int streamNumber:func.applyAsIntStream(number).toArray()){
                newNumbers.add(streamNumber);
            }
        }
        return new AsIntStream(newNumbers);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        for(int number: numbers){
            identity = op.apply(identity, number);
        }
        return identity;
    }

    @Override
    public int[] toArray() {
        int[] result = new int[(int)count()];
        for(int i =0; i< count();i++){
            result[i] = numbers.get(i);
        }
        return result;
    }

}
