package phil.homework.week1day5take3_feliznavidrawer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_emi_calculator.*

class EmiCalculatorActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emi_calculator)

        sbPrinciple.max = 11
        sbInterest.max = 41
        sbDuration.max = 45
    }
}
