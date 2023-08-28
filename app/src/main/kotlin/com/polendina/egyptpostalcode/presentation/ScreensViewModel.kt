package com.polendina.egyptpostalcode.presentation

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
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.polendina.egyptpostalcode.R
import com.polendina.egyptpostalcode.data.repository.EgyptPostCodeRepository
import com.polendina.egyptpostalcode.domain.model.PostOffice
import com.polendina.egyptpostalcode.domain.model.PostOfficeAdditional

class ScreensViewModel: ViewModel() {

    private var _query = mutableStateOf("")
    var query = _query
    // TODO: Check if the provided value is alphanumeric & not empty etc...
    private val _officesList = mutableStateListOf<PostOffice>()
    val officeList = _officesList
    private val _postOffice: SnapshotStateMap<Int?, PostOfficeAdditional?> = mutableStateMapOf()

    fun updateQuery(value: String) {
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

    private fun setOfficeFeatures(postOfficeAdditional: PostOfficeAdditional?) {
        // TODO: There gotta be a better approach than this
        postOfficeAdditional?.data?.run {
            OfficeFeatures.ATM.available = atm.toBoolean()
            OfficeFeatures.POS.available = pos.toBoolean()
            OfficeFeatures.IFS.available = ifs.toBoolean()
            OfficeFeatures.ONE_WINDOW.available = one_window.toBoolean()
            OfficeFeatures.INTERNAL_IFS.available = internal_ifs.toBoolean()
            OfficeFeatures.SPEED_MAIL.available = speed_mail.toBoolean()
        }
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

fun String.toBoolean(): Boolean {
    return when (this.toInt()) {
        0 -> false
        1 -> true
        else -> false
    }
}

enum class OfficeFeatures(
    var stringResource: Int,
    var available: Boolean,
    var icon: ImageVector
) {
    // TODO: Some of these icons aren't exactly what I'm looking after
    ATM(R.string.atm, true, Icons.Outlined.Atm),
    POS(R.string.pos, false, Icons.Outlined.PointOfSale),
    IFS(R.string.ifs, true, Icons.Outlined.SwapVert),
    ONE_WINDOW(R.string.one_window, true, Icons.Outlined.Window),
    INTERNAL_IFS(R.string.internal_ifs, true, Icons.Outlined.SwapHoriz),
    SPEED_MAIL(R.string.speed_mail, true, Icons.Outlined.Mail),
}
