package iview.wsienski.restexample.common

import io.reactivex.Scheduler

/**
 * Created by Witold Sienski on 21.11.2018.
 */
interface ISchedulersProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
}