package com.demo.flow.presentation.view.uiActions

import com.demo.flow.presentation.viewmodels.OperatorMathematicalViewModel

sealed class OperatorMathematicalAction {
    data class  OperatorMathSum(val message: String) : OperatorMathematicalAction()
    data class  OperatorMathSumBy(val message: String) : OperatorMathematicalAction()
    data class  OperatorAverage(val message: String) : OperatorMathematicalAction()
    data class  OperatorMinBy(val message: String) : OperatorMathematicalAction()
    data class  OperatorMaxBy(val message: String) : OperatorMathematicalAction()
}