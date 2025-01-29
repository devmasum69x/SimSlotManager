# SimSlotManager

[![](https://jitpack.io/v/devmasum69x/SimSlotManager.svg)](https://jitpack.io/#devmasum69x/SimSlotManager)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

SimSlotManager is a powerful Android library that provides easy management and detection of SIM card slots in Android devices. This library simplifies the process of handling dual SIM functionality and SIM card information in your Android applications.

## Features

- 📱 Detect number of SIM slots available
- 🔄 Get real-time SIM card status
- 📡 Access carrier information
- 📊 Retrieve SIM card details
- 🔐 Handle dual SIM functionality
- 💫 Easy integration

## Installation

### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2. Add the dependency

```gradle
dependencies {
    implementation 'com.github.devmasum69x:SimSlotManager:latest-version'
}
```

## Usage

Here's a complete example of how to use SimSlotManager in your MainActivity:

```java
public class MainActivity extends AppCompatActivity {
    private SimSlotManager simSlotManager;
    private TextView simInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize SimSlotManager
        simSlotManager = new SimSlotManager(this);
        simInfoTextView = findViewById(R.id.simInfoTextView);
        
        // Check for required permissions
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) 
            != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        } else {
            displaySimInformation();
        }
    }

    private void displaySimInformation() {
        StringBuilder info = new StringBuilder();
        
        // Get number of SIM slots
        int simSlotCount = simSlotManager.getSimSlotCount();
        info.append("Total SIM Slots: ").append(simSlotCount).append("\n\n");
        
        // Check each SIM slot
        for (int i = 0; i < simSlotCount; i++) {
            info.append("SIM Slot ").append(i + 1).append(":\n");
            
            // Check if SIM is present and active
            if (simSlotManager.isSimSlotActive(i)) {
                info.append("Status: Active\n");
                
                // Get carrier name
                String carrierName = simSlotManager.getCarrierName(i);
                info.append("Carrier: ").append(carrierName).append("\n");
                
                // Get phone number if available
                String phoneNumber = simSlotManager.getPhoneNumber(i);
                if (phoneNumber != null && !phoneNumber.isEmpty()) {
                    info.append("Phone Number: ").append(phoneNumber).append("\n");
                }
                
                // Get network type
                String networkType = simSlotManager.getNetworkType(i);
                info.append("Network Type: ").append(networkType).append("\n");
            } else {
                info.append("Status: Inactive\n");
            }
            info.append("\n");
        }
        
        simInfoTextView.setText(info.toString());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, 
                                         int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            displaySimInformation();
        }
    }
}
```

### XML Layout Example
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/simInfoTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="16sp" />

</LinearLayout>
```

## Permissions

Add these permissions to your AndroidManifest.xml:

```xml
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```

## License

```
Copyright 2024 devmasum69x

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## Contact

Dev Masum - [@devmasum69x](https://github.com/devmasum69x)

Project Link: [https://github.com/devmasum69x/SimSlotManager](https://github.com/devmasum69x/SimSlotManager)
