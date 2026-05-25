package com.heliosx.heliosx_2

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heliosx.heliosx_2.ui.theme.*

enum class ChargingPriority { SOLAR, GRID, BALANCED }

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    var isAdvanced by remember { mutableStateOf(false) }
    var isBluetoothEnabled by remember { mutableStateOf(true) }
    var isEmailEnabled by remember { mutableStateOf(true) }
    var isPushEnabled by remember { mutableStateOf(true) }
    var dischargeLimit by remember { mutableStateOf(15f) }
    var selectedPriority by remember { mutableStateOf(ChargingPriority.SOLAR) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceDim)
            .padding(32.dp)
    ) {
        // Header Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    "System Settings",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Manage connection protocols and energy optimization parameters.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OnSurfaceVariant
                )
            }
            
            // Global/Advanced selector
            Row(
                modifier = Modifier
                    .background(SurfaceContainerHigh, RoundedCornerShape(8.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(8.dp))
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (!isAdvanced) PrimaryEmerald.copy(alpha = 0.1f) else Color.Transparent)
                        .clickable { isAdvanced = false }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Global",
                        style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold),
                        color = if (!isAdvanced) PrimaryEmerald else OnSurfaceVariant
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (isAdvanced) PrimaryEmerald.copy(alpha = 0.1f) else Color.Transparent)
                        .clickable { isAdvanced = true }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Advanced",
                        style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold),
                        color = if (isAdvanced) PrimaryEmerald else OnSurfaceVariant
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Two columns
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Left Column
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Connection Settings
                SettingsCard(title = "Connection Settings", icon = Icons.Default.Language) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        // Wifi Item
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(44.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(SurfaceContainerHigh),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.Wifi, contentDescription = null, tint = OnSurfaceVariant)
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    Text("Wi-Fi Network", style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold), color = Color.White)
                                    Text("HELIOS_5G_NORTH (Connected)", style = TextStyle(fontSize = 12.sp), color = OnSurfaceVariant)
                                }
                            }
                            Button(
                                onClick = { Toast.makeText(context, "Scanning Wi-Fi...", Toast.LENGTH_SHORT).show() },
                                colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainerHigh),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("CHANGE", style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp), color = Color.White)
                            }
                        }
                        
                        Divider(color = Color.White.copy(alpha = 0.05f))
                        
                        // Bluetooth Item
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(44.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(SurfaceContainerHigh),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.Bluetooth, contentDescription = null, tint = OnSurfaceVariant)
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    Text("Bluetooth Pairing", style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold), color = Color.White)
                                    Text("Visible as HELIOS-X-SRV-092", style = TextStyle(fontSize = 12.sp), color = OnSurfaceVariant)
                                }
                            }
                            Switch(
                                checked = isBluetoothEnabled,
                                onCheckedChange = { isBluetoothEnabled = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = PrimaryEmerald,
                                    uncheckedThumbColor = OnSurfaceVariant,
                                    uncheckedTrackColor = SurfaceContainerHigh
                                )
                            )
                        }
                    }
                }
                
                // Alert Configuration
                SettingsCard(title = "Alert Configuration", icon = Icons.Default.Notifications) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Mail, contentDescription = null, tint = OnSurfaceVariant, modifier = Modifier.size(24.dp))
                                Spacer(modifier = Modifier.width(16.dp))
                                Text("Email Notifications", style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold), color = Color.White)
                            }
                            Switch(
                                checked = isEmailEnabled,
                                onCheckedChange = { isEmailEnabled = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = PrimaryEmerald,
                                    uncheckedThumbColor = OnSurfaceVariant,
                                    uncheckedTrackColor = SurfaceContainerHigh
                                )
                            )
                        }
                        
                        Divider(color = Color.White.copy(alpha = 0.05f))
                        
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.NotificationsActive, contentDescription = null, tint = OnSurfaceVariant, modifier = Modifier.size(24.dp))
                                Spacer(modifier = Modifier.width(16.dp))
                                Text("Push Alerts", style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold), color = Color.White)
                            }
                            Switch(
                                checked = isPushEnabled,
                                onCheckedChange = { isPushEnabled = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = PrimaryEmerald,
                                    uncheckedThumbColor = OnSurfaceVariant,
                                    uncheckedTrackColor = SurfaceContainerHigh
                                )
                            )
                        }
                    }
                }
            }
            
            // Right Column
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Battery Management
                SettingsCard(title = "Battery Management", icon = Icons.Default.BatteryChargingFull) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        // Discharge Limit
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Column {
                                    Text("Discharge Limit", style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold), color = Color.White)
                                    Text("Minimum SOC before grid fallback", style = TextStyle(fontSize = 12.sp), color = OnSurfaceVariant)
                                }
                                Text("${dischargeLimit.toInt()}%", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold), color = PrimaryEmerald)
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Slider(
                                value = dischargeLimit,
                                onValueChange = { dischargeLimit = it },
                                valueRange = 5f..50f,
                                colors = SliderDefaults.colors(
                                    thumbColor = PrimaryEmerald,
                                    activeTrackColor = PrimaryEmerald,
                                    inactiveTrackColor = Color.White.copy(alpha = 0.1f)
                                )
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("5% (MIN)", style = TextStyle(fontSize = 9.sp, fontWeight = FontWeight.Bold), color = OnSurfaceVariant)
                                Text("50% (MAX)", style = TextStyle(fontSize = 9.sp, fontWeight = FontWeight.Bold), color = OnSurfaceVariant)
                            }
                        }
                        
                        // Charging Priority
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text("Charging Priority", style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold), color = Color.White)
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                PriorityItem(
                                    label = "SOLAR",
                                    icon = Icons.Default.Bolt,
                                    selected = selectedPriority == ChargingPriority.SOLAR,
                                    onClick = { selectedPriority = ChargingPriority.SOLAR },
                                    modifier = Modifier.weight(1f)
                                )
                                PriorityItem(
                                    label = "GRID",
                                    icon = Icons.Default.GridView,
                                    selected = selectedPriority == ChargingPriority.GRID,
                                    onClick = { selectedPriority = ChargingPriority.GRID },
                                    modifier = Modifier.weight(1f)
                                )
                                PriorityItem(
                                    label = "BALANCED",
                                    icon = Icons.Default.Bolt,
                                    selected = selectedPriority == ChargingPriority.BALANCED,
                                    onClick = { selectedPriority = ChargingPriority.BALANCED },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Bottom Upgrade Widget
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(PrimaryEmerald.copy(alpha = 0.05f))
                .border(1.dp, PrimaryEmerald.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(PrimaryEmerald.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.SystemUpdate, contentDescription = null, tint = PrimaryEmerald)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("Firmware v2.4.1-Stable", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold), color = Color.White)
                    Text("System is up to date. Last checked today 08:12", style = TextStyle(fontSize = 12.sp), color = OnSurfaceVariant)
                }
            }
            
            Button(
                onClick = { Toast.makeText(context, "Firmware is up-to-date", Toast.LENGTH_SHORT).show() },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryEmerald),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
            ) {
                Text("CHECK FOR UPDATES", style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp), color = OnPrimary)
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Bottom System Info Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InfoCard(title = "SERIAL NUMBER", value = "HX-992-ALPHA-01", modifier = Modifier.weight(1f))
            InfoCard(title = "UPTIME", value = "142D 12H 04M", modifier = Modifier.weight(1f))
            InfoCard(title = "LOCALE", value = "NORTH_REG_A1", modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun SettingsCard(title: String, icon: ImageVector, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainer.copy(alpha = 0.6f))
            .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White.copy(alpha = 0.05f))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = PrimaryEmerald, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                title.uppercase(),
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp),
                color = PrimaryEmerald
            )
        }
        
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Composable
fun PriorityItem(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderCol = if (selected) PrimaryEmerald else Color.White.copy(alpha = 0.05f)
    val bg = if (selected) PrimaryEmerald.copy(alpha = 0.1f) else SurfaceContainerLow
    val color = if (selected) PrimaryEmerald else OnSurfaceVariant
    val opacity = if (selected) 1f else 0.6f
    
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(bg)
            .border(2.dp, borderCol, RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(36.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = label,
            style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp),
            color = color
        )
    }
}

@Composable
fun InfoCard(title: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainer.copy(alpha = 0.6f))
            .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            title,
            style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp),
            color = OnSurfaceVariant
        )
        Text(
            value,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            color = Color.White
        )
    }
}
