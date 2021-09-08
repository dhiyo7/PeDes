package com.plugin.pengaduandesa

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import coil.api.load
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.esafirm.imagepicker.model.Image
import com.plugin.pengaduandesa.contracts.PengaduanActivityContract
import com.plugin.pengaduandesa.databinding.ActivityCreatePengaduanBinding
import com.plugin.pengaduandesa.models.Category
import com.plugin.pengaduandesa.presenters.CreatePengaduanActivityPresenter
import com.plugin.pengaduandesa.utils.PengaduanUtils
import kotlinx.android.synthetic.main.activity_create_pengaduan.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class CreatePengaduanActivity : AppCompatActivity(), PengaduanActivityContract.CreatePengaduanView,
    AdapterView.OnItemSelectedListener {

    private var presenter : PengaduanActivityContract.CreatePengaduanInteractor? = null
    private lateinit var binding : ActivityCreatePengaduanBinding
    private lateinit var imageUri : Uri

    private var choosedImage: Image? = null

    private val imagePickerLauncher = registerImagePicker {
        choosedImage = if(it.size == 0)  null else it[0]
        showImage()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = CreatePengaduanActivityPresenter(this)
        binding = ActivityCreatePengaduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.setOnClickListener {
            chooseImage()
        }

        toolbarAction()
        doSave()
        intentMap()
    }

    private fun intentMap (){
        binding.btnPickLocation.setOnClickListener {
            val intent = Intent(this@CreatePengaduanActivity, PickLocationActivity::class.java);
            startActivityForResult(intent, 1);
        }
    }

    private fun getData() {
        val token = PengaduanUtils.getToken(this)
        presenter?.getCategory(token!!)
    }

    private fun showAlertDialog(message: String){
        AlertDialog.Builder(this@CreatePengaduanActivity).apply {
            setMessage(message)
            setPositiveButton("OK"){ d, _ ->
                d.cancel()
            }
        }.show()
    }

    private fun doSave(){
        binding.btnSave.setOnClickListener {
            if(choosedImage == null){
                showAlertDialog(getString(R.string.validation_no_image))
                return@setOnClickListener
            }

            val token = PengaduanUtils.getToken(this)
            val selectedObject = binding.spinnerCategory.selectedItem as Category
            val complaintContent : RequestBody = RequestBody.create(
                MultipartBody.FORM, binding.etComplaintContent.text.toString()
            )
            val complaintCategory : RequestBody = RequestBody.create(MultipartBody.FORM, selectedObject.id)
            val latitude : RequestBody = RequestBody.create(MultipartBody.FORM, binding.etLatitude.text.toString())
            val longitude : RequestBody = RequestBody.create(MultipartBody.FORM, binding.etLongitude.text.toString())

            val originalFile = File(choosedImage?.path!!)

            val imagePart : RequestBody = RequestBody.create(
                MediaType.parse("image/*"),
                originalFile
            )

            val image : MultipartBody.Part = MultipartBody.Part.createFormData(
                "complaint_image",
                originalFile.name,
                imagePart
            )

            presenter?.postComplaint(token!!, complaintCategory, complaintContent, latitude, longitude, image)
        }
    }

    private fun chooseImage(){
        val config = ImagePickerConfig{
            mode = ImagePickerMode.SINGLE
            isIncludeVideo = false
            isShowCamera = true
        }

        imagePickerLauncher.launch(config)
    }

    private fun showImage(){
        choosedImage?.let {
            image -> binding.imageView.load(image.uri)
        } ?: binding.imageView.load(R.drawable.no_image)
    }

    private fun toolbarAction(){
        val action = supportActionBar
        action!!.title = "Create Pengaduan"
        action.setDisplayHomeAsUpEnabled(true)
        action.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true
    }

    override fun attachToSpinner(category: List<Category>) {
        var spinnerAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,category)
        spinnerCategory.apply {
            adapter = spinnerAdapter
        }

        binding.spinnerCategory.onItemSelectedListener = this
    }

    override fun showToast(message: String) {
        Toast.makeText(this@CreatePengaduanActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun successPost() {
        val intent = Intent(this@CreatePengaduanActivity, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedObject = parent?.selectedItem as Category
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                val lat = data!!.getStringExtra("LATITUDE")
                val long = data!!.getStringExtra("LONGITUDE")

                binding.etLatitude.setText(lat)
                binding.etLongitude.setText(long)
            }
        }
    }
}