import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CityStatistics {
    private List<Building> buildings;

    public CityStatistics(List<Building> buildings) {
        this.buildings = buildings;
    }

    public void displayDuplicateBuildings() {
        Map<Building, Integer> buildingCountMap = new HashMap<>();

        for (Building building : buildings) {
            buildingCountMap.put(building, buildingCountMap.getOrDefault(building, 0) + 1);
        }

        System.out.println("Дублирующиеся записи:");
        boolean hasDuplicates = false;

        for (Map.Entry<Building, Integer> entry : buildingCountMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " раз(а)");
                hasDuplicates = true;
            }
        }

        if (!hasDuplicates) {
            System.out.println("Дублирующих записей не найдено.");
        }
    }

    public void displayBuildingFloors() {
        Map<String, Map<Integer, Integer>> cityFloorCountMap = new HashMap<>();

        for (Building building : buildings) {
            String city = building.getCity();
            int floors = building.getFloors();

            Map<Integer, Integer> floorCountMap = cityFloorCountMap
                    .computeIfAbsent(city, k -> new HashMap<>());

            floorCountMap.put(floors, floorCountMap.getOrDefault(floors, 0) + 1);
        }

        for (Map.Entry<String, Map<Integer, Integer>> cityEntry : cityFloorCountMap.entrySet()) {
            String city = cityEntry.getKey();
            Map<Integer, Integer> floorCountMap = cityEntry.getValue();

            System.out.println(city + ":");
            for (Map.Entry<Integer, Integer> entry : floorCountMap.entrySet()) {
                System.out.println(entry.getKey() + " этаж(-ей): " + entry.getValue());
            }
            System.out.println();
        }
    }
}
