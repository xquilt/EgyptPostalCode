package com.polendina.egyptpostalcode

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Atm
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.PointOfSale
import androidx.compose.material.icons.outlined.SwapHoriz
import androidx.compose.material.icons.outlined.SwapVert
import androidx.compose.material.icons.outlined.Window
import androidx.compose.ui.graphics.vector.ImageVector
import com.polendina.egyptpostalcode.domain.model.PostOffice

val offices = listOf<PostOffice>(
    PostOffice( tel_code =  "047", visits =  "10029", id =  "1585", postal_code =  "33757", office =  "مكتب بريد مجاز الحامول", address =  "مجاز الحامول الحامول برارى", tel =  "3825171", link =  "https://egpostal.com/ar/مكتب-بريد-مجاز-الحامول" ),
    PostOffice( tel_code =  "048", visits =  "11334", id =  "1325", postal_code =  "32657", office =  "مكتب بريد شنتنا الحجر", address =  "ش داير الناحية شنتنا الحجر  بركة السبع", tel =  "2997011", link =  "https://egpostal.com/ar/مكتب-بريد-شنتنا-الحجر" ),
    PostOffice( tel_code =  "013", visits =  "14000", id =  "532", postal_code =  "13637", office =  "مكتب بريد عزبة زكى", address =  "قرية عزبة زكى بنها  ", tel =  "3233801", link =  "https://egpostal.com/ar/مكتب-بريد-عزبة-زكى" ),
    PostOffice( tel_code =  "013", visits =  "18910", id =  "689", postal_code =  "13887", office =  "مكتب بريد كفر رجب", address =  "قرية كفر رجب ", tel =  "2554456", link =  "https://egpostal.com/ar/مكتب-بريد-كفر-رجب" ),
    PostOffice( tel_code =  "03", visits =  "175369", id =  "815", postal_code =  "21928", office =  "مكتب بريد العجمى البيطاش", address =  "مساكن المعلمين بيطاش / الدخيلة", tel =  "0", link =  "https://egpostal.com/ar/مكتب-بريد-العجمى-البيطاش" ),
    PostOffice( tel_code =  "048", visits =  "10104", id =  "1422", postal_code =  "32855", office =  "مكتب بريد جزيرة الحجر", address =  "ش داير الناحية قرية نادر / الشهداء", tel =  "2773574", link =  "https://egpostal.com/ar/مكتب-بريد-جزيرة-الحجر" ),
    PostOffice( tel_code = "040", visits = "6085", id = "1256", postal_code = "31859", office = "مكتب بريد ابو النجا", address = "المحلة الكبرى / ابو النجا ", tel = "2025517", link = "https://egpostal.com/ar/مكتب-بريد-ابو-النجا" ),
    PostOffice( tel_code =  "040", visits =  "79789", id =  "1049", postal_code =  "31589", office =  "مكتب بريد قسم تفيش الجميزة", address =  "السنطة ثانى", tel =  "5344467", link =  "https://egpostal.com/ar/مكتب-بريد-قسم-تفيش-الجميزة" ),
    PostOffice( tel_code =  "040", visits =  "14859", id =  "1087", postal_code =  "31649", office =  "مكتب بريد الغريب مركز زفتى", address =  "الغريب/ مركز زفتى ", tel =  "5632943", link =  "https://egpostal.com/ar/مكتب-بريد-الغريب-مركز-زفتى" ),
    PostOffice( tel_code = "040", visits = "9233", id = "1237", postal_code = "31837", office = "مكتب بريد السجاعية", address = "بجوار نقطة الشرطة", tel = "2062023", link = "https://egpostal.com/ar/مكتب-بريد-السجاعية" )
)

enum class OfficeFeatures(var stringResource: Int, var available: Boolean, var icon: ImageVector) {
    // todo: Some of these icons aren't exactly what I'm looking after
    ATM(R.string.atm, true, Icons.Outlined.Atm),
    POS(R.string.pos, true, Icons.Outlined.PointOfSale),
    IFS(R.string.ifs, false, Icons.Outlined.SwapVert),
    ONE_WINDOW(R.string.one_window, true, Icons.Outlined.Window),
    INTERNAL_IFS(R.string.internal_ifs, false, Icons.Outlined.SwapHoriz),
    SPEED_MAIL(R.string.speed_mail, true, Icons.Outlined.Mail),
}

val iconList = listOf(
    Icons.Outlined.Atm,
    Icons.Outlined.AttachMoney,
    Icons.Outlined.AttachMoney,
    Icons.Outlined.AttachMoney,
    Icons.Outlined.AttachMoney,
    Icons.Outlined.AttachMoney,
)

