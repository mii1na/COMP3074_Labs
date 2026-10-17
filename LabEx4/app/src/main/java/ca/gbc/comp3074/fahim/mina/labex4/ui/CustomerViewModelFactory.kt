package ca.gbc.comp3074.fahim.mina.labex4.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.gbc.comp3074.fahim.mina.labex4.data.CustomerDao

class CustomerViewModelFactory(
    private val customerDao: CustomerDao
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(CustomerViewModel::class.java)) {
            return CustomerViewModel(customerDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
