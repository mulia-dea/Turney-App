package com.codemul.pabmul.helloworld

import android.Manifest
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codemul.pabmul.helloworld.db.DatabaseHelper
import java.text.SimpleDateFormat
import java.util.*

import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.Image
import android.net.Uri
import android.provider.MediaStore
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.codemul.pabmul.helloworld.data.DataEvent
//import com.codemul.pabmul.helloworld.data.Utility
//import com.google.android.gms.cast.framework.media.ImagePicker



class DaftarEventActivity : AppCompatActivity(){

    private lateinit var btnAddEvent : Button
    private lateinit var edtTglEvent : EditText
    private lateinit var edtTglAkhir : EditText
    private lateinit var edtName : EditText
    private lateinit var edtFee : EditText
    private lateinit var edtVenue : EditText
    private lateinit var edtCp : EditText
    private lateinit var imageBtn : Button
    private lateinit var img_event : ImageView
    private lateinit var AwalTanggal : String
    private lateinit var AkhirTanggal : String
    var selectImagePath: String? = null

    private var event: DataEvent? = null

    var Gallery_Request_Code :Int = 100
    var REQUEST_CODE_GALLERY = 999

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_event)
        val db = DatabaseHelper(this)
        db.open()

        edtName = findViewById(R.id.name_event)
        imageBtn = findViewById(R.id.choose_img)
        img_event = findViewById(R.id.img_event)
        edtTglEvent = findViewById(R.id.tgl_event)
        edtTglAkhir = findViewById(R.id.tgl_akhir)
        edtFee = findViewById(R.id.fee)
        edtVenue = findViewById(R.id.venue)
        edtCp = findViewById(R.id.cp)
        btnAddEvent = findViewById(R.id.btn_add_event)
//
//        val name = edtName.text.toString()
//        val fee = edtFee.text.toString()
//        val venue = edtVenue.text.toString()
//        val cp = edtCp.text.toString()
        edtTglEvent.inputType = InputType.TYPE_NULL;
        edtTglAkhir.inputType = InputType.TYPE_NULL;
//        edtTglEvent.requestFocus();

//        val name = edtName.text.toString()
////            val image = img_event.setImageBitmap()
////        val fee = edtFee.text.toString()
//        val venue = edtVenue.text.toString()
//        val cp = edtCp.text.toString()
//        val tgl_event = edtTglEvent.text.toString()
//        val tgl_akhir = edtTglAkhir.text.toString()

        imageBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(applicationContext,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_PERMISSION)
            } else {
                selectImage()
            }
        }

        edtTglEvent.setOnClickListener{
            val cal = Calendar.getInstance()

            val date = DatePickerDialog(this,
                { _, year, monthOfYear, dayOfMonth ->
                    val newDate = Calendar.getInstance()
                    newDate[year, monthOfYear] = dayOfMonth
                    val bulan = arrayOf("Januari", "Februari", "Maret", "April", "Mei",
                        "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")
                    AwalTanggal = dayOfMonth.toString() + " " + bulan[monthOfYear] + " " + year
                    edtTglEvent.setText(AwalTanggal)
                },cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))

            date.show()
        }

        edtTglAkhir.setOnClickListener{
            val cal = Calendar.getInstance()

            val date = DatePickerDialog(this,
                { _, year, monthOfYear, dayOfMonth ->
                    val newDate = Calendar.getInstance()
                    newDate[year, monthOfYear] = dayOfMonth
                    val bulan = arrayOf("Januari", "Februari", "Maret", "April", "Mei",
                        "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")
                    AkhirTanggal = dayOfMonth.toString() + " " + bulan[monthOfYear] + " " + year
                    edtTglAkhir.setText(AkhirTanggal)
                },cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))

            date.show()
        }

        btnAddEvent.setOnClickListener {

            // debug
            Log.d("Value Path: ", selectImagePath.toString())

            // masukan data ke class DataEvent
            val event = DataEvent()
            event.image = selectImagePath
            event.name = edtName.text.toString()
            event.fee = Integer.valueOf(edtFee.text.toString())
            event.venue = edtVenue.text.toString()
            event.contact = edtCp.text.toString()
            event.tgl_event = edtTglEvent.text.toString()
            event.tgl_akhir = edtTglAkhir.text.toString()

            // masukan data dari class DataEvent ke Database
            val values = ContentValues()
            values.put(DatabaseHelper.EventColumns.KEY_IMAGE, event.image)
            values.put(DatabaseHelper.EventColumns.KEY_NAME, event.name)
            values.put(DatabaseHelper.EventColumns.KEY_FEE, event.fee)
            values.put(DatabaseHelper.EventColumns.KEY_TGL_EVENT, event.tgl_event)
            values.put(DatabaseHelper.EventColumns.KEY_TGL_AKHIR, event.tgl_akhir)
            values.put(DatabaseHelper.EventColumns.KEY_VENUE, event.venue)
            values.put(DatabaseHelper.EventColumns.KEY_CP, event.contact)

            DatabaseHelper(this).insertEvent(values)

        }

