# 📱Sim Slot Manager

[![](https://jitpack.io/v/devmasum69x/SimSlotManager.svg)](https://jitpack.io/#devmasum69x/SimSlotManager)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![Java](https://img.shields.io/badge/Java-8-brightgreen?style=flat&logo=java)](https://www.oracle.com/java/)
[![Android Studio](https://img.shields.io/badge/Android_Studio-2024.2.2-blue?style=flat&logo=android-studio)](https://developer.android.com/studio)





SimSlotManager is a powerful Android library that provides easy management and detection of SIM card slots in Android devices. This library simplifies the process of handling dual SIM functionality and SIM card information in your Android applications.

## ✨Features

- 📱 Detect number of SIM slots available
- 🔄 Get real-time SIM card status
- 📡 Access carrier information
- 📊 Retrieve SIM card details
- 🔐 Handle dual SIM functionality
- 💫 Easy integration

## ⬇️Installation

### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://www.jitpack.io' }
		}
	}
```

### Step 2. Add the dependency

```gradle
dependencies {
    implementation 'com.github.devmasum69x:SimSlotManager:1.0.1'
}
```

## 🚀Usage

Here's a complete example of how to use SimSlotManager in your MainActivity:

1. Initialize the PhoneCallManager:
```java
PhoneCallManager phoneCallManager = new PhoneCallManager(context);
```

2. Get SIM card information:
```java
// Basic SIM info retrieval
List<PhoneCallManager.SimInfo> simInfoList = phoneCallManager.getAvailableSimCards();

// Advanced SIM information example with full implementation
public class SimInfoExample extends AppCompatActivity {
    private PhoneCallManager phoneCallManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim_info);
        
        simInfoTextView = findViewById(R.id.simInfoTextView);
        phoneCallManager = new PhoneCallManager(this);
        displaySimInformation();
    }

    private void displaySimInformation() {
        List<PhoneCallManager.SimInfo> simInfoList = phoneCallManager.getAvailableSimCards();
        
        if (simInfoList.isEmpty()) {
            showSimInfo("No SIM cards available");
            return;
        }

        // Display as formatted text
        StringBuilder plainText = new StringBuilder();
        plainText.append("📱 Available SIM Cards:\n\n");
        
        for (PhoneCallManager.SimInfo simInfo : simInfoList) {
            plainText.append("🔵 SIM ").append(simInfo.getSlotIndex() + 1).append(":\n")
                    .append("   • Display Name: ").append(simInfo.getDisplayName()).append("\n")
                    .append("   • Carrier: ").append(simInfo.getCarrierName()).append("\n")
                    .append("   • Slot Index: ").append(simInfo.getSlotIndex() + 1).append("\n\n");
        }
        
        showSimInfo(plainText.toString());

        // Display as JSON (optional)
        Gson gson = new Gson();
        String jsonOutput = gson.toJson(simInfoList);
        Log.d("SimInfo", "JSON format: " + jsonOutput);
    }

    private void showSimInfo(String simInfo) {
        simInfoTextView.setText(simInfo);
    }
}
```

3. Make a phone call:
```java
phoneCallManager.makePhoneCall("*247#", 0);  // USSD Call using SIM1
phoneCallManager.makePhoneCall("01812345678", 1);  // Normal call using SIM2
```



## 🔓Permissions

Add these permissions to your AndroidManifest.xml:

```xml
 <uses-permission android:name="android.permission.READ_PHONE_STATE" />
 <uses-permission android:name="android.permission.CALL_PHONE" />
 <uses-permission android:name="android.permission.READ_PHONE_NUMBERS" />
```

## 📶SIM Information API

The library provides detailed SIM card information including:
- Display Name
- Carrier Name
- Slot Index
- Subscription ID
- Network Status

## ⛔Error Handling

The library includes comprehensive error handling for:
- Missing permissions
- Invalid SIM states
- Network availability
- Device compatibility


## 👨🏻‍💻Support

For enterprise support and custom development:

**Contact**: Masum Mahmud  
**Email**: [developermasum.help@gmail.com](developermasum.help@gmail.com)  
**Professional Inquiries**: +8801923329579 (WhatsApp/Telegram)
**Project Link**: [https://github.com/devmasum69x/SimSlotManager](https://github.com/devmasum69x/SimSlotManager)


## 📋License

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


