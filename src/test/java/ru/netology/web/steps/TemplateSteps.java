package ru.netology.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.VerificationPage;
import ru.netology.web.page.DashBoardPage;
import ru.netology.web.page.TransferPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateSteps {
    private static LoginPage loginPage;
    private static DashBoardPage dashBoardPage;
    private static VerificationPage verificationPage;
    private static TransferPage transferPage;

    String verificationCode = "12345";
    DataHelper.CardInfo toCard = DataHelper.getFirstCardInfo();
    DataHelper.CardInfo fromCard = DataHelper.getSecondCardInfo();

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void logInValidationAndGettingDashBoard(String login, String password) {
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
        DataHelper.AuthInfo info = new DataHelper.AuthInfo(login, password);
        verificationPage = loginPage.validLogin(info);
        dashBoardPage = verificationPage.validVerify(verificationCode);
    }

    @Когда("пользователь переводит {int} рублей с карты с номером 5559 0000 0000 0002 на свою 1 карту с главной страницы")
    public void transferFromCardToCard(int amount, DataHelper.CardInfo fromCard, DataHelper.CardInfo toCard) {
        transferPage = dashBoardPage.cardSelection(toCard);
        transferPage.transfer(amount, fromCard);
    }

    @Тогда("баланс его 1 карты из списка на главной странице должен стать {int} рублей")
    public void actualBalance(int balance) {
        assertEquals(dashBoardPage.getCardBalance(toCard), balance);
    }
}