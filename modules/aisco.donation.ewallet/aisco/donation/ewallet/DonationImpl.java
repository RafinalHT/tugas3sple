package aisco.donation.ewallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import aisco.donation.core.DonationComponent;

public class DonationImpl extends DonationComponent {
    private String walletName;
    private String walletID;
    private static List<DonationImpl> donationList = new ArrayList<>();

    public DonationImpl() {
        super("", "", "", 0, "");
        this.walletName = "";
        this.walletID = "";
    }

    public DonationImpl(String name, String email, String phone, int amount, String paymentMethod, String walletName) {
        super(name, email, phone, amount, paymentMethod);
        this.walletName = walletName;
        this.walletID = getWalletID(phone, walletName);
    }

    private String getWalletID(String phone, String walletName) {
        String walletCode = getWalletCode(walletName);
        String userPhoneLast4 = phone.substring(phone.length() - 4);
        return walletCode + userPhoneLast4 + generateRandomNumber(4);
    }

    public String getWalletID() {
        return walletID;
    }

    private String getWalletCode(String walletName) {
        switch (walletName.toLowerCase()) {
            case "gopay": return "901";
            case "ovo": return "902";
            case "dana": return "903";
            default: return "999";
        }
    }

    private String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public void addDonation() {
        donationList.add(new DonationImpl("Anisa", "anisa@jmail.com", "+62878 6654 3321", 2500000, "E-Wallet", "GoPay"));
        donationList.add(new DonationImpl("Dave", "dave@jmail.com", "+62828 2345 3091", 500000, "E-Wallet", "OVO"));
        donationList.add(new DonationImpl("Edo", "edo@jmail.com", "+62828 2345 3091", 300000, "E-Wallet", "DANA"));
        System.out.println("Donations added via E-Wallet.");
    }

    public void getDonation() {
        System.out.println("Fetching all E-Wallet donations:");
        for (DonationImpl donation : donationList) {
            System.out.println(donation);
        }
    }

    @Override
    public String toString() {
        return "- Donasi " + name + ": Rp" + amount + " via " + walletName + " (Wallet ID: " + walletID + ")";
    }
}
