package com.example.future_sliced_design

import com.example.future_sliced_design.dialogs.CreateNewPackageDialogWrapper
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys

class CreateNewPackageAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val dialog = CreateNewPackageDialogWrapper(
            e.getData(PlatformDataKeys.VIRTUAL_FILE)
        )
        dialog.show()
    }
}