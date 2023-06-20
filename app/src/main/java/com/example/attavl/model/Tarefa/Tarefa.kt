package com.example.attavl.model.Tarefa
import java.util.Date
class Tarefa( val nome: String,val datalhes:String?,val createDate: Date,val pzoFinal: Date, status: Double) {
    var status = 0.0

    get() {return field}
    set(value){
        field = value
    }
}
