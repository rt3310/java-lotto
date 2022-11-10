package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LottoGame {
    private static final String INVALID_NUMBER_TYPE_REGEX = "\\D";
    private static final int UNIT_OF_MONEY = 1000;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public String inputMoney() {
        String money = Console.readLine();
        validateMoneyType(money);
        validateUnitOf1000(money);
        return money;
    }

    private void validateMoneyType(String money) {
        if (Pattern.compile(INVALID_NUMBER_TYPE_REGEX).matcher(money).find()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY_TYPE.getMessage());
        }
    }

    private void validateUnitOf1000(String money) {
        if (Integer.parseInt(money) % UNIT_OF_MONEY != 0) {
            throw new IllegalArgumentException();
        }
    }

    public void printLottos(List<Lotto> lottos) {
        lottos.forEach(lotto -> printSortedLotto(lotto.getNumbers()));
    }

    public void printSortedLotto(List<Integer> lotto) {
        System.out.println(getSortedByAscend(lotto));
    }

    private List<Integer> getSortedByAscend(List<Integer> lotto) {
        return lotto.stream()
                .sorted(Comparator.comparing(Integer::valueOf))
                .collect(Collectors.toList());
    }

    public List<Integer> inputWinningNumbers() {
        String winningNumbers = Console.readLine();
        return getNumbers(winningNumbers);
    }

    private List<Integer> getNumbers(String winningNumbers) {
        validateNumberCount(winningNumbers);
        return Arrays.stream(winningNumbers.split(","))
                .peek(this::validateNumberType)
                .map(Integer::parseInt)
                .peek(this::validateNumberRange)
                .collect(Collectors.toList());
    }

    private void validateNumberCount(String numbers) {
        if (numbers.split(",").length != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_COUNT.getMessage());
        }
    }

    private void validateNumberType(String number) {
        if (Pattern.compile(INVALID_NUMBER_TYPE_REGEX).matcher(number).find()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_WINNING_NUMBER_TYPE.getMessage());
        }
    }

    private void validateNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_RANGE.getMessage());
        }
    }
}
