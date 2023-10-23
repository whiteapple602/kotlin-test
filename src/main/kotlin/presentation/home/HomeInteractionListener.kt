package presentation.home

import presentation.base.BaseInteractionListener

interface HomeInteractionListener : BaseInteractionListener {

    fun onDropDownMenuExpand(expand: Boolean)

    fun onSearchCitySelected(city: String)

    fun getData()

    fun clearData()

    fun onSearchExpand(state: Boolean)

}
