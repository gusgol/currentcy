package com.goldhardt.currentcy.currencies.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.goldhardt.core.data.api.BCBApi
import com.goldhardt.core.data.currency.Currency
import com.goldhardt.core.data.currency.CurrencyService
import com.goldhardt.currentcy.R
import com.goldhardt.currentcy.currencies.data.CurrenciesRemoteDataSource
import com.goldhardt.currentcy.currencies.data.CurrenciesRepository
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_currencies.*

/**
 * A simple [Fragment] subclass.
 */
class CurrenciesFragment : Fragment() {

    companion object {
        fun newInstance(): CurrenciesFragment = CurrenciesFragment()
    }

    private lateinit var viewModel: CurrenciesViewModel
    private lateinit var currenciesAdapter: CurrenciesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_currencies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currenciesAdapter = CurrenciesAdapter().apply {
            onClick = { currency ->
//                Snackbar.make(root, currency.name, Snackbar.LENGTH_LONG).show()
                showError()
            }
        }
        currencies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = currenciesAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, CurrenciesViewModelFactory(
                CurrenciesRepository(
                        CurrenciesRemoteDataSource(
                                BCBApi.get().create(CurrencyService::class.java)
                        )
                )
        ))[CurrenciesViewModel::class.java]
        viewModel.currencies.observe(this, Observer<List<Currency>> {
            currenciesAdapter.currencies = it
            currencies.visibility = View.VISIBLE
        })
        viewModel.error.observe(this, Observer {
            showError()
        })
        viewModel.getCurrencies()
    }

    private fun showError() {
        currencies.visibility = View.GONE
        layoutInflater.inflate(R.layout.view_error, root).apply {
            this.findViewById<MaterialButton>(R.id.tryAgain).setOnClickListener {
                viewModel.getCurrencies()
                root.removeView(this)
            }
            this.findViewById<ImageView>(R.id.image).apply {
                startAnimation(AnimationUtils.loadAnimation(context, R.anim.shake))
            }
        }
    }
}
