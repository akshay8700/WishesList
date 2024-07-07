package com.akshay8700.wisheslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.akshay8700.wisheslist.data.DummyWish
import com.akshay8700.wisheslist.data.Wish
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailScreen(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {

    val snackMessage = remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    if (id != 0L) {
        // Need to understand later
        val wish = viewModel.getAWishById(id).collectAsState(initial = Wish(0L, "", ""))
        viewModel.wishTitleState = wish.value.title
        viewModel.wishDescriptionState = wish.value.description
    } else {
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState = ""
    }

    Scaffold(
        topBar = {
            AppBarView(
                title =
                if (id != 0L) stringResource(id = R.string.update_wish)
                else stringResource(id = R.string.add_wish)
            ) {
                navController.navigateUp()
            }
        },
        scaffoldState = scaffoldState
    ) { innerPadding ->

        DetailView(
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel,
            id,
            snackMessage,
            scope,
            scaffoldState,
            navController
        )
    }
}

@Composable
fun DetailView(
    modifier: Modifier,
    viewModel: WishViewModel,
    id: Long,
    snackMessage: MutableState<String>,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController
) {
    val spacerPadding = 8.dp

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.padding(vertical = spacerPadding))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = viewModel.wishTitleState,
            onValueChange = {
                viewModel.onWishTitleChanged(it)
            },
            placeholder = { Text(text = "Title") })
        Spacer(modifier = Modifier.padding(vertical = spacerPadding))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            placeholder = { Text(text = "Description") },
            value = viewModel.wishDescriptionState,
            onValueChange = {
                viewModel.onWishDescriptionChanged(it)
            })
        Spacer(modifier = Modifier.padding(vertical = spacerPadding))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                if (viewModel.wishTitleState.isNotEmpty() &&
                    viewModel.wishDescriptionState.isNotEmpty()
                ) {
                    if (id != 0L) {
                        viewModel.updateWish(
                            Wish(
                                id = id,
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                    } else {
                        //  AddWish
                        viewModel.addWish(
                            Wish(
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                        snackMessage.value = "Wish has been created"
                    }
                } else {
                    snackMessage.value = "Enter fields to create a wish"
                }
                scope.launch {
                    // scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }

            }
        ) {
            Text(text = "Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditDetailScreenPreview(modifier: Modifier = Modifier) {
    AddEditDetailScreen(id = 0, viewModel = viewModel(), navController = rememberNavController())
}