package com.popwoot.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.popwoot.R
import com.popwoot.adapter.NoteAdapter
import com.popwoot.model.Row
import com.popwoot.mvvm.ListViewModel
import com.popwoot.utils.CustomProgressDialog
import com.popwoot.utils.verifyAvailableNetwork
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : BaseActivity() {

    private val pd by lazy { CustomProgressDialog(this, false) }
    private val mViewModel by lazy { ViewModelProviders.of(this).get(ListViewModel::class.java) }
    private lateinit var mAdapter: NoteAdapter
    private var itemsArray = mutableListOf<Row>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        //observer
        observeLiveData()
        //Api Call
        apiCall()

    }

    private fun apiCall() {

        if(verifyAvailableNetwork()) {
            pd.show()
            mViewModel.getApiCall()
        }else{
            pd.hide()
            showSnackBarErr()
        }


        //pull to Refresh
        refreshContainer.setListener {
            if(verifyAvailableNetwork())
                mViewModel.getApiCall()
            else {
                showSnackBarErr()
                refreshContainer.finishRefreshing();
            }
        }
    }


    private fun observeLiveData() {
        mViewModel.requestData.observe(this, Observer {
            pd.hide()
            refreshContainer.finishRefreshing();
            itemsArray.clear()
            itemsArray = it.rows as MutableList<Row>
            notifyDataSetChanged()
        })
    }

    private fun notifyDataSetChanged() {
        mAdapter = NoteAdapter(itemsArray)
        recyclerView.adapter = mAdapter
        mAdapter.notifyDataSetChanged()

    }
}
