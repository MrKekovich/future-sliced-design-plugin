package com.mrkekovich.future_sliced_design.dialogs

import com.mrkekovich.future_sliced_design.utils.createPackageStructure
import com.mrkekovich.future_sliced_design.utils.validatePackageName
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.openapi.ui.addKeyboardAction
import com.intellij.openapi.vfs.VirtualFile
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.KeyEvent
import javax.swing.*

class CreateNewPackageDialogWrapper(
    private val event: AnActionEvent
) : DialogWrapper(true) {

    private val textField = JTextField().apply {
        maximumSize = Dimension(Integer.MAX_VALUE, preferredSize.height)

        addKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0)) {
            doOKAction()
        }

        addKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0)) {
            doCancelAction()
        }
    }

    private val checkBox = JCheckBox("lib, model, ui")

    init {
        title = "New TS Package"
        init()
    }

    override fun createCenterPanel(): JComponent {
        return textField
    }

    override fun doValidate(): ValidationInfo? {
        return validatePackageName(textField)
    }

    override fun createSouthPanel(): JComponent {
        val createButton = JButton("Create").apply {
            addActionListener {
                doOKAction()
            }
        }
        val panel = JPanel(FlowLayout(FlowLayout.RIGHT)).apply {
            add(checkBox)
            add(createButton)
        }
        return panel

    }

    override fun doOKAction() {
        createPackageStructure(
            packageName = packageName,
            parentDirectory = virtualFile,
            addExtra = checkBoxIsTicked
        )
        close(OK_EXIT_CODE)
    }

    override fun doCancelAction() {
        close(CANCEL_EXIT_CODE)
    }

    private val checkBoxIsTicked
        get() = checkBox.isSelected

    private val packageName
        get() = textField.text

    private val virtualFile: VirtualFile?
        get() = event.getData(PlatformDataKeys.VIRTUAL_FILE)
}