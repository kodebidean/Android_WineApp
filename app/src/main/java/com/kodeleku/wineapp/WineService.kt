package com.kodeleku.wineapp

import retrofit2.http.GET

interface WineService {
    @GET(Constants.PATH_WINES)
    // Preparado para Corrutinas -> suspend
    suspend fun getRedWines() : List<Wine>
}