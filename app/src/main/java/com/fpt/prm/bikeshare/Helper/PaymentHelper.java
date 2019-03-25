package com.fpt.prm.bikeshare.Helper;

public class PaymentHelper {
    public static boolean chargeByPhoneCard(String cardNumber, String cardSerial) {
        // Todo: card validation
        if (cardSerial.length() == 11 && cardNumber.length() == 11) {
            double balance = AppEnvironment.getCurrentUser().getBalance();
            AppEnvironment.getCurrentUser().setBalance(balance + 100);
            return true;
        } else {
            return false;
        }
        // Todo: Upload card to payment service
    }

    public static boolean withdraw(String accountNumber, int amount) {
        double balance = AppEnvironment.getCurrentUser().getBalance();
        if (amount >= 100 && accountNumber.length() <= 11) {
            if (balance > amount) {
                AppEnvironment.getCurrentUser().setBalance(balance + 100);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
