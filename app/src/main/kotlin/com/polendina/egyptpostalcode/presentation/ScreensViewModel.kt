package com.polendina.egyptpostalcode.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.polendina.egyptpostalcode.data.repository.EgyptPostCodeRepository
import com.polendina.egyptpostalcode.domain.model.PostOffice
import com.polendina.egyptpostalcode.offices

class ScreensViewModel: ViewModel() {

    private var _query = mutableStateOf("")
    var query = _query
    // TODO: Check if the provided value is alphanumeric & not empty etc...
//    private val _officesList = mutableStateListOf<PostOffice>()
    private val _officesList = offices.toMutableList()
    val officeList = _officesList

    fun updateQuery(value: String) {
        _query.value = value
    }

    private val egyptPostCodeRepository = EgyptPostCodeRepository()
    fun getOffices(
        city: String
    ) {
        egyptPostCodeRepository.getOffices(
            city = city,
            responseBody = { officeResponse ->
                officeResponse?.let {
                    _officesList.clear()
                    println("office data is ${it.data}")
                    _officesList.addAll(it.data)
                }
            }
        ) {
            it.printStackTrace()
        }
    }

}