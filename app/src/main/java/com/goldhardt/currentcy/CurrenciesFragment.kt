package com.goldhardt.currentcy


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.goldhardt.core.BCBApi
import com.goldhardt.core.Currency
import com.goldhardt.core.CurrencyService
import com.google.android.material.snackbar.Snackbar
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
                Snackbar.make(root, currency.name, Snackbar.LENGTH_LONG).show()
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
        })
        viewModel.error.observe(this, Observer {
            Snackbar.make(root, "Failed", Snackbar.LENGTH_LONG).show()
        })
        viewModel.getCurrencies()
    }
}
