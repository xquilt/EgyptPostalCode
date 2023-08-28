package com.polendina.egyptpostalcode.domain.model
data class OfficeList(
    val data: List<PostOffice>
) {
}

/**
 * A data class representing main information about each postal office.
 */
data class PostOffice (
    val tel_code: String,
    val visits: String,
    val id: String,
    val postal_code: String,
    val office: String,
    val address: String,
    val tel: String,
    val link: String
)

/**
 * A data class representing main information about each postal office.
 */
data class PostOfficeAdditional(
    val data: Data
) {
    data class Data(
        val id: String,
        val postal_code: String,
        val financial_code: String,
        val address_ar: String,
        val office_ar: String,
        val sector_ar: String,
        val address_en: String,
        val office_en: String,
        val sector_en: String,
        val tel: String,
        val atm: String,
        val pos: String,
        val ifs: String,
        val one_window: String,
        val noon: String,
        val speed_mail: String,
        val internal_ifs: String,
        val extra_address: String,
        val visits: String,
        val province_id: String,
        val sun_thr_3: String,
        val sat_wed_3: String,
        val sat_thr_2: String,
        val fri_3: String,
        val lat: String,
        val lng: String,
        val no_location_use: String,
        val auto_geocoded: String,
        val office: String,
        val address: String,
        val sector: String,
        val tel_code: String
    )
}

/**
 * Data class modeling the API response of each Post office containing its additional metadata.
 *
 */
data class Office (
    val data: List<PostOffice>,
    val settings: Settings
) {
    data class Settings (
        val app_version: Int,
        val disable_ads: Boolean,
        val popup_frequency: Int
    )
}

data class ShipmentResponse (
    val data: List<Shipment>
) {
    data class Shipment (
        val time: String,
        val country: String,
        val location: String,
        val event_type: String,
        val extra_info: String
    )
}
