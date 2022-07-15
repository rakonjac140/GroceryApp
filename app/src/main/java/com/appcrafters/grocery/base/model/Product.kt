package com.appcrafters.grocery.base.model

data class Result(
    val count: Int = 0,
    val results: Array<Product> = arrayOf(),
)

data class Product(
    val is_one_top: Boolean = false,
    val cook_time_minutes: Int = 0,
    val promotion: String = "",
    val keywords: String = "",
    val show: Show = Show("", 0),
    val servings_noun_plural: String = "",
    val canonical_id: String = "",
    val show_id: Int = 0,
    val draft_status: String = "",
    val thumbnail_url: String = "",
    val name: String = "",
    val description: String = "",
    val instructions: Array<Instruction> = arrayOf(),
    val updated_at : Long = 0,
)

data class Show(
    val name: String = "",
    val id: Int = 0,
)

data class Instruction(
    val start_time: Int,
    val appliance: String,
    val end_time: Int,
    val temperature: Int,
    val id: Int,
    val position: Int,
    val display_text: String,
)