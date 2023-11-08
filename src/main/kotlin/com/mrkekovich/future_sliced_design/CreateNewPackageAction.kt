package com.mrkekovich.future_sliced_design

import com.mrkekovich.future_sliced_design.dialogs.CreateNewPackageDialogWrapper
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class CreateNewPackageAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val dialog = CreateNewPackageDialogWrapper(
            event
        )
        dialog.show()
    }
}