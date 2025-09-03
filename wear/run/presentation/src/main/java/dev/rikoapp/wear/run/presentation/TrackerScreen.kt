package dev.rikoapp.wear.run.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.material3.FilledTonalIconButton
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.IconButtonDefaults
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.OutlinedIconButton
import androidx.wear.compose.material3.Text
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewSquare
import dev.rikoapp.core.notification.ActiveRunService
import dev.rikoapp.core.presentation.designsystem.ExclamationMarkIcon
import dev.rikoapp.core.presentation.designsystem.FinishIcon
import dev.rikoapp.core.presentation.designsystem.PauseIcon
import dev.rikoapp.core.presentation.designsystem.StartIcon
import dev.rikoapp.core.presentation.designsystem_wear.RuniqueCourseTheme
import dev.rikoapp.presentation.ui.ObserveAsEvents
import dev.rikoapp.presentation.ui.formated
import dev.rikoapp.presentation.ui.toFormattedHeartRate
import dev.rikoapp.presentation.ui.toFormattedKm
import dev.rikoapp.wear.run.presentation.ambient.AmbientObserver
import dev.rikoapp.wear.run.presentation.ambient.ambientMode
import dev.rikoapp.wear.run.presentation.composables.RunDataCard
import org.koin.androidx.compose.koinViewModel
import kotlin.time.Duration

@Composable
fun TrackerScreenRoot(
    onServiceToggle: (isServiceRunning: Boolean) -> Unit,
    viewModel: TrackerViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state

    val isServiceActive by ActiveRunService.isServiceActive.collectAsStateWithLifecycle()

    LaunchedEffect(state.isRunActive, state.hasStartedRunning, isServiceActive) {
        if (state.isRunActive && !isServiceActive) {
            onServiceToggle(true)
        }
    }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is TrackerEvent.Error -> {
                Toast.makeText(
                    context,
                    event.message.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }

            TrackerEvent.RunFinished -> {
                onServiceToggle(false)
            }
        }
    }

    TrackerScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
fun TrackerScreen(
    state: TrackerState,
    onAction: (TrackerAction) -> Unit
) {
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { perms ->
        val hasBodySensorPermission = perms[Manifest.permission.BODY_SENSORS] == true
        onAction(TrackerAction.OnBodySensorPermissionResult(hasBodySensorPermission))
    }

    val context = LocalContext.current

    LaunchedEffect(true) {
        val hasBodySensorPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.BODY_SENSORS
        ) == PackageManager.PERMISSION_GRANTED

        val hasNotificationPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }

        val hasForegroundServicePermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.FOREGROUND_SERVICE
        ) == PackageManager.PERMISSION_GRANTED

        val hasForegroundHealthServicePermission =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.FOREGROUND_SERVICE_HEALTH
                ) == PackageManager.PERMISSION_GRANTED
            } else {
                true
            }

        val hasActivityRecognitionPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACTIVITY_RECOGNITION
        ) == PackageManager.PERMISSION_GRANTED


        val permissions = mutableListOf<String>()
        if (!hasBodySensorPermission) {
            permissions.add(Manifest.permission.BODY_SENSORS)
        }
        if (!hasNotificationPermission && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions.add(Manifest.permission.POST_NOTIFICATIONS)
        }
        if (!hasForegroundServicePermission) {
            permissions.add(Manifest.permission.FOREGROUND_SERVICE)
        }
        if (!hasForegroundHealthServicePermission && Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            permissions.add(Manifest.permission.FOREGROUND_SERVICE_HEALTH)
        }
        if (!hasActivityRecognitionPermission) {
            permissions.add(Manifest.permission.ACTIVITY_RECOGNITION)
        }

        permissionLauncher.launch(permissions.toTypedArray())
    }

    AmbientObserver(
        onEnterAmbient = {
            onAction(TrackerAction.OnEnterAmbientMode(it.burnInProtectionRequired))
        },
        onExitAmbient = {
            onAction(TrackerAction.OnExitAmbientMode)
        }
    )

    if (state.isConnectedPhoneNearby) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .ambientMode(state.isAmbientMode, state.burnInProtectionRequired),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                RunDataCard(
                    title = stringResource(id = R.string.heart_rate),
                    value = if (state.canTrackHeartRate) {
                        state.heartRate.toFormattedHeartRate()
                    } else {
                        stringResource(id = R.string.unsupported)
                    },
                    valueTextColor = if (state.canTrackHeartRate) {
                        MaterialTheme.colorScheme.onSurface
                    } else {
                        MaterialTheme.colorScheme.error
                    },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                RunDataCard(
                    title = stringResource(id = R.string.distance),
                    value = (state.distanceMeters / 1000.0).toFormattedKm(),
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = state.elapsedDuration.formated(),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (state.isTrackable) {
                    ToggleRunButton(
                        isRunActive = state.isRunActive,
                        onClick = {
                            onAction(TrackerAction.OnToggleRunClick)
                        }
                    )
                    if (!state.isRunActive && state.hasStartedRunning) {
                        FilledTonalIconButton(
                            onClick = {
                                onAction(TrackerAction.OnFinishRunClick)
                            },
                            colors = IconButtonDefaults.filledTonalIconButtonColors(
                                contentColor = MaterialTheme.colorScheme.onBackground
                            )
                        ) {
                            Icon(
                                imageVector = FinishIcon,
                                contentDescription = stringResource(id = R.string.finish_run),
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                } else {
                    Text(
                        text = stringResource(id = R.string.open_active_run_screen),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = ExclamationMarkIcon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.connect_your_phone),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ToggleRunButton(
    isRunActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedIconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        if (isRunActive) {
            Icon(
                imageVector = PauseIcon,
                contentDescription = stringResource(id = R.string.pause_run),
                tint = MaterialTheme.colorScheme.onBackground
            )
        } else {
            Icon(
                imageVector = StartIcon,
                contentDescription = stringResource(id = R.string.start_run),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@WearPreviewDevices
@WearPreviewSquare
@Composable
private fun TrackerScreenConnectedPhoneAndActivePausedRunPreview() {
    RuniqueCourseTheme {
        TrackerScreen(
            state = TrackerState(
                elapsedDuration = Duration.parse("PT1H2M3S"),
                distanceMeters = 1567,
                isConnectedPhoneNearby = true,
                isRunActive = false,
                isTrackable = true,
                hasStartedRunning = true
            ),
            onAction = {}
        )
    }
}

@WearPreviewDevices
@WearPreviewSquare
@Composable
private fun TrackerScreenConnectedPhoneAndActiveRunPreview() {
    RuniqueCourseTheme {
        TrackerScreen(
            state = TrackerState(
                elapsedDuration = Duration.parse("PT1H2M3S"),
                distanceMeters = 1567,
                heartRate = 123,
                canTrackHeartRate = true,
                isConnectedPhoneNearby = true,
                isRunActive = true,
                isTrackable = true
            ),
            onAction = {}
        )
    }
}

@WearPreviewDevices
@WearPreviewSquare
@Composable
private fun TrackerScreenConnectedPhonePreview() {
    RuniqueCourseTheme {
        TrackerScreen(
            state = TrackerState(
                isConnectedPhoneNearby = true
            ),
            onAction = {}
        )
    }
}

@WearPreviewDevices
@WearPreviewSquare
@Composable
private fun TrackerScreenPreview() {
    RuniqueCourseTheme {
        TrackerScreen(
            state = TrackerState(),
            onAction = {}
        )
    }
}
