package test.example.dribbblesample.interactor

import test.example.dribbblesample.ShotItem

interface ShotsInteractor: Interactor {
    interface Callback {
        fun onShotsReceived(shots: List<ShotItem>)
    }
}