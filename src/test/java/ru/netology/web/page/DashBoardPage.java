package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashBoardPage {

    private final SelenideElement header = $("[data-test-id=dashboard]");
    // к сожалению, разработчики не дали нам удобного селектора, поэтому так
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement firstCard = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private final SelenideElement secondCard = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private final SelenideElement firstCardChoice = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");
    private final SelenideElement secondCardChoice = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");

    public DashBoardPage() {
        header.should(Condition.visible);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int getCardBalance(int card) {

        String text;
        if (card == 1) {
            text = firstCard.getText();
        } else if (card == 2) {
            text = secondCard.getText();
        } else {
            throw new IllegalArgumentException("Несуществующий номер карты: " + card);
        }
        return extractBalance(text);
    }

    public TransferPage cardSelection(int card) {

        if (card == 1) {
            firstCardChoice.click();
        } else if (card == 2) {
            secondCardChoice.click();
        } else {
            throw new IllegalArgumentException("Несуществующий номер карты: " + card);
        }
        return new TransferPage();
    }

    public void renewCardBalances() {
        $("[data-test-id=action-reload]").click();
    }
}


