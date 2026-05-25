package com.heliosx.heliosx_2

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heliosx.heliosx_2.ui.theme.*

data class HourlyBreakdownItem(
    val time: String,
    val pvGen: String,
    val load: String,
    val status: String
)

@Composable
fun AnalyticsScreen() {
    var selectedPeriod by remember { mutableStateOf("Day") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceDim)
            .padding(32.dp)
    ) {
        // Header
        AnalyticsHeader(selectedPeriod, onPeriodSelected = { selectedPeriod = it })
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Top Row: KPIs and Chart
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.8f),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // KPI Column (left)
            Column(
                modifier = Modifier
                    .weight(0.35f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AnalyticsKpiCard(
                    title = "TOTAL YIELD",
                    value = "42.8",
                    unit = "kWh",
                    progress = 0.75f,
                    progressColor = PrimaryEmerald,
                    icon = Icons.Default.Bolt,
                    iconColor = PrimaryEmerald,
                    modifier = Modifier.weight(1f)
                )
                AnalyticsKpiCard(
                    title = "SELF-CONSUMPTION",
                    value = "84",
                    unit = "%",
                    progress = 0.84f,
                    progressColor = TertiaryAmber,
                    icon = Icons.Default.Home,
                    iconColor = TertiaryAmber,
                    modifier = Modifier.weight(1f)
                )
                AnalyticsKpiCard(
                    title = "PEAK OUTPUT",
                    value = "5.2",
                    unit = "kW",
                    progress = 0.50f,
                    progressColor = SecondaryBlue,
                    icon = Icons.Default.Speed,
                    iconColor = SecondaryBlue,
                    modifier = Modifier.weight(1f)
                )
            }
            
            // Chart Card (right)
            Box(
                modifier = Modifier
                    .weight(0.65f)
                    .fillMaxHeight()
            ) {
                AnalyticsChartCard()
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Bottom Row: Table and Weather/AI
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.2f),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Hourly breakdown table (left)
            Box(
                modifier = Modifier
                    .weight(0.55f)
                    .fillMaxHeight()
            ) {
                HourlyBreakdownTable()
            }
            
            // Weather & AI (right)
            Column(
                modifier = Modifier
                    .weight(0.45f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                WeatherCard(modifier = Modifier.weight(1f))
                AiInsightsCard(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun AnalyticsHeader(selectedPeriod: String, onPeriodSelected: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                "ANALYTICS CONTROL",
                style = MaterialTheme.typography.labelLarge,
                color = OnSurfaceVariant
            )
            Text(
                "Analytics Control",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .background(SurfaceContainerHigh, RoundedCornerShape(8.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(8.dp))
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("Day", "Week", "Month").forEach { period ->
                    val isSelected = selectedPeriod == period
                    val bg = if (isSelected) PrimaryEmerald.copy(alpha = 0.1f) else Color.Transparent
                    val textCol = if (isSelected) PrimaryEmerald else OnSurfaceVariant
                    
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(bg)
                            .clickable { onPeriodSelected(period) }
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = period,
                            style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp),
                            color = textCol
                        )
                    }
                }
            }
            
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Sensors,
                    contentDescription = null,
                    tint = PrimaryEmerald
                )
            }
        }
    }
}

@Composable
fun AnalyticsKpiCard(
    title: String,
    value: String,
    unit: String,
    progress: Float,
    progressColor: Color,
    icon: ImageVector,
    iconColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainer.copy(alpha = 0.4f))
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp),
                color = OnSurfaceVariant
            )
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(16.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                color = if (progressColor == PrimaryEmerald) PrimaryEmerald else Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = unit,
                style = MaterialTheme.typography.bodyMedium,
                color = OnSurfaceVariant
            )
        }
        
        Spacer(modifier = Modifier.height(10.dp))
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(SurfaceContainerHigh)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress)
                    .background(progressColor)
            )
        }
    }
}

