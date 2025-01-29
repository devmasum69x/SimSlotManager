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

Here's a quick example of how to use SimSlotManager:

```kotlin
// Initialize SimSlotManager
val simSlotManager = SimSlotManager(context)

// Get number of SIM slots
val simSlotCount = simSlotManager.getSimSlotCount()

// Check if specific SIM slot is active
val isSimSlot1Active = simSlotManager.isSimSlotActive(0)
```

## Permissions

Add these permissions to your AndroidManifest.xml:

```xml
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```

## Contributing

Contributions are welcome! Feel free to submit issues and enhancement requests.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

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
