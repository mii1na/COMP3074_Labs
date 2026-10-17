package ca.gbc.comp3074.fahim.mina.labex4.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.gbc.comp3074.fahim.mina.labex4.data.Customer
import ca.gbc.comp3074.fahim.mina.labex4.data.CustomerDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class CustomerViewModel(
    private val customerDao: CustomerDao
) : ViewModel() {

    val customers: Flow<List<Customer>> =
        customerDao.getAllCustomers()

    private val searchQuery = MutableStateFlow("")

    val searchResults: Flow<List<Customer>> =
        searchQuery.flatMapLatest { text ->
            if (text.isBlank()) {
                customerDao.getAllCustomers()
            } else {
                customerDao.searchCustomers(text)
            }
        }

    fun searchCustomers(text: String) {
        searchQuery.value = text.trim()
    }

    fun clearSearch() {
        searchQuery.value = ""
    }

    fun addCustomer(
        name: String,
        age: Int,
        isActive: Boolean
    ) {
        viewModelScope.launch {
            customerDao.insert(
                Customer(
                    name = name,
                    age = age,
                    isActive = isActive
                )
            )
        }
    }

    fun deleteCustomer(customer: Customer) {
        viewModelScope.launch {
            customerDao.delete(customer)
        }
    }
}
