package presentation.home

import data.WeatherService
import data.dto.Notification
import kotlinx.coroutines.Job
import presentation.base.BaseScreenModel
import presentation.base.ErrorState

class HomeScreenModel(private val service: WeatherService) : BaseScreenModel<HomeUIState, HomeUiEffect>(HomeUIState()),
    HomeInteractionListener {

    private var searchJob: Job? = null

    init {
        getData()
    }

    override fun getData() {
        updateState { it.copy(isLoading = true, error = null) }
        tryToExecute(
            { service.getNotification() },
            onSuccess = ::onGetNotificationSuccess,
            onError = ::onError
        )
    }

    private fun onGetNotificationSuccess(notifications: List<Notification>) {
        updateState { it.copy(notificationUIState = notifications, isLoading = false, error = null) }
    }

    override fun onSearchExpand(state: Boolean) {
        updateState { it.copy(isSearchExpanded = state) }
    }

    override fun onDropDownMenuExpand(expand: Boolean) {
        updateState { it.copy(isExpandMenuSuggestion = expand) }
    }

    private fun onError(error: ErrorState) {
        updateState { it.copy(error = error, isLoading = false) }
    }

    override fun onSearchCitySelected(city: String) {
        updateState { it.copy(isLoading = true, isSearchExpanded = false) }
        tryToExecute(
            { service.getNotification() },
            onSuccess = ::onGetNotificationSuccess,
            onError = ::onError
        )
    }
}