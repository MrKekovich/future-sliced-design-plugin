package com.example.future_sliced_design.dialogs

import com.example.future_sliced_design.utils.createPackageStructure
import com.example.future_sliced_design.utils.validatePackageName
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.openapi.ui.addKeyboardAction
import com.intellij.openapi.vfs.VirtualFile
import java.awt.Component
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.KeyEvent
import javax.swing.*

class CreateNewPackageDialogWrapper(
    private val data: VirtualFile?
) : DialogWrapper(true) {

    private val textField = JTextField().apply {
        maximumSize = Dimension(Integer.MAX_VALUE, preferredSize.height)
        addKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0)) {
            createPackageStructure(
                text,
                data, this@CreateNewPackageDialogWrapper.checkBoxTicked
            )
            dispose()
        }
        addKeyboardAction(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0)) {
            dispose()
        }
    }


    private val checkBox = JCheckBox("lib, model, ui").apply {
        alignmentX = Component.LEFT_ALIGNMENT
    }

    private val contentPanel = JPanel().apply {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        add(textField)
        add(checkBox)
    }

    init {
        title = "Create New Package"
        init()
    }

    override fun createCenterPanel(): JComponent {
        return textField
    }

    override fun doValidate(): ValidationInfo? {
        return validatePackageName(textField)
    }

    override fun createSouthPanel(): JComponent {
        val panel = JPanel(FlowLayout(FlowLayout.LEFT)).apply {
            add(checkBox)
        }
        return panel
    }

    private val checkBoxTicked
        get() = checkBox.isSelected
}