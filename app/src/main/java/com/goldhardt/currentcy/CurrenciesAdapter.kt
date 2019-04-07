package com.goldhardt.currentcy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goldhardt.core.Currency
import com.goldhardt.core.Flags
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency.*
import java.lang.IllegalArgumentException

class CurrenciesAdapter : RecyclerView.Adapter<CurrenciesAdapter.CurrencyViewHolder>() {

    var currencies: List<Currency> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClick: ((Currency) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
            CurrencyViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
            )

    override fun getItemCount(): Int = currencies.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencies[position])
    }

    inner class CurrencyViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private lateinit var currency: Currency

        init {
            containerView.setOnClickListener {
                onClick?.invoke(currency)
            }
        }

        fun bind(currency: Currency) {
            this.currency = currency
            name.text = currency.name
            val resource: Int = try {
                Flags.valueOf(currency.symbol).resourceId
            } catch (e: IllegalArgumentException) {
                R.drawable.ic_circle_default
            }
            flag.setImageResource(resource)

        }
    }
}