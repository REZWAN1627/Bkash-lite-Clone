package com.rex.bkashliteclone.ClassInterface_Fragment;

public interface OnClickedFragment {

    public void EveryPageNavClicked();

    public void MainPageNavClicked();

    public void OnInboxClicked();

    public void OnSendMoneyClicked();

    public void OnCashOutClicked();

    public void OnMobileRechargedClicked();

    public void OnPayBillClicked();

    public void OnSendMoneyNextButtonClicked(String ReceiverNumber);

    public void OnSendMoneyAmountNextButtonClicked(String DataBaseAmount, String Amount, String ReceiverNumber);

    public void OnCashOutNextButtonClicked(String AgentNumber);

    public void OnCashOutAmountNextButtonClicked(String DataBaseAmount, String Amount, String ReceiverNumber);

    public void OnMobileRechargeNextButtonClicked(String ReceiverNumber);

    public void OnMobileRechargeAmountNextButtonClicked(String databaseAmount, String Amount, String receiverNumber);

    public void OnSettingNameChangeClicked();
    public void OnSettingPictureChangeClicked();

}
