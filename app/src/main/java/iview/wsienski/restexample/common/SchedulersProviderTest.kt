package iview.wsienski.restexample.common

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class SchedulersProviderTest : ISchedulersProvider {
    override fun io(): Scheduler = Schedulers.trampoline()
    override fun ui(): Scheduler = Schedulers.trampoline()
}