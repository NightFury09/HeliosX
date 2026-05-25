package com.heliosx.heliosx_2

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heliosx.heliosx_2.ui.theme.*

enum class LogStatus { SUCCESS, WARNING, ERROR, INFO }
enum class LogFilter { ALL, ERRORS, WARNINGS, SUCCESS }

data class LogEntry(
    val time: String,
    val date: String,
    val status: LogStatus,
    val title: String,
    val description: String,
    val source: String,
    val latency: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventLogsScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf(LogFilter.ALL) }
    var selectedRows by remember { mutableStateOf(setOf<Int>()) }
    
    val sampleLogs = remember {
        listOf(
            LogEntry(
                time = "14:22:05",
                date = "OCT 24",
                status = LogStatus.SUCCESS,
                title = "Grid Connection Restored",
                description = "External utility grid sync finalized. Phase matching successful at 60.02Hz. System switching from Island Mode to Grid-Tie.",
                source = "INV-01",
                latency = "12ms"
            ),
            LogEntry(
                time = "12:15:00",
                date = "OCT 24",
                status = LogStatus.INFO,
                title = "PV Generation Peak",
                description = "Array reached maximum daily yield of 8.4kW. MPPT tracking optimized for high-noon positioning.",
                source = "ARRAY-A",
                latency = "0ms"
            ),
            LogEntry(
                time = "09:12:44",
                date = "OCT 24",
                status = LogStatus.WARNING,
                title = "Battery Low Warning",
                description = "SOC level dropped below configuration threshold.",
                source = "BAT-A",
                latency = "8ms"
            ),
            LogEntry(
                time = "04:30:12",
                date = "OCT 24",
                status = LogStatus.ERROR,
                title = "Communication Link Failure",
                description = "Lost connection to Sub-Station B (RS485-COM2). Monitoring sensors offline for this node.",
                source = "SUB-B",
                latency = "ERR"
            ),
            LogEntry(
                time = "22:58:31",
                date = "OCT 23",
                status = LogStatus.INFO,
                title = "Firmware v2.4.0 Applied",
                description = "Build 902 successfully installed. Optimizations for low-light MPPT efficiency active.",
                source = "CORE",
                latency = "N/A"
            )
        )
    }
    
    val filteredLogs = remember(searchQuery, selectedFilter) {
        sampleLogs.filter { log ->
            val matchesSearch = log.title.contains(searchQuery, ignoreCase = true) ||
                                log.description.contains(searchQuery, ignoreCase = true) ||
                                log.source.contains(searchQuery, ignoreCase = true)
            
            val matchesFilter = when (selectedFilter) {
                LogFilter.ALL -> true
                LogFilter.ERRORS -> log.status == LogStatus.ERROR
                LogFilter.WARNINGS -> log.status == LogStatus.WARNING
                LogFilter.SUCCESS -> log.status == LogStatus.SUCCESS
            }
            
            matchesSearch && matchesFilter
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceDim)
            .padding(32.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    "System History",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Full audit trail for the Helios-X grid ecosystem.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OnSurfaceVariant
                )
            }
            
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .border(1.dp, Color.White.copy(alpha = 0.05f), CircleShape)
                        .background(SurfaceContainerLow)
                ) {
                    Icon(Icons.Default.Download, contentDescription = null, tint = OnSurfaceVariant)
                }
                IconButton(
                    onClick = { searchQuery = ""; selectedFilter = LogFilter.ALL },
                    modifier = Modifier
                        .border(1.dp, Color.White.copy(alpha = 0.05f), CircleShape)
                        .background(SurfaceContainerLow)
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = null, tint = OnSurfaceVariant)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Filter and Search Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainerLow.copy(alpha = 0.4f))
                .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Search Input
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = SurfaceContainerLow,
                    unfocusedContainerColor = SurfaceContainerLow,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                placeholder = {
                    Text("Search event logs, sources...", style = TextStyle(fontSize = 13.sp), color = OnSurfaceVariant.copy(alpha = 0.6f))
                },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null, tint = OnSurfaceVariant, modifier = Modifier.size(20.dp))
                },
                singleLine = true
            )
            
            // Filter buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // ALL filter
                LogFilterButton(
                    text = "ALL",
                    isSelected = selectedFilter == LogFilter.ALL,
                    onClick = { selectedFilter = LogFilter.ALL },
                    dotColor = null
                )
                // ERRORS filter
                LogFilterButton(
                    text = "ERRORS",
                    isSelected = selectedFilter == LogFilter.ERRORS,
                    onClick = { selectedFilter = LogFilter.ERRORS },
                    dotColor = ErrorRed
                )
                // WARNINGS filter
                LogFilterButton(
                    text = "WARNINGS",
                    isSelected = selectedFilter == LogFilter.WARNINGS,
                    onClick = { selectedFilter = LogFilter.WARNINGS },
                    dotColor = TertiaryAmber
                )
                // SUCCESS filter
                LogFilterButton(
                    text = "SUCCESS",
                    isSelected = selectedFilter == LogFilter.SUCCESS,
                    onClick = { selectedFilter = LogFilter.SUCCESS },
                    dotColor = PrimaryEmerald
                )
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Log Table
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceContainer.copy(alpha = 0.2f))
                .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
        ) {
            // Table Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceContainerHigh.copy(alpha = 0.9f))
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("TIME", modifier = Modifier.width(120.dp), style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp), color = OnSurfaceVariant)
                Text("STAT", modifier = Modifier.width(70.dp), style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp), color = OnSurfaceVariant)
                Text("EVENT & DESCRIPTION", modifier = Modifier.weight(1f), style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp), color = OnSurfaceVariant)
                Text("SOURCE", modifier = Modifier.width(110.dp), style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp), color = OnSurfaceVariant)
                Text("LAT.", modifier = Modifier.width(90.dp), style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp), color = OnSurfaceVariant)
            }
            
            LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f)) {
                itemsIndexed(filteredLogs) { index, log ->
                    val isSelected = selectedRows.contains(index)
                    val bg = if (isSelected) Color.White.copy(alpha = 0.05f) else Color.Transparent
                    
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(bg)
                            .border(1.dp, Color.White.copy(alpha = 0.02f))
                            .clickable {
                                selectedRows = if (isSelected) {
                                    selectedRows - index
                                } else {
                                    selectedRows + index
                                }
                            }
                            .padding(horizontal = 24.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        // Time & Date
                        Column(modifier = Modifier.width(120.dp)) {
                            Text(log.time, style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold), color = if (log.status == LogStatus.SUCCESS) PrimaryEmerald else if (log.status == LogStatus.WARNING) TertiaryAmber else if (log.status == LogStatus.ERROR) ErrorRed else SecondaryBlue)
                            Text(log.date, style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold), color = OnSurfaceVariant)
                        }
                        
                        // Status Icon
                        Box(modifier = Modifier.width(70.dp), contentAlignment = Alignment.Center) {
                            val statusColors = getStatusColors(log.status)
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(statusColors.first)
                                    .border(1.dp, statusColors.second, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = getStatusIcon(log.status),
                                    contentDescription = null,
                                    tint = statusColors.third,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                        
                        // Event & Description
                        Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                            Text(log.title, style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold), color = Color.White)
                            Spacer(modifier = Modifier.height(4.dp))
                            if (log.title.contains("Battery")) {
                                Column {
                                    Box(
                                        modifier = Modifier
                                            .width(200.dp)
                                            .height(6.dp)
                                            .clip(RoundedCornerShape(3.dp))
                                            .background(SurfaceContainerHigh)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .fillMaxWidth(0.148f)
                                                .background(TertiaryAmber)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text("SOC: 14.8%", style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold), color = OnSurfaceVariant)
                                }
                            } else {
                                Text(log.description, style = TextStyle(fontSize = 13.sp, lineHeight = 18.sp), color = OnSurfaceVariant)
                            }
                        }
                        
                        // Source
                        Box(modifier = Modifier.width(110.dp)) {
                            Surface(
                                color = SurfaceContainerHigh,
                                shape = RoundedCornerShape(4.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.05f))
                            ) {
                                Text(
                                    text = log.source,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold),
                                    color = OnSurfaceVariant
                                )
                            }
                        }
                        
                        // Latency
                        Box(modifier = Modifier.width(90.dp), contentAlignment = Alignment.CenterEnd) {
                            Text(
                                text = log.latency,
                                style = TextStyle(fontSize = 13.sp),
                                color = if (log.latency == "ERR") ErrorRed else OnSurfaceVariant
                            )
                        }
                    }
                }
            }
            
            // Bottom Pagination Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceContainerHigh.copy(alpha = 0.4f))
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "5 OF 1,248 EVENTS",
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
                    color = OnSurfaceVariant
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.1f)),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Icon(Icons.Default.History, contentDescription = null, tint = OnSurfaceVariant, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("LAST 24H", style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold), color = OnSurfaceVariant)
                    }
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.ChevronLeft, contentDescription = null, tint = OnSurfaceVariant)
                        }
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(PrimaryEmerald),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("1", style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold), color = OnPrimary)
                        }
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(6.dp))
                                .background(SurfaceContainerLow),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("2", style = TextStyle(fontSize = 14.sp), color = OnSurfaceVariant)
                        }
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(6.dp))
                                .background(SurfaceContainerLow),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("3", style = TextStyle(fontSize = 14.sp), color = OnSurfaceVariant)
                        }
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = OnSurfaceVariant)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LogFilterButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    dotColor: Color?
) {
    val borderCol = if (isSelected) PrimaryEmerald.copy(alpha = 0.4f) else Color.White.copy(alpha = 0.05f)
    val bg = if (isSelected) PrimaryEmerald.copy(alpha = 0.1f) else Color.Transparent
    val textCol = if (isSelected) PrimaryEmerald else OnSurfaceVariant
    
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(bg)
            .border(1.dp, borderCol, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (dotColor != null) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(dotColor)
            )
            Spacer(modifier = Modifier.width(8.dp))
        } else if (text == "ALL") {
            Icon(Icons.Default.FilterList, contentDescription = null, tint = textCol, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
        }
        
        Text(
            text = text,
            style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp),
            color = textCol
        )
    }
}

fun getStatusIcon(status: LogStatus): ImageVector {
    return when (status) {
        LogStatus.SUCCESS -> Icons.Default.Bolt
        LogStatus.WARNING -> Icons.Default.BatteryAlert
        LogStatus.ERROR -> Icons.Default.Warning
        LogStatus.INFO -> Icons.Default.TrendingUp
    }
}

fun getStatusColors(status: LogStatus): Triple<Color, Color, Color> {
    return when (status) {
        LogStatus.SUCCESS -> Triple(PrimaryEmerald.copy(alpha = 0.1f), PrimaryEmerald.copy(alpha = 0.2f), PrimaryEmerald)
        LogStatus.WARNING -> Triple(TertiaryAmber.copy(alpha = 0.1f), TertiaryAmber.copy(alpha = 0.2f), TertiaryAmber)
        LogStatus.ERROR -> Triple(ErrorRed.copy(alpha = 0.1f), ErrorRed.copy(alpha = 0.2f), ErrorRed)
        LogStatus.INFO -> Triple(SecondaryBlue.copy(alpha = 0.1f), SecondaryBlue.copy(alpha = 0.2f), SecondaryBlue)
    }
}
