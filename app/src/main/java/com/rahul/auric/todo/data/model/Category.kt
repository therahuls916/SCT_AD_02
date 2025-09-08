// FileName: Category.kt
package com.rahul.auric.todo.data.model

import androidx.compose.ui.graphics.Color

// A sealed class is a great choice here because it allows us to define a restricted
// set of category types. This prevents errors from typos and makes our code safer.
sealed class Category(val name: String, val color: Color) {
    object Work : Category("Work", Color(0xFF4285F4)) // Blue
    object Urgent : Category("Urgent", Color(0xFFDB4437)) // Red
    object Personal : Category("Personal", Color(0xFF0F9D58)) // Green
    object Health : Category("Health", Color(0xFFF4B400)) // Yellow

    companion object {
        // A list of all categories, which we'll use for the dropdown menu.
        val categoryList = listOf(Work, Urgent, Personal, Health)

        // A helper function to find a category by its name string.
        // This is useful when loading a task from the database.
        fun fromString(name: String): Category {
            return categoryList.find { it.name == name } ?: Personal // Default to Personal
        }
    }
}