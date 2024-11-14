package com.example.yatask.ui.screens.homeScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yatask.utils.NavigationPath
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory

@Composable
fun  HomeScreenRoute(
    viewModel: HomeViewModel = hiltViewModel<HomeViewModelImpl>(),
    navigateToEditScreen : (String) -> Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState ,
        onDoneTask = { viewModel.handleEvent(HomeScreenUiEvent.OnItemCompleted(it))},
        onRemoveTask = {viewModel.handleEvent(HomeScreenUiEvent.OnDeleteItem(it))} ,
        onHideCompletedClick = {viewModel.handleEvent(HomeScreenUiEvent.OnCompletedItemsHide(!viewModel.isHideCompletedItems))} ,
        onClickFab = { navigateToEditScreen.invoke("null") } ,
        onClickTask = navigateToEditScreen
    )

}