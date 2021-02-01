import android.util.Log
import com.google.errorprone.annotations.Var

class User(var id: String?, var name: String?, var weight: Double?, var height: Double?, var sex: String?, var age: Double?, var target: Double?, var activity: Double? ) {

    constructor() : this(null, null, null, null, null, null, null, null)

    fun getBMI(): Double? {
        if (height != null && weight != null) {

            var meterHeight = height!! / 100;
            var BMI = ( weight!! / (meterHeight * meterHeight))

            return BMI

        } else return null
    }

    fun getBMR(): Double?{
        if(getPPM() == null){

            return null
        }else{

            var PPM = getPPM()!!.toDouble()
            var BMR: Double? = null

            if(target != null && activity != null){
                BMR = (PPM * activity!!) + target!!

            }
            return BMR
        }
    }

    fun getPPM(): Int? {
        var PPM: Double

        if (height != null && weight != null && age != null && sex != null) {
            // PPM dla kobiet
            if (sex == "Kobieta") {
                PPM = 655.0955 + (9.5634 * weight!!) + (1.8496 * height!!) - (4.6756 * age!!)
                return PPM.toInt()

                // PPM dla mężczyzn
            } else if (sex == "Mężczyzna") {
                PPM = 66.4730 + (13.7516 * weight!!) + (5.033 * height!!) - (6.7550 * age!!)
                return PPM.toInt()

            } else return null

        } else return null

    }

    fun getMacroElements() : Map<String, Double?> {
        var proteins: Double?
        var fat: Double?
        var carbohydrates: Double?
        var BMR = getBMR()

        if(BMR != null){
            proteins = 2 * weight!!
            fat = (BMR!! * 0.27) / 9
            carbohydrates = (BMR - (proteins * 4 + fat * 9)) / 4

        }else{
            proteins = null
            fat = null
            carbohydrates = null
        }


        var macroElements = mapOf<String, Double?>(
            "fat" to fat,
            "proteins" to proteins,
            "carbohydrates" to carbohydrates
        )

        return macroElements
    }


}