@Composable
fun AnalyticsChartCard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainer.copy(alpha = 0.4f))
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    "Generation vs Consumption",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Mission flow profile",
                    style = TextStyle(fontSize = 12.sp),
                    color = OnSurfaceVariant
                )
            }
            
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Box(modifier = Modifier.size(8.dp).clip(RoundedCornerShape(4.dp)).background(PrimaryEmerald))
                    Text("PV Solar", style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold), color = Color.White)
                }
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Box(modifier = Modifier.size(8.dp).clip(RoundedCornerShape(4.dp)).background(Color.White.copy(alpha = 0.4f)))
                    Text("Load", style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold), color = Color.White)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = size.width
                val height = size.height
                
                val gridLines = 4
                for (i in 0..gridLines) {
                    val y = (height / gridLines) * i
                    drawLine(
                        color = Color.White.copy(alpha = 0.03f),
                        start = androidx.compose.ui.geometry.Offset(0f, y),
                        end = androidx.compose.ui.geometry.Offset(width, y),
                        strokeWidth = 1.dp.toPx()
                    )
                }
                
                val genPath = Path().apply {
                    moveTo(0f, height)
                    cubicTo(
                        width * 0.25f, height,
                        width * 0.35f, height * 0.15f,
                        width * 0.5f, height * 0.12f
                    )
                    cubicTo(
                        width * 0.65f, height * 0.15f,
                        width * 0.75f, height,
                        width, height
                    )
                }
                
                val fillPath = Path().apply {
                    addPath(genPath)
                    lineTo(width, height)
                    lineTo(0f, height)
                    close()
                }
                
                drawPath(
                    path = fillPath,
                    brush = Brush.verticalGradient(
                        colors = listOf(PrimaryEmerald.copy(alpha = 0.2f), Color.Transparent),
                        startY = height * 0.12f,
                        endY = height
                    )
                )
                
                drawPath(
                    path = genPath,
                    color = PrimaryEmerald,
                    style = Stroke(width = 3.dp.toPx())
                )
                
                val loadPath = Path().apply {
                    moveTo(0f, height * 0.9f)
                    cubicTo(
                        width * 0.2f, height * 0.85f,
                        width * 0.4f, height * 0.95f,
                        width * 0.5f, height * 0.87f
                    )
                    cubicTo(
                        width * 0.6f, height * 0.8f,
                        width * 0.8f, height * 0.92f,
                        width, height * 0.88f
                    )
                }
                
                drawPath(
                    path = loadPath,
                    color = Color.White.copy(alpha = 0.3f),
                    style = Stroke(width = 2.dp.toPx())
                )
                
                drawCircle(
                    color = PrimaryEmerald,
                    radius = 5.dp.toPx(),
                    center = androidx.compose.ui.geometry.Offset(width * 0.5f, height * 0.12f)
                )
                
                drawCircle(
                    color = PrimaryEmerald.copy(alpha = 0.2f),
                    radius = 10.dp.toPx(),
                    center = androidx.compose.ui.geometry.Offset(width * 0.5f, height * 0.12f)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val times = listOf("00:00", "06:00", "12:00", "18:00", "23:59")
            times.forEach { time ->
                Text(
                    text = time,
                    style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold),
                    color = OnSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun HourlyBreakdownTable() {
    val items = listOf(
        HourlyBreakdownItem("12:00 - 13:00", "5.24 kWh", "1.12 kWh", "Surplus"),
        HourlyBreakdownItem("11:00 - 12:00", "4.89 kWh", "0.98 kWh", "Surplus"),
        HourlyBreakdownItem("10:00 - 11:00", "4.12 kWh", "0.87 kWh", "Surplus"),
        HourlyBreakdownItem("09:00 - 10:00", "3.56 kWh", "1.05 kWh", "Surplus"),
        HourlyBreakdownItem("08:00 - 09:00", "2.11 kWh", "1.30 kWh", "Surplus"),
        HourlyBreakdownItem("07:00 - 08:00", "0.98 kWh", "1.45 kWh", "Deficit")
    )
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainer.copy(alpha = 0.4f))
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceContainerHigh.copy(alpha = 0.3f))
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "HOURLY BREAKDOWN",
                style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp),
                color = Color.White
            )
            Icon(
                imageVector = Icons.Default.Download,
                contentDescription = null,
                tint = OnSurfaceVariant,
                modifier = Modifier.size(16.dp)
            )
        }
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceContainerHigh.copy(alpha = 0.5f))
                .padding(horizontal = 20.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("TIME", modifier = Modifier.weight(1.2f), style = TextStyle(fontSize = 9.sp, fontWeight = FontWeight.Bold), color = OnSurfaceVariant)
            Text("PV GEN", modifier = Modifier.weight(1f), style = TextStyle(fontSize = 9.sp, fontWeight = FontWeight.Bold), color = OnSurfaceVariant)
            Text("LOAD", modifier = Modifier.weight(1f), style = TextStyle(fontSize = 9.sp, fontWeight = FontWeight.Bold), color = OnSurfaceVariant)
            Text("STATUS", modifier = Modifier.weight(0.8f), style = TextStyle(fontSize = 9.sp, fontWeight = FontWeight.Bold), color = OnSurfaceVariant)
        }
        
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(items) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.White.copy(alpha = 0.01f))
                        .clickable {}
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(item.time, modifier = Modifier.weight(1.2f), style = TextStyle(fontSize = 13.sp), color = Color.White)
                    Text(item.pvGen, modifier = Modifier.weight(1f), style = TextStyle(fontSize = 13.sp), color = PrimaryEmerald)
                    Text(item.load, modifier = Modifier.weight(1f), style = TextStyle(fontSize = 13.sp), color = Color.White)
                    
                    Box(modifier = Modifier.weight(0.8f), contentAlignment = Alignment.CenterStart) {
                        val isSurplus = item.status == "Surplus"
                        val tagBg = if (isSurplus) PrimaryEmerald.copy(alpha = 0.1f) else ErrorRed.copy(alpha = 0.1f)
                        val tagTextCol = if (isSurplus) PrimaryEmerald else ErrorRed
                        Surface(
                            color = tagBg,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = item.status.uppercase(),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                style = TextStyle(fontSize = 8.sp, fontWeight = FontWeight.Bold),
                                color = tagTextCol
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherCard(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainer.copy(alpha = 0.4f))
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "WEATHER",
                style = TextStyle(fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp),
                color = OnSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "24°C",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Cloudy, 12% yield loss",
                style = TextStyle(fontSize = 11.sp),
                color = OnSurfaceVariant
            )
        }
        Icon(
            imageVector = Icons.Default.WbCloudy,
            contentDescription = null,
            tint = TertiaryAmber,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
fun AiInsightsCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceContainer.copy(alpha = 0.4f))
            .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(
            "AI INSIGHTS",
            style = TextStyle(fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp),
            color = OnSurfaceVariant
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(PrimaryEmerald.copy(alpha = 0.05f))
                .border(1.dp, PrimaryEmerald.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                .padding(12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Maintenance: Washing window tomorrow 09:00.",
                style = TextStyle(fontSize = 11.sp, lineHeight = 16.sp, fontWeight = FontWeight.Bold),
                color = PrimaryEmerald
            )
        }
    }
}
