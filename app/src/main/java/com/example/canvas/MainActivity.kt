package com.example.canvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.canvas.ui.theme.CanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    CanvasRectangle()
//                    DrawGradientCircles()

                }
            }
        }
    }
}

@Composable
fun CanvasRectangle()
{
    Canvas(modifier = Modifier
        .padding(20.dp)
        .size(300.dp))
    {
        drawRect(
            color = Color.Black,
            size = size
        )
        drawRect(
            color = Color.Red,
            topLeft = Offset(100f,100f),
            size = Size(650f,650f),
            style= Stroke(width=5.dp.toPx())

            )
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.Red,Color.Yellow),
                center = center,
                radius = 100f
            ),
            radius = 100f,
            center = center
        )
        drawArc(
            color = Color.Green,
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            topLeft = Offset(300f,550f),
            size = Size(150f,150f),
            style = Stroke(width=15.dp.toPx())
        )
        drawArc(
            color = Color.Yellow,
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            topLeft = Offset(350f,550f),
            size = Size(100f,100f),
            style = Stroke(width=15.dp.toPx())
        )
        drawArc(
            color = Color.Red,
            startAngle = 0f,
            sweepAngle = 270f,
            useCenter = true,
            topLeft = Offset(150f,550f),
            size = Size(100f,100f)
        )
        drawOval(
            color=Color.White,
            topLeft = Offset(500f,100f),
            size=Size(200f,300f)
        )
        drawLine(
            color = Color.Cyan,
            start = Offset(120f,180f),
            end = Offset(300f,180f),
            strokeWidth = 5.dp.toPx()
        )
    }
}

@Composable
fun InstaIcon()
{
    val instaColor = listOf(Color.Yellow,Color.Magenta,Color.Transparent,Color.Yellow,Color.Magenta)

    Canvas(modifier = Modifier
        .padding(16.dp)
        .size(100.dp))
    {
        val canvasSize = size
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawRoundRect(
            brush = Brush.linearGradient(instaColor),
            cornerRadius = CornerRadius(60f,60f),
            size = canvasSize,
            style = Stroke(20f)
        )
        drawCircle(
            brush = Brush.linearGradient(instaColor),
            radius= 45f,
            style = Stroke(15f)
        )
        drawCircle(
            brush = Brush.linearGradient(instaColor),
            radius= 16f,
            center = Offset(canvasWidth.times(.80f),canvasHeight.times(.20f))
        )
    }
}

