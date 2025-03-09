package dev.rikoapp.run.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.rikoapp.core.domain.run.RunRepository

class FetchRunsWorker(
    context: Context,
    params: WorkerParameters,
    private val runRepository: RunRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        if (runAttemptCount >= 5) {
            return Result.failure()
        }

        return when (val result = runRepository.fetchRuns()) {
            is dev.rikoapp.core.domain.util.Result.Error -> {
               result.error.toWorkerResult()
            }

            is dev.rikoapp.core.domain.util.Result.Success -> Result.success()
        }
    }
}