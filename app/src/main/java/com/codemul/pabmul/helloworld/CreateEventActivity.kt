package com.codemul.pabmul.helloworld

import android.Manifest
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.codemul.pabmul.helloworld.data.Event
import com.codemul.pabmul.helloworld.databinding.ActivityCreateEventBinding
import com.codemul.pabmul.helloworld.databinding.ActivityDetailEventBinding
import com.codemul.pabmul.helloworld.db.RealtimeDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import java.util.*


class CreateEventActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCreateEventBinding

    var selectImagePath: String? = null
    private var imageUrl : Uri? = null
    private lateinit var AwalTanggal: String
    private lateinit var AkhirTanggal : String
    private lateinit var awalDaftar : String
    private lateinit var akhirDaftar : String
    private var storage : FirebaseStorage? = null
    private var databaseRef: DatabaseReference? = null
    private var database : FirebaseDatabase? = null



//    lateinit var event : Event
//
//    private val storage = RealtimeDatabase.instances()
//    private var storageReference : StorageReference? =null
//    private var uploadTask: StorageTask<*>? = null
//    private var databaseRef: DatabaseReference? = null

//    private val db = RealtimeDatabase.instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Buat Event"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityCreateEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        databaseRef = Firebase.database.reference.child("event")
        storage = FirebaseStorage.getInstance()

        binding.chooseImg.setOnClickListener {
            if (ContextCompat.checkSelfPermission(applicationContext,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_PERMISSIONS)
            } else {
                selectImage()
            }
        }

        binding.tglEvent.setOnClickListener{
            val cal = Calendar.getInstance()
            val date = DatePickerDialog(this,
                { _, year, monthOfYear, dayOfMonth ->
                    val newDate = Calendar.getInstance()
                    newDate[year, monthOfYear] = dayOfMonth
                    val bulan = arrayOf("Januari", "Februari", "Maret", "April", "Mei",
                        "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")
                    AwalTanggal = dayOfMonth.toString() + " " + bulan[monthOfYear] + " " + year
                    binding.tglEvent.setText(AwalTanggal)
                },cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))

            date.show()
        }

        binding.tglAkhirEvent.setOnClickListener{
            val cal = Calendar.getInstance()
            val date = DatePickerDialog(this,
                { _, year, monthOfYear, dayOfMonth ->
                    val newDate = Calendar.getInstance()
                    newDate[year, monthOfYear] = dayOfMonth
                    val bulan = arrayOf("Januari", "Februari", "Maret", "April", "Mei",
                        "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")
                    AkhirTanggal = dayOfMonth.toString() + " " + bulan[monthOfYear] + " " + year
                    binding.tglAkhirEvent.setText(AkhirTanggal)
                },cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))

            date.show()
        }

        binding.tglAwalDaftar.setOnClickListener{
            val cal = Calendar.getInstance()
            val date = DatePickerDialog(this,
                { _, year, monthOfYear, dayOfMonth ->
                    val newDate = Calendar.getInstance()
                    newDate[year, monthOfYear] = dayOfMonth
                    val bulan = arrayOf("Januari", "Februari", "Maret", "April", "Mei",
                        "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")
                    awalDaftar = dayOfMonth.toString() + " " + bulan[monthOfYear] + " " + year
                    binding.tglAwalDaftar.setText(awalDaftar)
                },cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))

            date.show()
        }

        binding.tglAkhir.setOnClickListener{
            val cal = Calendar.getInstance()
            val date = DatePickerDialog(this,
                { _, year, monthOfYear, dayOfMonth ->
                    val newDate = Calendar.getInstance()
                    newDate[year, monthOfYear] = dayOfMonth
                    val bulan = arrayOf("Januari", "Februari", "Maret", "April", "Mei",
                        "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")
                    akhirDaftar = dayOfMonth.toString() + " " + bulan[monthOfYear] + " " + year
                    binding.tglAkhir.setText(akhirDaftar)
                },cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))

            date.show()
        }

        binding.btnAddEvent.setOnClickListener {
            setDataToFirebase()
            Toast.makeText(this, "Daftar berhasil", Toast.LENGTH_SHORT).show()

            //masuk ke main penyelenggara
//            startActivity(Intent(MainActivity))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {
        super.onActivityResult(requestCode,
            resultCode,
            data)

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {

            // Get the Uri of data
            imageUrl = data.data
            binding.imgEvent.setImageURI(imageUrl)
        }
    }

    private fun setDataToFirebase()
    {
        val event = Event()
        event.id = UUID.randomUUID().toString()
        event.name = binding.nameEvent.text.toString().trim()
//        event.image = uploadImage().toString()
        event.tgl_event = binding.tglEvent.text.toString().trim()
        event.tgl_akhir = binding.tglAkhirEvent.text.toString().trim()
        event.tgl_daftar = binding.tglAwalDaftar.text.toString().trim()
        event.tgl_akhir_daftar = binding.tglAkhir.text.toString().trim()
        event.fee = Integer.valueOf(binding.fee.text.toString())
        event.contact = binding.cp.text.toString().trim()
        event.contact2 = binding.cp2.text.toString().trim()
        event.venue = binding.venue.text.toString().trim()

        val filePath = storage!!.reference.child("event").child(imageUrl!!.lastPathSegment!!)
        filePath.putFile(imageUrl!!).addOnSuccessListener { taskSnapshot ->
            val downloadUrl = taskSnapshot.storage.downloadUrl.addOnCompleteListener { task ->
                val t = task.result.toString()
                val newPost = databaseRef!!.push()
                newPost.child("name").setValue(event.name)
                newPost.child("image").setValue(task.getResult().toString())
                newPost.child("id").setValue(event.id)
                newPost.child("tgl_event").setValue(event.tgl_event)
                newPost.child("tgl_akhir").setValue(event.tgl_akhir)
                newPost.child("tgl_daftar").setValue(event.tgl_daftar)
                newPost.child("tgl_akhir_daftar").setValue(event.tgl_akhir_daftar)
                newPost.child("fee").setValue(event.fee)
                newPost.child("contact").setValue(event.contact)
                newPost.child("contact2").setValue(event.contact2)
            }
        }
    }

//    private fun getFileExtension(uri: Uri): String?{
//        val cr = contentResolver
//        val mime = MimeTypeMap.getSingleton()
//
//        return mime.getExtensionFromMimeType(cr.getType(uri))
//    }


    private fun selectImage() {

        // Defining Implicit Intent to mobile gallery
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(
                intent,
                "Select Image from here..."),
            PICK_IMAGE_REQUEST)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSIONS && grantResults.size > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectImage()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }


    companion object {
        private const val REQUEST_PERMISSIONS = 3
        private const val REQUEST_SELECTS = 4
        private const val PICK_IMAGE_REQUEST = 22
        const val EXTRA_NOTE = "extra_note"
        const val EXTRA_POSITION = "extra_position"
    }
}