//
//        btnAddEvent.setOnClickListener {
//            db.insertEvent(values)
//        }
//        edtTglAkhir.inputType = InputType.TYPE_NULL;
//        edtTglAkhir.requestFocus();

//        btnAddEvent.setOnClickListener {
//            db.addEvent(name, edtTglEvent.toString(), edtTglAkhir.toString(), fee.toInt(), venue, cp)
//        }

//        btnAddEvent.setOnClickListener(this)
//        imageBtn.setOnClickListener (this)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when(requestCode){
//            Gallery_Request_Code->
//            {
//                if(resultCode== RESULT_OK)
//                {
//                    var URI = data!!.data
//                    var Image = MediaStore.Images.Media.getBitmap(this.contentResolver,URI)
//                    img_event.setImageBitmap(Image)
//                }
//            }
//        }
//    }

    override fun onClick(view: View?) {


//        if(view?.id == R.id.choose_img){
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
//                REQUEST_CODE_GALLERY
//            )
//        }
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION && grantResults.size > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectImage()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SELECT && resultCode == RESULT_OK) {
            if (data != null) {
                val selectImgUri = data.data
                if (selectImgUri != null) {
                    try {
                        val inputStream = contentResolver.openInputStream(selectImgUri)
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        img_event.setImageBitmap(bitmap)
//                        imageNote.visibility = View.VISIBLE
//                        fabDeleteImage.visibility = View.VISIBLE
                        selectImagePath = getPathFromUri(selectImgUri)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun getPathFromUri(contentUri: Uri): String? {
        val filePath: String?
        val cursor = contentResolver.query(contentUri, null, null, null, null)
        if (cursor == null) {
            filePath = contentUri.path
        } else {
            cursor.moveToFirst()
            val index = cursor.getColumnIndex("_data")
            filePath = cursor.getString(index)
            cursor.close()
        }
        return filePath
    }

//    private fun OpenGallery() {
//        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
//        {
//            var GalleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            startActivityForResult(GalleryIntent,Gallery_Request_Code)
//        }
//        else
//        {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),Gallery_Request_Code)
//        }
//    }

//    private fun OpenCamera() {
//        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)
//        {
//            val CameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            startActivityForResult(CameraIntent,CameraRequestCode)
//        }
//        else
//        {
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),CameraRequestCode)
//        }
//
//    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        when(requestCode){
//            Gallery_Request_Code->
//            {
//                if(grantResults.size>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
//                {
//                    OpenGallery()
//                }
//                else
//                {
//                    Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }


//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        if(requestCode == REQUEST_CODE_GALLERY){
//           if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) run {
//               val intent = Intent(Intent.ACTION_PICK)
//               intent.setType("image/*")
//               startActivityForResult(intent, REQUEST_CODE_GALLERY)
//           }
//            else{
//                Toast.makeText(applicationContext, "you dont have permission to acces file", Toast.LENGTH_SHORT).show()
//           }
//            return
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }

    companion object {
        private const val REQUEST_PERMISSION = 1
        private const val REQUEST_SELECT = 2

        const val EXTRA_NOTE = "extra_note"
        const val EXTRA_POSITION = "extra_position"
    }
}