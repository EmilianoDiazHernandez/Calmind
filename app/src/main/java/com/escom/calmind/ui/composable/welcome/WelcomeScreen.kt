package com.escom.calmind.ui.composable.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escom.calmind.ui.composable.components.PagerLabel
import com.escom.calmind.ui.shape.semiCircleShape
import com.escom.calmind.ui.theme.CalmindTheme

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    name: String,
    onNameChange: (String) -> Unit,
    selectedHobbies: List<String>,
    onCheckHobby: (String) -> Unit,
    onUncheckHobby: (String) -> Unit,
    schooling: String,
    onChangeSchooling: (String) -> Unit,
    age: String,
    onAgeChange: (String) -> Unit,
    onClickStartButton: () -> Unit
) {
    Surface {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val pagerState = rememberPagerState(pageCount = { 2 })
            val isInFirstPage by remember { derivedStateOf { pagerState.currentPage == 0 } }
            val surfaceColor by animateColorAsState(
                targetValue = if (isInFirstPage) MaterialTheme.colorScheme.surfaceContainer
                else MaterialTheme.colorScheme.surface,
                animationSpec = tween(
                    durationMillis = 1000,
                    delayMillis = if (isInFirstPage) 0 else 500
                )
            )
            val radius by animateFloatAsState(
                targetValue = if (isInFirstPage) LocalWindowInfo.current.containerSize.height.toFloat()
                else 100.0f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            AnimatedVisibility(isInFirstPage) { TopView() }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(if (isInFirstPage) semiCircleShape(radius) else RectangleShape)
                    .background(color = surfaceColor)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    if (page == 0) FirstPage() else SecondPage(
                        modifier,
                        name,
                        onNameChange,
                        selectedHobbies,
                        onCheckHobby,
                        onUncheckHobby,
                        schooling,
                        onChangeSchooling,
                        age,
                        onAgeChange,
                        onClickStartButton
                    )
                }
                PagerLabel(
                    Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp),
                    pagerState
                )
            }
        }
    }
}


@Preview(device = "id:pixel_7", showSystemUi = true, showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    CalmindTheme {
        WelcomeScreen(
            name = "Diego",
            onNameChange = {},
            selectedHobbies = listOf("Drawing, Sports", "Swimming"),
            onCheckHobby = {},
            onUncheckHobby = {},
            schooling = "Secondary School",
            onChangeSchooling = {},
            age = "8",
            onAgeChange = {},
            onClickStartButton = {}
        )
    }
}
