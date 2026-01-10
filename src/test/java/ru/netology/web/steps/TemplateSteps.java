package ru.netology.web.steps;

import com.codeborne.selenide.Selenide;

import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.VerificationPage;
import ru.netology.web.page.DashBoardPage;
import ru.netology.web.page.TransferPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateSteps {
    private static LoginPage loginPage;
    private static VerificationPage verificationPage;
    private static DashBoardPage dashBoardPage;
    private static TransferPage transferPage;


    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void logInValidationAndGettingDashBoard(String login, String password) {
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
        DataHelper.AuthInfo info = new DataHelper.AuthInfo(login, password);
        verificationPage = loginPage.validLogin(info);
        dashBoardPage = verificationPage.validVerify(String.valueOf(DataHelper.getVerificationCodeFor(info)));
    }

    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void transferFromCardToCard(String amount, String fromCardNumber, int card) {
        transferPage = dashBoardPage.cardSelection(card);
        transferPage.transfer(amount, fromCardNumber);
    }

    @Тогда("баланс его {int} карты из списка на главной странице должен стать {int} рублей")
    public void actualBalance(int card, int expectedBalance) {
        dashBoardPage.renewCardBalances();
        int actualBalance = dashBoardPage.getCardBalance(card);
        assertEquals(expectedBalance, actualBalance);
    }
}
