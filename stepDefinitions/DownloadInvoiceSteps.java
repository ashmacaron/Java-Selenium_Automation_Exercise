package stepDefinitions;

import actions.DownloadInvoiceActions;
import base.BaseUtil;
import io.cucumber.java.en.*;


public class DownloadInvoiceSteps {

    @When("I proceed to checkout as guest user")
    public void proceedToCheckoutAsGuestUser() {
        BaseUtil.handlePopup();
        DownloadInvoiceActions.proceedToCheckoutAsGuestUser();
    }

    @When("I complete the purchase process")
    public void completePurchaseProcess() {
        DownloadInvoiceActions.completePurchaseProcessWithInvoice();
    }
}