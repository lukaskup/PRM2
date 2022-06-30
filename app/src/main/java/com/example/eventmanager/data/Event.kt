package com.example.eventmanager.data

import java.util.Date

data class Event(
    var id: String,
    var name: String,
    var location: String,
    var imageUrl: String,
    var date: Date,
)
