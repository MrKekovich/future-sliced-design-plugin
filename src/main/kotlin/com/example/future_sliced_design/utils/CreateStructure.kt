package com.example.future_sliced_design.utils

import com.intellij.openapi.application.WriteAction
import com.intellij.openapi.vfs.VirtualFile

val PACKAGES = listOf("lib", "model", "ui")

fun createPackageStructure(
    name: String,
    parentDirectory: VirtualFile?,
    addExtra: Boolean
): VirtualFile? {
    val packageDir = createPackage(
        name = name,
        path = parentDirectory
    )

    if (addExtra) {
        PACKAGES.map {
            createPackage(
                name = it,
                path = packageDir
            )
        }
    }

    return packageDir
}

fun createPackage(name: String, path: VirtualFile?): VirtualFile? {
    var directory: VirtualFile? = null

    WriteAction.run<Throwable> {
        val currentDirectory = path?.createChildDirectory(null, name)
        currentDirectory?.createChildData(null, "index.ts")

        directory = currentDirectory
    }

    return directory
}
