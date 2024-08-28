package com.imysko.data.offers

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.imysko.common.core.utils.readJsonFromAssets
import com.imysko.data.offers.entities.Offer
import com.imysko.data.offers.entities.OfferId
import com.imysko.data.offers.utils.OfferIdJsonAdapter
import javax.inject.Inject

internal class MockOffersRepository @Inject constructor(
    private val context: Context
) : OffersRepository {

    override suspend fun getOffersList(): List<Offer> {
        val gson = GsonBuilder().registerTypeAdapter(
            OfferId::class.java,
            OfferIdJsonAdapter().nullSafe(),
        ).create()

        val jsonString = readJsonFromAssets(context, FILE_NAME)
        val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)
        val offersJsonArray = jsonObject.getAsJsonArray(OFFERS_KEY)

        return gson.fromJson(offersJsonArray, object : TypeToken<List<Offer>>() {}.type)
    }

    companion object {

        const val FILE_NAME = "mock.json"

        const val OFFERS_KEY = "offers"
    }
}
