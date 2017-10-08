package test.example.dribbblesample.interactor

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


/**
 * This singleton class will make sure that each interactor operation gets a background thread.
 */
class ThreadExecutor : Executor {

    private val CORE_POOL_SIZE = 3
    private val MAX_POOL_SIZE = 5
    private val KEEP_ALIVE_TIME = 120
    private val TIME_UNIT = TimeUnit.SECONDS
    private val WORK_QUEUE = LinkedBlockingQueue<Runnable>()
    private val threadPoolExecutor: ThreadPoolExecutor

    init {
        val keepAlive = KEEP_ALIVE_TIME.toLong()
        threadPoolExecutor = ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                keepAlive,
                TIME_UNIT,
                WORK_QUEUE)
    }

    override fun execute(interactor: AbstractInteractor) {
        threadPoolExecutor.submit {
            // run the main logic
            interactor.run()

            // mark it as finished
            interactor.onFinished()
        }
    }
}
