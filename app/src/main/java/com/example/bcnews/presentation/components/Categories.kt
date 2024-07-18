import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Locale.Category


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryTab(
    modifier: Modifier=Modifier,
    pagerState: PagerState,
    categories:List<String>,
    onTabClicked:(Int)->Unit

) {
    val coroutineScope= rememberCoroutineScope()
    val selectedIndex= pagerState.currentPage



    LazyRow(
        modifier = Modifier.fillMaxWidth()

        ) {

        itemsIndexed(categories){index,category->
         TextButton(onClick = { onTabClicked(index)


                      coroutineScope.launch { pagerState.animateScrollToPage(index) }
                                          },
             modifier = Modifier

                 .padding(horizontal = 7.dp)
                 .border(
                      width = 1.dp,
                     shape = RoundedCornerShape(10.dp),
                     color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                 )

                 .background(
                     if (selectedIndex == index) MaterialTheme.colorScheme.primary else Color.Transparent,
                     shape = RoundedCornerShape(10.dp),
                 )
                 .height(35.dp)





            ) {
             Text(text = category,
                 style = if(selectedIndex ==index)TextStyle(color = MaterialTheme.colorScheme.background)else{TextStyle(color = MaterialTheme.colorScheme.onBackground)}

                 )

         }

          }

        }

    }



