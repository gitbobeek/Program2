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
        Map<Integer, Integer> floorCountMap = new HashMap<>();

        for (Building building : buildings) {
            floorCountMap.put(building.getFloors(), floorCountMap.getOrDefault(building.getFloors(), 0) + 1);
        }

        System.out.println("Количество зданий по этажности:");
        for (Map.Entry<Integer, Integer> entry : floorCountMap.entrySet()) {
            System.out.println(entry.getKey() + " этажных: " + entry.getValue());
        }
    }
}
