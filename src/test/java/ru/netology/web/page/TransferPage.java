package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TransferPage {

    private final SelenideElement amountField = $("[data-test-id='amount'] input");
    private final SelenideElement transferFromField = $("[data-test-id=from] input");
    private final SelenideElement buttonToTransfer = $("[data-test-id=action-transfer]");


    public TransferPage() {
        amountField.should(Condition.visible, Duration.ofSeconds(15));
    }


    public void transfer(String amount, String fromCardNumber) {
        amountField.setValue(amount);
        transferFromField.setValue(fromCardNumber);
        buttonToTransfer.click();
    }
}



