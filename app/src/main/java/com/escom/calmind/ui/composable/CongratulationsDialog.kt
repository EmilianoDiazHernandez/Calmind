package com.escom.calmind.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.escom.calmind.R
import com.escom.calmind.model.ResilienceResult
import com.escom.calmind.model.StressResult
import com.escom.calmind.model.TestResult
import com.escom.calmind.model.TraumaResult
import com.escom.calmind.model.UserData
import com.escom.calmind.ui.composable.components.PagerLabel
import com.escom.calmind.ui.theme.CalmindTheme

@Composable
fun CongratulationDialog(
    onTestResultsAvailable: () -> TestResult,
    currentUser: UserData?,
    onConfirmDialog: () -> Unit,
    pageState: PagerState
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        content = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                )
            ) {
                val testResults = onTestResultsAvailable()
                Text(
                    text = stringResource(R.string.congratulations),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Text(
                    currentUser?.name.orEmpty(),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                PagerLabel(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    pagerState = pageState
                )
                HorizontalPager(
                    state = pageState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) { page ->
                    val messageId: Int by remember {
                        derivedStateOf {
                            when (page) {
                                0 -> R.string.message_successfully
                                1 -> when (testResults.stressResult!!) {
                                    StressResult.LOW -> R.string.stress_low
                                    StressResult.MIDDLE -> R.string.stress_medium
                                    StressResult.HIGH -> R.string.stress_high
                                }

                                2 -> when (testResults.resilienceResult!!) {
                                    ResilienceResult.LOW -> R.string.resilience_low
                                    ResilienceResult.MIDDLE -> R.string.resilience_middle
                                    ResilienceResult.HIGH -> R.string.resilience_high
                                }

                                else -> when (testResults.traumaResult!!) {
                                    TraumaResult.NO_PTSD -> R.string.no_ptsd
                                    TraumaResult.PROBABLE_PTSD -> R.string.probable_ptsd
                                    TraumaResult.HIGHLY_PROBABLE_PTSD -> R.string.high_probable_ptsd
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(messageId),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        when (page) {
                            0 -> {
                                Icon(
                                    painter = painterResource(R.drawable.swipe),
                                    contentDescription = stringResource(R.string.swipe_right),
                                    modifier = Modifier.padding(vertical = 16.dp)
                                )
                            }
                            3 -> {
                                Button(
                                    onClick = onConfirmDialog,
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(text = stringResource(R.string.go))
                                }
                            }
                            else -> {
                                val emojis = listOf("🌻", "☺", "⭐", "🎈")
                                Text(
                                    text = emojis.random(),
                                    fontSize = 50.sp,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                            }
                        }
                    }
                }

            }
        }
    )
}

@Preview(name = "Dialog - page 1", group = "Dialog")
@Composable
private fun DialogPreview() {
    val pageState = rememberPagerState(initialPage = 1, pageCount = { 4 })
    CalmindTheme {
        CongratulationDialog(
            currentUser = UserData("Diego", 13, emptyList(), "High School"),
            onTestResultsAvailable = {
                TestResult(
                    StressResult.LOW,
                    ResilienceResult.LOW,
                    TraumaResult.NO_PTSD
                )
            },
            onConfirmDialog = {},
            pageState = pageState
        )
    }
}

@Preview(name = "Dialog - page 2", group = "Dialog")
@Composable
private fun DialogPreview2() {
    val pageState = rememberPagerState(initialPage = 2, pageCount = { 4 })
    CalmindTheme {
        CongratulationDialog(
            currentUser = UserData("Diego", 13, emptyList(), "High School"),
            onTestResultsAvailable = {
                TestResult(
                    StressResult.LOW,
                    ResilienceResult.MIDDLE,
                    TraumaResult.NO_PTSD
                )
            },
            onConfirmDialog = {},
            pageState = pageState
        )
    }
}

@Preview(name = "Dialog - page 3", group = "Dialog")
@Composable
private fun DialogPreview3() {
    val pageState = rememberPagerState(initialPage = 3, pageCount = { 4 })
    CalmindTheme {
        CongratulationDialog(
            currentUser = UserData("Diego", 13, emptyList(), "High School"),
            onTestResultsAvailable = {
                TestResult(
                    StressResult.LOW,
                    ResilienceResult.MIDDLE,
                    TraumaResult.NO_PTSD
                )
            },
            onConfirmDialog = {},
            pageState = pageState
        )
    }
}