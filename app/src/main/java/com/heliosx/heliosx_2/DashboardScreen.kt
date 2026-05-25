package com.heliosx.heliosx_2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heliosx.heliosx_2.ui.theme.*

enum class HeliosScreen {
    Dashboard, Analytics, EventLogs, Settings
}

@Composable
fun DashboardScreen() {
    var currentScreen by remember { mutableStateOf(HeliosScreen.Dashboard) }

    Row(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Sidebar(currentScreen = currentScreen, onScreenSelected = { currentScreen = it })
        Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
            when (currentScreen) {
                HeliosScreen.Dashboard -> DashboardContent()
                HeliosScreen.Analytics -> AnalyticsScreen()
                HeliosScreen.EventLogs -> EventLogsScreen()
                HeliosScreen.Settings -> SettingsScreen()
            }
        }
    }
}

@Composable
fun Sidebar(currentScreen: HeliosScreen, onScreenSelected: (HeliosScreen) -> Unit) {
    Column(
        modifier = Modifier
            .width(260.dp)
            .fillMaxHeight()
            .background(SurfaceContainerLow)
            .border(1.dp, Color.White.copy(alpha = 0.05f))
            .padding(vertical = 24.dp)
    ) {
        // Logo
        Row(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Bolt,
                contentDescription = null,
                tint = PrimaryEmerald,
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                "HELIOS-X",
                style = MaterialTheme.typography.headlineSmall,
                color = PrimaryEmerald,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Nav Items
        NavigationItem("Dashboard", Icons.Default.Dashboard, selected = currentScreen == HeliosScreen.Dashboard, onClick = { onScreenSelected(HeliosScreen.Dashboard) })
        NavigationItem("Analytics", Icons.Default.Insights, selected = currentScreen == HeliosScreen.Analytics, onClick = { onScreenSelected(HeliosScreen.Analytics) })
        NavigationItem("Event Logs", Icons.Default.History, selected = currentScreen == HeliosScreen.EventLogs, onClick = { onScreenSelected(HeliosScreen.EventLogs) })
        NavigationItem("Settings", Icons.Default.Settings, selected = currentScreen == HeliosScreen.Settings, onClick = { onScreenSelected(HeliosScreen.Settings) })

        Spacer(modifier = Modifier.weight(1f))

        // User profile
        UserCard()
    }
}

@Composable
fun NavigationItem(text: String, icon: ImageVector, selected: Boolean = false, onClick: () -> Unit = {}) {
    val backgroundColor = if (selected) PrimaryEmerald.copy(alpha = 0.1f) else Color.Transparent
    val contentColor = if (selected) PrimaryEmerald else OnSurfaceVariant

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = contentColor, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text,
            style = MaterialTheme.typography.bodyMedium,
            color = contentColor,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium
        )
    }
}

@Composable
fun UserCard() {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainerHigh.copy(alpha = 0.5f))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(PrimaryEmerald.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, contentDescription = null, tint = PrimaryEmerald)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text("Admin User", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
            Text("Hub #842", style = TextStyle(fontSize = 10.sp), color = OnSurfaceVariant)
        }
        Icon(Icons.Default.Logout, contentDescription = null, tint = OnSurfaceVariant, modifier = Modifier.size(16.dp))
    }
}

@Composable
fun DashboardContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceDim)
            .padding(32.dp)
    ) {
        Header()
        Spacer(modifier = Modifier.height(32.dp))
        HeroMetric()
        Spacer(modifier = Modifier.height(32.dp))
        StatsGrid()
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                "LIVE SYSTEM STATUS",
                style = MaterialTheme.typography.labelLarge,
                color = OnSurfaceVariant
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(PrimaryEmerald))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Online & Optimizing", color = PrimaryEmerald, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                color = SurfaceContainerHigh,
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(1.dp, Color.White.copy(alpha = 0.05f))
            ) {
                Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Thermostat, contentDescription = null, tint = TertiaryAmber, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("42°C", fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = {}) {
                Icon(Icons.Default.Notifications, contentDescription = null, tint = OnSurfaceVariant)
            }
        }
    }
}

@Composable
fun HeroMetric() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(
                Brush.radialGradient(
                    colors = listOf(PrimaryEmerald.copy(alpha = 0.08f), Color.Transparent),
                    radius = 500f
                )
            )
            .border(1.dp, PrimaryEmerald.copy(alpha = 0.1f), RoundedCornerShape(32.dp))
            .background(SurfaceContainer.copy(alpha = 0.4f))
            .padding(32.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    "CURRENT SYSTEM OUTPUT",
                    style = MaterialTheme.typography.labelLarge,
                    color = OnSurfaceVariant
                )
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        "5.8",
                        style = MaterialTheme.typography.displayLarge,
                        fontSize = 72.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("kW", style = MaterialTheme.typography.displayMedium, color = PrimaryEmerald)
                }
            }

            Row {
                MetricSmall("Peak Today", "6.2 kW", Color.White)
                Spacer(modifier = Modifier.width(32.dp))
                Box(modifier = Modifier.width(1.dp).height(48.dp).background(Color.White.copy(alpha = 0.1f)))
                Spacer(modifier = Modifier.width(32.dp))
                MetricSmall("Grid Export", "0.4 kW", SecondaryBlue)
            }
        }
    }
}

@Composable
fun MetricSmall(label: String, value: String, valueColor: Color) {
    Column(horizontalAlignment = Alignment.End) {
        Text(label.uppercase(), style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp), color = OnSurfaceVariant)
        Text(value, style = MaterialTheme.typography.headlineSmall, color = valueColor)
    }
}

@Composable
fun StatsGrid() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
        StatCard("DAILY YIELD", "24.5 kWh", "+12%", Modifier.weight(1f))
        StatCard("SYSTEM TEMP", "Stable", null, Modifier.weight(1f))
        StatCard("IMPACT", "12.8 kg", "CO2", Modifier.weight(1f))
    }
}

@Composable
fun StatCard(label: String, value: String, tag: String?, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainer)
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
            .padding(20.dp)
    ) {
        Text(label, style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp), color = OnSurfaceVariant)
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            if (tag != null) {
                Surface(
                    color = PrimaryEmerald.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        tag,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold),
                        color = PrimaryEmerald
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 1200, heightDp = 800)
@Composable
fun DashboardPreview() {
    HeliosX_2Theme {
        DashboardScreen()
    }
}
