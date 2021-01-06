package com.example.desafiophi.features.bankStatement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiophi.architecture.networking.Resource
import com.example.desafiophi.data.PhiService
import com.example.desafiophi.data.models.responses.Statement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BankStatementViewModel : ViewModel() {

    private val service = PhiService()
    private val _balance = MutableLiveData<Resource<Int>>()
    private val _statement = MutableLiveData<Resource<List<Statement.Item>>>()

    val balance: LiveData<Resource<Int>>
        get() = _balance

    val statement: LiveData<Resource<List<Statement.Item>>>
        get() = _statement

    fun getBalance() {
        _balance.value = Resource.loading()
        viewModelScope.launch(Dispatchers.IO) {
            val response = service.getBalance()

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _balance.value = Resource.success(response.body()?.amount!!)
                }
            }
        }
    }

    fun getStatement(pageNumber: Int) {
        _statement.value = Resource.loading()
        viewModelScope.launch(Dispatchers.IO) {
            val response = service.getStatement(pageNumber)

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _statement.value = Resource.success(response.body()?.items!!)
                }
            }
        }
    }
}