@Composable
fun DrawGradientCircles() {
    val startAngle = 0f
    val angle1 = remember { androidx.compose.animation.core.Animatable(initialValue = 380f) }
    val angle2 = remember { androidx.compose.animation.core.Animatable(initialValue = 380f) }
    val angle3 = remember { androidx.compose.animation.core.Animatable(initialValue = 380f) }
    val density = LocalDensity.current.density
    val boxSize = 300f
    val circleStrokeWidth = 90f
    val outerCircleSize = (boxSize * density) - (circleStrokeWidth / 2 * density)
    val margin = with(LocalDensity.current) { 0.dp.toPx() }

    Canvas(
        modifier = Modifier.size(Dp(boxSize))
    ) {
        drawRect(Color.White)
        rotate(-90f) {
            val offset1 = circleStrokeWidth / 2
            val size1 = outerCircleSize
            val topLeft1 = Offset(offset1, offset1)
            val bottomRight1 = Offset(offset1 + size1, offset1 + size1)

            drawArc(
                Brush.sweepGradient(listOf(Color.Magenta, Color.Red)),
                startAngle,
                angle1.value,
                false,
                topLeft = topLeft1,
                size = Size(size1, size1),
                style = Stroke(circleStrokeWidth, 0f, StrokeCap.Round, StrokeJoin.Bevel)
            )
            drawArc(
                SolidColor(Color.Black.copy(alpha = 0.2f)),
                startAngle,
                angle1.value,
                false,
                topLeft = topLeft1,
                size = Size(size1, size1),
                style = Stroke(circleStrokeWidth + 10f, 0f, StrokeCap.Round, StrokeJoin.Round)
            )

            // Draw semicircle at the end edge of the first circle
            val endEdgeRadius1 = circleStrokeWidth / 2
            val endEdgeCenter1 = Offset(bottomRight1.x, (topLeft1.y + bottomRight1.y) / 2)
            drawArc(
                Brush.sweepGradient(listOf(Color(0xFFCC1806), Color.Transparent)),
                331f,
                30f,
                false,
                topLeft = topLeft1,
                size = Size(size1, size1),
                style = Stroke(circleStrokeWidth, 0f, StrokeCap.Round, StrokeJoin.Bevel)
            )

            val offset2 = offset1 + circleStrokeWidth + margin
            val size2 = size1 - (circleStrokeWidth * 2) - (margin * 2)
            val topLeft2 = Offset(offset2, offset2)
            val bottomRight2 = Offset(offset2 + size2, offset2 + size2)

            drawArc(
                Brush.sweepGradient(listOf(Color.Green, Color.Yellow)),
                startAngle,
                angle2.value,
                false,
                topLeft = topLeft2,
                size = Size(size2, size2),
                style = Stroke(circleStrokeWidth, 0f, StrokeCap.Round, StrokeJoin.Round)
            )
            drawArc(
                SolidColor(Color.Black.copy(alpha = 0.2f)),
                startAngle,
                angle2.value,
                false,
                topLeft = topLeft2,
                size = Size(size2, size2),
                style = Stroke(circleStrokeWidth + 10f, 0f, StrokeCap.Round, StrokeJoin.Round)
            )

            // Draw semicircle at the end edge of the second circle
            val endEdgeRadius2 = circleStrokeWidth / 2
            val endEdgeCenter2 = Offset(bottomRight2.x, (topLeft2.y + bottomRight2.y) / 2)
            drawArc(
                Brush.sweepGradient(listOf(Color(0xFFC8D63F), Color.Transparent)),
                331f,
                30f,
                false,
                topLeft = topLeft2,
                size = Size(size2, size2),
                style = Stroke(circleStrokeWidth, 0f, StrokeCap.Round, StrokeJoin.Bevel)
            )

            val offset3 = offset2 + (circleStrokeWidth ) + margin


            val size3 = size2 - (circleStrokeWidth * 2) - (margin * 2)
            val topLeft3 = Offset(offset3, offset3)
            val bottomRight3 = Offset(offset3 + size3, offset3 + size3)

            drawArc(
                Brush.sweepGradient(listOf(Color.Cyan, Color.Blue)),
                startAngle,
                angle3.value,
                false,
                topLeft = topLeft3,
                size = Size(size3, size3),
                style = Stroke(circleStrokeWidth, 0f, StrokeCap.Round, StrokeJoin.Bevel)
            )
            drawArc(
                SolidColor(Color.Black.copy(alpha = 0.2f)),
                startAngle,
                angle3.value,
                false,
                topLeft = topLeft3,
                size = Size(size3, size3),
                style = Stroke(circleStrokeWidth + 10f, 0f, StrokeCap.Round, StrokeJoin.Round)
            )

            // Draw semicircle at the end edge of the third circle
            val endEdgeRadius3 = circleStrokeWidth / 2
            val endEdgeCenter3 = Offset(bottomRight3.x, (topLeft3.y + bottomRight3.y) / 2)
            drawArc(
                Brush.sweepGradient(listOf(Color(0xFF0625B9), Color.Transparent)),
                331f,
                30f,
                false,
                topLeft = topLeft3,
                size = Size(size3, size3+2f),
                style = Stroke(circleStrokeWidth, 10f, StrokeCap.Round, StrokeJoin.Bevel)
            )
        }
    }
}

@Composable
fun YoutubeIcon()
{
    Canvas(modifier = Modifier
        .padding(16.dp)
        .size(200.dp, 100.dp))
    {
        val x = size.width
        val y = size.height
        val center = size.center

        drawRoundRect(
            color = Color.Red,
            size = Size(x,y),
            cornerRadius = CornerRadius(40f,40f)
        )

        val path = Path().apply {
            moveTo(center.x,center.y - 40)
            lineTo(center.x,center.y + 40)
            lineTo(center.x + 80 , center.y)
        }
        drawPath(
            path, color = Color.White
        )
    }
}

@Composable
fun SquidGameCard()
{
    val color = Color(0xFF957C4A)
    Canvas(modifier= Modifier
        .padding(16.dp)
        .size(200.dp, 100.dp))
    {
        val x = size.width
        val y = size.height
        val center = size.center

        drawRect(
            color = color,
            size = Size(x,y)
        )
        drawCircle(
            radius = center.x/6,
            style = Stroke(15f),
            color = Color.Black,
            center = Offset(center.x - 120 ,center.y)
        )
        val path = Path().apply {
            moveTo(center.x,center.y-40)
            lineTo(center.x+40 , center.y+40)
            lineTo(center.x - 40 , center.y + 40)
            close()
        }
        drawPath(path,color = Color.Black, style = Stroke(15f))

        drawRect(
            color= Color.Black,
            style = Stroke(15f),
            topLeft = Offset(center.x + 90,center.y-50),
            size = Size(center.x/3 , center.x/3)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview()
{
    DrawGradientCircles()
}

@Composable
fun Dp.toPx(): Float {
    val density = LocalDensity.current.density
    return (this.value * density)
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview3()
{
    InstaIcon()
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview4()
{
    YoutubeIcon()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreviewSquid() {
    SquidGameCard()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2()
{
    CanvasRectangle()
}