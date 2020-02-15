import model.*

fun main(args:Array<String>) {
    var categorias: Array<String> = arrayOf("Agua",
        "Carne",
        "Verduras",
        "Frutas",
        "Cereal",
        "Aceites","Lacteos")
    var aguas: List<Agua> = listOf(
        Agua(1,"Limon") ,
        Agua(1,"Naranja"),
        Agua(1,"Uva"),
        Agua(1,"Natural"),
        Agua(1,"Horchata")
    )
    var frutas:List<Frutas> = listOf(
        Frutas(1,"Fresa"),
        Frutas(1,"Platano"),
        Frutas(1,"Pera"),
        Frutas(1,"Cereza"),
        Frutas(1,"Manzana")
        )
    var cereales: List<Granos> = listOf(
        Granos(1,"Avena"),
        Granos(1,"Trigo"),
        Granos(1,"Arroz"),
        Granos(1,"Maiz")
    )
    var verduras: List<Verduras> = listOf(
        Verduras(1,"Zanahoria"),
        Verduras(1,"Papa"),
        Verduras(1,"Calabaza"),
        Verduras(1,"Berenjena")
    )
    var aceites: List<Aceites> = listOf(
        Aceites(1,"Maiz"),
        Aceites(1,"Coco"),
        Aceites(1,"Oliva"),
        Aceites(1,"Ballena")
        )

    var lacteos:List<Lacteos> = listOf(
        Lacteos(1,"Leche"),
        Lacteos(1,"Queso"),
        Lacteos(1,"Mantequilla"),
        Lacteos(1,"Yogurt")
        )
    var carnes: List<Carnes> = listOf(
        Carnes(1,"Pollo"),
        Carnes(1,"Pescado"),
        Carnes(1,"Huevo"),
        Carnes(1,"Res")
    )
    var alimentos: List<List<out Alimentos>> = listOf(aguas,
        carnes,
        verduras,
        frutas,
        cereales,
        aceites,lacteos)
    var recetas = mutableListOf(Recipe("Corn Flakes", mutableListOf("Maiz","Leche")),
        Recipe("Huevo Revuelto", mutableListOf("Huevo","Leche","Tocino")),
        Recipe("Hot Cakes", mutableListOf("Harina","Leche","Mantequilla")))
    mostrarMenu(categorias,alimentos,recetas)

}

fun mostrarMenu(categorias: Array<String>, alimentos: List<List<out Alimentos>>,recetas: MutableList<Recipe>){
    var cycle:Boolean = true
    while (cycle){
    println(":: Bienvenido a Recipe Maker ::")
    var instruciones =  """
    Selecciona la opción deseada
    1. Hacer una receta
    2. Ver mis recetas
    3. Salir
    """.trimIndent()
    println(instruciones)
    print("Opcion: ")
        when(readLine()){
            "1" -> {
                makeRecipe(categorias,alimentos)?.let { recetas.add(it) }
                }
            "2" -> {viewRecetas(recetas)
                }
            "3" -> cycle = false
            else -> {println("Opcion invalida")
                println(instruciones)
                println("Escriba una opcion valida: ")
            }
        }
    }
}

fun viewRecetas(recetas: MutableList<Recipe>){
    var cycle = true
    while (cycle) {
        var index = 1
        println("Usted tiene las siguientes recetas: ")
        for ( rec in recetas){
            println("$index. ${rec.nombre}")
            index++
        }
        println("Ingresa el numero de la receta que busca o (S) para salir: ")
        val input: String? = readLine()
        if (input != null) {
            var isnum: Boolean = input.matches("-?\\d+(\\.\\d+)?".toRegex())
            if (isnum) {
                var numinput = input.toInt()
                if (numinput <= 0 || numinput > recetas.size) {
                    println("El numero del ingrediente que busca no existe")
                } else {
                    recetas[numinput-1].mostrarIng()
                }
            }
            else if (input == "S"){
                cycle = false
            }
            else {
                println("Usted no a ingresado un numero")
            }
        }
    }

}

fun makeRecipe (recetas: Array<String>, alimentos: List<List<out Alimentos>>): Recipe? {
    println("""
        Hacer receta
        Cual es el nombre de la receta: """.trimIndent())
    var cycle = true
    var name = readLine()
    var receta = name?.let { Recipe(it, mutableListOf()) }
    println("Agrega ingredientes.")
    receta?.agregarIng(viewRecipe(recetas, alimentos))
    while (cycle){
        println("Desea seguir agregando ingredientres (Y) (N)?")
        var input = readLine()
        if (input == "Y"){
            receta?.agregarIng(viewRecipe(recetas, alimentos))
        }
        else if (input == "N"){
            cycle = false
        }
        else {
         print("Por favor, escriba una opcion valida")
        }
    }
    return  receta
    }

fun viewAlimentos (alimentos: List<out Alimentos>): String{
    var ind:Int = 0
    var cycle = true
    while (cycle) {
        var index = 1
        for ( ing in alimentos){
            println("$index. ${ing.GetNombre()}")
            index++
        }
        println("Ingresa el numero del ingrediente que busca: ")
        val input: String? = readLine()
        if (input != null) {
            var isnum: Boolean = input.matches("-?\\d+(\\.\\d+)?".toRegex())
            if (isnum) {
                var numinput = input.toInt()
                if (numinput <= 0 || numinput > alimentos.size) {
                    println("El numero del ingrediente que busca no existe")
                } else {
                    cycle = false
                    ind = input.toInt()
                }
            }
            else {
                println("Usted no a ingresado un numero")
            }
        }
    }
    return alimentos[ind-1].GetNombre()
}

fun viewRecipe(recetas: Array<String>, alimentos: List<List<out Alimentos>>): String{
    var cycle = true
    var ing: String = ""
    while (cycle){
        var index = 1
        for ( receta in recetas){
            println("$index. $receta")
            index++
        }
    println("Ingresa el numero del ingrediente que busca: ")
    val input: String? = readLine()
    if (input != null) {
        var isnum: Boolean = input.matches("-?\\d+(\\.\\d+)?".toRegex())
        if (isnum) {
            var numinput = input.toInt()
            if (numinput <= 0 || numinput > recetas.size){
                println("El numero de la receta que busca no existe")
            }
            else{
                ing = viewAlimentos(alimentos[input.toInt()-1])
                cycle = false
            }
            }
        else{
            println("Usted no a ingresado un numero")
        }
    }
}
    return ing
}


