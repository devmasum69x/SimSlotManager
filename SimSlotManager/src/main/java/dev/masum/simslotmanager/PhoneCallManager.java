package dev.masum.simslotmanager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.net.Uri;
import android.content.Intent;
import androidx.core.app.ActivityCompat;
import java.util.List;
import java.util.ArrayList;

public class PhoneCallManager {
    private final Context context;
    private final TelephonyManager telephonyManager;
    private final SubscriptionManager subscriptionManager;
    private final TelecomManager telecomManager;

    public PhoneCallManager(Context context) {
        this.context = context;
        this.telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        this.subscriptionManager = (SubscriptionManager) context.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
        this.telecomManager = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
    }

    @SuppressLint("MissingPermission")
    public List<SimInfo> getAvailableSimCards() {
        List<SimInfo> simInfoList = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                List<SubscriptionInfo> subscriptionInfos = subscriptionManager.getActiveSubscriptionInfoList();
                if (subscriptionInfos != null) {
                    for (SubscriptionInfo info : subscriptionInfos) {
                        simInfoList.add(new SimInfo(
                                info.getSubscriptionId(),
                                info.getDisplayName().toString(),
                                info.getCarrierName().toString(),
                                info.getSimSlotIndex()
                        ));
                    }
                }
            }
        }
        return simInfoList;
    }

    @SuppressLint("MissingPermission")
    public void makePhoneCall(String phoneNumber, int simSlot) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Encode the phone number if it's a USSD code
        String encodedNumber = phoneNumber.startsWith("*") ? Uri.encode(phoneNumber) : phoneNumber;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<PhoneAccountHandle> phoneAccountHandleList = telecomManager.getCallCapablePhoneAccounts();
            if (phoneAccountHandleList != null && phoneAccountHandleList.size() > simSlot) {
                PhoneAccountHandle phoneAccountHandle = phoneAccountHandleList.get(simSlot);
                Intent intent = new Intent(Intent.ACTION_CALL)
                        .setData(Uri.parse("tel:" + encodedNumber))
                        .putExtra(TelecomManager.EXTRA_PHONE_ACCOUNT_HANDLE, phoneAccountHandle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        } else {
            // For Android versions below M (6.0)
            Intent intent = new Intent(Intent.ACTION_CALL)
                    .setData(Uri.parse("tel:" + encodedNumber));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public static class SimInfo {
        private final int subscriptionId;
        private final String displayName;
        private final String carrierName;
        private final int slotIndex;

        public SimInfo(int subscriptionId, String displayName, String carrierName, int slotIndex) {
            this.subscriptionId = subscriptionId;
            this.displayName = displayName;
            this.carrierName = carrierName;
            this.slotIndex = slotIndex;
        }

        public int getSubscriptionId() { return subscriptionId; }
        public String getDisplayName() { return displayName; }
        public String getCarrierName() { return carrierName; }
        public int getSlotIndex() { return slotIndex; }
    }
}