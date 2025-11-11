//package com.codeplace.postsandroidapp.feature_explore.presentation.components
//
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.text.input.TextFieldState
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.SearchBar
//import androidx.compose.material3.SearchBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.semantics.isTraversalGroup
//import androidx.compose.ui.semantics.semantics
//import androidx.compose.ui.semantics.traversalIndex
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SimpleSearchBar(
//    textFieldState: TextFieldState,
//    onSearch: (String) -> Unit,
//    searchResults: List<String>,
//    modifier: Modifier = Modifier
//) {
//    // Controls expansion state of the search bar
//    var expanded by rememberSaveable { mutableStateOf(false) }
//
//    Box(
//        modifier
//            .fillMaxSize()
//            .semantics { isTraversalGroup = true }
//    ) {
//
//
//        SearchBar(
//            modifier = Modifier
//                .align(Alignment.TopCenter)
//                .semantics { traversalIndex = 0f },
////            inputField = {
////                SearchBarDefaults.InputField(
////                    query = textFieldState.text.toString(),
////                    onQueryChange = { textFieldState.edit { replace(0, length, it) } },
////                    onSearch = {
////                        onSearch(textFieldState.text.toString())
////                        expanded = false
////                    },
////                    expanded = expanded,
////                    onExpandedChange = { expanded = it },
////                    placeholder = { Text("Search") }
////                )
////            },
//            expanded = expanded,
//            onExpandedChange = { expanded = it },
//        ) {
//            // Display search results in a scrollable column
//            Column(Modifier.verticalScroll(rememberScrollState())) {
//                searchResults.forEach { result ->
//                    ListItem(
//                        headlineContent = { Text(result) },
//                        modifier = Modifier
//                            .clickable {
//                                textFieldState.edit { replace(0, length, result) }
//                                expanded = false
//                            }
//                            .fillMaxWidth()
//                    )
//                }
//            }
//        }
//    }
//}