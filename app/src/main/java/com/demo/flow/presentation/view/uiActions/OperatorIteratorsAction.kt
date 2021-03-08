package com.demo.flow.presentation.view.uiActions

sealed class OperatorIteratorsAction {

    data class  OperatorActionIterator(val message: String) : OperatorIteratorsAction()
    data class  OperatorActionFor(val message: String) : OperatorIteratorsAction()
    data class  OperatorActionForEach(val message: String) : OperatorIteratorsAction()
    data class  OperatorActionForEachIndexed(val message: String) : OperatorIteratorsAction()

}
