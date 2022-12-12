package com.example.timsaverapplication

class Dog {
    // in this very simple approach the properties that appear below are also in the constructor
    var breed: String
    var ave_height_inches: Int
    var colors: String
    var character: String

    // the constructor
    constructor(breed_data: String, ave_height_inches_data: Int, colors_data: String, char_data: String) {
        this.breed = breed_data
        this.ave_height_inches = ave_height_inches_data
        this.colors = colors_data
        this.character = char_data
    }

    // the getters and setters come automatically

}