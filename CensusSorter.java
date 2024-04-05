import java.util.*;
import java.util.stream.Collectors;

public class CensusSorter {
    public static void main(String args[]) {
        Scanner S = new Scanner(System.in);
        System.out.println("Please enter the number of entries");
        int N = S.nextInt();
        S.nextLine(); // Consume the newline character

        System.out.println("Enter the number of Columns: ");
        int M = S.nextInt();
        S.nextLine(); // Consume the newline character

        // Read and store the census data
        List<String[]> censusData = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] entry = new String[M];
            for (int j = 0; j < M; j++) {
                entry[j] = S.nextLine();
            }
            censusData.add(entry);
        }

        // Sort the census data by age and name
        List<String[]> sortedData = new ArrayList<>();
        try {
            sortedData = censusData.stream()
                    .sorted(Comparator.<String[]>comparingInt(entry -> {
                        try {
                            return Integer.parseInt(entry[1]);
                        } catch (NumberFormatException e) {
                            return Integer.MAX_VALUE; // Treat non-integer ages as maximum value
                        }
                    }).thenComparing(entry -> entry[0]))
                    .collect(Collectors.toList());

            // Print the sorted data
            for (String[] entry : sortedData) {
                System.out.println(String.join(",", entry));
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid age format detected.");
        }
    }
}