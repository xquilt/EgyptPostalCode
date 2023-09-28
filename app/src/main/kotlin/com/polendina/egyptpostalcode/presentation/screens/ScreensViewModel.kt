package com.polendina.egyptpostalcode.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Atm
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.PointOfSale
import androidx.compose.material.icons.outlined.SwapHoriz
import androidx.compose.material.icons.outlined.SwapVert
import androidx.compose.material.icons.outlined.Window
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.polendina.egyptpostalcode.R
import com.polendina.egyptpostalcode.data.repository.EgyptPostCodeRepository
import com.polendina.egyptpostalcode.domain.model.PostOffice
import com.polendina.egyptpostalcode.domain.model.PostOfficeAdditional
import com.polendina.egyptpostalcode.offices

class ScreensViewModel: ViewModel() {

    private var _query = mutableStateOf("")
    var query = _query
    // TODO: Check if the provided value is alphanumeric & not empty etc...
    private val _officesList = mutableStateListOf<PostOffice>()
    val officeList = _officesList
    private val _postOffice: SnapshotStateMap<Int?, PostOfficeAdditional?> = mutableStateMapOf()

    fun updateQuery(value: String) {
        // TODO: Do some form of input validation for the user input
        _query.value = value
    }

    val egyptPostCodeRepository = EgyptPostCodeRepository()
    fun getOffices(
        city: String
    ) {
        egyptPostCodeRepository.searchOffices(
            city = city,
            responseBody = { officeResponse ->
                officeResponse?.let {
                    _officesList.clear()
                    _officesList.addAll(it.data)
                }
            }
        ) {
            it.printStackTrace()
        }
    }

    private val _officeFeatures: SnapshotStateList<PostOfficeAdditional> = mutableStateListOf()
    private fun setOfficeFeatures(postOfficeAdditional: PostOfficeAdditional?) {
        // TODO: There gotta be a better approach than this
        postOfficeAdditional?.data?.run {
            val services = listOf(atm, pos, ifs, one_window, internal_ifs, speed_mail)
            for ((feature, availability) in OfficeFeatures.entries.zip(services)) {
                feature.setAvailability(availability.toBoolean())
            }
        }
//        _officeFeatures.clear()
//        _officeFeatures.addAll(OfficeFeatures.entries.toMutableStateList())
    }

    suspend fun obtainOffice(
        id: Int
    ) {
//        viewModelScope.launch {
            _postOffice.get(id)?.let {
                setOfficeFeatures(it)
            } ?: egyptPostCodeRepository.getOffice(id).run {
                _postOffice.put(id, this)
                setOfficeFeatures(this)
            }
//        }
    }

}

fun String.toBoolean() = when (this.toInt()) {
    0 -> false
    1 -> true
    else -> false
}

enum class OfficeFeatures(
    @StringRes val stringResource: Int,
    var available: Boolean,
    val icon: ImageVector
) {
    // TODO: Some of these icons aren't exactly what I'm looking after
    ATM(R.string.atm, false, Icons.Outlined.Atm),
    POS(R.string.pos, false, Icons.Outlined.PointOfSale),
    IFS(R.string.ifs, false, Icons.Outlined.SwapVert),
    ONE_WINDOW(R.string.one_window, false, Icons.Outlined.Window),
    INTERNAL_IFS(R.string.internal_ifs, false, Icons.Outlined.SwapHoriz),
    SPEED_MAIL(R.string.speed_mail, true, Icons.Outlined.Mail);

    fun setAvailability(available: Boolean) {
        this.available = available
    }
}
