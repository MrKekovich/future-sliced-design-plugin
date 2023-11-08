package com.mrkekovich.future_sliced_design.utils

import com.intellij.openapi.ui.ValidationInfo
import javax.swing.JTextField

const val FORBIDDEN_PACKAGE_NAME_CHARACTERS = "!@#\$%^&*()-+=[]{}|\\;:'\",<>./?~"

fun validatePackageName(component: JTextField): ValidationInfo? {
    val name = component.text

    if (name.isBlank()) {
        return ValidationInfo("Package name cannot be blank", component)
    }
    if (FORBIDDEN_PACKAGE_NAME_CHARACTERS.any { it in name }) {
        return ValidationInfo("Package name contains forbidden characters", component)
    }
    return null
}