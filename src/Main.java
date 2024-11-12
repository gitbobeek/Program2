import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileHandler fileHandler = new FileHandler();

        while (true) {
            System.out.println("Введите путь к файлу (или 'exit' для выхода): ");
            String input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            long startTime = System.currentTimeMillis();
            try {
                List<Building> buildings = fileHandler.loadBuildings(input);
                CityStatistics statistics = new CityStatistics(buildings);

                statistics.displayDuplicateBuildings();
                statistics.displayBuildingFloors();

                long endTime = System.currentTimeMillis();
                System.out.println("Время обработки файла: " + (endTime - startTime) + " мс");

            } catch (Exception e) {
                System.out.println("Ошибка при обработке файла: " + e.getMessage());
            }
        }

        scanner.close();
    }
}