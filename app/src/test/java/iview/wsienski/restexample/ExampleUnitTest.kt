package iview.wsienski.restexample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import iview.wsienski.restexample.common.SchedulersProviderTest
import iview.wsienski.restexample.data.repository.UserRepositoryFake
import iview.wsienski.restexample.ui.main.MainViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `when SUCCESS init viewModel show loader and hide`() {
        //Given
        val mainViewModel = MainViewModel(UserRepositoryFake(isSuccess = true), SchedulersProviderTest())
        val testObserver = mainViewModel.screenState.testObserver()
        //When
        mainViewModel.init()
        //Then
        assert(testObserver.observedValues.first()?.isLoading == true)
        assert(testObserver.observedValues.last()?.isLoading == false)
    }

    @Test
    fun `when SUCCESS init viewModel no error`() {
        //Given
        val mainViewModel = MainViewModel(UserRepositoryFake(isSuccess = true), SchedulersProviderTest())
        val testObserver = mainViewModel.screenState.testObserver()
        //When
        mainViewModel.init()
        //Then
        testObserver.observedValues.forEach { assert(it?.error == null) }
    }

    @Test
    fun `when UNSUCCESS init viewModel return error`() {
        //Given
        val mainViewModel = MainViewModel(UserRepositoryFake(isSuccess = false), SchedulersProviderTest())
        val testObserver = mainViewModel.screenState.testObserver()
        //When
        mainViewModel.init()
        //Then
        assert(testObserver.observedValues.any { !it.error.isNullOrEmpty() })
    }


    class TestObserver<T> : Observer<T> {

        val observedValues = mutableListOf<T>()

        override fun onChanged(value: T) {
            observedValues.add(value)
        }
    }

    fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
        observeForever(it)
    }
}
