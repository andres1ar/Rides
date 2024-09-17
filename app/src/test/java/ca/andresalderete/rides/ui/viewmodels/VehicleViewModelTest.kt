package ca.andresalderete.rides.ui.viewmodels

import ca.andresalderete.rides.repository.VehicleRepository
import org.junit.Assert.*

import org.junit.Test

class VehicleViewModelTest {

    @Test
    fun isValidNumber() {
        val viewModel = VehicleViewModel(VehicleRepository())
        assertTrue(viewModel.isValidNumber(1))
        assertTrue(viewModel.isValidNumber(100))
        assertFalse(viewModel.isValidNumber(0))
        assertFalse(viewModel.isValidNumber(101))
    }

    @Test
    fun calculateCarbonEmission() {
        val viewModel = VehicleViewModel(VehicleRepository())
        assertEquals(100.0, viewModel.calculateCarbonEmission(100), 0.0)
        assertEquals(5000.0, viewModel.calculateCarbonEmission(5000), 0.0)
        assertEquals(12500.0, viewModel.calculateCarbonEmission(10000), 0.0)
        assertEquals(27500.0, viewModel.calculateCarbonEmission(20000), 0.0)
        assertEquals(50367.5, viewModel.calculateCarbonEmission(35245), 0.0)
    }
}