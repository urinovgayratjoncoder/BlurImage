package uz.urinov.blurimage.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class ReapedWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    private val TAG = "TTTReapedWorker"
    var count = 1
    override fun doWork(): Result {

        Log.d(TAG, "doWork:${count++} ")

        return Result.success()
    }
}