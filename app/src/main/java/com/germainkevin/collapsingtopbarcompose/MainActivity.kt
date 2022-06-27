package com.germainkevin.collapsingtopbarcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import com.germainkevin.collapsingtopbar.CollapsingTopBar
import com.germainkevin.collapsingtopbar.rememberCollapsingTopBarScrollBehavior
import com.germainkevin.collapsingtopbarcompose.ui.*
import com.germainkevin.collapsingtopbarcompose.ui.theme.CollapsingTopBarComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CollapsingTopBarComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val coroutineScope = rememberCoroutineScope()
                    val scaffoldState = rememberScaffoldState()

                    val openLeftDrawer: () -> Unit = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                    val closeLeftDrawer: () -> Unit = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }

                    HomeScreen(
                        contactNames = stringArrayResource(id = R.array.contactNames),
                        scaffoldState = scaffoldState,
                        openLeftDrawer = openLeftDrawer,
                        closeLeftDrawer = closeLeftDrawer
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeScreen(
    contactNames: Array<String>,
    scaffoldState: ScaffoldState,
    openLeftDrawer: () -> Unit,
    closeLeftDrawer: () -> Unit
) {
    val scrollBehavior = rememberCollapsingTopBarScrollBehavior(
        isAlwaysCollapsed = false,
        isExpandedWhenFirstDisplayed = true,
        centeredTitleAndSubtitle = false,
        expandedTopBarMaxHeight = 156.dp,
    )
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        scaffoldState = scaffoldState,
        drawerContent = { LeftDrawer(closeLeftDrawer) },
        topBar = {
            CollapsingTopBar(
                scrollBehavior = scrollBehavior,
                title = TitleText,
                subtitle = { SubtitleText(contactNames) },
                navigationIcon = { NavigationIcon(openLeftDrawer) },
                actions = { MoreMenuIcons() },
            )
        },
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(6.dp))
            }
            items(count = contactNames.size) {
                ContactNameItem(LocalContext.current, contactNames[it])
            }
        }
    }
}