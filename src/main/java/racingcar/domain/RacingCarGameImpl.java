package racingcar.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import racingcar.config.AppConfig;
import racingcar.factory.CarFactory;
import racingcar.generator.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarGameImpl implements RacingCarGame {
    private final RandomNumberGenerator randomNumberGenerator;
    private final InputView inputView;
    private final OutputView outputView;
    private Cars cars;

    public RacingCarGameImpl(AppConfig appConfig, String[] carNames) {
        randomNumberGenerator = appConfig.getRandomNumberGenerator();
        inputView = appConfig.getInputView();
        outputView = appConfig.getOutputView();
        this.cars = createCars(carNames);
    }

    @Override
    public Cars getCars() {
        return cars;
    }

    @Override
    public Cars createCars(String[] carNames) {
        return new Cars(Arrays.stream(carNames)
                .map(CarFactory::createCar)
                .collect(Collectors.toList()));
    }

    @Override
    public void race() {
        List<Integer> movingNumbers = new ArrayList<>();
        int carsSize = getCarsSize();
        for (int i = 0; i < carsSize; i++) {
            movingNumbers.add(randomNumberGenerator.generator());
        }

        cars.move(movingNumbers);
    }

    @Override
    public int getCarsSize() {
        return getCars().getCars()
                .size();
    }

}
