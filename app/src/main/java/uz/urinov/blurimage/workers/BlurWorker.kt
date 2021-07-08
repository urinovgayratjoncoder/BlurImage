package uz.urinov.blurimage.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import uz.urinov.blurimage.Constants.KEY_IMAGE_URI


class BlurWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    private val TAG = "TTTBlurWorker"
    override fun doWork(): Result {


        try {
            Log.d(TAG, "doWork: 1")

            val resolver = applicationContext.contentResolver

            val resourceUri = inputData.getString(KEY_IMAGE_URI)

            val picture = BitmapFactory.decodeStream(
                resolver.openInputStream(
                    Uri.parse(resourceUri)
                )
            )

//            val picture =
//                BitmapFactory.decodeResource(applicationContext.resources, R.drawable.imam2)
//

            Log.d(TAG, "doWork: 2")

            val output = WorkerUtils.blurBitmap(picture, applicationContext)
            Log.d(TAG, "doWork: 3")
            val outputUri = WorkerUtils.writeBitmapToFile(applicationContext, output)
            WorkerUtils.makeStatusNotification("blur image is $outputUri", applicationContext)
            Log.d(TAG, "doWork: 4")

            return Result.success()


        } catch (throwable: Throwable) {
            return Result.failure()
        }

    }
}