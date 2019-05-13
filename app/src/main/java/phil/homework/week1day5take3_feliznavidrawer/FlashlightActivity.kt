package phil.homework.week1day5take3_feliznavidrawer

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Camera
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast

class FlashlightActivity : BaseActivity() {

    lateinit var fab: FloatingActionButton
    lateinit var layout: ConstraintLayout
    lateinit var handler: Handler

    lateinit var cameraManager: CameraManager
    lateinit var cameraId: String

    private val FLASH_IS_ENABLED = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashlight)

        val hasCameraFlash = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        cameraId = cameraManager.cameraIdList[0]

        layout = findViewById(R.id.flashlight_layout)
        handler = Handler()
        fab = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            if (hasCameraFlash) {
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
            }
        }
    }

    private fun requestCameraFlashPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), FLASH_IS_ENABLED)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            FLASH_IS_ENABLED -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    lightsOn()
                } else {
                    Toast.makeText(applicationContext, "Y u no let flash go?", Toast.LENGTH_LONG).show()
                    handler.postDelayed({
                        Toast.makeText(applicationContext, "Flash traitor.", Toast.LENGTH_SHORT).show()
                    }, 2500)
                }
                return
            }
            else -> {

            }
        }

    }

    private fun notifyNoCameraFlashEnabled() {
        Toast.makeText(
            applicationContext,
            "Your phone doesn't support camera flash. Alas for the dying of the light!",
            Toast.LENGTH_LONG
        ).show()
        handler.postDelayed({
            Toast.makeText(applicationContext, "In other words:", Toast.LENGTH_SHORT).show()
        }, 4500)
        handler.postDelayed({
            Toast.makeText(applicationContext, "Get a better phone, bro!", Toast.LENGTH_LONG).show()
        }, 6500)
        handler.postDelayed({
            Toast.makeText(applicationContext, "(or sis)", Toast.LENGTH_SHORT).show()
        }, 9000)
        handler.postDelayed({
            Toast.makeText(applicationContext, "(Or any derogatory pronoun of your choice, gendered or non-)", Toast.LENGTH_LONG).show()
        }, 11000)
        handler.postDelayed({
            Toast.makeText(applicationContext, "But seriously, who doesn't have flash?", Toast.LENGTH_SHORT).show()
        }, 14500)
        handler.postDelayed({
            Toast.makeText(applicationContext, "It's OK, just...", Toast.LENGTH_SHORT).show()
        }, 16500)
        handler.postDelayed({
            Toast.makeText(applicationContext, "Do better next time.", Toast.LENGTH_LONG).show()
        }, 18500)
    }

    fun lightsOn() {
        Toast.makeText(applicationContext, "Lo!", Toast.LENGTH_SHORT).show()
        layout.setBackgroundColor(resources.getColor(R.color.colorAccent))
        fab.setBackgroundColor(resources.getColor(R.color.background_material_light))
        cameraManager.setTorchMode(cameraId, true)
        handler.postDelayed({
            lightsOff()
        }, 2000)
    }

    fun lightsOff() {
        fab.setBackgroundColor(resources.getColor(R.color.colorAccent))
        layout.setBackgroundColor(resources.getColor(R.color.background_material_light))
        cameraManager.setTorchMode(cameraId, false)
    }
}
