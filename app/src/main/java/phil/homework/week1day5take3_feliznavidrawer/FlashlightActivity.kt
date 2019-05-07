package phil.homework.week1day5take3_feliznavidrawer

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.widget.Toast

class FlashlightActivity : BaseActivity() {

    lateinit var fab: FloatingActionButton
    lateinit var layout: ConstraintLayout
    lateinit var handler: Handler

    //lateinit var flashLightStatus: Boolean
    //lateinit var cameraRequest: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashlight)

        val hasCameraFlash = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
        //var isEnabled = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

        layout = findViewById(R.id.flashlight_layout)
        handler = Handler()
        fab = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            /*if (hasCameraFlash) {
                if (ContextCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                )
                    lightsOn()
                else
                    requestCameraFlashPermission()
            } else {
                notifyNoCameraFlashEnabled()
            }*/
            notifyNoCameraFlashEnabled()
        }
    }

    fun requestCameraFlashPermission() {

    }

    fun notifyNoCameraFlashEnabled() {
        Toast.makeText(applicationContext, "Alas! For the lack of the light (features on your phone.", Toast.LENGTH_LONG).show()
        handler.postDelayed({
            Toast.makeText(applicationContext, "Get a better phone, bro. (or sis)", Toast.LENGTH_SHORT ).show()
        }, 4500)
        handler.postDelayed({
            Toast.makeText(applicationContext, "(Or any derogatory pronoun of your choice, gendered or un-)", Toast.LENGTH_SHORT ).show()
        }, 7500)
        handler.postDelayed({
            Toast.makeText(applicationContext, "But seriously, who doesn't have flash?", Toast.LENGTH_SHORT ).show()
        }, 12000)
        handler.postDelayed({
            Toast.makeText(applicationContext, "It's OK, just...", Toast.LENGTH_SHORT ).show()
        }, 15000)
        handler.postDelayed({
            Toast.makeText(applicationContext, "Do better next time.", Toast.LENGTH_SHORT ).show()
        }, 17000)
    }

    fun lightsOn() {
        Toast.makeText(applicationContext, "Lo!", Toast.LENGTH_SHORT).show()
        layout.setBackgroundColor(resources.getColor(R.color.colorAccent))
        fab.setBackgroundColor(resources.getColor(R.color.background_material_light))
        handler.postDelayed({
            lightsOff()
        }, 2000)
    }

    fun lightsOff() {
        fab.setBackgroundColor(resources.getColor(R.color.colorAccent))
        layout.setBackgroundColor(resources.getColor(R.color.background_material_light))
    }
}
