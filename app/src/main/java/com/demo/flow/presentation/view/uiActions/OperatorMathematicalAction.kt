package com.demo.flow.presentation.view.uiActions

import com.demo.flow.presentation.viewmodels.OperatorMathematicalViewModel

sealed class OperatorMathematicalAction {
    object  OperatorMathSum : OperatorMathematicalAction()
    object  OperatorMathSumBy : OperatorMathematicalAction()
    object  OperatorAverage : OperatorMathematicalAction()
    object  OperatorMinBy : OperatorMathematicalAction()
    object  OperatorMaxBy : OperatorMathematicalAction()
}