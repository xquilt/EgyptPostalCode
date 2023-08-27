package com.polendina.egyptpostalcode.data.repository

import java.security.MessageDigest
import java.util.Calendar
import java.util.TimeZone

fun generateHeaders(): Map<String, String> {
    val id = Calendar.getInstance(TimeZone.getTimeZone("GMT")).timeInMillis.toString()
    val auth = mapOf(
        "sender" to encode(paramString = "com.egyptcodebase.egyptcodebase"),
        "id" to id,
        "token" to encode(paramString = id)
    )
    return (mapOf(
        "Host" to "egpostal.com",
//        "Auth" to Base64.getEncoder().encodeToString(Json.encodeToString(auth).toByteArray()),
        "Auth" to "eyJzZW5kZXIiOiJhNTBlN2EyMGFkNjJiZDQ1ZWNhMjZjYTJjNDUwOTYxYyIsImlkIjoiMTY5MDM3NTUwODAyNCIsInRva2VuIjoiMWQ4YjA2ZDExYzBmOGYwNmI0NzFhZmUxOTY0YzEyODQifQ==",
//        "Accept-Encoding" to "gzip, deflate",
        "User-Agent" to "okhttp/3.9.0",
        "Connection" to "close"
    ))
}

private fun encode(paramString: String): String {
    val byteArray = MessageDigest
        .getInstance("MD5")
        .apply {
            this.update(paramString.toByteArray())
        }
        .digest()
    val stringBuffer = StringBuffer()
    for (byte in byteArray) {
        val hexString = "%02x".format(byte)
        stringBuffer.append(hexString)
    }
    return (stringBuffer.toString())
}

//fun encodeString(value: String) {
//    val messageDigest = MessageDigest.getInstance("MD5")
//    messageDigest.update(value.toByte())
//    val byteArray = messageDigest.digest()
//    val stringBuffer = StringBuffer()
//    for (i in 0..byteArray.size) {
//        for (paramString = Integer.toHexString(byteArray[i] & 0xFF)) {
//
//        }
//    }
//}

fun makeRequest() {
    val headers = generateHeaders()
    val connectTimeOutMillis = 5000L
    println("""
        {"data":[{"tel_code":"02","visits":"1717856","id":"2","postal_code":"11511","office":"\u0645\u0643\u062a\u0628 \u0628\u0631\u064a\u062f \u0627\u0644\u0642\u0627\u0647\u0631\u0629 \u0627\u0644\u0631\u0626\u064a\u0633\u0649","address":"\u0645\u064a\u062f\u0627\u0646 \u0627\u0644\u0639\u062a\u0628\u0629\/1\u0634 \u0639\u0628\u062f \u0627\u0644\u062e\u0627\u0644\u0642 \u062b\u0631\u0648\u062a","tel":"23961695","link":"https:\/\/egpostal.com\/ar\/\u0645\u0643\u062a\u0628-\u0628\u0631\u064a\u062f-\u0627\u0644\u0642\u0627\u0647\u0631\u0629-\u0627\u0644\u0631\u0626\u064a\u0633\u0649"},{"tel_code":"02","visits":"61983","id":"17","postal_code":"11529","office":"\u0645\u0643\u062a\u0628 \u0628\u0631\u064a\u062f \u0645\u0631\u0648\u0631 \u0627\u0644\u0642\u0627\u0647\u0631\u0629","address":" \u0645\u0628\u0646\u0649 \u0625\u062f\u0627\u0631\u0629 \u0645\u0631\u0648\u0631 \u0627\u0644\u0642\u0627\u0647\u0631\u0629 \/ \u0627\u0644\u0639\u062a\u0628\u0629 ","tel":"24855408","link":"https:\/\/egpostal.com\/ar\/\u0645\u0643\u062a\u0628-\u0628\u0631\u064a\u062f-\u0645\u0631\u0648\u0631-\u0627\u0644\u0642\u0627\u0647\u0631\u0629"},{"tel_code":"02","visits":"76357","id":"39","postal_code":"11567","office":"\u0645\u0643\u062a\u0628 \u0628\u0631\u064a\u062f \u0628\u0631\u062c \u0627\u0644\u0642\u0627\u0647\u0631\u0629","address":"\u0645\u0628\u0646\u0649 \u0627\u0644\u0628\u0631\u062c \/ \u0627\u0644\u062d\u0632\u064a\u0631\u0629 \/ \u0642\u0635\u0631 \u0627\u0644\u0646\u064a\u0644","tel":"","link":"https:\/\/egpostal.com\/ar\/\u0645\u0643\u062a\u0628-\u0628\u0631\u064a\u062f-\u0628\u0631\u062c-\u0627\u0644\u0642\u0627\u0647\u0631\u0629"},
    """.trimIndent().toByteArray(Charsets.UTF_8).decodeToString())
}
