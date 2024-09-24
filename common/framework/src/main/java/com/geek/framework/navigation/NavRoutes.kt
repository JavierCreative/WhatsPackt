package com.geek.framework.navigation

object NavRoutes {
    const val CONVERSATIONS_LIST = "conversations_list"
    const val NEW_CONVERSATION = "new_conversation"
    const val CHAT = "chat/{chatId}"
    object ChatArgs {
        const val CHAT_ID = "chatId"
    }
}