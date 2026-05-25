# ⚡ HELIOS-X

### *Next-Generation HMI Dashboard Console for Hybrid Inverters*

[![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Android Platform](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/about)
[![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)](https://gradle.org/)

---

**Helios-X** is an industrial-grade **Human Machine Interface (HMI)** dashboard application designed for hybrid solar inverters and grid-tie energy storage units. Optimized for landscape tablet consoles, the application delivers a premium, high-contrast, real-time monitoring and control interface built entirely in **Jetpack Compose**.


---

## 🚀 Key Modules & Features

### 1. Live System Status (Dashboard Console)
*   **Live Power HUD**: Displays the current system output in high-precision kilowatts (kW) using a radial power flow indicator.
*   **Grid Interoperability**: Monitors peak daily yields, grid-export metrics, and real-time battery feeding.
*   **Dynamic Micro-KPIs**: Small-form cards tracking daily yield (kWh), thermal state metrics, and environmental impact (CO₂ savings equivalent).
*   **Status Beacon**: Continuous status indicator reflecting connection state (`Online & Optimizing` vs `Grid Fault / Island Mode`).

### 2. Analytics Control Centre
*   **Custom Chart Engine**: A custom Compose `Canvas`-drawn bezier-curve graph charting **PV Solar Generation vs. Household Load** in real-time.
*   **Granular Metrics**: Tracks Total Yield, Self-Consumption percentages, and Peak Output levels with animated progress metrics.
*   **Weather & AI Assist**: Displays weather forecasts and automated smart maintenance alerts (e.g., advising panel cleanings when cloudy periods degrade efficiency).
*   **Data Exporters**: Download options for hourly energy breakdowns.

### 3. Industrial Event Auditing (Logs Screen)
*   **Full-Audit Trail**: Tracks grid-tie syncs, MPPT performance spikes, voltage drops, and low SOC thresholds.
*   **Interactive Search & Filter**: Instant searching across event logs, hardware components (`INV-01`, `ARRAY-A`, `BAT-A`), and status types (`SUCCESS`, `WARNING`, `ERROR`, `INFO`).
*   **State-Of-Charge Alert Gauge**: Expanding UI rows featuring context-aware sub-information (e.g., specific SoC battery levels during low-power warnings).
*   **Performance Latency Tracker**: Measures the response latencies of each hardware sub-node.

### 4. Hardware Parameters (System Settings)
*   **Connectivity Center**: Manage network status (Wi-Fi 5G/2.4G) and toggles for Bluetooth advertisement protocols.
*   **Smart Battery Limits**: Interactive slider controls to establish Depth of Discharge limits before automatic fallback to utility grid feeding occurs.
*   **Charging Priorities**: Fast switches to prioritize power routing (`SOLAR`, `GRID`, or `BALANCED` modes).
*   **Firmware & Diagnostics**: Dynamic update portal showing build numbers (`v2.4.1-Stable`), uptimes, and serial indicators.

---

## 🎨 HMI Design System & Visual Tokens

The user interface uses a high-contrast dark theme optimized for readability in solar sub-stations, control rooms, and outdoor environments. 

| Token | HSL / Hex Code | Semantics | Application |
| :--- | :--- | :--- | :--- |
| **`PrimaryEmerald`** | `#4EDEA3` | Optimal / Solar Gen | Active power metrics, charging/generation curves |
| **`SecondaryBlue`** | `#ADC6FF` | Utility Grid | Grid export figures, battery level overlays |
| **`TertiaryAmber`** | `#FFFFB95F` | Attention / Warn | Thermal warnings, weather warnings, medium alerts |
| **`ErrorRed`** | `#FFFFB4AB` | Severe / Fault | Disconnect states, hardware failure event alerts |
| **`SurfaceDim`** | `#111415` | Background | Root dashboard scaffold background |
| **`SurfaceContainer`** | `#242729` | Layout Element | Metric cards, lists, settings panels |

---

## 📁 Repository Structure

```files
HeliosX/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/heliosx/heliosx_2/
│   │       │   ├── ui/theme/
│   │       │   │   ├── Color.kt          # Design System Hex Codes
│   │       │   │   ├── Theme.kt          # Material 3 Custom Wrapper
│   │       │   │   └── Type.kt           # Custom Typography & Font Scaling
│   │       │   ├── MainActivity.kt       # Application entry point
│   │       │   ├── DashboardScreen.kt    # Root Layout & Dashboard View
│   │       │   ├── AnalyticsScreen.kt    # Custom Canvas graphing & breakdowns
│   │       │   ├── EventLogsScreen.kt    # Audit logging with filter logic
│   │       │   └── SettingsScreen.kt     # Hardware & communication limits
│   │       └── AndroidManifest.xml       # App declarations & configurations
│   └── build.gradle.kts
├── assets/                               # README Visual Assets
│   ├── screen.png                        # Dashboard layout
│   └── emulator_screen.png               # Tablet portrait/landscape views
├── settings.gradle.kts                   # Project configuration
└── build.gradle.kts                      # Root Gradle build script
```

---

## 🛠️ Build & Installation

### Prerequisites
*   **JDK 17** or higher
*   **Android SDK 34** (or higher)
*   **Gradle 8.2** or higher (configured via Wrapper)

### Quick Start (Build via Command Line)

Clone the repository and build the debug APK:

```bash
# Clone the repository
git clone https://github.com/NightFury09/HeliosX.git
cd HeliosX

# Build the project using Gradle Wrapper
./gradlew assembleDebug
```

After building, the output APK will be generated at:
`app/build/outputs/apk/debug/app-debug.apk`

---

## 📷 Screenshots

### Active Device Emulation
<p align="center">
  <img src="assets/emulator_screen.png" alt="Device Emulator Dashboard View" width="45%" style="border-radius: 8px; border: 1px solid rgba(255,255,255,0.05); margin-right: 15px;" />
  <img src="assets/emulator_screen_nolock.png" alt="Device Emulator Settings View" width="45%" style="border-radius: 8px; border: 1px solid rgba(255,255,255,0.05);" />
</p>
