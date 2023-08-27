package com.polendina.egyptpostalcode.domain.model
data class OfficeList(
    val data: List<PostOffice>
) {
}

/**
 * A data class representing each postal office.
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

data class OfficeResponse (
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
