package com.example.future_sliced_design.utils

import com.intellij.openapi.application.WriteAction
import com.intellij.openapi.vfs.VirtualFile

val PACKAGES = listOf("lib", "model", "ui")

fun createPackageStructure(
    packageName: String,
    parentDirectory: VirtualFile?,
    addExtra: Boolean
): VirtualFile? {
    val packageDir = createPackage(
        packageName = packageName,
        path = parentDirectory
    )

    if (addExtra) {
        PACKAGES.forEach {
            createPackage(
                packageName = it,
                path = packageDir
            )
        }
    }

    return packageDir
}

fun createPackage(packageName: String, path: VirtualFile?): VirtualFile? {
    var directory: VirtualFile? = null

    WriteAction.run<Throwable> {
        val currentDirectory = path?.createChildDirectory(null, packageName)
        currentDirectory?.createChildData(null, "index.ts")

        directory = currentDirectory
    }

    return directory
}
