/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.andihasan7.lib.irsyadulmurid.WaktuSholat

import kotlin.math.abs
import kotlin.math.asin
import kotlin.math.acos
import kotlin.math.atan
import kotlin.math.sin
import kotlin.math.cos
import kotlin.math.tan
import com.andihasan7.lib.irsyadulmurid.round


class IrsyadSholat(
	val date: Int,
    val month: Int,
    val year: Int,
    val latitude: Double,
    val longitude: Double,
    val timeZone: Number,
    val elevation: Number,
    val ihthiyati: Int
    ) {
    
    
	val ihthiyat = (ihthiyati).toDouble() / 60
	
	// koreksi bulan & tahun
	var bulan = if (month < 3) {
			month + 12
		} else {
			month
		}
	
	var tahun = if (month < 3) {
			year - 1
		} else {
			year
		}
		
	// koreksi gergorius
	val krg = (2 - (tahun.toDouble() / 100) + ((tahun.toDouble() / 100) / 4)).toInt()
	
	// julian day
	val jd = ((365.25 * (tahun + 4716)).toInt() + (30.6001 * (bulan + 1)).toInt() + date + (((10 + 23 / 60.0) / 24) + krg) - 1524.5).round(3)

	
	// juz ashal miladiyah
	val jam = ((jd - 2451545) / 36525).round(9)
	
	/* 
		mulai menghitung data matahari
	*/
	
	// wasatus syamsi
	val s = 280.46645 + 36000.76983 * jam
	val frS = (s / 360 - (s / 360).toInt()) * 360
	
	// khooshotus syamsi
	val m = 357.52910 + 35999.05030 * jam
    val frm = (m / 360 - (m / 360).toInt()) * 360
	
	// 'uqdatus syams
	val n = 125.04 - 1934.136 * jam
    val frN = (n / 360 - (n / 360).toInt()) * 360
	
	// tahshishul awwal/ koreksi pertama
	val k1 = (17.264 / 3600) * sin(Math.toRadians(frN)) + (0.206 / 3600) * sin(Math.toRadians(2 * frN))
	
	// tahshishus tsani/ koreksi kedua
    val k2 = (-1.264 / 3600) * sin(Math.toRadians(2 * frS))
	
	// tahshishus tsaalist/ koreksi ketiga
    val r = (9.23 / 3600) * cos(Math.toRadians(frN)) - (0.090 / 3600) * cos(Math.toRadians(2 * frN))
	
	// tahshishur roobi'/ koreksi keempat
	val r1 = (0.548 / 3600) * cos(Math.toRadians(2 * frS))
	
	// mail kulli
    val q = 23.43929111 + r + r1 - (46.8150 / 3600) * jam
	
	// ta'diilus syams
    val e = (6898.06 / 3600) * sin(Math.toRadians(frm)) +  (72.095 / 3600) * sin(Math.toRadians(2 * frm)) + (0.966 / 3600) * sin(Math.toRadians(3 * frm))
	
	// thuulus syams
    val s1 = frS + e + k1 + k2 - (20.47 / 3600)
	
	// mail syams/ deklinasi matahari
    val dek = Math.toDegrees(asin(sin(Math.toRadians(s1)) * sin(Math.toRadians(q))))
	
	// ta'diluz zaman/ equation of time
    val eq = (-1.915 * sin(Math.toRadians(frm)) + (-0.02) * sin(Math.toRadians(2 * frm)) + 2.466 * sin(Math.toRadians(2 * s1)) + (-0.053) * sin(Math.toRadians(4 * s1))) / 15
	
	// semidiameter
	val sd = 0.267 / (1 - 0.017 * cos(Math.toRadians(frm)))
	
	
	/* 
		proses hitung
	*/
	
	// Dzuhur
    val dzuhurWD = 12 - eq + (((timeZone).toDouble() * 15) - longitude) / 15 + ihthiyat
	
	// Ashar
    val hAshar = Math.toDegrees(atan(1.0 / (tan(Math.toRadians(abs(latitude - dek))) + 1)))
	val f = -tan(Math.toRadians(latitude)) * tan(Math.toRadians(dek))
	val gE = cos(Math.toRadians(latitude)) * cos(Math.toRadians(dek))
	val aS = 12 + Math.toDegrees(acos(f + sin(Math.toRadians(hAshar)) / gE)) / 15 + ihthiyat // Ashar Istiwa'
	val asharWD = aS - eq - (longitude - ((timeZone).toDouble() * 15)) / 15
	
	// Maghrib
    val dip = (1.76 / 60) * Math.sqrt((elevation).toDouble()) // konversi ke Double disini untuk memudahkan suatu saat custom tinggi tempat
    // ho Maghrib
    val hM = -(sd + (34.5 / 60) + dip) - 0.0024
    // Maghrib WD
    val tM = 12 + Math.toDegrees(acos(f + sin(Math.toRadians(hM)) / gE)) / 15 + ihthiyat
    // Maghrib WD
    val maghribWD = tM - eq - (longitude - ((timeZone).toDouble() * 15)) / 15
	
	// Isya'
    val hI = -18.0
    // Isya' WIS
    val iS = 12 + Math.toDegrees(acos(f + sin(Math.toRadians(hI)) / gE)) / 15 + ihthiyat 
	// Isya' WD
    val isyaWD = iS - eq - (longitude - ((timeZone).toDouble() * 15)) / 15
	
	// h Shubuh
    val hS = -20.0
    // Shubuh WIS
    val sI = 12 - Math.toDegrees(acos(f + sin(Math.toRadians(hS)) / gE)) / 15 + ihthiyat
    // Shubuh WD
    val shubuhWD = sI - eq - (longitude - ((timeZone).toDouble() * 15)) / 15
	
	// Imsak
    val imsakWD = shubuhWD - (10.0 / 60)
	
	// h Thulu'
    val hT = -(sd + (34.5 / 60) + dip) - 0.0024
    // Thulu' WIS
    val tI = 12 - Math.toDegrees(acos(f + sin(Math.toRadians(hT)) / gE)) / 15 - ihthiyat // Ihthiyat untuk Thulu' dikurangi 2 menit
    // Thulu' WD
    val thuluWD = tI - eq - (longitude - ((timeZone).toDouble() * 15)) / 15
	
	// Dluha
    val hD = 4.5
    // Dluha WIS
    val dL = 12 - Math.toDegrees(acos(f + sin(Math.toRadians(hD)) / gE)) / 15 + ihthiyat
    // Dluha WD
    val dluhaWD = dL - eq - (longitude - ((timeZone).toDouble() * 15)) / 15
	
	// Nishful Lail
    val tengahMalamWD = maghribWD + ((shubuhWD + 24 - maghribWD) / 2) - ihthiyat // dikurangi ihthiyat karena sudah mengambil data maghrib dan shubuh yang sudah ditambah ihthiyat
	
	// Arah Qiblat
    // lintang dan bujur Ka'bah'
    val lK = 21.42191389
    val bK = 39.82951944
	
    // Selisih Bujur
	
    val a = 360 - bK + longitude - 360
	
    val h = Math.toDegrees(asin(sin(Math.toRadians(latitude)) * sin(Math.toRadians(lK)) + cos(Math.toRadians(latitude))  * cos(Math.toRadians(lK)) * cos(Math.toRadians(a))))
	
    // Azimuth U-B
    val aQ = Math.toDegrees(acos((sin(Math.toRadians(lK)) - sin(Math.toRadians(latitude)) * sin(Math.toRadians(h))) / cos(Math.toRadians(latitude)) / cos(Math.toRadians(h))))
	
	// Azimuth B-U
    val bU = 90 - aQ
	
	// Azimuth UTSB
	val p = 360 - aQ
	
	// Roshdul Qiblat
    val b = 90 - latitude 
	
	val pR = Math.toDegrees(atan(1.0 / (cos(Math.toRadians(b)) * tan(Math.toRadians(p)))))
	
	val cA = Math.toDegrees(acos(tan(Math.toRadians(dek)) * tan(Math.toRadians(b)) * cos(Math.toRadians(pR))))
	
	
	// Roshdul Qiblat 1
	
    val rq1 = -((pR + cA) / 15) + 12//% 24
	val rq2 = rq1.mod(24.0)
	
    // Roshdul Qiblat 1 TimeZone
    val rashdu1 = rq2 - eq - (longitude - ((timeZone).toDouble() * 15)) / 15
	
    // Roshdul Qiblat 2
	
    val rQ = -(pR -cA) / 15 + 12
	
    // Roshdul Qiblat 2 TimeZone
    val rashdu2 = rQ - eq - (longitude - ((timeZone).toDouble() * 15)) / 15
    
    // Selisih Jam antara Markaz ~ Makkah
    val selisih = (longitude - bK)
    val selisihJam = selisih / 15
    
    // Jarak antara Markas ~ Makkah (kilometer)
    val nilaiAwal = Math.toDegrees(acos(sin(Math.toRadians(latitude)) * sin(Math.toRadians(lK)) + cos(Math.toRadians(latitude)) * cos(Math.toRadians(lK)) * cos(Math.toRadians(selisih))))
    val jarakKeduanya = nilaiAwal / 360 * 6.283185307 * 6378.388
    
    // selisih deklinasi dengan Lintang Ka'bah
    val selisihLintangK = dek - lK
    
    // selisih deklinasi dengan Lintang Tempat
    val selisihLintangT = dek - latitude
	
	
	
	/*
		fungsi-fungsi
	*/
    
	
	// Imsak WD
	fun imsak(): Double = imsakWD
    
	// Shubuh WD
	fun shubuh(): Double = shubuhWD
    
	// Thulu' WD
	fun thulu(): Double = thuluWD
    
	// Dluha WD
	fun dluha(): Double = dluhaWD
    
    // Dzuhur WD
	fun dzuhur(): Double = dzuhurWD
    
    // Ashar WD
	fun ashar(): Double = asharWD
    
	// Maghrib WD
	fun maghrib(): Double = maghribWD
    
    // Isya' WD
	fun isya(): Double = isyaWD
    
	// Tengah Malam WD
	fun tengahMalam(): Double = tengahMalamWD
	
	
	
	// fungsi getDeklinasi
	fun deklinasi(): Double = dek
	
	// fungsi getEquationOfTime
	fun equationOfTime(): Double = eq
    
    // fungsi semidiameter
    fun semiDiameter(): Double = sd
	
	// fungsi Arah Qiblat B-U/B-S
	fun qiblat(): Double = bU
	
	// fungsi Arah Qiblat UTSB
	fun qiblatUTSB(): Double = p
	
	// fungsi Rashdul Qiblat 1
	fun rashdu1(): Double = rashdu1
	
	// fungsi Rashdul Qiblat 2
	fun rashdu2(): Double = rashdu2
    
    // Selisih Jam antara Markaz ~ Makkah
    fun selisihJam(): Double = selisihJam
    
    // Jarak antara Markaz ~ Makkah (kilometer)
    fun jarakKeduanya(): Double = jarakKeduanya
    
    // selisih deklinasi dengan Lintang Ka'bah
    fun selisihLintangK(): Double = selisihLintangK
    
    // selisih deklinasi dengan Lintang Tempat
    fun selisihLintangT(): Double = selisihLintangT
}