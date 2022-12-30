package racingcar.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = Collections.unmodifiableList(cars);
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Car> getWinner() {
        int maxPosition = getMaxPosition();
        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toList());
    }

    public int getMaxPosition() {
        return cars.stream()
                .max(Comparator.comparingInt(Car::getPosition))
                .orElseThrow(NoSuchElementException::new)
                .getPosition();
    }

    public List<String> getEachCarResults() {
        return cars.stream()
                .map(Car::toString)
                .collect(Collectors.toList());
    }

    public void move(int[] movingNumbers) {
        for (int i = 0; i < movingNumbers.length; i++) {
            cars.get(i)
                    .move(movingNumbers[i]);
        }
    }
}
