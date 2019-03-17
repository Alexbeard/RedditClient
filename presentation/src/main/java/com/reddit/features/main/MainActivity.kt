package com.reddit.features.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.reddit.BuildConfig
import com.reddit.R
import com.reddit.common.BaseActivity
import com.reddit.common.adapter.LoadingAdapter
import com.reddit.databinding.ActivityMainBinding
import com.reddit.features.main.filter.PeriodDialogFragment
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RedditAdapter
    private lateinit var loadingAdapter: LoadingAdapter

    private val activityComponent by lazy {
        MainActivityComponent.Initializer.init(this)
    }

    override fun inject(fragment: Fragment) {
        if (fragment is PeriodDialogFragment) activityComponent.inject(fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, com.reddit.R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setUpRecycler()
        setSupportActionBar(binding.toolbar)
        observe()
    }

    private fun setUpRecycler() {
        adapter = RedditAdapter(viewModel)
        loadingAdapter = LoadingAdapter(adapter)
        loadingAdapter.setLoadMoreListener(object : LoadingAdapter.LoadMoreListener {
            override fun onLoadMore() {
                viewModel.fetchNext()
            }
        })
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = loadingAdapter
    }

    private fun observe() {
        viewModel.content.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.hasNext.observe(this, Observer { loadingAdapter.setHasNext(it!!) })
        viewModel.loading.observe(this, Observer { loadingAdapter.setLoading(it!!) })
        viewModel.filterClickedEvent.observe(this, Observer {
            PeriodDialogFragment.run(supportFragmentManager)
        })
        viewModel.postClickedEvent.observe(this, Observer {
            openDetailsPage(it.permalink)
        })
        viewModel.shareClickedEvent.observe(this, Observer {
            openShareChooser(it.permalink)
        })
        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun openDetailsPage(permalink: String) {
        val url = BuildConfig.SCHEME + BuildConfig.HOST + permalink
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    private fun openShareChooser(permalink: String) {
        val url = BuildConfig.SCHEME + BuildConfig.HOST + permalink
        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setChooserTitle(R.string.chooser_share_title)
                .setText(url)
                .startChooser()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == R.id.filterBtn) {
            viewModel.onFilterClickedEvent()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

}