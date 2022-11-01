package com.challenge5syifa

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.challenge5syifa.MainActivity.Companion.db
import com.challenge5syifa.MainActivity.Companion.gson
import com.challenge5syifa.MainActivity.Companion.sharedPref
import com.challenge5syifa.databinding.FragmentHomeBinding
import com.challenge5syifa.databinding.FragmentProfileBinding
import com.challenge5syifa.room.entity.User
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log

class ProfileFragment : Fragment() {
    private lateinit var bind: FragmentProfileBinding
    private lateinit var logged: User
    private var calendar: Calendar = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentProfileBinding.inflate(inflater,container,false)
        sharedPref.getString("logged", null)?.let {
            logged= gson.fromJson(it, User::class.java)
        }?: run {
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
        initElement()
        return bind.root
    }
    private fun initElement(){
        bind.etUsername.setText(logged.username)
        bind.etNama.setText(logged.namaLengkap)
        bind.etTanggalLahir.setText(logged.tanggalLahir)
        bind.etAlamat.setText(logged.alamat)
        bind.etTanggalLahir.setOnClickListener{
            showCalendar()
        }
        bind.btnUpdate.setOnClickListener{
            logged.namaLengkap=bind.etNama.text.toString()
            logged.username=bind.etUsername.text.toString()
            logged.tanggalLahir=bind.etTanggalLahir.text.toString()
            logged.alamat=bind.etAlamat.text.toString()
            if(db.userDao().update(logged)>0){
                Toast.makeText(requireContext(),"Berhasil mengubah profil",Toast.LENGTH_SHORT).show()
                with (sharedPref.edit()) {
                    putString("logged", gson.toJson(logged))
                    commit()
                }
            }else{
                Toast.makeText(requireContext(),"Gagal mengubah profil",Toast.LENGTH_SHORT).show()
            }
        }
        bind.btnLogout.setOnClickListener{
            with (sharedPref.edit()) {
                remove("logged")
                commit()
            }
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }
    private fun showCalendar() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                bind.etTanggalLahir.setText(SimpleDateFormat("dd MMMM yyyy").format(GregorianCalendar(year,month,dayOfMonth).time))
            },
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        )
        if (!datePickerDialog.isShowing) {
            datePickerDialog.show()
        }
    }
}