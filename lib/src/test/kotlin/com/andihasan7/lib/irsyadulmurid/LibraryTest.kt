/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.andihasan7.lib.irsyadulmurid

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.math.abs
import com.andihasan7.lib.irsyadulmurid.AwalBulan.IrsyadBulan
import com.andihasan7.lib.irsyadulmurid.WaktuSholat.IrsyadSholat
import com.andihasan7.lib.irsyadulmurid.round
import com.andihasan7.lib.irsyadulmurid.TahwilTarikh.TahwilTarikh
import com.andihasan7.lib.irsyadulmurid.Umum.Umum
import com.andihasan7.lib.irsyadulmurid.MoonPhases.MoonPhases

class LibraryTest {

    // DD° MM` SS,ss`` dibulatkan ke 2 angka di belakang koma
	fun toDegreeFullRound2(decimal: Double): String {
    	var degree = abs(decimal).toInt().toString()
    	var minute = ((abs(decimal) - degree.toDouble()) * 60).toInt().toString()
    	var second = ((((abs(decimal) - degree.toDouble()) * 60) - minute.toDouble()) * 60).round(2).toString()

        // Tambahkan nol sebelum angka yang kurang dari 10
        degree = degree.padStart(2, '0')
        minute = minute.padStart(2, '0')
        second = second.padStart(2, '0')
                
        if (decimal < 0) {
            degree = "-$degree"
        }

    	return "$degree\u00B0 $minute\u2032 $second\u2033"
	}
            
    @Test fun testAwalBulan() {
        /* val classUnderTest = Library()
        assertTrue(classUnderTest, "someLibraryMethod should return 'true'")
        */
        
            

	        val bln = 10
            val thn = 1443
            val lat = -7.464141667 //-7.43333333334 //-7.476111111 // -7.183333333
            val long = 111.145825 //111.43333333334 //111.313055556 // 113.25
            val tZ = 7
            val tt = 202
            val check = true
    
            val b = IrsyadBulan(bln, thn, lat, long, tZ, tt, check)
            
            println("Prediksi = ${b.awalBulanPrediksi()}")
            println("JD Ijtima = ${b.jdIjtima()}")
            println("TGL = ${b.tanggal()}") // variable vrTGLFix
            println("BLN = ${b.bulanString()}")
            println("THN = ${b.tahun()}")
            println("Hari = ${b.hari()}")
            println("Pasaran = ${b.pasaran()}")
            println("Ijtima' = ${toDegreeFullRound2(b.ijtimaWD())}")
            println("Maghrib = ${toDegreeFullRound2(b.maghribFinal())}")
            println("Azimuth Matahari = ${toDegreeFullRound2(b.azimuthMatahariUTSB())}")
            println("T Hilal Hakiki = ${toDegreeFullRound2(b.tinggiHakiki())}")
            println("T Hilal Mar'i Upper = ${toDegreeFullRound2(b.tinggiUpper())}")
            println("T Hilal Mar'i Center = ${toDegreeFullRound2(b.tinggiCenter())}")
            println("T Hilal Mar'i Lower = ${toDegreeFullRound2(b.tinggiLower())}")
            println("Azimuth Bulan = ${toDegreeFullRound2(b.azimuthHilalUTSB())}")
            println("Posisi Hilal = ${toDegreeFullRound2(b.posisiHilal())}")
            println("Posisi Hilal String = ${b.posisiHilalString()}")
            println("Lama Hilal = ${toDegreeFullRound2(b.lamaHilal())}")
            println("Elongasi = ${toDegreeFullRound2(b.elongasi())}")
            println("Nurul Hilal= ${b.nurulHilal()}")
            println("Ghurub Hilal= ${toDegreeFullRound2(b.ghurubHilal())}")
            println("Samkul Hilal= ${b.samkulHilal()}")
            println("Umur Hilal= ${toDegreeFullRound2(b.umurHilal())}")
            
            /*
            val tgl = 30
            val m = 7
            val th = 2023
            val ith = 2
            
            val s = IrsyadSholat(tgl, m, th, lat, long, tZ, tt, ith)
            
            println("Imsak = ${toDegreeFullRound2(s.imsak())}")
            println("Shubuh = ${toDegreeFullRound2(s.shubuh())}")
            println("Terbit = ${toDegreeFullRound2(s.thulu())}")
            println("Dluha = ${toDegreeFullRound2(s.dluha())}")
            println("Dzuhur = ${toDegreeFullRound2(s.dzuhur())}")
            println("Ashar = ${toDegreeFullRound2(s.ashar())}")
            println("Maghrib = ${toDegreeFullRound2(s.maghrib())}")
            println("Isya = ${toDegreeFullRound2(s.isya())}")
            println("T Malam = ${toDegreeFullRound2(s.tengahMalam())}")
            
            println("Deklinasi = ${toDegreeFullRound2(s.deklinasi())}")
            println("EoT = ${toDegreeFullRound2(s.equationOfTime())}")
            println("Semi Diameter = ${toDegreeFullRound2(s.semiDiameter())}")
            println("Arah Qiblat = ${toDegreeFullRound2(s.qiblat())}")
            println("Arah Qiblat = ${toDegreeFullRound2(s.qiblatUTSB())}")
            println("Arah Qiblat = ${toDegreeFullRound2(s.qiblat())}")
            println("Rashdul Qiblat 1 = ${toDegreeFullRound2(s.rashdul1())}")
            println("Rashdul Qiblat 2 = ${toDegreeFullRound2(s.rashdul2())}")
            println("Selisih Jam Markaz ~ Makkah = ${toDegreeFullRound2(s.selisihJam())}")
            println("Jarak Markaz ~ Makkah = ${s.jarakKeduanya()} Km")
            println("S Deklinasi ~ L Ka'bah = ${toDegreeFullRound2(s.selisihLintangK())}")
            println("S Deklinasi ~ L Tempat = ${toDegreeFullRound2(s.selisihLintangT())}")
            */
    }
    @Test
    fun testTahwil() {
        
        val tgl = 1
        val bln = 1
        val thn = 1
        
        val mTH = TahwilTarikh().hijriToMiladi(tgl, bln, thn)
        val hari = mTH[0]
        val pasaran = mTH[1]
        val tanggal = mTH[2]
        val bulan = mTH[3]
        val tahun = mTH[4]
        
        println("$hari $pasaran, $tanggal $bulan $tahun")
    }
    
