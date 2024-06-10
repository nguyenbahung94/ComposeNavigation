package com.example.composenavigatio

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.composenavigatio.ui.theme.ComposeNavigatioTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeNavigatioTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "screenA") {

                    // pass data object by argument
                    composable(
                        route = "screenA"
                    ) {
                        ScreenA(navigationToScreenB = { person ->
                            val encodePerson = Uri.encode((Json.encodeToString(person)))
                            navController.navigate("screenB/$encodePerson")
                        })
                    }

                    composable(
                        route = "screenB/{person}", arguments = listOf(
                            navArgument("person") {
                            type = PersonNavType
                            nullable = true
                        })
                    ) {
                        it.arguments?.getParcelable<Person>("person")?.let { person ->
                            ScreenB(person = person)
                        }
                    }


                    /* navigation(
                         route = "main",
                         startDestination = "screenA"
                     ) {
                         composable(
                             route = "screenA"
                         ) {
                             val viewModel: SharedViewModel = it.sharedViewModel(navController = navController)
                             ScreenA(navigationToScreenB = { person ->
                                 viewModel.person = person
                                 navController.navigate("screenB")
                             })
                         }

                         composable(
                             route = "screenB"
                         ) {
                             val viewModel: SharedViewModel = it.sharedViewModel(navController = navController)
                             viewModel.person?.let { person ->
                                 ScreenB(person = person)
                             }
                         }
                     }*/


                    // use shared view model
                    /* navigation(
                         route = "main",
                         startDestination = "screenA"
                     ) {
                         composable(
                             route = "screenA"
                         ) {
                             val viewModel: SharedViewModel = it.sharedViewModel(navController = navController)
                             ScreenA(navigationToScreenB = { person ->
                                 viewModel.person = person
                                 navController.navigate("screenB")
                             })
                         }

                         composable(
                             route = "screenB"
                         ) {
                             val viewModel: SharedViewModel = it.sharedViewModel(navController = navController)
                             viewModel.person?.let { person ->
                                 ScreenB(person = person)
                             }
                         }
                     }*/


                    // pass data object by saved state handle
                    /*  composable(
                          route = "screenA"
                      ) {
                          ScreenA(navigationToScreenB = { person ->
                              navController.currentBackStackEntry?.savedStateHandle?.set("person", person)
                              navController.navigate("screenB")
                          })
                      }

                      composable(route = "screenB"
                      ) {
                          navController.previousBackStackEntry?.savedStateHandle?.get<Person>("person")?.let { person ->
                              ScreenB(person = person)
                          }
                      }*/


                    // navigation with arguments optional
                    /* composable(
                         route = "screenA"
                     ) {
                         ScreenA(navigationToScreenB = { text ->
                             navController.navigate("screenB?text=$text")
                         })
                     }

                     composable(route = "screenB?text={text}&id={id}",
                         arguments = listOf(
                             navArgument("text") {
                             type = NavType.StringType
                             nullable = true
                             defaultValue = "Unknown"
                         },
                             navArgument("id") {
                                 type = NavType.IntType
                                 defaultValue = 0
                             })
                     ) {
                         val text = it.arguments?.getString("text") ?: "Unknown"
                         ScreenB(text = text)
                     }*/


                    // simple navigation
                    /*  composable(
                          route = "screenA"
                      ) {
                          ScreenA(navigationToScreenB = { text ->
                              navController.navigate("screenB/$text/12")
                          })
                      }

                      composable(route = "screenB/{text}/{id}",
                          arguments = listOf(navArgument("text") {
                              type = NavType.StringType
                              nullable = true
                              defaultValue = "Unknown"
                          },
                              navArgument("id") {
                              type = NavType.IntType
                          })
                      ) {
                          val text = it.arguments?.getString("text") ?: "Unknown"
                          val id = it.arguments?.getInt("id") ?: 0
                          ScreenB(text = text)
                      }*/
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeNavigatioTheme {
        Greeting("Android")
    }
}

val PersonNavType = NavigationUtil.parcelableNavType<Person>()