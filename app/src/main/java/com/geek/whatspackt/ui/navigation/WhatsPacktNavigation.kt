package com.geek.whatspackt.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.geek.chat.ui.ChatScreen
import com.geek.conversations.ui.ConversationListScreen
import com.geek.create_chat.ui.CreateConversationScreen
import com.geek.framework.navigation.NavRoutes

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.CONVERSATIONS_LIST
    ) {
        addConversationsList(navController)
        addCreateConversation(navController)
        addChat(navController)
    }
}

private fun NavGraphBuilder.addConversationsList(navController: NavHostController) {
    composable(route = NavRoutes.CONVERSATIONS_LIST) {
        ConversationListScreen(
            onNewConversationClick = {
                navController.navigate(NavRoutes.NEW_CONVERSATION)
            },
            onConversationClick = { chatId ->
                navController.navigate(
                    NavRoutes.CHAT.replace(
                        "{chatId}", chatId
                    )
                )
            }
        )
    }
}

private fun NavGraphBuilder.addCreateConversation(navController: NavHostController) {
    composable(route = NavRoutes.NEW_CONVERSATION) {
        CreateConversationScreen(
            onCreateConversation = {
                navController.navigate(NavRoutes.CHAT)
            }
        )
    }
}

private fun NavGraphBuilder.addChat(navController: NavHostController) {
    composable(
        route = NavRoutes.CHAT,
        arguments = listOf(
            navArgument(NavRoutes.ChatArgs.CHAT_ID) {
                type = NavType.StringType
            })
    ) { backStackEntry ->
        val chatId = backStackEntry.arguments?.getString(NavRoutes.ChatArgs.CHAT_ID)
        ChatScreen(chatId = chatId, onBack = {
            navController.popBackStack()
        })
    }
}