    @Test
    fun testSelamatan() {
    
        val jd = 2453485.5
    
        val s = Umum.selamatan(jd)
    
        val self = s[0]
        val p7 = s[1]
        val p40 = s[2]
        val p100 = s[3]
        val p1 = s[4]
        val p2 = s[5]
        val sewu = s[6]
    
    
        println("$self")
        println("$p7")
        println("$p40")
        println("$p100")
        println("$p1")
        println("$p2")
        println("$sewu")
    
    }
    @Test
    fun testNewMoon() {
        // new moon
        val bNM = 10
        val thNM = 1443
        val tZN = 7
    
        val n = MoonPhases(bNM, thNM, tZN)
        val dUT = n.doubleNewMoonUT
        val dWD = n.doubleNewMoonWD
        
        println("NewMoonUT = ${toDegreeFullRound2(dUT)}")
        println("NewMoonWD = ${toDegreeFullRound2(dWD)}")
        println("hari int = ${n.intHariNewMoon}")
        println("pasaran int = ${n.intPasaranNewMoon}")
        println("tanggal int = ${n.intTanggalNewMoon}")
        println("bulan int = ${n.intBulanNewMoon}")
        println("tahun int = ${n.intTahunNewMoon}")
        println("hari string = ${n.stringHariNewMoon}")
        println("pasaran string = ${n.stringPasaranNewMoon}")
        println("bulan string = ${n.stringBulanNewMoon}")
    }
    
    @Test
    fun testFirstQuarter() {
        // new moon
        val bNM = 9
        val thNM = 1426
        val tZN = 7
    
        val n = MoonPhases(bNM, thNM, tZN)
        val dUT = n.doubleFirstUT
        val dWD = n.doubleFirstWD
        
        println("FirstUT = ${toDegreeFullRound2(dUT)}")
        println("FirstWD = ${toDegreeFullRound2(dWD)}")
        println("hari int = ${n.intHariFirst}")
        println("pasaran int = ${n.intPasaranFirst}")
        println("tanggal int = ${n.intTanggalFirst}")
        println("bulan int = ${n.intBulanFirst}")
        println("tahun int = ${n.intTahunFirst}")
        println("hari string = ${n.stringHariFirst}")
        println("pasaran string = ${n.stringPasaranFirst}")
        println("bulan string = ${n.stringBulanFirst}")
    }
}
