package ca.gbc.comp3074.fahim.mina.labex4.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val age: Int,
    val isActive: Boolean
)
