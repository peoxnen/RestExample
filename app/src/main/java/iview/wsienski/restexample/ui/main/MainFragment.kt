package iview.wsienski.restexample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import iview.wsienski.restexample.R
import iview.wsienski.restexample.RestExampleApp
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java) }
    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as RestExampleApp).appComponent.inject(this)
        viewModel.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.main_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersList.layoutManager = LinearLayoutManager(activity)
        usersList.adapter = adapter
        observeViewModel()
    }

    private fun observeViewModel() {
        observe(viewModel.users) { adapter.submitList(it) }
        observe(viewModel.usersRepo) { message.text = it }
        observe(viewModel.screenState) { screenState ->
            screenState.error?.let { showToast(it) }
            loader.visibility = if (screenState.isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun <T> Fragment.observe(liveData: LiveData<T>, action: (T) -> Unit) =
        liveData.observe(this.viewLifecycleOwner, Observer { action(it) })

    private fun Fragment.showToast(text: String) =
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}
