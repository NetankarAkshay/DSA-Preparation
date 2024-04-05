import java.util.*;
/*
* This Java program uses a ParkingLot class to manage the available spots on each floor and implements the parking logic as described in the problem statement.
The parkVehicle method is called for each vehicle that arrives, and it prints the parking process to the console.
*
* */
class ParkingLot {
    private final Map<String, List<Integer>> availableSpots;

    public ParkingLot(List<String> floors) {
        availableSpots = new HashMap<>();
        for (String floor : floors) {
            availableSpots.put(floor, new ArrayList<>());
        }
    }

    public void parkVehicle(String vehicleType, String manufacturer, String plateNo) {
        boolean foundSpot = false;
        for (Map.Entry<String, List<Integer>> entry : availableSpots.entrySet()) {
            String floor = entry.getKey();
            List<Integer> spots = entry.getValue();
            for (int i = 0; i < spots.size(); i++) {
                String spotType = floor.substring(i, i + 1);
                if ((spotType.equals("S") && vehicleType.equals("MOTORCYCLE")) ||
                        (spotType.equals("M") && vehicleType.equals("SEDAN")) ||
                        (spotType.equals("L") && (vehicleType.equals("SEDAN") || vehicleType.equals("TRUCK")))) {
                    int spotNo = spots.remove(i);
                    System.out.printf("%s %s %s is going to a %s parking spot on floor %s No. %s%n",
                            vehicleType, manufacturer, plateNo, getSpotSize(spotType), floor, spotNo);
                    foundSpot = true;
                    break;
                }
            }
            if (foundSpot) {
                break;
            }
        }
        if (!foundSpot) {
            System.out.printf("There is no available spot for %s %s %s in the parking lot%n",
                    vehicleType, manufacturer, plateNo);
        }
    }

    private String getSpotSize(String spotType) {
        switch (spotType) {
            case "S":
                return "SMALL";
            case "M":
                return "MEDIUM";
            case "L":
                return "LARGE";
            default:
                return "";
        }
    }
}

public class ParkingLotProblem {
    public static void main(String[] args) {
        List<String> floors = Arrays.asList("2", "2SM", "1M");
        List<String> vehicles = Arrays.asList("MOTORCYCLE Yamaha AA-111-AA", "TRUCK MAN RR-322-TT", "SEDAN BMW BB-555-CC");

        ParkingLot parkingLot = new ParkingLot(floors.subList(1, floors.size()));
        vehicles.forEach(vehicle -> {
            String[] parts = vehicle.split(" ");
            parkingLot.parkVehicle(parts[0], parts[1], parts[2]);
        });
    }
}