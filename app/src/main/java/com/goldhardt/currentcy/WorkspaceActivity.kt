package com.goldhardt.currentcy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.goldhardt.currentcy.currencies.ui.CurrenciesFragment

class WorkspaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workspace)
        supportFragmentManager.transaction(allowStateLoss = true) {
            replace(R.id.content, CurrenciesFragment.newInstance(), "currencies")
        }
    }
}
