package com.imysko.data.offers.mock.utils

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.imysko.data.offers.domain.entities.OfferId

class OfferIdJsonAdapter : TypeAdapter<OfferId>() {

    override fun write(out: JsonWriter, value: OfferId) {
        out.value(value.name.lowercase())
    }

    override fun read(input: JsonReader): OfferId = OfferId.valueOf(input.nextString().uppercase())
}
