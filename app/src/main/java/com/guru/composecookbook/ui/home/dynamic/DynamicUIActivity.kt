package com.guru.composecookbook.ui.home.dynamic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.guru.composecookbook.R
import com.guru.composecookbook.carousel.CarouselLayout
import com.guru.composecookbook.theme.ComposeCookBookMaterial3Theme
import com.guru.composecookbook.ui.home.androidviews.AndroidViews
import com.guru.composecookbook.ui.home.constraintlayout.ConstraintLayoutDemos
import com.guru.composecookbook.ui.home.dialogs.BottomSheetLayouts
import com.guru.composecookbook.ui.home.motionlayout.MotionLayoutDemo
import com.guru.composecookbook.ui.home.pullrefreshdemos.PullRefreshList
import com.guru.composecookbook.ui.home.tabslayout.TabLayout
import com.guru.composecookbook.ui.learnwidgets.Layouts


class DynamicUIActivity : ComponentActivity() {

    private val dynamicUiType: String by lazy {
        intent?.getStringExtra(TYPE) ?: DynamicUiType.TABS.name
    }

    private val isDarkTheme: Boolean by lazy {
        intent?.getBooleanExtra(DARK_THEME, false) ?: false
    }

    @OptIn(ExperimentalComposeUiApi::class,
    ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCookBookMaterial3Theme(isDarkTheme) {
                DynamicUiWrapper(dynamicUiType) {
                    onBackPressed()
                }
            }
        }
    }

    companion object {
        const val TYPE = "type"
        const val DARK_THEME = "darkTheme"
        fun newIntent(context: Context, dynamicUiType: String, isDarkTheme: Boolean) =
            Intent(context, DynamicUIActivity::class.java).apply {
                putExtra(TYPE, dynamicUiType)
                putExtra(DARK_THEME, isDarkTheme)
            }
    }
}


@OptIn(ExperimentalMaterial3Api::class,
ExperimentalComposeUiApi::class,
ExperimentalMaterialApi::class)
@Composable
fun DynamicUiWrapper(uiType: String, onback: () -> Unit) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = uiType) },
                navigationIcon = {
                    IconButton(onClick = onback) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.cd_back)
                        )
                    }
                }
            )
        },
        content = {
            // We setup a base activity and we will change content depending upon ui type so
            // we don't have to create Activity for every feature showcase
            when (uiType) {
                DynamicUiType.TABS.name -> {
                    TabLayout()
                }
                DynamicUiType.BOTTOMSHEET.name -> {
                    BottomSheetLayouts()
                }
                DynamicUiType.LAYOUTS.name -> {
                    Layouts()
                }
                DynamicUiType.CONSTRAINTLAYOUT.name -> {
                    ConstraintLayoutDemos()
                }
                DynamicUiType.CAROUSELL.name -> {
                    CarouselLayout()
                }
                DynamicUiType.MODIFIERS.name -> {
                    HowToModifiers()
                }
                DynamicUiType.ANDROIDVIEWS.name -> {
                    AndroidViews()
                }
                DynamicUiType.PULLRERESH.name -> {
                    PullRefreshList(onPullRefresh = {})
                }
                DynamicUiType.MOTIONLAYOUT.name -> {
                    MotionLayoutDemo()
                }
            }
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class,
ExperimentalMaterialApi::class)
@Preview
@Composable
fun PreviewDynamicUI() {
    DynamicUiWrapper(uiType = DynamicUiType.TABS.name, onback = {})
}


