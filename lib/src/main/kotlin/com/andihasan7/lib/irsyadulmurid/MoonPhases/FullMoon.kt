package com.andihasan7.lib.irsyadulmurid.MoonPhases

import kotlin.math.sin
import kotlin.math.pow
import com.andihasan7.lib.irsyadulmurid.round
import com.andihasan7.lib.irsyadulmurid.AwalBulan.TanggalHarpas

class FullMoon(val month: Int, val year: Int, timeZone: Number) {
	
    
    // HY
    val vrHY = year.toDouble() + ((month.toDouble() * 29.53) / 354.3671) 
    // K
    val vrK = (((vrHY - 1410) * 12) - 0.5).round(1)
    // T
    val vrT = vrK.toDouble() / 1200.0
    // JD
    val vrJD = 2447740.652 + 29.53058868 * vrK + 0.0001178 * vrT.pow(2)
    // M
    val vrM = (((207.9587074 + 29.10535608 * vrK + -0.0000333 * vrT.pow(2)) / 360.0) * 360).mod(360.0)
    // M'
    val vrM1 = (((111.1791307 + 385.81691806 * vrK + 0.0107306 * vrT.pow(2)) / 360) * 360.0).mod(360.0)
    // F
    val vrF = (((164.2162296 + 390.67050646 * vrK + -0.0016528 * vrT.pow(2)) / 360.0) * 360).mod(360.0)
    // T1
    val vrT1 = (0.1734 - 0.000393 * vrT) * sin(Math.toRadians(vrM))
    val vrT2 = 0.0021 * sin(Math.toRadians(2 * vrM))
    val vrT3 = -0.4068 * sin(Math.toRadians(vrM1))
    val vrT4 = 0.0161 * sin(Math.toRadians(2 * vrM1))
    val vrT5 = -0.0004 * sin(Math.toRadians(3 * vrM1))
    val vrT6 = 0.0104 * sin(Math.toRadians(2 * vrF))
    val vrT7 = -0.0051 * sin(Math.toRadians(vrM + vrM1))
    val vrT8 = -0.0074 * sin(Math.toRadians(vrM - vrM1))
    val vrT9 = 0.0004 * sin(Math.toRadians(2 * vrF + vrM))
    val vrT10 = -0.0004 * sin(Math.toRadians(2 * vrF - vrM))
    val vrT11 = -0.0006 * sin(Math.toRadians(2 * vrF + vrM1))
    val vrT12 = 0.0010 * sin(Math.toRadians(2 * vrF - vrM1))
    val vrT13 = 0.0005 * sin(Math.toRadians(vrM + 2 * vrM1))
    // MT
    val vrMT = vrT1 + vrT2 + vrT3 + vrT4 + vrT5 + vrT6 + vrT7 + vrT8 + vrT9 + vrT10 + vrT11 + vrT12 + vrT13
    // JD Full Moon
    val vrJDFullMoon = (vrJD + 0.5 + vrMT).round(3)
    
    // Waktu First Quarter Waktu UT Fix
    val vrWFUT = ((vrJDFullMoon).mod(1.0) * 24)
    val vrWFMWD = vrWFUT + timeZone.toDouble() // masih diatas 24 jam
    // Waktu Full Moon Waktu Daerah Fix
    val vrWFFWD =
    	if (vrWFMWD >= 24) {
        	vrWFMWD - 24
    	} else {
        	vrWFMWD
        }
        
    val t = TanggalHarpas(vrJDFullMoon, vrWFMWD)
    val hariFullInt = t.hari
    val pasaranFullInt = t.pasaran
    val tanggalFullInt = t.vrTGLFix
    val bulanFullInt = t.vrBLNFix
    val tahunFullInt = t.vrTHN
    
    val hariFullString = t.hari()
    val pasaranFullString = t.pasaran()
    val bulanFullString = t.fnBLNString()
}