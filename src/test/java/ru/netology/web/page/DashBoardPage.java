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
        String text = cards.get(card-1).getText();
        return extractBalance(text);
    }

    public TransferPage cardSelection(int card) {

        cards.get(card-1).find("[data-test-id='action-deposit']").click();
        return new TransferPage();
    }

    public void renewCardBalances() {
        $("[data-test-id=action-reload]").click();
    }
}


