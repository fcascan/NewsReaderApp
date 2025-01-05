import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit
) {
    TextField(
        value = query,
        onValueChange = {
            Log.d("SearchBar", "Query changed: $it")
            onQueryChanged(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text("Search news...") },
        singleLine = true,
        trailingIcon = {
            IconButton(
                onClick = {
                    Log.d("SearchBar", "Search clicked")
                    onSearch()
                }
            ) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    var query by remember { mutableStateOf("") }
    SearchBar(query = query, onQueryChanged = { query = it }, onSearch = {})
}