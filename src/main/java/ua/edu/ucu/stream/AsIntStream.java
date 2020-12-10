package ua.edu.ucu.stream;

import ua.edu.ucu.
        function.IntBinaryOperator;
import ua.edu.ucu.
        function.IntConsumer;
import ua.edu.ucu.
        function.IntPredicate;
import ua.edu.ucu.
        function.IntToIntStreamFunction;
import ua.edu.ucu.
        function.IntUnaryOperator;

import java.util.ArrayList;
import java.util.List;

public class AsIntStream implements IntStream {
    private final List<Integer> numbers;

    private AsIntStream(int[] values) {
        numbers = new ArrayList < >();
        for (int number: values) {
            numbers.add(number);
        }
    }

    private AsIntStream(List < Integer > values) {
        numbers = new ArrayList < >();
        numbers.addAll(values);
    }

    public static IntStream of(int...values) {
        return new AsIntStream(values);
    }

    @Override
    public double average() {
        return (double) sum() / (double) count();
    }

    @Override
    public int max() {
        int maximum = 0;
        for (int number: numbers)
            if (maximum < number) {
                maximum = number;
            }
        return maximum;
    }

    @Override
    public int min() {
        int minimum = Integer.MAX_VALUE;
        for (int number: numbers) {
            if (minimum > number) {
                minimum = number;
            }
        }
        return minimum;
    }

    @Override
    public long count() {
        return numbers.size();
    }

    @Override
    public int sum() {
        int sum = 0;
        for (int number: numbers) {
            sum += number;
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        List<Integer> newNumbers = new ArrayList < >();
        for (Integer number: numbers) {
            if (predicate.test(number)) {
                newNumbers.add(number);
            }
        }
        return new AsIntStream(newNumbers);
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int number: numbers) {
            action.accept(number);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        List<Integer> newNumbers = new ArrayList < >();
        for (int number: numbers) {
            newNumbers.add(mapper.apply(number));
        }
        return new AsIntStream(newNumbers);
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        List<Integer> newNumbers = new ArrayList < >();
        for (int number: numbers) {
            for (int streamNumber: func.applyAsIntStream(number).toArray()) {
                newNumbers.add(streamNumber);
            }
        }
        return new AsIntStream(newNumbers);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        for (int number: numbers) {
            result = op.apply(result, number);
        }
        return result;
    }

    @Override
    public int[] toArray() {
        int[] result = new int[(int) count()];
        for (int i = 0; i < count(); i++) {
            result[i] = numbers.get(i);
        }
        return result;
    }

}