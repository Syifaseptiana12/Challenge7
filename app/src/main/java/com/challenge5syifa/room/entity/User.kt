package com.challenge5syifa.room.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val email: String,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "nama_lengkap") var namaLengkap: String?,
    @ColumnInfo(name = "tanggal_lahir") var tanggalLahir: String?,
    @ColumnInfo(name = "alamat") var alamat: String?
)