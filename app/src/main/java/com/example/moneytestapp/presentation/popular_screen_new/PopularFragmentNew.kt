package com.example.moneytestapp.presentation.popular_screen_new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.moneytestapp.R

class PopularFragmentNew : Fragment() {

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ComposeView {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_popular_new, container, false)

//        (rootView as ViewGroup).setContent {
//            Hello("Jetpack Compose")
//        }
//        (rootView as ComposeView).apply {
//            setContent {
//                var functionalityNotAvailablePopupShown by remember { mutableStateOf(false) }
//                if (functionalityNotAvailablePopupShown) {
//                    FunctionalityNotAvailablePopup { functionalityNotAvailablePopupShown = false }
//                }
//            setContent {
//                Text("Hello world!")
//            }
//        }

        return ComposeView(requireContext())
    }

    companion object {
        fun newInstance() = PopularFragmentNew()
    }

    @Composable
    fun Hello(name: String) = MaterialTheme {
//        FlexColumn {
//            inflexible {
//                // Item height will be equal content height
//                TopAppBar( // App Bar with title
//                    title = { Text("Jetpack Compose Sample") }
//                )
//            }
//            expanded(1F) {
//                // occupy whole empty space in the Column
//                Center {
//                    // Center content
//                    Text("Hello $name!") // Text label
//                }
//            }
//        }
    }
//    @Composable
//    fun MyApp() {
//        Scaffold(
//            content = {
//                BarkHomeContent()
//            }
//        )
//    }
}