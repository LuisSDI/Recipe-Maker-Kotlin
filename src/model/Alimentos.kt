package model

abstract class Alimentos (var cantidad: Int, var nombre: String) {
    fun GetCantidad(): Int {
        return cantidad
    }
    fun GetNombre():String{
        return nombre
    }
}

 class  Agua (cantidad: Int, nombre: String): Alimentos(cantidad,nombre){
}

class  Frutas (cantidad: Int, nombre: String): Alimentos(cantidad,nombre){
}
class  Verduras (cantidad: Int, nombre: String): Alimentos(cantidad,nombre){
}
class  Lacteos (cantidad: Int, nombre: String): Alimentos(cantidad,nombre){
}
class  Carnes (cantidad: Int, nombre: String): Alimentos(cantidad,nombre){
}
class  Aceites (cantidad: Int, nombre: String): Alimentos(cantidad,nombre){
}
class  Granos (cantidad: Int, nombre: String): Alimentos(cantidad,nombre){
}

class Recipe (var nombre: String , var ingredientes: MutableList<String>) {

    fun agregarIng(ingrediente: String){
        ingredientes.add(ingrediente)
    }

    fun mostrarIng (){
        var index = 1
        for ( ing in ingredientes){
            println("$index. $ing")
            index++
        }
        println("Presione \"Enter\" para salir")
        readLine()
    }
}
