class User(var id: String?, var name: String?, var weight: Double?, var height: Double?, var sex: String?, var age: Double? ) {

    constructor() : this(null, null, null, null, null, null)

    fun getBMI(): Double? {
        if (height != null && weight != null) {

            var meterHeight = height!! / 100;
            var BMI = weight!! / meterHeight * meterHeight

            return BMI

        } else return null
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


}