package uz.urinov.blurimage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.*
import uz.urinov.blurimage.Constants.KEY_IMAGE_URI
import uz.urinov.blurimage.workers.ReapedWorker
import java.util.concurrent.TimeUnit


class BlurViewModel(app: Application) : AndroidViewModel(app) {
    private var workManager: WorkManager = WorkManager.getInstance(app)
    private var imageUrl: String = ""

    fun applyBlur(blurLevel: Int) {
//        workManager.enqueueUniquePeriodicWork(
//            "Salom", ExistingPeriodicWorkPolicy.REPLACE,
//            PeriodicWorkRequest.Builder(
//                ReapedWorker::class.java, 1, TimeUnit.SECONDS
//            ).build()
//        )


        val workRequest = PeriodicWorkRequest.Builder(
            ReapedWorker::class.java,
            1,
            TimeUnit.SECONDS,
            PeriodicWorkRequest.MAX_BACKOFF_MILLIS,
            TimeUnit.SECONDS
        )
            .addTag("send_reminder_periodic")
            .build()


        workManager.enqueueUniquePeriodicWork(
                "send_reminder_periodic",
                ExistingPeriodicWorkPolicy.REPLACE,
                workRequest
            )
//        workManager.enqueue(
//            OneTimeWorkRequest.Builder(BlurWorker::class.java)
//                .setInputData(createInputDataForUri())
//                .build()
//        )
    }

    private fun createInputDataForUri(): Data {
        val data = Data.Builder()
        data.putString(KEY_IMAGE_URI, imageUrl)

        return data.build()
    }


    fun setImageUri(uri: String) {
        imageUrl = uri
    }

}