import android.R.attr.scaleX
import android.R.attr.scaleY
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codeplace.postsandroidapp.core.presentation.components.IconAction
import com.codeplace.postsandroidapp.core.presentation.components.IconContainer

@Preview
@Composable
fun DefaultSearchBarPreview() {
    DefaultSearchBar(
        searchResults = listOf("Result"),
        onSearch = {}
    )
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DefaultSearchBar(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState = rememberTextFieldState(),
    onSearch: (String) -> Unit,
    searchResults: List<String>,
) {
    // Controls expansion state of the search bar
    var expanded by rememberSaveable { mutableStateOf(false) }

    val horizontalPadding by animateDpAsState(
        targetValue = if (expanded) 0.dp else 16.dp,
        label = "horizontalPadding"
    )
    val inputFieldColor by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.surfaceContainerLowest else MaterialTheme.colorScheme.surfaceContainer,
        label = "inputFieldColor"
    )

    Column {
        Box(
            modifier
                .background(MaterialTheme.colorScheme.background)
                 .fillMaxWidth()
                .semantics { isTraversalGroup = true }
        ) {
            SearchBar(
                modifier = Modifier
                    .padding(all = horizontalPadding)
                    .align(Alignment.TopCenter)
                    .semantics { traversalIndex = 0f },
                colors = SearchBarDefaults.colors(
                    containerColor =inputFieldColor,

                ),
                inputField = {
                    SearchBarDefaults.InputField(
                        modifier = Modifier
                            .fillMaxSize(),
                        query = textFieldState.text.toString(),
                        onQueryChange = { textFieldState.edit { replace(0, length, it) } },
                        onSearch = {
                            onSearch(textFieldState.text.toString())
                            expanded = false
                        },
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = it
                        },
                        placeholder = { Text("Search") },
                        leadingIcon = {
                            if (expanded) {
                                IconAction(
                                    iconElement = {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.onSurface
                                            )
                                    },
                                    onClick = {
                                            expanded = false
                                    }
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = null,
                                )
                            }
                        }

                    )
                },
                expanded = expanded,
                onExpandedChange = { expanded = it },
            ) {
                // Display search results in a scrollable column

                Column(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                        .verticalScroll(rememberScrollState())
                ) {
                    searchResults.forEach { result ->
                        Text(
                            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                            text = "Recent words searches",
                            style = MaterialTheme.typography.labelMediumEmphasized,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            letterSpacing = 1.2.sp
                        )
                        Spacer(Modifier.size(8.dp))
                        ListItem(
                            colors = ListItemDefaults.colors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
                            ),
                            leadingContent = {
                                IconContainer(
                                    iconElement = {
                                        Icon(
                                            imageVector = Icons.Default.AccessTime,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                )
                            },
                            headlineContent = { Text(
                                text = result,
                                style = MaterialTheme.typography.bodyMedium

                            ) },
                            modifier = Modifier
                                .clickable {
                                    textFieldState.edit { replace(0, length, result) }
                                    expanded = false
                                }
                                .fillMaxWidth()
                        )
                    }
                }


            }

        }
//
    }
}