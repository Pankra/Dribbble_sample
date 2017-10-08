package test.example.dribbblesample.interactor

import test.example.dribbblesample.di.ShotsRepository

class ShotsInteractorImpl(threadExecutor: Executor, val page: Int, val shotsRepository: ShotsRepository, val callback: ShotsInteractor.Callback): AbstractInteractor(threadExecutor), ShotsInteractor {
    override fun run() {
        val shots = shotsRepository.getShots(page)
        callback.onShotsReceived(shots)
    }
}