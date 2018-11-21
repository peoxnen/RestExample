package iview.wsienski.restexample.data.model

/**
 * Created by Witold Sienski on 20.11.2018.
 */
data class User(
    val login: String,
    val id: Float = 0.toFloat(),
    val node_id: String = "",
    val avatar_url: String = "",
    val gravatar_id: String = "",
    val url: String = "",
    val html_url: String = "",
    val followers_url: String = "",
    val following_url: String = "",
    val gists_url: String = "",
    val starred_url: String = "",
    val subscriptions_url: String = "",
    val organizations_url: String = "",
    val repos_url: String = "",
    val events_url: String = "",
    val received_events_url: String = "",
    val type: String = "",
    val site_admin: Boolean = false
)