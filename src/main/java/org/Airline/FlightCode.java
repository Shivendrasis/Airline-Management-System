package ArrayQuestion;

import java.util.*;

public class FlightCode {

    static class Flight {
        String to;
        int takeOff, landing, cost;

        Flight(String to, int takeOff, int landing, int cost) {
            this.to = to;
            this.takeOff = takeOff;
            this.landing = landing;
            this.cost = cost;
        }
    }

    // Convert time in "HH:MMAm/Pm" format to minutes since midnight (24-hour format)
    private static int convertToMinutes(String time) {
        boolean isPM = time.endsWith("Pm");
        String[] parts = time.substring(0, time.length() - 2).split(":");
        int hours = Integer.parseInt(parts[0]) % 12;
        int minutes = Integer.parseInt(parts[1]);
        if (isPM) hours += 12;
        return hours * 60 + minutes;
    }

    static class City {
        String name;
        int cost;

        City(String name, int cost) {
            this.name = name;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine(); // Consume the newline

        Map<String, List<Flight>> graph = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String from = sc.next();
            String to = sc.next();
            String takeOff = sc.next();
            String landing = sc.next();
            int cost = sc.nextInt();
            sc.nextLine(); // Consume the newline

            int takeOffMinutes = convertToMinutes(takeOff);
            int landingMinutes = convertToMinutes(landing);

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(new Flight(to, takeOffMinutes, landingMinutes, cost));
        }

        String startCity = sc.next();
        String endCity = sc.next();
        String preferredStartTime = sc.next();
        String preferredEndTime = sc.next();

        int startTime = convertToMinutes(preferredStartTime);
        int endTime = convertToMinutes(preferredEndTime);

        // Dijkstra's Algorithm to find the minimum cost path
        PriorityQueue<City> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        Map<String, Integer> minCost = new HashMap<>();
        minCost.put(startCity, 0); // Start with city A and 0 cost
        pq.add(new City(startCity, 0)); // [current cost, current city]

        while (!pq.isEmpty()) {
            City currCity = pq.poll();
            String currentCity = currCity.name;
            int currentCost = currCity.cost;

            if (currentCity.equals(endCity)) {
                System.out.println(currentCost);
                return;
            }

            if (!graph.containsKey(currentCity)) continue;

            for (Flight flight : graph.get(currentCity)) {
                if (flight.takeOff >= startTime && flight.landing <= endTime) {
                    int newCost = currentCost + flight.cost;
                    if (newCost < minCost.getOrDefault(flight.to, Integer.MAX_VALUE)) {
                        minCost.put(flight.to, newCost);
                        pq.add(new City(flight.to, newCost));
                    }
                }
            }
        }

        System.out.println("Impossible");
        sc.close();
    }
}
