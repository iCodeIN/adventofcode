package _2020.day01;

import _2020.FileReader;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ReportRepair {
    private static final int TARGET = 2020;
    private static final String FILE_LOCATION = "src/_2020/day01/input.txt";

    public static void main(String[] args) throws IOException {
        List<Integer> numbers = FileReader.getFileLines(Paths.get(FILE_LOCATION)).stream()
                .mapToInt(Integer::valueOf)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        // Part one
        int leftIndex = 0;
        int rightIndex = numbers.size() - 1;
        while (leftIndex <= rightIndex) {
            int sum = numbers.get(leftIndex) + numbers.get(rightIndex);
            if (sum == TARGET) {
                System.out.println(numbers.get(leftIndex) * numbers.get(rightIndex));
                break;
            } else if (sum < TARGET) {
                leftIndex++;
            } else {
                rightIndex--;
            }
        }

        // Part two
        for (int i = 0; i < numbers.size() - 2; i++) {
            int firstNumber = numbers.get(i);
            leftIndex = i + 1;
            rightIndex = numbers.size() - 1;
            while (leftIndex <= rightIndex) {
                int sum = firstNumber + numbers.get(leftIndex) + numbers.get(rightIndex);
                if (sum == TARGET) {
                    System.out.println(numbers.get(leftIndex) * numbers.get(rightIndex) * firstNumber);
                    break;
                } else if (sum < TARGET) {
                    leftIndex++;
                } else {
                    rightIndex--;
                }
            }
        }
    }
}
