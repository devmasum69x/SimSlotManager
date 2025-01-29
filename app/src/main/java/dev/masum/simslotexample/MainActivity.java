package dev.masum.simslotexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import java.util.List;
import dev.masum.simslotmanager.PhoneCallManager;
import androidx.core.app.ActivityCompat;
import com.google.gson.Gson;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSIONS = 101;
    private PhoneCallManager phoneCallManager;
    private String simJson = "";
    private LinearLayout linear1;
    private EditText edittext1;
    private EditText edittext2;
    private Button button1;
    private TextView jsonTextView;
    private TextView textview1;
    private boolean JsonStatus = false;
    private String SimInfoJson;
    private double douleCount = 0;


    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize(_savedInstanceState);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            getSimInfo();
        } else {
            if (checkAndRequestPermissions()) {
                getSimInfo();
            }
        }

    }

    private void initialize(Bundle _savedInstanceState) {
        edittext1 = findViewById(R.id.edittext1);
        edittext2 = findViewById(R.id.edittext2);
        button1 = findViewById(R.id.button1);
        jsonTextView = findViewById(R.id.jsonTextView);
        textview1 = findViewById(R.id.textview1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

                // Create By Masum Mahmud 🇧🇩
                // Paid Work Contract +8801923329579 WhatsApp | Telegram

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    String phoneNumber = edittext1.getText().toString().trim();
                    if (!phoneNumber.isEmpty()) {
                        douleCount = Double.parseDouble(edittext2.getText().toString());
                        int intCount = (int) douleCount;
                        phoneCallManager.makePhoneCall(edittext1.getText().toString(), intCount);

                    } else {
                        Toast.makeText(MainActivity.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (checkAndRequestPermissions()) {

                        String phoneNumber = edittext1.getText().toString().trim();
                        if (!phoneNumber.isEmpty()) {
                            douleCount = Double.parseDouble(edittext2.getText().toString());
                            int intCount = (int) douleCount;
                            phoneCallManager.makePhoneCall(edittext1.getText().toString(), intCount);
                        } else {
                            Toast.makeText(MainActivity.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                        }

                    }


                }

            }
        });
    }


    private void getSimInfo(){

        phoneCallManager = new PhoneCallManager(this);
        List<PhoneCallManager.SimInfo> simInfoList = phoneCallManager.getAvailableSimCards();

        if (simInfoList.isEmpty()) {
            textview1.setText("Sim Load Failed");
        } else {

            if (JsonStatus) {
                if (simInfoList == null || simInfoList.isEmpty()) {
                    Toast.makeText(this, "No SIM cards available", Toast.LENGTH_LONG).show();
                } else {
                    Gson gson = new Gson();
                    String json = gson.toJson(simInfoList);
                    SimInfoJson = json;
                }

                //Make By Masum Mahmud 🇧🇩
                // Paid Work For Contact +8801923329579 Whatapp || Telegram ✅
                jsonTextView.setText(SimInfoJson);
                textview1.setText("Successful");
            }
            else {
                StringBuilder plainText = new StringBuilder();
                plainText.append("🔹 Available SIM Cards:\n\n");
                for (PhoneCallManager.SimInfo simInfo : simInfoList) {
                    plainText.append("🟢 Display Name: ").append(simInfo.getDisplayName()).append("\n")
                            .append("📶 Carrier Name: ").append(simInfo.getCarrierName()).append("\n")
                            .append("📥 Slot Index: ").append(simInfo.getSlotIndex() + 1).append("\n\n");
                }

                jsonTextView.setText(plainText.toString());
                textview1.setText("Successful");
                //Make By Masum Mahmud 🇧🇩
                // Paid Work For Contact +8801923329579 Whatapp || Telegram ✅
            }
        }

    }


    private boolean checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Android 6+
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
                    (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && // Android 8+
                            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED)) {

                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_PHONE_NUMBERS
                }, REQUEST_PERMISSIONS);
                return false;
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            if (allGranted) {

                getSimInfo();

            } else {
                Toast.makeText(this, "Permissions Denied! Enable them in Settings.", Toast.LENGTH_LONG).show();
                openAppSettings();
            }
        }
    }

    private void openAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

}
