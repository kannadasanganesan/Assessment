package com.example.searchindictionary.ui.src.viewmodel

import com.example.searchindictionary.ui.src.repository.MainRepository
import com.example.searchindictionary.ui.src.viewmodel.rules.MainCoroutineRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class SearchViewModelTest {

    private lateinit var searchViewModel: SearchViewModel

    private val mainRepository: MainRepository = mockk()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        searchViewModel = SearchViewModel()
    }

    @Test
    fun getSuccessResult() {
        searchViewModel.getResult("VMM")
    }

}