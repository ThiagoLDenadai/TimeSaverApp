package com.example.timsaverapplication

class Agendamentos {
    var dia: String
    var todoList = mutableListOf<Todo>()

    //constructor(dia_data: String, todoList_data: MutableList<Todo>) {
    //    this.dia = dia_data
    //    this.todoList = todoList_data
    //}

    constructor(dia_data: String) {
        this.dia = dia_data
    }


}