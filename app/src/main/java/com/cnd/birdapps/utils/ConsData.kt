package com.cnd.birdapps.utils

/**
 ** Written by CND_Studio 14/03/2021 00.09.
 ** Author @JoeFachrizal
 ** Happy Code...
 **/
object ConsData {
    const val SUCCESS = "success"
    const val PUBLIK = 0
    const val ADMIN = 1
    const val USER = 2
    const val USER_ADD = "3"

    var stateLogin = 0
    var role: Int = 0
    var name: String = ""
    var userID: Int = 0
    var username: String = ""

    var stateKategory = ""
    const val KAT_ANIS ="Anis"
    const val KAT_BEO ="Beo"
    const val KAT_JALAK ="Jalak"
    const val KAT_LOVEBIRD ="Lovebird"
    const val KAT_MORE =""

    var birdSpecies = ""
    const val LOVE_BIRD ="loveBird"
    const val KENARI ="kenari"
    const val JALAK_RIO ="jalakrio"
    const val CILILIN ="cililin"
    const val CC_JENGGOT ="jenggot"
}